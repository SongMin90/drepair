package com.drepair.service.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.StuMapper;
import com.drepair.mapper.StuMapperCustom;
import com.drepair.po.OrderCustom;
import com.drepair.po.Stu;
import com.drepair.po.StuCustom;
import com.drepair.service.EvalService;
import com.drepair.service.OrderService;
import com.drepair.service.StuService;

public class StuServiceImpl implements StuService {

	@Autowired
	private StuMapper stuMapper;
	
	@Autowired
	private StuMapperCustom stuMapperCustom;
	
	@Autowired
	private EvalService evalService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public StuCustom findById(String id) throws Exception {
		Stu stu = stuMapper.selectByPrimaryKey(id);
		
		StuCustom stuCustom = null;
		
		if(stu == null) {
			//throw new CustomException("通过ID没找到此学生！");
		} else {
			stuCustom = new StuCustom();
			BeanUtils.copyProperties(stu, stuCustom);
		}
		
		return stuCustom;
	}

	@Override
	public void save(StuCustom stuCustom) throws Exception {
		stuMapper.insert(stuCustom);
	}

	@Override
	public StuCustom findByPhone(String stuPhone) throws Exception {
		return stuMapperCustom.findByPhone(stuPhone);
	}

	@Override
	public int findAllCount() throws Exception {
		return stuMapperCustom.findAllCount();
	}

	@Override
	public List<StuCustom> findAllStu(int startPosition, Integer size) throws Exception {
		return stuMapperCustom.findAllStu(startPosition, size);
	}

	@Override
	public void deleteStu(HttpServletRequest request, Integer stuId) throws Exception {
		// 1、先删除该学生的所有评价
		evalService.delByStuId(stuId);
		
		// 2、再删除该学生的所有订单
		List<OrderCustom> orderList = orderService.findByStuId(stuId);
		for (OrderCustom orderCustom : orderList) {
			orderService.delById(request, orderCustom.getOrderId());
		}
		
		// 3、在删除该学生
		stuMapperCustom.deleteStu(stuId);
	}

	@Override
	public void updateStu(StuCustom stu) throws Exception {
		stuMapperCustom.updateStu(stu);
	}

	@Override
	public void updatePwdByPhone(String phone, String newPwd) throws Exception {
		stuMapperCustom.updatePwdByPhone(phone, newPwd);
	}

}
