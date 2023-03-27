package com.lawencon.leaf.community.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.constant.EnumRole;
import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.dao.PremiumDao;
import com.lawencon.leaf.community.dao.ProfileDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserPremiumDao;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Premium;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserPremium;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumReq;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumRes;
import com.lawencon.security.principal.PrincipalService;

@Service
public class UserPremiumService extends BaseService<PojoUserPremiumRes> {
	@Autowired
	private UserPremiumDao userPremiumDao;
	@Autowired
	private PremiumDao premiumDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private ProfileDao profileDao;
	private final PrincipalService principalService;

	public UserPremiumService(PrincipalService principalService) {
		this.principalService = principalService;
	}

	@Override
	public PojoUserPremiumRes getById(String id) {
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
	
	public Optional<PojoUserPremiumRes> getByUserPurchased(String id) {
		PojoUserPremiumRes userPremiumRes = null;
		try {
			UserPremium userPremium = userPremiumDao.getByUserPurchased(id).get();
			 userPremiumRes = new PojoUserPremiumRes();
			userPremiumRes.setId(userPremium.getId());
		}catch (Exception e) {
		}
		
		return Optional.ofNullable(userPremiumRes);
	}
	
	public List<PojoUserPremiumRes> getAll(int limit, int page, String code) {
		List<UserPremium> userPremiums = new ArrayList<>();
		
		if(code==null) {
			userPremiums = userPremiumDao.getAll(limit,page);

		}
		else if (code.equals("non")) {
			userPremiums = userPremiumDao.getAllNonApproved(limit,page);

		} 
		List<PojoUserPremiumRes> userPremiumRes = new ArrayList<>();
		for (int i = 0; i < userPremiums.size(); i++) {
			PojoUserPremiumRes userPremium = new PojoUserPremiumRes();
			userPremium.setId(userPremiums.get(i).getId());
			userPremium.setMemberName(userPremiums.get(i).getMember().getProfile().getFullName());
			if (userPremiums.get(i).getExpireDate() != null) {
				userPremium.setExpireDate(userPremiums.get(i).getExpireDate());
			}
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
		if(userPremiumDao.getByUser(principalService.getAuthPrincipal()).isPresent()) {
			throw new RuntimeException("You Cant Subscribe to application right now because your last transaction yet to be approved");
		}
		UserPremium userPremium = new UserPremium();
		Premium premium = premiumDao.getById(data.getPremiumId()).get();
		userPremium.setPremium(premium);
		User member = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		userPremium.setMember(member);

		File file = new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
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
		User admin = userDao.getUserByRole(EnumRole.SY.getCode()).get();
		Profile profileAdmin = profileDao.getByIdAndDetach(admin.getProfile().getId()).get();
		UserPremium userPremium = userPremiumDao.getByIdAndDetach(data.getId()).get();
		if (userPremiumDao.getByUserPurchased(userPremium.getMember().getId()).isPresent()) {
			UserPremium userPremiumPurchased = userPremiumDao.getByUserPurchased(userPremium.getMember().getId()).get();
			userPremiumPurchased=userPremiumDao.getByIdAndDetach(userPremiumPurchased.getId()).get();
			Long duration = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(),userPremiumPurchased.getExpireDate()
					);
			System.out.println(duration);

			duration +=Long.valueOf(userPremium.getPremium().getDuration().toString()) ;
			userPremium.setExpireDate(LocalDate.now().plusDays(Integer.valueOf(duration.toString())));
			userPremiumPurchased.setIsActive(false);
			userPremiumDao.save(userPremiumPurchased);
		} else {
			userPremium.setExpireDate(LocalDate.now().plusDays(userPremium.getPremium().getDuration()));
		}
		userPremiumDao.save(userPremium);

		profileAdmin.setBalance(profileAdmin.getBalance().add(userPremium.getPremium().getPrice()));
		profileDao.save(profileAdmin);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Approve UserPremium");
		return pojoRes;
	}

	@Override
	List<PojoUserPremiumRes> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			userPremiumDao.deleteById(UserPremium.class, id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete User Premium");
		return pojoRes;
	}
}
