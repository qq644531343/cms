package com.sina.cms.backend.tools;

import java.util.Random;

public class NumberTool {
	
	/**
	 * 产生min~max之间的随机整数
	 */
	public static int random(int min, int max) {
		int num = new Random().nextInt(99999999) % (max - min);
		return num + min;
	}
}
