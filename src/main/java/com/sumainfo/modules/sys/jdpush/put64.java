package com.sumainfo.modules.sys.jdpush;
import java.io.IOException;

import com.qiniu.common.QiniuException;  
import com.qiniu.http.Response;  
import com.qiniu.storage.UploadManager;  
import com.qiniu.util.Auth;  
import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.ComUtils1;

import okhttp3.*;
public class put64 {
	//设置好账号的ACCESS_KEY和SECRET_KEY  
    String ACCESS_KEY = "J0_JMsxM_qkDVT_7HBen1sFyGuYCgeyxD5jbvWri"; //这两个登录七牛 账号里面可以找到  
    String SECRET_KEY = "xrTfxYSJUSiLtpkEta-hdzeKEUQQUMvGIGVD5WID";  

    //要上传的空间  
    String bucketname = "agency-pc"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）  
    
    //上传文件的路径  
    String FilePath = "D:\\1.jpg";  //本地要上传文件路径  

    //密钥配置  
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);  
    //创建上传对象  
    UploadManager uploadManager = new UploadManager();  

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
    public String getUpToken(){  
        return auth.uploadToken(bucketname);  
    }  
    //普通上传  
    public void upload() throws IOException{  
      try {  
    	//上传到七牛后保存的文件名  
    	String key = ComUtils1.randomUID()+".png";    
        //调用put方法上传  
        Response res = uploadManager.put(FilePath, key, getUpToken());  
        //打印返回的信息  
        System.out.println(res.bodyString());
        System.err.println("http://p6funux82.bkt.clouddn.com/"+key);
        } catch (QiniuException e) {  
            Response r = e.response;  
            // 请求失败时打印的异常的信息  
            System.out.println(r.toString());  
            try {  
                //响应的文本信息  
              System.out.println(r.bodyString());  
            } catch (QiniuException e1) {  
                //ignore  
            }  
        }         
    }  

    public static void main(String args[]) throws IOException{    
      new put64().upload();  
    }  
}
