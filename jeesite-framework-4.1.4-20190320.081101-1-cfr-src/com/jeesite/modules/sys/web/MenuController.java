/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
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
import org.hyperic.sigar.ThreadCpu;	
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
    private MenuService menuService;	
    @Autowired	
    private ModuleService moduleService;	
	
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
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String excludeCode, String isShowHide, String sysCode, boolean isShowNameOrig, HttpServletResponse response) {	
        int a;	
        ArrayList<Map<String, Object>> a2 = ListUtils.newArrayList();	
        List<Menu> a3 = this.menuService.findList(new Menu());	
        int n = a = 0;	
        while (n < a3.size()) {	
            Menu a4 = a3.get(a);	
            if (!(!"0".equals(a4.getStatus()) || StringUtils.isNotBlank(excludeCode) && (a4.getId().equals(excludeCode) || a4.getParentCodes().contains(new StringBuilder().insert(0, ",").append(excludeCode).append(",").toString())) || StringUtils.isNotBlank(isShowHide) && isShowHide.equals("0") && a4.getIsShow().equals("0") || StringUtils.isNotBlank(sysCode) && !sysCode.equals(a4.getSysCode()))) {	
                void a5;	
                HashMap<String, String> hashMap = MapUtils.newHashMap();	
                void v1 = a5;	
                v1.put("pId", a4.getParentCode());	
                hashMap.put("id", a4.getId());	
                Menu menu = a4;	
                a2.add((Map<String, Object>)a5);	
                v1.put("name", isShowNameOrig ? menu.getMenuNameOrig() : menu.getMenuName());	
            }	
            n = ++a;	
        }	
        return a2;	
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
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public List<Menu> listData(Menu menu) {	
        if (StringUtils.isBlank(menu.getParentCode())) {	
            menu.setParentCode("0");	
        }	
        if (StringUtils.isNotBlank(menu.getMenuNameOrig())) {	
            menu.setParentCode(null);	
        }	
        return this.menuService.findList(menu);	
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
        if (StringUtils.isNotBlank(menu.getParentCode())) {	
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
        if (StringUtils.isBlank(menu.getSysCode())) {	
            menu.setSysCode("default");	
        }	
        if (StringUtils.isBlank(menu.getMenuType())) {	
            menu.setMenuType("1");	
        }	
        if (StringUtils.isBlank(menu.getIsShow()) == false) return menu;	
        menu.setIsShow("1");	
        return menu;	
    }	
	
    @RequiresPermissions(value={"sys:menu:edit"})	
    @RequestMapping(value={"updateTreeSort"})	
    @ResponseBody	
    public String updateTreeSort(String[] ids, Integer[] sorts) {	
        int a;	
        if (!UserUtils.getUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        int n = a = 0;	
        while (n < ids.length) {	
            void a2;	
            Menu menu = new Menu(ids[a]);	
            a2.setTreeSort(sorts[a]);	
            this.menuService.updateTreeSort((Menu)a2);	
            n = ++a;	
        }	
        return this.renderResult("true", "保存菜单排序成功！");	
    }	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"list"})	
    public String list(Menu menu, Model model) {	
        if (StringUtils.isBlank(menu.getSysCode())) {	
            menu.setSysCode("default");	
        }	
        return "modules/sys/menuList";	
    }	
	
    @ModelAttribute	
    public Menu get(String menuCode, boolean isNewRecord) {	
        return (Menu)this.menuService.get(menuCode, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:menu:view"})	
    @RequestMapping(value={"form"})	
    public String form(Menu menu, Model model) {	
        menu = this.createNextNode(menu);	
        Module a = new Module();	
        List<Module> a2 = this.moduleService.findList(a);	
        model.addAttribute("moduleList", a2);	
        model.addAttribute("menu", menu);	
        return "modules/sys/menuFom";	
    }	
}	
	
