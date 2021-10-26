package com.ssg.dto;

import com.ssg.Util;

public class Article extends Dto {
	// index란 static 변수가 0으로 1회(만!) 초기화됨. 그 뒤로 값이 누적
	public static int index = 0;
	public int id;
	public String title;
	public String body;
	public String regDate;
	public int hit;

	// Article class가 생길때마다 아래 생성자 실행
	// index 값이 1 오름(누적 데이터)

	public Article(String title, String body) {
		this.index++;
		this.id = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
	}

	public void increaseHit() {
		this.hit++;
	}
}