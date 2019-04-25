package com.xuce.book.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 使用uuid获得随机字符串 验证码
	 */
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
