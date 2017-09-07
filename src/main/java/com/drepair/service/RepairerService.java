package com.drepair.service;

import java.util.List;

import com.drepair.po.RepairerCustom;

/**
 * RepairerService
 * @author SongM
 * @date 2017年7月7日 下午3:02:12
 */
public interface RepairerService {

	/**
	 * 维修员通过ID查询
	 * @param repairerId
	 * @return
	 * @throws Exception
	 */
	public RepairerCustom findById(Integer repairerId) throws Exception;

	/**
	 * 维修员通过手机号查询
	 * @param repairerPhone
	 * @return
	 */
	public RepairerCustom findByPhone(String repairerPhone) throws Exception;

	/**
	 * 添加维修员
	 * @param repairerCustom
	 * @throws Exception
	 */
	public void save(RepairerCustom repairerCustom) throws Exception;
	
	/**
	 * 查询全部维修员信息
	 * @param size 
	 * @param startPosition 
	 * @return
	 * @throws Exception
	 */
	public List<RepairerCustom> findAllRepairer(int startPosition, Integer size) throws Exception;

	/**
	 * 获取维修员总记录数
	 * @return
	 * @throws Exception
	 */
	public int findAllCount() throws Exception;

	/**
	 * 通过ID删除维修员
	 * @param repairerId
	 * @throws Exception
	 */
	public void deleteRepairer(Integer repairerId) throws Exception;

	/**
	 * 更新维修员信息
	 * @param repairer
	 * @throws Exception
	 */
	public void updateRepairer(RepairerCustom repairer) throws Exception;

	/**
	 * 客户端根据手机号重置密码
	 * @param phone
	 * @param newPwd
	 * @throws Exception
	 */
	public void updatePwdByPhone(String phone, String newPwd) throws Exception;
}
