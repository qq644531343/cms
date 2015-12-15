package com.sina.cms.backend.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	
	private static DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date dateFromString(String time) {
		if (!(time instanceof String) || time.length() == 0) {
			return null;
		}
		try {
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public static String stringFromDate(Date date) {
		if (date == null) {
			return "";
		}
		return df.format(date);
	}
	
	public static Date dateFromMillis(long millis) {
		return new Date(millis);
	}
	
	public static String stringFromSimpeDate(Date date) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}
	
	public static Timestamp javaDateToTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());
	}
	
	public static Date timestampToJavaDate(Timestamp ts) {
		if (ts == null) {
			return null;
		}
		return new Date(ts.getTime());
	}
}
