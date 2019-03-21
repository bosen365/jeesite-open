/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.DictType;	
	
public interface DictTypeService	
extends CrudServiceApi<DictType> {	
    @Override	
    public DictType get(DictType var1);	
	
    @Override	
    public void delete(DictType var1);	
	
    public void save(DictType var1, DictType var2);	
	
    @Override	
    public void updateStatus(DictType var1);	
	
    @Override	
    public Page<DictType> findPage(DictType var1);	
}	
	
