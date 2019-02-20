package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.service.api.TreeServiceApi;	
import com.jeesite.modules.sys.entity.Menu;	
import java.util.List;	
	
public interface MenuService extends TreeServiceApi {	
   String getMenuNamePath(String var1, String var2);	
	
   List findByUserCode(Menu var1);	
	
   void disableByModuleCodes(String var1);	
	
   void save(Menu var1);	
	
   List findList(Menu var1);	
	
   Menu get(Menu var1);	
	
   void updateTreeSort(Menu var1);	
	
   List findByRoleCode(Menu var1);	
	
   void delete(Menu var1);	
	
   void enableByModuleCodes(String var1);	
}	
