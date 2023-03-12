package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.CategoryDao;
import com.lawencon.leaf.community.dao.CommentDao;
import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.dao.LikeDao;
import com.lawencon.leaf.community.dao.PollingDao;
import com.lawencon.leaf.community.dao.PollingDetailDao;
import com.lawencon.leaf.community.dao.PostDao;
import com.lawencon.leaf.community.dao.PostFileDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.model.Category;
import com.lawencon.leaf.community.model.Comment;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Polling;
import com.lawencon.leaf.community.model.PollingDetail;
import com.lawencon.leaf.community.model.Post;
import com.lawencon.leaf.community.model.PostFile;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.pojo.polling.PollingDetailRes;
import com.lawencon.leaf.community.pojo.polling.PollingResGet;
import com.lawencon.leaf.community.pojo.post.PojoPostReqInsert;
import com.lawencon.leaf.community.pojo.post.PojoPostReqUpdate;
import com.lawencon.leaf.community.pojo.post.PojoPostResGetAll;
import com.lawencon.leaf.community.util.DateUtil;
import com.lawencon.leaf.community.util.GenerateCodeUtil;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class PostService {

	private final PostDao postDao;
	private final CategoryDao categoryDao;
	private final PollingDao pollingDao;
	private final PollingDetailDao pollingDetailDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	private final FileDao fileDao;
	private final PostFileDao postFileDao;
	private final LikeDao likeDao;
	private final CommentDao commentDao;

	public PostService(PostDao postDao, CategoryDao categoryDao, PollingDao pollingDao,
			PollingDetailDao pollingDetailDao, UserDao userDao, PrincipalServiceImpl principalServiceImpl,
			FileDao fileDao, PostFileDao postFileDao, LikeDao likeDao, CommentDao commentDao) {
		this.postDao = postDao;
		this.categoryDao = categoryDao;
		this.pollingDao = pollingDao;
		this.pollingDetailDao = pollingDetailDao;
		this.userDao = userDao;
		this.principalService = principalServiceImpl;
		this.fileDao = fileDao;
		this.postFileDao = postFileDao;
		this.likeDao = likeDao;
		this.commentDao = commentDao;
	}

	public PojoRes insert(final PojoPostReqInsert data) {

		ConnHandler.begin();

		Post postInsert = new Post();
		postInsert.setPostCode(GenerateCodeUtil.generateCode(10));
		postInsert.setTitle(data.getTitle());
		postInsert.setContent(data.getContent());
		postInsert.setIsPremium(data.getIsPremium());

		final Category category = categoryDao.getById(Category.class, data.getCategoryId());
		postInsert.setCategory(category);

		final User user = userDao.getById(principalService.getAuthPrincipal()).get();
		postInsert.setMember(user);

		Polling polling = new Polling();
		polling.setContent(data.getPolling().getContent());

		polling = pollingDao.save(polling);

		postInsert.setPolling(polling);

		if (data.getPolling() != null) {
			for (int i = 0; i < data.getPolling().getPollingDetail().size(); i++) {
				final PollingDetail pollingDetail = new PollingDetail();
				pollingDetail.setPolling(polling);
				pollingDetail.setContent(data.getPolling().getPollingDetail().get(i).getContent());
				pollingDetailDao.save(pollingDetail);
			}
		}

		postInsert = postDao.save(postInsert);

		if (data.getFile() != null) {
			for (int i = 0; i < data.getFile().size(); i++) {
				File file = new File();
				file.setFileContent(data.getFile().get(i).getFileContent());
				file.setFileExtension(data.getFile().get(i).getFileExtension());
				file = fileDao.save(file);

				final PostFile postFile = new PostFile();
				postFile.setFile(file);
				postFile.setPost(postInsert);
				postFileDao.save(postFile);
			}
		}

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Post created");
		return res;

	}

	public PojoRes update(PojoPostReqUpdate data) {
		ConnHandler.begin();

		final Post postInsert = postDao.getById(data.getPostId()).get();
		postInsert.setTitle(data.getTitle());
		postInsert.setContent(data.getContent());

		postDao.save(postInsert);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Post edited");
		return res;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			postDao.deleteById(Comment.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Comment Deleted");
		return pojoRes;
	}

	public List<PojoPostResGetAll> getAll(int limit, int offset, String code) {
		List<Post> postList = new ArrayList<>();

		if (code == null) {
			postList = postDao.getAll(limit, offset);

		} else if ("profile".equals(code)) {
			postList = postDao.getAllByUser(limit, offset, principalService.getAuthPrincipal());

		} else if ("like".equals(code)) {
			postList = postDao.getAllByLike(limit, offset, principalService.getAuthPrincipal());

		} else if ("bookmark".equals(code)) {
			postList = postDao.getAllByBookmark(limit, offset, principalService.getAuthPrincipal());
		}

		final List<PojoPostResGetAll> resList = new ArrayList<>();

		for (int i = 0; i < postList.size(); i++) {
			final PojoPostResGetAll res = new PojoPostResGetAll();
			res.setPostId(postList.get(i).getId());
			res.setTitle(postList.get(i).getTitle());
			res.setContent(postList.get(i).getContent());
			res.setIsPremium(postList.get(i).getIsPremium());
			res.setCategoryId(postList.get(i).getCategory().getId());
			res.setMemberId(postList.get(i).getMember().getId());
			res.setCreatedAt(DateUtil.dateToStr(postList.get(i).getCreatedAt()));
			res.setLikeSum(likeDao.countLike(postList.get(i).getId()));
			res.setCommentSum(commentDao.countComment(postList.get(i).getId()));

			if (postList.get(i).getPolling() != null) {
				final PollingResGet polling = new PollingResGet();
				polling.setPollingId(postList.get(i).getPolling().getId());
				polling.setContent(postList.get(i).getPolling().getContent());
				polling.setExpired(postList.get(i).getPolling().getExpired());

				final List<PollingDetailRes> resPollingDetailList = new ArrayList<>();
				final List<PollingDetail> pollingDetailList = pollingDetailDao
						.getAllByPolling(postList.get(i).getPolling().getId());

				for (int j = 0; j < pollingDetailList.size(); j++) {
					final PollingDetailRes resPollingDetail = new PollingDetailRes();
					resPollingDetail.setPollingDetailId(pollingDetailList.get(j).getId());
					resPollingDetail.setContent(pollingDetailList.get(j).getContent());

					resPollingDetailList.add(resPollingDetail);
				}
				polling.setPollingDetail(resPollingDetailList);
				res.setPolling(polling);
			}

			if (postFileDao.getAllByPost(postList.get(i).getId()).size() > 0) {
				final List<PojoFileRes> fileList = new ArrayList<>();
				final List<PostFile> postFileList = postFileDao.getAllByPost(postList.get(i).getId());

				for (int j = 0; j < postFileList.size(); j++) {
					final PojoFileRes file = new PojoFileRes();
					file.setFileId(postFileList.get(j).getFile().getId());
					file.setFileContent(postFileList.get(j).getFile().getFileContent());
					file.setFileExtension(postFileList.get(j).getFile().getFileExtension());
					fileList.add(file);
				}
				res.setFile(fileList);
			}
			resList.add(res);
		}

		return resList;
	}

}
