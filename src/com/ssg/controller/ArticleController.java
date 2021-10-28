package com.ssg.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Article;

public class ArticleController extends Controller {

	private Scanner scanner;
	private List<Article> articles;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner scanner, List<Article> articles) {

		this.scanner = scanner;
		this.articles = articles;

	}

	public void makeTestData() {

		articles.add(new Article("제목1", "내용1", 1, " admin"));
		articles.add(new Article("제목1", "내용1", 1, " admin"));
		articles.add(new Article("제목1", "내용1", 1, " admin"));

		System.out.printf("Article테스트데이터를 생성했습니다.\n");

	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {

		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("잘못된 명령어 입니다.");
			break;
		}
	}

	public void doWrite() {

		if (loginedMember == null) {
			System.out.printf("로그인 후에 이용해주세요.\n");
			return;
		}

		System.out.printf("제목 : ");
		String title = scanner.nextLine();

		System.out.printf("내용 : ");
		String body = scanner.nextLine();

		Article article = new Article(title, body, loginedMember.memberID, loginedMember.name);
		articles.add(article);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", article.articleId);

	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> searchedArticles = new ArrayList<>();

		if (searchKeyword.length() > 0) {
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					searchedArticles.add(article);
				}
			}

			if (searchedArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}

		} else {
			searchedArticles = articles;
		}
		System.out.printf("  번호  |  제목  |  조회수   \n");

		for (int i = searchedArticles.size() - 1; i >= 0; i--) {
			Article article = searchedArticles.get(i);
			System.out.printf("  %d    |    %s|    %d\n", article.articleId, article.title, article.hit);

		}

	}

	public void showDetail() {

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}
		String checkStr = commandBits[2];
		int foundId = getFoundIdByCheckStr(checkStr);
		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}
		Article foundArticle = getFoundArticleById(foundId);
		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		foundArticle.increaseHit();
		System.out.printf("번호 : %d\n", foundArticle.articleId);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", foundArticle.memberName);
		System.out.printf("조회수 : %d\n", foundArticle.hit);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("===================\n");

	}

	public void doModify() {
		
		if(loginedMember == null) {
			System.out.printf("로그인 후에 이용해 주세요.\n");
			return;
		}
		
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = getFoundIdByCheckStr(checkStr);
		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		if (loginedMember.memberID != foundArticle.memberId) {
			System.out.printf("권한이 없습니다.\n");
			return;
		}

		System.out.printf("제목 : ");
		String title = scanner.nextLine();

		System.out.printf("내용 : ");
		String body = scanner.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.println(foundArticle.articleId + "번 게시물이 수정되었습니다.");

	}

	public void doDelete() {
		
		if(loginedMember == null) {
			System.out.printf("로그인 후에 이용해 주세요.\n");
			return;
		}
		
		command = command.trim();
		String[] commandBits = command.split(" ");
		String checkStr = commandBits[2];

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		int foundId = getFoundIdByCheckStr(checkStr);
		if (foundId == 0) {
			System.out.printf("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);
		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", foundId);
			return;
		}
		if (loginedMember.memberID != foundArticle.memberId) {
			System.out.printf("권한이 없습니다.\n");
			return;
		}

		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.articleId);

	}

	private int getFoundIdByCheckStr(String checkStr) {

		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
	}

	private Article getFoundArticleById(int foundId) {

		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;
	}
}
