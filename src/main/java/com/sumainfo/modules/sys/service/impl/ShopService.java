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
		}else if(deptName.equals("商家店长")){//商家店长，就要按照父节点查所在大区
			newList.add(getDeptMap.get("parentid").toString());
			params.put("deptidList", newList);
		}else if(deptName.equals("商家店员")){//商家店员，就不查询
			return result;
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
		}else if(deptName.equals("商家店长")){//商家店长，就要按照父节点查所在大区
			newList.add(getDeptMap.get("parentid").toString());
			params.put("deptidList", newList);
		}else if(deptName.equals("商家店员")){//商家店员，就不查询
			return 0;
		}
		return shopMapper.getShopListCout(params);
	}
	
	public Map<String,Object>getShop(Map<String,Object>params){
		
		return null;
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
}
