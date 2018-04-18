package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sumainfo.common.util.ComUtils;
import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.MenuRecursion;
import com.sumainfo.modules.sys.dao.ShopMapper;
import com.sumainfo.modules.sys.dao.VehicledetMapper;
import com.sumainfo.modules.sys.entity.Menu;

@Service
public class ShopService implements Serializable {

	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	ShopMapper shopMapper;

	@Autowired
	VehicledetMapper vehicledetMapper;

	public List<Map<String, Object>> getShopList(Map<String, Object> params) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(StringUtils.isEmpty(params.get("loginDeptid"))){
			List<Map<String, Object>> shopList = shopMapper.getShopList(params);
			return shopList;
		}
		//获取当前登录的部门
		Map<String,Object>getDeptMap=shopMapper.getDeptMap(params);
		String deptName=getDeptMap.get("name").toString();
		if(deptName.lastIndexOf("-")!=-1){
			deptName= deptName.substring(0, deptName.lastIndexOf("-"));
		}
		List<String> newList = new ArrayList<String>();
		if(deptName.equals("集团老总")){//集团老总，找当前集团下面的所有大区
			params.put("pareid", getDeptMap.get("deptid"));
			List<Menu> deptList = shopMapper.getDept(params);//就获取下面的所有子部门
			int dept = Integer.valueOf(getDeptMap.get("deptid").toString());
			List<Menu> tree = MenuRecursion.treeMenuList(deptList, dept);
			List<String> deptidList = new ArrayList<String>();// 子部门编号
			for (Menu m : tree) {
				deptidList.add(m.getId());
			}
			// 去重复子部门
			for (String cd : deptidList) {
				if (!newList.contains(cd)) {
					newList.add(cd);
				}
			}
			params.put("deptidList", newList);
		}else if(deptName.equals("大区经理")){//大区经理，直接找当前的大区
			newList.add(getDeptMap.get("deptid").toString());
			params.put("deptidList", newList);
		}else if(deptName.equals("商家店长")){//商家店长，就获取登录店长的店铺编号直接按店铺编号查询
			Map<String,Object>userShopId=shopMapper.getUserShopId(params);
			params.put("shopId", userShopId.get("shopid"));
		}else if(deptName.equals("商家店员")){//商家店员，就获取登录店员的店铺编号直接按店铺编号查询
			Map<String,Object>userShopId=shopMapper.getUserShopId(params);
			params.put("shopId", userShopId.get("shopid"));
		}
		List<Map<String, Object>> shopList = shopMapper.getShopList(params);
		return shopList;
	}

	public Integer getShopListCout(Map<String, Object> params) {
		if(StringUtils.isEmpty(params.get("loginDeptid"))){
			return shopMapper.getShopListCout(params);
		}
		//获取当前登录的部门
		Map<String,Object>getDeptMap=shopMapper.getDeptMap(params);
		String deptName=getDeptMap.get("name").toString();
		if(deptName.lastIndexOf("-")!=-1){
			deptName= deptName.substring(0, deptName.lastIndexOf("-"));
		}
		List<String> newList = new ArrayList<String>();
		if(deptName.equals("集团老总")){//集团老总，找当前集团下面的所有大区
			params.put("pareid", getDeptMap.get("deptid"));
			List<Menu> deptList = shopMapper.getDept(params);//就获取下面的所有子部门
			int dept = Integer.valueOf(getDeptMap.get("deptid").toString());
			List<Menu> tree = MenuRecursion.treeMenuList(deptList, dept);
			List<String> deptidList = new ArrayList<String>();// 子部门编号
			for (Menu m : tree) {
				deptidList.add(m.getId());
			}
			// 去重复子部门
			for (String cd : deptidList) {
				if (!newList.contains(cd)) {
					newList.add(cd);
				}
			}
			params.put("deptidList", newList);
		}else if(deptName.equals("大区经理")){//大区经理，直接找当前的大区
			newList.add(getDeptMap.get("deptid").toString());
			params.put("deptidList", newList);
		}else if(deptName.equals("商家店长")){//商家店长，就获取登录店长的店铺编号直接按店铺编号查询
			Map<String,Object>userShopId=shopMapper.getUserShopId(params);
			params.put("shopId", userShopId.get("shopid"));
		}else if(deptName.equals("商家店员")){//商家店员，就获取登录店员的店铺编号直接按店铺编号查询
			Map<String,Object>userShopId=shopMapper.getUserShopId(params);
			params.put("shopId", userShopId.get("shopid"));
		}
		return shopMapper.getShopListCout(params);
	}
	
	/**
	 * 获取店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月11日
	 */
	public Map<String,Object>getShop(Map<String,Object>params){
		Map<String,Object>shop=shopMapper.getShop(params);
		if(shop==null || shop.size()<1){
			return new HashMap<String,Object>();
		}
		params.put("associationId", shop.get("shopId"));
		List<String>pic=new ArrayList<String>();
		List<Map<String,Object>>shopPic=shopMapper.getShopPic(params);
		if(shopPic==null || shopPic.size()<1){
			shop.put("imgs", new ArrayList<Map<String,Object>>());
		}else{
			for (Map<String, Object> map : shopPic) {
				if(map.get("picAddress")!=null){
					pic.add(map.get("picAddress").toString());
				}
			}
			shop.put("imgs", pic);
		}
		return shop;
	}
	
	/**
	 * 获取大区的部门
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月10日
	 */
	public List<Map<String,Object>>getDeptRegList(Map<String,Object>params){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		
		//获取当前登录的部门
		Map<String,Object>getDeptMap=shopMapper.getDeptMap(params);
		String deptName=getDeptMap.get("name").toString();
		if(deptName.lastIndexOf("-")!=-1){
			deptName= deptName.substring(0, deptName.lastIndexOf("-"));
		}
		if(deptName.equals("集团老总")){//集团老总，找当前集团下面的所有大区
			
		}else if(deptName.equals("大区经理")){//大区经理，直接找当前的大区
			params.put("parentid", getDeptMap.get("parentid"));
		}else if(deptName.equals("商家店长")){//商家店长，就要按照父节点查所在大区
			params.put("deptid", getDeptMap.get("parentid"));
		}else if(deptName.equals("商家店员")){//商家店员，就不查询
			return result;
		}
		
		List<Map<String,Object>>getDept=shopMapper.getDeptList(params);
		for (Map<String, Object> map : getDept) {
			Map<String,Object>dept=new HashMap<String,Object>();
			//获取父节点
			System.err.println(map.get("parentid"));
			params.put("parentid", map.get("parentid"));
			Map<String,Object>parentDept=shopMapper.getDeptParntMap(params);
			
			dept.put("id",map.get("deptid"));
			String pareName=parentDept.get("name").toString();
			String deptNames=map.get("name").toString();
			dept.put("value",pareName.substring(pareName.indexOf("-")+1)+"-"+deptNames.substring(deptNames.indexOf("-")+1));
			result.add(dept);
		}
		return result;
	}
	
	/**
	 * 添加店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月13日
	 */
	public boolean setShop(Map<String,Object>params){
		boolean result = false;
		params.put("shopId",ComUtils.randomUID("shop"));
		params.put("createTime", ConvertDateTime.getCurrentTime());
		log.info("params->>>>>>>>>"+params);
		List<String>pic=(List<String>) params.get("imgs");//获取图片数组
		try {
			for (int i = 0; i < pic.size(); i++) {
				Map<String, Object>picMap=new HashMap<String, Object>();
				picMap.put("classify",params.get("classify"));
				picMap.put("associationId",params.get("shopId"));
				picMap.put("picAddress",pic.get(i));
				picMap.put("createTime",params.get("createTime"));
				if(i==0){//第一张图片是展示图片
					picMap.put("slideshow",2);
				}else{
					picMap.put("slideshow",1);
				}//其他图片是轮播图
				picMap.put("delfg",params.get("delfg"));
				shopMapper.setShopPic(picMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int shop=shopMapper.setShop(params);
			if(shop>0){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 编辑店铺信息
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月13日
	 */
	public boolean updateShop(Map<String,Object>params){
		boolean result=false;
		params.put("createTime", ConvertDateTime.getCurrentTime());
		//修改图片的时候不管修改没有修改，先全部删除。在新增
		Map<String,Object>picPama=new HashMap<String,Object>();
		picPama.put("associationId",params.get("shopId"));
		List<Map<String,Object>>picParams=shopMapper.shopPicList(picPama);//查询所有的图片
		List<Map<String,Object>>picList=new ArrayList<Map<String,Object>>();//存放图片的资料
		try {
			for (Map<String, Object> map : picParams) {
				//只获获取状态为1和2的图片。进行删除
				if(map.get("slideshow").toString().equals("1")||map.get("slideshow").toString().equals("2")){
					//新增资料
					Map<String,Object>picMap=new HashMap<String,Object>();
					picMap.put("classify", map.get("classify"));
					picMap.put("associationId", map.get("associationId"));
					picMap.put("createTime", map.get("createTime"));
					picList.add(picMap);
					
					//删除资料
					Map<String,Object>picMapDel=new HashMap<String,Object>();
					picMap.put("associationId", map.get("associationId"));
					picMap.put("slideshow", map.get("slideshow"));
					
					shopMapper.delectShopPic(picMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String>pic=(List<String>) params.get("imgs");
		try {
			for (int i = 0; i < pic.size(); i++) {//新增图片
				Map<String,Object>picMap=new HashMap<String,Object>();
				if(i==0){
					picList.get(i).put("slideshow",2);
				}else{
					if(i>picList.size()-1){
						picMap.put("slideshow",1);
					}else{
						picList.get(i).put("slideshow",1);
					}
				}
				if(picList.size()-1<i){
					picMap.put("classify", params.get("classify"));
					picMap.put("associationId", params.get("shopId"));
					picMap.put("picAddress",pic.get(i));
					picMap.put("createTime", ConvertDateTime.getCurrentTime());
					shopMapper.setShopPic(picMap);//新的图片
				}else{
					picList.get(i).put("picAddress",pic.get(i));//把原来的数据拿过来新增进去
					shopMapper.setShopPic(picList.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(shopMapper.updateShop(params)>0){
			result=true;
		}
		return result;
	}
	
	/**
	 * 删除店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月18日
	 */
	public boolean delectShop(Map<String,Object>params){
		boolean result=false;
		try {
			int deleshop=shopMapper.delectShop(params);
			if(deleshop>0){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
