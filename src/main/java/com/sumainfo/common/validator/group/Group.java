package com.sumainfo.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
* @Title: Group.java 
* @Package com.sumainfo.common.validator.group  
* @author zhlu
* @date 2018年3月15日
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
