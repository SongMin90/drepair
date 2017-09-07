package com.drepair.exception;

/**
 * 自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author SongM
 * @date 2017年6月21日 下午4:32:23
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5183561757177473132L;
	
	// 异常信息
	public String message;
	
	public CustomException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
