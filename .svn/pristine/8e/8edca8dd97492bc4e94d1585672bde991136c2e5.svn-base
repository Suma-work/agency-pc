package com.sumainfo.common.validator;

//import io.renren.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

import com.sumainfo.common.exception.RRException;

/**
 * 数据校验
* @Title: Assert.java 
* @Package com.sumainfo.common.validator  
* @author zhlu
* @date 2018年3月15日
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
