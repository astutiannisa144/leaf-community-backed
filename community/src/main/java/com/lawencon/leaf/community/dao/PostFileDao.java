package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.PostFile;

@Repository
public class PostFileDao extends BaseDao<PostFile> {

	@Override
	List<PostFile> getAll() {
		return null;
	}

	@Override
	Optional<PostFile> getById(String id) {
		return Optional.empty();
	}

	@Override
	Optional<PostFile> getByIdAndDetach(String id) {
		return Optional.empty();
	}
	
	@SuppressWarnings("unchecked")
	public List<PostFile> getAllByPost(String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_post_file ");
		sql.append("WHERE post_id = :id ");
		
		final List<PostFile> postFileList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), PostFile.class)
				.setParameter("id", id)
				.getResultList();
		
		return postFileList;
	}

}
