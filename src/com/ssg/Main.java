package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==== 프로그램 시작 ====");

		Scanner scanner = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("article write")) {

				System.out.printf("제목 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);
				articles.add(article);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);

			} else if (command.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.printf("  번호  |  제목  \n");
				/*
				 * for each문
				 * for( Article article : articles){
				 * syso("~", article.id, article.title);
				 * }
				 * 
				 */
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("  %d    |    %s \n", article.id, article.title);

				}

			} else if (command.startsWith("article detail ")) {
				command = command.trim();
				String[] commandBits = command.split(" ");
				int articleId = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = null;
				
				//순회하는 반복문
				for(Article article : articles) {
					if(article.id == articleId) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n",articleId);
					continue;
				}
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("------------------\n");

			} else if (command.equals("article modify")) {
				System.out.println("article modify");
			} else if (command.equals("system exit")) {
				break;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("==== 프로그램 끝 ====");
		scanner.close();
	}

}

class Article {
	static int index = 0;
	int id;
	String title;
	String body;

	//생성
	Article(String title, String body) {
		this.index++;
		this.id = index;
		this.title = title;
		this.body = body;

	}

}