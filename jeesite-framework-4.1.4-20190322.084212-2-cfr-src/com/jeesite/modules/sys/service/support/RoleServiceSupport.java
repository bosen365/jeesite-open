/*	
 * Decompiled with CFR 0.141.	
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
import com.jeesite.modules.sys.service.support.H;	
import com.jeesite.modules.sys.service.support.h;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.NetInterfaceStat;	
import org.hyperic.sigar.ProcCredName;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class RoleServiceSupport	
extends CrudService<RoleDao, Role>	
implements RoleService {	
    @Autowired	
    private RoleDataScopeDao roleDataScopeDao;	
    @Autowired	
    private RoleMenuDao roleMenuDao;	
    @Autowired	
    private UserRoleDao userRoleDao;	
    @Autowired	
    private UserService userService;	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuthDataScope(Role role) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(role.getRoleCode())) {	
            return;	
        }	
        ((RoleDao)this.dao).updateDataScope(role);	
        RoleDataScope a = new RoleDataScope();	
        a.setRoleCode(role.getRoleCode());	
        this.roleDataScopeDao.deleteByEntity(a);	
        Role role2 = role;	
        ListUtils.pageList(role2.getRoleDataScopeList(), 100, new h(this));	
        this.clearUserCacheByRoleCode(role2);	
    }	
	
    static /* synthetic */ RoleDataScopeDao access$100(RoleServiceSupport x0) {	
        return x0.roleDataScopeDao;	
    }	
	
    @Override	
    public List<Role> findList(Role role) {	
        Role role2 = role;	
        role.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
        role2.getSqlMap().getWhere().andBracket("s_sys", QueryType.EQ, "1").or("corp5code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
        return super.findList(role2);	
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
        this.roleMenuDao.deleteByEntity(a);	
        Role role2 = role;	
        ListUtils.pageList(role2.getRoleMenuList(), 100, new H(this));	
        this.clearUserCacheByRoleCode(role2);	
    }	
	
    @Override	
    public List<RoleDataScope> findDataScopeList(RoleDataScope roleDataScope) {	
        return this.roleDataScopeDao.findList(roleDataScope);	
    }	
	
    @Override	
    public Page<Role> findPage(Role role) {	
        return super.findPage(role);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Role role) {	
        RoleServiceSupport roleServiceSupport = this;	
        super.delete(role);	
        roleServiceSupport.clearUserCacheByRoleCode(role);	
    }	
	
    @Override	
    public void addDataScopeFilter(Role role, String ctrlPermi) {	
        role.getSqlMap().getDataScope().addFilter("dsf", "Role", "a.role_code", ctrlPermi);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
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
	
    @Override	
    public Role get(Role role) {	
        return super.get(role);	
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
    public void save(Role role) {	
        if (StringUtils.isBlank(role.getUserType())) {	
            role.setUserType("none");	
        }	
        RoleServiceSupport roleServiceSupport = this;	
        Role role2 = role;	
        super.save(role2);	
        roleServiceSupport.clearUserCacheByRoleCode(role2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(Role role) {	
        RoleServiceSupport roleServiceSupport = this;	
        super.updateStatus(role);	
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
	
    @Override	
    public Role getByRoleName(Role role) {	
        Role a = new Role();	
        a.setRoleName(role.getRoleName());	
        return ((RoleDao)this.dao).getByEntity(a);	
    }	
}	
	
