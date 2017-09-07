package com.drepair.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drepair.po.HmrCustom;

/**
 * 宿管Service
 * @author SongM
 * @date 2017年8月9日 下午3:45:05
 */
public interface HmrService {

	/**
	 * 取到宿管总记录数
	 * @return
	 */
	public int findAllCount() throws Exception;

	/**
	 * 分页查询全部宿管
	 * @param i
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<HmrCustom> findAllHmr(int startPosition, Integer size) throws Exception;

	/**
	 * 通过宿管Id删除宿管
	 * @param hmrId
	 * @throws Exception
	 */
	public void deleteHmr(HttpServletRequest request, Integer hmrId) throws Exception;

	/**
	 * 通过ID查询宿管信息
	 * @param hmrId
	 * @return
	 * @throws Exception
	 */
	public HmrCustom findById(Integer hmrId) throws Exception;

	/**
	 * 更新宿管信息
	 * @param hmr
	 * @throws Exception
	 */
	public void updateHmr(HmrCustom hmr) throws Exception;

	/**
	 * 通过手机号查找宿管信息
	 * @param hmrPhone
	 * @return
	 * @throws Exception
	 */
	public HmrCustom findByPhone(String hmrPhone) throws Exception;

	/**
	 * 添加一条宿管信息记录
	 * @param hmrCustom
	 * @throws Exception
	 */
	public void save(HmrCustom hmrCustom) throws Exception;

	/**
	 * 宿管通过手机号更新密码
	 * @param phone
	 * @param newPwd
	 * @throws Exception
	 */
	public void updatePwdByPhone(String phone, String newPwd) throws Exception;

}
