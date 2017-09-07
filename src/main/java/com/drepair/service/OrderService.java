package com.drepair.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drepair.po.OrderCustom;

/**
 * OrderService
 * @author SongM
 * @date 2017年7月6日 上午11:42:33
 */
public interface OrderService {
	
	/**
	 * 新增报修订单，返回新增订单的自增ID
	 * @param orderCustom
	 * @return
	 * @throws Exception
	 */
	public Integer save(OrderCustom orderCustom) throws Exception;

	/**
	 * 根据是否是属于学生和订单状态分页取到全部报修订单
	 * @return
	 * @throws Exception 
	 */
	public List<OrderCustom> findAllOrder(Integer isStu, Integer orderState, Integer startPosition, Integer size) throws Exception;

	/**
	 * 根据是否是属于学生和订单状态取到全部报修订单总页数
	 * @param isStu
	 * @param orderState
	 * @return
	 * @throws Exception
	 */
	public Integer findAllOrderCount(Integer isStu, Integer orderState) throws Exception;

	/**
	 * 报修订单审核通过
	 * @param orderId
	 * @throws Exception
	 */
	public void passOrder(Integer orderId, Integer adminId, Integer orderSort) throws Exception;

	/**
	 * 报修订单审核不通过
	 * @param orderId
	 * @throws Exception
	 */
	public void failOrder(Integer orderId, Integer adminId) throws Exception;
	
	/**
	 * 分页查询所有审核通过的报修订单（根据订单级别降序）
	 * @param startPosition
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<OrderCustom> findOrderByPass(Integer startPosition, Integer size) throws Exception;

	/**
	 * 通过ID查询报修订单
	 * @param orderId
	 * @return
	 */
	public OrderCustom findOrderById(Integer orderId) throws Exception;

	/**
	 * 维修员抢单
	 * @param repairerId
	 * @throws Exception
	 */
	public void repairerGetOrder(Integer repairerId, Integer orderId) throws Exception;

	/**
	 * 报修端获取已经提交的全部订单
	 * @param orderCustom
	 * @return
	 */
	public List<OrderCustom> getCommittedOrders(OrderCustom orderCustom) throws Exception;

	/**
	 * 抢单端获取自己的全部订单
	 * @param orderState
	 * @param repairerId
	 * @return
	 */
	public List<OrderCustom> getRepairOrders(Integer orderState, Integer repairerId) throws Exception;

	/**
	 * 更新订单信息
	 * @param orderCustom
	 * @throws Exception
	 */
	public void update(OrderCustom orderCustom) throws Exception;

	/**
	 * 维修员提交已维修的订单
	 * @param orderCustom
	 * @throws Exception
	 */
	public void orderIsRepair(OrderCustom orderCustom) throws Exception;

	/**
	 * 通过订单ID删除订单
	 * @param id
	 * @throws Exception
	 */
	public void delById(HttpServletRequest request, int id) throws Exception;

	/**
	 * 通过学生ID查询订单信息
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public List<OrderCustom> findByStuId(Integer stuId) throws Exception;
	
	/**
	 * 通过宿管ID查询订单信息
	 * @param hmrId
	 * @return
	 * @throws Exception
	 */
	public List<OrderCustom> findByHmrId(Integer hmrId) throws Exception;

	/**
	 * 通过维修员ID将所有订单的维修员ID为null
	 * @param repairerId
	 * @throws Exception
	 */
	public void setNullByRepairerId(Integer repairerId) throws Exception;

}
