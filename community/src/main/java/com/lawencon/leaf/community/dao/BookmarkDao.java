package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Bookmark;

@Repository
public class BookmarkDao extends BaseDao<Bookmark> {

	@Override
	List<Bookmark> getAll() {
		return null;
	}

	@Override
	public Optional<Bookmark> getById(String id) {
		return Optional.ofNullable(super.getById(Bookmark.class, id));
	}

	@Override
	Optional<Bookmark> getByIdAndDetach(String id) {
		return Optional.empty();
	}
	
	public Optional<String> getId(String uid, String pid) {
		String bookmarkId = null;
		
		try {
			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT id FROM t_bookmark ");
			sql.append("WHERE member_id = :uid ");
			sql.append("AND post_id = :pid ");
			
			bookmarkId = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("uid", uid)
					.setParameter("pid", pid)
					.getSingleResult().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(bookmarkId);
	}

}
