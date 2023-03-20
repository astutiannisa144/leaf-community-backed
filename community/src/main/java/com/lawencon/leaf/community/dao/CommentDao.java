package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Comment;

@Repository
public class CommentDao extends BaseDao<Comment> {

	@Override
	List<Comment> getAll() {
		return null;
	}

	@Override
	public Optional<Comment> getById(String id) {
		return Optional.ofNullable(super.getById(Comment.class, id));
	}

	@Override
	Optional<Comment> getByIdAndDetach(String id) {
		return Optional.empty();
	}

	public Long countComment(String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM t_comment ");
		sql.append("WHERE post_id  = :id ");

		final Long count = Long.valueOf(ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("id", id).getSingleResult().toString());

		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getByPostId(int limit, int offset, String id) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_comment ");
		sql.append("WHERE post_id = :id ");
		sql.append("ORDER BY created_at DESC ");

		final List<Comment> commentList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Comment.class)
				.setFirstResult(offset)
				.setMaxResults(limit).setParameter("id", id)
				.getResultList();

		return commentList;
	}

}
