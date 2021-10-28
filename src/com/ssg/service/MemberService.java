package com.ssg.service;

import com.ssg.container.Container;
import com.ssg.dto.Member;

public class MemberService {

	public void add(Member member) {
		Container.memberDao.add(member);
	}
	
	public Member getMemberByLoginId(String loginID) {
		return Container.memberDao.getMemberByLoginId(loginID);
	}

}
