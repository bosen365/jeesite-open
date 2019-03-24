/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.l.I;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.e.j;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.service.ModuleService;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.NetRoute;	
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
@RequestMapping(value={"${adminPath}/sys/lang"})	
@ConditionalOnProperty(name={"lang.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class LangController	
extends BaseController {	
    @Autowired	
    private LangService langService;	
    @Autowired	
    private ModuleService moduleService;	
	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"form"})	
    public String form(Lang lang, Model model) {	
        model.addAttribute("lang", lang);	
        Module a = new Module();	
        List<Module> a2 = this.moduleService.findList(a);	
        model.addAttribute("moduleList", a2);	
        return "modules/sys/langForm";	
    }	
	
    @ModelAttribute	
    public Lang get(String id, boolean isNewRecord) {	
        return (Lang)this.langService.get(id, isNewRecord);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:lang:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Lang lang, String oldLangCode, String oldLangType) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        if (oldLangCode == null || !oldLangCode.equals(lang.getLangCode()) || oldLangType == null || !oldLangType.equals(lang.getLangType())) {	
            void a;	
            Lang lang2 = new Lang();	
            void v0 = a;	
            v0.setLangCode(lang.getLangCode());	
            v0.setLangType(lang.getLangType());	
            if (this.langService.findList(a).size() > 0) {	
                return this.renderResult("false", "编码和类型已经存在！");	
            }	
        }	
        this.langService.save(lang);	
        return this.renderResult("true", "保存语言成功！");	
    }	
	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"list", ""})	
    public String list(Lang lang, Model model) {	
        model.addAttribute("lang", lang);	
        Module a = new Module();	
        List<Module> a2 = this.moduleService.findList(a);	
        model.addAttribute("moduleList", a2);	
        return "modules/sys/langList";	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Lang> listData(Lang lang, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void lang2;	
        void response;	
        void request;	
        lang2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.langService.findPage((Lang)lang2);	
    }	
	
    @RequiresPermissions(value={"sys:lang:edit"})	
    @RequestMapping(value={"clearCache"})	
    @ResponseBody	
    public String clearCache() {	
        LangController langController = this;	
        langController.langService.clearCache();	
        return langController.renderResult("true", "清理缓存成功！");	
    }	
	
    @RequiresPermissions(value={"sys:lang:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Lang lang) {	
        LangController langController = this;	
        langController.langService.delete(lang);	
        return langController.renderResult("true", "删除语言成功！");	
    }	
}	
	
