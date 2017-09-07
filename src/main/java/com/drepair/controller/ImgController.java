package com.drepair.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	// 报修订单图片存放地址
	@Value("#{configProperties['imgPath']}")
	private String imgPath;
	
	/**
	 * 添加图片
	 * @param orderId 图片所属报修订单的ID
	 * @param request HttpServletRequest
	 * @param orderImg 图片
	 * @return
	 */
	@RequestMapping(value="/addImg", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> addImg(Integer orderId, HttpServletRequest request, @RequestParam("orderImg") MultipartFile[] orderImg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("add", "error");

		// 文件上传（报修订单附加图片）
		try {
			if (orderImg != null && orderImg.length > 0 && orderId != null) { // 验证图片上传的数据
				for (int i = 0; i < orderImg.length; i++) { // for循环多张图片
					if(orderImg[i].getSize() > 0) { // 判断当前循环的图片是否存在
						// 物理图片存储
						//String path = request.getServletContext().getRealPath("/"); // 获取项目相对路径
						String filePath = imgPath + DateTime.getDate();
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
	@RequestMapping(value="/findByOrderId", method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> findByOrderId(Integer orderId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("find", "error");
		
		List<ImgCustom> imgList = null;
		
		try {
			imgList = imgService.findByOrderId(orderId);
		} catch (Exception e) {
			map.put("reason", "服务器出错啦！");
			e.printStackTrace();
		}
		
		if(imgList != null && imgList.size() > 0) {
			map.put("find", "success");
			map.put("imgList", imgList);
		}
		
		return map;
	}

}