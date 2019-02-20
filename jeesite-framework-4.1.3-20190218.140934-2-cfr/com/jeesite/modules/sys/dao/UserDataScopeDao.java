/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.UserDataScope;	
	
@MyBatisDao(dataSourceName="default")	
public interface UserDataScopeDao	
extends CrudDao<UserDataScope> {	
}	
	
