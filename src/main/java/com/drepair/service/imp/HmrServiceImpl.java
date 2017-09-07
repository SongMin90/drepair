package com.drepair.service.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.HmrMapperCustom;
import com.drepair.po.HmrCustom;
import com.drepair.po.OrderCustom;
import com.drepair.service.EvalService;
import com.drepair.service.HmrService;
import com.drepair.service.OrderService;

/**
 * 宿管Service实现类
 * @author SongM
 * @date 2017年8月9日 下午3:45:32
 */
public class HmrServiceImpl implements HmrService {

	@Autowired
	private HmrMapperCustom hmrMapperCustom;
	
	@Autowired
	private EvalService evalService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public int findAllCount() throws Exception {
		return hmrMapperCustom.findAllCount();
	}

	@Override
	public List<HmrCustom> findAllHmr(int startPosition, Integer size) throws Exception {
		return hmrMapperCustom.findAllHmr(startPosition, size);
	}

	@Override
	public void deleteHmr(HttpServletRequest request, Integer hmrId) throws Exception {
		// 1、先删除该宿管的所有评价
		evalService.delByHmrId(hmrId);
		
		// 2、再删除该宿管的所有订单
		List<OrderCustom> orderList = orderService.findByHmrId(hmrId);
		for (OrderCustom orderCustom : orderList) {
			orderService.delById(request, orderCustom.getOrderId());
		}
		
		// 3、再删除该宿管
		hmrMapperCustom.deleteHmr(hmrId);
	}

	@Override
	public HmrCustom findById(Integer hmrId) throws Exception {
		return hmrMapperCustom.findById(hmrId);
	}

	@Override
	public void updateHmr(HmrCustom hmr) throws Exception {
		hmrMapperCustom.updateHmr(hmr);
	}

	@Override
	public HmrCustom findByPhone(String hmrPhone) throws Exception {
		return hmrMapperCustom.findByPhone(hmrPhone);
	}

	@Override
	public void save(HmrCustom hmrCustom) throws Exception {
		hmrMapperCustom.save(hmrCustom);
	}

	@Override
	public void updatePwdByPhone(String phone, String newPwd) throws Exception {
		hmrMapperCustom.updatePwdByPhone(phone, newPwd);
	}

}
