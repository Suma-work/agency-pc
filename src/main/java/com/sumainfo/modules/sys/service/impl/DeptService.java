package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.modules.sys.dao.DeptMapper;

@Service
public class DeptService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(DeptService.class);
	
	@Autowired
	DeptMapper deptMapper;
	
	public Map<String,Object>getDept(Map<String,Object>params){
		Map<String,Object>dept=deptMapper.getDept(params);
		return dept;
	}
}
