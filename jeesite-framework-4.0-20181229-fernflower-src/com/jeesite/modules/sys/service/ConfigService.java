package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.entity.Config;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class ConfigService extends CrudService {	
   public Config get(Config config) {	
      return (Config)super.get(config);	
   }	
	
   public Page findPage(Page page, Config config) {	
      return super.findPage(page, config);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Config config) {	
      if (config.getIsNewRecord()) {	
         config.setIsSys("0");	
      }	
	
      super.save(config);	
      Global.clearCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Config config) {	
      super.delete(config);	
      Global.clearCache();	
   }	
}	
