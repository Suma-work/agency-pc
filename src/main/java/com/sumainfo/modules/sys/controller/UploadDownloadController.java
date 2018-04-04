package com.sumainfo.modules.sys.controller;
import com.alibaba.fastjson.JSONObject;  
import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.JsonResult;
import com.sumainfo.common.util.ToolsUntil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.web.multipart.MultipartFile;  

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import java.io.*; 
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/uplo")
public class UploadDownloadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);  
    
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)  
	public JsonResult uopl(@RequestParam(value = "file") MultipartFile file){
		JsonResult result=new JsonResult();
		if (file.isEmpty()) {  
	       return result.putFailed("图片不能为空！");
	    } 
		// 获取文件名  
        String fileName = file.getOriginalFilename();  
        logger.info("上传的文件名为：" + fileName);  
        // 获取文件的后缀名  
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  
        logger.info("上传的后缀名为：" + suffixName);  
        // 文件上传后的路径  
        InputStream is;
        System.err.println();
//        String filePath = "D:\\";  
        String filePath = "C://Users//Administrator//Desktop//tomcat//apache-tomcat-8.5.24-windows-x64//apache-tomcat-8.5.24//webapps//image//";
        // 解决中文问题，liunx下中文路径，图片显示问题  
        // fileName = UUID.randomUUID() + suffixName;  
//        File dest = new File(filePath + fileName);  
        File dest = new File(filePath + fileName);  
        // 检测是否存在目录  
        if (!dest.getParentFile().exists()) {  
            dest.getParentFile().mkdirs();  
        }  
        try {  
            file.transferTo(dest);  
            logger.info("上传成功后的文件路径未：" + filePath + fileName);  
            return result.putSuccess(fileName);  
        } catch (IllegalStateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result.putFailed("上传失败！"); 
	}
	
	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)  
	public JsonResult uopls(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "file")MultipartFile[] file){
		JsonResult result=new JsonResult();
		if (file.length<0) {  
	       return result.putFailed("图片不能为空！");
	    } 
		for(int i=0;i<file.length;i++){
			// 获取文件名  
			String fileName = file[i].getOriginalFilename();
			logger.info("上传的文件名为：" + fileName); 
			// 获取文件的后缀名  
			String suffixName = fileName.substring(fileName.lastIndexOf("."));  
			logger.info("上传的后缀名为：" + suffixName); 
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(ComUtils.randomUID("img"))
					+ "." + suffixName;
			// 文件上传后的路径  
        String filePath = "D:\\";  
//			String filePath = ToolsUntil.PATH_URL;
			// 解决中文问题，liunx下中文路径，图片显示问题  
			// fileName = UUID.randomUUID() + suffixName;  
//        File dest = new File(filePath + fileName);  
			File dest = new File(filePath + newFileName);  
			// 检测是否存在目录  
			if (!dest.getParentFile().exists()) {  
				dest.getParentFile().mkdirs();  
			}  
			try {  
				file[i].transferTo(dest);  
				logger.info("上传成功后的文件路径未：" +ToolsUntil.PICTURE_URL+newFileName);  
				return result.putSuccess(fileName);  
			} catch (IllegalStateException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
        return result.putFailed("上传失败！"); 
	}
    
}
