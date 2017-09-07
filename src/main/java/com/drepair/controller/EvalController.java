package com.drepair.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.po.EvalCustom;
import com.drepair.po.EvalCustom1;
import com.drepair.po.EvalCustom2;
import com.drepair.service.EvalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 评价Controller
 * @author SongM
 * @date 2017年8月24日 下午6:50:13
 */
@Controller
@RequestMapping("eval")
public class EvalController {

	@Autowired
	private EvalService evalService;
	
	/**
	 * 通用对报修订单添加评价
	 * @param id
	 * @param profession
	 * @param orderId
	 * @param evalContent
	 * @return
	 */
	@RequestMapping(value="addEval", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> addEval(int id, String profession, int orderId, String evalContent) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("add", "error");
		try {
			EvalCustom evalCustom = new EvalCustom();
			
			// 判断是否是学生，宿管还是维修员，并设置 ID
			if(profession.equals("stu")) {
				evalCustom.setStuId(id+"");
			} else if(profession.equals("hmr")) {
				evalCustom.setHmrId(id);
			} else if(profession.equals("repairer")) {
				evalCustom.setRepairerId(id);
			}
			
			// 设置评价其他参数
			evalCustom.setOrderId(orderId);
			evalCustom.setEvalContent(evalContent);
			
			// 添加评价
			evalService.add(evalCustom);
			
			// 发送添加成功的json
			map.put("add", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 安卓端根据订单Id获取所有评价，返回Json数据
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/getEvalByOrderId", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> getEvalByOrderId(int orderId) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<EvalCustom2> evalsList = new ArrayList<EvalCustom2>();
			List<EvalCustom> evalList = evalService.findByOrderId(orderId);
			for(int i=0; i<evalList.size(); i++) {
				EvalCustom2 evalCustom2 = new EvalCustom2();
				// 取出评价人信息
				if(evalList.get(i).getStuId() != null) {
					// TODO 根据api获取学生姓名，先模拟数据
					evalCustom2.setEvalName("张三");
				} else if(evalList.get(i).getHmrId() != null) {
					// TODO 根据api获取宿管姓名，先模拟数据
					evalCustom2.setEvalName("张三");
				} else {
					// 查询评论内容和评论人ID及姓名
					EvalCustom1 evalCustom1 = evalService.findOrder(evalList.get(i));
					// 设置评价人姓名
					if(evalCustom1.getAdminCustom() != null) {
						evalCustom2.setEvalName(evalCustom1.getAdminCustom().getAdminName());
					} else if (evalCustom1.getRepairerCustom() != null) {
						evalCustom2.setEvalName(evalCustom1.getRepairerCustom().getRepairerName());
					}
				}
				// 设置评价内容
				evalCustom2.setEvalContent(evalList.get(i).getEvalContent());
				evalsList.add(evalCustom2);
			}
			
			// 将评价列表存入map
			map.put("evalList", evalsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
}
