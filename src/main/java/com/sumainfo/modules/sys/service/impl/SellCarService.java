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

import com.sumainfo.modules.sys.dao.sellCarMapper;

@Service
public class SellCarService implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(SellCarService.class);
	
	@Autowired
	sellCarMapper sellCarMapper;
	
	/**
	 * 获取热销车型集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	public List<Map<String,Object>>getSellCarList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>sellcar=sellCarMapper.getSellCarList(params);
		for (Map<String, Object> map : sellcar) {
			//定义获取车型和图片的中间表
			Map<String,Object>sellcarMap=new HashMap<String,Object>();
			sellcarMap.put("carName", map.get("hotcar"));
			sellcarMap.put("classify", map.get("hottype"));
			Map<String,Object>vehiBan=sellCarMapper.getVehiBan(sellcarMap);
			vehiBan.put("isShow","1");//获取图片
			Map<String,Object>vehBanPic=sellCarMapper.getveBanPic(vehiBan);
			
			Map<String,Object>resu=new HashMap<String,Object>();
			resu.put("hotid", map.get("hotid"));
			resu.put("hotcar", map.get("hotcar"));
			resu.put("hottype", map.get("hottype"));
			resu.put("createTime", map.get("createTime"));
			resu.put("sellPrice", map.get("sellPrice"));
			resu.put("deflg", map.get("deflg"));
			resu.put("picAddress", vehBanPic.get("picAddress"));
			result.add(resu);
		}
		return result;
	}
	
	/**
	 * 获取热销车型的总数
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	public int getSellCarCout(Map<String,Object>params){
		int sellcar=sellCarMapper.getSellCarCout(params);
		return sellcar;
	}
}
