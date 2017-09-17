package com.drepair.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.api.json.Analysis;
import com.drepair.api.net.HttpUtils;
import com.drepair.api.po.Hmr;
import com.drepair.api.po.Stu;
import com.drepair.exception.CustomException;
import com.drepair.po.EvalCustom;
import com.drepair.po.EvalCustom1;
import com.drepair.po.EvalCustom2;
import com.drepair.po.HmrCustom;
import com.drepair.po.ImgCustom;
import com.drepair.po.OrderCustom;
import com.drepair.po.RepairerCustom;
import com.drepair.po.StuCustom;
import com.drepair.service.EvalService;
import com.drepair.service.HmrService;
import com.drepair.service.ImgService;
import com.drepair.service.OrderService;
import com.drepair.service.RepairerService;
import com.drepair.service.StuService;
import com.drepair.utils.CookieHelper;

/**
 * OrderController
 * @author SongM
 * @date 2017年7月6日 上午11:48:57
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ImgService imgService;
	
	@Autowired
	private EvalService evalService;
	
	@Autowired
	private HmrService hmrService;
	
	/**
	 * 添加报修订单
	 * @param orderCustom
	 * @return
	 */
	@RequestMapping(value="/addOrder", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> addOrder(OrderCustom orderCustom) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("add", "error");
		
		orderCustom.setOrderStartTime(new Date());
		orderCustom.setOrderState(1); // 设置订单创建初始状态（审核中）
		orderCustom.setOrderSort(0); // 设置订单创建初始等级（未评定）
		
		// 添加订单记录
		try {
			Integer orderId = orderService.save(orderCustom);
			map.put("orderId", orderId+"");
			map.put("add", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 查询所有审核通过的报修订单
	 * @param nowPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/findOrderByPass", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> findOrderByPass(Integer nowPage, Integer size) {
		nowPage = 1;
		size = 1000; // 目前先写死每次显示大小
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("find", "error");
		List<OrderCustom> orderList = null;
		try {
			// 分页查询所有审核通过的报修订单（根据订单级别降序）
			orderList = orderService.findOrderByPass(nowPage * size - size, size);
			
			// 取出所有订单的第一种图片
			for(int i=0; i<orderList.size(); i++) {
				Integer orderId = orderList.get(i).getOrderId();
				List<ImgCustom> list = imgService.findByOrderId(orderId);
				if(list.size() > 0) {
					String imgUrl = list.get(0).getImgUrl();
					orderList.get(i).setListImgUrl(imgUrl);
				}
			}
			
			// 将所有订单信息发送
			map.put("orderList", orderList);
			map.put("find", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据订单状态分页查询全部报修订单
	 * @param model
	 * @param nowPage
	 * @param size
	 * @param isStu
	 * @param orderState
	 * @return
	 */
	@RequestMapping(value="/allOrder", method={RequestMethod.GET})
	public String findAllOrder(Model model, Integer nowPage, Integer size, Integer isStu, Integer orderState, String searchContent) {
		Integer allCount = null;
		List<OrderCustom> orderList = null;
		if(size == null) { // 默认将每页显示大小设为10条
			size = 10;
		}
		if(orderState == null) { // 默认设置订单状态为1，即正在审核
			orderState = 1;
		}
		try {
			allCount = orderService.findAllOrderCount(isStu, orderState); // 取到总报修订单记录数
			Integer pageCount =  allCount / size; // 计算出总页数
			if((allCount % size) != 0) {
				pageCount++;
			}
			if(nowPage == null) { // 解决当前页为空的情况，为null就设为1
				nowPage = 1;
			}
			orderList = orderService.findAllOrder(isStu, orderState, nowPage * size - size, size); // 分页查询
			model.addAttribute("pageCount", pageCount); // 将总页数发送到页面
			model.addAttribute("nowPage", nowPage); // 将当前页发送到页面
			model.addAttribute("orderList", orderList); // 将查询到的订单发送到页面
			model.addAttribute("size", size); // 将每页显示大小发送到页面
			model.addAttribute("orderState", orderState); // 将当前订单状态发送到页面
			model.addAttribute("isStu", isStu); // 将是否为学生订单的状态发送到页面
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "../WEB-INF/jsp/allOrders";
	}
	
	/**
	 * 报修订单审核通过
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/passOrder", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> passOrder(Integer orderId, Integer orderSort) {
		Map<String,String> map = new HashMap<String, String>();
		try {
			orderService.passOrder(orderId, null, orderSort);
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 报修订单审核不通过
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/failOrder", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> failOrder(HttpServletRequest request, HttpServletResponse response, Integer orderId, String evalContent) {
		Map<String,String> map = new HashMap<String, String>();
		Integer adminId = null;
		try {
			adminId = Integer.parseInt(CookieHelper.isGet(request, response, "adminId"));
			orderService.failOrder(orderId, null);
			// 添加管理员评价
			EvalCustom evalCustom = new EvalCustom();
			evalCustom.setAdminId(adminId);
			evalCustom.setEvalContent(evalContent);
			evalCustom.setOrderId(orderId);
			evalService.save(evalCustom);
			
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * findById
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/findById", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> findById(Integer orderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("find", "error");
		
		OrderCustom orderCustom = null;
		try {
			orderCustom = orderService.findOrderById(orderId);
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.getStackTrace();
		}
		
		if(orderCustom != null) {
			map.put("find", "success");
			map.put("order", orderCustom);
		} else {
			map.put("reason", "没找到该报修订单！");
		}
		
		return map;
	}
	
	/**
	 * 维修员抢单
	 * @param repairerId
	 * @return
	 */
	@RequestMapping(value="/repairerGetOrder", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> repairerGetOrder(Integer repairerId, Integer orderId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("get", "error");
		
		try {
			orderService.repairerGetOrder(repairerId, orderId);
			map.put("get", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 报修端获取已经提交的全部订单
	 * @param orderState 订单状态
	 * @param stuId 学生ID
	 * @return
	 */
	@RequestMapping(value="/getCommittedOrders", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> getCommittedOrders(OrderCustom orderCustom) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("find", "error");
		
		try {
			List<OrderCustom> orderList = orderService.getCommittedOrders(orderCustom);
			
			for(int i=0; i<orderList.size(); i++) {
				Integer orderId = orderList.get(i).getOrderId();
				List<ImgCustom> list = imgService.findByOrderId(orderId);
				if(list.size() > 0) {
					String imgUrl = list.get(0).getImgUrl();
					orderList.get(i).setListImgUrl(imgUrl);
				}
			}
			
			map.put("orderList", orderList);
			map.put("find", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 抢单端获取自己的全部订单
	 * @param orderState 订单状态
	 * @param repairerId 维修员ID
	 * @return
	 */
	@RequestMapping(value="/getRepairOrders", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> getRepairOrders(Integer orderState, Integer repairerId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("find", "error");
		
		try {
			List<OrderCustom> orderList = orderService.getRepairOrders(orderState, repairerId);
			
			for(int i=0; i<orderList.size(); i++) {
				Integer orderId = orderList.get(i).getOrderId();
				List<ImgCustom> list = imgService.findByOrderId(orderId);
				if(list.size() > 0) {
					String imgUrl = list.get(0).getImgUrl();
					orderList.get(i).setListImgUrl(imgUrl);
				}
			}
			
			map.put("orderList", orderList);
			map.put("find", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * 跳转到订单信息修改页面
	 * @param model
	 * @param orderId
	 * @return
	 */
	@Autowired StuService stuService;
	@Autowired RepairerService repairerService;
	@RequestMapping(value="/editOrder", method={RequestMethod.GET})
	public String editOrder(Model model, Integer orderId) {
		OrderCustom order = null;
		try {
			// 取出订单信息
			order = orderService.findOrderById(orderId);
			
			// 取出订单创建人信息
			if(order.getStuId() != null) {
				StuCustom stuCustom = stuService.findById(order.getStuId());
				String json = HttpUtils.getJSON(HttpUtils.STU_URL, order.getStuId());
				Stu forStu = Analysis.forStu(json);
				if(forStu.getFind().equals("success")) {
					model.addAttribute("name", forStu.getStudent().getStudentName());
				}
				model.addAttribute("id", stuCustom.getStuId());
				model.addAttribute("phone", stuCustom.getStuPhone());
			} else {
				// TODO 取出宿管信息，并发送到jsp
				HmrCustom hmr = hmrService.findById(order.getHmrId());
				String json = HttpUtils.getJSON(HttpUtils.HMR_URL, order.getHmrId()+"");
				Hmr forHmr = Analysis.forHmr(json);
				if(forHmr.getFind().equals("success")) {
					model.addAttribute("name", forHmr.getManager().getManagerName());
				}
				model.addAttribute("id", hmr.getHmrId());
				model.addAttribute("phone", hmr.getHmrPhone());
			}
			
			// 取出维修员的信息
			if(order.getRepairerId() != null) {
				RepairerCustom repairerCustom = repairerService.findById(order.getRepairerId());
				// 将维修员信息发送到页面
				model.addAttribute("repairerId", repairerCustom.getRepairerId());
				model.addAttribute("repairerName", repairerCustom.getRepairerName());
				model.addAttribute("repairerPhone", repairerCustom.getRepairerPhone());
			}
			
			// 取出订单的图片
			List<ImgCustom> imglist = imgService.findByOrderId(orderId);
			// 将图片信息发送到页面
			model.addAttribute("imglist", imglist);
			
			// 取出评价信息
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
			
			// 将所有评价发送到页面
			model.addAttribute("evalsList", evalsList);
		} catch (Exception e) {
			new CustomException("订单查询失败！");
			e.printStackTrace();
		}
		// 将订单信息发送到页面
		model.addAttribute("order", order);
		return "../WEB-INF/jsp/editOrder";
	}
	
	/**
	 * 更新报修订单信息
	 * @param orderCustom
	 * @return
	 */
	@RequestMapping(value="/update", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> update(HttpSession httpSession, OrderCustom orderCustom) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", "更新失败！");
		
		try {
			orderService.update(orderCustom);
			map.put("result", "更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 维修员提交已维修的订单
	 * @param orderCustom
	 * @return
	 */
	@RequestMapping(value="orderIsRepair", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> orderIsRepair(OrderCustom orderCustom) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("update", "error");
		
		try {
			orderCustom.setOrderOverTime(new Date());
			orderService.orderIsRepair(orderCustom);
			map.put("update", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 批量删除订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delAllOrders", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delAllOrders(HttpServletRequest request, int[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 循环删除
			for (int id : ids) {
				// 通过订单ID删除该订单
				orderService.delById(request, id);
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
}
