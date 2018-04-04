package com.sumainfo.common.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

public class ComUtils {
	
	public static Map<String,String> unicodeParamstr2Map(String str){
		String paramstr = StringEscapeUtils.unescapeJava(str);
		Map<String, String> result = new LinkedHashMap<String, String>();
	    String[] pairs = paramstr.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        if (idx >= 0){
	        	result.put(pair.substring(0, idx), pair.substring(idx + 1));
	        }else{
	        	result.put(pair, null);
	        }
	    }
	    return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList randomList(ArrayList<Map<String, Object>> sourceList){ 
		if (isEmpty(sourceList)) {
			return sourceList; 
		} 
		ArrayList randomList = new ArrayList(sourceList.size()); 
		do{ 
			int randomIndex = Math.abs(new Random().nextInt(sourceList.size())); 
			randomList.add(sourceList.remove(randomIndex)); 
		}while(sourceList.size() > 0); 
		return randomList; 
	}
	
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 最简单最基本的方法 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */  
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(ArrayList sourceList) { 
		 return (sourceList == null || sourceList.size() == 0);
	}
	
	public static UUID byte2uuid(byte[] bytes){
		ByteBuffer bb = ByteBuffer.wrap(bytes);
	    long firstLong = bb.getLong();
	    long secondLong = bb.getLong();
	    return new UUID(firstLong, secondLong);

	}
	public static byte[] uuid2byte(UUID uuid){
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	public static UUID hexstr2uuid(String hexstr){
		// Use regex to format the hex string by inserting hyphens in the canonical format: 8-4-4-4-12
    	if(StringUtils.isEmpty(hexstr)){
    		return null;
    	}
		String hexStringWithInsertedHyphens =  hexstr.replaceFirst( "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5" );
		java.util.UUID uuid = java.util.UUID.fromString(hexStringWithInsertedHyphens);
		return uuid;
    }
    
    public static String hexstr2str(String hexstr){
		// Use regex to format the hex string by inserting hyphens in the canonical format: 8-4-4-4-12
    	if(StringUtils.isEmpty(hexstr)){
    		return null;
    	}
		String hexStringWithInsertedHyphens =  hexstr.replaceFirst("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5");
		return hexStringWithInsertedHyphens;
    }
    public static String randomUID(){
    	return randomUID("");
    }
    
    public static String randomUID(String prefix){
    	int length = 32;
    	String prefixStr = "";
    	if (prefix == null || "".equals(prefix)){
    		prefixStr = "";
    	}else{
    		length = length - prefix.length() - 1;
    		prefixStr = prefix.toUpperCase() + "_";
    	}
    	
		String uuid = prefixStr + RandomStringUtils.random(length,"_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890");
		return uuid;
    }
    public static String randomAlphanumUID(){
    	return randomAlphanumUID("");
    }
    public static String randomAlphanumUID(String prefix){
    	int length = 32;
    	String prefixStr = "";
    	if (prefix == null || "".equals(prefix)){
    		prefixStr = "";
    	}else{
    		length = length - prefix.length() - 1;
    		prefixStr = prefix.toUpperCase() + "_";
    	}
		String uuid = prefixStr + RandomStringUtils.random(length,"ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
		return uuid;
    }
    public static String randomNumUID(){
    	int length = 8;
    	String prefixStr = "";
		String uuid = prefixStr + RandomStringUtils.random(length,"ABCD1234567890");
		return uuid;
    }
    
//    public static void main(String[] args) {
//    	System.err.println(ComUtils.randomUID("re"));
//	}
}
