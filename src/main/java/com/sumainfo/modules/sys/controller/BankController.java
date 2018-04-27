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
import com.sumainfo.modules.sys.service.impl.BankService;

@RestController
@RequestMapping("bank")
public class BankController implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	BankService bankService;
	
	/**
	 * 获取金融服务的集合
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/getBankList",method=RequestMethod.GET)
	public JsonResult getBankList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		log.info("params->>>>>>>>"+params);
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		int cout=bankService.getBankListCout(params);
		if(cout==0){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		List<Map<String,Object>>getShopList=bankService.getBankList(params);
		result=pageUtils.getJsonResult(getShopList, params, cout);
		return result;
	}
	
	/**
	 * 获取金融服务对象
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="getBankMap",method=RequestMethod.GET)
	public JsonResult getBankMap(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		result.put(bankService.getBankMap(params));
		return result;
	}
	
	/**
	 * 新增金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	@RequestMapping(value="/addBank",method=RequestMethod.POST)
	public JsonResult addBank(@RequestBody Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>>"+params);
		if(bankService.addBank(params)){
			result.putSuccess("新增成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
	
	/**
	 * 修改金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	@RequestMapping(value="/updateBank",method=RequestMethod.POST)
	public JsonResult updateBank(@RequestBody Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>>"+params);
		if(bankService.updateBank(params)){
			result.putSuccess("修改成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
	
	/**
	 * 删除金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/delectBank",method=RequestMethod.GET)
	public JsonResult delectBank(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>"+params);
		if(bankService.deltctBank(params)){
			result.putSuccess("删除成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
}
