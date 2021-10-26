// git add . => 모든 파일을 깃으로 추가
// git commit -m "{msg}" => 커밋할 내용을 메시지로 추가
// git push origin main => 깃 서버로 파일 업로드

// 리팩토링 => 코드를 잘게 부숴서 모듈화시키는 것

package com.ssg;

public class Main {
	public static void main(String[] args) {
		new  App().start();
	}
}