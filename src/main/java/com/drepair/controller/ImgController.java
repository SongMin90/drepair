package com.drepair.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drepair.po.Img;
import com.drepair.po.ImgCustom;
import com.drepair.service.ImgService;
import com.drepair.utils.DateTime;

/**
 * ImgController
 * 
 * @author SongM
 * @date 2017年7月17日 下午5:00:11
 */
@Controller
@RequestMapping("/img")
public class ImgController {

	@Autowired
	private ImgService imgService;

	/**
	 * 添加图片
	 * 
	 * @param orderId
	 *            图片所属报修订单的ID
	 * @param request
	 *            HttpServletRequest
	 * @param orderImg
	 *            图片
	 * @return
	 */
	@RequestMapping(value = "/addImg", method = { RequestMethod.POST })
	public @ResponseBody Map<String, String> addImg(Integer orderId, HttpServletRequest request,
			@RequestParam("orderImg") MultipartFile[] orderImg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("add", "error");

		// 文件上传（报修订单附加图片）
		try {
			if (orderImg != null && orderImg.length > 0 && orderId != null) { // 验证图片上传的数据
				for (int i = 0; i < orderImg.length; i++) { // for循环多张图片
					if (orderImg[i].getSize() > 0) { // 判断当前循环的图片是否存在
						// 物理图片存储
						// String path =
						// request.getServletContext().getRealPath("/"); //
						// 获取项目相对路径
						String filePath = WebsetCotroller.imgPath(request) + DateTime.getDate();
						File file = new File(filePath);
						if (!file.exists()) {
							file.mkdir();
						}
						filePath = filePath + "/" + DateTime.getTime();
						file = new File(filePath);
						if (!file.exists()) {
							file.mkdir();
						}
						filePath = filePath + "/" + orderImg[i].getOriginalFilename();
						file = new File(filePath);
						orderImg[i].transferTo(file);

						// 数据库保存
						Img img = new Img();
						img.setImgUrl("imgs" + filePath.split("imgs")[1]);
						img.setImgDatetime(new Date());
						img.setOrderId(orderId);
						imgService.save(img);
					}
				}
				map.put("add", "success");
			} else {
				map.put("reason", "图片上传的数据有误！");
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 通过报修订单查询图片
	 */
	@RequestMapping(value = "/findByOrderId", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> findByOrderId(Integer orderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("find", "error");

		List<ImgCustom> imgList = null;

		try {
			imgList = imgService.findByOrderId(orderId);
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}

		if (imgList != null && imgList.size() > 0) {
			map.put("find", "success");
			map.put("imgList", imgList);
		}

		return map;
	}

	/**
	 * 用户设置头像
	 * 
	 * @param userId
	 * @param profession
	 * @param request
	 * @param icon
	 * @return
	 */
	@RequestMapping(value = "/updateUserIcon", method = { RequestMethod.POST })
	public @ResponseBody Map<String, String> updateUserIcon(String userId, String profession,
			HttpServletRequest request, @RequestParam("icon") MultipartFile[] icon) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("set", "error");

		ImgCustom imgCustom = new ImgCustom();
		imgCustom.setUserId(userId);
		imgCustom.setProfession(profession);

		String iconUrl = null;
		try {
			iconUrl = imgService.findIconUrl(imgCustom);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 文件上传
		try {
			if (icon != null && icon.length > 0 && userId != null) { // 验证图片上传的数据
				for (int i = 0; i < icon.length; i++) { // for循环多张图片
					if (icon[i].getSize() > 0) { // 判断当前循环的图片是否存在
						// 物理图片存储
						// String path =
						// request.getServletContext().getRealPath("/"); //
						// 获取项目相对路径
						String filePath = WebsetCotroller.imgPath(request) + DateTime.getDate();
						File file = new File(filePath);
						if (!file.exists()) {
							file.mkdir();
						}
						filePath = filePath + "/" + DateTime.getTime();
						file = new File(filePath);
						if (!file.exists()) {
							file.mkdir();
						}
						filePath = filePath + "/" + icon[i].getOriginalFilename();
						file = new File(filePath);
						icon[i].transferTo(file);

						// 数据库保存
						
						imgCustom.setImgUrl("imgs" + filePath.split("imgs")[1]);
						imgCustom.setImgDatetime(new Date());
						if(iconUrl == null) { // 为空就新增
							imgService.saveIcon(imgCustom);
						} else { // 有就更新
							imgService.updateIcon(imgCustom);
						}
					}
				}
				map.put("set", "success");
			} else {
				map.put("reason", "图片上传的数据有误！");
			}
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 查询用户头像的URL
	 * @param userId
	 * @param profession
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findIconUrl", method = { RequestMethod.GET })
	public @ResponseBody Map<String, String> findIconUrl(String userId, String profession, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("find", "error");
		
		try {
			ImgCustom imgCustom = new ImgCustom();
			imgCustom.setUserId(userId);
			imgCustom.setProfession(profession);
			String iconUrl = null;
			try {
				iconUrl = imgService.findIconUrl(imgCustom);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(iconUrl == null) {
				iconUrl = "icon/icon.jpeg";
			}
			map.put("iconUrl", iconUrl);
			map.put("find", "success");
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}

}