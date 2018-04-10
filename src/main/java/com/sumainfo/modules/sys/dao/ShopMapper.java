package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sumainfo.modules.sys.entity.Menu;

@Mapper
public interface ShopMapper {

	/**
	 * 获取店铺信息
	 * @param prams
	 * @return
	 */
	List<Map<String,Object>>getShopList(Map<String,Object>params);
	
	/**
	 * 获取某一个店铺
	 * @param params
	 * @return
	 */
	Map<String,Object>getShop(Map<String,Object>params);
	
	/**
	 * 获取店铺总数
	 * @param params
	 * @return
	 */
	Integer getShopListCout(Map<String,Object>params);
	
	/**
	 * 获取子部门
	 * @author:zhlu
	 * @date: 2018年4月3日
	 * @param params
	 * @return
	 */
	 List<Menu> getDept(Map<String,Object>params);
	 
	 /**
	  * 获取大区的部门
	  * @param parmas
	  * @return
	  */
	 List<Map<String,Object>>getDeptList(Map<String,Object>params);
	 
	 /**
	  * 获取父部门
	  * @param params
	  * @return
	  */
	 Map<String,Object>getDeptMap(Map<String,Object>params);
}
