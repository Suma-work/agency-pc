package com.sumainfo.modules.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.common.util.JsonResult;
import com.sumainfo.common.util.PageUtils;
import com.sumainfo.common.util.Pager;
import com.sumainfo.modules.sys.service.impl.BannerService;

@RestController
@RequestMapping("banner")
public class BannerController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(BannerController.class);
	
	@Autowired
	BannerService bannerService;
	
	/**
	 * 获取轮播图列表1
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	@ResponseBody
	@RequestMapping(value="/getBanList",method=RequestMethod.GET)
	public JsonResult getBannerList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer cout=bannerService.getBannerCout(params);
		if(cout==0){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		List<Map<String,Object>>getBanList=bannerService.getBannerList(params);
		result=pageUtils.getJsonResult(getBanList, params, cout);
		return result;
	}
	
	/**
	 * 获取轮播图
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月14日
	 */
	@ResponseBody
	@RequestMapping(value="/getBanMap")
	public JsonResult getBanMap(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		Map<String,Object>banMap=bannerService.getBanMap(params);
		return result.put(banMap);
	}
	
	@RequestMapping(value="upBan",method=RequestMethod.POST)
	public JsonResult upBan(@RequestBody Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>>>>>"+params);
		if(params==null||params.get("bannerid")==null){
			return result.putFailed("缺失bannerid");
		}
		if(bannerService.updateBan(params)){
			result.putSuccess("修改成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
}
