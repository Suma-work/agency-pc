package com.sumainfo.common.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class push {
	public static void main(String[] args) {
		pushs();
	}
	
	private static void pushs(){
		String masterSecet="a58cd2403ec6a28d370ef5a6";
		String appKey="938530c89eb995e144f5c306";
		
		JPushClient jPushClient=new JPushClient(masterSecet, appKey);
		
		PushPayload payload=PushPayload.newBuilder()
				.setPlatform(Platform.all())//设置推送的端
				.setAudience(Audience.all())//设置谁可以接收
				.setNotification(Notification.alert("agency"))//设置通知
				.setMessage(Message.content("message"))
				.build();
		
		try {
			PushResult result=jPushClient.sendPush(payload);
			System.err.println("success");
			System.err.println(result.msg_id);
			System.err.println(result.sendno);
		} catch (APIConnectionException e) {
			System.err.println("connection error");
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.err.println("request error");
			e.printStackTrace();
		}
	}
}
