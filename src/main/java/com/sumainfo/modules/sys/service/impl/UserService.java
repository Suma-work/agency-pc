package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.modules.sys.dao.UserMapper;

@Service
public class UserService implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger log=LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 查询所有用户，可以根据部门编号
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @return
	 */
	public List<Map<String,Object>>getUserList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		log.info("获取所有的子部门");
		if(!"".equals(params.get("deptid").toString())){
			List<Map<String,Object>>deptList=userMapper.getDeptList(params);
			if(deptList!=null && !deptList.isEmpty()){
				List<String> deptidList =new ArrayList<String>();
				for (Map<String, Object> map : deptList) {
					deptidList.add(map.get("deptid").toString());
				}
				deptidList.add(params.get("deptid").toString());
				params.put("deptidList", deptidList);
				log.info("deptidList:->>>>>>>>"+deptidList.size());
			}
		}
		List<Map<String,Object>>userList=userMapper.getUserList(params);
		for (Map<String, Object> map : userList) {
			//获取部门资料
			params.put("udeptid", map.get("udeptid"));
			Map<String,Object>dept=userMapper.getDeptMap(params);
			map.put("deptname", dept.get("name"));
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 查询所有用户总数，可以根据部门编号
	 * @author:zhlu
	 * @date: 2018年4月2日
	 * @return
	 */
	public Integer getUserListCout(Map<String,Object>params){
		return userMapper.getUserListCout(params);
	}
	
	public boolean getIsPhone(Map<String,Object>params){
		boolean result=false;
		int count=userMapper.getIsPhone(params);
		if(count>0){
			result=true;
		}
		return result;
	}
}
