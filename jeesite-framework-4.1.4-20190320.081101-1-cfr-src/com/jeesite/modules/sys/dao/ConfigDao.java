/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Config;	
	
@MyBatisDao(dataSourceName="default")	
public interface ConfigDao	
extends CrudDao<Config> {	
}	
	
