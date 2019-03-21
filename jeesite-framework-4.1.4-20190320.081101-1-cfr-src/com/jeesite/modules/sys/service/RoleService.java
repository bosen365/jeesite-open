/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import java.util.List;	
	
public interface RoleService	
extends CrudServiceApi<Role> {	
    public void saveAuthDataScope(Role var1);	
	
    @Override	
    public void save(Role var1);	
	
    @Override	
    public void delete(Role var1);	
	
    @Override	
    public List<Role> findList(Role var1);	
	
    public List<RoleDataScope> findDataScopeList(RoleDataScope var1);	
	
    public void saveAuthUser(Role var1);	
	
    @Override	
    public void updateStatus(Role var1);	
	
    public void saveAuth(Role var1);	
	
    public Role getByRoleName(Role var1);	
	
    public void deleteAuthUser(Role var1);	
	
    @Override	
    public Role get(Role var1);	
	
    @Override	
    public void addDataScopeFilter(Role var1, String var2);	
	
    @Override	
    public Page<Role> findPage(Role var1);	
	
    public List<Role> findListByUserCode(Role var1);	
}	
	
