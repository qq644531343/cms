package com.sina.cms.utils;

public class StringUtils {

	public static boolean isEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String string) {
		return ! StringUtils.isEmpty(string);
	}
	
}
