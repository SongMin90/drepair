package com.drepair.api.json;

import com.alibaba.fastjson.JSON;
import com.drepair.api.po.Hmr;
import com.drepair.api.po.Stu;

/**
 * json解析
 * @author SongM
 * @date 2017年9月5日 下午9:09:56
 */
public class Analysis {

	/**
	 * json解析（宿管）
	 * @param jsonData
	 * @return
	 */
	public static Hmr forHmr(String jsonData) {
		return JSON.parseObject(jsonData, Hmr.class);
	}
	
	/**
	 * json解析（学生）
	 * @param jsonData
	 * @return
	 */
	public static Stu forStu(String jsonData) {
		return JSON.parseObject(jsonData, Stu.class);
	}
}
