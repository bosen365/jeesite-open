package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.RoleService;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.utils.RoleUtils;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.FileSystemMap;	
import org.hyperic.sigar.ProcCredName;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/sys/role"})	
@ConditionalOnProperty(	
   name = {"web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class RoleController extends BaseController {	
   @Autowired	
   private MenuService menuService;	
   @Autowired	
   private UserService userService;	
   @Autowired	
   private RoleService roleService;	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"checkRoleName"})	
   @ResponseBody	
   public String checkRoleName(String oldRoleName, String roleName) {	
      Role a;	
      (a = new Role()).setRoleName(roleName);	
      if (roleName != null && roleName.equals(oldRoleName)) {	
         return "true";	
      } else {	
         return roleName != null && this.roleService.getByRoleName(a) == null ? "true" : "false";	
      }	
   }	
	
   @RequiresPermissions({"sys:role:view"})	
   @RequestMapping({"list"})	
   public String list(Role role, Model model) {	
      return "modules/sys/roleList";	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(Role role, HttpServletRequest request) {	
      if (Role.CORP_ADMIN_ROLE_CODE.equals(role.getRoleCode())) {	
         return this.renderResult("false", "非法操作，此角色为内置角色，不允许删除！");	
      } else {	
         Role a;	
         if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
         } else {	
            this.roleService.delete(role);	
            return this.renderResult("true", "删除角色成功！");	
         }	
      }	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"disable"})	
   @ResponseBody	
   public String disable(Role role, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         role.setStatus("2");	
         this.roleService.updateStatus(role);	
         return this.renderResult("true", "停用角色成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"formAuthUser"})	
   public String formAuthUser(Role role, Model model, HttpServletRequest request) {	
      Map a = MapUtils.newHashMap();	
      User a = new User(role);	
      Iterator var6;	
      Iterator var10000 = var6 = this.userService.findListByRoleCode(a).iterator();	
	
      while(var10000.hasNext()) {	
         User a = (User)var6.next();	
         var10000 = var6;	
         a.put(a.getUserCode(), a);	
      }	
	
      model.addAttribute("dataMap", a);	
      model.addAttribute("role", role);	
      return "modules/sys/roleFormAuthUser";	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"deleteAuthUser"})	
   @ResponseBody	
   public String deleteAuthUser(Role role, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.roleService.deleteAuthUser(role);	
         return this.renderResult("true", "取消用户和角色授权成功");	
      }	
   }	
	
   @RequiresPermissions({"sys:role:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(Role role, String ctrlPermi, HttpServletRequest request, HttpServletResponse response) {	
      if (!role.getCurrentUser().isSuperAdmin()) {	
         this.roleService.addDataScopeFilter(role, ctrlPermi);	
      }	
	
      role.setPage(new Page(request, response));	
      return this.roleService.findPage(role);	
   }	
	
   @ModelAttribute	
   public Role get(String roleCode, boolean isNewRecord) {	
      return (Role)this.roleService.get(roleCode, isNewRecord);	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"saveAuthUser"})	
   @ResponseBody	
   public String saveAuthUser(Role role, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.roleService.saveAuthUser(role);	
         return this.renderResult("true", "角色授权给用户成功");	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"treeData"})	
   @ResponseBody	
   public List treeData(String userType, Boolean isAll, String isShowCode, String ctrlPermi) {	
      List a = ListUtils.newArrayList();	
      Role a = new Role();	
      a.setStatus("0");	
      if (isAll == null || !isAll) {	
         a.setUserType((String)StringUtils.defaultIfBlank(userType, "employee"));	
         this.roleService.addDataScopeFilter(a, ctrlPermi);	
      }	
	
      List a = this.roleService.findList(a);	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.size(); var10000 = a) {	
         Role a = (Role)a.get(a);	
         if ("0".equals(a.getStatus())) {	
            HashMap a;	
            (a = MapUtils.newHashMap()).put("id", a.getId());	
            a.put("pId", "0");	
            a.put("name", StringUtils.getTreeNodeName(isShowCode, a.getRoleCode(), a.getRoleName()));	
            a.add(a);	
         }	
	
         ++a;	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"formAuthDataScope"})	
   public String formAuthDataScope(Role role, String checkbox, Model model, HttpServletRequest request) {	
      RoleDataScope a = new RoleDataScope();	
      a.setRoleCode(role.getRoleCode());	
      List a = this.roleService.findDataScopeList(a);	
      model.addAttribute("roleDataScopeList", a);	
      model.addAttribute("role", role);	
      return "modules/sys/roleFormAuthDataScope";	
   }	
	
   @RequiresPermissions({"sys:role:view"})	
   @RequestMapping({"menuTreeData"})	
   @ResponseBody	
   public Map menuTreeData(Role role, HttpServletRequest request) {	
      Map a = MapUtils.newHashMap();	
      Menu a = new Menu();	
      User a;	
      RoleController var10000;	
      if ((a = role.getCurrentUser()).isSuperAdmin()) {	
         var10000 = this;	
         a.setWeight_lt(Menu.WEIGHT_SUPER_ADMIN);	
      } else if ("1".equals(a.getMgrType())) {	
         var10000 = this;	
         a.setWeight_lt(Menu.WEIGHT_CORP_ADMIN);	
      } else if ("2".equals(a.getMgrType())) {	
         a.setWeight_lt(Menu.WEIGHT_SEC_ADMIN);	
         var10000 = this;	
      } else {	
         a.setWeight_lt(Menu.WEIGHT_DEFAULT);	
         var10000 = this;	
      }	
	
      List a = var10000.menuService.findList(a);	
      Map a = MapUtils.newLinkedHashMap();	
      Iterator var8;	
      Iterator var12 = var8 = a.iterator();	
	
      while(var12.hasNext()) {	
         Menu a = (Menu)var8.next();	
         Object a;	
         if ((a = (List)a.get(a.getSysCode())) == null) {	
            a = ListUtils.newArrayList();	
            a.put(a.getSysCode(), a);	
         }	
	
         HashMap a = MapUtils.newHashMap();	
         var12 = var8;	
         a.put("id", a.getMenuCode());	
         a.put("pId", a.getParentCode());	
         a.put("name", (new StringBuilder()).insert(0, a.getMenuName()).append("<font color=#888> &nbsp; &nbsp; ").append(StringUtils.abbr((new StringBuilder()).insert(0, ObjectUtils.toString(a.getPermission())).append(" &nbsp; ").append(ObjectUtils.toString(a.getMenuHref())).toString(), 50)).append("</font>").toString());	
         a.put("title", (new StringBuilder()).insert(0, a.getMenuName()).append("&nbsp;").append(ObjectUtils.toString(a.getPermission())).append("\n").append(ObjectUtils.toString(a.getMenuHref())).toString());	
         ((List)a).add(a);	
      }	
	
      a.put("menuMap", a);	
      a = new Menu();	
      a.setRoleCode(role.getRoleCode());	
      List a = this.menuService.findByRoleCode(a);	
      a.put("roleMenuList", a);	
      return a;	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"saveAuthDataScope"})	
   @ResponseBody	
   public String saveAuthDataScope(Role role, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.roleService.saveAuthDataScope(role);	
         return this.renderResult("true", "角色授权数据权限成功");	
      }	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @RequestMapping({"enable"})	
   @ResponseBody	
   public String enable(Role role, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         role.setStatus("0");	
         this.roleService.updateStatus(role);	
         return this.renderResult("true", "启用角色成功！");	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"hasUserRole"})	
   @ResponseBody	
   public Boolean hasUserRole(String userCode, String roleCode) {	
      return StringUtils.isNotBlank(userCode) ? RoleUtils.hasUserRole(userCode, roleCode) : RoleUtils.hasCurrentUserRole(roleCode);	
   }	
	
   @RequiresPermissions({"sys:role:view"})	
   @RequestMapping({"form"})	
   public String form(Role role, String op, Model model) {	
      if (role.getIsNewRecord()) {	
         role.setRoleSort((int)this.roleService.findCount(role) * 10);	
      }	
	
      model.addAttribute("op", op);	
      model.addAttribute("role", role);	
      return "modules/sys/roleForm";	
   }	
	
   @RequiresPermissions({"sys:role:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated Role role, String oldRoleName, String op, HttpServletRequest request) {	
      Role a;	
      if ((a = (Role)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else if (!role.getCurrentUser().isSuperAdmin() && "1".equals(role.getIsSys())) {	
         return this.renderResult("false", "保存失败，只有系统管理员才能保存为系统角色！");	
      } else if (!"true".equals(this.checkRoleName(oldRoleName, role.getRoleName()))) {	
         return this.renderResult("false", (new StringBuilder()).insert(0, "保存角色'").append(role.getRoleName()).append("'失败, 角色名称已存在").toString());	
      } else {	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "add";	
         var10001[1] = "edit";	
         if (StringUtils.inString(op, var10001)) {	
            this.roleService.save(role);	
         }	
	
         var10001 = new String[2];	
         var10003 = true;	
         var10001[0] = "add";	
         var10001[1] = "auth";	
         if (StringUtils.inString(op, var10001)) {	
            this.roleService.saveAuth(role);	
         }	
	
         return this.renderResult("true", (new StringBuilder()).insert(0, "保存角色'").append(role.getRoleName()).append("'成功！").toString());	
      }	
   }	
}	
