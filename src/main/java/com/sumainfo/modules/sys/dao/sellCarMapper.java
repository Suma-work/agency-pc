package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface sellCarMapper {
	
	/**
	 * 热销车型集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	List<Map<String,Object>>getSellCarList(Map<String,Object>params);
	
	/**
	 * 获取热销车型总数
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	Integer getSellCarCout(Map<String,Object>params);
	
	/**
	 * 根据编号获取热销车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	Map<String,Object>getSellCarMap(Map<String,Object>params);
	
	/**
	 * 获取车型和图片中间表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	Map<String,Object>getVehiBan(Map<String,Object>params);
	
	/**
	 * 获取图片
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	Map<String,Object>getveBanPic(Map<String,Object>params);
	
	/**
	 * 获取所有车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	List<Map<String,Object>>getVehiCarList(Map<String,Object>params);
	
	/**
	 * 修改热销车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	Integer updateHote(Map<String,Object>params);
}
