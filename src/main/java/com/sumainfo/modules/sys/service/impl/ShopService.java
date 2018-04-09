package com.sumainfo.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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

		// 获取登录用户下的子部门
		if (!StringUtils.isEmpty(params.get("deptid"))) {
			List<Menu> deptList = shopMapper.getDept(params);
			int dept = Integer.valueOf(params.get("deptid").toString());
			System.err.println(dept);
			List<Menu> tree = MenuRecursion.treeMenuList(deptList, dept);
			List<String> deptidList = new ArrayList<String>();// 子部门编号
			for (Menu m : tree) {
				deptidList.add(m.getId());
			}
			// 去重复子部门
			List<String> newList = new ArrayList<String>();
			for (String cd : deptidList) {
				if (!newList.contains(cd)) {
					newList.add(cd);
				}
			}
			params.put("deptidList", newList);
		}
		List<Map<String, Object>> shopList = shopMapper.getShopList(params);
		return shopList;
	}

	public Integer getShopListCout(Map<String, Object> params) {
		// 获取登录用户下的子部门
		if (!StringUtils.isEmpty(params.get("deptid"))) {
			List<Menu> deptList = shopMapper.getDept(params);
			int dept = Integer.valueOf(params.get("deptid").toString());
			List<Menu> tree = MenuRecursion.treeMenuList(deptList, dept);
			List<String> deptidList = new ArrayList<String>();// 子部门编号
			for (Menu m : tree) {
				deptidList.add(m.getId());
			}
			// 去重复子部门
			List<String> newList = new ArrayList<String>();
			for (String cd : deptidList) {
				if (!newList.contains(cd)) {
					newList.add(cd);
				}
			}
			params.put("deptidList", newList);
		}
		return shopMapper.getShopListCout(params);
	}
	
	public Map<String,Object>getShop(Map<String,Object>params){
		
		return null;
	}
}
