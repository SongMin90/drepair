package com.drepair.po;

/**
 * 自定义编辑订单时显示订单的评价内容及评价人
 * 
 * @author SongM
 * @date 2017年8月24日 下午4:47:14
 */
public class EvalCustom2 {

	// 评价人姓名
	private String evalName;

	// 评价内容
	private String evalContent;

	public String getEvalName() {
		return evalName;
	}

	public void setEvalName(String evalName) {
		this.evalName = evalName;
	}

	public String getEvalContent() {
		return evalContent;
	}

	public void setEvalContent(String evalContent) {
		this.evalContent = evalContent;
	}
}
