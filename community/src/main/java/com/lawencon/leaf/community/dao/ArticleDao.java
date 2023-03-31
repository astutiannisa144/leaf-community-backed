package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Article;

@Repository
public class ArticleDao extends BaseDao<Article> {
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_article");
		final List<Article> articles = ConnHandler.getManager().createNativeQuery(str.toString(), Article.class)
				.getResultList();
		return articles;
	}

	@Override
	public Optional<Article> getById(String id) {
		return Optional.ofNullable(super.getById(Article.class, id));
	}
	
	
	@SuppressWarnings("unchecked")
	public Optional<Article> getByBk(String code) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_article ");
		str.append("WHERE article_code=:code");
		final List<Article> articles = ConnHandler.getManager().createNativeQuery(str.toString(), Article.class)
				.setParameter("code", code)
				.getResultList();
		return Optional.ofNullable(articles.get(0)) ;
	}
	@Override
	Optional<Article> getByIdAndDetach(String id) {
		return Optional.empty();
	}
	@SuppressWarnings("unchecked")
	public List<Article> getAll(Integer limit, Integer offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_article");
		final List<Article> articles = ConnHandler.getManager().createNativeQuery(str.toString(), Article.class)
				.setFirstResult((offset-1)*limit)
				.setMaxResults(limit)
				.getResultList();
		return articles;
	}
}
