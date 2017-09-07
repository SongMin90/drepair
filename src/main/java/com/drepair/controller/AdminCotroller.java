package com.drepair.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.po.AdminCustom;
import com.drepair.service.AdminService;
import com.drepair.utils.CookieHelper;
import com.drepair.utils.RandomValidateCode;

/**
 * 管理员Cotroller
 * 
 * @author SongM
 * @date 2017年7月26日 下午5:20:10
 */
@Controller
@RequestMapping("/admin")
public class AdminCotroller {

	@Autowired
	private AdminService adminService;
	
	// cookie储存时间(秒)
	@Value("#{configProperties['cookieSaveTime']}")
	private int cookieSaveTime;

	/**
	 * 登录获取验证码
	 * @param response
	 * @param request
	 */
    @RequestMapping("/getImgCode")
    public void getImgCode(HttpServletResponse response,
            HttpServletRequest request) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response, "imagecode");// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 管理员登录
	 * @param response
	 * @param httpSession
	 * @param request
	 * @param idOrPhone
	 * @param password
	 * @param model
	 * @param validateCode
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(HttpServletResponse response, HttpSession httpSession, 
			HttpServletRequest request, String idOrPhone, String password, 
			Model model, String validateCode, String remember) {
		// 根据cookie判断是否已经登录
		String name = CookieHelper.isGet(request, response, "adminId");
		String pwd = CookieHelper.isGet(request, response, "adminPwd");
		if(name != null && pwd != null) {
			// 将管理员ID存入session
			httpSession.setAttribute("adminId", name);
			return "forward:main";
		}
		
		String message = null; // 提示信息
		if (idOrPhone != null || password != null || validateCode != null) {
			model.addAttribute("idOrPhone", idOrPhone); // 将idOrPhone发送到页面
			model.addAttribute("password", password); // 将idOrPhone发送到页面
			// 去除数据两边空格
			idOrPhone = idOrPhone.trim();
			password = password.trim();
			
			// 验证验证码
			String code = null;
	        //1:获取cookie里面的验证码信息
	        Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            if ("imagecode".equals(cookie.getName())) {
	                code = cookie.getValue();
	                break;
	            }
	        }
	        if(code != null && code.equalsIgnoreCase(validateCode)) {
	        	boolean isPhone = false; // 是否为手机号码登录标识
				// 判断是否为手机号
				if (idOrPhone.length() == 11) {
					isPhone = true;
				}
				// 登录信息验证
				AdminCustom adminCustom = null;
				try {
					if (isPhone) {
						adminCustom = adminService.findByPhone(idOrPhone);
					} else {
						adminCustom = adminService.findById(Integer.parseInt(idOrPhone));
					}
					if (adminCustom == null) {
						if (isPhone) {
							message = "没有此手机号！";
						} else {
							message = "没有此工号！";
						}
					} else {
						if (!adminCustom.getAdminPwd().equals(password)) {
							message = "密码错误！";
						} else { // 登录成功
							// 将管理员ID存入session
							httpSession.setAttribute("adminId", adminCustom.getAdminId());
							// 判断是否勾选自动登录
							if(remember != null) {
								// 将登录的Id和密码存入cookie
								CookieHelper.add(cookieSaveTime, response, "adminId", adminCustom.getAdminId()+"");
								CookieHelper.add(cookieSaveTime, response, "adminPwd", adminCustom.getAdminPwd());
							}
							return "forward:main";
						}
					}
				} catch (Exception e) {
					message = "登录出错！";
					e.printStackTrace();
				}
	        } else {
	        	message = "验证码不正确！";
	        }
		}
		model.addAttribute("message", message);
		return "login";
	}
	
	/**
	 * 退出登录
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 清空所有Cookie
		CookieHelper.clearAll(request, response);
		// 清空session
		session.removeAttribute("adminId");
		return "redirect:login";
	}
	
	/**
	 * 跳转到后台管理
	 * @return
	 */
	@RequestMapping("/main")
	public String main() {
		return "../WEB-INF/jsp/main";
	}
	
	/**
	 * 新增管理
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addAdmin")
	public String addAdmin(Model model, AdminCustom admin) {
		try {
			if(admin.getAdminName() != null && admin.getAdminIcard() != null && admin.getAdminPhone() != null
					&& admin.getAdminPwd() != null) {
				adminService.save(admin);
				model.addAttribute("message", "新增成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "新增失败！");
			model.addAttribute("admin", admin); // 添加失败，将添加的信息重新发送到表单
			e.printStackTrace();
		}
		return "../WEB-INF/jsp/addAdmin";
	}
	
	/**
	 * 分页查询全部管理员
	 * @param model
	 * @param nowPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/allAdmin", method={RequestMethod.GET})
	public String allAdmin(Model model, Integer nowPage, Integer size) {
		if(size == null) { // 默认将每页显示大小设为10条
			size = 10;
		}
		if(nowPage == null) { // 解决当前页为空的情况，为null就设为1
			nowPage = 1;
		}
		try {
			// 取到管理员总记录数
			Integer allCount = adminService.findAllCount();
			// 计算出总页数
			int pageCount = allCount / size;
			if(allCount % size != 0) {
				pageCount++;
			}
			List<AdminCustom> adminList = adminService.findAllAdmin(nowPage * size - size, size);
			// 将取到的管理员列表发送到页面
			model.addAttribute("adminList", adminList);
			// 将总页数发送到页面
			model.addAttribute("pageCount", pageCount);
			// 将当前页发送到页面
			model.addAttribute("nowPage", nowPage);
			// 将每页显示大小发送到页面
			model.addAttribute("size", size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/allAdmin";
	}

	/**
	 * 跳转到管理员信息编辑页面
	 * @param model
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="/editAdmin", method={RequestMethod.GET})
	public String editAdmin(Model model, int adminId) {
		try {
			AdminCustom adminCustom = adminService.findById(adminId);
			// 将管理员信息发送到页面
			model.addAttribute("admin", adminCustom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/editAdmin";
	}
	
	/**
	 * 更新管理员信息
	 * @param adminCustom
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateAdmin", method={RequestMethod.POST})
	public String updateAdmin(AdminCustom admin, Model model) {
		try {
			if(admin.getAdminId() != null && admin.getAdminName() != null && admin.getAdminIcard() != null && admin.getAdminPhone() != null
					&& admin.getAdminPwd() != null) {
				adminService.updateById(admin);
				model.addAttribute("message", "更新成功！");
			}
		} catch (Exception e) {
			model.addAttribute("message", "更新失败！");
			e.printStackTrace();
		}
		model.addAttribute("admin", admin); // 将信息重新发送到表单
		return "../WEB-INF/jsp/editAdmin";
	}
	
	/**
	 * 根据Id删除管理员
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="/delAdmin", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAdmin(Integer adminId) {
		Map<String,String> map = new HashMap<String, String>();
		try {
			adminService.deleteById(adminId);
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 管理员根据手机号重置密码
	 * @param phone
	 * @param new_password
	 * @param sms_code
	 * @return
	 */
	@RequestMapping(value="/updatePwd", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> updatePwd(String phone, String new_password, String sms_code) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("update", "error");
		try {
			adminService.updatePwdByPhone(phone, new_password);
			map.put("update", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 批量删除管理员
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delAllAdmins", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAllAdmins(int[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 批量删除
			for (int id : ids) {
				adminService.deleteById(id);
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
}
