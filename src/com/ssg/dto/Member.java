package com.ssg.dto;

import com.ssg.Util;

public class Member extends Dto {
	public static int index = 0;
	public int memberID;
	public String loginID;
	public String loginPW;
	public String name;
	public String regDate;
	
	public Member(String loginID, String loginPW, String name) {
		this.index++;
		this.memberID = index;
		this.loginID = loginID;
		this.loginPW = loginPW;
		this.name = name;
		this.regDate = Util.getNowDateStr();
	}
}
