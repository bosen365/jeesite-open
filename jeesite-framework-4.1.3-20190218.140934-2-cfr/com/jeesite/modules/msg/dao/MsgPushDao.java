/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.msg.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.util.List;	
	
@MyBatisDao(dataSourceName="default")	
public interface MsgPushDao	
extends CrudDao<MsgPush> {	
    public List<MsgPush> findListByMergePush(MsgPush var1);	
}	
	
