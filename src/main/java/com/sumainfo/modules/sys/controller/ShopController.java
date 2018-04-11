package com.sumainfo.modules.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumainfo.common.util.Distance;
import com.sumainfo.common.util.JsonResult;
import com.sumainfo.common.util.PageUtils;
import com.sumainfo.common.util.Pager;
import com.sumainfo.modules.sys.service.impl.ShopService;

@RestController
@RequestMapping("shop")
public class ShopController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	/**
	 * 获取店铺列表，按部门查询
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月11日
	 */
	@ResponseBody
	@RequestMapping(value="/getShopList",method=RequestMethod.GET)
	public JsonResult getShopList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		int cout=shopService.getShopListCout(params);
		if(cout==0){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		List<Map<String,Object>>getShopList=shopService.getShopList(params);
		result=pageUtils.getJsonResult(getShopList, params, cout);
		return result;
	}
	
	/**
	 * 获取获取大区的部门
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月11日
	 */
	@ResponseBody
	@RequestMapping(value="/getDeptList",method=RequestMethod.GET)
	public JsonResult getDeptList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		params.put("isregion", "1");
		return result.put(shopService.getDeptRegList(params));
	}
	
	/**
	 * 获取地理位置
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月11日
	 */
	@ResponseBody
	@RequestMapping(value="getDistance",method=RequestMethod.GET)
	public JsonResult getDistance(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		Map<String,Object>dist=Distance.getLngLatFromOneAddr(params);
		if(dist==null || dist.size()<1){
			result.putFailed("获取地理位置失败，请重新输入地址！");
		}else{
			result.put(dist);
		}
		return result;
	}
	
	/**
	 * 获取某一个店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月11日
	 */
	@ResponseBody
	@RequestMapping(value="getShopMap",method=RequestMethod.GET)
	public JsonResult getShopMap(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params--------->"+params);
		final List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
		params.put("slideshow", ids);
		result.put(shopService.getShop(params));
		return result;
	}
}
