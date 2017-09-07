package com.drepair.mapper;

import java.util.List;

import com.drepair.po.SqlCustom;

/**
 * 自定义sql的Mapper
 * @author SongM
 * @date 2017年9月3日 上午9:31:44
 */
public interface SqlMapperCustom {

	/**
	 * 保存一条记录
	 * @param sqlCustom
	 * @throws Exception
	 */
	public void save(SqlCustom sqlCustom) throws Exception;
	
	/**
	 * 根据ID删除一条记录
	 * @param sqlId
	 * @throws Exception
	 */
	public void deleteById(Integer sqlId) throws Exception;
	
	/**
	 * 根据ID查询记录
	 * @param sqlId
	 * @return
	 * @throws Exception
	 */
	public SqlCustom findById(Integer sqlId) throws Exception;
	
	/**
	 * 分页查询全部记录，根据ID递减查询
	 * @param startPosition
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<SqlCustom> findAll(Integer startPosition, Integer size) throws Exception;

	/**
	 * 取到所有记录数
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCount() throws Exception;
	
}
