package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
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
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class RoleServiceSupport extends CrudService implements RoleService {	
   @Autowired	
   private UserRoleDao userRoleDao;	
   @Autowired	
   private RoleMenuDao roleMenuDao;	
   @Autowired	
   private RoleDataScopeDao roleDataScopeDao;	
   @Autowired	
   private UserService userService;	
	
   public Role getByRoleName(Role role) {	
      Role a = new Role();	
      a.setRoleName(role.getRoleName());	
      return (Role)((RoleDao)this.dao).getByEntity(a);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Role role) {	
      super.delete(role);	
      this.clearUserCacheByRoleCode(role);	
   }	
	
   public List findListByUserCode(Role role) {	
      return ((RoleDao)this.dao).findListByUserCode(role);	
   }	
	
   // $FF: synthetic method	
   private void clearUserCacheByRoleCode(Role role) {	
      User a = new User();	
      a.setRoleCode(role.getRoleCode());	
	
      Iterator var4;	
      for(Iterator var10000 = var4 = this.userService.findListByRoleCode(a).iterator(); var10000.hasNext(); var10000 = var4) {	
         UserUtils.clearCache((User)var4.next());	
      }	
	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Role role) {	
      if (StringUtils.isBlank(role.getUserType())) {	
         role.setUserType("none");	
      }	
	
      super.save(role);	
      this.clearUserCacheByRoleCode(role);	
   }	
	
   public List findList(Role role) {	
      role.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
      role.getSqlMap().getWhere().andBracket("is_sys", QueryType.EQ, "1").or("corp_code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
      return super.findList(role);	
   }	
	
   public List findDataScopeList(RoleDataScope roleDataScope) {	
      return this.roleDataScopeDao.findList(roleDataScope);	
   }	
	
   public void addDataScopeFilter(Role role, String ctrlPermi) {	
      role.getSqlMap().getDataScope().addFilter("dsf", "Role", "a.role_code", ctrlPermi);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void saveAuth(Role role) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(role.getRoleCode())) {	
         RoleMenu a = new RoleMenu();	
         a.setRoleCode(role.getRoleCode());	
         this.roleMenuDao.deleteByEntity(a);	
         ListUtils.pageList(role.getRoleMenuList(), 100, new MethodCallback() {	
            public Object execute(Object... ax) {	
               RoleServiceSupport.this.roleMenuDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         this.clearUserCacheByRoleCode(role);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(Role role) {	
      super.updateStatus(role);	
      this.clearUserCacheByRoleCode(role);	
   }	
	
   public Page findPage(Role role) {	
      return super.findPage(role);	
   }	
	
   public Role get(Role role) {	
      return (Role)super.get(role);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void deleteAuthUser(Role role) {	
      Global.assertDemoMode();	
      Iterator var3;	
      Iterator var10000 = var3 = role.getUserRoleList().iterator();	
	
      while(var10000.hasNext()) {	
         UserRole a = (UserRole)var3.next();	
         var10000 = var3;	
         this.userRoleDao.delete(a);	
      }	
	
      this.clearUserCacheByRoleCode(role);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void saveAuthUser(Role role) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(role.getRoleCode())) {	
         Iterator var3 = role.getUserRoleList().iterator();	
	
         while(var3.hasNext()) {	
            UserRole a = (UserRole)var3.next();	
            if (this.userRoleDao.get(a) == null) {	
               this.userRoleDao.insert(a);	
            }	
         }	
	
         this.clearUserCacheByRoleCode(role);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void saveAuthDataScope(Role role) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(role.getRoleCode())) {	
         ((RoleDao)this.dao).updateDataScope(role);	
         RoleDataScope a = new RoleDataScope();	
         a.setRoleCode(role.getRoleCode());	
         this.roleDataScopeDao.deleteByEntity(a);	
         ListUtils.pageList(role.getRoleDataScopeList(), 100, new MethodCallback() {	
            public Object execute(Object... ax) {	
               RoleServiceSupport.this.roleDataScopeDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         this.clearUserCacheByRoleCode(role);	
      }	
   }	
}	
