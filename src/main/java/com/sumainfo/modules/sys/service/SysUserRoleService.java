package com.sumainfo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.sumainfo.modules.sys.entity.SysUserRoleEntity;

import java.util.List;


/**
 * 用户与角色对应关系
* @Title: SysUserRoleService.java 
* @Package com.sumainfo.modules.sys.service  
* @author zhlu
* @date 2018年3月15日
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity>  {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
