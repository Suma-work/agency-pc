package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sumainfo.modules.sys.entity.Menu;

@Mapper
public interface ShopMapper {

	/**
	 * 获取店铺信息
	 * @param prams
	 * @return
	 */
	List<Map<String,Object>>getShopList(Map<String,Object>params);
	
	/**
	 * 获取某一个店铺
	 * @param params
	 * @return
	 */
	Map<String,Object>getShop(Map<String,Object>params);
	
	/**
	 * 获取店铺总数
	 * @param params
	 * @return
	 */
	Integer getShopListCout(Map<String,Object>params);
	
	/**
	 * 获取子部门
	 * @author:zhlu
	 * @date: 2018年4月3日
	 * @param params
	 * @return
	 */
	 List<Menu> getDept(Map<String,Object>params);
	 
	 /**
	  * 获取大区的部门
	  * @param parmas
	  * @return
	  */
	 List<Map<String,Object>>getDeptList(Map<String,Object>params);
	 
	 /**
	  * 获取父部门
	  * @param params
	  * @return
	  */
	 Map<String,Object>getDeptParntMap(Map<String,Object>params);
	 
	 /**
	  * 获取当前的部门
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月10日
	  */
	 Map<String,Object>getDeptMap(Map<String,Object>params);
	 
	 /**
	  * 获取店铺的图片
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月11日
	  */
	 List<Map<String,Object>>getShopPic(Map<String,Object>params);
	 
	 /**
	  * 新增店铺
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月13日
	  */
	 Integer setShop(Map<String,Object>params);
	 
	 /**
	  * 新增店铺的图片表
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月13日
	  */
	 Integer setShopPic(Map<String,Object>params);
	 
	 /**
	  * 修改店铺信息
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月13日
	  */
	 Integer updateShop(Map<String,Object>params);
	 
	 /**
	  * 获取图片资料
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月13日
	  */
	 List<Map<String,Object>>shopPicList(Map<String,Object>params);
	 
	 /**
	  * 修改图片资料的时候，先删除图片信息,然后新增图片
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月13日
	  */
	 Integer delectShopPic(Map<String,Object>params);
	 
	 
}
