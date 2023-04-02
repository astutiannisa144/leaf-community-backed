package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.PositionDao;
import com.lawencon.leaf.community.model.Position;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.position.PojoPositionReq;
import com.lawencon.leaf.community.pojo.position.PojoPositionRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;

@Service
public class PositionService extends BaseService<PojoPositionRes> {
	
	@Autowired
	private PositionDao positionDao;
	
	private void valIdNull(PojoPositionReq position) {
		if (position.getId() != null) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}



	private void valNonBk(PojoPositionReq position) {
		if (position.getPositionName() == null) {
			throw new RuntimeException("Position Name Cannot Be Empty");
		}
	}

	private void valIdNotNull(PojoPositionReq position) {
		if (position.getId() == null) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}

	private void valIdExist(String id) {
		if (positionDao.getById(id).isEmpty()) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}
	
	@Override
	public PojoPositionRes getById(String id) {
		final PojoPositionRes pojoPositionRes = new PojoPositionRes();
		Position position = positionDao.getById(id).get();
		pojoPositionRes.setPositionCode(position.getPositionCode());
		pojoPositionRes.setPositionName(position.getPositionName());
		pojoPositionRes.setId(id);
		pojoPositionRes.setVer(position.getVer());
		pojoPositionRes.setIsActive(position.getIsActive());
		
		return pojoPositionRes;
	}

	@Override
	public List<PojoPositionRes> getAll() {
		final List<PojoPositionRes> pojoPositionRes = new ArrayList<>();
		List<Position> positions = positionDao.getAll();
		for(int i=0;i<positions.size();i++) {
			PojoPositionRes position = new PojoPositionRes();
			position.setPositionCode(positions.get(i).getPositionCode());
			position.setPositionName(positions.get(i).getPositionName());
			position.setId(positions.get(i).getId());
			position.setVer(positions.get(i).getVer());
			position.setIsActive(positions.get(i).getIsActive());
			pojoPositionRes.add(position);
		}
		return pojoPositionRes;
	}
	
	public PojoRes insert(PojoPositionReq data) {
		ConnHandler.begin();
		valIdNull(data);
		valNonBk(data);
	
		Position position = new Position();

		position.setPositionName(data.getPositionName());
		position.setPositionCode(GenerateCodeUtil.generateCode(10));
		position.setIsActive(true);
		

		positionDao.save(position);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Succes Build Position");
		return pojoRes;
	}
	
	public PojoRes update(PojoPositionReq data) {
		ConnHandler.begin();
		valIdNotNull(data);
		valIdExist(data.getId());
		valNonBk(data);
		Position position = positionDao.getById(data.getId()).get();
		position.setId(data.getId());
		position.setPositionName(data.getPositionName());
		position.setIsActive(true);
		positionDao.save(position);
		ConnHandler.commit();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Position updated");
		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			valIdExist(id);
			positionDao.deleteById(Position.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Position deleted");
		return pojoRes;
	}

}
