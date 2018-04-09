package com.sumainfo.modules.sys.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
	
	/**
	 * 获取部门，如果获取的部门父节点是1，就说明当前部门是大区经理
	 * @param params
	 * @return
	 */
	Map<String,Object>getDept(Map<String,Object>params);
}
