package com.lawencon.leaf.community.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.PollingDetailDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.UserPollingDao;
import com.lawencon.leaf.community.model.PollingDetail;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.model.UserPolling;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.polling.PollingDetailRes;
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

		UserPolling userPolling = new UserPolling();
		final PollingDetail pollingDetail = pollingDetailDao.getById(PollingDetail.class, data.getPollingDetailId());
		userPolling.setPollingDetail(pollingDetail);
		userPolling.setPolling(pollingDetail.getPolling());

		final User user = userDao.getById(principalService.getAuthPrincipal()).get();
		userPolling.setMember(user);

		userPolling = userPollingDao.save(userPolling);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setId(userPolling.getId());
		res.setMessage("Success insert UserPolling");
		return res;
	}

	public List<PollingDetailRes> getPercentage(String id) {
		final List<PollingDetailRes> resList = new ArrayList<>();
		final List<PollingDetail> pollingDetailList = pollingDetailDao.getAllByPolling(id);
		final Long totalPolling = userPollingDao.countTotalPolling(id);

		for (int j = 0; j < pollingDetailList.size(); j++) {
			final PollingDetailRes resPollingDetail = new PollingDetailRes();
			resPollingDetail.setPollingDetailId(pollingDetailList.get(j).getId());
			resPollingDetail.setContent(pollingDetailList.get(j).getContent());

			BigDecimal percentage = BigDecimal
					.valueOf(userPollingDao.countPercentage(pollingDetailList.get(j).getId()) * 100);
			if (totalPolling > 0) {
				percentage = percentage.divide(BigDecimal.valueOf(totalPolling), 2, RoundingMode.HALF_UP);
			} else {
				percentage = BigDecimal.valueOf(0);
			}

			resPollingDetail.setPercentage(percentage);

			resList.add(resPollingDetail);
		}
		return resList;
	}

}
