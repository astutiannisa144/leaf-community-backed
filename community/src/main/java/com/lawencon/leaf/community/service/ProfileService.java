package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ProfileDao;
import com.lawencon.leaf.community.dao.ProfileSocialMediaDao;
import com.lawencon.leaf.community.dao.SocialMediaDao;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Job;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.ProfileSocialMedia;
import com.lawencon.leaf.community.model.SocialMedia;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.pojo.job.PojoJobRes;
import com.lawencon.leaf.community.pojo.profile.PojoProfileReq;
import com.lawencon.leaf.community.pojo.profile.PojoProfileRes;
import com.lawencon.leaf.community.pojo.profile.social.media.PojoProfileSocialMediaRes;
import com.lawencon.leaf.community.pojo.social.media.PojoSocialMediaRes;
import com.lawencon.security.principal.PrincipalService;

@Service
public class ProfileService extends AbstractJpaDao {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private SocialMediaDao socialMediaDao;
	@Autowired
	private ProfileSocialMediaDao profileSocialMediaDao;
	private final PrincipalService pricipalService;
	
	public ProfileService(PrincipalService pricipalService) {
		this.pricipalService=pricipalService;
	}
	
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
		
		final Job job = getByIdAndDetach(Job.class, data.getJob().getId());
		
		job.setCompanyName(data.getJob().getCompanyName());
		
		for (int i = 0; i < data.getProfileSocialMedia().size(); i++) {
			ProfileSocialMedia profileSocialMedia = new ProfileSocialMedia();
			if (data.getProfileSocialMedia().get(i).getId() != null) {
				profileSocialMedia=getByIdAndDetach(ProfileSocialMedia.class, data.getProfileSocialMedia().get(i).getId());
				if(data.getProfileSocialMedia().get(i).getSocialMediaId()==null) {
					try {
						deleteById(ProfileSocialMedia.class, profileSocialMedia.getId());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				profileSocialMedia.setVer(data.getProfileSocialMedia().get(i).getVer());
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
	
	public PojoProfileRes getById() {
		ConnHandler.begin();
		User user =getById(User.class,pricipalService.getAuthPrincipal() );
		final Profile profile = profileDao.getByIdAndDetach(user.getProfile().getId()).get();
		final PojoProfileRes profileRes = new PojoProfileRes();
		
		profileRes.setId(profile.getId());
		profileRes.setAddress(profile.getAddress());
		profileRes.setBalance(profile.getBalance());
		PojoFileRes file = new PojoFileRes();
		file.setFileId(profile.getFile().getId());
		file.setFileContent(profile.getFile().getFileContent());
		file.setFileExtension(profile.getFile().getFileExtension());
		file.setVer(profile.getFile().getVer());
		profileRes.setFile(file);
		profileRes.setFullName(profile.getFullName());
		profileRes.setPhoneNumber(profile.getPhoneNumber());
		
		List<ProfileSocialMedia> profileSocialMedias = profileSocialMediaDao.getAllByProfileId(user.getProfile().getId());
		List<PojoProfileSocialMediaRes> profileSocialMediasRes = new ArrayList<>();
		System.out.println(profileSocialMedias.size());
		for (int i = 0; i < profileSocialMedias.size(); i++) {
			PojoProfileSocialMediaRes pojoProfileSocialMediaRes = new PojoProfileSocialMediaRes();
			pojoProfileSocialMediaRes.setId(profileSocialMedias.get(i).getId());
			pojoProfileSocialMediaRes.setVer(profileSocialMedias.get(i).getVer());
			pojoProfileSocialMediaRes.setProfileLink(profileSocialMedias.get(i).getProfileLink());
			pojoProfileSocialMediaRes.setSocialMediaId(profileSocialMedias.get(i).getSocialMedia().getId());
			pojoProfileSocialMediaRes.setProfileId(profileSocialMedias.get(i).getProfile().getId());
			pojoProfileSocialMediaRes.setUsername(profileSocialMedias.get(i).getUsername());
			PojoSocialMediaRes socialMedia = new PojoSocialMediaRes();
			socialMedia.setId(profileSocialMedias.get(i).getSocialMedia().getId());
			socialMedia.setSocialMediaName(profileSocialMedias.get(i).getSocialMedia().getSocialMediaName());
			socialMedia.setSocialMediaIcon(profileSocialMedias.get(i).getSocialMedia().getSocialMediaIcon());
			socialMedia.setSocialMediaLink(profileSocialMedias.get(i).getSocialMedia().getSocialMediaLink());
			File fileInsert = new File();
			fileInsert.setId(profileSocialMedias.get(i).getSocialMedia().getFile().getId());
			socialMedia.setFile(file);
			pojoProfileSocialMediaRes.setSocialMedia(socialMedia);
			profileSocialMediasRes.add(pojoProfileSocialMediaRes);
		}
		profileRes.setProfileSocialMedia(profileSocialMediasRes);
		
		PojoJobRes job = new PojoJobRes();
		job.setId(profile.getJob().getId());
		job.setVer(profile.getJob().getVer());
		job.setCompanyName(profile.getJob().getCompanyName());
		job.setIndustryId(profile.getJob().getIndustry().getId());
		job.setIndustryName(profile.getJob().getIndustry().getIndustryName());
		job.setPositionId(profile.getJob().getPosition().getId());
		job.setPositionName(profile.getJob().getPosition().getPositionName());
		profileRes.setJob(job);
		ConnHandler.commit();

		return profileRes;
	}
	
	
	
}
