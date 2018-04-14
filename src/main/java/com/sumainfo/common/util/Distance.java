package com.sumainfo.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
/**
 * 通过java api从高德地图获取经纬度
 *  address 位置
 *  output 返回结果格式
 *  key  高德key值，需申请
 */
public class Distance {
	
	 private static final String KEY = "4134d271e841795acd4133c81e320af5";  
	 private static final String OUTPUT = "JSON";  
	 private static final String GET_LNG_LAT_URL = "http://restapi.amap.com/v3/geocode/geo";
	 private static final Logger log=LoggerFactory.getLogger(Distance.class);
	 
	
	 /**
	  * 
	  * @Description: TODO(从高德地图获取经纬度) 
	  * @author zhlu
	  * @date 2018年4月11日
	  */
	 public static Map<String,Object> getLngLatFromOneAddr(Map<String,Object>params){
		 Map<String,Object>result=new HashMap<String,Object>();
		 if(params.get("address")==null) {  
	        return new HashMap<String,Object>();  
	     } 
		 String url = GET_LNG_LAT_URL+"?output="+OUTPUT+"&key="+KEY+"&address="+params.get("address");
		  AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
	        builder.setCompressionEnabled(true).setAllowPoolingConnection(true);
	        builder.setRequestTimeoutInMs((int) TimeUnit.MINUTES.toMillis(1));
	        builder.setIdleConnectionTimeoutInMs((int) TimeUnit.MINUTES.toMillis(1));
	        AsyncHttpClient client = new AsyncHttpClient(builder.build());
	        try {
	            ListenableFuture<Response> future = client.prepareGet(url).execute();
//	            String result = future.get().getResponseBody();
	            log.info("result->>>>>>"+result);
	            JsonNode jsonNode = new com.fasterxml.jackson.databind.ObjectMapper().readTree(future.get().getResponseBody());
	            if(jsonNode.findValue("status").textValue().equals("1")) {
	                JsonNode listSource = jsonNode.findValue("location");//经纬度
	                JsonNode province = jsonNode.findValue("province");//省份名
	                JsonNode city = jsonNode.findValue("city");//城市名
	                JsonNode district = jsonNode.findValue("district");//地址所在的区
	                JsonNode adcode = jsonNode.findValue("adcode");//区域编码
	                JsonNode formatted_address = jsonNode.findValue("formatted_address");//区域编码
	                log.info("listSource->>>>>>"+province+city+district+adcode+formatted_address);
	                if(listSource!=null){
	                	Map<String,Object>map=new HashMap<String,Object>();
	                	map.put("listSource", listSource);
	                	String[] a=map.get("listSource").toString().split(",");
	                	String logs=a[0].substring(1);
	                	String lat=a[1].substring(0,a[1].length()-1);
	                	result.put("formatted_address",formatted_address);//详细地址
	                	result.put("province",province);//省
	                	result.put("city",city);//市
	                	result.put("district",district);//区
	                	result.put("adcode",adcode);//区域编号
	                	result.put("log",logs);//经度
	                	result.put("lat",lat);//纬度
	                	log.info(logs+"---"+lat);
	                }else{
	                	return new HashMap<String,Object>();
	                }
//	                for(String location : listSource.textValue().split(",")){
//	                    //得到这个位置的经纬度
////	                    System.out.println(location);
//	                    log.info("经纬度location->>>>>>"+location);
//	                    
//	                    //System.out.println(Double.valueOf(location));
//	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if(client != null){
	                client.close();
	            }
	        }
		 return result;
	 }
	
//	 public static void main(String[] args) {
//	     Map<String,Object>map=new HashMap<String,Object>();
//	     map.put("address", "上海东校区");
//		Map<String,Object>result= Distance.getLngLatFromOneAddr(map);
//		 log.info("返回result-》》》"+result);
//	 }
}
