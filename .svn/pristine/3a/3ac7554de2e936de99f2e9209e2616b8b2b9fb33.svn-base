package com.sumainfo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sumainfo.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色与部门对应关系
* @Title: SysRoleDeptDao.java 
* @Package com.sumainfo.modules.sys.dao  
* @author zhlu
* @date 2018年3月15日
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long[] roleIds);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
