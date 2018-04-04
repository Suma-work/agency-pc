package com.sumainfo.modules.sys.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;








import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.JsonResult;
import com.sumainfo.modules.sys.jdpush.JiPushService;
import com.sumainfo.modules.sys.service.impl.JpushService;

@RestController
@RequestMapping("push")
public class pushController implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger log=LoggerFactory.getLogger(pushController.class);
	
	@Autowired
	JpushService jpushService;
	
	@RequestMapping(value="/getPush",method=RequestMethod.POST)
	public JsonResult getPush(@RequestBody Map<String,Object>params){
		JsonResult result=new JsonResult();
//		//推送编号
		String pushId=ComUtils.randomUID("pid");
		params.put("pushId",pushId);
//		//推送标题
//		params.put("pushTitle",ComUtils.randomUID("pid"));
//		//文章标题
//		params.put("pushAtitle",ComUtils.randomUID("pid"));
//		//内容
//		params.put("pushMsg",ComUtils.randomUID("pid"));
//		//推送端 0安卓 1苹果  2安卓和苹果
//		params.put("insterm",ComUtils.randomUID("pid"));
//		//创建人
//		params.put("insby",ComUtils.randomUID("pid"));
		
		log.info("params->>>>>>>>>"+params);
		if(params==null|| params.get("insterm")==null){
		 return result.putFailed("缺失推送端insterm,至少选择推送一个端！");
			
		}
		Map<String,String>push=new HashMap<String,String>();
		//推送编号
		push.put("id",pushId);
		//推送标题
		push.put("title",params.get("pushTitle").toString());
        //文章标题
		push.put("Atitle",params.get("pushAtitle").toString());
        //设置提示信息,内容是文章标题
		push.put("msg",params.get("pushMsg").toString());
		
		String type=params.get("insterm").toString();
		int code=0;
		if("1".equals(type)){
			code=JiPushService.jpushAndroid(push);
			if(code>0){
				if(jpushService.setJpush(params)){
					result.putSuccess("推送成功！");
				}else{
					result.putFailed("服务器繁忙,请稍后重试!");
				}
			}
		}else if("2".equals(type)){
			code=JiPushService.jpushAndroid(push);
			if(code>0){
				if(jpushService.setJpush(params)){
					result.putSuccess("推送成功！");
				}else{
					result.putFailed("服务器繁忙,请稍后重试!");
				}
			}
		}else if("3".equals(type)){
			code=JiPushService.jpushAndroid(push);
			code=JiPushService.jpushAndroid(push);
			if(code>0){
				if(jpushService.setJpush(params)){
					result.putSuccess("推送成功！");
				}else{
					result.putFailed("服务器繁忙,请稍后重试!");
				}
			}
		}
		return result;
	}
}
