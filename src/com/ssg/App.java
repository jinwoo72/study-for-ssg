package com.ssg;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.ssg.container.Container;
import com.ssg.controller.ArticleController;
import com.ssg.controller.Controller;
import com.ssg.controller.MemberController;

import com.ssg.dto.Article;
import com.ssg.dto.Member;

public class App {
	
	void start() {
		System.out.printf("==== 프로그램 시작 ====\n");

		Scanner scanner = new Scanner(System.in);

		MemberController memberController = new MemberController(scanner);
		ArticleController articleController = new ArticleController(scanner);
		
		Container.articleDao.makeTestData();
	    Container.memberDao.makeTestData();
	    
	    

		while (true) {

			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			String[] commandBits = command.split(" ");

			if (commandBits.length < 2) {
				System.out.printf("존재하지 않는 명령어입니다.\n");
				continue;
			}

			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;

			if (command.length() == 0) {
				System.out.printf("검색어를 다시 입력해주세요.\n");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.printf("존재하지 않는 명령어입니다.\n");
				continue;
			}

			controller.doAction(command, actionMethodName);

		}

		System.out.println("==== 프로그램 끝 ====");
		scanner.close();
	}

}
