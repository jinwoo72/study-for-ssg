package com.ssg.service;

import java.util.List;

import com.ssg.container.Container;
import com.ssg.dto.Article;

public class ArticleService {

	public void add(Article article) {
		Container.articleDao.add(article);
	}

	public List<Article> getSearchedArticlesByKeyword(String searchKeyword) {

		return Container.articleDao.getSearchedArticlesByKeyword(searchKeyword);
	}
	
	public int getFoundIdByCheckStr(String checkStr) {
		return Container.articleDao.getFoundIdByCheckStr(checkStr);
	}

	public Article getFoundArticleById(int foundId) {

		return Container.articleDao.getFoundArticleById(foundId);
	}

	public void modify(Article foundArticle, String title, String body) {

		Container.articleDao.modify(foundArticle, title, body);

	}

	public void remove(Article foundArticle) {
		
		Container.articleDao.remove(foundArticle);

	}

}
