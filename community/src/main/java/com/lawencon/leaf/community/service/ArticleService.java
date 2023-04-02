package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ArticleDao;
import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.model.Article;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.article.PojoArticleReqInsert;
import com.lawencon.leaf.community.pojo.article.PojoArticleReqUpdate;
import com.lawencon.leaf.community.pojo.article.PojoArticleRes;
import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;
import com.lawencon.security.principal.PrincipalService;

@Service
public class ArticleService extends AbstractJpaDao{
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalService principalService;

	private void valIdNull(PojoArticleReqInsert article) {
		if (article.getId() != null) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}

	private void valBkNull(PojoArticleReqInsert article) {
		if (article.getArticleCode() != null) {
			throw new RuntimeException("Article Code Should Be Empty");

		}
	}
//	private void valBkNotExist(Article article) {
//		if (articleDao.getByBk(article.getArticleCode()).isPresent()) {
//			throw new RuntimeException("Article Code Already Exist");
//		}
//	}

	private void valNonBk(PojoArticleReqInsert article) {
		if (article.getContent() == null) {
			throw new RuntimeException("Content Cannot Be Empty");
		}
		if (article.getTitle() == null) {
			throw new RuntimeException("Title Cannot Be Empty");
		}
		if (article.getFile() == null) {
			throw new RuntimeException("Image Cannot Be Empty");
		}


	}

	private void valIdNotNull(PojoArticleReqUpdate article) {
		if (article.getId() == null) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}

	private void valIdExist(String id) {
		if (articleDao.getById(id).isEmpty()) {
			throw new RuntimeException("Id Cannot Be Empty");
		}
	}
	
	public PojoRes insert(PojoArticleReqInsert data) {
		ConnHandler.begin();
		valIdNull(data);
		valNonBk(data);
		valBkNull(data);

		final Article article = new Article();
		
		article.setArticleCode(GenerateCodeUtil.generateCode(10));

		article.setTitle(data.getTitle());
		
		final User admin = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		article.setAdmin(admin);
		
		article.setContent(data.getContent());
		
		File file = new File();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setIsActive(true); 	
		File fileInsert = save(file);
		article.setFile(fileInsert);
		
		article.setIsActive(true);
		
		articleDao.save(article);
		
		ConnHandler.commit();
		
		final PojoRes res = new PojoRes();
		res.setMessage("Success insert Article");
		return res;

	}
	
	public PojoRes update(PojoArticleReqUpdate data) {
		ConnHandler.begin();
		valIdNotNull(data);
		valIdExist(data.getId());
		final Article articleUpdate = articleDao.getById(data.getId()).get();
		articleUpdate.setTitle(data.getTitle());
		articleUpdate.setContent(data.getContent());
		File file=fileDao.getById(data.getFile().getId()).get();
		file.setFileContent(data.getFile().getFileContent());
		file.setFileExtension(data.getFile().getFileExtension());
		file.setVer(data.getFile().getVer());
		fileDao.save(file);
		articleUpdate.setFile(file);
		articleUpdate.setVer(data.getVer());
		articleDao.save(articleUpdate);

		ConnHandler.commit();

		final PojoRes res = new PojoRes();
		res.setMessage("Article edited");
		return res;
	}
	
	public List<PojoArticleRes> getAll(int limit,int page) {
		final List<PojoArticleRes> pojoArticleRes = new ArrayList<>();
		
		List<Article> articles = articleDao.getAll(limit,page);
		for (int i = 0; i < articles.size(); i++) {
			PojoArticleRes articleRes = new PojoArticleRes();
			articleRes.setArticleId(articles.get(i).getId());
			articleRes.setAdminId(articles.get(i).getAdmin().getId());
			articleRes.setContent(articles.get(i).getContent());
			articleRes.setCreatedAt(articles.get(i).getCreatedAt());
			articleRes.setFileId(articles.get(i).getFile().getId());
			articleRes.setTitle(articles.get(i).getTitle());
			articleRes.setVer(articles.get(i).getVer());
			articleRes.setIsActive(articles.get(i).getIsActive());
			
			pojoArticleRes.add(articleRes);
		}
		
		return pojoArticleRes;
	}
	public PojoArticleRes getById(String id) {
		
			Article articles = articleDao.getById(id).get();

			PojoArticleRes articleRes = new PojoArticleRes();
			articleRes.setArticleId(articles.getId());
			articleRes.setAdminId(articles.getAdmin().getId());
			articleRes.setContent(articles.getContent());
			articleRes.setFullName(articles.getAdmin().getProfile().getFullName());
			articleRes.setCreatedAt(articles.getCreatedAt());
			PojoFileRes file = new PojoFileRes();
			file.setFileId(articles.getFile().getId());
			file.setFileContent(articles.getFile().getFileContent());
			file.setFileExtension(articles.getFile().getFileExtension());
			file.setVer(articles.getFile().getVer());
			
			articleRes.setFile(file);
			articleRes.setFileId(articles.getFile().getId());
			articleRes.setTitle(articles.getTitle());
			articleRes.setVer(articles.getVer());
			articleRes.setIsActive(articles.getIsActive());
			
		
		
		return articleRes;
	}
	public PojoRes delete(String id) {

		try {
			ConnHandler.begin();
			valIdExist(id);
			
			articleDao.deleteById(Article.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Article Deleted");
		return pojoRes;
	}

}
