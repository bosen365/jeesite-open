/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import java.util.List;	
	
public interface UserService	
extends CrudServiceApi<User> {	
    @Override	
    public void updateStatus(User var1);	
	
    public List<User> findListByRoleCode(User var1);	
	
    public String checkLoginCode(String var1, String var2);	
	
    public void updateUserLoginInfo(User var1);	
	
    public void saveAuthDataScope(User var1);	
	
    public List<UserDataScope> findDataScopeList(UserDataScope var1);	
	
    @Override	
    public List<User> findList(User var1);	
	
    @Override	
    public Page<User> findPage(User var1);	
	
    @Override	
    public void delete(User var1);	
	
    public User getByUserTypeAndRefCode(User var1);	
	
    public List<User> findCorpList(User var1);	
	
    @Override	
    public void save(User var1);	
	
    @Override	
    public User get(User var1);	
	
    public void updateUserInfo(User var1);	
	
    public void updatePassword(String var1, String var2);	
	
    public User getByLoginCode(User var1);	
	
    public void updateMgrType(User var1);	
	
    public void updateQuestion(User var1);	
	
    public void saveAuth(User var1);	
}	
	
