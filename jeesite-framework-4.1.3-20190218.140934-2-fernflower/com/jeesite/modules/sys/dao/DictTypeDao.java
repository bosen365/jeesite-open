package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface DictTypeDao extends CrudDao {	
}	
