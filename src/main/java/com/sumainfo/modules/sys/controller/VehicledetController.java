package com.sumainfo.modules.sys.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.util.JsonResult;
import com.sumainfo.common.util.PageUtils;
import com.sumainfo.common.util.Pager;
import com.sumainfo.modules.sys.service.impl.VehicaledetService;

@RestController
@RequestMapping("vehi")
public class VehicledetController implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(VehicledetController.class);
	
	@Autowired
	VehicaledetService vehicaledetService;
	
	/**
	 * 根据店铺查看新车列表
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @param pager
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVehiList",method=RequestMethod.GET)
	public JsonResult getVehiList(@RequestParam Map<String,Object>params,Pager pager){
		JsonResult jsonResult=new JsonResult();
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer cout=vehicaledetService.getVehiCout(params);
		if(0==cout){
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,cout);
		}
		log.info("params->>>>>>>>>>>>>>"+params);
		List<Map<String,Object>>veMainList=vehicaledetService.getVehiList(params);
		jsonResult = pageUtils.getJsonResult(veMainList, params, cout);
		return jsonResult;
	}
	
	/**
	 * 根据新车编号获取新车资料
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVehi",method=RequestMethod.GET)
	public JsonResult getVehi(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		Map<String,Object>vehi=vehicaledetService.getVehiMap(params);
		return result.put(vehi);
	}
	
	/**
	 * 添加新车
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/setVehi",method=RequestMethod.POST)
	public JsonResult setVehi(@RequestBody Map<String,Object>params,MultipartFile[] files){
		log.info("params->>>>>>>>>>>>"+params);
		log.info("files->>>>>>>>>>>>"+files);
		JsonResult result=new JsonResult();
		if(vehicaledetService.addVehi(params)){
			result.putSuccess("新增成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
	
	/**
	 * 修改新车
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/modiVehi",method=RequestMethod.POST)
	public JsonResult modiVehi(@RequestBody Map<String,Object>params)throws IOException{
		log.info("params->>>>>>>>>>>>"+params);
//		log.info("files->>>>>>>>>>>>"+file);
//		String fileName = file.getOriginalFilename();//获取到上传文件的名字
		JsonResult result=new JsonResult();
		if(params==null|| params.get("carId")==null){
			throw new IllegalAccessError("缺失用户carId");
		}
		if(vehicaledetService.updateVehi(params)){
			result.putSuccess("修改成功！");
		}else{
			result.putFailed("服务器繁忙,请稍后重试!");
		}
		return result;
	}
	
	/**
	 * 获取所有的车型
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCarList",method=RequestMethod.GET)
	public JsonResult getClvl(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(vehicaledetService.getCarList(params));
//		return result;
	}
	
	/**
	 * 获取所有的店铺品牌
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getBanList",method=RequestMethod.GET)
	public JsonResult getBanList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>"+params);
		return result.put(vehicaledetService.getBanList(params));
	}
	
	/**
	 * 获取发动机
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCarEnList",method=RequestMethod.GET)
	public JsonResult getCarEnList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>"+params);
		return result.put(vehicaledetService.getCarEnList(params));
	}
	
	/**
	 * 获取变速箱
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCarGearList",method=RequestMethod.GET)
	public JsonResult getCarGearList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>"+params);
		return result.put(vehicaledetService.getCarGearList(params));
	}
	

	/**
	 * 获取车型结构
	 * @author:zhlu
	 * @date: 2018年3月21日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCarTypeList",method=RequestMethod.GET)
	public JsonResult getCarTypeList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>"+params);
		return result.put(vehicaledetService.getCarTypeList(params));
	}
}
