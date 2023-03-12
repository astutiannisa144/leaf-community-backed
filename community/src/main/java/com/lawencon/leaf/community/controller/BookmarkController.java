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
import com.lawencon.leaf.community.pojo.bookmark.PojoBookmarkReqInsert;
import com.lawencon.leaf.community.service.BookmarkService;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	public BookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}

	@PostMapping()
	public ResponseEntity<PojoRes> insertBookmark(final @RequestBody PojoBookmarkReqInsert data) {
		final PojoRes res = bookmarkService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteBookmark(final @Valid @PathVariable String id) {
		final PojoRes res = bookmarkService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
