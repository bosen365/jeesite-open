/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
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
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.v.m;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.service.ModuleService;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
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
    private ModuleService moduleService;	
    @Autowired	
    private LangService langService;	
	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"form"})	
    public String form(Lang lang, Model model) {	
        Module a = new Module();	
        List<Module> a2 = this.moduleService.findList(a);	
        model.addAttribute("moduleList", a2);	
        model.addAttribute("lang", (Object)lang);	
        return "modules/sys/langForm";	
    }	
	
    @RequiresPermissions(value={"sys:lang:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Lang lang, String oldLangCode, String oldLangType) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
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
	
    @ModelAttribute	
    public Lang get(String id, boolean isNewRecord) {	
        return (Lang)this.langService.get(id, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"list", ""})	
    public String list(Lang lang, Model model) {	
        Module a = new Module();	
        List<Module> a2 = this.moduleService.findList(a);	
        model.addAttribute("moduleList", a2);	
        model.addAttribute("lang", (Object)lang);	
        return "modules/sys/langList";	
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
	
    @RequiresPermissions(value={"sys:lang:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Lang> listData(Lang lang, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void lang2;	
        void request;	
        void response;	
        lang2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.langService.findPage((Lang)lang2);	
    }	
}	
	
