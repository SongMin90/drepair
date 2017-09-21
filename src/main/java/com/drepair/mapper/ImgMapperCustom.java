package com.drepair.mapper;

import java.util.List;

import com.drepair.po.ImgCustom;

public interface ImgMapperCustom {

	/**
	 * findByOrderId
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<ImgCustom> findByOrderId(Integer orderId) throws Exception;

	/**
	 * 通过图片ID删除
	 * @param imgId
	 * @throws Exception
	 */
	public void delById(Integer imgId) throws Exception;

	/**
	 * 添加用户头像
	 * @param imgCustom
	 * @throws Exception
	 */
	public void saveIcon(ImgCustom imgCustom) throws Exception;
	
	/**
	 * 更新用户头像
	 * @param imgCustom
	 * @throws Exception
	 */
	public void updateIcon(ImgCustom imgCustom) throws Exception;
	
	/**
	 * 查询头像url
	 * @param imgCustom
	 * @return
	 * @throws Exception
	 */
	public String findIconUrl(ImgCustom imgCustom) throws Exception;
	
}
