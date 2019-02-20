/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.dao.ModuleDao;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class ModuleServiceSupport	
extends CrudService<ModuleDao, Module>	
implements ModuleService {	
    @Autowired	
    private MenuService menuService;	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Module module) {	
        super.save(module);	
        ModuleUtils.clearCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(Module module) {	
        super.updateStatus(module);	
        ModuleUtils.clearCache();	
        if ("2".equals(module.getStatus())) {	
            this.menuService.disableByModuleCodes(module.getModuleCode());	
            return;	
        }	
        if ("0".equals(module.getStatus())) {	
            this.menuService.enableByModuleCodes(module.getModuleCode());	
        }	
    }	
	
    @Override	
    public Page<Module> findPage(Module module) {	
        return super.findPage(module);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Module module) {	
        super.delete(module);	
        ModuleUtils.clearCache();	
    }	
	
    @Override	
    public Module get(Module module) {	
        return super.get(module);	
    }	
}	
	
