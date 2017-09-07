package com.drepair.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * 
 * @author SongM
 * @date 2017年8月24日 上午11:01:21
 */
public class CookieHelper {

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 */
	public static void add(int cookieSaveTime, HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(cookieSaveTime);// 设置为30天
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 根据cookieName查询所有cookie，返回name对应的值
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @return
	 */
	public static String isGet(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
			// System.out.println("没有cookie=========");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name) && cookie.getValue() != null) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 根据name修改值
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void update(int cookieSaveTime, HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
			// System.out.println("没有cookie=========");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(value);
					cookie.setMaxAge(cookieSaveTime);// 设置为30天
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
	}

	/**
	 * 清空所有cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void clearAll(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			// System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				cookie.setValue(null);
				cookie.setMaxAge(0);// 立即销毁cookie
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}

}
