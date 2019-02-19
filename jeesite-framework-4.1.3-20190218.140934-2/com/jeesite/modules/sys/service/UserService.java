package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import java.util.List;	
	
public interface UserService extends CrudServiceApi {	
   void updateStatus(User var1);	
	
   List findListByRoleCode(User var1);	
	
   String checkLoginCode(String var1, String var2);	
	
   void updateUserLoginInfo(User var1);	
	
   void saveAuthDataScope(User var1);	
	
   List findDataScopeList(UserDataScope var1);	
	
   List findList(User var1);	
	
   Page findPage(User var1);	
	
   void delete(User var1);	
	
   User getByUserTypeAndRefCode(User var1);	
	
   List findCorpList(User var1);	
	
   void save(User var1);	
	
   User get(User var1);	
	
   void updateUserInfo(User var1);	
	
   void updatePassword(String var1, String var2);	
	
   User getByLoginCode(User var1);	
	
   void updateMgrType(User var1);	
	
   void updateQuestion(User var1);	
	
   void saveAuth(User var1);	
}	
