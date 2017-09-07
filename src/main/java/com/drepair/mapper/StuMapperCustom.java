package com.drepair.mapper;

import java.util.List;

import com.drepair.po.StuCustom;

public interface StuMapperCustom {

	/**
	 * 学生通过手机号码查询
	 * @param stuPhone
	 * @return
	 * @throws Exception
	 */
	public StuCustom findByPhone(String stuPhone) throws Exception;
	
	/**
	 * 查询所有学生
	 * @param size 
	 * @param startPosition 
	 * @return
	 * @throws Exception
	 */
	public List<StuCustom> findAllStu(Integer startPosition, Integer size) throws Exception;

	/**
	 * 查询学生总记录数
	 * @return
	 * @throws Exception
	 */
	public int findAllCount() throws Exception;

	/**
	 * 通过学生Id删除学生
	 * @param stuId
	 * @throws Exception
	 */
	public void deleteStu(Integer stuId) throws Exception;

	/**
	 * 更新学生信息
	 * @param stu
	 * @throws Exception
	 */
	public void updateStu(StuCustom stu) throws Exception;

	/**
	 * 根据手机号重置密码
	 * @param phone
	 * @param newPwd
	 * @throws Exception
	 */
	public void updatePwdByPhone(String phone, String newPwd) throws Exception;
}
