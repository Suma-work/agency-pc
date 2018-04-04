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

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.MenuRecursion;
import com.sumainfo.modules.sys.dao.VehicledetMapper;
import com.sumainfo.modules.sys.entity.Menu;

import freemarker.template.utility.StringUtil;

@Service
public class VehicaledetService implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(VehicaledetService.class);
	
	@Autowired
	VehicledetMapper vehicledetMapper;
	
	
	public List<Map<String,Object>>getVehiList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		
		//获取登录用户下的子部门
		List<Menu>deptList=vehicledetMapper.getDept(params);
		int dept=Integer.valueOf(params.get("deptid").toString());
		System.err.println(dept);
		List<Menu> tree=MenuRecursion.treeMenuList(deptList, dept);
		List<String> deptidList =new ArrayList<String>();//子部门编号
		for(Menu m:tree){  
			deptidList.add(m.getId());
        }  
		
		List<String> newList = new  ArrayList<String>(); 
         for (String cd:deptidList) {
            if(!newList.contains(cd)){
                newList.add(cd);
            }
        }
		
		/*//获取所在部门的店铺编号
		List<Map<String,Object>>getShopList=vehicledetMapper.getShopList(params);
		List<String> shopidList =new ArrayList<String>();//所在部门的店铺编号
		for (Map<String, Object> map : getShopList) {
			shopidList.add(map.get("shopId").toString());
			System.err.println(map.get("shopId").toString());
		}*/
		
		params.put("deptidList", newList);
		//新车列表
//		params.put("shopidList", shopidList);
		List<Map<String,Object>>vehiList=vehicledetMapper.getVehiList(params);
		for (Map<String, Object> map : vehiList) {
			if(!StringUtils.isEmpty(map.get("shopId"))){
				params.put("shopId", map.get("shopId"));
				params.put("bandName", map.get("bandName"));
				params.put("carName", map.get("carName"));
				map.put("shopName", vehicledetMapper.getShop(params).get("shopName"));
				//获取品牌编号
				map.put("fvcid", vehicledetMapper.getFcv(params).get("id"));
				//获取车型编号
				map.put("secid", vehicledetMapper.getSec(params).get("id"));
				result.add(map);
			}
		}
		return result;
	}
	
	public Integer getVehiCout(Map<String,Object>params){
		//获取登录用户下的子部门
		List<Menu>deptList=vehicledetMapper.getDept(params);
		int dept=Integer.valueOf(params.get("deptid").toString());
		System.err.println(dept);
		List<Menu> tree=MenuRecursion.treeMenuList(deptList, dept);
		List<String> deptidList =new ArrayList<String>();//子部门编号
		for(Menu m:tree){  
			deptidList.add(m.getId());
		}  
				
		List<String> newList = new  ArrayList<String>(); 
		for (String cd:deptidList) {
		     if(!newList.contains(cd)){
		       newList.add(cd);
		     }
		}
		params.put("deptidList", newList);
		return vehicledetMapper.getVehiCout(params);
		
	}
	
	public Map<String,Object>getVehiMap(Map<String,Object>params){
		Map<String,Object>VehiMap=vehicledetMapper.getVehiMap(params);
		if(VehiMap==null || VehiMap.size()<1){
			return new HashMap<String,Object>();
		}
		params.put("carName", VehiMap.get("carName"));
		params.put("shopId", VehiMap.get("shopId"));
		params.put("bandName", VehiMap.get("bandName"));
		params.put("carName", VehiMap.get("carName"));
		List<Map<String,Object>>vehiPicList=vehicledetMapper.getVePic(params);
		List<Map<String,Object>>vepics=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : vehiPicList) {
			Map<String,Object>vepic=new HashMap<String,Object>();
			vepic.put("isShow", map.get("isShow"));
			vepic.put("picAddress", map.get("picAddress"));
			vepics.add(vepic);
		}
		VehiMap.put("imgs", vepics);
		//获取品牌编号
		VehiMap.put("fvcid", vehicledetMapper.getFcv(params).get("id"));
		//获取车型编号
		VehiMap.put("secid", vehicledetMapper.getSec(params).get("id"));
		return VehiMap;
	}
	
	public boolean addVehi(Map<String,Object>params){
		boolean result=false;
		log.info("params->>>>>>>>>>>"+params);
		params.put("createTime", ConvertDateTime.getCurrentTime());
		int bole=vehicledetMapper.addVehi(params);
		if(bole>0){
			result=true;
		}
		return result;
	}
	
	public boolean updateVehi(Map<String,Object>params){
		boolean result=false;
		log.info("params->>>>>>>>>>>>"+params);
		params.put("modifyTime", ConvertDateTime.getCurrentTime());
		int bole=vehicledetMapper.updateVehi(params);
		if(bole>0){
			result=true;
		}
		return result;
	}
}
