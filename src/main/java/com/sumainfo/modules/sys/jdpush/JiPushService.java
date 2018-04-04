package com.sumainfo.modules.sys.jdpush;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JiPushService {

    
   /* public static void main(String[] args) {
		Map<String, String> parm =new HashMap<String, String>();
        //这是我的文章id
        parm.put("id","1");
        //文章标题
        parm.put("Atitle","文章标题");
        //设置提示信息,内容是文章标题
        parm.put("msg","设置提示信息,内容是文章标题");
        System.err.println(jpushAndroid(parm));
	}*/
    
	private final static String appKey="86ddcbae96498798a08da6bb";
	private final  static String masterSecret="6915e62b6d7c7873f2880253";
  //极光推送>>Android
    //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
    public static int jpushAndroid(Map<String, String> parm) {
    	int result = 0;
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        //推送的关键,构造一个payload 
        PushPayload payload = PushPayload.newBuilder()
             .setPlatform(Platform.android())//指定android平台的用户
             .setAudience(Audience.all())//你项目中的所有用户
             .setNotification(Notification.android(parm.get("msg"), parm.get("title"), parm))
             //发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
             .setOptions(Options.newBuilder().setApnsProduction(false).build())
             /*//这里是指定开发环境,不用设置也没关系
             .setMessage(Message.content(parm.get("msg")))//自定义信息
*/             .build();

        try {
             PushResult pu = jpushClient.sendPush(payload);
             if(pu.getResponseCode()==200){
            	                  result=1;
             }

         } catch (APIConnectionException e) {
             e.printStackTrace();
         } catch (APIRequestException e) {
             e.printStackTrace();
         }  
        return result;
    }
    
  //极光推送>>ios
    //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
    public static  int jpushIOS(Map<String, String> parm) {
    	int result = 0;
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload = PushPayload.newBuilder()
             .setPlatform(Platform.ios())//ios平台的用户
             .setAudience(Audience.all())//所有用户
             .setNotification(Notification.newBuilder()
             .addPlatformNotification(IosNotification.newBuilder()
                             .setAlert(parm.get("msg"))
                             .setBadge(+1)
                             .setSound("happy")//这里是设置提示音(更多可以去官网看看)
                             .addExtras(parm)
                             .build())
                     .build())
             .setOptions(Options.newBuilder().setApnsProduction(false).build())
             .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
             .build();

        try {
             PushResult pu = jpushClient.sendPush(payload);
             if(pu.getResponseCode()==200){
                 result=1;
             }
         } catch (APIConnectionException e) {
             e.printStackTrace();
         } catch (APIRequestException e) {
             e.printStackTrace();
         }  
        return result;
    }
}
