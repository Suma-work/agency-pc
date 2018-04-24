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
	
	/**
	 * 判断手机号是否存在
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	Integer getIsPhone(Map<String,Object>params);
	
//	/**
//	 * 新增商家用户
//	* @Description: TODO(这里用一句话描述这个方法的作用) 
//	* @author zhlu
//	* @date 2018年4月24日
//	 */
//	Integer setUser(Map<String,Object>params);
//	
	/**
	 * 新增商家和角色中间表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	Integer setUserRole(Map<String,Object>params);
	
	/**
	 * 获取角色信息
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	Map<String,Object>getRole(Map<String,Object>params);
	
	/**
	 * 获取用户资料
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	Map<String,Object>getUser(Map<String,Object>params);
	
	/**
	 * 获取用户和角色中间表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	Integer getUserRole(Map<String,Object>params);
	
	/**
	 * 删除用户和角色中间表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	Integer delectUserRole(Map<String,Object>params);
//	
//	/**
//	 * 新增商家用户日志表
//	* @Description: TODO(这里用一句话描述这个方法的作用) 
//	* @author zhlu
//	* @date 2018年4月24日
//	 */
//	Integer setLog(Map<String,Object>params);
	
}
