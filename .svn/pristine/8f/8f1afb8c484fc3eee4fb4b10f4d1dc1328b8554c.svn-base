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
import com.sumainfo.modules.sys.service.impl.VehiclemainService;

@RestController
@RequestMapping("vehic")
public class VehiclemainController implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(VehiclemainController.class);
	
	@Autowired
	VehiclemainService vehiclemainService;
	
	/**
	 * 获取一级品牌
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @param pager
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVeMainList",method=RequestMethod.GET)
	public JsonResult getVeMainList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult jsonResult=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		params.put("isShow", 1);
		Integer cout=vehiclemainService.getVeMainListCout(params);
		if(0==cout){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		log.info("params->>>>>>>>>>>>>>"+params);
		List<Map<String,Object>>veMainList=vehiclemainService.getVeMainList(params);
		jsonResult = pageUtils.getJsonResult(veMainList, params, cout);
		return jsonResult;
	}
	
	/**
	 * 获取所有的品牌
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getFvc",method=RequestMethod.GET)
	public JsonResult getFvc(@RequestParam Map<String,Object>params){
		JsonResult jsonResult=new JsonResult();
		return jsonResult.put(vehiclemainService.getFvc(params));
	}
	
	/**
	 * 获取某一个品牌
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVeMap",method=RequestMethod.GET)
	public JsonResult getVeMainMap(@RequestParam Map<String,Object>params){
		JsonResult jsonResult=new JsonResult();
		params.put("isShow", 1);
		return jsonResult.put(vehiclemainService.getVeMainMap(params));
	}
	
	/**
	 * 根据name获取品牌
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getFvcByName",method=RequestMethod.GET)
	public JsonResult getFvcByName(@RequestParam Map<String,Object>params){
		JsonResult jsonResult=new JsonResult();
		return jsonResult.put(vehiclemainService.getFvc(params));
	}
	
	/**
	 * 获取所有的车型
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getClvl",method=RequestMethod.GET)
	public JsonResult getClvl(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(vehiclemainService.getClvl(params));
	}
	
	/**
	 * 获取所有店铺
	 * @author:zhlu
	 * @date: 2018年3月22日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getshop",method=RequestMethod.GET)
	public JsonResult getshop(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(vehiclemainService.getshop(params));
	}
}
