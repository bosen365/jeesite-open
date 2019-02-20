package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.j.F;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.jni.ArchLoaderException;	
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
@RequestMapping({"${adminPath}/msg/msgTemplate"})	
@ConditionalOnProperty(	
   name = {"msg.enabled", "web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgTemplateController extends BaseController {	
   @Autowired	
   private ModuleService moduleService;	
   @Autowired	
   private MsgTemplateService msgTemplateService;	
	
   @RequiresPermissions({"msg:msgTemplate:view"})	
   @RequestMapping({"list", ""})	
   public String list(MsgTemplate msgTemplate, Model model) {	
      Module a = new Module();	
      a.setStatus("0");	
      model.addAttribute("moduleList", this.moduleService.findList(a));	
      model.addAttribute("msgTemplate", msgTemplate);	
      return "modules/msg/msgTemplateList";	
   }	
	
   @RequiresPermissions({"msg:msgTemplate:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated MsgTemplate msgTemplate) {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         this.msgTemplateService.save(msgTemplate);	
         String var10002 = "保存消息模板成功！";	
         String[] var10003 = new String[0];	
         boolean var10005 = true;	
         return this.renderResult("true", text(var10002, var10003));	
      }	
   }	
	
   @RequiresPermissions({"msg:msgTemplate:view"})	
   @RequestMapping({"form"})	
   public String form(MsgTemplate msgTemplate, Model model) {	
      Module a = new Module();	
      a.setStatus("0");	
      model.addAttribute("moduleList", this.moduleService.findList(a));	
      model.addAttribute("msgTemplate", msgTemplate);	
      return "modules/msg/msgTemplateForm";	
   }	
	
   @RequiresPermissions({"msg:msgTemplate:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(MsgTemplate msgTemplate, HttpServletRequest request, HttpServletResponse response) {	
      msgTemplate.setPage(new Page(request, response));	
      return this.msgTemplateService.findPage(msgTemplate);	
   }	
	
   @ModelAttribute	
   public MsgTemplate get(String id, boolean isNewRecord) {	
      return (MsgTemplate)this.msgTemplateService.get(id, isNewRecord);	
   }	
	
   @RequiresPermissions({"msg:msgTemplate:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(MsgTemplate msgTemplate) {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         this.msgTemplateService.delete(msgTemplate);	
         String var10002 = "删除消息模板成功！";	
         String[] var10003 = new String[0];	
         boolean var10005 = true;	
         return this.renderResult("true", text(var10002, var10003));	
      }	
   }	
	
   @RequiresPermissions({"msg:msgTemplate:edit"})	
   @RequestMapping({"checkTplKey"})	
   @ResponseBody	
   public String checkTplKey(String oldTplKey, String tplKey) {	
      MsgTemplate a;	
      (a = new MsgTemplate()).setTplKey(tplKey);	
      if (tplKey != null && tplKey.equals(oldTplKey)) {	
         return "true";	
      } else {	
         return tplKey != null && this.msgTemplateService.findCount(a) == 0L ? "true" : "false";	
      }	
   }	
}	
