/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
	
@MyBatisDao(dataSourceName="default")	
public interface MsgTemplateDao	
extends CrudDao<MsgTemplate> {	
}	
	
