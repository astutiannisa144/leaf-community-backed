package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.PremiumDao;
import com.lawencon.leaf.community.model.Premium;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.premium.PojoPremiumReq;
import com.lawencon.leaf.community.pojo.premium.PojoPremiumRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;

@Service
public class PremiumService extends BaseService<PojoPremiumRes> {
	
	@Autowired
	private PremiumDao premiumDao;
	
	@Override
	public PojoPremiumRes getById(String id) {
		final PojoPremiumRes pojoPremiumRes = new PojoPremiumRes();
		Premium premium = premiumDao.getById(id).get();
		pojoPremiumRes.setPremiumCode(premium.getPremiumCode());
		pojoPremiumRes.setPremiumName(premium.getPremiumName());
		pojoPremiumRes.setPrice(premium.getPrice());
		pojoPremiumRes.setDuration(premium.getDuration());
		pojoPremiumRes.setId(id);
		pojoPremiumRes.setVer(premium.getVer());
		pojoPremiumRes.setIsActive(premium.getIsActive());
		pojoPremiumRes.setPrice(premium.getPrice());
		return pojoPremiumRes;
	}

	@Override
	public List<PojoPremiumRes> getAll() {
		final List<PojoPremiumRes> pojoPremiumRes = new ArrayList<>();
		List<Premium> premiums = premiumDao.getAll();
		for(int i=0;i<premiums.size();i++) {
			PojoPremiumRes premium = new PojoPremiumRes();
			premium.setPremiumCode(premiums.get(i).getPremiumCode());
			premium.setPremiumName(premiums.get(i).getPremiumName());
			premium.setPrice(premiums.get(i).getPrice());
			premium.setDuration(premiums.get(i).getDuration());
			premium.setId(premiums.get(i).getId());
			premium.setVer(premiums.get(i).getVer());
			premium.setIsActive(premiums.get(i).getIsActive());
			premium.setPrice(premiums.get(i).getPrice());
			pojoPremiumRes.add(premium);
		}
		return pojoPremiumRes;
	}
	
	public PojoRes insert(PojoPremiumReq data) {
		ConnHandler.begin();

		Premium premium = new Premium();

		premium.setPremiumName(data.getPremiumName());
		premium.setPremiumCode(GenerateCodeUtil.generateCode(10));
		premium.setPrice(data.getPrice());
		premium.setDuration(data.getDuration());
		premium.setIsActive(true);
		premiumDao.save(premium);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build Premium");
		return pojoRes;
	}
	
	public PojoRes update(PojoPremiumReq data) {
		ConnHandler.begin();
		
		Premium premium = premiumDao.getByIdAndDetach(data.getId()).get();
		premium.setId(data.getId());
		if(data.getPremiumName()!=null) {
			premium.setPremiumName(data.getPremiumName());			
		}
		if(data.getPrice()!=null) {
			premium.setPrice(data.getPrice());
		}
		if(data.getDuration()!=null) {
			premium.setDuration(data.getDuration());
		}
		premium.setIsActive(true);
		premiumDao.save(premium);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update Premium");
		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			premiumDao.deleteById(Premium.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete Premium");
		return pojoRes;
	}

}
