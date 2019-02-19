package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.User;	
import java.util.List;	
	
@MyBatisDao(	
   dataSourceName = "default"	
)	
public interface UserDao extends CrudDao {	
   List findListByRoleCode(User var1);	
	
   int updateLoginInfo(User var1);	
	
   List findCorpList(User var1);	
	
   int updateMgrType(User var1);	
	
   User getByUserTypeAndRefCode(User var1);	
	
   User getByLoginCode(User var1);	
	
   int updateUserInfo(User var1);	
	
   int updatePassword(User var1);	
	
   int updateQuestion(User var1);	
}	
