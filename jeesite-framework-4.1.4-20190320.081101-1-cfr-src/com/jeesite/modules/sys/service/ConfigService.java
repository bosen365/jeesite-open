/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Config;	
	
public interface ConfigService	
extends CrudServiceApi<Config> {	
    @Override	
    public Page<Config> findPage(Config var1);	
	
    @Override	
    public Config get(Config var1);	
	
    @Override	
    public void delete(Config var1);	
	
    @Override	
    public void save(Config var1);	
}	
	
