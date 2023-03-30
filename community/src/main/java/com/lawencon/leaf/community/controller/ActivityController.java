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
import com.lawencon.leaf.community.pojo.activity.PojoActivityReq;
import com.lawencon.leaf.community.pojo.activity.PojoActivityReqGetAll;
import com.lawencon.leaf.community.pojo.activity.PojoActivityRes;
import com.lawencon.leaf.community.pojo.activity.type.PojoActivityTypeRes;
import com.lawencon.leaf.community.service.ActivityService;

@RestController
@RequestMapping("activities")
public class ActivityController {
	private ActivityService activityService;

	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoActivityRes>> getAll(@RequestParam(required = false) String type,
			@RequestParam(required = false) String category, @RequestParam(required = false) String code, @RequestParam int limit, @RequestParam int page) throws Exception {
		List<PojoActivityRes> result = activityService.getAll(type,category,code,limit,page);

		return new ResponseEntity<List<PojoActivityRes>>(result, HttpStatus.OK);
	}
	@PostMapping("/filter")
	public ResponseEntity<List<PojoActivityRes>> getAllByListCategory(final @Valid @RequestBody PojoActivityReqGetAll data) throws Exception {
		List<PojoActivityRes> result = activityService.getAllByListCategory(data);

		return new ResponseEntity<List<PojoActivityRes>>(result, HttpStatus.OK);
	}
	@GetMapping("/type")
	public ResponseEntity<List<PojoActivityTypeRes>> getAllType() throws Exception {
		List<PojoActivityTypeRes> result = activityService.getAllType();

		return new ResponseEntity<List<PojoActivityTypeRes>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PojoActivityRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoActivityRes result = activityService.getById(id);
		return new ResponseEntity<PojoActivityRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoActivityReq data) {
		final PojoRes res = activityService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoActivityReq data) {
		final PojoRes res = activityService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = activityService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("schedule/{id}")
	public ResponseEntity<PojoRes> deleteSchedule(final @Valid @PathVariable String id) {
		final PojoRes res = activityService.deleteSchedule(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
