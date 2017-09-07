package com.drepair.utils;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * properties文件更新工具类
 * 
 * @author SongM
 * @date 2017年9月2日 下午3:00:01
 */
public class PropertiesHelper {

	/**
	 * 更新操作
	 * 
	 * @param filePath
	 * @param key
	 * @param value
	 */
	public static void update(String filePath, String key, String value) {
		try {
			PropertiesConfiguration config  = new PropertiesConfiguration(filePath);
            System.out.println(config.getString("webName"));
            config.setAutoSave(true);
            config.setProperty(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
