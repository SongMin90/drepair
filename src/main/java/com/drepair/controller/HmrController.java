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
import com.drepair.api.po.Hmr;
import com.drepair.exception.CustomException;
import com.drepair.po.HmrCustom;
import com.drepair.service.HmrService;
import com.drepair.utils.FileHelper;

/**
 * 宿管Controller
 * @author SongM
 * @date 2017年8月9日 下午3:47:46
 */
@Controller
@RequestMapping("/hmr")
public class HmrController {

	@Autowired
	private HmrService hmrService;
	
	/**
	 * 分页查询全部宿管
	 * @param model
	 * @param nowPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="allHmr", method={RequestMethod.GET})
	public String allHmr(Model model, Integer nowPage, Integer size) {
		// 默认值设置
		if(nowPage == null) {
			nowPage = 1;
		}
		if(size == null) {
			size = 10;
		}
		try {
			int allCount = hmrService.findAllCount(); // 取到宿管总记录数
			// 计算出总页数
			int pageCount = allCount / size;
			if(allCount % size != 0) {
				pageCount++;
			}
			// 分页查询全部学生信息
			List<HmrCustom> hmrList = hmrService.findAllHmr(nowPage * size - size, size);
			
			// 将全部学生信息发送到页面
			model.addAttribute("hmrList", hmrList);
			// 将总页数发送到页面
			model.addAttribute("pageCount", pageCount);
			// 将每页显示大小发送到页面
			model.addAttribute("size", size);
			// 将当前页发送到页面
			model.addAttribute("nowPage", nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/allHmr";
	}
	
	/**
	 * 通过宿管Id删除宿管
	 * @param hmrId
	 * @return
	 */
	@RequestMapping(value="/delHmr", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delHmr(HttpServletRequest request, Integer hmrId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			hmrService.deleteHmr(request, hmrId);
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 通过宿管ID跳转到编辑宿管信息
	 * @param model
	 * @param hmrId
	 * @return
	 */
	@RequestMapping(value="editHmr", method={RequestMethod.GET})
	public String editHmr(Model model, Integer hmrId, HttpServletRequest request) {
		try {
			HmrCustom hmr = hmrService.findById(hmrId);
			
			// TODO 网络接口调用
			Hmr forHmr = new Hmr();
			if(WebsetCotroller.read(request).getApiState().equals("on")) {
				try {
					String json = HttpUtils.getJSON(HttpUtils.HMR_URL, hmrId+"");
					forHmr = Analysis.forHmr(json);
				} catch (Exception e) {
					new CustomException("网络接口取值失败！");
					e.printStackTrace();
				}
			} else {
				// 模拟数据
				String path = request.getServletContext().getRealPath("/WEB-INF/") + "/hmr.json";
				String json = FileHelper.readUTF8(path);
				forHmr = Analysis.forHmr(json);
			}
			
			hmr.setHmrName(forHmr.getManager().getManagerName());
			hmr.setHmrFloor(forHmr.getManager().getDormInfo().getDormName());
			hmr.setHmrSex("性别");
			hmr.setHmrIcard("身份证号码");
			
			// 将学生信息发送到页面
			model.addAttribute("hmr", hmr);
		} catch (Exception e) {
			new CustomException("网络接口取值失败！");
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/editHmr";
	}
	
	/**
	 * 更新宿管信息
	 * @param hmr
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateHmr", method={RequestMethod.POST})
	public String updateHmr(HmrCustom hmr, Model model) {
		try {
			if(hmr.getHmrId() != null && hmr.getHmrPwd() != null && hmr.getHmrPhone() != null) {
				hmrService.updateHmr(hmr);
				model.addAttribute("message", "更新成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "更新失败！");
			e.printStackTrace();
		}
		model.addAttribute("hmr", hmr); // 将信息重新发送到表单
		return "../WEB-INF/jsp/editHmr";
	}
	
	/**
	 * 客户端宿管登录验证
	 * @param hmrCustom
	 * @return
	 */
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> login(HmrCustom hmrCustom) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login", "error");
		HmrCustom hmr = null;
		try {
			// 判断是ID还是Phone登录
			if(hmrCustom.getHmrId() != null) {
				hmr = hmrService.findById(hmrCustom.getHmrId());
			} else if(hmrCustom.getHmrPhone() != null) {
				hmr = hmrService.findByPhone(hmrCustom.getHmrPhone());
			}
			// 判断是否登录成功
			if(hmr != null) {
				if(hmr.getHmrPwd().equals(hmrCustom.getHmrPwd())) {
					map.put("login", "success");
				} else {
					map.put("reason", "密码错误！");
				}
			} else {
				if(hmrCustom.getHmrId() != null) {
					map.put("reason", "工号不存在！");
				} else {
					map.put("reason", "手机号不存在！");
				}
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询宿管详细信息
	 * @param idOrPhone
	 * @return
	 */
	@RequestMapping(value="/findFullInfo", method={RequestMethod.GET})
	public @ResponseBody Map<String, String> findFullInfo(String idOrPhone, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("find", "error");
		
		HmrCustom hmrCustom = null;
		try {
			// 判断是ID还是Phone
			if(idOrPhone.length() == 11) {
				hmrCustom = hmrService.findByPhone(idOrPhone);
			} else {
				hmrCustom = hmrService.findById(Integer.parseInt(idOrPhone));
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.getStackTrace();
		}
		
		// 取出数据存入map
		if(hmrCustom != null) {
			map.put("id", hmrCustom.getHmrId() + "");
			map.put("phone", hmrCustom.getHmrPhone() + "");
			
			// TODO 网络接口调用
			Hmr forHmr = new Hmr();
			if(WebsetCotroller.read(request).getApiState().equals("on")) {
				try {
					String json = HttpUtils.getJSON(HttpUtils.HMR_URL, hmrCustom.getHmrId()+"");
					forHmr = Analysis.forHmr(json);
				} catch (Exception e) {
					map.put("reason", "网络接口取值失败！");
					e.printStackTrace();
					return map;
				}
			} else {
				// 模拟数据
				String path = request.getServletContext().getRealPath("/WEB-INF/") + "/hmr.json";
				String json = FileHelper.readUTF8(path);
				forHmr = Analysis.forHmr(json);
			}
			
			if(forHmr.getFind().equals("success")) {
				map.put("name", forHmr.getManager().getManagerName());
				map.put("room", forHmr.getManager().getDormInfo().getDormName());
				
				map.put("find", "success");
			}
		}
		
		return map;
	}
	
	/**
	 * 宿管注册
	 * @param hmrCustom
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value="/register", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> register(HmrCustom hmrCustom, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("register", "error");
		try {
			// 验证工号是否已经注册
			if(hmrService.findById(hmrCustom.getHmrId()) != null) {
				map.put("reason", "工号已经注册，请直接登录！");
				return map;
			}
			
			// 验证手机号是否注册
			if(hmrService.findByPhone(hmrCustom.getHmrPhone()) != null) {
				map.put("reason", "手机号已经注册！");
				return map;
			}
			
			// TODO 这里调用罗何元的通过工号查询数据，如果返回为error表示工号不存在，即注册失败
			Hmr forHmr = new Hmr();
			if(WebsetCotroller.read(request).getApiState().equals("on")) {
				try {
					String json = HttpUtils.getJSON(HttpUtils.HMR_URL, hmrCustom.getHmrId()+"");
					forHmr = Analysis.forHmr(json);
				} catch (Exception e) {
					map.put("reason", "网络接口取值失败！");
					e.printStackTrace();
					return map;
				}
			} else {
				// 模拟数据
				String path = request.getServletContext().getRealPath("/WEB-INF/") + "/hmr.json";
				String json = FileHelper.readUTF8(path);
				forHmr = Analysis.forHmr(json);
			}
			
			if(forHmr.getFind().equals("error")) {
				map.put("reason", "工号不存在！");
				return map;
			}
			
			// 注册
			hmrService.save(hmrCustom);
			map.put("register", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 客户端宿管重置密码
	 * @param phone
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/updatePwd", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> updatePwd(String phone, String newPwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("update", "error");
		
		try {
			hmrService.updatePwdByPhone(phone, newPwd);
			map.put("update", "success");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
		}
		
		return map;
	}
	
	/**
	 * 批量删除宿管
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delAllHmrs", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAllHmrs(HttpServletRequest request, int[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 循环删除
			for (int id : ids) {
				// 通过订单ID删除该订单
				hmrService.deleteHmr(request, id);
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
}
