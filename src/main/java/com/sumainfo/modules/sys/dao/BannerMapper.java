package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BannerMapper {

	/**
	 * 获取轮播图集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	List<Map<String,Object>>getBannerList(Map<String,Object>params);
	
	/**
	 * 获取轮播图总数量
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	Integer getBannerCout(Map<String,Object>params);
	
	/**
	 * 获取某一个轮播图
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	Map<String,Object>getBanMap(Map<String,Object>params);
	
	/**
	 * 修改轮播图
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	Integer updaBanner(Map<String,Object>params);
}
