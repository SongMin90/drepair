package com.drepair.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.drepair.po.SqlCustom;
import com.drepair.po.webset.Setting;
import com.drepair.service.SqlService;
import com.drepair.utils.DateTime;
import com.drepair.utils.FileHelper;
import com.drepair.utils.MySQLDatabase;

/**
 * 网站设置Cotroller
 * @author SongM
 * @date 2017年8月30日 上午8:29:17
 */
@Controller
@RequestMapping("webset")
public class WebsetCotroller {
	
	/**
	 * 报修客户端版本
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getBaoxiuVersion", method={RequestMethod.GET})
	public @ResponseBody double getBaoxiuVersion(HttpServletRequest request) {
		return Double.parseDouble(read(request).getVersion_baoxiu());
	}
	
	/**
	 * 抢修修客户端版本
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getQiangxiuVersion", method={RequestMethod.GET})
	public @ResponseBody double getQiangxiuVersion(HttpServletRequest request) {
		return Double.parseDouble(read(request).getVersion_qiangxiu());
	}
	
	/**
	 * 报修客户端版本
	 * @param request
	 * @return
	 */
	public static String version_baoxiu(HttpServletRequest request) {
		return read(request).getVersion_baoxiu();
	}
	
	/**
	 * 报修客户端更新信息
	 * @param request
	 * @return
	 */
	public static String updateInfo_baoxiu(HttpServletRequest request) {
		return read(request).getUpdateInfo_baoxiu();
	}
	
	/**
	 * 抢修客户端版本
	 * @param request
	 * @return
	 */
	public static String version_qiangxiu(HttpServletRequest request) {
		return read(request).getVersion_qiangxiu();
	}
	
	/**
	 * 抢修客户端更新信息
	 * @param request
	 * @return
	 */
	public static String updateInfo_qiangxiu(HttpServletRequest request) {
		return read(request).getUpdateInfo_qiangxiu();
	}
	
	/**
	 * 后台管理导航栏颜色
	 * @param request
	 * @return
	 */
	public static String topbarColor(HttpServletRequest request) {
		return read(request).getTopbarColor();
	}
	
	/**
	 * 网站名称
	 * @param request
	 * @return
	 */
	public static String webName(HttpServletRequest request) {
		return read(request).getWebName();
	}
	
	/**
	 * 图片存放路径
	 * @param request
	 * @return
	 */
	public static String imgPath(HttpServletRequest request) {
		return read(request).getImgPath();
	}
	
	@Autowired
	private SqlService sqlService;
	
	/**
	 * 跳转至设置界面
	 * @param model
	 * @return
	 */
	@RequestMapping("setting")
	public String setting(HttpServletRequest request, Model model, Integer nowPage, Integer size) {
		// 将配置文件的内容发送到页面
		Setting setting = read(request);
		model.addAttribute("version_baoxiu", setting.getVersion_baoxiu());
		model.addAttribute("version_qiangxiu", setting.getVersion_qiangxiu());
		model.addAttribute("updateInfo_web", setting.getUpdateInfo_web());
		model.addAttribute("web_path", setting.getWeb_path());
		model.addAttribute("apkPath", setting.getApkPath());
		model.addAttribute("cookieSaveTime", setting.getCookieSaveTime());
		model.addAttribute("webName", setting.getWebName());
		model.addAttribute("sqlBackupPath", setting.getSqlBackupPath());
		model.addAttribute("sqlBackupTime", setting.getSqlBackupTime());
		model.addAttribute("isStart_sqlBackup", setting.getIsStart_sqlBackup());
		model.addAttribute("imgPath", setting.getImgPath());
		model.addAttribute("topbarColor", setting.getTopbarColor());
		model.addAttribute("apiState", setting.getApiState());
		// 取到数据库的所有备份文件
		// 如果当前页为null，默认设为1
		if(nowPage == null) {
			nowPage = 1;
		}
		// 如果每页显示大小为null，默认设为10
		if(size == null) {
			size = 10;
		}
		try {
			Integer allCount = sqlService.findAllCount(); // 取到所有记录数
			// 计算出总页数
			Integer pageCount = allCount / size;
			if((allCount % size) != 0) {
				pageCount++;
			}
			// 分页根据ID降序查询所有sql记录
			List<SqlCustom> sqlList = sqlService.findAll(nowPage * size - size, size);
			
			model.addAttribute("sqlList", sqlList);// 将查到的所有sql发送到页面
			model.addAttribute("pageCount", pageCount); // 将总页数发送到页面
			model.addAttribute("nowPage", nowPage); // 将当前页发送到页面
			model.addAttribute("size", size); // 将每页显示大小发送到页面
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "../WEB-INF/jsp/webset";
	}
	
	/**
	 * 报修客户端更新版本
	 * @param attr
	 * @param file_baoxiu
	 * @param updateVersion_baoxiu
	 * @param updateInfo_baoxiu
	 * @return
	 */
	@RequestMapping(value="updateBaoxiu", method={RequestMethod.POST})
	public String updateBaoxiu(HttpServletRequest request, RedirectAttributes attr, MultipartFile file_baoxiu, String updateVersion_baoxiu, String updateInfo_baoxiu) {
		Setting setting = read(request);
		// 判断文件夹是否存在，不存在就创建
		if(!(new File(setting.getApkPath())).exists()) {
			(new File(setting.getApkPath())).mkdir();
		}
		// 更新文件
		try {
			setting.setVersion_baoxiu(updateVersion_baoxiu); // 更新版本号
			setting.setUpdateInfo_baoxiu(updateInfo_baoxiu); // 更新内容设置
			save(setting, request); // 储存
			file_baoxiu.transferTo(new File(setting.getApkPath() + "Baoxiu.apk")); // 复制文件
			attr.addFlashAttribute("message", "提交更新成功！");
		} catch (IOException e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return "redirect:setting";
	}
	
	/**
	 * 抢修客户端更新版本
	 * @param attr
	 * @param file_qiangxiu
	 * @param updateVersion_qiangxiu
	 * @param updateInfo_qiangxiu
	 * @return
	 */
	@RequestMapping(value="updateQiangxiu", method={RequestMethod.POST})
	public String updateQiangxiu(HttpServletRequest request, RedirectAttributes attr, MultipartFile file_qiangxiu, String updateVersion_qiangxiu, String updateInfo_qiangxiu) {
		Setting setting = read(request);
		// 判断文件夹是否存在，不存在就创建
		if(!(new File(setting.getApkPath())).exists()) {
			(new File(setting.getApkPath())).mkdir();
		}
		// 更新文件
		try {
			setting.setVersion_qiangxiu(updateVersion_qiangxiu); // 更新版本号
			setting.setUpdateInfo_qiangxiu(updateInfo_qiangxiu); // 更新内容设置
			save(setting, request); // 储存
			file_qiangxiu.transferTo(new File(setting.getApkPath() + "Qiangxiu.apk")); // 复制文件
			attr.addFlashAttribute("message", "提交更新成功！");
		} catch (IOException e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		
		return "redirect:setting";
	}
	
	/**
	 * 更新网站
	 * @param attr
	 * @param file_web
	 * @param updateInfo_web
	 * @return
	 */
	@RequestMapping(value="updateWeb", method={RequestMethod.POST})
	public String updateWeb(HttpServletRequest request, RedirectAttributes attr, MultipartFile file_web, String updateInfo_web) {
		Setting setting = read(request);
		try {
			setting.setUpdateInfo_web(updateInfo_web); // 设置更新内容
			save(setting, request); // 储存
			file_web.transferTo(new File(setting.getWeb_path())); // 复制文件
			attr.addFlashAttribute("message", "提交更新成功！");
		} catch (Exception e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		return "redirect:setting";
	}
	
	/**
	 * 设置网站信息
	 * @param attr
	 * @param webName
	 * @param cookieSaveTime
	 * @param web_path
	 * @return
	 */
	@RequestMapping(value="setWebInfo", method={RequestMethod.POST})
	public String setWebInfo(HttpServletRequest request, RedirectAttributes attr, String webName, int cookieSaveTime, String web_path, String topbarColor) {
		try {	
			// 设置网站信息
			Setting setting = read(request);
			setting.setTopbarColor(topbarColor);
			setting.setWebName(webName);
			setting.setCookieSaveTime(cookieSaveTime);
			setting.setWeb_path(web_path);
			// 储存网站信息
			save(setting, request);
			attr.addFlashAttribute("message", "保存成功！");
		} catch (Exception e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		return "redirect:setting";
	}
	
	/**
	 * 数据库定时备份
	 * @param attr
	 * @param sqlBackupPath
	 * @param sqlBackupTime
	 * @return
	 */
	@RequestMapping(value="sqlBackup", method={RequestMethod.POST})
	public String sqlBackup(HttpServletRequest request, RedirectAttributes attr, final String sqlBackupPath, final int sqlBackupTime) {
		Setting setting = read(request);
		try {
			setting.setSqlBackupPath(sqlBackupPath); // 设置备份地址
			setting.setSqlBackupTime(sqlBackupTime); // 设置备份定时
			
			// 判断文件夹是否存在，不存在就创建
			if(!(new File(sqlBackupPath)).exists()) {
				(new File(sqlBackupPath)).mkdir();
			}
			
			// 判断是启动还是停止
			if(setting.getIsStart_sqlBackup().equals("off")) {
				// 开启定时备份
				setting.setIsStart_sqlBackup("on");
				save(setting, request); // 储存
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(setting.getIsStart_sqlBackup().equals("on")) {
							try {
								Thread.sleep(sqlBackupTime * 1000);
								String saveName = DateTime.getDate() + "_" + DateTime.getTime() + ".sql";
								// 物理位置储存
								new MySQLDatabase().exportDatabaseTool("127.0.0.1", "root", "sa", sqlBackupPath, saveName, "drepair");
								// 数据库添加一条记录
								try {
									SqlCustom sqlCustom = new SqlCustom();
									sqlCustom.setSqlPath(saveName);
									sqlService.save(sqlCustom);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
				attr.addFlashAttribute("message", "启动成功！");
			} else {
				// 关闭定时备份
				setting.setIsStart_sqlBackup("off");
				save(setting, request); // 储存
				attr.addFlashAttribute("message", "停止成功！");
			}
		} catch (Exception e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		return "redirect:setting";
	}
	
	/**
	 * api设置
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="api", method={RequestMethod.POST})
	public String api(HttpServletRequest request, RedirectAttributes attr) {
		try {
			Setting setting = read(request);
			String apiState = setting.getApiState();
			if(apiState.equals("on")) {
				setting.setApiState("off");
				attr.addFlashAttribute("message", "停用成功！");
			} else {
				setting.setApiState("on");
				attr.addFlashAttribute("message", "启用成功！");
			}
			save(setting, request);
		} catch (Exception e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		return "redirect:setting";
	}
	
	/**
	 * 储存网站信息
	 * @param setting
	 * @param request
	 */
	private void save(Setting setting, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/WEB-INF/") + "/webset.json";
		Object json = JSON.toJSON(setting);
		FileHelper.writeUTF8(path, json.toString());
	}
	
	/**
	 * 读取网站信息
	 * @param request
	 * @return
	 */
	public static Setting read(HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/WEB-INF/") + "/webset.json";
		String json = FileHelper.readUTF8(path);
		return JSON.parseObject(json, Setting.class);
	}
	
}
