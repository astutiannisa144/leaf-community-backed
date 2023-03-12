package com.lawencon.leaf.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.comment.PojoCommentReqInsert;
import com.lawencon.leaf.community.pojo.comment.PojoCommentReqUpdate;
import com.lawencon.leaf.community.service.CommentService;

@RestController
@RequestMapping("comments")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insertComment(@Valid @RequestBody PojoCommentReqInsert data) {
		final PojoRes res = commentService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteComment(final @Valid @PathVariable String id) {
		final PojoRes res = commentService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoCommentReqUpdate data) {
		final PojoRes res = commentService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
