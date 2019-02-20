package com.jeesite.modules.gen.dao;	
	
import com.jeesite.common.dao.BaseDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.gen.entity.GenTable;	
import java.util.List;	
	
@MyBatisDao	
public interface GenDataDictDao extends BaseDao {	
   List findTableList(GenTable var1);	
	
   List findTableColumnList(GenTable var1);	
	
   List findTablePK(GenTable var1);	
}	
