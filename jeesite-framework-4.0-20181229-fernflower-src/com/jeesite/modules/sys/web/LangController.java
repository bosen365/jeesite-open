package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.l.d.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.List;	
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
@RequestMapping({"${adminPath}/sys/lang"})	
public class LangController extends BaseController {	
   @Autowired	
   private ModuleService moduleService;	
   @Autowired	
   private LangService langService;	
	
   @RequiresPermissions({"sys:lang:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated Lang lang, String oldLangCode, String oldLangType) {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         if (oldLangCode == null || !oldLangCode.equals(lang.getLangCode()) || oldLangType == null || !oldLangType.equals(lang.getLangType())) {	
            Lang a = new Lang();	
            a.setLangCode(lang.getLangCode());	
            a.setLangType(lang.getLangType());	
            if (this.langService.findList(a).size() > 0) {	
               return this.renderResult("false", "编码和类型已经存在！");	
            }	
         }	
	
         this.langService.save(lang);	
         return this.renderResult("true", "保存语言成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:lang:view"})	
   @RequestMapping({"list", ""})	
   public String list(Lang lang, Model model) {	
      model.addAttribute("lang", lang);	
      Module a = new Module();	
      List a = this.moduleService.findList(a);	
      model.addAttribute("mduleList", a);	
      return "mdules/sys/langList";	
   }	
	
   @RequiresPermissions({"sys:lang:edit"})	
   @RequestMapping({"clearCache"})	
   @ResponseBody	
   public String clearCache() {	
      this.langService.clearCache();	
      return this.renderResult("true", "清理缓存成功！");	
   }	
	
   @RequiresPermissions({"sys:lang:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(Lang lang, HttpServletRequest request, HttpServletResponse response) {	
      return this.langService.findPage(new Page(request, response), lang);	
   }	
	
   @RequiresPermissions({"sys:lang:view"})	
   @RequestMapping({"form"})	
   public String form(Lang lang, Model model) {	
      model.addAttribute("lang", lang);	
      Module a = new Module();	
      List a = this.moduleService.findList(a);	
      model.addAttribute("mduleList", a);	
      return "mdules/sys/langFrm";	
   }	
	
   @RequiresPermissions({"sys:lang:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(Lang lang) {	
      this.langService.delete(lang);	
      return this.renderResult("true", "删除语言成功！");	
   }	
	
   @ModelAttribute	
   public Lang get(String id, boolean isNewRecord) {	
      return (Lang)this.langService.get(id, isNewRecord);	
   }	
}	
