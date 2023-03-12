package com.lawencon.leaf.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.LikeDao;
import com.lawencon.leaf.community.dao.PostDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.model.Like;
import com.lawencon.leaf.community.model.Post;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.like.PojoLikeReqInsert;
import com.lawencon.security.principal.PrincipalService;

@Service
public class LikeService {

	private final LikeDao likeDao;
	private final PostDao postDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public LikeService(LikeDao likeDao, PostDao postDao, UserDao userDao, PrincipalService principalService) {
		this.likeDao = likeDao;
		this.postDao = postDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	public PojoRes insert(PojoLikeReqInsert data) {
		ConnHandler.begin();

		final Like like = new Like();
		final Post post = postDao.getById(data.getPostId()).get();
		like.setPost(post);

		final User user = userDao.getById(principalService.getAuthPrincipal()).get();
		like.setMember(user);

		likeDao.save(like);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Post liked");
		return res;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			likeDao.deleteById(Like.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Like Deleted");
		return pojoRes;
	}

}
