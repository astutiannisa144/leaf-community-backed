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
	Optional<Article> getById(String id) {
		return Optional.ofNullable(super.getById(Article.class, id));
	}

	@Override
	Optional<Article> getByIdAndDetach(String id) {
		return Optional.empty();
	}
}
