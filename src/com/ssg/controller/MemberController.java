package com.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.ssg.container.Container;
import com.ssg.dto.Article;
import com.ssg.dto.Member;
import com.ssg.service.MemberService;

public class MemberController extends Controller {

	private Scanner scanner;
	private MemberService memberService;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner scanner) {
		this.scanner = scanner;
		this.memberService = new MemberService();

	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {

		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("잘못된 명령어 입니다.");
			break;
		}
	}

	public void doJoin() {

		if (loginedMember != null) {
			System.out.printf("로그아웃 후 이용해 주세요.\n");
			return;
		}

		String loginID = null;
		while (true) {
			System.out.printf("회원가입 ID : ");
			loginID = scanner.nextLine();
			
			Member foundMember = memberService.getMemberByLoginId(loginID);
			if (foundMember != null) {
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
		memberService.add(member);

		System.out.printf("%s번째 회원이 생성되었습니다.\n", member.memberID);

	}

	public void doLogin() {

		if (loginedMember != null) {
			System.out.printf("로그아웃 후 이용해 주세요.\n");
			return;
		}

		System.out.printf("로그인 ID : ");
		String loginID = scanner.nextLine();

		System.out.printf("로그인 PW : ");
		String loginPW = scanner.nextLine();

		Member foundMember = memberService.getMemberByLoginId(loginID);

		if (foundMember == null) {
			System.out.printf("존재하지 않는 아이디입니다.\n");
			return;
		}

		if (!foundMember.loginPW.equals(loginPW)) {
			System.out.printf("비밀번호를 확인해주세요.\n");
			return;
		}

		loginedMember = foundMember;

		System.out.printf("%s님이 로그인하셨습니다.\n", foundMember.name);

	}

	public void doLogout() {
		if (loginedMember == null) {
			System.out.printf("로그인 후에 이용해주세요.\n");
			return;
		}

		loginedMember = null;
		System.out.printf("로그아웃 하였습니다.\n");
	}

}
