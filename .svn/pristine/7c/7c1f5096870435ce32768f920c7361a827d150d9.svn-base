package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sumainfo.modules.sys.dao.StatisticsMapper;

@Service
public class StatisticsService implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(StatisticsService.class);
	
	@Autowired
	StatisticsMapper statisticsMapper;
	
	public List<Map<String,Object>>getStaList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>StaList=statisticsMapper.getStatiList(params);
		for (Map<String, Object> map : StaList) {
			Map<String,Object>staMap=new HashMap<String, Object>();
			String sex=map.get("sex").toString();
			String id = "";
			String name = "";		
			String value = "";
			switch(sex){
			case "0":
				id = sex;
				name = "女";
				value = map.get("cout").toString();
				break;
			case "1":
				id = sex;
				name = "男";
				value = map.get("cout").toString();
				break;
			default :
				id = "2";
				name = "未认证";
				value = map.get("cout").toString();
				break;
			}
			staMap.put("id", id);
			staMap.put("name", name);
			staMap.put("value", value);
			result.add(staMap);
		}
		return result;
	}
	
	public Map<String,Object>getShopSta(Map<String,Object>params){
		List<Map<String,Object>>proList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>shopList=statisticsMapper.getShopSta(params);
		for (Map<String, Object> map : shopList) {
			Map<String,Object>proMap=new HashMap<String,Object>();
			if(!StringUtils.isEmpty(map.get("value"))){
				proMap.put("name", map.get("name"));
				proMap.put("value", map.get("value"));
			}else{
				proMap.put("name", map.get("name"));
				proMap.put("value", 0);
			}
			proList.add(proMap);
		}
		Map<String,Object>result=new HashMap<String,Object>();
		result.put("data", proList);
		result.put("max", statisticsMapper.getShopStaCout(params));
		return result;
	}
	
}
