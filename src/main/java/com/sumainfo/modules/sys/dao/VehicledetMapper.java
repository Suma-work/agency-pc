package com.sumainfo.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sumainfo.modules.sys.entity.Menu;

@Mapper
public interface VehicledetMapper {

	/**
	 * 根据店铺编号获取店铺的新车
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getVehiList(Map<String,Object>params);
	
	/**
	 * 根据店铺编号获取店铺
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	Map<String,Object>getShop(Map<String,Object>params);
	
	/**
	 * 根据店铺编号获取店铺新车总数
	 * @author:zhlu
	 * @date: 2018年3月26日
	 * @param params
	 * @return
	 */
	Integer getVehiCout(Map<String,Object>params);
	
	/**
	 * 根据汽车主键获取汽车
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	Map<String,Object>getVehiMap(Map<String,Object>params);
	
	/**
	 * 获取新车的图片
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getVePic(Map<String,Object>params);
	
	/**
	 * 获取新车的图片
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getVePics(Map<String,Object>params);
	
	
	/**
	 * 根据品牌名称获取品牌编号
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	Map<String,Object>getFcv(Map<String,Object>params);
	
	/**
	 * 根据车型名称获取车型编号
	 * @author:zhlu
	 * @date: 2018年3月27日
	 * @param params
	 * @return
	 */
	Map<String,Object>getSec(Map<String,Object>params);
	
	/**
	 * 修改新车信息
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @return
	 */
	Integer updateVehi(Map<String,Object>params);
	
	/**
	 * 新增新车
	 * @author:zhlu
	 * @date: 2018年3月28日
	 * @param params
	 * @return
	 */
	Integer addVehi(Map<String,Object>params);
	
	/**
	 * 新增新车和图片的中间表
	 * @author:zhlu
	 * @date: 2018年3月31日
	 * @param params
	 * @return
	 */
	Integer addVhiBan(Map<String,Object>params);
	
	/**
	 * 获取新车和图片中间表的主键
	 * @author:zhlu
	 * @date: 2018年3月31日
	 * @param params
	 * @return
	 */
	Map<String,Object> getVhiBan(Map<String,Object>params);
	
	/**
	 * 新车的图片信息表
	 * @author:zhlu
	 * @date: 2018年3月31日
	 * @param params
	 * @return
	 */
	Integer addVhiPic(Map<String,Object>params);
	
	/**
	 * 删除新车的图片信息表，修改的时候先删除
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月12日
	 */
	Integer deltctVhiPic(Map<String,Object>params);
	
//	/**
//	 * 删除新车
//	 * @author:zhlu
//	 * @date: 2018年3月31日
//	 * @param params
//	 * @return
//	 */
//	Integer deteleVehi(Map<String,Object>params);
	
	/**
	 * 获取子部门
	 * @author:zhlu
	 * @date: 2018年4月3日
	 * @param params
	 * @return
	 */
	 List<Menu> getDept(Map<String,Object>params);
	
	/**
	 * 获取部门里面的用户店铺编号
	 * @author:zhlu
	 * @date: 2018年4月3日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getUserShop(Map<String,Object>params);
	
	 /**
	  * 获取部门的店铺编号
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月11日
	  */
	 Map<String,Object>userShop(Map<String,Object>prams);
	 
	 /**
	  * 获取用户的店铺编号
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月11日
	  */
	 Map<String,Object>userAnShop(Map<String,Object>params);
	 
	 /**
	  * 店铺车型
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author zhlu
	 * @date 2018年4月12日
	  */
	 List<Map<String,Object>>getCarList(Map<String,Object>params);
}
