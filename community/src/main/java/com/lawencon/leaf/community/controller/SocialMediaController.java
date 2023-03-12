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
import com.lawencon.leaf.community.pojo.social.media.PojoSocialMediaReq;
import com.lawencon.leaf.community.pojo.social.media.PojoSocialMediaRes;
import com.lawencon.leaf.community.service.SocialMediaService;

@RestController

@RequestMapping("social-media")
public class SocialMediaController {
	private SocialMediaService socialMediaService;

	public SocialMediaController(SocialMediaService socialMediaService) {
		this.socialMediaService = socialMediaService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoSocialMediaRes>> getAll() throws Exception {
		final List<PojoSocialMediaRes> result = socialMediaService.getAll();
		return new ResponseEntity<List<PojoSocialMediaRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoSocialMediaRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoSocialMediaRes result = socialMediaService.getById(id);
		return new ResponseEntity<PojoSocialMediaRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoSocialMediaReq data) {
		final PojoRes res = socialMediaService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoSocialMediaReq data) {
		final PojoRes res = socialMediaService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = socialMediaService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
