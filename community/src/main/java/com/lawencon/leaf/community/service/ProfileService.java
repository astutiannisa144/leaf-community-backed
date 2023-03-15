package com.lawencon.leaf.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ProfileDao;
import com.lawencon.leaf.community.dao.ProfileSocialMediaDao;
import com.lawencon.leaf.community.dao.SocialMediaDao;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.ProfileSocialMedia;
import com.lawencon.leaf.community.model.SocialMedia;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.profile.PojoProfileReq;

@Service
public class ProfileService extends AbstractJpaDao {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private SocialMediaDao socialMediaDao;
	@Autowired
	private ProfileSocialMediaDao profileSocialMediaDao;

	public PojoRes update(PojoProfileReq data) {
		ConnHandler.begin();

		final Profile profile = profileDao.getByIdAndDetach(data.getId()).get();
		profile.setId(data.getId());
		profile.setAddress(data.getAddress());
		profile.setFullName(data.getFullName());

		final File file = new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setIsActive(true);
		
		final File fileInsert = save(file);
		profile.setFile(fileInsert);

		profile.setPhoneNumber(data.getPhoneNumber());
		profile.setIsActive(true);
		Profile profileInsert = save(profile);

		for (int i = 0; i < data.getProfileSocialMedia().size(); i++) {
			ProfileSocialMedia profileSocialMedia = new ProfileSocialMedia();
			if (data.getProfileSocialMedia().get(i).getId() != null) {
				profileSocialMedia.setId(data.getProfileSocialMedia().get(i).getId());
				if(data.getProfileSocialMedia().get(i).getSocialMediaId()==null) {
					deleteById(ProfileSocialMedia.class, profileSocialMedia.getId());
				}
			}
			profileSocialMedia.setProfile(profileInsert);
			SocialMedia socialMedia = socialMediaDao.getById(data.getProfileSocialMedia().get(i).getSocialMediaId())
					.get();
			
			profileSocialMedia.setUsername(data.getProfileSocialMedia().get(i).getUsername());
			profileSocialMedia.setSocialMedia(socialMedia);
			profileSocialMedia.setProfileLink(
					profileSocialMedia.getSocialMedia().getSocialMediaLink() + profileSocialMedia.getUsername());
			profileSocialMedia.setIsActive(true);
			profileSocialMediaDao.save(profileSocialMedia);
		}

		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update Profile");
		return pojoRes;
	}
}
