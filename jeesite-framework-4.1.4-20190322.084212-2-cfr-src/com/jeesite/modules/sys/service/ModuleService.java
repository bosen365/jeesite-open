/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Module;	
	
public interface ModuleService	
extends CrudServiceApi<Module> {	
    @Override	
    public Page<Module> findPage(Module var1);	
	
    @Override	
    public void save(Module var1);	
	
    @Override	
    public Module get(Module var1);	
	
    @Override	
    public void updateStatus(Module var1);	
	
    @Override	
    public void delete(Module var1);	
}	
	
