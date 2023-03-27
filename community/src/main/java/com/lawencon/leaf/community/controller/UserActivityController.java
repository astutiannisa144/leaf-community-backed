package com.lawencon.leaf.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.lawencon.leaf.community.pojo.report.PojoActivityIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberParticipantRes;
import com.lawencon.leaf.community.pojo.report.PojoActivityParticipantRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityDataRes;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityReq;
import com.lawencon.leaf.community.pojo.user.activity.PojoUserActivityRes;
import com.lawencon.leaf.community.service.UserActivityService;
import com.lawencon.util.JasperUtil;

@RestController

@RequestMapping("user-activity")
public class UserActivityController {
	private UserActivityService userActivityService;

	@Autowired
	private JasperUtil jasperUtil;

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
	public ResponseEntity<PojoUserActivityRes> getAll(@RequestParam int limit, @RequestParam int page,@RequestParam(required = false) String typeCode,
			@RequestParam(required = false) String code) throws Exception {
		final PojoUserActivityRes result = userActivityService.getAll(limit, page,typeCode, code );
		return new ResponseEntity<PojoUserActivityRes>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoUserActivityDataRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoUserActivityDataRes result = userActivityService.getById(id);
		return new ResponseEntity<PojoUserActivityDataRes>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = userActivityService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/report/activity-participants")
	public ResponseEntity<?> getActivityParticipants(@RequestParam String dateStart, @RequestParam String dateEnd)
			throws Exception {
		final List<PojoActivityParticipantRes> result = userActivityService.getActivityParticipants(dateStart, dateEnd);
		final String fileName = "activity-participants";
		final byte[] fileBytes = jasperUtil.responseToByteArray(result, null, "activity-participants");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
				.body(fileBytes);
	}

	@GetMapping("/report/member-participants")
	public ResponseEntity<?> getMemberParticipants(@RequestParam String dateStart, @RequestParam String dateEnd)
			throws Exception {
		final List<PojoMemberParticipantRes> result = userActivityService.getMemberParticipants(dateStart, dateEnd);
		final String fileName = "member-participants";
		final byte[] fileBytes = jasperUtil.responseToByteArray(result, null, "member-participants");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
				.body(fileBytes);
	}

	@GetMapping("/report/member-incomes")
	public ResponseEntity<?> getMemberIncome(@RequestParam String dateStart, @RequestParam String dateEnd) throws Exception {
		final List<PojoMemberIncomeRes> result = userActivityService.getMemberIncome(dateStart, dateEnd);
		final String fileName = "member-incomes";
		final byte[] fileBytes = jasperUtil.responseToByteArray(result, null, "member-incomes");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
				.body(fileBytes);
	}

	@GetMapping("/report/activity-incomes")
	public ResponseEntity<?> getEventIncome(@RequestParam String dateStart, @RequestParam String dateEnd) throws Exception {
		final List<PojoActivityIncomeRes> result = userActivityService.getEventIncome(dateStart, dateEnd);
		final String fileName = "activity-incomes";
		final byte[] fileBytes = jasperUtil.responseToByteArray(result, null, "activity-incomes");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
				.body(fileBytes);
	}

}
