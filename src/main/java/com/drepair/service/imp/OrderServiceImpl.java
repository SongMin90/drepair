package com.drepair.service.imp;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.OrderMapperCustom;
import com.drepair.po.OrderCustom;
import com.drepair.service.EvalService;
import com.drepair.service.ImgService;
import com.drepair.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapperCustom orderMapperCustom;
	
	@Autowired
	private ImgService imgService;
	
	@Autowired
	private EvalService evalService;
	
	@Override
	public Integer save(OrderCustom orderCustom) throws Exception {
		orderCustom.setOrderSort(0); // 订单评分初始设为0
		orderMapperCustom.addOrder(orderCustom);
		return orderMapperCustom.getNowAddOrderId();
	}

	@Override
	public List<OrderCustom> findAllOrder(Integer isStu, Integer orderState, Integer startPosition, Integer size) throws Exception {
		if(isStu == 1) {
			return orderMapperCustom.findAllOrderByStu(orderState, startPosition, size);
		}
		return orderMapperCustom.findAllOrderByHmr(orderState, startPosition, size);
	}

	@Override
	public Integer findAllOrderCount(Integer isStu, Integer orderState) throws Exception {
		if(isStu == 1) {
			return orderMapperCustom.findAllCountByStu(orderState);
		}
		return orderMapperCustom.findAllCountByHmr(orderState);
	}

	@Override
	public void passOrder(Integer orderId, Integer adminId, Integer orderSort) throws Exception {
		orderMapperCustom.passOrder(orderId, adminId, orderSort);
	}

	@Override
	public void failOrder(Integer orderId, Integer adminId) throws Exception {
		OrderCustom orderCustom = new OrderCustom();
		orderCustom.setOrderState(3);
		orderCustom.setOrderId(orderId);
		orderCustom.setAdminId(adminId);
		orderCustom.setOrderOverTime(new Date());
		orderMapperCustom.failOrder(orderCustom);
	}

	@Override
	public List<OrderCustom> findOrderByPass(Integer startPosition, Integer size) throws Exception {
		return orderMapperCustom.findOrderByPass(startPosition, size);
	}

	@Override
	public OrderCustom findOrderById(Integer orderId) throws Exception {	
		return orderMapperCustom.findOrderById(orderId);
	}

	@Override
	public void repairerGetOrder(Integer repairerId, Integer orderId) throws Exception {
		orderMapperCustom.repairerGetOrder(repairerId, orderId);
	}

	@Override
	public List<OrderCustom> getCommittedOrders(OrderCustom orderCustom) throws Exception {
		return orderMapperCustom.getCommittedOrders(orderCustom);
	}

	@Override
	public List<OrderCustom> getRepairOrders(Integer orderState, Integer repairerId) throws Exception {
		return orderMapperCustom.getRepairOrders(orderState, repairerId);
	}
	
	@Override
	public void update(OrderCustom orderCustom) throws Exception {
		orderMapperCustom.updateById(orderCustom);
	}

	@Override
	public void orderIsRepair(OrderCustom orderCustom) throws Exception {
		orderMapperCustom.orderIsRepair(orderCustom);
	}

	@Override
	public void delById(HttpServletRequest request, int id) throws Exception {
		// 级联操作
		// 1、先删除该订单的所有图片
		imgService.delById(request, id);
		
		// 2、再删除该订单的所有评价
		evalService.delByOrderId(id);
		
		// 3、再删除该订单记录
		orderMapperCustom.delById(id);
	}

	@Override
	public List<OrderCustom> findByStuId(Integer stuId) throws Exception {
		return orderMapperCustom.findByStuId(stuId);
	}

	@Override
	public List<OrderCustom> findByHmrId(Integer hmrId) throws Exception {
		return orderMapperCustom.findByHmrId(hmrId);
	}

	@Override
	public void setNullByRepairerId(Integer repairerId) throws Exception {
		orderMapperCustom.setNullByRepairerId(repairerId);
	}

}
