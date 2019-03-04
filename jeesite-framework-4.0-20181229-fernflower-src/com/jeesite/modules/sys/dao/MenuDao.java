package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Menu;	
import java.util.List;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface MenuDao extends TreeDao {	
   List findByUserCode(Menu var1);	
	
   List findByRoleCode(Menu var1);	
}	
