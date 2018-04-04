package com.sumainfo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.sumainfo.common.utils.PageUtils;
import com.sumainfo.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典
* @Title: SysDictService.java 
* @Package com.sumainfo.modules.sys.service  
* @author zhlu
* @date 2018年3月15日
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

