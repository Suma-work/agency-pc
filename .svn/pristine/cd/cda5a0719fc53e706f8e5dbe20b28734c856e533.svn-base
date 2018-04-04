package com.sumainfo.common.util;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public  class ParamsCommon {

	/**
	 * 通用 新增
	 * @author fengfeng
	 * 
	 */
	public static Map<String, Object> commonInsertParms(Map<String, Object>params) {
		if (params==null ) {
			return params;
		}
		params.put("instm", ConvertDateTime.getCurrentTime());//创建时间 Insert时间
		params.put("updtm", ConvertDateTime.getCurrentTime());//更新时间 Update时间
		if (params.get("insby")==null) {
			params.put("insby", "suma");//创建人 Insert操作者  默认给auto
		}
		if (params.get("fnc")!=null) {
			params.put("insfnc", params.get("fnc"));//更新人 Update操作者  默认给auto
			params.remove("fnc");
		}
		if (params.get("term")!=null) {
			params.put("insterm", params.get("term"));//创建人 Insert操作者  默认给auto
			params.remove("term");
		}
		return params; 
	}
	
	/**
	 * 通用 修改
	 * @author fengfeng
	 * 
	 */
	public static Map<String, Object> commonUpdateParms(Map<String, Object>params) {
		if (params==null ) {
			return params;
		}
		params.put("updtm", ConvertDateTime.getCurrentTime());//更新时间 Update时间
		if (params.get("updby")==null) {
			params.put("updby", "suma");//更新人 Update操作者  默认给auto
		}
		if (params.get("fnc")!=null) {
			params.put("updfnc", params.get("fnc"));//更新人 Update操作者  默认给auto
			params.remove("fnc");
		}
		if (params.get("term")!=null) {
			params.put("updterm", params.get("term"));//更新人 Update操作者  默认给auto
			params.remove("term");
		}
		return params; 
	}
}
