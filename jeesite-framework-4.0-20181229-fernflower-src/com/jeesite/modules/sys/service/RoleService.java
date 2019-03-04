package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
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
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class RoleService extends CrudService {	
   @Autowired	
   private RoleDataScopeDao roleDataScopeDao;	
   @Autowired	
   private UserService userService;	
   @Autowired	
   private RoleMenuDao roleMenuDao;	
   @Autowired	
   private UserRoleDao userRoleDao;	
	
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
               RoleService.this.roleDataScopeDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         this.clearUserCacheByRoleCode(role);	
      }	
   }	
	
   public boolean hasUserRoleByRoleCode(Role role) {	
      UserRole a = new UserRole();	
      a.setRoleCode(role.getRoleCode());	
      return this.userRoleDao.findCount(a) > 0L;	
   }	
	
   public void addDataScopeFilter(Role role, String ctrlPermi) {	
      role.getSqlMap().getDataScope().addFilter("dsf", "Role", "a.role_code", ctrlPermi);	
   }	
	
   public Role getByRoleName(Role role) {	
      Role a = new Role();	
      a.setRoleName(role.getRoleName());	
      return (Role)((RoleDao)this.dao).getByEntity(a);	
   }	
	
   public List findListByUserCode(Role role) {	
      return ((RoleDao)this.dao).findListByUserCode(role);	
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
               RoleService.this.roleMenuDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         this.clearUserCacheByRoleCode(role);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Role role) {	
      super.delete(role);	
      this.clearUserCacheByRoleCode(role);	
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
	
   public List findDataScopeList(RoleDataScope roleDataScope) {	
      return this.roleDataScopeDao.findList(roleDataScope);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(Role role) {	
      super.updateStatus(role);	
      this.clearUserCacheByRoleCode(role);	
   }	
	
   public Page findPage(Page page, Role role) {	
      return super.findPage(page, role);	
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
	
   public Role get(Role role) {	
      return (Role)super.get(role);	
   }	
}	
