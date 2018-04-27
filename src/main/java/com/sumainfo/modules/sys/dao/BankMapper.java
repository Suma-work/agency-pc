package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankMapper {

	/**
	 * 获取金融服务的集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	List<Map<String,Object>>getBankList(Map<String,Object>params);
	
	/**
	 * 获取金融服务的总数
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	Integer getBankLitCout(Map<String,Object>params);
	
	/**
	 * 获取金融服务的对象
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	Map<String,Object>getBankMap(Map<String,Object>params);
	
	/**
	 * 新增金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	Integer addBank(Map<String,Object>params);
	
	/**
	 * 修改金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	Integer updateBank(Map<String,Object>params);
	
	/**
	 * 删除金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	Integer delectBank(Map<String,Object>params);
}
