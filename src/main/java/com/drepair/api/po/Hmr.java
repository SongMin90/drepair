package com.drepair.api.po;

/**
 * Api 通过工号查询宿管详细信息
 * @author SongM
 * @date 2017年9月5日 下午9:03:22
 */
public class Hmr {

	private String find; // 是否找到，success为成功，error为失败
	private Manager manager;
	
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
