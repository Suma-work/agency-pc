package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.ParamsCommon;
import com.sumainfo.modules.sys.dao.JpushMapper;

@Service
public class JpushService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(JpushService.class);
	
	@Autowired
	JpushMapper jpushMapper;
	
	public boolean setJpush(Map<String,Object>params){
		boolean result=false;
		params.put("fnc",
				this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());// 创建Function名
		ParamsCommon.commonInsertParms(params);
		int bole=jpushMapper.setJpush(params);
		if(bole>0){
			result=true;
		}
		return result;
	}
}
