package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Role;	
import java.util.List;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface RoleDao extends CrudDao {	
   List findListByUserCode(Role var1);	
	
   int updateDataScope(Role var1);	
}	
