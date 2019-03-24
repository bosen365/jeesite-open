/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.UserRole;	
	
@MyBatisDao(dataSourceName="default")	
public interface UserRoleDao	
extends CrudDao<UserRole> {	
}	
	
