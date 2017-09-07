package com.drepair.po;

/**
 * 学生信息扩展类
 * @author SongM
 * @date 2017年6月23日 下午5:40:44
 */
public class StuCustom extends Stu {

	// TODO 这里是学生的其他属性（如寝室号等等）
	private String stuName; // 学生姓名
	private String stuSex; // 学生性别
	private String stuRoom; // 学生所住寝室
	private String stuIcard; // 学生身份证号
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuRoom() {
		return stuRoom;
	}
	public void setStuRoom(String stuRoom) {
		this.stuRoom = stuRoom;
	}
	public String getStuIcard() {
		return stuIcard;
	}
	public void setStuIcard(String stuIcard) {
		this.stuIcard = stuIcard;
	}
	
}
