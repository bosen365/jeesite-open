/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.util.List;	
	
public interface MsgPushService	
extends CrudServiceApi<MsgPush> {	
    @Override	
    public void delete(MsgPush var1);	
	
    @Override	
    public void save(MsgPush var1);	
	
    public void updateMsgPush(MsgPush var1);	
	
    @Override	
    public Page<MsgPush> findPage(MsgPush var1);	
	
    @Override	
    public MsgPush get(MsgPush var1);	
	
    public List<MsgPush> findListByMergePush(MsgPush var1);	
}	
	
