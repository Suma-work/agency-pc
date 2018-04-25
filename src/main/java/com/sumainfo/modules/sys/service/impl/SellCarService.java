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

import com.sumainfo.common.util.ConvertDateTime;
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
			sellcarMap.put("shopId", map.get("shopId"));
			Map<String,Object>resu=new HashMap<String,Object>();
			Map<String,Object>vehiBan=sellCarMapper.getVehiBan(sellcarMap);
			if(vehiBan == null || vehiBan.size() == 0){
				resu.put("picAddress","http://52.80.16.16:8080/image/timg.jpg");
			}else{//如果为空
				vehiBan.put("isShow","1");//获取图片
				Map<String,Object>vehBanPic=sellCarMapper.getveBanPic(vehiBan);
				resu.put("picAddress", vehBanPic.get("picAddress"));
			}
			resu.put("hotid", map.get("hotid"));
			resu.put("shopId", map.get("shopId"));
			resu.put("shopName", map.get("shopName"));
			resu.put("hotcarid", map.get("hotcarid"));
			resu.put("hotcar", map.get("hotcar"));
			resu.put("hottype", map.get("hottype"));
			resu.put("createTime", map.get("createTime"));
			resu.put("sellPrice", map.get("sellPrice"));
			resu.put("deflg", map.get("deflg"));
			
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
	
	/**
	 * 根据编号获取热销车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	public Map<String,Object>getSellCarMap(Map<String,Object>params){
		Map<String,Object>result=sellCarMapper.getSellCarMap(params);
		return result;
	}
	
	/**
	 * 获取所有车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	public List<Map<String,Object>>getVehiCarList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>vehiCarList=sellCarMapper.getVehiCarList(params);
		for (Map<String, Object> map : vehiCarList) {
			Map<String,Object> vhciCar=new HashMap<String,Object>();
			vhciCar.put("id", map.get("carid"));
			vhciCar.put("value", map.get("carName"));
			result.add(vhciCar);
		}
		return result;
	}
	
	/**
	 * 修改热销车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	public int updateHote(Map<String,Object>params){
		params.put("modifyTime", ConvertDateTime.getCurrentTime());
		log.info("params->>>"+params);
		int boole=sellCarMapper.updateHote(params);
		return boole;
	}
	
	/**
	 * 获取所有的店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月25日
	 */
	public List<Map<String,Object>>getShopList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>shopList=sellCarMapper.getShopList(params);
		for (Map<String, Object> map : shopList) {
			Map<String,Object> shop=new HashMap<String,Object>();
			shop.put("id", map.get("shopId"));
			shop.put("value", map.get("shopName"));
			result.add(shop);
		}
		return result;
	}
}
