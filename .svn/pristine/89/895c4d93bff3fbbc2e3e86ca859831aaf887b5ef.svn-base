package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface customerMapper {

	/**
	 * 获取个人用户信息
	 * @author:zhlu
	 * @date: 2018年3月23日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>gerCustList(Map<String,Object>params);
	
	/**
	 * 获取个人用户的总数
	 * @author:zhlu
	 * @date: 2018年3月23日
	 * @param params
	 * @return
	 */
	Integer getCustCout(Map<String,Object>params);
	
	/**
	 * 根据个人用户编号获取用户资料
	 * @author:zhlu
	 * @date: 2018年3月23日
	 * @param params
	 * @return
	 */
	Map<String,Object>getCustMap(Map<String,Object>params);
	
	/**
	 * 新增用户资料
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
//	Integer addCust(Map<String,Object>params);
	
	/**
	 * 修改用户资料
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	Integer updateCust(Map<String,Object>params);
}
