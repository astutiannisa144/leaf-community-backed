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
import com.lawencon.leaf.community.pojo.premium.PojoPremiumReq;
import com.lawencon.leaf.community.pojo.premium.PojoPremiumRes;
import com.lawencon.leaf.community.service.PremiumService;

@RestController

@RequestMapping("premiums")
public class PremiumController {
	private PremiumService premiumService;

	public PremiumController(PremiumService premiumService) {
		this.premiumService = premiumService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoPremiumRes>> getAll() throws Exception {
		final List<PojoPremiumRes> result = premiumService.getAll();
		return new ResponseEntity<List<PojoPremiumRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoPremiumRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoPremiumRes result = premiumService.getById(id);
		return new ResponseEntity<PojoPremiumRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoPremiumReq data) {
		final PojoRes res = premiumService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoPremiumReq data) {
		final PojoRes res = premiumService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = premiumService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
