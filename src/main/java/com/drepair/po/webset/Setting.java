package com.drepair.po.webset;

/**
 * 网站设置
 * @author SongM
 * @date 2017年9月21日 上午10:41:42
 */
public class Setting {

	private String apkPath; // apk存放地址
	private String version_baoxiu; // 报修客户端版本
	private String updateInfo_baoxiu; // 报修客户端更新信息
	private String version_qiangxiu; // 抢修客户端版本
	private String updateInfo_qiangxiu; // 抢修客户端更新信息
	private String updateInfo_web; // 网站更新内容
	private String web_path; // 网站存放地址
	private int cookieSaveTime; // cookie储存时间(秒)，默认30天
	private String webName; // 网站名称
	private String sqlBackupPath; // 数据库备份地址
	private int sqlBackupTime; // 数据库备份定时(秒)，默认1天
	private String isStart_sqlBackup; // 数据库定时备份是否启动，on为开启，off为关闭，默认关闭
	private String imgPath; // 报修订单图片存放地址
	private String topbarColor; // 后台管理导航栏颜色
	private String apiState; // API开取状态，on为开启，off为关闭，默认关闭
	
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	public String getVersion_baoxiu() {
		return version_baoxiu;
	}
	public void setVersion_baoxiu(String version_baoxiu) {
		this.version_baoxiu = version_baoxiu;
	}
	public String getUpdateInfo_baoxiu() {
		return updateInfo_baoxiu;
	}
	public void setUpdateInfo_baoxiu(String updateInfo_baoxiu) {
		this.updateInfo_baoxiu = updateInfo_baoxiu;
	}
	public String getVersion_qiangxiu() {
		return version_qiangxiu;
	}
	public void setVersion_qiangxiu(String version_qiangxiu) {
		this.version_qiangxiu = version_qiangxiu;
	}
	public String getUpdateInfo_qiangxiu() {
		return updateInfo_qiangxiu;
	}
	public void setUpdateInfo_qiangxiu(String updateInfo_qiangxiu) {
		this.updateInfo_qiangxiu = updateInfo_qiangxiu;
	}
	public String getUpdateInfo_web() {
		return updateInfo_web;
	}
	public void setUpdateInfo_web(String updateInfo_web) {
		this.updateInfo_web = updateInfo_web;
	}
	public String getWeb_path() {
		return web_path;
	}
	public void setWeb_path(String web_path) {
		this.web_path = web_path;
	}
	public int getCookieSaveTime() {
		return cookieSaveTime;
	}
	public void setCookieSaveTime(int cookieSaveTime) {
		this.cookieSaveTime = cookieSaveTime;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getSqlBackupPath() {
		return sqlBackupPath;
	}
	public void setSqlBackupPath(String sqlBackupPath) {
		this.sqlBackupPath = sqlBackupPath;
	}
	public int getSqlBackupTime() {
		return sqlBackupTime;
	}
	public void setSqlBackupTime(int sqlBackupTime) {
		this.sqlBackupTime = sqlBackupTime;
	}
	public String getIsStart_sqlBackup() {
		return isStart_sqlBackup;
	}
	public void setIsStart_sqlBackup(String isStart_sqlBackup) {
		this.isStart_sqlBackup = isStart_sqlBackup;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getTopbarColor() {
		return topbarColor;
	}
	public void setTopbarColor(String topbarColor) {
		this.topbarColor = topbarColor;
	}
	public String getApiState() {
		return apiState;
	}
	public void setApiState(String apiState) {
		this.apiState = apiState;
	}
	
}
