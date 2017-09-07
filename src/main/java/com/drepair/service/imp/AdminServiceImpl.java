package com.drepair.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.AdminMapperCustom;
import com.drepair.po.AdminCustom;
import com.drepair.service.AdminService;
import com.drepair.service.EvalService;

/**
 * AdminServiceImpl
 * @author SongM
 * @date 2017年7月26日 下午5:22:20
 */
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapperCustom adminMapperCustom;
	
	@Autowired
	private EvalService evalService;

	@Override
	public AdminCustom findById(Integer adminId) throws Exception {
		return adminMapperCustom.findById(adminId);
	}

	@Override
	public AdminCustom findByPhone(String adminPhone) throws Exception {
		return adminMapperCustom.findByPhone(adminPhone);
	}

	@Override
	public void save(AdminCustom adminCustom) throws Exception {
		adminMapperCustom.save(adminCustom);
	}

	@Override
	public void updateById(AdminCustom adminCustom) throws Exception {
		adminMapperCustom.updateById(adminCustom);
	}

	@Override
	public void deleteById(Integer adminId) throws Exception {
		// 1、先删除该管理的全部评价
		evalService.delByAdminId(adminId);
		
		// 2、再删除该管理员
		adminMapperCustom.deleteById(adminId);
	}

	@Override
	public List<AdminCustom> findAllAdmin(Integer startPosition, Integer size) throws Exception {
		return adminMapperCustom.findAllAdmin(startPosition, size);
	}

	@Override
	public Integer findAllCount() throws Exception {
		return adminMapperCustom.findAllCount();
	}

	@Override
	public void updatePwdByPhone(String phone, String new_password) throws Exception {
		adminMapperCustom.updatePwdByPhone(phone, new_password);
	}
	
}
