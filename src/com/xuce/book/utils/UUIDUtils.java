package com.xuce.book.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * ʹ��uuid�������ַ��� ��֤��
	 */
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
