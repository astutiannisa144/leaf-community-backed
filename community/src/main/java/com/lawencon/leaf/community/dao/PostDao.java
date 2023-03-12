package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Post;

@Repository
public class PostDao extends BaseDao<Post> {

	@SuppressWarnings("unchecked")
	public List<Post> getAll(int limit, int offset) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_post ");
		sql.append("ORDER BY created_at DESC ");
		
		final List<Post> postList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Post.class)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();

		return postList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getAllByUser(int limit, int offset, String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_post p ");
		sql.append("WHERE p.member_id = :id ");
		sql.append("ORDER BY p.created_at DESC ");

		final List<Post> postList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Post.class)
				.setParameter("id", id)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();

		return postList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getAllByLike(int limit, int offset, String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_post p ");
		sql.append("INNER JOIN t_like l ON l.post_id = p.id ");
		sql.append("WHERE l.member_id = :id ");
		sql.append("ORDER BY p.created_at DESC ");

		final List<Post> postList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Post.class)
				.setParameter("id", id)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();

		return postList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getAllByBookmark(int limit, int offset, String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_post p ");
		sql.append("INNER JOIN t_bookmark b ON b.post_id = p.id ");
		sql.append("WHERE b.member_id = :id ");
		sql.append("ORDER BY p.created_at DESC ");

		final List<Post> postList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Post.class)
				.setParameter("id", id)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();

		return postList;
	}

	@Override
	public Optional<Post> getById(String id) {
		return Optional.ofNullable(super.getById(Post.class, id));
	}

	@Override
	Optional<Post> getByIdAndDetach(String id) {
		return Optional.empty();
	}

	@Override
	List<Post> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
