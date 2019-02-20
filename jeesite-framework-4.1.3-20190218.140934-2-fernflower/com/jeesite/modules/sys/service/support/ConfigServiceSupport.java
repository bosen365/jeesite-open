package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.service.ConfigService;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class ConfigServiceSupport extends CrudService implements ConfigService {	
   public Page findPage(Config config) {	
      return super.findPage(config);	
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
	
   public Config get(Config config) {	
      return (Config)super.get(config);	
   }	
}	
