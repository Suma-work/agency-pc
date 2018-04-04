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
import com.sumainfo.modules.sys.service.impl.customerService;

@RestController
@RequestMapping("cust")
public class CustomeController implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(CustomeController.class);
	
	@Autowired
	private customerService customerService;
	
	/**
	 * 获取个人用户信息
	 * @author:zhlu
	 * @date: 2018年3月23日
	 * @param params
	 * @param pager
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCusto",method=RequestMethod.GET)
	public JsonResult getCustomeList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer cout=customerService.getCustCout(params);
		if(cout==0){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		List<Map<String,Object>>getCustList=customerService.getCustoList(params);
		result=pageUtils.getJsonResult(getCustList, params, cout);
		return result;
	}
	
	/**
	 * 根据用户编号获取用户
	 * @author:zhlu
	 * @date: 2018年3月23日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCustoMap",method=RequestMethod.GET)
	public JsonResult getCustoMap(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(customerService.getCustMap(params));
	}
	
	/**
	 * 修改个人用户资料
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/setCust",method=RequestMethod.POST)
	public JsonResult setCust(@RequestBody Map<String,Object>params){
		log.info("cust/setCust   params-----<<<<<<"+params);
		JsonResult result=new JsonResult();
		if(params==null|| params.get("cusId")==null){
			throw new IllegalAccessError("缺失用户cusId");
		}
		if(customerService.update(params)){
			result.putSuccess();
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
}
