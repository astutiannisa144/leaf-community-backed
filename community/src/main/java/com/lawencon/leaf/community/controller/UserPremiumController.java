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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumReq;
import com.lawencon.leaf.community.pojo.user.premium.PojoUserPremiumRes;
import com.lawencon.leaf.community.service.UserPremiumService;

@RestController

@RequestMapping("user-premium")
public class UserPremiumController {
	private UserPremiumService userPremiumService;

	public UserPremiumController(UserPremiumService userPremiumService) {
		this.userPremiumService = userPremiumService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoUserPremiumRes>> getAll(@RequestParam (required = false) String code,@RequestParam int limit,@RequestParam int page) throws Exception {
		final List<PojoUserPremiumRes> result = userPremiumService.getAll(limit,page,code);
		return new ResponseEntity<List<PojoUserPremiumRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoUserPremiumRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoUserPremiumRes result = userPremiumService.getById(id);
		return new ResponseEntity<PojoUserPremiumRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoUserPremiumReq data) {
		final PojoRes res = userPremiumService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoUserPremiumReq data) {
		final PojoRes res = userPremiumService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = userPremiumService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
