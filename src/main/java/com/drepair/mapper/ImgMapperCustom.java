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
	
}
