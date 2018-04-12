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

import com.sumainfo.common.util.ConvertDateTime;
import com.sumainfo.common.util.MenuRecursion;
import com.sumainfo.modules.sys.dao.DeptMapper;
import com.sumainfo.modules.sys.dao.VehicledetMapper;
import com.sumainfo.modules.sys.entity.Menu;

import freemarker.template.utility.StringUtil;

@Service
public class VehicaledetService implements Serializable {
	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(VehicaledetService.class);

	@Autowired
	VehicledetMapper vehicledetMapper;

	@Autowired
	DeptMapper deptMapper;

	public List<String> deptList(Map<String, Object> params) {
		// 获取登录用户下的子部门
		List<Menu> deptList = vehicledetMapper.getDept(params);
		int dept = Integer.valueOf(params.get("loginDeptid").toString());
		List<Menu> tree = MenuRecursion.treeMenuList(deptList, dept);
		List<String> deptidList = new ArrayList<String>();// 子部门编号
		for (Menu m : tree) {
			deptidList.add(m.getId());
		}
		List<String> newList = new ArrayList<String>();
		for (String cd : deptidList) {
			if (!newList.contains(cd)) {
				newList.add(cd);
			}
		}
		// params.put("deptidList", newList);
		return newList;
	}

	public List<Map<String, Object>> getVehiList(Map<String, Object> params) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();// loginDeptid
		// 如果是管理员或者公司admin，直接查询全部
		if (StringUtils.isEmpty(params.get("loginDeptid"))) {
			List<Map<String, Object>> vehiList = vehicledetMapper
					.getVehiList(params);
			for (Map<String, Object> map : vehiList) {
				if (map.get("shopId") != null) {
					params.put("shopId", map.get("shopId"));
					params.put("bandName", map.get("bandName"));
					params.put("carName", map.get("carName"));
					map.put("shopName",
							vehicledetMapper.getShop(params).get("shopName"));
					// 获取品牌编号
					map.put("fvcid", vehicledetMapper.getFcv(params).get("id"));
					// 获取车型编号
					map.put("secid", vehicledetMapper.getSec(params).get("id"));
					result.add(map);
				}
			}
			return result;
		}

		// 其他登录用户先，获取当前登录的部门
		Map<String, Object> getDeptMap = deptMapper.getDeptMap(params);
		String deptName = getDeptMap.get("name").toString();
		if (deptName.lastIndexOf("-") != -1) {
			deptName = deptName.substring(0, deptName.lastIndexOf("-"));
		}
		// List<String> newList = new ArrayList<String>();
		if (deptName.equals("集团老总")) {// 集团老总，找当前集团下面的所有大区
			params.put("deptidList", deptList(params));
			// 获取部门里面的用户店铺编号
			List<Map<String, Object>> userShop = vehicledetMapper
					.getUserShop(params);
			List<String> shopList = new ArrayList<String>();
			for (Map<String, Object> map : userShop) {
				if (map.get("shopid") != null) {
					shopList.add(map.get("shopid").toString());
				}
			}
			List<String> shoplist = new ArrayList<String>();
			for (String cd : shopList) {
				if (!shoplist.contains(cd)) {
					shoplist.add(cd);
				}
			}
			log.info("shopList->>>>>>>" + shoplist);
			params.put("shopidList", shoplist);
		} else if (deptName.equals("大区经理")) {// 大区经理，直接找当前的大区
			params.put("deptidList", deptList(params));
			// 获取部门里面的用户店铺编号
			List<Map<String, Object>> userShop = vehicledetMapper
					.getUserShop(params);
			List<String> shopList = new ArrayList<String>();
			for (Map<String, Object> map : userShop) {
				if (map.get("shopid") != null) {
					shopList.add(map.get("shopid").toString());
				}
			}
			List<String> shoplist = new ArrayList<String>();
			for (String cd : shopList) {
				if (!shoplist.contains(cd)) {
					shoplist.add(cd);
				}
			}
			log.info("shopList->>>>>>>" + shoplist);
			params.put("shopidList", shoplist);
		} else if (deptName.equals("商家店长")) {// 商家店长，就要按照父节点查所在大区
			List<String> shoplist = new ArrayList<String>();
			shoplist.add(vehicledetMapper.userAnShop(params).get("shopid")
					.toString());
			params.put("shopidList", shoplist);
		} else if (deptName.equals("商家店员")) {// 商家店员，就不查询
			List<String> shoplist = new ArrayList<String>();
			shoplist.add(vehicledetMapper.userAnShop(params).get("shopid")
					.toString());
			params.put("shopidList", shoplist);
		}
		// 新车列表
		List<Map<String, Object>> vehiList = vehicledetMapper
				.getVehiList(params);
		for (Map<String, Object> map : vehiList) {
			if (!StringUtils.isEmpty(map.get("shopId"))) {
				params.put("shopId", map.get("shopId"));
				params.put("bandName", map.get("bandName"));
				params.put("carName", map.get("carName"));
				map.put("shopName",
						vehicledetMapper.getShop(params).get("shopName"));
				// 获取品牌编号
				map.put("fvcid", vehicledetMapper.getFcv(params).get("id"));
				// 获取车型编号
				map.put("secid", vehicledetMapper.getSec(params).get("id"));
				result.add(map);
			}
		}
		return result;
	}

	public Integer getVehiCout(Map<String, Object> params) {
		// 如果是管理员或者公司admin，直接查询全部
		if (StringUtils.isEmpty(params.get("loginDeptid"))) {
			return vehicledetMapper.getVehiCout(params);
		}
		// 其他登录用户先，获取当前登录的部门
		Map<String, Object> getDeptMap = deptMapper.getDeptMap(params);
		String deptName = getDeptMap.get("name").toString();
		if (deptName.lastIndexOf("-") != -1) {
			deptName = deptName.substring(0, deptName.lastIndexOf("-"));
		}
		if (deptName.equals("集团老总")) {// 集团老总，找当前集团下面的所有大区
			params.put("deptidList", deptList(params));
			// 获取部门里面的用户店铺编号
			List<Map<String, Object>> userShop = vehicledetMapper
					.getUserShop(params);
			List<String> shopList = new ArrayList<String>();
			for (Map<String, Object> map : userShop) {
				if (map.get("shopid") != null) {
					shopList.add(map.get("shopid").toString());
				}
			}
			List<String> shoplist = new ArrayList<String>();
			for (String cd : shopList) {
				if (!shoplist.contains(cd)) {
					shoplist.add(cd);
				}
			}
			log.info("shopList->>>>>>>" + shoplist);
			params.put("shopidList", shoplist);
		} else if (deptName.equals("大区经理")) {// 大区经理，直接找当前的大区
			params.put("deptidList", deptList(params));
			// 获取部门里面的用户店铺编号
			List<Map<String, Object>> userShop = vehicledetMapper
					.getUserShop(params);
			List<String> shopList = new ArrayList<String>();
			for (Map<String, Object> map : userShop) {
				if (map.get("shopid") != null) {
					shopList.add(map.get("shopid").toString());
				}
			}
			List<String> shoplist = new ArrayList<String>();
			for (String cd : shopList) {
				if (!shoplist.contains(cd)) {
					shoplist.add(cd);
				}
			}
			log.info("shopList->>>>>>>" + shoplist);
			params.put("shopidList", shoplist);
		} else if (deptName.equals("商家店长")) {// 商家店长，就要按照父节点查所在大区
			List<String> shoplist = new ArrayList<String>();
			shoplist.add(vehicledetMapper.userAnShop(params).get("shopid")
					.toString());
			params.put("shopidList", shoplist);
		} else if (deptName.equals("商家店员")) {// 商家店员，就不查询
			List<String> shoplist = new ArrayList<String>();
			shoplist.add(vehicledetMapper.userAnShop(params).get("shopid")
					.toString());
			params.put("shopidList", shoplist);
		}
		// //获取登录用户下的子部门
		// List<Menu>deptList=vehicledetMapper.getDept(params);
		// int dept=Integer.valueOf(params.get("deptid").toString());
		// List<Menu> tree=MenuRecursion.treeMenuList(deptList, dept);
		// List<String> deptidList =new ArrayList<String>();//子部门编号
		// for(Menu m:tree){
		// deptidList.add(m.getId());
		// }
		//
		// List<String> newList = new ArrayList<String>();
		// for (String cd:deptidList) {
		// if(!newList.contains(cd)){
		// newList.add(cd);
		// }
		// }
		// params.put("deptidList", newList);
		 return vehicledetMapper.getVehiCout(params);

//		return 1;
	}

	public Map<String, Object> getVehiMap(Map<String, Object> params) {
		Map<String, Object> VehiMap = vehicledetMapper.getVehiMap(params);
		if (VehiMap == null || VehiMap.size() < 1) {
			return new HashMap<String, Object>();
		}
		params.put("carName", VehiMap.get("carName"));
		params.put("shopId", VehiMap.get("shopId"));
		params.put("bandName", VehiMap.get("bandName"));
		params.put("carName", VehiMap.get("carName"));
		List<Map<String, Object>> vehiPicList = vehicledetMapper
				.getVePic(params);
//		StringBuilder str = new StringBuilder();
//		for (int i = 0; i < vehiPicList.size(); i++) {
//			if(i==vehiPicList.size()){
//				str.append(vehiPicList.get(i).get("picAddress"));
//			}else{
//				str.append(vehiPicList.get(i).get("picAddress")+";");
//			}
//		}
		List<Map<String, Object>> vepics = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : vehiPicList) {
			Map<String, Object> vepic = new HashMap<String, Object>();
//			vepic.put("isShow", map.get("isShow"));
			vepic.put("picAddress", map.get("picAddress"));
			vepics.add(vepic);
		}
		VehiMap.put("imgs",vepics);
		// 获取品牌编号
		VehiMap.put("fvcid", vehicledetMapper.getFcv(params).get("id"));
		// 获取车型编号
		VehiMap.put("secid", vehicledetMapper.getSec(params).get("id"));
		return VehiMap;
	}

	public boolean addVehi(Map<String, Object> params) {
		boolean result = false;
		log.info("params->>>>>>>>>>>" + params);
		params.put("createTime", ConvertDateTime.getCurrentTime());
		int bole = vehicledetMapper.addVehi(params);
		if (bole > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 修改新车资料
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月12日
	 */
	public boolean updateVehi(Map<String, Object> params) {
		boolean result = false;
		log.info("params->>>>>>>>>>>>" + params);
		params.put("modifyTime", ConvertDateTime.getCurrentTime());
		
		Map<String,Object>vhiBan=vehicledetMapper.getVhiBan(params);//获取新车和图片中间表
		params.put("vehId", vhiBan.get("vehId"));
		List<Map<String,Object>>vhiPic=vehicledetMapper.getVePic(params);//获取新车的图片
		int bo=0;
		for (Map<String, Object> map : vhiPic) {//循环删除现有新车的图片，然后新增
			params.put("vbId", map.get("vbId"));
			if(vehicledetMapper.deltctVhiPic(params)>0){
				bo=1;
			}else{
				bo=0;
			}
		}
		if(bo>0){//说明删除了新车的图片
			int bole = vehicledetMapper.updateVehi(params);
			if (bole > 0) {//修改新车主信息
				result = true;
			}
			
		}
		return result;
	}
}
