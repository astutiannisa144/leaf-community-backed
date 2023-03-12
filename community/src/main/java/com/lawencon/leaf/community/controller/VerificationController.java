package com.lawencon.leaf.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.verification.PojoVerificationReq;
import com.lawencon.leaf.community.pojo.verification.PojoVerificationRes;
import com.lawencon.leaf.community.service.VerificationService;

@RestController

@RequestMapping("verifications")
public class VerificationController {
	private VerificationService verificationService;

	public VerificationController(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoVerificationRes>> getAll() throws Exception {
		final List<PojoVerificationRes> result = verificationService.getAll();
		return new ResponseEntity<List<PojoVerificationRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoVerificationRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoVerificationRes result = verificationService.getById(id);
		return new ResponseEntity<PojoVerificationRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoVerificationReq data) {
		final PojoRes res = verificationService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	
}
