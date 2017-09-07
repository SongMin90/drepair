package com.drepair.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.drepair.utils.CookieHelper;

/**
 * 登录认证拦截器
 * @author SongM
 * @date 2017年6月21日 下午9:06:16
 */
public class LoginInterceptor implements HandlerInterceptor {

	// 进入Handler方法之前执行
	// 用户身份认证、身份授权
	// 比如是否登录验证
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 获取请求的url
		String url = request.getRequestURI();
		
		// 判断url是否是公开地址（实际使用时将公开地址配置在配置文件中）
		// 这里公开地址是登录提交的地址
		if(url.indexOf("login") >= 0 || url.indexOf("getImgCode") >= 0) {
			return true;
		}
		
		// 判断session
		HttpSession session = request.getSession();
		// 从session中取出用户身份信息
		Object adminId = session.getAttribute("adminId");
		// 判断是否存在
		if(adminId != null) {
			return true;
		}
		
		// 判断cookie
		String name = CookieHelper.isGet(request, response, "adminId");
		String pwd = CookieHelper.isGet(request, response, "adminPwd");
		if(name != null && pwd != null) {
			// 将管理员ID存入session
			session.setAttribute("adminId", name);
			return true;
		}
		
		// 执行到这里表示身份需要认证，跳转到登录页面
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		
		// false表示拦截，true表示通过放行
		return false;
	}

	// 进入Handler方法之后，返回modelAndView之前执行
	// 应用场景从modelAndView出发：将公用的模型数据（比如菜单导航）放这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	// 执行Handler完成执行此方法
	// 应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
