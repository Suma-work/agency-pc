package com.sumainfo.modules.sys.controller;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.JsonResult;
import com.sumainfo.common.util.MessageUntil;
import com.sumainfo.common.util.ObjectToJsonUntil;
import com.sumainfo.modules.sys.service.impl.uploadpicService;

@RestController
@RequestMapping("uplo")
public class uploadpicController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(uploadpicController.class);
	
	@Autowired
	uploadpicService uploadpicService;
	
	@RequestMapping(value="/getUplo",method=RequestMethod.POST)
	public void getUpload(HttpServletRequest request,HttpServletResponse response,MultipartFile[] files){
		log.info("request->>>>>>>>>>>>>>>>>"+request);
		log.info("request->>>>>>>>>>>>>>>>>"+files);
		MessageUntil<String> res = uploadpicService.updateVehi(request, files);
		/*try {
			String resu = ObjectToJsonUntil.toJson(res);
			log.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
	}
	
	/**
	 * 二手车信息发布需要带token
	 * @param request
	 * @return
	 */
	@PostMapping("/usedVehicleSellsMessage.do")
	public void findVehicleOrderMessage(HttpServletRequest request,HttpServletResponse response,MultipartFile[] files){
		MessageUntil<String> res =  uploadpicService.updateVehis(request, files);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			log.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
