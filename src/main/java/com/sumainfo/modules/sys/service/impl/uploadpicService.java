package com.sumainfo.modules.sys.service.impl;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.MessageUntil;
import com.sumainfo.common.util.ToolsUntil;
import com.sumainfo.common.util.UploadPicUntil;
import com.sumainfo.modules.sys.dao.VehicledetMapper;


@Service
public class uploadpicService implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(uploadpicService.class);
	
	@Autowired
	VehicledetMapper vehicledetMapper;
	
//	public MessageUntil<String> updateVehi(HttpServletRequest request, MultipartFile[] files){
//		log.info("updateVehi");
//		MessageUntil<String> mu = new MessageUntil<String>();
//		//添加新车资料
//		Map<String,Object>params1=new HashMap<String,Object>();
//		params1.put("bandName", request.getParameter("bandName"));//汽车品牌名称
//		params1.put("carName", request.getParameter("carName"));//品牌的子系名称
//		params1.put("carDetName", request.getParameter("carDetName"));//车辆的具体名称
//		params1.put("sellPrice", request.getParameter("sellPrice"));//汽车当前售价
//		params1.put("orginPrice", request.getParameter("orginPrice"));//汽车原始售价
//		params1.put("carEngine", request.getParameter("carEngine"));//发动机
//		params1.put("gearbox", request.getParameter("gearbox"));//变速箱
//		params1.put("carType", request.getParameter("carType"));//车体结构
//		params1.put("isLicence", request.getParameter("isLicence"));//是否上牌 0否1是
//		params1.put("createTime",ConvertDateTime.getCurrentTime());//创建时间
//		params1.put("shopId", request.getParameter("shopId"));//店铺主键
//		
//		
//		//添加图片和新车的中间表
//		Map<String,Object>params2=new HashMap<String,Object>();
//		params2.put("carName", request.getParameter("carName"));//品牌的子系名称
//		params2.put("shopId", request.getParameter("shopId"));//店铺主键
//		params2.put("classify", 1);//平台和其他店铺的区分 0平台 1 4s店 
//		
//		//下标
//		String index ="1";
//		Integer indexNum =null;
//		if(!index.equals("")&&null!=index){
//			indexNum = Integer.valueOf(index);
//		}
//		
//		//添加图片表
//		ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		List<String> picList = new ArrayList<String>();
//		for(int i=0;i<files.length;i++){
//			Map<String,Object>imgMap=new HashMap<String,Object>();
//			InputStream is;
//			try{
//				is = files[i].getInputStream();
//			    String pic = UploadPicUntil.uploadPic(is,ToolsUntil.PATH_URL,files[i].getOriginalFilename());
//			    String url = ToolsUntil.PICTURE_URL+pic;
//			    imgMap.put("picAddress", url);//图片地址
////			    imgMap.put("picAddress", url);//图片地址
//			    imgMap.put("createTime", ConvertDateTime.getCurrentTime());//创建时间
//			    if(indexNum !=null){
//			    	if(i>=indexNum){
//			    		//详情图片
//			    		picList.add(url);
//			    	}else if(i==0){
//			    		//展示图片
//			    		imgMap.put("isShow", 1);//图片地址
//				    }else{
//				    	//轮播图
//				    	imgMap.put("isShow", 0);//图片地址
//				    }
//			    }else{
//			    	if(i==0){
//			    		imgMap.put("isShow", 1);//图片地址
//				    }else{
//				    	imgMap.put("isShow",0);//图片地址
//				    }
//			    }
//			    list.add(imgMap);
//			    log.info("图片上传:"+imgMap);
//			}catch (Exception e) {
//				mu.setMessageCode("2");
//				log.info("图片上传失败:"+e);
//			}
//		 }
//		int num = 0;
//		boolean result=false;
////		log.info("params1->>>>>>>>>>>>"+params1);
////		//新车资料
////		try {
////			num = vehicledetMapper.addVehi(params1);
////			log.info("新车:"+num);
////			if(num>0){
////				result=true;
////			}
////		} catch (Exception e) {
////			mu.setMessageCode("2");
////			log.info("保存失败:"+e);
////		}
////		
////		//新车和图片中间表
////		log.info("params2->>>>>>>>>>>>"+params2);
////		try {
////			num = vehicledetMapper.addVhiBan(params2);
////			log.info("新车和图片中间表:"+num);
////			if(num>0){
////				result=true;
////				Map<String,Object>vhiBan=vehicledetMapper.getVhiBan(params2);
////				for (Map<String,Object> picMap : list) {//赋值关联id',
////					picMap.put("vehId", vhiBan.get("vehId"));
////				}
////			}
////		} catch (Exception e) {
////			mu.setMessageCode("2");
////			log.info("保存失败:"+e);
////		}
//			
//		try {
//			for (Map<String,Object> picGet : list) {
//				log.info("picGet->>>>>>>>>>>>"+picGet);
//				picGet.put("vehId", 50);
//				num = vehicledetMapper.addVhiPic(picGet);
//				log.info("图片表:"+num);
//				if(num>0){
//					result=true;
//				}
//			}
//		} catch (Exception e) {
//			log.info("保存图片信息失败:"+e);
//			mu.setMessageCode("2");
//			mu.setMessageStr("行车信息保存出错，请稍后重试");
////			vehicledetMapper.deteleVehi();
//		}
//		
//		return mu;
//	}
	
	public MessageUntil<String> updateVehis(HttpServletRequest request, MultipartFile[] files){
		log.info("updateVehis");
		MessageUntil<String> mu = new MessageUntil<String>();
		
		List<String> picList = new ArrayList<String>();
		for(int i=0;i<files.length;i++){
			InputStream is;
			try{
				is = files[i].getInputStream();
			    String pic = UploadPicUntil.uploadPic(is,ToolsUntil.PATH_URL,files[i].getOriginalFilename());
			    String url = ToolsUntil.PICTURE_URL+pic;
			    		//详情图片
				    	picList.add(url);
			}catch (Exception e) {
				mu.setMessageCode("2");
				log.info("图片上传失败:"+e);
			}
		 }
		return mu;
	}
	
}
