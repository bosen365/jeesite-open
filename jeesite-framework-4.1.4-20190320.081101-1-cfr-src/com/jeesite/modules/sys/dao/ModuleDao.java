/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Module;	
	
@MyBatisDao(dataSourceName="default")	
public interface ModuleDao	
extends CrudDao<Module> {	
}	
	
