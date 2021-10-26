package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Article;
import com.ssg.dto.Member;

public class App {

	List<Article> articles = new ArrayList<>();
	List<Member> members = new ArrayList<>();

	void start() {
		System.out.printf("==== 프로그램 시작 ====\n");

		Scanner scanner = new Scanner(System.in);

		makeTestData();

		while (true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			if (command.equals("member join")) {

				String loginID = null;
				while (true) {
					boolean isJoinable = false;

					System.out.printf("회원가입 ID : ");
					loginID = scanner.nextLine();

					for (Member member : members) {
						if (member.loginID.equals(loginID)) {
							isJoinable = confirmLoginId(loginID);
							break;
						}
					}
					if (isJoinable) {
						System.out.printf("이미 존재하는 ID입니다.\n");
						continue;
					}
					break;
				}
				String loginPW = null;
				String loginPwConfirm = null;

				while (true) {
					System.out.printf("회원가입 PW : ");
					loginPW = scanner.nextLine();

					System.out.printf("회원가입 PW 확인 : ");
					loginPwConfirm = scanner.nextLine();

					if (!loginPW.equals(loginPwConfirm)) {
						System.out.printf("비밀번호를 확인해주세요.\n");
						continue;
					}
					break;
				}

				System.out.printf("회원가입 이름 : ");
				String name = scanner.nextLine();

				Member member = new Member(loginID, loginPW, name);
				members.add(member);

				System.out.printf("%s번 회원이 생성되었습니다.\n", member.memberID);

			} else if (command.equals("article write")) {

				System.out.printf("제목 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);
				articles.add(article);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);

			} else if (command.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				String searchKeyword = command.substring("article list".length()).trim();

				List<Article> searchedArticles = new ArrayList<>();

				if (searchKeyword.length() > 0) {
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							searchedArticles.add(article);
						}
					}

					if (articles.size() == 0) {
						System.out.println("검색결과가 존재하지 않습니다.");
						continue;
					}

				} else {
					searchedArticles = articles;
				}
				System.out.printf("  번호  |  제목  |  조회수   \n");

				for (int i = searchedArticles.size() - 1; i >= 0; i--) {
					Article article = searchedArticles.get(i);
					System.out.printf("  %d    |    %s|    %d\n", article.id, article.title, article.hit);

				}

			} else if (command.startsWith("article detail ")) {
				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				int foundId = getFoundIdByCheckStr(checkStr);
				if (foundId == 0) {
					System.out.printf("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);
				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", foundId);
					continue;
				}

				foundArticle.increaseHit();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("조회수 : %d\n", foundArticle.hit);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

				System.out.printf("------------------\n");

			} else if (command.startsWith("article modify")) {
				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				if (commandBits.length > 3) {
					System.out.printf("명령어를 잘못입력하셨습니다.\n");
					continue;
				}

				int foundId = getFoundIdByCheckStr(checkStr);
				if (foundId == 0) {
					System.out.printf("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);
				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", foundId);
					continue;
				}

				System.out.printf("제목 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", foundArticle.id);

			} else if (command.startsWith("article delete")) {
				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못입력하셨습니다.");
					continue;
				}

				int foundId = getFoundIdByCheckStr(checkStr);
				if (foundId == 0) {
					System.out.printf("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);
				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", foundId);
					continue;
				}

				articles.remove(foundArticle);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.id);

			} else if (command.equals("system exit")) {
				break;
			} else {
				System.out.printf("존재하지 않는 명령어입니다.\n");
			}
		}

		System.out.println("==== 프로그램 끝 ====");
		scanner.close();
	}

	void makeTestData() {

		articles.add(new Article("제목1", "내용1"));
		articles.add(new Article("제목2", "내용2"));
		articles.add(new Article("제목3", "내용3"));

		System.out.printf("테스트데이터를 생성했습니다.\n");

	}
	boolean confirmLoginId(String loginID) {
		boolean isJoinable = false;
		
		for(Member member : members) {
			if(member.loginID.equals(loginID)) {
				isJoinable = true;
				break;
			}
		}
		
		return isJoinable;
	}

	int getFoundIdByCheckStr(String checkStr) {
		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}

		return foundId;
	}

	Article getFoundArticleById(int foundId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.id == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;

	}

}
