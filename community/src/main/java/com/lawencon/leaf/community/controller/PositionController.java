package com.lawencon.leaf.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.position.PojoPositionReq;
import com.lawencon.leaf.community.pojo.position.PojoPositionRes;
import com.lawencon.leaf.community.service.PositionService;

@RestController

@RequestMapping("positions")
public class PositionController {
	private PositionService positionService;

	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoPositionRes>> getAll() throws Exception {
		final List<PojoPositionRes> result = positionService.getAll();
		return new ResponseEntity<List<PojoPositionRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoPositionRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoPositionRes result = positionService.getById(id);
		return new ResponseEntity<PojoPositionRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoPositionReq data) {
		final PojoRes res = positionService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoPositionReq data) {
		final PojoRes res = positionService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = positionService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
