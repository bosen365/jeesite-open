/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.win32.FileVersion;	
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
@RequestMapping(value={"${adminPath}/sys/module"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class ModuleController	
extends BaseController {	
    @Autowired	
    private ModuleService moduleService;	
	
    @RequiresPermissions(value={"sys:module:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Module> listData(Module module, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void request;	
        void response;	
        void module2;	
        module2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.moduleService.findPage((Module)module2);	
    }	
	
    @RequiresPermissions(value={"sys:module:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Module module) {	
        if (!module.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        this.moduleService.save(module);	
        return this.renderResult("true", "保存模块成功！");	
    }	
	
    @ModelAttribute	
    public Module get(String moduleCode, boolean isNewRecord) {	
        return (Module)this.moduleService.get(moduleCode, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:module:edit"})	
    @RequestMapping(value={"enable"})	
    @ResponseBody	
    public String enable(Module module) {	
        if (!module.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        module.setStatus("0");	
        ModuleController moduleController = this;	
        moduleController.moduleService.updateStatus(module);	
        return moduleController.renderResult("true", new StringBuilder().insert(0, "启用模块").append(module.getModuleName()).append("成功").toString());	
    }	
	
    @RequiresPermissions(value={"sys:module:view"})	
    @RequestMapping(value={"list"})	
    public String list(Module module, Model model) {	
        module.setStatus("");	
        return "modules/sys/moduleList";	
    }	
	
    @RequiresPermissions(value={"sys:module:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Module module) {	
        if (!module.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        if ("core".equals(module.getModuleCode())) {	
            return this.renderResult("false", "核心模块，不允许禁用！");	
        }	
        this.moduleService.delete(module);	
        return this.renderResult("true", "删除模块成功！");	
    }	
	
    @RequiresPermissions(value={"sys:module:edit"})	
    @RequestMapping(value={"disable"})	
    @ResponseBody	
    public String disable(Module module) {	
        if (!module.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改此数据！");	
        }	
        if ("core".equals(module.getModuleCode())) {	
            return this.renderResult("false", "核心模块，不允许禁用！");	
        }	
        module.setStatus("2");	
        ModuleController moduleController = this;	
        moduleController.moduleService.updateStatus(module);	
        return moduleController.renderResult("true", new StringBuilder().insert(0, "停用模块").append(module.getModuleName()).append("成功").toString());	
    }	
	
    @RequiresPermissions(value={"sys:module:view"})	
    @RequestMapping(value={"form"})	
    public String form(Module module, Model model) {	
        if (StringUtils.isBlank(module.getMainClassName())) {	
            module.setMainClassName("com.jeesite.modules.sys.web.LoginController");	
        }	
        model.addAttribute("modul", module);	
        return "modules/sys/moduleForm";	
    }	
	
    @RequiresPermissions(value={"sys:module:edit"})	
    @RequestMapping(value={"checkModuleCode"})	
    @ResponseBody	
    public String checkModuleCode(String oldCode, String moduleCode) {	
        Module a = new Module();	
        String string = moduleCode;	
        a.setModuleCode(string);	
        if (string != null && moduleCode.equals(oldCode)) {	
            return "true";	
        }	
        if (moduleCode != null && this.moduleService.get(a) == null) {	
            return "true";	
        }	
        return "false";	
    }	
}	
	
