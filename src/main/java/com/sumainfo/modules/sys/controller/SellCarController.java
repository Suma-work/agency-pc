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
import com.sumainfo.modules.sys.service.impl.SellCarService;

@RestController
@RequestMapping("selcar")
public class SellCarController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(SellCarController.class);
	
	@Autowired
	SellCarService sellCarService;
	
	/**
	 * 获取热销车型集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月16日
	 */
	@ResponseBody
	@RequestMapping(value="/getSellCarList",method=RequestMethod.GET)
	public JsonResult getSellCarList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer cout=sellCarService.getSellCarCout(params);
		if(cout==0){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		List<Map<String,Object>>getBanList=sellCarService.getSellCarList(params);
		result=pageUtils.getJsonResult(getBanList, params, cout);
		return result;
	}
	
	/**
	 * 根据编号获取热销
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getSellCarMap",method=RequestMethod.GET)
	public JsonResult getSellCarMap(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>"+params);
		if(params==null||params.get("hotid")==null){
			result.putFailed("缺少hotid!");
		}
		result.put(sellCarService.getSellCarMap(params));
		return result;
	}
	
	
	/**
	 * 获取所有的车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getVhciCarList",method=RequestMethod.GET)
	public JsonResult getVhciCarList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		result.put(sellCarService.getVehiCarList(params));
		return result;
		
	}
	
	/**
	 * 修改热销车型
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	
	@RequestMapping(value="/updateHote",method=RequestMethod.POST)
	public JsonResult updateHote(@RequestBody Map<String,Object>params){
		JsonResult result=new JsonResult();
		if(params==null||params.get("hotid")==null){
			result.putFailed("缺失hotid！");
		}
		int cout=sellCarService.updateHote(params);
		if(cout>0){
			result.putSuccess("修改热销车型成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
	
	/**
	 * 获取所有的店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getShopList",method=RequestMethod.GET)
	public JsonResult getShopList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		result.put(sellCarService.getShopList(params));
		return result;
	}
}
