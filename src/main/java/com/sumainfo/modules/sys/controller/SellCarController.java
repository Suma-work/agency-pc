package com.sumainfo.modules.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value="getSellCarList",method=RequestMethod.GET)
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
}
