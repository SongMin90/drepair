package com.drepair.api.po;

/**
 * Api 通过工号查询学生详细信息
 * @author SongM
 * @date 2017年9月5日 下午9:03:22
 */
public class Stu {

	private String find; // 是否找到，success为成功，error为失败
	private Student student;
	
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
