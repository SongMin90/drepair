package com.drepair.po;

/**
 * 图片属性扩展类
 * @author SongM
 * @date 2017年7月17日 下午11:12:13
 */
public class ImgCustom extends Img {

	private String userId; // 用户ID
	private String profession; // 归属人类型
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}
