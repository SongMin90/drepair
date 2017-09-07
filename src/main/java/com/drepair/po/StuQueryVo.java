package com.drepair.po;

import java.util.List;

/**
 * 学生包装对象
 * @author SongM
 * @date 2017年6月23日 下午5:41:46
 */
public class StuQueryVo {

	// 学生信息
	private Stu stu;
	
	// 学生信息扩展
	private StuCustom stuCustom;
	
	// 学生信息表
	private List<StuCustom> stuList;

	public Stu getStu() {
		return stu;
	}

	public void setStu(Stu stu) {
		this.stu = stu;
	}

	public StuCustom getStuCustom() {
		return stuCustom;
	}

	public void setStuCustom(StuCustom stuCustom) {
		this.stuCustom = stuCustom;
	}

	public List<StuCustom> getStuList() {
		return stuList;
	}

	public void setStuList(List<StuCustom> stuList) {
		this.stuList = stuList;
	}
	
}
