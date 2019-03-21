/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.shiro.d.g;	
import com.jeesite.modules.sys.dao.RoleDao;	
import com.jeesite.modules.sys.dao.RoleDataScopeDao;	
import com.jeesite.modules.sys.dao.RoleMenuDao;	
import com.jeesite.modules.sys.dao.UserRoleDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import com.jeesite.modules.sys.entity.RoleMenu;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserRole;	
import com.jeesite.modules.sys.service.RoleService;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.service.support.C;	
import com.jeesite.modules.sys.service.support.c;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.Tcp;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class RoleServiceSupport	
extends CrudService<RoleDao, Role>	
implements RoleService {	
    @Autowired	
    private RoleDataScopeDao roleDataScopeDao;	
    @Autowired	
    private UserService userService;	
    @Autowired	
    private RoleMenuDao roleMenuDao;	
    @Autowired	
    private UserRoleDao userRoleDao;	
	
    @Override	
    public Page<Role> findPage(Role role) {	
        return super.findPage(role);	
    }	
	
    @Override	
    public void addDataScopeFilter(Role role, String ctrlPermi) {	
        role.getSqlMap().getDataScope().addFilter("dsf", "Role", "a.role_code", ctrlPermi);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuthDataScope(Role role) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(role.getRoleCode())) {	
            return;	
        }	
        RoleDataScope a = new RoleDataScope();	
        a.setRoleCode(role.getRoleCode());	
        Role role2 = role;	
        ListUtils.pageList(role2.getRoleDataScopeList(), 100, new c(this));	
        this.clearUserCacheByRoleCode(role2);	
        this.roleDataScopeDao.deleteByEntity(a);	
        ((RoleDao)this.dao).updateDataScope(role);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Role role) {	
        RoleServiceSupport roleServiceSupport = this;	
        super.delete(role);	
        roleServiceSupport.clearUserCacheByRoleCode(role);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuthUser(Role role) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(role.getRoleCode())) {	
            return;	
        }	
        for (UserRole a : role.getUserRoleList()) {	
            if (this.userRoleDao.get(a) != null) continue;	
            this.userRoleDao.insert(a);	
        }	
        this.clearUserCacheByRoleCode(role);	
    }	
	
    @Override	
    public Role get(Role role) {	
        return super.get(role);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuth(Role role) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(role.getRoleCode())) {	
            return;	
        }	
        RoleMenu a = new RoleMenu();	
        a.setRoleCode(role.getRoleCode());	
        Role role2 = role;	
        ListUtils.pageList(role2.getRoleMenuList(), 100, new C(this));	
        this.clearUserCacheByRoleCode(role2);	
        this.roleMenuDao.deleteByEntity(a);	
    }	
	
    @Override	
    public List<Role> findList(Role role) {	
        Role role2 = role;	
        role2.getSqlMap().getWhere().andBracket("is_sys", QueryType.EQ, "1").or("corp_code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
        role.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
        return super.findList(role2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(Role role) {	
        RoleServiceSupport roleServiceSupport = this;	
        super.updateStatus(role);	
        roleServiceSupport.clearUserCacheByRoleCode(role);	
    }	
	
    @Override	
    public Role getByRoleName(Role role) {	
        Role a = new Role();	
        a.setRoleName(role.getRoleName());	
        return ((RoleDao)this.dao).getByEntity(a);	
    }	
	
    @Override	
    public List<RoleDataScope> findDataScopeList(RoleDataScope roleDataScope) {	
        return this.roleDataScopeDao.findList(roleDataScope);	
    }	
	
    @Override	
    public List<Role> findListByUserCode(Role role) {	
        return ((RoleDao)this.dao).findListByUserCode(role);	
    }	
	
    static /* synthetic */ RoleMenuDao access$000(RoleServiceSupport x0) {	
        return x0.roleMenuDao;	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void deleteAuthUser(Role role) {	
        Iterator<UserRole> iterator;	
        Global.assertDemoMode();	
        Iterator<UserRole> iterator2 = iterator = role.getUserRoleList().iterator();	
        while (iterator2.hasNext()) {	
            void a;	
            UserRole userRole = iterator.next();	
            iterator2 = iterator;	
            this.userRoleDao.delete(a);	
        }	
        this.clearUserCacheByRoleCode(role);	
    }	
	
    private /* synthetic */ void clearUserCacheByRoleCode(Role role) {	
        Iterator<User> iterator;	
        User a = new User();	
        a.setRoleCode(role.getRoleCode());	
        Iterator<User> iterator2 = iterator = this.userService.findListByRoleCode(a).iterator();	
        while (iterator2.hasNext()) {	
            UserUtils.clearCache(iterator.next());	
            iterator2 = iterator;	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Role role) {	
        if (StringUtils.isBlank(role.getUserType())) {	
            role.setUserType("none");	
        }	
        RoleServiceSupport roleServiceSupport = this;	
        Role role2 = role;	
        super.save(role2);	
        roleServiceSupport.clearUserCacheByRoleCode(role2);	
    }	
	
    static /* synthetic */ RoleDataScopeDao access$100(RoleServiceSupport x0) {	
        return x0.roleDataScopeDao;	
    }	
}	
	
