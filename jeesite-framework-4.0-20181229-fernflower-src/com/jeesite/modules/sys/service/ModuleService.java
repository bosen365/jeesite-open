package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class ModuleService extends CrudService {	
   @Autowired	
   private MenuService menuService;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Module module) {	
      super.delete(module);	
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
	
   public Page findPage(Page page, Module module) {	
      return super.findPage(page, module);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Module module) {	
      super.save(module);	
      ModuleUtils.clearCache();	
   }	
	
   public Module get(Module module) {	
      return (Module)super.get(module);	
   }	
}	
