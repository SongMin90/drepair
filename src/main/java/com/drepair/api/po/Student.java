package com.drepair.api.po;

public class Student {

	private String studentName;
	private String studentIdcard;
	private String studentSex;
	private DormInfo dormInfo;
	private dormroomInfo dormroomInfo;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentIdcard() {
		return studentIdcard;
	}
	public void setStudentIdcard(String studentIdcard) {
		this.studentIdcard = studentIdcard;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public DormInfo getDormInfo() {
		return dormInfo;
	}
	public void setDormInfo(DormInfo dormInfo) {
		this.dormInfo = dormInfo;
	}
	public dormroomInfo getDormroomInfo() {
		return dormroomInfo;
	}
	public void setDormroomInfo(dormroomInfo dormroomInfo) {
		this.dormroomInfo = dormroomInfo;
	}
	
}
