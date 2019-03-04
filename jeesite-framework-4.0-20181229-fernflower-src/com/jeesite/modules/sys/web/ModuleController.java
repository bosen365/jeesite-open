package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/sys/module"})	
public class ModuleController extends BaseController {	
   @Autowired	
   private ModuleService moduleService;	
	
   @RequiresPermissions({"sys:module:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(Module module) {	
      if (!module.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", ValidCodeController.ALLATORIxDEMO ("趡杗擦佈Ｇ叾朢趑續箵琭呌扦胩俅攭歏敤捅５"));	
      } else if ("core".equals(module.getModuleCode())) {	
         return this.renderResult("false", "核心模块，不允许禁用！");	
      } else {	
         this.moduleService.delete(module);	
         return this.renderResult("true", ValidCodeController.ALLATORIxDEMO ("刴陏樵坼戄労５"));	
      }	
   }	
	
   @RequiresPermissions({"sys:module:edit"})	
   @RequestMapping({"enable"})	
   @ResponseBody	
   public String enable(Module module) {	
      if (!module.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
      } else {	
         module.setStatus("0");	
         this.moduleService.updateStatus(module);	
         return this.renderResult("true", (new StringBuilder()).insert(0, ValidCodeController.ALLATORIxDEMO ("各甼樊坃")).append(module.getModuleName()).append("成功").toString());	
      }	
   }	
	
   @RequiresPermissions({"sys:module:view"})	
   @RequestMapping({"list"})	
   public String list(Module module, Model var2) {	
      module.setStatus("");	
      return ValidCodeController.ALLATORIxDEMO ("F{OaGqX;XmX;F{OaGqg}X`");	
   }	
	
   @ModelAttribute	
   public Module get(String moduleCode, boolean isNewRecord) {	
      return (Module)this.moduleService.get(moduleCode, isNewRecord);	
   }	
	
   @RequiresPermissions({"sys:module:view"})	
   @RequestMapping({"form"})	
   public String form(Module module, Model model) {	
      if (StringUtils.isBlank(module.getMainClassName())) {	
         module.setMainClassName("com.jeesite.modules.sys.web.LoginConroller");	
      }	
	
      model.addAttribute(ValidCodeController.ALLATORIxDEMO ("F{OaGq"), module);	
      return "modules/sys/moduleForm";	
   }	
	
   @RequiresPermissions({"sys:module:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(Module module, HttpServletRequest request, HttpServletResponse response) {	
      return this.moduleService.findPage(new Page(request, response), module);	
   }	
	
   @RequiresPermissions({"sys:module:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated Module module) {	
      if (!module.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", ValidCodeController.ALLATORIxDEMO ("趡杗擦佈Ｇ叾朢趑續箵琭呌扦胩俅攭歏敤捅５"));	
      } else {	
         this.moduleService.save(module);	
         return this.renderResult("true", "保存模块成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:module:edit"})	
   @RequestMapping({"disable"})	
   @ResponseBody	
   public String disable(Module module) {	
      if (!module.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", ValidCodeController.ALLATORIxDEMO ("趡杗擦佈Ｇ叾朢趑續箵琭呌扦胩俅攭歏敤捅５"));	
      } else if ("core".equals(module.getModuleCode())) {	
         return this.renderResult("false", "核心模块，不允许禁用！");	
      } else {	
         module.setStatus("2");	
         this.moduleService.updateStatus(module);	
         return this.renderResult("true", (new StringBuilder()).insert(0, ValidCodeController.ALLATORIxDEMO ("偷甼樊坃")).append(module.getModuleName()).append("成功").toString());	
      }	
   }	
	
   @RequiresPermissions({"sys:module:edit"})	
   @RequestMapping({"checkModuleCode"})	
   @ResponseBody	
   public String checkModuleCode(String oldCode, String moduleCode) {	
      Module a;	
      (a = new Module()).setModuleCode(moduleCode);	
      if (moduleCode != null && moduleCode.equals(oldCode)) {	
         return "true";	
      } else {	
         return moduleCode != null && this.moduleService.get(a) == null ? "true" : "false";	
      }	
   }	
}	
