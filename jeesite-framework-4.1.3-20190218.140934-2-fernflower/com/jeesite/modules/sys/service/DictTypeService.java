package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.DictType;	
	
public interface DictTypeService extends CrudServiceApi {	
   DictType get(DictType var1);	
	
   void delete(DictType var1);	
	
   void updateStatus(DictType var1);	
	
   void save(DictType var1, DictType var2);	
	
   Page findPage(DictType var1);	
}	
