/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.dao;	
	
import com.jeesite.common.dao.BaseDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import java.util.List;	
	
@MyBatisDao	
public interface GenDataDictDao	
extends BaseDao {	
    public List<GenTableColumn> findTableColumnList(GenTable var1);	
	
    public List<GenTableColumn> findTablePK(GenTable var1);	
	
    public List<GenTable> findTableList(GenTable var1);	
}	
	
