package com.drepair.service;

import java.util.List;

import com.drepair.po.AdminCustom;

/**
 * AdminService
 * @author SongM
 * @date 2017年7月26日 下午5:21:26
 */
public interface AdminService {

	/**
	 * 通过ID查询
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public AdminCustom findById(Integer adminId) throws Exception;
	
	/**
	 * 通过手机号查询
	 * @param adminPhone
	 * @return
	 * @throws Exception
	 */
	public AdminCustom findByPhone(String adminPhone) throws Exception;
	
	/**
	 * 添加一条记录
	 * @param adminCustom
	 * @throws Exception
	 */
	public void save(AdminCustom adminCustom) throws Exception;
	
	/**
	 * 通过ID更新一条记录
	 * @param adminCustom
	 * @throws Exception
	 */
	public void updateById(AdminCustom adminCustom) throws Exception;
	
	/**
	 * 通过Id删除一条记录
	 * @param adminId
	 * @throws Exception
	 */
	public void deleteById(Integer adminId) throws Exception;
	
	/**
	 * 分页查询全部管理员
	 * @param size 
	 * @param startPosition 
	 * @throws Exception
	 */
	public List<AdminCustom> findAllAdmin(Integer startPosition, Integer size) throws Exception;
	
	/**
	 * 查询管理员总记录数
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCount() throws Exception;

	/**
	 * 管理员根据手机号重置密码
	 * @param phone
	 * @param new_password
	 * @throws Exception
	 */
	public void updatePwdByPhone(String phone, String new_password) throws Exception;
}
