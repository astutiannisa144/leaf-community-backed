package com.lawencon.leaf.community.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.dao.PremiumDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserPremiumDao;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Premium;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserPremium;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumReq;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;
import com.lawencon.security.principal.PrincipalService;

public class UserPremiumService extends BaseService<PojoUserPremiumRes> {
	@Autowired
	private UserPremiumDao userPremiumDao;
	@Autowired
	private PremiumDao premiumDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;
	
	private final PrincipalService principalService;
	public UserPremiumService(PrincipalService principalService) {
		this.principalService=principalService;
	}
	
	@Override
	PojoUserPremiumRes getById(String id) {
		UserPremium userPremium = userPremiumDao.getById(UserPremium.class, id);
		PojoUserPremiumRes userPremiumRes = new PojoUserPremiumRes();
		userPremiumRes.setId(userPremium.getId());
		userPremiumRes.setMemberName(userPremium.getMember().getProfile().getFullName());
		userPremiumRes.setExpireDate(userPremium.getExpireDate());
		userPremiumRes.setIsActive(userPremium.getIsActive());
		userPremiumRes.setMemberId(userPremium.getMember().getId());
		userPremiumRes.setVer(userPremium.getVer());
		userPremiumRes.setPremiumId(userPremium.getPremium().getId());
		userPremiumRes.setPremiumName(userPremium.getPremium().getPremiumName());
		return userPremiumRes;
	}

	@Override
	List<PojoUserPremiumRes> getAll() {
		List<UserPremium> userPremiums = userPremiumDao.getAll();
		List<PojoUserPremiumRes> userPremiumRes = new ArrayList<>();
		for(int i=0;i<userPremiums.size();i++) {
			PojoUserPremiumRes userPremium = new PojoUserPremiumRes();
			userPremium.setId(userPremiums.get(i).getId());
			userPremium.setMemberName(userPremiums.get(i).getMember().getProfile().getFullName());
			userPremium.setExpireDate(userPremiums.get(i).getExpireDate());
			userPremium.setIsActive(userPremiums.get(i).getIsActive());
			userPremium.setMemberId(userPremiums.get(i).getMember().getId());
			userPremium.setVer(userPremiums.get(i).getVer());
			userPremium.setPremiumId(userPremiums.get(i).getPremium().getId());
			userPremium.setPremiumName(userPremiums.get(i).getPremium().getPremiumName());
			userPremiumRes.add(userPremium);
		}
		return userPremiumRes;
	}
	
	public PojoRes insert(PojoUserPremiumReq data) {
		ConnHandler.begin();

		UserPremium userPremium = new UserPremium();
		Premium premium = premiumDao.getById(data.getPremiumId()).get();
		userPremium.setPremium(premium);
		User member = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		userPremium.setMember(member);
		
		File file =new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setIsActive(true);
		File fileInsert = fileDao.save(file);
		userPremium.setFile(fileInsert);
		userPremium.setIsActive(true);
		userPremiumDao.save(userPremium);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build UserPremium");
		return pojoRes;
	}
	
	public PojoRes update(PojoUserPremiumReq data) {
		ConnHandler.begin();
	
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update UserPremium");
		return pojoRes;
	}
	
}
