package com.yuanx.util;

//配置文件工具类
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

}
