package com.lawencon.leaf.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.like.PojoLikeReqInsert;
import com.lawencon.leaf.community.service.LikeService;

@RestController
@RequestMapping("likes")
public class LikeController {

	private final LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insertLike(final @Valid @RequestBody PojoLikeReqInsert data) {
		final PojoRes res = likeService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteLike(final @Valid @PathVariable String id) {
		final PojoRes res = likeService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
