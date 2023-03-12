package com.lawencon.leaf.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.industry.PojoIndustryReq;
import com.lawencon.leaf.community.pojo.industry.PojoIndustryRes;
import com.lawencon.leaf.community.service.IndustryService;

@RestController

@RequestMapping("industries")
public class IndustryController {
	private IndustryService industryService;

	public IndustryController(IndustryService industryService) {
		this.industryService = industryService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoIndustryRes>> getAll() throws Exception {
		final List<PojoIndustryRes> result = industryService.getAll();
		return new ResponseEntity<List<PojoIndustryRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoIndustryRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoIndustryRes result = industryService.getById(id);
		return new ResponseEntity<PojoIndustryRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoIndustryReq data) {
		final PojoRes res = industryService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Validated @RequestBody PojoIndustryReq data) {
		final PojoRes res = industryService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = industryService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
