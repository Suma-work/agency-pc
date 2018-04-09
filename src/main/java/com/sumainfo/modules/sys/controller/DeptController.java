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
import com.sumainfo.modules.sys.service.impl.DeptService;

@RestController
@RequestMapping("dept")
public class DeptController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService deptService;
	
	@ResponseBody
	@RequestMapping(value="/getDept",method=RequestMethod.GET)
	public JsonResult getDept(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(deptService.getDept(params));
	}
}
