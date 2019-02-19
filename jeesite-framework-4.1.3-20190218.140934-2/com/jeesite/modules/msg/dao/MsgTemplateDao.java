package com.jeesite.modules.msg.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface MsgTemplateDao extends CrudDao {	
}	
