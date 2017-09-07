package com.drepair.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前时间
 * @author SongM
 * @date 2017年7月13日 下午3:49:25
 */
public class DateTime {

	/**
	 * 示例：2017_7_13
	 * @return
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyy_MM_dd").format(new Date());
	}
	
	/**
	 * 示例：15_52_30
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("HH_mm_ss").format(new Date());
	}
	
	/**
	 * 示例：2017-8-6 14:01:20
	 * @return
	 */
	public static String format() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
}
