package com.lawencon.leaf.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.constant.EnumRole;
import com.lawencon.leaf.community.dao.ActivityDao;
import com.lawencon.leaf.community.dao.UserActivityDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.VoucherDao;
import com.lawencon.leaf.community.model.Activity;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserActivity;
import com.lawencon.leaf.community.model.UserVoucher;
import com.lawencon.leaf.community.model.Voucher;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.report.PojoActivityIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoActivityParticipantRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberParticipantRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityDataRes;
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
	private VoucherDao voucherDao;
	private static final BigDecimal percent = new BigDecimal(0.8);

	public UserActivityService(PrincipalServiceImpl principalServiceImpl) {
		this.principalService = principalServiceImpl;
	}

	private void valIdNull(UserActivity userActivity) {
		if (userActivity.getId() != null) {
			throw new RuntimeException("Id Harus Kosong");
		}
	}

	private void valBkNotNull(UserActivity userActivity) {
		if (userActivity.getInvoiceCode() == null) {
			throw new RuntimeException("Invoice Code Tidak Boleh Kosong");
		}
	}


	private void valNonBk(UserActivity userActivity) {
		if (userActivity.getMember() == null) {
			throw new RuntimeException("Member Tidak Boleh Kosong");
		}
		if (userActivity.getTotalPrice() == null) {
			throw new RuntimeException("Price Tidak Boleh Kosong");
		}
		if (userActivity.getActivity() == null) {
			throw new RuntimeException("Activity Tidak Boleh Kosong");
		}
		if ((userActivity.getIsActive() == null)) {
			throw new RuntimeException("Active Tidak Boleh Kosong");

		}

	}

	private void valIdNotNull(UserActivity userActivity) {
		if (userActivity.getId() == null) {
			throw new RuntimeException("Id Tidak Boleh Kosong");
		}
	}

	private void valIdExist(String id) {
		if (userActivityDao.getById(id).isEmpty()) {
			throw new RuntimeException("Id Tidak Boleh Kosong");
		}
	}

	public PojoRes insert(PojoUserActivityReq data) {
		ConnHandler.begin();
		String code = GenerateCodeUtil.generateCode(10);
		final UserActivity userActivity = new UserActivity();
		final User member = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		final Activity activity = activityDao.getByIdAndDetach(Activity.class, data.getActivityId());
		if (data.getVoucherCode() != null) {
			Voucher voucher = voucherDao.getByCode(data.getVoucherCode()).get();

			if (voucher.getMinimumPurchase().compareTo(activity.getPrice()) == 1) {
				throw new RuntimeException("This Voucher Cant be used for this purchase");
			}
			UserVoucher userVoucher = new UserVoucher();
			userVoucher.setMember(member);
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

		valIdNull(userActivity);
		valNonBk(userActivity);

		userActivityDao.save(userActivity);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success insert UserActivity");
		return res;
	}

	public PojoRes update(PojoUserActivityReq data) {
		ConnHandler.begin();
		final UserActivity userActivity = userActivityDao.getByIdAndDetach(UserActivity.class, data.getId());
		if(data.getIsApprove()!=null) {
			if(data.getIsApprove()==false) {
				userActivity.setIsApproved(false);
				userActivityDao.save(userActivity);
				final PojoRes res = new PojoRes();
				res.setMessage("Success Reject Transaction");
				return res;
			}
		}
		
		if (userActivity.getVer() > 0) {
			throw new RuntimeException("Anda Sudah Approve Transaksi ini TIdak bisa approve lagi");
		}
		User admin = userDao.getUserByRole(EnumRole.SY.getCode()).get();
		final Activity activity = activityDao.getByIdAndDetach(Activity.class, userActivity.getActivity().getId());
		final Profile profileAdmin = getByIdAndDetach(Profile.class, admin.getProfile().getId());
		final User member = userDao.getById(User.class, activity.getMember().getId());
		final Profile profileMember = getByIdAndDetach(Profile.class, member.getProfile().getId());
		BigDecimal priceMember = activity.getPrice().multiply(percent);
		BigDecimal memberBalance = profileMember.getBalance().add(priceMember);
		BigDecimal adminBalance = profileAdmin.getBalance().add(userActivity.getTotalPrice().subtract(priceMember));

		profileMember.setBalance(memberBalance);
		profileAdmin.setBalance(adminBalance);
		save(profileAdmin);
		save(profileMember);
		userActivity.setIsApproved(true);
		userActivity.setIsActive(true);

		valIdExist(userActivity.getId());
		valIdNotNull(userActivity);
		valBkNotNull(userActivity);
//		valBkNotChange(userActivity);
		valNonBk(userActivity);

		userActivityDao.save(userActivity);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success Approved UserActivity");
		return res;
	}

	public PojoUserActivityDataRes getById(String id) {
		
		UserActivity userActivity = userActivityDao.getById(id).get();
		final PojoUserActivityDataRes pojoUserActivityRes = new PojoUserActivityDataRes();
		pojoUserActivityRes.setActivityName(userActivity.getActivity().getTitle());
		pojoUserActivityRes.setFileId(userActivity.getFile().getId());
		pojoUserActivityRes.setId(userActivity.getId());
		pojoUserActivityRes.setIsApprove(userActivity.getIsApproved());
		pojoUserActivityRes.setMemberName(userActivity.getMember().getProfile().getFullName());
		pojoUserActivityRes.setTotalPrice(userActivity.getTotalPrice());
		pojoUserActivityRes.setVer(userActivity.getVer());
		if (userActivity.getUserVoucher() != null) {
			pojoUserActivityRes.setVoucherCode(userActivity.getUserVoucher().getVoucher().getVoucherCode());
		}
		pojoUserActivityRes.setIsActive(userActivity.getIsActive());
		pojoUserActivityRes.setInvoiceCode(userActivity.getInvoiceCode());

		return pojoUserActivityRes;
	}

	public PojoUserActivityRes getAll(int limit, int offset,String typeCode, String code) {
		final PojoUserActivityRes activityRes= new PojoUserActivityRes();
		final List<PojoUserActivityDataRes> pojoUserActivityRes = new ArrayList<>();
		List<UserActivity> userActivities = new ArrayList<>();

		if (code == null && typeCode == null) {
			userActivities = userActivityDao.getAll(limit,offset);

		} else if (typeCode != null && code == null) {
			userActivities = userActivityDao.getAllByActivityType(typeCode,limit,offset);
		} else if (code.equals("profile") && typeCode == null) {

			userActivities = userActivityDao.getAllByActivityPurchased(principalService.getAuthPrincipal(),limit,offset);
		} else if (code.equals("profile") && typeCode != null) {
			userActivities = userActivityDao.getAllByActivityByTypeAndMember(typeCode,
					principalService.getAuthPrincipal(),limit,offset);

		} else if (code.equals("non") && typeCode != null) {
			userActivities = userActivityDao.getAllByActivityTypeNotPurchase(typeCode,limit,offset);
		}

		for (int i = 0; i < userActivities.size(); i++) {
			PojoUserActivityDataRes userActivity = new PojoUserActivityDataRes();
			userActivity.setActivityName(userActivities.get(i).getActivity().getTitle());
			userActivity.setActivityId(userActivities.get(i).getActivity().getId());
			userActivity.setFileId(userActivities.get(i).getFile().getId());
			userActivity.setId(userActivities.get(i).getId());
			userActivity.setIsApprove(userActivities.get(i).getIsApproved());
			userActivity.setMemberName(userActivities.get(i).getMember().getProfile().getFullName());
			userActivity.setTotalPrice(userActivities.get(i).getTotalPrice());
			userActivity.setVer(userActivities.get(i).getVer());
			if (userActivities.get(i).getUserVoucher() != null) {
				userActivity.setVoucherCode(userActivities.get(i).getUserVoucher().getVoucher().getVoucherCode());
			}
			userActivity.setInvoiceCode(userActivities.get(i).getInvoiceCode());
			userActivity.setIsActive(userActivities.get(i).getIsActive());
			if(typeCode != null) {
				userActivity.setTransactionSum(userActivityDao.countUserActivity(typeCode));

			}
			pojoUserActivityRes.add(userActivity);
		}
		activityRes.setData(pojoUserActivityRes);
		activityRes.setTotal(userActivityDao.countUserActivity(typeCode));
		
		return activityRes;
	}

	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			valIdExist(id);
			userActivityDao.deleteById(UserActivity.class, id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete Transaction");
		return pojoRes;
	}
	
	public List<PojoActivityParticipantRes> getActivityParticipants(String dateStart, String dateEnd) {
		return userActivityDao.getActivityParticipants(dateStart, dateEnd);
	}

	public List<PojoMemberParticipantRes> getMemberParticipants(String dateStart, String dateEnd) {
		return userActivityDao.getMemberParticipants(dateStart, dateEnd);
	}

	public List<PojoActivityIncomeRes> getEventIncome(String dateStart, String dateEnd) {
		return userActivityDao.getEventIncome(dateStart, dateEnd);
	}

	public List<PojoMemberIncomeRes> getMemberIncome(String dateStart, String dateEnd) {
		return userActivityDao.getMemberIncome(dateStart, dateEnd);
	}

}
