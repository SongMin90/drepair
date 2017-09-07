package com.drepair.service;

import java.util.List;

import com.drepair.po.EvalCustom;
import com.drepair.po.EvalCustom1;

/**
 * 评价Service
 * @author SongM
 * @date 2017年8月2日 下午5:20:57
 */
public interface EvalService {

	/**
	 * 管理员添加一条评价
	 * @param evalCustom
	 * @throws Exception
	 */
	public void save(EvalCustom evalCustom) throws Exception;
	
	/**
	 * 通过订单Id查询评价
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<EvalCustom> findByOrderId(Integer orderId) throws Exception;
	
	/**
	 * 查询评论内容和评论人ID及姓名
	 * @param evalCustom
	 * @return
	 * @throws Exception
	 */
	public EvalCustom1 findOrder(EvalCustom evalCustom) throws Exception;

	/**
	 * 通用添加评价
	 * @param evalCustom
	 * @throws Exception
	 */
	public void add(EvalCustom evalCustom) throws Exception;

	/**
	 * 通过订单ID删除评价
	 * @param id
	 * @throws Exception
	 */
	public void delByOrderId(int orderId) throws Exception;

	/**
	 * 通过管理员ID删除评价
	 * @param adminId
	 * @throws Exception
	 */
	public void delByAdminId(Integer adminId) throws Exception;

	/**
	 * 通过学生ID删除评价
	 * @param stuId
	 * @throws Exception
	 */
	public void delByStuId(Integer stuId) throws Exception;
	
	/**
	 * 通过宿管ID删除评价
	 * @param hmrId
	 * @throws Exception
	 */
	public void delByHmrId(Integer hmrId) throws Exception;
	
	/**
	 * 通过维修员ID删除评价
	 * @param repairerId
	 * @throws Exception
	 */
	public void delByRepairerId(Integer repairerId) throws Exception;
	
}
