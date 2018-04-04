package com.sumainfo.common.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.reflect.TypeToken;

public class ObjectToJsonUntil {
public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/**
	 * @MethodName toJson
	 * @Description 将对象转为JSON字符串
	 * @param obj
	 * 被转化对象
	 * @return JSON字符串
	 */
	public static String toJson(Object obj) {
		
		if(obj == null) {
			return gson.toJson(JsonNull.INSTANCE);
		}
		return gson.toJson(obj);
	}
	
	/**
	 * @param<T> 类
	 * @param jsonText jsonText
	 * @param classOfT classOfT
	 * @return T
	 */
	public static <T> T str2Object(String jsonText, Class<T> classOfT) {
		if(jsonText == null) {
			return null;
		}
		return gson.fromJson(jsonText, classOfT);
	}
	
	/**
	 * @param <T>类
	 * @param jsonText jsonText
	 * @return T
	 */
	public static <T> List<T> str2ListObject(String jsonText){
		if(jsonText == null) {
			return null;
		}
		return gson.fromJson(jsonText,new TypeToken<List<T>>() {}.getType());
	}
	
	

}
