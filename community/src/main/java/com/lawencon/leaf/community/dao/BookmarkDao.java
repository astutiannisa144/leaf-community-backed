package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

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

}
