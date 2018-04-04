package com.sumainfo.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.sumainfo.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
* @Title: SysDeptService.java 
* @Package com.sumainfo.modules.sys.service  
* @author zhlu
* @date 2018年3月15日
 */
public interface SysDeptService extends IService<SysDeptEntity> {

	List<SysDeptEntity> queryList(Map<String, Object> map);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID，用于数据过滤
	 */
	List<Long> getSubDeptIdList(Long deptId);

}
