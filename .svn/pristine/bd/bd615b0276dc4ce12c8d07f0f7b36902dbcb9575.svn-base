package com.sumainfo.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sumainfo.modules.sys.entity.Menu;

public class MenuRecursion {
	 //子节点  
    static  List<Menu> childMenu=new ArrayList<Menu>();  
  
    /** 
     * 获取某个父节点下面的所有子节点 
     * @param menuList 
     * @param pid 
     * @return 
     */  
    public static List<Menu> treeMenuList( List<Menu> menuList, int pid){  
        for(Menu mu: menuList){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(Integer.valueOf(mu.getPid())==pid){  
                //递归遍历下一级  
                treeMenuList(menuList,Integer.valueOf(mu.getId()));  
                childMenu.add(mu);  
            }  
        }  
    return childMenu;  
    }  
    
}
