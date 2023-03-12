package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.IndustryDao;
import com.lawencon.leaf.community.model.Industry;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.industry.PojoIndustryReq;
import com.lawencon.leaf.community.pojo.industry.PojoIndustryRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;

@Service
public class IndustryService extends BaseService<PojoIndustryRes> {
	
	@Autowired
	private IndustryDao industryDao;
	
	@Override
	public PojoIndustryRes getById(String id) {
		final PojoIndustryRes pojoIndustryRes = new PojoIndustryRes();
		Industry industry = industryDao.getById(id).get();
		pojoIndustryRes.setIndustryCode(industry.getIndustryCode());
		pojoIndustryRes.setIndustryName(industry.getIndustryName());
		pojoIndustryRes.setId(id);
		pojoIndustryRes.setVer(industry.getVer());
		pojoIndustryRes.setIsActive(industry.getIsActive());
		
		return pojoIndustryRes;
	}

	@Override
	public List<PojoIndustryRes> getAll() {
		final List<PojoIndustryRes> pojoIndustryRes = new ArrayList<>();
		List<Industry> industries = industryDao.getAll();
		for(int i=0;i<industries.size();i++) {
			PojoIndustryRes industry = new PojoIndustryRes();
			industry.setIndustryCode(industries.get(i).getIndustryCode());
			industry.setIndustryName(industries.get(i).getIndustryName());
			industry.setId(industries.get(i).getId());
			industry.setVer(industries.get(i).getVer());
			industry.setIsActive(industries.get(i).getIsActive());
			pojoIndustryRes.add(industry);
		}
		return pojoIndustryRes;
	}
	
	public PojoRes insert(PojoIndustryReq data) {
		ConnHandler.begin();

		Industry industry = new Industry();

		industry.setIndustryName(data.getIndustryName());
		industry.setIndustryCode(GenerateCodeUtil.generateCode(10));
		industry.setIsActive(true);
		industryDao.save(industry);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build Industry");
		return pojoRes;
	}
	
	public PojoRes update(PojoIndustryReq data) {
		ConnHandler.begin();
		
		Industry industry = industryDao.getById(data.getId()).get();
		industry.setId(data.getId());
		industry.setIndustryName(data.getIndustryName());
		industry.setIsActive(true);
		industryDao.save(industry);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Update Industry");
		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			industryDao.deleteById(Industry.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Delete Industry");
		return pojoRes;
	}

}
