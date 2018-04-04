package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sumainfo.modules.sys.dao.VehiclemainMapper;

@Service
public class VehiclemainService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	VehiclemainMapper vehiclemainMapper;
	
	/**
	 * 获取一级汽车
	 * @author:zhlu
	 * @date: 2018年3月20日
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getVeMainList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>VeMainList=vehiclemainMapper.getVeMainList(params);
		for (Map<String, Object> map : VeMainList) {
			Map<String,Object>veMap=new HashMap<String,Object>();
			veMap.put("typeId", map.get("typeId"));
			veMap.put("uqId", map.get("uqId"));
			veMap.put("shopId", map.get("shopId"));
			veMap.put("shopName", map.get("shopName"));
			veMap.put("bandName", map.get("bandName"));
			veMap.put("carName", map.get("carName"));
			veMap.put("rank", map.get("rank"));
			veMap.put("gearbox", map.get("gearbox"));
			veMap.put("carType", map.get("carType"));
			
//			String[] carEn=map.get("carEngine").toString().split(",");
			veMap.put("carEngine",map.get("carEngine"));
			
			veMap.put("isHot", map.get("isHot"));
			//获取图片。传递店铺编号和汽车名称，以及展示标志1
			params.put("carName", map.get("carName"));
			params.put("shopId", map.get("shopId"));
			Map<String,Object>vePic=vehiclemainMapper.getVePic(params);
			if(vePic==null){
				veMap.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
			}else{
				veMap.put("picAddress", vePic.get("picAddress"));
			}
			veMap.put("createTime", map.get("createTime"));
			params.put("name",map.get("bandName"));
			Map<String,Object>fvcMap=vehiclemainMapper.getFcvMap(params);
			veMap.put("fcvid", fvcMap.get("id"));
			result.add(veMap);
		}
		return result;
	}
	
	public Integer getVeMainListCout(Map<String,Object>params){
		return vehiclemainMapper.getVeMainListCout(params);
	}
	
	public List<Map<String,Object>>getFvc(Map<String,Object>params){
		return vehiclemainMapper.getFcv(params);
	}
	
	public Map<String,Object>getVeMainMap(Map<String,Object>params){
		Map<String,Object>getVeMainMap=vehiclemainMapper.gerVeMainMap(params);
		params.put("carName", getVeMainMap.get("carName"));
		params.put("shopId", getVeMainMap.get("shopId"));
		Map<String,Object>vePic=vehiclemainMapper.getVePic(params);
		if(vePic==null){
			getVeMainMap.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
		}else{
			getVeMainMap.put("picAddress", vePic.get("picAddress"));
		}
		params.put("name",getVeMainMap.get("bandName"));
		Map<String,Object>fvcMap=vehiclemainMapper.getFcvMap(params);
		getVeMainMap.put("fcvid", fvcMap.get("id"));
		return getVeMainMap;
	}
	
	public List<Map<String,Object>>getClvl(Map<String,Object>params){
		return vehiclemainMapper.getClvl(params);
	}
	
	public List<Map<String,Object>>getshop(Map<String,Object>params){
		return vehiclemainMapper.getshop(params);
	}
}
