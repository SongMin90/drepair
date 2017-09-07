package com.drepair.utils;

/**
 * 数据库定时备份(多线程)
 * @author SongM
 * @date 2017/5/14
 */
public class PlanBackup implements Runnable {

	private String planBackupPath; //定时备份路径
	private int planBackupTime; //定时备份时间(秒)
	private String ip = "127.0.0.1";
	private String name = "root";
	private String pwd = "sa";
	private String saveName = DateTime.getDate() + "_" + DateTime.getTime() + ".sql";
	private String databaseName = "drepair";
	private boolean isPlan;
	
	public PlanBackup(Boolean isPlan, String planBackupPath, int planBackupTime) {
		this.planBackupPath = planBackupPath;
		this.planBackupTime = planBackupTime;
		this.isPlan = isPlan;
	}
	
	@Override
	public void run() {
		while(isPlan) {
			try {
				Thread.sleep(planBackupTime * 1000);
				new MySQLDatabase().exportDatabaseTool(ip, name, pwd, planBackupPath, saveName, databaseName);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
