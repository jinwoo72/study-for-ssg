package com.ssg.container;

import com.ssg.dao.ArticleDao;
import com.ssg.dao.MemberDao;

public class Container {
	//DB를 한군데에 모으는 과정.
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	//실행하자마자 1회 초기화 후 그 뒤로 누적.
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}

}
