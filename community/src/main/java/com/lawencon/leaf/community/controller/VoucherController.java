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
import com.lawencon.leaf.community.pojo.voucher.PojoVoucherReq;
import com.lawencon.leaf.community.pojo.voucher.PojoVoucherRes;
import com.lawencon.leaf.community.service.VoucherService;

@RestController

@RequestMapping("vouchers")
public class VoucherController {
	private VoucherService voucherService;

	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoVoucherRes>> getAll() throws Exception {
		final List<PojoVoucherRes> result = voucherService.getAll();
		return new ResponseEntity<List<PojoVoucherRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoVoucherRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoVoucherRes result = voucherService.getById(id);
		return new ResponseEntity<PojoVoucherRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoVoucherReq data) {
		final PojoRes res = voucherService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoVoucherReq data) {
		final PojoRes res = voucherService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = voucherService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
