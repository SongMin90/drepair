package com.drepair.api.net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.drepair.api.json.Analysis;
import com.drepair.utils.StreamUtils;

/**
 * 网络请求获取数据
 * @author SongM
 * @date 2017年9月4日 下午5:53:37
 */
public class HttpUtils {

	public static final String STU_URL = "http://47.94.252.54:8080/dormitory/External/studentInfo.action?studentId=";
	public static final String HMR_URL = "http://47.94.252.54:8080/dormitory/External//dormitoryManagerInfo.action?managerId=";
	public static final String EVAL_ICON_URL = "http://118.89.101.23:8080/img/findIconUrl";
	
	/**
	 * 网络请求获取数据
	 * @param urlByStuOrHmr 学生或宿管的URL
	 * @param id 学号或工号
	 * @return
	 */
	public static String getJSON(String urlByStuOrHmr, String id) {
		try {
			URL u = new URL(urlByStuOrHmr + id);
			HttpURLConnection openConnection = (HttpURLConnection) u.openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.setReadTimeout(1000 * 10);
			openConnection.disconnect();
			int code = openConnection.getResponseCode();
			if(code == 200) {
				InputStream inputStream = openConnection.getInputStream();
				return StreamUtils.streamToString(inputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 网络请求获取评论人头像url
	 * @param urlByStuOrHmr
	 * @param id
	 * @return
	 */
	public static String getEvalIconUrl(String userId, String profession) {
		try {
			URL u = new URL(EVAL_ICON_URL + "?userId=" + userId + "&profession=" + profession);
			HttpURLConnection openConnection = (HttpURLConnection) u.openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.setReadTimeout(1000 * 10);
			openConnection.disconnect();
			int code = openConnection.getResponseCode();
			if(code == 200) {
				InputStream inputStream = openConnection.getInputStream();
				String jsonData = StreamUtils.streamToString(inputStream);
				return Analysis.forEvalIconUrl(jsonData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_ICON_URL;
	}
	
}
