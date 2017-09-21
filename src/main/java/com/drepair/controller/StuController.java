package com.drepair.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.api.json.Analysis;
import com.drepair.api.net.HttpUtils;
import com.drepair.api.po.Stu;
import com.drepair.exception.CustomException;
import com.drepair.po.StuCustom;
import com.drepair.service.StuService;
import com.drepair.utils.FileHelper;

/**
 * StuController
 * @author SongM
 * @date 2017年6月23日 下午5:37:08
 */
@Controller
@RequestMapping("/stu") //为了对url进行分类管理
public class StuController {

	@Autowired
	private StuService stuService;
	
	/**
	 * 学生登录
	 * @param stuCustom
	 * @return
	 */
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> login(StuCustom stuCustom) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login", "error");
		StuCustom stuInfo = null;
		String str = "";
		// 判断是学号还是手机号登录
		try {
			if(stuCustom.getStuId() != null && !stuCustom.getStuId().equals("")) {
				stuInfo = stuService.findById(stuCustom.getStuId());
				str = "学号";
			} else {
				stuInfo = stuService.findByPhone(stuCustom.getStuPhone());
				str = "手机号";
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		if(stuInfo == null) {
			map.put("reason", str + "不存在！");
		} else {
			if(!stuInfo.getStuPwd().equals(stuCustom.getStuPwd())) {
				map.put("reason", "密码错误！");
			} else {
				map.put("login", "success");
			}
		}
		return map;
	}
	
	/**
	 * 学生注册
	 * @param stuCustom
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value="/register", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> register(StuCustom stuCustom, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("register", "error");
		
		// 验证注册的信息
		StuCustom stu = null;
		// 验证学号是否注册
		try {
			stu = stuService.findById(stuCustom.getStuId());
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		if(stu != null) {
			map.put("reason", "学号已经注册，请直接登录！");
			return map;
		}
		// 验证手机号是否注册
		try {
			stu = stuService.findByPhone(stuCustom.getStuPhone());
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		if(stu != null) {
			map.put("reason", "手机号已经注册！");
			return map;
		}
		
		// TODO 这里调用罗何元的通过学号查询数据，如果返回error表示学号不存在，即注册失败
		if(WebsetCotroller.read(request).getApiState().equals("on")) {
			try {
				String json = HttpUtils.getJSON(HttpUtils.STU_URL, stuCustom.getStuId());
				Stu forStu = Analysis.forStu(json);
				if(forStu.getFind().equals("error")) {
					map.put("reason", "学号不存在！");
					return map;
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("reason", "网络接口取值失败！");
				return map;
			}
		}
		
		// 信息存入数据库
		try {
			stuService.save(stuCustom);
			map.put("register", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 获取学生信息
	 * @param idOrPhone
	 * @return
	 */
	@RequestMapping(value="/findFullInfo", method={RequestMethod.GET})
	public @ResponseBody Map<String, String> findFullInfo(String idOrPhone, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("find", "error");
		
		StuCustom stuCustom = null;
		
		// 判断是学号还是手机号
		try {
			if(idOrPhone.length() == 11) {
				stuCustom = stuService.findByPhone(idOrPhone);
			} else {
				stuCustom = stuService.findById(idOrPhone);
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.getStackTrace();
		}
		
		// 取出数据存入map
		if(stuCustom != null) {
			map.put("id", stuCustom.getStuId() + "");
			map.put("phone", stuCustom.getStuPhone() + "");
			
			// TODO 通过学号或工号取出学生详细信息（罗何元的接口）
			Stu forStu = new Stu();
			if(WebsetCotroller.read(request).getApiState().equals("on")) {
				try {
					String json = HttpUtils.getJSON(HttpUtils.STU_URL, stuCustom.getStuId());
					forStu = Analysis.forStu(json);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("reason", "网络接口取值失败！");
					return map;
				}
			} else {
				// 模拟数据
				String path = request.getServletContext().getRealPath("/WEB-INF/") + "/stu.json";
				String json = FileHelper.readUTF8(path);
				forStu = Analysis.forStu(json);
			}
			
			if(forStu.getFind().equals("success")) {
				map.put("name", forStu.getStudent().getStudentName());
				map.put("room", forStu.getStudent().getDormInfo().getDormName() + "-" + forStu.getStudent().getDormroomInfo().getRoomNum());
				
				map.put("find", "success");
			} else {
				map.put("reason", "网络接口取值失败！");
			}
		}
		
		return map;
	}
	
	/**
	 * 分页查询全部学生信息
	 * @param nowPage
	 * @param size
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/allStu", method={RequestMethod.GET})
	public String allStu(Integer nowPage, Integer size, Model model) {
		// 默认值设置
		if(nowPage == null) {
			nowPage = 1;
		}
		if(size == null) {
			size = 10;
		}
		try {
			int allCount = stuService.findAllCount(); // 取到学生总记录数
			// 计算出总页数
			int pageCount = allCount / size;
			if(allCount % size != 0) {
				pageCount++;
			}
			// 分页查询全部学生信息
			List<StuCustom> stuList = stuService.findAllStu(nowPage * size - size, size);
			
			// 将全部学生信息发送到页面
			model.addAttribute("stuList", stuList);
			// 将总页数发送到页面
			model.addAttribute("pageCount", pageCount);
			// 将每页显示大小发送到页面
			model.addAttribute("size", size);
			// 将当前页发送到页面
			model.addAttribute("nowPage", nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/allStu";
	}
	
	/**
	 * 通过学生Id删除学生
	 * @param stuId
	 * @return
	 */
	@RequestMapping(value="/delStu", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delStu(HttpServletRequest request, Integer stuId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			stuService.deleteStu(request, stuId);
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 通过学生ID跳转到编辑学生信息
	 * @param model
	 * @param stuId
	 * @return
	 */
	@RequestMapping(value="editStu", method={RequestMethod.GET})
	public String editStu(Model model, String stuId, HttpServletRequest request) {
		try {
			StuCustom stu = stuService.findById(stuId);
			
			// TODO 这里根据api获取数据
			Stu forStu = new Stu();
			if(WebsetCotroller.read(request).getApiState().equals("on")) {
				try {
					String json = HttpUtils.getJSON(HttpUtils.STU_URL, stuId);
					forStu = Analysis.forStu(json);
				} catch (Exception e) {
					e.printStackTrace();
					new CustomException("网络接口取值失败！");
				}
			} else {
				// 模拟数据
				String path = request.getServletContext().getRealPath("/WEB-INF/") + "/stu.json";
				String json = FileHelper.readUTF8(path);
				forStu = Analysis.forStu(json);
			}
			
			stu.setStuName(forStu.getStudent().getStudentName());
			stu.setStuSex(forStu.getStudent().getStudentSex());
			stu.setStuRoom(forStu.getStudent().getDormInfo().getDormName() + "-" + forStu.getStudent().getDormroomInfo().getRoomNum());
			stu.setStuIcard(forStu.getStudent().getStudentIdcard());
			
			// 将学生信息发送到页面
			model.addAttribute("stu", stu);
		} catch (Exception e) {
			new CustomException("网络接口取值失败！");
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/editStu";
	}
	
	/**
	 * 更新学生信息
	 * @param stu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateStu", method={RequestMethod.POST})
	public String updateStu(StuCustom stu, Model model) {
		try {
			if(stu.getStuId() != null && stu.getStuPwd() != null && stu.getStuPhone() != null) {
				stuService.updateStu(stu);
				model.addAttribute("message", "更新成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "更新失败！");
			e.printStackTrace();
		}
		model.addAttribute("stu", stu); // 将信息重新发送到表单
		return "../WEB-INF/jsp/editStu";
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
			stuService.updatePwdByPhone(phone, newPwd);
			map.put("update", "success");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
		}
		
		return map;
	}
	
	/**
	 * 批量删除学生
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delAllStus", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAllStus(HttpServletRequest request, int[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 循环删除
			for (int id : ids) {
				// 通过订单ID删除该订单
				stuService.deleteStu(request, id);
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
}
