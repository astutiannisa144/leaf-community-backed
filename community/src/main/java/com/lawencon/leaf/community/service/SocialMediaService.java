package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.dao.SocialMediaDao;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.SocialMedia;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.pojo.social.media.PojoSocialMediaReq;
import com.lawencon.leaf.community.pojo.social.media.PojoSocialMediaRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;

@Service
public class SocialMediaService extends BaseService<PojoSocialMediaRes> {
	
	@Autowired
	private SocialMediaDao socialMediaDao;
	@Autowired
	private FileDao fileDao;
	@Override
	public PojoSocialMediaRes getById(String id) {
		final PojoSocialMediaRes pojoSocialMediaRes = new PojoSocialMediaRes();
		SocialMedia socialMedia = socialMediaDao.getById(id).get();
		pojoSocialMediaRes.setSocialMediaCode(socialMedia.getSocialMediaCode());
		pojoSocialMediaRes.setSocialMediaName(socialMedia.getSocialMediaName());
		pojoSocialMediaRes.setId(id);
		pojoSocialMediaRes.setVer(socialMedia.getVer());
		pojoSocialMediaRes.setIsActive(socialMedia.getIsActive());
		
		return pojoSocialMediaRes;
	}

	@Override
	public List<PojoSocialMediaRes> getAll() {
		final List<PojoSocialMediaRes> pojoSocialMediaRes = new ArrayList<>();
		List<SocialMedia> socialMedias = socialMediaDao.getAll();
		for(int i=0;i<socialMedias.size();i++) {
			PojoSocialMediaRes socialMedia = new PojoSocialMediaRes();
			socialMedia.setSocialMediaCode(socialMedias.get(i).getSocialMediaCode());
			socialMedia.setSocialMediaName(socialMedias.get(i).getSocialMediaName());
			socialMedia.setSocialMediaLink(socialMedias.get(i).getSocialMediaLink());
			socialMedia.setSocialMediaIcon(socialMedias.get(i).getSocialMediaIcon());
			PojoFileRes file = new PojoFileRes();
			file.setFileId(socialMedias.get(i).getFile().getId());
			file.setFileContent(socialMedias.get(i).getFile().getFileContent());
			file.setFileExtension(socialMedias.get(i).getFile().getFileExtension());
			socialMedia.setFile(file);
			socialMedia.setId(socialMedias.get(i).getId());
			socialMedia.setVer(socialMedias.get(i).getVer());
			socialMedia.setIsActive(socialMedias.get(i).getIsActive());
			pojoSocialMediaRes.add(socialMedia);
		}
		return pojoSocialMediaRes;
	}
	
	public PojoRes insert(PojoSocialMediaReq data) {
		ConnHandler.begin();

		SocialMedia socialMedia = new SocialMedia();
		
		File file = new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setIsActive(true);
		File fileInsert = fileDao.save(file);
		socialMedia.setFile(fileInsert);
		
		socialMedia.setSocialMediaName(data.getSocialMediaName());
		socialMedia.setSocialMediaCode(GenerateCodeUtil.generateCode(10));
		socialMedia.setIsActive(true);
		socialMediaDao.save(socialMedia);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build SocialMedia");
		return pojoRes;
	}
	
	public PojoRes update(PojoSocialMediaReq data) {
		ConnHandler.begin();
		
		SocialMedia socialMedia = socialMediaDao.getById(data.getId()).get();
		if(data.getSocialMediaName()!=null) {
			socialMedia.setSocialMediaName(data.getSocialMediaName());
		}
		if(data.getSocialMediaLink()!=null) {
			socialMedia.setSocialMediaLink(data.getSocialMediaLink());
		}
		
		if(data.getFile()!=null) {
			File file = new File();
			file.setFileContent(data.getFile().getFileContent());
			file.setFileExtension(data.getFile().getFileExtension());
			file.setIsActive(true);
			File fileInsert = fileDao.save(file);
			socialMedia.setFile(fileInsert);
		}
		
		
		socialMedia.setIsActive(true);
		socialMediaDao.save(socialMedia);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update SocialMedia");
		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			socialMediaDao.deleteById(SocialMedia.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete SocialMedia");
		return pojoRes;
	}

}
