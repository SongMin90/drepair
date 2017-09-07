package com.drepair.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.EvalMapperCustom;
import com.drepair.po.EvalCustom;
import com.drepair.po.EvalCustom1;
import com.drepair.service.EvalService;

/**
 * 评价ServiceImpl
 * @author SongM
 * @date 2017年8月2日 下午5:21:31
 */
public class EvalServiceImpl implements EvalService {

	@Autowired
	private EvalMapperCustom evalMapperCustom;
	
	@Override
	public void save(EvalCustom evalCustom) throws Exception {
		evalMapperCustom.save(evalCustom);
	}

	@Override
	public EvalCustom1 findOrder(EvalCustom evalCustom) throws Exception {
		return evalMapperCustom.findOrder(evalCustom);
	}

	@Override
	public List<EvalCustom> findByOrderId(Integer orderId) throws Exception {
		return evalMapperCustom.findByOrderId(orderId);
	}

	@Override
	public void add(EvalCustom evalCustom) throws Exception {
		evalMapperCustom.add(evalCustom);
	}

	@Override
	public void delByOrderId(int orderId) throws Exception {
		evalMapperCustom.delByOrderId(orderId);
	}

	@Override
	public void delByAdminId(Integer adminId) throws Exception {
		evalMapperCustom.delByAdminId(adminId);
	}

	@Override
	public void delByStuId(Integer stuId) throws Exception {
		evalMapperCustom.delByStuId(stuId);
	}

	@Override
	public void delByHmrId(Integer hmrId) throws Exception {
		evalMapperCustom.delByHmrId(hmrId);
	}

	@Override
	public void delByRepairerId(Integer repairerId) throws Exception {
		evalMapperCustom.delByRepairerId(repairerId);
	}

}
