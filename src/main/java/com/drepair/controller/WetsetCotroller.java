package com.drepair.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.drepair.po.SqlCustom;
import com.drepair.service.SqlService;
import com.drepair.utils.DateTime;
import com.drepair.utils.MySQLDatabase;

/**
 * 网站设置Cotroller
 * @author SongM
 * @date 2017年8月30日 上午8:29:17
 */
@Controller
@RequestMapping("webset")
public class WetsetCotroller {
	
	// 报修客户端当前版本号
	@Value("#{configProperties['version_baoxiu']}")
	public static String version_baoxiu = "1";
	
	// 报修客户端更新内容
	@Value("#{configProperties['updateInfo_baoxiu']}")
	public static String updateInfo_baoxiu = "测试阶段。";
	
	// 客户端保存地址
	@Value("#{configProperties['apkPath']}")
	private String apkPath;
	
	// 抢修客户端当前版本号
	@Value("#{configProperties['version_qiangxiu']}")
	public static String version_qiangxiu = "1";
	
	// 抢修客户端更新内容
	@Value("#{configProperties['updateInfo_qiangxiu']}")
	public static String updateInfo_qiangxiu = "测试阶段。";
	
	// 网站更新内容
	@Value("#{configProperties['updateInfo_web']}")
	private String updateInfo_web;
	
	// 网站存放地址
	@Value("#{configProperties['web_path']}")
	private String web_path;
	
	// cookie储存时间(秒)
	@Value("#{configProperties['cookieSaveTime']}")
	private int cookieSaveTime;
	
	// 网站名称
	@Value("#{configProperties['webName']}")
	public static String webName = "drepair";
	
	// 数据库备份地址
	@Value("#{configProperties['sqlBackupPath']}")
	private String sqlBackupPath;
	
	// 数据库备份定时(秒)
	@Value("#{configProperties['sqlBackupTime']}")
	private int sqlBackupTime;
	
	// 数据库定时备份是否启动，on为开启，off为关闭
	@Value("#{configProperties['isStart_sqlBackup']}")
	private String isStart_sqlBackup;
	
	// 报修订单图片存放地址
	@Value("#{configProperties['imgPath']}")
	private String imgPath;
	
	// 后台管理导航栏颜色
	@Value("#{configProperties['topbarColor']}")
	public static String topbarColor = "#0e90d2";
	
	@Autowired
	private SqlService sqlService;
	
	/**
	 * 跳转至设置界面
	 * @param model
	 * @return
	 */
	@RequestMapping("setting")
	public String setting(Model model, Integer nowPage, Integer size) {
		// 将配置文件的内容发送到页面
		model.addAttribute("version_baoxiu", version_baoxiu);
		model.addAttribute("version_qiangxiu", version_qiangxiu);
		model.addAttribute("updateInfo_web", updateInfo_web);
		model.addAttribute("web_path", web_path);
		model.addAttribute("apkPath", apkPath);
		model.addAttribute("cookieSaveTime", cookieSaveTime);
		model.addAttribute("webName", webName);
		model.addAttribute("sqlBackupPath", sqlBackupPath);
		model.addAttribute("sqlBackupTime", sqlBackupTime);
		model.addAttribute("isStart_sqlBackup", isStart_sqlBackup);
		model.addAttribute("imgPath", imgPath);
		model.addAttribute("topbarColor", topbarColor);
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
	public String updateBaoxiu(RedirectAttributes attr, MultipartFile file_baoxiu, String updateVersion_baoxiu, String updateInfo_baoxiu) {
		// 判断文件夹是否存在，不存在就创建
		if(!(new File(apkPath)).exists()) {
			(new File(apkPath)).mkdir();
		}
		// 更新文件
		try {
			version_baoxiu = updateVersion_baoxiu; // 更新版本号
			WetsetCotroller.updateInfo_baoxiu = updateInfo_baoxiu; // 更新内容设置
			file_baoxiu.transferTo(new File(apkPath + "Baoxiu.apk")); // 复制文件
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
	public String updateQiangxiu(RedirectAttributes attr, MultipartFile file_qiangxiu, String updateVersion_qiangxiu, String updateInfo_qiangxiu) {
		// 判断文件夹是否存在，不存在就创建
		if(!(new File(apkPath)).exists()) {
			(new File(apkPath)).mkdir();
		}
		// 更新文件
		try {
			version_qiangxiu = updateVersion_qiangxiu; // 更新版本号
			WetsetCotroller.updateInfo_qiangxiu = updateInfo_qiangxiu; // 更新内容设置
			file_qiangxiu.transferTo(new File(apkPath + "Qiangxiu.apk")); // 复制文件
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
	public String updateWeb(RedirectAttributes attr, MultipartFile file_web, String updateInfo_web) {
		try {
			this.updateInfo_web = updateInfo_web; // 设置更新内容
			file_web.transferTo(new File(web_path)); // 复制文件
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
//			String paths = getClass().getResource("/").getFile().toString();
//			String path = paths.substring(1, paths.length());
//			PropertiesHelper.update(path + "webset.properties", "webName", webName);
//			PropertiesHelper.update(path + "webset.properties", "cookieSaveTime", cookieSaveTime+"");
//			PropertiesHelper.update(path + "webset.properties", "web_path", web_path);
			WetsetCotroller.topbarColor = topbarColor;
			WetsetCotroller.webName = webName;
			this.cookieSaveTime = cookieSaveTime;
			this.web_path = web_path;
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
	public String sqlBackup(RedirectAttributes attr, final String sqlBackupPath, final int sqlBackupTime) {
		try {
			this.sqlBackupPath = sqlBackupPath; // 设置备份地址
			this.sqlBackupTime = sqlBackupTime; // 设置备份定时
			
			// 判断文件夹是否存在，不存在就创建
			if(!(new File(sqlBackupPath)).exists()) {
				(new File(sqlBackupPath)).mkdir();
			}
			
			// 判断是启动还是停止
			if(isStart_sqlBackup.equals("off")) {
				// 开启定时备份
				this.isStart_sqlBackup = "on";
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(isStart_sqlBackup.equals("on")) {
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
				this.isStart_sqlBackup = "off";
				attr.addFlashAttribute("message", "停止成功！");
			}
		} catch (Exception e) {
			attr.addFlashAttribute("message", "服务器出错啦！");
			e.printStackTrace();
		}
		return "redirect:setting";
	}
	
}
