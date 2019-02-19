package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class ModuleServiceSupport extends CrudService implements ModuleService {	
   @Autowired	
   private MenuService menuService;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Module module) {	
      super.save(module);	
      ModuleUtils.clearCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(Module module) {	
      super.updateStatus(module);	
      ModuleUtils.clearCache();	
      if ("2".equals(module.getStatus())) {	
         this.menuService.disableByModuleCodes(module.getModuleCode());	
      } else {	
         if ("0".equals(module.getStatus())) {	
            this.menuService.enableByModuleCodes(module.getModuleCode());	
         }	
	
      }	
   }	
	
   public Page findPage(Module module) {	
      return super.findPage(module);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Module module) {	
      super.delete(module);	
      ModuleUtils.clearCache();	
   }	
	
   public Module get(Module module) {	
      return (Module)super.get(module);	
   }	
}	
