package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.modules.sys.dao.BankMapper;

@Service
public class BankService implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(BankService.class);

	@Autowired
	BankMapper bankMapper;
	
	/**
	 * 获取金融服务的集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public List<Map<String,Object>>getBankList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>bankList=bankMapper.getBankList(params);
		return bankList;
	}
	
	/**
	 * 获取金融服务的总数
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public Integer getBankListCout(Map<String,Object>params){
		return bankMapper.getBankLitCout(params);
	}
	
	/**
	 * 获取金融服务对象
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public Map<String,Object>getBankMap(Map<String,Object>params){
		return bankMapper.getBankMap(params);
	}
	
	/**
	 * 新增金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public boolean addBank(Map<String,Object>params){
		boolean result=false;
		params.put("bankId", ComUtils.randomUID("bank"));
		params.put("createTime", ConvertDateTime.getCurrentTime());
		try {
			int cout=bankMapper.addBank(params);
			if(cout>0){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public boolean updateBank(Map<String,Object>params){
		boolean result=false;
		params.put("modifyTime", ConvertDateTime.getCurrentTime());
		try {
			int cout=bankMapper.updateBank(params);
			if(cout>0){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	public boolean deltctBank(Map<String,Object>params){
		boolean result=false;
		try {
			int cout=bankMapper.delectBank(params);
			if(cout>0){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
