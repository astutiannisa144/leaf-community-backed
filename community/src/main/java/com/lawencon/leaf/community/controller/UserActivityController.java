package com.lawencon.leaf.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityReq;
import com.lawencon.leaf.community.service.UserActivityService;

@RestController

@RequestMapping("userActivitys")
public class UserActivityController {
	private UserActivityService userActivityService;

	public UserActivityController(UserActivityService userActivityService) {
		this.userActivityService = userActivityService;
	}



	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoUserActivityReq data) {
		final PojoRes res = userActivityService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	
}
