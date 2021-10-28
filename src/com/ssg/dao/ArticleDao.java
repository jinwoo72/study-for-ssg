package com.ssg.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dto.Article;

public class ArticleDao {

	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
	}

	public List<Article> getSearchedArticlesByKeyword(String searchKeyword) {
		List<Article> searchedArticles = null;

		if (searchKeyword.length() > 0) {
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					searchedArticles.add(article);
				}
			}
		} else {
			searchedArticles = articles;
		}

		return searchedArticles;

	}

	public Article getFoundArticleById(int foundId) {

		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;
	}
	
	public int getFoundIdByCheckStr(String checkStr) {

		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
	}

	public void modify(Article foundArticle, String title, String body) {

		foundArticle.title = title;
		foundArticle.body = body;

	}
	
	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
		
	}

	public void makeTestData() {

		articles.add(new Article("제목1", "내용1", 1, " admin"));
		articles.add(new Article("제목1", "내용1", 1, " admin"));
		articles.add(new Article("제목1", "내용1", 1, " admin"));

		System.out.printf("Article테스트데이터를 생성했습니다.\n");

	}

	

}
