package com.lawencon.leaf.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.dao.ArticleDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.model.Article;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.article.PojoArticleReq;
import com.lawencon.leaf.community.pojo.article.PojoArticleRes;
import com.lawencon.leaf.community.util.GenerateCodeUtil;
import com.lawencon.security.principal.PrincipalService;

@Service
public class ArticleService extends AbstractJpaDao{
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrincipalService principalService;

	public PojoRes insert(PojoArticleReq data) {
		ConnHandler.begin();

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

	
	public List<PojoArticleRes> getAll() {
		final List<PojoArticleRes> pojoArticleRes = new ArrayList<>();
		List<Article> articles = articleDao.getAll();
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

}
