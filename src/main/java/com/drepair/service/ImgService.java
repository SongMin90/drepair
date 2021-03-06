package com.drepair.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drepair.po.Img;
import com.drepair.po.ImgCustom;

/**
 * ImgService
 * @author SongM
 * @date 2017年7月17日 下午10:24:00
 */
public interface ImgService {

	/**
	 * 添加图片
	 * @param img
	 * @throws Exception
	 */
	public void save(Img img) throws Exception;

	/**
	 * findByOrderId
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<ImgCustom> findByOrderId(Integer orderId) throws Exception;
	
	/**
	 * 通过订单ID删除图片
	 * @param orderId
	 * @throws Exception
	 */
	public void delById(HttpServletRequest request, Integer orderId) throws Exception;

	/**
	 * 添加用户头像
	 * @param imgCustom
	 * @throws Exception
	 */
	public void saveIcon(ImgCustom imgCustom) throws Exception;

	/**
	 * 查询用户头像url
	 * @param imgCustom
	 * @return
	 * @throws Exception
	 */
	public String findIconUrl(ImgCustom imgCustom) throws Exception;

	/**
	 * 更新用户头像
	 * @param imgCustom
	 * @throws Exception
	 */
	public void updateIcon(ImgCustom imgCustom) throws Exception;
}
