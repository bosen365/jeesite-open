package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import java.util.List;	
	
public interface RoleService extends CrudServiceApi {	
   void saveAuthDataScope(Role var1);	
	
   void deleteAuthUser(Role var1);	
	
   List findListByUserCode(Role var1);	
	
   void saveAuthUser(Role var1);	
	
   List findList(Role var1);	
	
   Role getByRoleName(Role var1);	
	
   Page findPage(Role var1);	
	
   void delete(Role var1);	
	
   Role get(Role var1);	
	
   void addDataScopeFilter(Role var1, String var2);	
	
   void updateStatus(Role var1);	
	
   List findDataScopeList(RoleDataScope var1);	
	
   void save(Role var1);	
	
   void saveAuth(Role var1);	
}	
