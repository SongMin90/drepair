package com.drepair.service.imp;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.ImgMapper;
import com.drepair.mapper.ImgMapperCustom;
import com.drepair.po.Img;
import com.drepair.po.ImgCustom;
import com.drepair.service.ImgService;

public class ImgServiceImpl implements ImgService {

	@Autowired
	private ImgMapper imgMapper;
	
	@Autowired
	private ImgMapperCustom imgMapperCustom;
	
	@Override
	public void save(Img img) throws Exception {
		imgMapper.insert(img);
	}

	@Override
	public List<ImgCustom> findByOrderId(Integer orderId) throws Exception {
		return imgMapperCustom.findByOrderId(orderId);
	}

	@Override
	public void delById(HttpServletRequest request, Integer orderId) throws Exception {
		// 1、先findById取出imgId物理地址
		List<ImgCustom> imgList = imgMapperCustom.findByOrderId(orderId);
		
		for (ImgCustom imgCustom : imgList) {
			// 2、再删除记录
			imgMapperCustom.delById(imgCustom.getImgId());
			
			// 3、再删除物理地址的图片
			ResourceBundle resource = ResourceBundle.getBundle("webset"); // 读取properties文件
			String imgPath = resource.getString("imgPath");
			String imgPaths = imgPath.split("imgs")[0] + imgCustom.getImgUrl();
			FileUtils.deleteQuietly(new File(imgPaths));
		}
		
	}

	@Override
	public void saveIcon(ImgCustom imgCustom) throws Exception {
		imgMapperCustom.saveIcon(imgCustom);
	}

	@Override
	public String findIconUrl(ImgCustom imgCustom) throws Exception {
		return imgMapperCustom.findIconUrl(imgCustom);
	}

	@Override
	public void updateIcon(ImgCustom imgCustom) throws Exception {
		imgMapperCustom.updateIcon(imgCustom);
	}

}
