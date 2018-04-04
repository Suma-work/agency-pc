package com.sumainfo.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UploadPicUntil {
	static Logger logger = LoggerFactory.getLogger(UploadPicUntil.class);
	
	/**
	 * 获取图片上传至服务器
	 * @param is
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String uploadPic(InputStream is,String filePath,String fileName) throws Exception{
//		String fileName = System.currentTimeMillis()+".jpg";
		OutputStream  os = null;
		try {
			File tempFile = new File(filePath);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			String fileTmp = tempFile.getPath() + File.separator + fileName;
			File file = new File(fileTmp);
			while (file.exists()) {
				fileName = getRandom(6)+fileName;
				fileTmp = tempFile.getPath() + File.separator + fileName;
				file = new File(fileTmp);
			}
			os= new FileOutputStream(file.getPath());
			// 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
			int len;
			while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				if(os!=null) {
					os.close();
				}
			} catch (IOException e) {
            	e.printStackTrace();
            }   
		}
		return fileName;
	}
	
	
	/**
	 * 获取服务器上图片的地址下载上传到服务器
	 * @author dYang
	 * @param netUrl
	 * @param urlPath
	 * @return
	 * @throws IOException
	 */
	public static String upLoadPicsByUrl(String netUrl,String urlPath) throws IOException{
		String fileName = System.currentTimeMillis()+".jpg";//下载的文件命名 netUrl.substring(netUrl.length()-4, netUrl.length())
		urlPath = "D:\\testPicture\\";//下载到的本地地址
		 // 输入流    
	    InputStream is = null;
	     // 输出流    
	    OutputStream os = null;
		 // 构造URL    
	    URL url;
		try {
			url = new URL(netUrl);
			  // 打开连接    
		    URLConnection con = url.openConnection();    
		    //设置请求超时为5s    
		    con.setConnectTimeout(5*1000);    
		    // 输入流    
		    is = con.getInputStream();    
		    logger.info("网络图片地址---------------------------"+netUrl);
		    // 1K的数据缓冲    
		    byte[] bs = new byte[1024];    
		    // 读取到的数据长度    
		    int len;    
		    // 输出的文件流    
		   File sf=new File(urlPath);  
		   if(!sf.exists()){    
		       sf.mkdirs();    
		   }
		   String fileTmp = sf.getPath() + File.separator + fileName;
			File file = new File(fileTmp);
			while (file.exists()) {
				fileName = getRandom(6)+fileName;
				fileTmp = sf.getPath() + File.separator + fileName;
				file = new File(fileTmp);
			}
			 logger.info("服务器地址---------------------------"+fileTmp);
		      os = new FileOutputStream(file.getPath());   //+"\\"+fileName 
		    // 开始读取    
		    while ((len = is.read(bs)) != -1) {    
		      os.write(bs, 0, len);    
		    }  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 完毕，关闭所有链接    
		    os.close();    
		    is.close();    
		}
	    return fileName;
		
	}
	
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static String getRandom(int n) {
		String num = "";
		for (int i = 0 ; i < n ; i ++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
	}


}
