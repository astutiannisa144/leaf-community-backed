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
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityReq;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityRes;
import com.lawencon.leaf.community.service.UserActivityService;

@RestController

@RequestMapping("user-activity")
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
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoUserActivityReq data) {
		final PojoRes res = userActivityService.update(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<List<PojoUserActivityRes>> getAll(@RequestParam(required = false) String id) throws Exception {
		final List<PojoUserActivityRes> result = userActivityService.getAll(id);
		return new ResponseEntity<List<PojoUserActivityRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoUserActivityRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoUserActivityRes result = userActivityService.getById(id);
		return new ResponseEntity<PojoUserActivityRes>(result, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = userActivityService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
