package com.drepair.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drepair.po.StuCustom;

/**
 * StuService
 * @author SongM
 * @date 2017年6月23日 下午5:38:38
 */
public interface StuService {

	/**
	 * 学生通过ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StuCustom findById(String id) throws Exception;
	
	/**
	 * 添加学生
	 * @param stuCustom
	 * @throws Exception
	 */
	public void save(StuCustom stuCustom) throws Exception;

	/**
	 * 学生通过手机号码查询
	 * @param stuPhone
	 * @return
	 * @throws Exception
	 */
	public StuCustom findByPhone(String stuPhone) throws Exception;
	
	/**
	 * 查询所有学生信息
	 * @param size 
	 * @param startPosition 
	 * @return
	 * @throws Exception
	 */
	public List<StuCustom> findAllStu(int startPosition, Integer size) throws Exception;

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
	public void deleteStu(HttpServletRequest request, Integer stuId) throws Exception;

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
