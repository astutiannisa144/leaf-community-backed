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
import com.lawencon.leaf.community.pojo.post.PojoPostReqInsert;
import com.lawencon.leaf.community.pojo.post.PojoPostReqUpdate;
import com.lawencon.leaf.community.pojo.post.PojoPostResGetAll;
import com.lawencon.leaf.community.service.PostService;

@RestController
@RequestMapping("posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @RequestBody PojoPostReqInsert data) {
		final PojoRes res = postService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoPostReqUpdate data) {
		final PojoRes res = postService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<PojoPostResGetAll>> getAll(@RequestParam(required = false) String code,
			final @RequestParam int limit, @RequestParam int page) {
		final List<PojoPostResGetAll> res = postService.getAll(limit, page, code);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deletePost(final @Valid @PathVariable String id) {
		final PojoRes res = postService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
