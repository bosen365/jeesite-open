package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.HashMap;	
import java.util.List;	
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
@RequestMapping({"${adminPath}/sys/menu"})	
@ConditionalOnProperty(	
   name = {"web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MenuController extends BaseController {	
   @Autowired	
   private ModuleService moduleService;	
   @Autowired	
   private MenuService menuService;	
	
   @RequiresPermissions({"sys:menu:view"})	
   @RequestMapping({"form"})	
   public String form(Menu menu, Model model) {	
      menu = this.createNextNode(menu);	
      model.addAttribute("menu", menu);	
      Module a = new Module();	
      List a = this.moduleService.findList(a);	
      model.addAttribute("moduleList", a);	
      return "modules/ss/menuForm";	
   }	
	
   @RequiresPermissions({"sys:menu:view"})	
   @RequestMapping({"list"})	
   public String list(Menu menu, Model model) {	
      if (StringUtils.isBlank(menu.getSysCode())) {	
         menu.setSysCode("default");	
      }	
	
      return "modules/sys/menuList";	
   }	
	
   @RequiresPermissions({"sys:menu:view"})	
   @RequestMapping({"treeData"})	
   @ResponseBody	
   public List treeData(String excludeCode, String isShowHide, String sysCode, boolean isShowNameOrig, HttpServletResponse response) {	
      List a = ListUtils.newArrayList();	
      List a = this.menuService.findList(new Menu());	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.size(); var10000 = a) {	
         Menu a = (Menu)a.get(a);	
         if ("0".equals(a.getStatus()) && (!StringUtils.isNotBlank(excludeCode) || !a.getId().equals(excludeCode) && !a.getParentCodes().contains((new StringBuilder()).insert(0, ",").append(excludeCode).append(",").toString())) && (!StringUtils.isNotBlank(isShowHide) || !isShowHide.equals("0") || !a.getIsShow().equals("0")) && (!StringUtils.isNotBlank(sysCode) || sysCode.equals(a.getSysCode()))) {	
            HashMap a = MapUtils.newHashMap();	
            a.put("id", a.getId());	
            a.put("pId", a.getParentCode());	
            a.put("name", isShowNameOrig ? a.getMenuNameOrig() : a.getMenuName());	
            a.add(a);	
         }	
	
         ++a;	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"sys:menu:edit"})	
   @RequestMapping({"updateTreeSort"})	
   @ResponseBody	
   public String updateTreeSort(String[] ids, Integer[] sorts) {	
      if (!UserUtils.getUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
      } else {	
         int a;	
         for(int var10000 = a = 0; var10000 < ids.length; var10000 = a) {	
            Menu a = new Menu(ids[a]);	
            a.setTreeSort(sorts[a]);	
            ++a;	
            this.menuService.updateTreeSort(a);	
         }	
	
         return this.renderResult("true", "保存菜单排序成功！");	
      }	
   }	
	
   @ModelAttribute	
   public Menu get(String menuCode, boolean isNewRecord) {	
      return (Menu)this.menuService.get(menuCode, isNewRecord);	
   }	
	
   @RequiresPermissions({"sys:menu:edit"})	
   @RequestMapping({"fixTreeData"})	
   @ResponseBody	
   public String fixTreeData() {	
      if (!UserUtils.getUser().isAdmin()) {	
         return this.renderResult("false", "操作失败，只有管理员才能进行修复！");	
      } else {	
         this.menuService.fixTreeData();	
         return this.renderResult("true", "数据修复成功");	
      }	
   }	
	
   @RequiresPermissions({"sys:menu:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public List listData(Menu menu) {	
      if (StringUtils.isBlank(menu.getParentCode())) {	
         menu.setParentCode("0");	
      }	
	
      if (StringUtils.isNotBlank(menu.getMenuNameOrig())) {	
         menu.setParentCode((String)null);	
      }	
	
      return this.menuService.findList(menu);	
   }	
	
   @RequiresPermissions({"sys:menu:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated Menu menu) {	
      if (!menu.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
      } else {	
         this.menuService.save(menu);	
         return this.renderResult("true", (new StringBuilder()).insert(0, "保存菜单'").append(menu.getMenuNameOrig()).append("'成功！").toString(), menu);	
      }	
   }	
	
   @RequiresPermissions({"sys:menu:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(Menu menu) {	
      if (!menu.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
      } else {	
         this.menuService.delete(menu);	
         return this.renderResult("true", "删除菜单成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:menu:edit"})	
   @RequestMapping({"createNextNode"})	
   @ResponseBody	
   public Menu createNextNode(Menu menu) {	
      if (StringUtils.isNotBlank(menu.getParentCode())) {	
         menu.setParent((Menu)this.menuService.get((String)menu.getParentCode()));	
      }	
	
      Menu var10000;	
      label40: {	
         if (menu.getIsNewRecord()) {	
            Menu a = new Menu();	
            a.setParentCode(menu.getParentCode());	
            Menu a;	
            if ((a = (Menu)this.menuService.getLastByParentCode(a)) != null) {	
               var10000 = menu;	
               menu.setTreeSort(a.getTreeSort() + 30);	
               menu.setMenuType(a.getMenuType());	
               menu.setModuleCodes(a.getModuleCodes());	
               break label40;	
            }	
	
            if (menu.getParent() != null) {	
               menu.setMenuType(menu.getParent().getMenuType());	
               menu.setModuleCodes(menu.getParent().getModuleCodes());	
            }	
         }	
	
         var10000 = menu;	
      }	
	
      if (var10000.getTreeSort() == null) {	
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
	
      if (StringUtils.isBlank(menu.getIsShow())) {	
         menu.setIsShow("1");	
      }	
	
      return menu;	
   }	
}	
