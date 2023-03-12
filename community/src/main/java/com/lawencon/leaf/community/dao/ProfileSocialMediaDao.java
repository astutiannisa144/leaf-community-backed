package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.ProfileSocialMedia;

@Repository
public class ProfileSocialMediaDao extends BaseDao<ProfileSocialMedia> {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProfileSocialMedia> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_profile_social_media");

		final List<ProfileSocialMedia> activities = ConnHandler.getManager().createNativeQuery(str.toString(), ProfileSocialMedia.class).getResultList();
		return activities;
	}

	@Override
	public Optional<ProfileSocialMedia> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(ProfileSocialMedia.class, id));
	}

	@Override
	public Optional<ProfileSocialMedia> getById(String id) {

		return Optional.ofNullable(super.getById(ProfileSocialMedia.class, id));

	}
}
