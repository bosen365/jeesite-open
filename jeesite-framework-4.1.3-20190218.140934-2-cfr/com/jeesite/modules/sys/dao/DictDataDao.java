/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.DictData;	
	
@MyBatisDao(dataSourceName="default")	
public interface DictDataDao	
extends TreeDao<DictData> {	
}	
	
