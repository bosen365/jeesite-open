package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Config;	
	
public interface ConfigService extends CrudServiceApi {	
   Page findPage(Config var1);	
	
   void save(Config var1);	
	
   Config get(Config var1);	
	
   void delete(Config var1);	
}	
