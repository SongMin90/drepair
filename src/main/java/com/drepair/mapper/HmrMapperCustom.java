package com.drepair.mapper;

import java.util.List;

import com.drepair.po.HmrCustom;

/**
 * 宿管自定义Mapper
 * @author SongM
 * @date 2017年8月9日 下午3:42:42
 */
public interface HmrMapperCustom {

	/**
	 * 取到宿管总记录数
	 * @return
	 * @throws Exception
	 */
	public int findAllCount() throws Exception;

	/**
	 * 分页查询全部宿管
	 * @param startPosition
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<HmrCustom> findAllHmr(Integer startPosition, Integer size) throws Exception;

	/**
	 * 通过宿管Id删除宿管
	 * @param hmrId
	 * @throws Exception
	 */
	public void deleteHmr(Integer hmrId) throws Exception;

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
	 * 通过手机号查找
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
