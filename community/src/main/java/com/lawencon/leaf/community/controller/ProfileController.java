package com.lawencon.leaf.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.profile.PojoProfileReq;
import com.lawencon.leaf.community.pojo.profile.PojoProfileRes;
import com.lawencon.leaf.community.service.ProfileService;

@RestController
@RequestMapping("profiles")
public class ProfileController {

	private final ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}


	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoProfileReq data) {
		final PojoRes res = profileService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<PojoProfileRes> getById() {
		PojoProfileRes res = profileService.getById();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
