package com.lawencon.leaf.community.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.ProfileSocialMedia;
import com.lawencon.leaf.community.model.SocialMedia;

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
	
	@SuppressWarnings("unchecked")
	public List<ProfileSocialMedia> getAllByProfileId(String id) {
		final List<ProfileSocialMedia> socialMedias = new ArrayList<>();
		
			
		final StringBuilder str = new StringBuilder();
		
		str.append("SELECT tp.profile_id,tp.id as tpid,tp.username,tp.social_media_id,tp.ver,tp.is_active,tp.profile_link,sm.id,file_id,social_media_name,social_media_icon,social_media_link ");
		str.append("FROM t_profile_social_media tp ");
		str.append("INNER JOIN t_social_media sm ON tp.social_media_id=sm.id ");
		str.append("WHERE profile_id =:id ");
		str.append("UNION ALL ");
		str.append("SELECT null,null,null,null,null,null,null,sm.id,file_id,social_media_name,social_media_icon,social_media_link ");
		str.append("FROM t_social_media sm ");
		str.append("WHERE sm.id NOT IN( ");
		str.append("SELECT tp.social_media_id ");
		str.append("FROM t_profile_social_media tp ");
		str.append("WHERE profile_id =:id) ");
		
		

		final List<Object> result = ConnHandler.getManager().createNativeQuery(str.toString())
				.setParameter("id", id)
				.getResultList();
		if (result != null) {
			for(Object objs : result) {
				ProfileSocialMedia profileSocialMedia = new ProfileSocialMedia();

				final Object[] objArr = (Object[]) objs;
				final Profile profile = new Profile();
				if(objArr[0]!=null) {
					profile.setId(objArr[0].toString());
				}else {
					profile.setId(null);

				}
				
				profileSocialMedia.setProfile(profile);
				if(objArr[1]!=null) {
					profileSocialMedia.setId(objArr[1].toString());
				}else {
					profileSocialMedia.setId(null);

				}
				if(objArr[2]!=null) {
					profileSocialMedia.setUsername(objArr[2].toString());
				}else {
					profileSocialMedia.setUsername(null);

				}
				if(objArr[4]!=null) {
					profileSocialMedia.setVer(Integer.valueOf(objArr[4].toString()) );
				}else {
					profileSocialMedia.setVer(null);

				}
				if(objArr[6]!=null) {
					profileSocialMedia.setProfileLink(objArr[6].toString());
				}else {
					profileSocialMedia.setProfileLink(null);

				}
				SocialMedia socialMedia= new SocialMedia();
				socialMedia.setId(objArr[7].toString());
				File file = new File();
				file.setId(objArr[8].toString());
				socialMedia.setFile(file);
				socialMedia.setSocialMediaName(objArr[9].toString());
				socialMedia.setSocialMediaIcon(objArr[10].toString());
				socialMedia.setSocialMediaLink(objArr[11].toString());
				profileSocialMedia.setSocialMedia(socialMedia);
				socialMedias.add(profileSocialMedia);
			}
			
		}
		return socialMedias;
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
