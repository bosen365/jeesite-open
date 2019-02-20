package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Lang;	
	
public interface LangService extends CrudServiceApi {	
   void delete(Lang var1);	
	
   Page findPage(Lang var1);	
	
   Lang get(Lang var1);	
	
   void clearCache();	
	
   void save(Lang var1);	
}	
