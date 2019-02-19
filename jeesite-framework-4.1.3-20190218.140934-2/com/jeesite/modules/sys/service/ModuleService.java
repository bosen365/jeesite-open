package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Module;	
	
public interface ModuleService extends CrudServiceApi {	
   void updateStatus(Module var1);	
	
   Module get(Module var1);	
	
   void delete(Module var1);	
	
   Page findPage(Module var1);	
	
   void save(Module var1);	
}	
