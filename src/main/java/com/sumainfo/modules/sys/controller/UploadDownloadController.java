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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.ResponseBody;
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
	private static final Logger log = LoggerFactory.getLogger(UploadDownloadController.class);  
    
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)  
	public JsonResult uopl(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		JsonResult result=new JsonResult();
		if (file.isEmpty()) {  
	       return result.putFailed("图片不能为空！");
	    } 
		log.info("request->>>>>>"+request.getParameter("aaa"));
		// 获取文件名  
        String fileName = file.getOriginalFilename();  
        log.info("上传的文件名为：" + fileName);  
        // 获取文件的后缀名  
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  
        log.info("上传的后缀名为：" + suffixName);  
        // 文件上传后的路径  
        InputStream is;
        System.err.println();
        String filePath = "D:\\";  
//        String filePath = "C://Users//Administrator//Desktop//tomcat//apache-tomcat-8.5.24-windows-x64//apache-tomcat-8.5.24//webapps//image//";
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
            log.info("上传成功后的文件路径未：" + filePath + fileName);  
            response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
            return result.putSuccess(fileName);  
        } catch (IllegalStateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result.putFailed("上传失败！"); 
	}
	
	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)  
	public JsonResult uopls(@RequestParam(value = "file")MultipartFile[] file){
		JsonResult result=new JsonResult();
		Map<String,Object>resultMap=new HashMap<String,Object>();
		if (file.length<0) {  
	       return result.putFailed("图片不能为空！");
	    } 
//		log.info("bandName->>>>>>"+request.getParameter("bandName"));
		log.info("file->>>>>>"+file.length);
		for(int i=0;i<file.length;i++){
			// 获取文件名  
			String fileName = file[i].getOriginalFilename();
			log.info("上传的文件名为：" + fileName); 
			// 获取文件的后缀名  
			String suffixName = fileName.substring(fileName.lastIndexOf("."));  
			log.info("上传的后缀名为：" + suffixName); 
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(ComUtils.randomUID("img"))
					 + suffixName;
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
				String url = ToolsUntil.PICTURE_URL+newFileName;
				log.info("上传成功后的文件路径未：" +url);  
				resultMap.put("pictureUrl",url);//图片地址
				resultMap.put("picName",newFileName);//图片地址
				return result.put(resultMap);
			} catch (IllegalStateException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
        return result.putFailed("上传失败！"); 
	}
	
	/**
	 * 根据名字删除图片
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月12日
	 */
	@ResponseBody
	@RequestMapping(value="/deleteImages",method=RequestMethod.GET)
    public JsonResult getDelete(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		log.info("params->>>>>>>>>>"+params);
		File file=new File(ToolsUntil.PATH_URL+params.get("picName").toString());
        if (file.exists()) {
           file.delete();//如果文件存在 则删除该文件
           result.putSuccess("删除图片成功！");
        }else{
           result.putFailed("删除失败，不存在图片！");
        }
		return result;
    }
	
}
