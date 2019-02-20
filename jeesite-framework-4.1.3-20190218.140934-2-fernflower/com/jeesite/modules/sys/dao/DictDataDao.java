package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface DictDataDao extends TreeDao {	
}	
