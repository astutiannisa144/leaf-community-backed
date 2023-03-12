package com.lawencon.leaf.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.CommentDao;
import com.lawencon.leaf.community.dao.PostDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.model.Comment;
import com.lawencon.leaf.community.model.Post;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.comment.PojoCommentReqInsert;
import com.lawencon.leaf.community.pojo.comment.PojoCommentReqUpdate;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CommentService {

	private final CommentDao commentDao;
	private final PostDao postDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public CommentService(CommentDao commentDao, PostDao postDao, UserDao userDao, PrincipalService principalService) {
		this.commentDao = commentDao;
		this.postDao = postDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	public PojoRes insert(PojoCommentReqInsert data) {
		ConnHandler.begin();

		final Comment comment = new Comment();
		final Post post = postDao.getById(Post.class, data.getPostId());
		comment.setContent(data.getContent());
		comment.setPost(post);

		final User user = userDao.getById(principalService.getAuthPrincipal()).get();
		comment.setMember(user);

		commentDao.save(comment);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success comment a post");
		return res;
	}

	public PojoRes update(PojoCommentReqUpdate data) {
		ConnHandler.begin();

		final Comment comment = commentDao.getById(data.getCommentId()).get();
		comment.setContent(data.getContent());

		commentDao.save(comment);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Comment updated");
		return res;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			commentDao.deleteById(Comment.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Comment Deleted");
		return pojoRes;
	}

}
