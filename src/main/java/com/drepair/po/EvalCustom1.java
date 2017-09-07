package com.drepair.po;

/**
 * 自定义评价类包装对象
 * @author SongM
 * @date 2017年8月24日 下午2:52:16
 */
public class EvalCustom1 {
	
	// 订单信息
	private OrderCustom orderCustom;
	
	// 学生信息
	private StuCustom stuCustom;
	
	// 宿管信息
	private HmrCustom hmrCustom;
	
	// 管理员信息
	private AdminCustom adminCustom;
	
	// 维修员信息
	private RepairerCustom repairerCustom;

	// 评价信息
	private EvalCustom evalCustom;
	
	public OrderCustom getOrderCustom() {
		return orderCustom;
	}

	public void setOrderCustom(OrderCustom orderCustom) {
		this.orderCustom = orderCustom;
	}

	public StuCustom getStuCustom() {
		return stuCustom;
	}

	public void setStuCustom(StuCustom stuCustom) {
		this.stuCustom = stuCustom;
	}

	public HmrCustom getHmrCustom() {
		return hmrCustom;
	}

	public void setHmrCustom(HmrCustom hmrCustom) {
		this.hmrCustom = hmrCustom;
	}

	public AdminCustom getAdminCustom() {
		return adminCustom;
	}

	public void setAdminCustom(AdminCustom adminCustom) {
		this.adminCustom = adminCustom;
	}

	public RepairerCustom getRepairerCustom() {
		return repairerCustom;
	}

	public void setRepairerCustom(RepairerCustom repairerCustom) {
		this.repairerCustom = repairerCustom;
	}

	public EvalCustom getEvalCustom() {
		return evalCustom;
	}

	public void setEvalCustom(EvalCustom evalCustom) {
		this.evalCustom = evalCustom;
	}
	
}
