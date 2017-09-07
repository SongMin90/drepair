package com.drepair.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drepair.po.SqlCustom;
import com.drepair.service.SqlService;
import com.drepair.utils.MySQLDatabase;

/**
 * sql语句的Controller
 * @author SongM
 * @date 2017年9月3日 上午10:22:00
 */
@Controller
@RequestMapping("sql")
public class SqlController {

	@Autowired
	private SqlService sqlService;
	
	// 数据库备份地址
	@Value("#{configProperties['sqlBackupPath']}")
	private String sqlBackupPath;
	
	/**
	 * 更新sql
	 * @param sqlId
	 * @return
	 */
	@RequestMapping(value="/update", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> update(Integer sqlId) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			// 先通过ID查询sql语句的path
			SqlCustom sqlCustom = sqlService.findById(sqlId);
			
			// 执行sql语句
			String sqlPath = sqlCustom.getSqlPath();
			new MySQLDatabase().dataBaseImport("127.0.0.1", "drepair", "root", "sa", sqlBackupPath + sqlPath);
			
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 批量删除sql
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delSqls", method={RequestMethod.POST})
	public @ResponseBody Map<String, String> delSqls(Integer[] ids) {
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			for (Integer id : ids) {
				// 先通过ID查询sql语句的path
				SqlCustom sqlCustom = sqlService.findById(id);
				String sqlPath = sqlCustom.getSqlPath();
				
				// 数据库删除记录
				sqlService.deleteById(id);
				
				// 物理地址删除
				FileUtils.deleteQuietly(new File(sqlBackupPath + sqlPath));
			}
			map.put("result", "成功！");
		} catch (Exception e) {
			map.put("result", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return map;
	}
	
}
