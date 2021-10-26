package com.ssg;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	static public String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format.format(time);
	}
}
