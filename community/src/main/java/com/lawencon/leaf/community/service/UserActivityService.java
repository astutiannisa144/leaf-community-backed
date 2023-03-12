package com.lawencon.leaf.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ActivityDao;
import com.lawencon.leaf.community.dao.UserActivityDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserVoucherDao;
import com.lawencon.leaf.community.model.Activity;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserActivity;
import com.lawencon.leaf.community.model.UserVoucher;
import com.lawencon.leaf.community.model.Voucher;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityReq;
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
		this.principalService=principalServiceImpl;
	}

	public PojoRes insert(PojoUserActivityReq data) {
		ConnHandler.begin();
		
		final UserActivity userActivity = new UserActivity();
		final User member = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		final Activity activity = activityDao.getByIdAndDetach(Activity.class, data.getActivityId());
		if(data.getUserVoucher()!=null) {
			if(userVoucherDao.getRefByMemberAndVoucher(member.getId(), data.getUserVoucher().getVoucherId()).isPresent()) {
				throw new RuntimeException("Voucher Ini Sudah Pernah dipakai");
			}
			UserVoucher userVoucher = new UserVoucher();
			userVoucher.setMember(member);
			Voucher voucher = getById(Voucher.class, data.getUserVoucher().getVoucherId());
			userVoucher.setVoucher(voucher);
			userActivity.setTotalPrice(activity.getPrice().subtract(voucher.getDiscountPrice()) );
			userActivity.setUserVoucher(userVoucher);
		}
		userActivity.setActivity(activity);
		userActivity.setMember(member);
		userActivity.setIsActive(true);
		userActivityDao.save(userActivity);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success insert UserActivity");
		return res;
	}



}
