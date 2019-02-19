package com.jeesite.modules.gen.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface GenTableColumnDao extends CrudDao {	
}	
