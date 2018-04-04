package com.sumainfo.modules.sys.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JpushMapper {

	/**
	 * 添加推送日志
	 * @author:zhlu
	 * @date: 2018年3月30日
	 * @param params
	 * @return
	 */
	Integer setJpush(Map<String,Object>params);
	
}
