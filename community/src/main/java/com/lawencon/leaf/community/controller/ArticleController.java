package com.lawencon.leaf.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.article.PojoArticleReq;
import com.lawencon.leaf.community.pojo.article.PojoArticleRes;
import com.lawencon.leaf.community.service.ArticleService;

@RestController
@RequestMapping("articles")
public class ArticleController {
	private ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@PostMapping()
	public ResponseEntity<PojoRes> insert(final @Valid @RequestBody PojoArticleReq data) {
		final PojoRes res = articleService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<PojoArticleRes>> getAll() throws Exception {
		final List<PojoArticleRes> result = articleService.getAll();
		return new ResponseEntity<List<PojoArticleRes>>(result, HttpStatus.OK);
	}
}
