package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	/**
	 * 查询所有的用户资料，根据部门查询
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getUserList(Map<String,Object>params);
	
	/**
	 * 查询所有的用户总数，根据部门查询
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @param params
	 * @return
	 */
	Integer getUserListCout(Map<String,Object>params);
	
	/**
	 * 获取部门资料
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @param params
	 * @return
	 */
	Map<String,Object>getDeptMap(Map<String,Object>params);
	
	/**
	 * 获取所有的子部门，除开苏马科技和平台管理员
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getDeptList(Map<String,Object>params);
	
}
