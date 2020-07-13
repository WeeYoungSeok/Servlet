package com.cal.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utils {

	private String toDates;
	
	public String getToDates() {
		return toDates;
	}
	
	public void setToDates(String mdate) {
		// yyyy-MM-dd hh:mm:00
		
		String m = mdate.substring(0,4) + "-"
				 + mdate.substring(4,6) + "-"
				 + mdate.substring(6,8) + " "
				 + mdate.substring(8,10) + ":"
				 + mdate.substring(10) + ":"	// 10부터 마지막까지 짜름
				 + "00";
		// 2020-07-24 11:11:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(m);
		System.out.println(tm.getClass().getName());
		toDates = sdf.format(tm);
		System.out.println(toDates.getClass().getName());
		
		// 타임스탬프는 나노세컨드
		// 데이트는 밀리세컨드
	}
	
	// 한자리수 숫자에 0붙여주는 메서드
	public static String isTwo(String msg) {
		return (msg.length()<2)? "0"+msg : msg;
		// msg가 한자리라면 0붙인다.
		
		// switch나 if문 가능
		// 대신 인트형으로 바꾸어주어야함
	}
	
	// 달력의 토요일, 일요일, 평일 생상 설정
	
	public static String fontColor(int date, int dayOfWeek) {
		String color = "";
		
		if ((dayOfWeek-1 + date) % 7 == 0) {
			color = "blue";
		} else if ((dayOfWeek-1 +date) % 7 == 1) {
			color = "red";
		} else {
			color = "black";
		}
		
		return color;
	}
}
