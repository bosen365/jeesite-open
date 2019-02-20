/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
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
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.hyperic.sigar.util.PrintfFormat;	
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
@RequestMapping(value={"${adminPath}/sys/menu"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class MenuController	
extends BaseController {	
    @Autowired	
    private ModuleService moduleService;	
    @Autowired	
    private MenuService menuService;	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"form"})	
    public String form(Menu menu, Model model) {	
        menu = this.createNextNode(menu);	
        Module a2 = new Module();	
        List<Module> a3 = this.moduleService.findList(a2);	
        model.addAttribute("moduleList", a3);	
        model.addAttribute("menu", (Object)menu);	
        return "modules/ss/menuForm";	
    }	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"list"})	
    public String list(Menu menu, Model model) {	
        if (StringUtils.isBlank((CharSequence)menu.getSysCode())) {	
            menu.setSysCode("default");	
        }	
        return "modules/sys/menuList";	
    }	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String excludeCode, String isShowHide, String sysCode, boolean isShowNameOrig, HttpServletResponse response) {	
        int a2;	
        ArrayList a3 = ListUtils.newArrayList();	
        List<Menu> a4 = this.menuService.findList(new Menu());	
        int n = a2 = 0;	
        while (n < a4.size()) {	
            Menu a5 = a4.get(a2);	
            if (!(!"0".equals(a5.getStatus()) || StringUtils.isNotBlank((CharSequence)excludeCode) && (a5.getId().equals(excludeCode) || a5.getParentCodes().contains(new StringBuilder().insert(0, ",").append(excludeCode).append(",").toString())) || StringUtils.isNotBlank((CharSequence)isShowHide) && isShowHide.equals("0") && a5.getIsShow().equals("0") || StringUtils.isNotBlank((CharSequence)sysCode) && !sysCode.equals(a5.getSysCode()))) {	
                void a6;	
                HashMap hashMap = MapUtils.newHashMap();	
                void v1 = a6;	
                v1.put("pId", a5.getParentCode());	
                hashMap.put("id", a5.getId());	
                Menu menu = a5;	
                a3.add(a6);	
                v1.put("name", isShowNameOrig ? menu.getMenuNameOrig() : menu.getMenuName());	
            }	
            n = ++a2;	
        }	
        return a3;	
    }	
	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @RequestMapping(value={"updateTreeSort"})	
    @ResponseBody	
    public String updateTreeSort(String[] ids, Integer[] sorts) {	
        int a2;	
        if (!UserUtils.getUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        int n = a2 = 0;	
        while (n < ids.length) {	
            void a3;	
            Menu menu = new Menu(ids[a2]);	
            a3.setTreeSort(sorts[a2]);	
            this.menuService.updateTreeSort((Menu)a3);	
            n = ++a2;	
        }	
        return this.renderResult("true", "保存菜单排序成功！");	
    }	
	
    @ModelAttribute	
    public Menu get(String menuCode, boolean isNewRecord) {	
        return (Menu)this.menuService.get(menuCode, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @RequestMapping(value={"fixTreeData"})	
    @ResponseBody	
    public String fixTreeData() {	
        if (!UserUtils.getUser().isAdmin()) {	
            return this.renderResult("false", "操作失败，只有管理员才能进行修复！");	
        }	
        this.menuService.fixTreeData();	
        return this.renderResult("true", "数据修复成功");	
    }	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public List<Menu> listData(Menu menu) {	
        if (StringUtils.isBlank((CharSequence)menu.getParentCode())) {	
            menu.setParentCode("0");	
        }	
        if (StringUtils.isNotBlank((CharSequence)menu.getMenuNameOrig())) {	
            menu.setParentCode(null);	
        }	
        return this.menuService.findList(menu);	
    }	
	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Menu menu) {	
        if (!menu.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        this.menuService.save(menu);	
        return this.renderResult("true", new StringBuilder().insert(0, "保存菜单'").append(menu.getMenuNameOrig()).append("'成功！").toString(), menu);	
    }	
	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Menu menu) {	
        if (!menu.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        this.menuService.delete(menu);	
        return this.renderResult("true", "删除菜单成功！");	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @RequestMapping(value={"createNextNode"})	
    @ResponseBody	
    public Menu createNextNode(Menu menu) {	
        if (StringUtils.isNotBlank((CharSequence)menu.getParentCode())) {	
            v0 = menu;	
            v0.setParent((Menu)this.menuService.get(v0.getParentCode()));	
        }	
        if (!menu.getIsNewRecord()) ** GOTO lbl20	
        a = new Menu();	
        a.setParentCode(menu.getParentCode());	
        a = this.menuService.getLastByParentCode(a);	
        if (a != null) {	
            v1 = menu;	
            v2 = v1;	
            v3 = a;	
            menu.setTreeSort(v3.getTreeSort() + 30);	
            v1.setMenuType(v3.getMenuType());	
            v1.setModuleCodes(a.getModuleCodes());	
        } else {	
            if (menu.getParent() != null) {	
                v4 = menu;	
                v4.setMenuType(v4.getParent().getMenuType());	
                v4.setModuleCodes(v4.getParent().getModuleCodes());	
            }	
lbl20: // 4 sources:	
            v2 = menu;	
        }	
        if (v2.getTreeSort() == null) {	
            menu.setTreeSort(30);	
        }	
        if (menu.getWeight() == null) {	
            menu.setWeight(Menu.WEIGHT_SEC_ADMIN);	
        }	
        if (StringUtils.isBlank((CharSequence)menu.getSysCode())) {	
            menu.setSysCode("default");	
        }	
        if (StringUtils.isBlank((CharSequence)menu.getMenuType())) {	
            menu.setMenuType("1");	
        }	
        if (StringUtils.isBlank((CharSequence)menu.getIsShow()) == false) return menu;	
        menu.setIsShow("1");	
        return menu;	
    }	
}	
	
