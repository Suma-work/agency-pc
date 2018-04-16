package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.modules.sys.dao.BannerMapper;

@Service
public class BannerService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(BannerService.class);
	
	@Autowired
	BannerMapper bannerMapper;
	
	/**
	 * 查询轮播图列表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	public List<Map<String,Object>>getBannerList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>banList=bannerMapper.getBannerList(params);
		return banList;
	}
	
	/**
	 * 轮播图总数
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	public Integer getBannerCout(Map<String,Object>params){
		int banCout=bannerMapper.getBannerCout(params);
		return banCout;
	}
	
	/**
	 * 根据轮播图编号获取轮播图
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	public Map<String,Object>getBanMap(Map<String,Object>params){
		Map<String,Object>banMap=bannerMapper.getBanMap(params);
		return banMap;
	}
	
	/**
	 * 修改轮播图
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	public boolean updateBan(Map<String,Object>params){
		boolean result=false;
		params.put("modifyTime", ConvertDateTime.getCurrentTime());
		int ban=bannerMapper.updaBanner(params);
		if(ban>0){
			result=true;
		}
		return result;
	}
	
}
