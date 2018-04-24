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
import com.sumainfo.modules.sys.service.impl.UserService;

@RestController
@RequestMapping("user")
public class UserController implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * 查询所有用户，可以根据部门编号
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getUserList",method=RequestMethod.GET)
	public JsonResult getUserList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer cout=userService.getUserListCout(params);
		if(0==cout){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		log.info("params->>>>>>>>>>>>>>"+params);
		List<Map<String,Object>>veMainList=userService.getUserList(params);
		result = pageUtils.getJsonResult(veMainList, params, cout);
		return result;
	}
	
	/**
	 * 验证手机号是否存在
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getIsPhone",method=RequestMethod.GET)
	public JsonResult getIsPhone(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		if(userService.getIsPhone(params)){
			result.putFailed("手机号码已存在，请重新输入！");
		}else{
			result.putSuccess();
		}
		return result;
	}
	
	/**
	 * 新增用户和角色中间表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月24日
	 */
	@RequestMapping(value="setUserRole",method=RequestMethod.POST)
	public JsonResult setUserRole(@RequestBody Map<String,Object>params){
		log.info("params->>>>>>>>"+params);
		JsonResult result=new JsonResult();
		if(userService.setUserRole(params)){
			result.putSuccess();
		}else{
			result.putFailed("新增失败");
		}
		return result;
	}
	
}
