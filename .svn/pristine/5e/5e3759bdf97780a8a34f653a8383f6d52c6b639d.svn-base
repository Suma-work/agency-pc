package com.sumainfo.modules.sys.controller;

import java.io.Serializable;
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
import com.sumainfo.modules.sys.service.impl.StatisticsService;

@RestController
@RequestMapping("stat")
public class StatisticsController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(StatisticsController.class);
	
	@Autowired
	StatisticsService statisticsService;
	
	@ResponseBody
	@RequestMapping(value="/getStat",method=RequestMethod.GET)
	public JsonResult getStaticList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		result.put(statisticsService.getStaList(params));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getShopSta",method=RequestMethod.GET)
	public JsonResult getShopSta(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		result.put(statisticsService.getShopSta(params));
		return result;
	}
}
