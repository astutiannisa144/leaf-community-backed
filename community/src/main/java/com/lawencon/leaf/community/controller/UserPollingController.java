package com.lawencon.leaf.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.polling.PojoUserPollingReqInsert;
import com.lawencon.leaf.community.service.UserPollingService;

@RestController
@RequestMapping("user-pollings")
public class UserPollingController {

	private final UserPollingService userPollingService;

	public UserPollingController(UserPollingService userPollingService) {
		this.userPollingService = userPollingService;
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insertLike(final @Valid @RequestBody PojoUserPollingReqInsert data) {
		final PojoRes res = userPollingService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

}
