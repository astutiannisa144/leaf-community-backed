package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.SocialMedia;

@Repository
public class SocialMediaDao extends BaseDao<SocialMedia> {

	@SuppressWarnings("unchecked")
	@Override
	public List<SocialMedia> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_social_media");

		final List<SocialMedia> socialMedia = ConnHandler.getManager().createNativeQuery(str.toString(), SocialMedia.class).getResultList();
		return socialMedia;
	}
	
	@SuppressWarnings("unchecked")
	public Optional<SocialMedia> getByBk(String code) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_social_media ");
		str.append("WHERE social_media_code = :code");
		final List<SocialMedia> socialMedia = ConnHandler.getManager().createNativeQuery(str.toString(), SocialMedia.class)
				.setParameter("code", code)
				.getResultList();
		return Optional.ofNullable(socialMedia.get(0));
	}
	
	@Override
	public Optional<SocialMedia> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(SocialMedia.class, id));
	}

	@Override
	public Optional<SocialMedia> getById(String id) {

		return Optional.ofNullable(super.getById(SocialMedia.class, id));

	}
}
