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
import com.lawencon.leaf.community.pojo.category.PojoCategoryReq;
import com.lawencon.leaf.community.pojo.category.PojoCategoryRes;
import com.lawencon.leaf.community.service.CategoryService;

@RestController

@RequestMapping("categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public ResponseEntity<List<PojoCategoryRes>> getAll() throws Exception {
		final List<PojoCategoryRes> result = categoryService.getAll();
		return new ResponseEntity<List<PojoCategoryRes>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PojoCategoryRes> getById(final @Valid @PathVariable("id") String id)

			throws Exception {
		final PojoCategoryRes result = categoryService.getById(id);
		return new ResponseEntity<PojoCategoryRes>(result, HttpStatus.OK);
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<PojoCategoryRes> getAllByCategory(final @Valid @PathVariable("id") String id)
			throws Exception {
		final PojoCategoryRes result = categoryService.getById(id);
		return new ResponseEntity<PojoCategoryRes>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoCategoryReq data) {
		final PojoRes res = categoryService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PatchMapping()
	public ResponseEntity<PojoRes> update(final @Valid @RequestBody PojoCategoryReq data) {
		final PojoRes res = categoryService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = categoryService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
