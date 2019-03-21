/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
	
public interface MsgTemplateService	
extends CrudServiceApi<MsgTemplate> {	
    @Override	
    public void save(MsgTemplate var1);	
	
    @Override	
    public Page<MsgTemplate> findPage(MsgTemplate var1);	
	
    @Override	
    public void delete(MsgTemplate var1);	
	
    @Override	
    public MsgTemplate get(MsgTemplate var1);	
	
    @Override	
    public void updateStatus(MsgTemplate var1);	
}	
	
