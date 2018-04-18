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
	
	/**
	  * 获取当前的部门
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月10日
	  */
	 Map<String,Object>getDeptMap(Map<String,Object>params);
	 
	 /**
	  * 新增部门
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月18日
	  */
	 Integer addDept(Map<String,Object>params);
	 
	 /**
	  * 修改部门
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月18日
	  */
	 Integer updateDept(Map<String,Object>params);
}
