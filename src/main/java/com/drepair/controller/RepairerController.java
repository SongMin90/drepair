package com.drepair.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.po.RepairerCustom;
import com.drepair.service.RepairerService;

/**
 * RepairerController
 * @author SongM
 * @date 2017年7月7日 下午6:15:01
 */
@Controller
@RequestMapping("/repairer")
public class RepairerController {

	@Autowired
	private RepairerService repairerService;
	
	/**
	 * 手机端登录操作
	 * @param repairerCustom
	 * @return
	 */
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> login(RepairerCustom repairerCustom) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login", "error");
		
		RepairerCustom repairer = null;
		try {
			if(repairerCustom.getRepairerPhone() != null && !repairerCustom.getRepairerPhone().equals("")) { // 判断是手机还是工号登录		
				repairer = repairerService.findByPhone(repairerCustom.getRepairerPhone());
			} else {
				repairer = repairerService.findById(repairerCustom.getRepairerId());
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		if(repairer == null) {
			if(repairerCustom.getRepairerPhone() != null && !repairerCustom.getRepairerPhone().equals("")) {
				map.put("reason", "手机号不存在！");
			} else {
				map.put("reason", "工号不存在！");
			}
		} else {
			if(!repairer.getRepairerPwd().equals(repairerCustom.getRepairerPwd())) {
				map.put("reason", "密码错误！");
			} else {
				map.put("login", "success");
			}
		}
		
		return map;
	}
	
	/**
	 * 获取到详细信息
	 * @param idOrPhone
	 * @return
	 */
	@RequestMapping(value="/findFullInfo", method={RequestMethod.GET})
	public @ResponseBody Map<String, String> findFullInfo(String idOrPhone) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("find", "error");
		
		RepairerCustom repairerCustom = null;
		try {
			if(idOrPhone.length() == 11) {
				repairerCustom = repairerService.findByPhone(idOrPhone);
			} else {
				repairerCustom = repairerService.findById(Integer.parseInt(idOrPhone));
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.getStackTrace();
		}
		
		if(repairerCustom != null) {
			map.put("repairerId", repairerCustom.getRepairerId()+"");
			map.put("repairerName", repairerCustom.getRepairerName());
			map.put("repairerPhone", repairerCustom.getRepairerPhone());
			map.put("find", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/addRepairer")
	public String addRepairer(Model model, RepairerCustom repairerCustom) {
		try {
			if(repairerCustom.getRepairerName() != null && repairerCustom.getRepairerIcard() != null
					&& repairerCustom.getRepairerPhone() != null && repairerCustom.getRepairerPwd() != null) {
				repairerService.save(repairerCustom);
				model.addAttribute("message", "新增成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "新增失败！");
			model.addAttribute("repairer", repairerCustom);
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/addRepairer";
	}
	
	/**
	 * 分页查询全部维修员
	 * @param model
	 * @param nowPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/allRepairer", method={RequestMethod.GET})
	public String allRepairer(Model model, Integer nowPage, Integer size) {
		// 默认值设置
		if(nowPage == null) {
			nowPage = 1;
		}
		if(size == null) {
			size = 10;
		}
		try {
			// 获取维修员总记录数
			int allCount = repairerService.findAllCount();
			// 计算出总页数
			int pageCount = allCount / size;
			if(allCount % size != 0) {
				pageCount++;
			}
			// 分页查询全部维修员信息
			List<RepairerCustom> repairerList = repairerService.findAllRepairer(nowPage * size - size, size);
			// 将全部维修员信发送到页面
			model.addAttribute("repairerList", repairerList);
			// 将总页数发送到页面
			model.addAttribute("pageCount", pageCount);
			// 将每页显示大小发送到页面
			model.addAttribute("size", size);
			// 将当前页发送到页面
			model.addAttribute("nowPage", nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/allRepairer";
	}
	
	/**
	 * 通过Id删除维修员
	 * @param repairerId
	 * @return
	 */
	@RequestMapping(value="delRepairer", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delRepairer(Integer repairerId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			repairerService.deleteRepairer(repairerId);
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 通过ID查询维修员信息跳转到编辑页面
	 * @param model
	 * @param repairerId
	 * @return
	 */
	@RequestMapping(value="/editRepairer", method={RequestMethod.GET})
	public String editRepairer(Model model, Integer repairerId) {
		try {
			RepairerCustom repairer = repairerService.findById(repairerId);
			// 将学生信息发送到页面
			model.addAttribute("repairer", repairer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/editRepairer";
	}
	
	/**
	 * 更新维修员信息
	 * @param model
	 * @param repairer
	 * @return
	 */
	@RequestMapping(value="/updateRepairer", method={RequestMethod.POST})
	public String updateRepairer(Model model, RepairerCustom repairer) {
		try {
			if(repairer.getRepairerId() != null && repairer.getRepairerName() != null && repairer.getRepairerPhone() != null
					&& repairer.getRepairerIcard() != null && repairer.getRepairerPwd() != null) {
				repairerService.updateRepairer(repairer);
				model.addAttribute("message", "更新成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "更新失败！");
			e.printStackTrace();
		}
		model.addAttribute("repairer", repairer); // 将信息重新发送到表单
		return "../WEB-INF/jsp/editRepairer";
	}
	
	/**
	 * 客户端根据手机号重置密码
	 * @param phone
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/updatePwd", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> updatePwd(String phone, String newPwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("update", "error");
		
		try {
			repairerService.updatePwdByPhone(phone, newPwd);
			map.put("update", "success");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
		}
		
		return map;
	}
	
	/**
	 * 批量删除维修员
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delAllRepairers", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAllRepairers(int[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 循环删除
			for (int id : ids) {
				// 通过订单ID删除该订单
				repairerService.deleteRepairer(id);
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
}
