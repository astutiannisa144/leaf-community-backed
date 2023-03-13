package com.lawencon.leaf.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ActivityDao;
import com.lawencon.leaf.community.dao.UserActivityDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserVoucherDao;
import com.lawencon.leaf.community.model.Activity;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Industry;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserActivity;
import com.lawencon.leaf.community.model.UserVoucher;
import com.lawencon.leaf.community.model.Voucher;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityReq;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class UserActivityService extends AbstractJpaDao {
	@Autowired
	private UserActivityDao userActivityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private final PrincipalService principalService;
	@Autowired
	private UserVoucherDao userVoucherDao;

	public UserActivityService(PrincipalServiceImpl principalServiceImpl) {
		this.principalService = principalServiceImpl;
	}

	public PojoRes insert(PojoUserActivityReq data) {
		ConnHandler.begin();
		String code = GenerateCodeUtil.generateCode(10);
		final UserActivity userActivity = new UserActivity();
		final User member = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		final Activity activity = activityDao.getByIdAndDetach(Activity.class, data.getActivityId());
		if (data.getUserVoucher() != null) {
//			if (userVoucherDao.getRefByMemberAndVoucher(member.getId(), data.getUserVoucher().getVoucherId()).isPresent()) {
//				throw new RuntimeException("Voucher Ini Sudah Pernah dipakai");
//			}
			UserVoucher userVoucher = new UserVoucher();
			userVoucher.setMember(member);
			Voucher voucher = getById(Voucher.class, data.getUserVoucher().getVoucherId());
			userVoucher.setVoucher(voucher);
			UserVoucher userVoucherInsert = save(userVoucher);
			userActivity.setTotalPrice(activity.getPrice().subtract(voucher.getDiscountPrice()));
			userActivity.setUserVoucher(userVoucherInsert);
		} else {
			userActivity.setTotalPrice(activity.getPrice());
		}
		File file = new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setIsActive(true);
		File fileInsert = save(file);
		userActivity.setInvoiceCode(code);
		userActivity.setFile(fileInsert);
		userActivity.setActivity(activity);
		userActivity.setMember(member);
		userActivity.setIsActive(true);
		userActivityDao.save(userActivity);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success insert UserActivity");
		return res;
	}

	public PojoRes update(PojoUserActivityReq data) {
		ConnHandler.begin();

		final UserActivity userActivity = userActivityDao.getByIdAndDetach(UserActivity.class, data.getId());
		final User admin = userDao.getById(User.class, principalService.getAuthPrincipal());
		final Activity activity = activityDao.getByIdAndDetach(Activity.class, userActivity.getActivity().getId());
		final Profile profileAdmin = getByIdAndDetach(Profile.class, admin.getProfile().getId());
		final User member = userDao.getById(User.class, activity.getMember().getId());
		final Profile profileMember = getByIdAndDetach(Profile.class, member.getProfile().getId());
		BigDecimal memberBalance = profileMember.getBalance().add(activity.getPrice().divide(new BigDecimal("0.8")));
		BigDecimal adminBalance = profileAdmin.getBalance().add(userActivity.getTotalPrice().subtract(memberBalance));
		profileMember.setBalance(memberBalance);
		profileAdmin.setBalance(adminBalance);
		save(profileAdmin);
		save(profileMember);
		userActivity.setIsApproved(true);
		userActivity.setIsActive(true);
		userActivityDao.save(userActivity);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success Approved UserActivity");
		return res;
	}

	public PojoUserActivityRes getById(String id) {
		UserActivity userActivity = userActivityDao.getById(id).get();
		final PojoUserActivityRes pojoUserActivityRes = new PojoUserActivityRes();
		pojoUserActivityRes.setActivityName(userActivity.getActivity().getTitle());
		pojoUserActivityRes.setFileId(userActivity.getFile().getId());
		pojoUserActivityRes.setId(userActivity.getId());
		pojoUserActivityRes.setIsApprove(userActivity.getIsApproved());
		pojoUserActivityRes.setMemberName(userActivity.getMember().getProfile().getFullName());
		pojoUserActivityRes.setTotalPrice(userActivity.getTotalPrice());
		pojoUserActivityRes.setVer(userActivity.getVer());
		if(userActivity.getUserVoucher()!=null) {			
			pojoUserActivityRes.setVoucherCode(userActivity.getUserVoucher().getVoucher().getVoucherCode());
		}
		pojoUserActivityRes.setIsActive(userActivity.getIsActive());
		pojoUserActivityRes.setInvoiceCode(userActivity.getInvoiceCode());

		return pojoUserActivityRes;
	}

	public List<PojoUserActivityRes> getAll(String id) {
		final List<PojoUserActivityRes> pojoUserActivityRes = new ArrayList<>();
		List<UserActivity> userActivities = new ArrayList<>();
		
		if(id!=null) {
			userActivities=userActivityDao.getAllByActivityType(id);
		}else {
			userActivities = userActivityDao.getAll();
		}
		for (int i = 0; i < userActivities.size(); i++) {
			PojoUserActivityRes userActivity = new PojoUserActivityRes();
			userActivity.setActivityName(userActivities.get(i).getActivity().getTitle());
			userActivity.setFileId(userActivities.get(i).getFile().getId());
			userActivity.setId(userActivities.get(i).getId());
			userActivity.setIsApprove(userActivities.get(i).getIsApproved());
			userActivity.setMemberName(userActivities.get(i).getMember().getProfile().getFullName());
			userActivity.setTotalPrice(userActivities.get(i).getTotalPrice());
			userActivity.setVer(userActivities.get(i).getVer());
			if(userActivities.get(i).getUserVoucher()!=null) {				
				userActivity.setVoucherCode(userActivities.get(i).getUserVoucher().getVoucher().getVoucherCode());
			}
			userActivity.setInvoiceCode(userActivities.get(i).getInvoiceCode());
			userActivity.setIsActive(userActivities.get(i).getIsActive());

			pojoUserActivityRes.add(userActivity);
		}
		return pojoUserActivityRes;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			userActivityDao.deleteById(Industry.class, id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete User Activity");
		return pojoRes;
	}



}
