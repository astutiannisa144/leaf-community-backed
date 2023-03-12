package com.lawencon.leaf.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.PollingDetailDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserPollingDao;
import com.lawencon.leaf.community.model.PollingDetail;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserPolling;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.polling.PojoUserPollingReqInsert;
import com.lawencon.security.principal.PrincipalService;

@Service
public class UserPollingService {

	private final UserPollingDao userPollingDao;
	private final PollingDetailDao pollingDetailDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public UserPollingService(UserPollingDao userPollingDao, PollingDetailDao pollingDetailDao, UserDao userDao,
			PrincipalService principalService) {
		this.userPollingDao = userPollingDao;
		this.pollingDetailDao = pollingDetailDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	public PojoRes insert(PojoUserPollingReqInsert data) {
		ConnHandler.begin();

		final UserPolling userPolling = new UserPolling();
		final PollingDetail pollingDetail = pollingDetailDao.getById(PollingDetail.class, data.getPollingDetailId());
		userPolling.setPollingDetail(pollingDetail);

		final User user = userDao.getById(principalService.getAuthPrincipal()).get();
		userPolling.setMember(user);

		userPollingDao.save(userPolling);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Success insert UserPolling");
		return res;
	}

}
