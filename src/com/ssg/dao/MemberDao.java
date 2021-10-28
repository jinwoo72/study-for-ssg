package com.ssg.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dto.Member;

public class MemberDao {

	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
	}
	
	public Member getMemberByLoginId(String loginID) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.loginID.equals(loginID)) {
				foundMember = member;
			}
		}
		return foundMember;
	}

	public void makeTestData() {

		members.add(new Member("111", "111", "홍길동"));
		members.add(new Member("222", "222", "홍길순"));
		members.add(new Member("333", "333", "홍길준"));

		System.out.printf("Member테스트데이터를 생성했습니다.\n");

	}

}
