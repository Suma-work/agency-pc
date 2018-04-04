package com.sumainfo.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils {
	
	private List<Map<String, Object>> dataList; // 列表数据
	private int pageSize; // 每页大小 
    private int totalCount; // 总记录数  
    private int currPage; // 当前页数  
    private int totalPage; // 总页数  
	
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * 处理参数名称不一致
	 * @param params
	 */
	public void getPageParam(Map<String, Object> params){
		Pager pager = new Pager();
		if(params.get("rows") != null ){
			pager.setPagesize(Integer.valueOf(params.get("rows").toString()));
		}else{
			pager.setPagesize(20);
		}
		if(params.get("page") != null){
			pager.setPageno(Integer.valueOf(params.get("page").toString()));
		}else{
			pager.setPageno(1);
		}
		
		pager.setPager(params, pager);
	}
	/**
	 * 分页
	 * @param dataList    列表数据		
	 * @param pageSize    每页大小
	 * @param totalCount  总记录数
	 * @param currPage    当前页数
	 * @param totalPage   总页数
	 */
	public JsonResult getJsonResult(List<Map<String, Object>> userList,Map<String, Object> params,Integer cout){
		JsonResult jr = new JsonResult();
		Map<String,Object> mapres = new HashMap<>();
		
		if(params.get("pageno") != null){
			mapres.put("currPage", params.get("pageno").toString());
		}
		Integer totalPage;//总页数
		Integer pagesize=Integer.valueOf(params.get("pagesize").toString());
		//总页数 = 总记录数 + 页大小 - 1，然后除以页大小。
		if(cout==0){
			totalPage=0;
		}else{
			totalPage = (cout + pagesize - 1)/pagesize;
		}
		mapres.put("dataList", userList);//集合数据
		mapres.put("pageSize", pagesize);//每页大小
		mapres.put("totalCount", cout);//总条数
//		mapres.put("currPage", params.get("pageno").toString());//当前页
		mapres.put("totalPage", totalPage);//总页数
		jr.put(mapres);
		return jr;
	}
}
