package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {

	/**
	 * 获取男女比例
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getStatiList(Map<String,Object>params);
	
	/**
	 * 获取店铺地区统计
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getShopSta(Map<String,Object>params);
	
	/**
	 * 获取店铺地区总数
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	Integer getShopStaCout(Map<String,Object>params);
	
}
