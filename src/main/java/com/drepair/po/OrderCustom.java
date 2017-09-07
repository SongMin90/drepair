package com.drepair.po;

/**
 * Order扩展类
 * @author SongM
 * @date 2017年7月6日 上午11:43:46
 */
public class OrderCustom extends Order {

	private String listImgUrl; // List页面订单首图地址

	public String getListImgUrl() {
		return listImgUrl;
	}

	public void setListImgUrl(String listImgUrl) {
		this.listImgUrl = listImgUrl;
	}
	
}
