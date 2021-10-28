package com.ssg.controller;

import com.ssg.dto.Member;

public class Controller {
	static Member loginedMember = null;
	private String command;
	private String actionMethodName;
	
	public void doAction(String command, String actionMethodName) {
	this.command = command;
	this.actionMethodName = actionMethodName;
	}
}
