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
	
	@ResponseBody
	@RequestMapping(value="/getDeptList",method=RequestMethod.GET)
	public JsonResult getDeptList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		params.put("isregion", "1");
		return result.put(shopService.getDeptRegList(params));
	}
}
