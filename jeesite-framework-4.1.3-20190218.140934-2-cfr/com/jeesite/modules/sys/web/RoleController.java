/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.validation.annotation.Validated	
 *  org.springframework.web.bind.annotation.ModelAttribute	
 *  org.springframework.web.bind.annotation.PostMapping	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
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
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
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
@RequestMapping(value={"${adminPath}/sys/role"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class RoleController	
extends BaseController {	
    @Autowired	
    private MenuService menuService;	
    @Autowired	
    private UserService userService;	
    @Autowired	
    private RoleService roleService;	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"checkRoleName"})	
    @ResponseBody	
    public String checkRoleName(String oldRoleName, String roleName) {	
        Role a2 = new Role();	
        String string = roleName;	
        a2.setRoleName(string);	
        if (string != null && roleName.equals(oldRoleName)) {	
            return "true";	
        }	
        if (roleName != null && this.roleService.getByRoleName(a2) == null) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"list"})	
    public String list(Role role, Model model) {	
        return "modules/sys/roleList";	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Role role, HttpServletRequest request) {	
        if (Role.CORP_ADMIN_ROLE_CODE.equals(role.getRoleCode())) {	
            return this.renderResult("false", "非法操作，此角色为内置角色，不允许删除！");	
        }	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.delete(role);	
        return this.renderResult("true", "删除角色成功！");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"disable"})	
    @ResponseBody	
    public String disable(Role role, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        role.setStatus("2");	
        RoleController roleController = this;	
        roleController.roleService.updateStatus(role);	
        return roleController.renderResult("true", "停用角色成功！");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"formAuthUser"})	
    public String formAuthUser(Role role, Model model, HttpServletRequest request) {	
        Iterator<User> iterator;	
        HashMap a2 = MapUtils.newHashMap();	
        User a3 = new User(role);	
        Iterator<User> iterator2 = iterator = this.userService.findListByRoleCode(a3).iterator();	
        while (iterator2.hasNext()) {	
            void a4;	
            User user = iterator.next();	
            iterator2 = iterator;	
            a2.put(a4.getUserCode(), a4);	
        }	
        model.addAttribute("role", (Object)role);	
        model.addAttribute("dataMap", (Object)a2);	
        return "modules/sys/roleFormAuthUser";	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"deleteAuthUser"})	
    @ResponseBody	
    public String deleteAuthUser(Role role, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.deleteAuthUser(role);	
        return this.renderResult("true", "取消用户和角色授权成功");	
    }	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Role> listData(Role role, String ctrlPermi, HttpServletRequest request, HttpServletResponse response) {	
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
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"saveAuthUser"})	
    @ResponseBody	
    public String saveAuthUser(Role role, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.saveAuthUser(role);	
        return this.renderResult("true", "角色授权给用户成功");	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String userType, Boolean isAll, String isShowCode, String ctrlPermi) {	
        void a2;	
        int a3;	
        ArrayList a4 = ListUtils.newArrayList();	
        Role role = new Role();	
        a2.setStatus("0");	
        if (isAll == null || !isAll.booleanValue()) {	
            a2.setUserType((String)StringUtils.defaultIfBlank((CharSequence)userType, (CharSequence)"employee"));	
            this.roleService.addDataScopeFilter((Role)a2, ctrlPermi);	
        }	
        List<Role> a5 = this.roleService.findList((Role)a2);	
        int n = a3 = 0;	
        while (n < a5.size()) {	
            Role a6 = a5.get(a3);	
            if ("0".equals(a6.getStatus())) {	
                HashMap a7 = MapUtils.newHashMap();	
                a4.add(a7);	
                a7.put("name", StringUtils.getTreeNodeName((String)isShowCode, (String)a6.getRoleCode(), (String)a6.getRoleName()));	
                a7.put("pId", "0");	
                a7.put("id", a6.getId());	
            }	
            n = ++a3;	
        }	
        return a4;	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"formAuthDataScope"})	
    public String formAuthDataScope(Role role, String checkbox, Model model, HttpServletRequest request) {	
        void a2;	
        void a3;	
        RoleDataScope roleDataScope = new RoleDataScope();	
        a3.setRoleCode(role.getRoleCode());	
        List<RoleDataScope> list = this.roleService.findDataScopeList((RoleDataScope)a3);	
        model.addAttribute("role", (Object)role);	
        model.addAttribute("roleDataScopeList", (Object)a2);	
        return "modules/sys/roleFormAuthDataScope";	
    }	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"menuTreeData"})	
    @ResponseBody	
    public Map<String, Object> menuTreeData(Role role, HttpServletRequest request) {	
        Iterator<Menu> iterator;	
        RoleController roleController;	
        HashMap a2 = MapUtils.newHashMap();	
        Menu a3 = new Menu();	
        User a4 = role.getCurrentUser();	
        if (a4.isSuperAdmin()) {	
            roleController = this;	
            a3.setWeight_lt(Menu.WEIGHT_SUPER_ADMIN);	
        } else if ("1".equals(a4.getMgrType())) {	
            roleController = this;	
            a3.setWeight_lt(Menu.WEIGHT_CORP_ADMIN);	
        } else {	
            Menu menu = a3;	
            if ("2".equals(a4.getMgrType())) {	
                menu.setWeight_lt(Menu.WEIGHT_SEC_ADMIN);	
                roleController = this;	
            } else {	
                menu.setWeight_lt(Menu.WEIGHT_DEFAULT);	
                roleController = this;	
            }	
        }	
        List<Menu> a5 = roleController.menuService.findList(a3);	
        LinkedHashMap a6 = MapUtils.newLinkedHashMap();	
        Iterator<Menu> iterator2 = iterator = a5.iterator();	
        while (iterator2.hasNext()) {	
            void a7;	
            Menu a8 = iterator.next();	
            List a9 = (List)a6.get(a8.getSysCode());	
            if (a9 == null) {	
                a9 = ListUtils.newArrayList();	
                a6.put(a8.getSysCode(), a9);	
            }	
            HashMap hashMap = MapUtils.newHashMap();	
            iterator2 = iterator;	
            a9.add(a7);	
            a7.put("title", new StringBuilder().insert(0, a8.getMenuName()).append("&nbsp;").append(ObjectUtils.toString((Object)a8.getPermission())).append("\n").append(ObjectUtils.toString((Object)a8.getMenuHref())).toString());	
            a7.put("name", new StringBuilder().insert(0, a8.getMenuName()).append("<font color=#888> &nbsp; &nbsp; ").append(StringUtils.abbr((String)new StringBuilder().insert(0, ObjectUtils.toString((Object)a8.getPermission())).append(" &nbsp; ").append(ObjectUtils.toString((Object)a8.getMenuHref())).toString(), (int)50)).append("</font>").toString());	
            a7.put("pId", a8.getParentCode());	
            a7.put("id", a8.getMenuCode());	
        }	
        HashMap hashMap = a2;	
        a3 = new Menu();	
        a3.setRoleCode(role.getRoleCode());	
        List<Menu> a10 = this.menuService.findByRoleCode(a3);	
        a2.put("roleMenuList", a10);	
        hashMap.put("menuMap", a6);	
        return hashMap;	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"saveAuthDataScope"})	
    @ResponseBody	
    public String saveAuthDataScope(Role role, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.saveAuthDataScope(role);	
        return this.renderResult("true", "角色授权数据权限成功");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"enable"})	
    @ResponseBody	
    public String enable(Role role, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        role.setStatus("0");	
        RoleController roleController = this;	
        roleController.roleService.updateStatus(role);	
        return roleController.renderResult("true", "启用角色成功！");	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"hasUserRole"})	
    @ResponseBody	
    public Boolean hasUserRole(String userCode, String roleCode) {	
        if (StringUtils.isNotBlank((CharSequence)userCode)) {	
            return RoleUtils.hasUserRole(userCode, roleCode);	
        }	
        return RoleUtils.hasCurrentUserRole(roleCode);	
    }	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"form"})	
    public String form(Role role, String op, Model model) {	
        if (role.getIsNewRecord()) {	
            Role role2 = role;	
            role2.setRoleSort((int)this.roleService.findCount(role2) * 10);	
        }	
        model.addAttribute("role", (Object)role);	
        model.addAttribute("op", (Object)op);	
        return "modules/sys/roleForm";	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Role role, String oldRoleName, String op, HttpServletRequest request) {	
        Role a2 = (Role)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        if (!role.getCurrentUser().isSuperAdmin() && "1".equals(role.getIsSys())) {	
            return this.renderResult("false", "保存失败，只有系统管理员才能保存为系统角色！");	
        }	
        if (!"true".equals(this.checkRoleName(oldRoleName, role.getRoleName()))) {	
            return this.renderResult("false", new StringBuilder().insert(0, "保存角色'").append(role.getRoleName()).append("'失败, 角色名称已存在").toString());	
        }	
        if (StringUtils.inString((String)op, (String[])new String[]{"add", "edit"})) {	
            this.roleService.save(role);	
        }	
        if (StringUtils.inString((String)op, (String[])new String[]{"add", "auth"})) {	
            this.roleService.saveAuth(role);	
        }	
        return this.renderResult("true", new StringBuilder().insert(0, "保存角色'").append(role.getRoleName()).append("'成功！").toString());	
    }	
}	
	
