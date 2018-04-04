package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.Md5Until;
import com.sumainfo.modules.sys.dao.customerMapper;

@Service
public class customerService implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger log=LoggerFactory.getLogger(customerService.class);
	
	@Autowired
	private customerMapper customerMapper;
	
	public List<Map<String,Object>>getCustoList(Map<String,Object>params){
		return customerMapper.gerCustList(params);
	}
	
	public Integer getCustCout(Map<String,Object>params){
		return customerMapper.getCustCout(params);
	}
	
	public Map<String,Object>getCustMap(Map<String,Object>params){
		return customerMapper.getCustMap(params);
	}
	
	public boolean update(Map<String,Object>params){
		boolean result=false;
		String passWord=Md5Until.encryPassWord(params.get("passWord").toString());
		log.info("第一次加密++++passWord》》》"+passWord);
		log.info("第二次加密++++passWord》》》"+Md5Until.encryPassWord(passWord));
		params.put("passWord",Md5Until.encryPassWord(passWord));
		params.put("modifyTime",ConvertDateTime.getCurrentTime());
		log.info("params-?????"+passWord);
		int cout=customerMapper.updateCust(params);
		if(cout>0){
			result=true;
		}
		return result;
	}
}
