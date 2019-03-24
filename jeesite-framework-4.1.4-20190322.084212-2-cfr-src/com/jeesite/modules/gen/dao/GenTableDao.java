/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.gen.entity.GenTable;	
import java.util.List;	
	
@MyBatisDao(dataSourceName="default")	
public interface GenTableDao	
extends CrudDao<GenTable> {	
    public List<GenTable> findGenBaseDirList(GenTable var1);	
}	
	
