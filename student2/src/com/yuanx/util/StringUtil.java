package com.yuanx.util;

//字符串工具类
public class StringUtil {
	// 判断一个字符串是否为空串或空
	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		}
		return false;
	}
}
