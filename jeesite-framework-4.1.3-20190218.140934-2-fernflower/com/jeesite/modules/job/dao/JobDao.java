package com.jeesite.modules.job.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface JobDao extends CrudDao {	
}	
