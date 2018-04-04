package com.sumainfo.common.util;

import java.util.HashMap;

public class JsonResult extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	final String SUCCESSCODE = "0";
	final String SUCCESSMSG = "ok";
	final String FAILEDCODE = "400";
	
	/**
	 * 返回结果集, 无记录条数, 和成功/错误信息+errCode
	 * @param object	结果集
	 * @param errCode
	 * @param errMsg	
	 * @return
	 */
	public JsonResult put(Object object, String errCode, String errMsg){
		super.put("data", object);
		super.put("messageCode", errCode);
		super.put("messageStr", errMsg);
		return this;
	}
	
	/**
	 * 返回结果集, 带记录条数
	 * @param object	结果集
	 * @param num		总记录条数
	 * @return
	 */
	public JsonResult put(Object object , String num) {
		super.put("data", object);
		super.put("messageCode", SUCCESSCODE);
		super.put("total", num);
		return this;
	}
	
	/**
	 * 返回结果集, 无记录条数
	 * @param object	结果集
	 * @return
	 */
	public JsonResult put(Object object){
		super.put("data", object);
		super.put("messageCode", SUCCESSCODE);
		return this;
	}
	
	/**
	 * 返回成功/错误信息+errCode
	 * @param errMsg	结果集
	 * @param errCode	messageCode
	 * @return
	 */
	public JsonResult put(String errMsg, String errCode){
		super.put("messageCode", errCode);
		super.put("messageStr", errMsg);
		return this;
	}
	
	/**
	 * 返回成功信息+errCode
	 * @param errMsg	结果集
	 * @param errCode	messageCode
	 * @return
	 */
	public JsonResult putSuccess(){
		super.put("messageCode", SUCCESSCODE);
		super.put("messageStr", SUCCESSMSG);
		return this;
	}
	/**
	 * 返回成功信息+errCode
	 * @param errMsg	结果集
	 * @param errCode	messageCode
	 * @return
	 */
	public JsonResult putSuccess(String msg){
		super.put("messageCode", SUCCESSCODE);
		super.put("messageStr", msg);
		return this;
	}
	
	/**
	 * 返回失败信息+errCode
	 * @param errMsg	结果集
	 * @param errCode	messageCode
	 * @return
	 */
	public JsonResult putFailed(String errMsg){
		super.put("messageCode", FAILEDCODE);
		super.put("messageStr", errMsg);
		return this;
	}
}
