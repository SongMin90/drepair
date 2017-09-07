package com.drepair.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.drepair.mapper.SqlMapperCustom;
import com.drepair.po.SqlCustom;
import com.drepair.service.SqlService;

public class SqlServiceImpl implements SqlService {

	@Autowired
	private SqlMapperCustom sqlMapperCustom;
	
	@Override
	public void save(SqlCustom sqlCustom) throws Exception {
		sqlMapperCustom.save(sqlCustom);
	}

	@Override
	public void deleteById(Integer sqlId) throws Exception {
		sqlMapperCustom.deleteById(sqlId);
	}

	@Override
	public SqlCustom findById(Integer sqlId) throws Exception {
		return sqlMapperCustom.findById(sqlId);
	}

	@Override
	public List<SqlCustom> findAll(Integer startPosition, Integer size) throws Exception {
		return sqlMapperCustom.findAll(startPosition, size);
	}

	@Override
	public Integer findAllCount() throws Exception {
		return sqlMapperCustom.findAllCount();
	}

}
