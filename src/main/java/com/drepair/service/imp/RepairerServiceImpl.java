package com.drepair.service.imp;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.RepairerMapper;
import com.drepair.mapper.RepairerMapperCustom;
import com.drepair.po.Repairer;
import com.drepair.po.RepairerCustom;
import com.drepair.service.EvalService;
import com.drepair.service.OrderService;
import com.drepair.service.RepairerService;

public class RepairerServiceImpl implements RepairerService {

	@Autowired
	private RepairerMapper repairerMapper;
	
	@Autowired
	private RepairerMapperCustom repairerMapperCustom;
	
	@Autowired
	private EvalService evalService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public RepairerCustom findById(Integer repairerId) throws Exception {
		Repairer repairer = repairerMapper.selectByPrimaryKey(repairerId);
		RepairerCustom repairerCustom = null;
		if(repairer != null) {
			repairerCustom = new RepairerCustom();
			BeanUtils.copyProperties(repairer, repairerCustom);
		}
		return repairerCustom;
	}

	@Override
	public RepairerCustom findByPhone(String repairerPhone) throws Exception {
		return repairerMapperCustom.findByPhone(repairerPhone);
	}

	@Override
	public void save(RepairerCustom repairerCustom) throws Exception {
		repairerMapperCustom.save(repairerCustom);
	}

	@Override
	public int findAllCount() throws Exception {
		return repairerMapperCustom.findAllCount();
	}

	@Override
	public List<RepairerCustom> findAllRepairer(int startPosition, Integer size) throws Exception {
		return repairerMapperCustom.findAllRepairer(startPosition, size);
	}

	@Override
	public void deleteRepairer(Integer repairerId) throws Exception {
		// 1、先删除维修员的全部评价
		evalService.delByRepairerId(repairerId);
		
		// 2、再将该维修员的所有订单的外键清空
		orderService.setNullByRepairerId(repairerId);
		
		// 3、再删除改维修员
		repairerMapperCustom.deleteRepairer(repairerId);
	}

	@Override
	public void updateRepairer(RepairerCustom repairer) throws Exception {
		repairerMapperCustom.updateRepairer(repairer);
	}

	@Override
	public void updatePwdByPhone(String phone, String newPwd) throws Exception {
		repairerMapperCustom.updatePwdByPhone(phone, newPwd);
	}

}
