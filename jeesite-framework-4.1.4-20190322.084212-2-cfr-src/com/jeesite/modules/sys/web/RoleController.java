/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.l.m;	
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
import java.util.function.Consumer;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.FileSystemUsage;	
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
    private UserService userService;	
    @Autowired	
    private RoleService roleService;	
    @Autowired	
    private MenuService menuService;	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"list"})	
    public String list(Role role, Model model) {	
        return "modules/sys/roleList";	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String userType, Boolean isAll, String isShowCode, String ctrlPermi) {	
        void a;	
        ArrayList<Map<String, Object>> a2 = ListUtils.newArrayList();	
        Role role = new Role();	
        a.setStatus("0");	
        if (isAll == null || !isAll.booleanValue()) {	
            a.setUserType(StringUtils.defaultIfBlank(userType, "employee"));	
            this.roleService.addDataScopeFilter((Role)a, ctrlPermi);	
        }	
        this.roleService.findList((Role)a).forEach(e2 -> {	
            HashMap<String, String> a = MapUtils.newHashMap();	
            a.put("id", e2.getId());	
            a.put("pId", "0");	
            a.put("name", StringUtils.getTreeNodeName(isShowCode, e2.getRoleCode(), e2.getRoleName()));	
            a2.add(a);	
        });	
        return a2;	
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
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"disable"})	
    @ResponseBody	
    public String disable(Role role, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        role.setStatus("2");	
        RoleController roleController = this;	
        roleController.roleService.updateStatus(role);	
        return roleController.renderResult("true", "停用角色成功！");	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"menuTreeData"})	
    @ResponseBody	
    public Map<String, Object> menuTreeData(Role role, HttpServletRequest request) {	
        Iterator<Menu> iterator;	
        RoleController roleController;	
        HashMap<String, Object> a = MapUtils.newHashMap();	
        Menu a2 = new Menu();	
        User a3 = role.getCurrentUser();	
        if (a3.isSuperAdmin()) {	
            roleController = this;	
            a2.setWeight_lt(Menu.WEIGHT_SUPER_ADMIN);	
        } else if ("1".equals(a3.getMgrType())) {	
            roleController = this;	
            a2.setWeight_lt(Menu.WEIGHT_CORP_ADMIN);	
        } else {	
            Menu menu = a2;	
            if ("2".equals(a3.getMgrType())) {	
                menu.setWeight_lt(Menu.WEIGHT_SEC_ADMIN);	
                roleController = this;	
            } else {	
                menu.setWeight_lt(Menu.WEIGHT_DEFAULT);	
                roleController = this;	
            }	
        }	
        List<Menu> a4 = roleController.menuService.findList(a2);	
        LinkedHashMap a5 = MapUtils.newLinkedHashMap();	
        Iterator<Menu> iterator2 = iterator = a4.iterator();	
        while (iterator2.hasNext()) {	
            void a6;	
            Menu a7 = iterator.next();	
            ArrayList<void> a8 = (ArrayList<void>)a5.get(a7.getSysCode());	
            if (a8 == null) {	
                a8 = ListUtils.newArrayList();	
                a5.put(a7.getSysCode(), a8);	
            }	
            HashMap hashMap = MapUtils.newHashMap();	
            iterator2 = iterator;	
            a6.put("id", a7.getMenuCode());	
            a6.put("pId", a7.getParentCode());	
            a6.put("name", new StringBuilder().insert(0, a7.getMenuName()).append("<font color=#888> &nbsp; &nbsp; ").append(StringUtils.abbr(new StringBuilder().insert(0, ObjectUtils.toString(a7.getPermission())).append(" &nbsp; ").append(ObjectUtils.toString(a7.getMenuHref())).toString(), 50)).append("</font>").toString());	
            a6.put("title", new StringBuilder().insert(0, a7.getMenuName()).append("&nbsp;").append(ObjectUtils.toString(a7.getPermission())).append("\n").append(ObjectUtils.toString(a7.getMenuHref())).toString());	
            a8.add(a6);	
        }	
        HashMap<String, Object> hashMap = a;	
        hashMap.put("menuMap", a5);	
        a2 = new Menu();	
        a2.setRoleCode(role.getRoleCode());	
        List<Menu> a9 = this.menuService.findByRoleCode(a2);	
        a.put("roleMenuList", a9);	
        return hashMap;	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"hasUserRole"})	
    @ResponseBody	
    public Boolean hasUserRole(String userCode, String roleCode) {	
        if (StringUtils.isNotBlank(userCode)) {	
            return RoleUtils.hasUserRole(userCode, roleCode);	
        }	
        return RoleUtils.hasCurrentUserRole(roleCode);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"formAuthDataScope"})	
    public String formAuthDataScope(Role role, String checkbox, Model model, HttpServletRequest request) {	
        void a;	
        void a2;	
        RoleDataScope roleDataScope = new RoleDataScope();	
        a2.setRoleCode(role.getRoleCode());	
        List<RoleDataScope> list = this.roleService.findDataScopeList((RoleDataScope)a2);	
        model.addAttribute("roleDataScopeList", a);	
        model.addAttribute("role", role);	
        return "modules/sys/roleFormAuthDataScope";	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Role role, HttpServletRequest request) {	
        if (Role.CORP_ADMIN_ROLE_CODE.equals(role.getRoleCode())) {	
            return this.renderResult("false", "非法操作，此角色为内置角色，不允许删除！");	
        }	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.delete(role);	
        return this.renderResult("true", "删除角色成功！");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"saveAuthUser"})	
    @ResponseBody	
    public String saveAuthUser(Role role, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.saveAuthUser(role);	
        return this.renderResult("true", "角色授权给用户成功");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Role role, String oldRoleName, String op, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        if (!role.getCurrentUser().isSuperAdmin() && "1".equals(role.getIsSys())) {	
            return this.renderResult("false", "保存失败，只有系统管理员才能保存为系统角色！");	
        }	
        if (!"true".equals(this.checkRoleName(oldRoleName, role.getRoleName()))) {	
            return this.renderResult("false", new StringBuilder().insert(0, "保存角色'").append(role.getRoleName()).append("'失败, 角色名称已存在").toString());	
        }	
        String[] arrstring = new String[2];	
        arrstring[0] = "add";	
        arrstring[1] = "edit";	
        if (StringUtils.inString(op, arrstring)) {	
            this.roleService.save(role);	
        }	
        String[] arrstring2 = new String[2];	
        arrstring2[0] = "add";	
        arrstring2[1] = "auth";	
        if (StringUtils.inString(op, arrstring2)) {	
            this.roleService.saveAuth(role);	
        }	
        return this.renderResult("true", new StringBuilder().insert(0, "保存角色'").append(role.getRoleName()).append("'成功！").toString());	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"checkRoleName"})	
    @ResponseBody	
    public String checkRoleName(String oldRoleName, String roleName) {	
        Role a = new Role();	
        String string = roleName;	
        a.setRoleName(string);	
        if (string != null && roleName.equals(oldRoleName)) {	
            return "true";	
        }	
        if (roleName != null && this.roleService.getByRoleName(a) == null) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"saveAuthDataScope"})	
    @ResponseBody	
    public String saveAuthDataScope(Role role, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.saveAuthDataScope(role);	
        return this.renderResult("true", "角色授权数据权限成功");	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"formAuthUser"})	
    public String formAuthUser(Role role, Model model, HttpServletRequest request) {	
        Iterator<User> iterator;	
        HashMap<String, void> a = MapUtils.newHashMap();	
        User a2 = new User(role);	
        Iterator<User> iterator2 = iterator = this.userService.findListByRoleCode(a2).iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            User user = iterator.next();	
            iterator2 = iterator;	
            a.put(a3.getUserCode(), a3);	
        }	
        model.addAttribute("dataMap", a);	
        model.addAttribute("role", role);	
        return "modules/sys/roleFormAuthUser";	
    }	
	
    @RequiresPermissions(value={"sys:role:view"})	
    @RequestMapping(value={"form"})	
    public String form(Role role, String op, Model model) {	
        if (role.getIsNewRecord()) {	
            Role role2 = role;	
            Role role3 = role;	
            role3.setRoleSort((int)this.roleService.findCount(role3) * 10);	
            role2.setUserType("employee");	
            role2.setIsSys("0");	
        }	
        model.addAttribute("op", op);	
        model.addAttribute("role", role);	
        return "modules/sys/roleForm";	
    }	
	
    @ModelAttribute	
    public Role get(String roleCode, boolean isNewRecord) {	
        return (Role)this.roleService.get(roleCode, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"deleteAuthUser"})	
    @ResponseBody	
    public String deleteAuthUser(Role role, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.roleService.deleteAuthUser(role);	
        return this.renderResult("true", "取消用户和角色授权成功");	
    }	
	
    @RequiresPermissions(value={"sys:role:edit"})	
    @RequestMapping(value={"enable"})	
    @ResponseBody	
    public String enable(Role role, HttpServletRequest request) {	
        Role a = (Role)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !role.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        role.setStatus("0");	
        RoleController roleController = this;	
        roleController.roleService.updateStatus(role);	
        return roleController.renderResult("true", "启用角色成功！");	
    }	
}	
	
