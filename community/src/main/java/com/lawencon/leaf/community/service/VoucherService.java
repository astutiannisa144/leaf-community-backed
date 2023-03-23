package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ActivityDao;
import com.lawencon.leaf.community.dao.UserVoucherDao;
import com.lawencon.leaf.community.dao.VoucherDao;
import com.lawencon.leaf.community.model.Activity;
import com.lawencon.leaf.community.model.Voucher;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.voucher.PojoVoucherReq;
import com.lawencon.leaf.community.pojo.voucher.PojoVoucherRes;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class VoucherService extends BaseService<PojoVoucherRes> {
	
	@Autowired
	private VoucherDao voucherDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private UserVoucherDao uservoucherDao;
	private final PrincipalServiceImpl principalService;
	
	public VoucherService(PrincipalServiceImpl principalServiceImpl) {
		this.principalService = principalServiceImpl;
	}
	@Override
	public PojoVoucherRes getById(String id) {
		final PojoVoucherRes pojoVoucherRes = new PojoVoucherRes();
		Voucher voucher = voucherDao.getById(id).get();
		pojoVoucherRes.setVoucherCode(voucher.getVoucherCode());
		pojoVoucherRes.setDiscountPrice(voucher.getDiscountPrice());
		pojoVoucherRes.setMinimumPurchase(voucher.getMinimumPurchase());
		pojoVoucherRes.setExpiredDate(voucher.getExpiredDate());
		pojoVoucherRes.setId(id);
		pojoVoucherRes.setVer(voucher.getVer());
		pojoVoucherRes.setIsActive(voucher.getIsActive());
		
		return pojoVoucherRes;
	}
	
	public PojoVoucherRes getByCode(String code,String activityId) throws JsonProcessingException {
		final PojoVoucherRes pojoVoucherRes = new PojoVoucherRes();
		if(voucherDao.getByCode(code).isPresent()) {
			Voucher voucher = voucherDao.getByCode(code).get();
			if(uservoucherDao.getRefByMemberAndVoucher(principalService.getAuthPrincipal(), voucher.getId()).isPresent()) {
				pojoVoucherRes.setCodeWarning("You Already Used this voucher Before Unable to Purchase this voucher");			
				throw new RuntimeException(new ObjectMapper().writeValueAsString(pojoVoucherRes));
			
			}
			
			Activity activity = activityDao.getById(activityId).get();
			if(voucher.getMinimumPurchase().compareTo(activity.getPrice())>0 ) {
				pojoVoucherRes.setCodeWarning("Minimum pembelian melewati harga ");			
				throw new RuntimeException(new ObjectMapper().writeValueAsString(pojoVoucherRes));
			}
			pojoVoucherRes.setVoucherCode(voucher.getVoucherCode());
			pojoVoucherRes.setDiscountPrice(voucher.getDiscountPrice());
			pojoVoucherRes.setMinimumPurchase(voucher.getMinimumPurchase());
			pojoVoucherRes.setExpiredDate(voucher.getExpiredDate());
			pojoVoucherRes.setId(voucher.getId());
			pojoVoucherRes.setVer(voucher.getVer());
			pojoVoucherRes.setIsActive(voucher.getIsActive());
		}else {
			pojoVoucherRes.setCodeWarning("Voucher Tidak Ditemukan");			
			throw new RuntimeException(new ObjectMapper().writeValueAsString(pojoVoucherRes));
		}
		
		
		return pojoVoucherRes;
	}
	@Override
	public List<PojoVoucherRes> getAll() {
		final List<PojoVoucherRes> pojoVoucherRes = new ArrayList<>();
		List<Voucher> vouchers = voucherDao.getAll();
		for(int i=0;i<vouchers.size();i++) {
			PojoVoucherRes voucher = new PojoVoucherRes();
			voucher.setVoucherCode(vouchers.get(i).getVoucherCode());
			voucher.setDiscountPrice(vouchers.get(i).getDiscountPrice());
			voucher.setExpiredDate(vouchers.get(i).getExpiredDate());
			voucher.setMinimumPurchase(vouchers.get(i).getMinimumPurchase());
			voucher.setId(vouchers.get(i).getId());
			voucher.setVer(vouchers.get(i).getVer());
			voucher.setIsActive(vouchers.get(i).getIsActive());
			pojoVoucherRes.add(voucher);
		}
		return pojoVoucherRes;
	}
	
	public PojoRes insert(PojoVoucherReq data) {
		ConnHandler.begin();

		Voucher voucher = new Voucher();

		voucher.setVoucherCode(data.getVoucherCode());
		voucher.setDiscountPrice(data.getDiscountPrice());
		voucher.setExpiredDate( data.getExpiredDate());
		voucher.setMinimumPurchase(data.getMinimumPurchase());
		voucher.setIsActive(true);
		voucherDao.save(voucher);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build Voucher");
		return pojoRes;
	}
	
	public PojoRes update(PojoVoucherReq data) {
		ConnHandler.begin();
		
		Voucher voucher = voucherDao.getByIdAndDetach(data.getId()).get();
		voucher.setId(data.getId());

		if(data.getDiscountPrice()!=null) {
			voucher.setDiscountPrice(data.getDiscountPrice());
		}
		if(data.getExpiredDate()!=null) {
			voucher.setExpiredDate(data.getExpiredDate());
		}
		if(data.getMinimumPurchase()!=null) {
			voucher.setMinimumPurchase(data.getMinimumPurchase());

		}
		voucher.setIsActive(true);
		voucherDao.save(voucher);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update Voucher");
		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			voucherDao.deleteById(Voucher.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete Voucher");
		return pojoRes;
	}

}
