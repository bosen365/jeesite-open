/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.d.m;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.hyperic.sigar.ProcCred;	
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
@RequestMapping(value={"${adminPath}/msg/msgTemplate"})	
@ConditionalOnProperty(name={"msg.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgTemplateController	
extends BaseController {	
    @Autowired	
    private MsgTemplateService msgTemplateService;	
    @Autowired	
    private ModuleService moduleService;	
	
    @RequiresPermissions(value={"msg:msgTemplate:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated MsgTemplate msgTemplate) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnMsg")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.msgTemplateService.save(msgTemplate);	
        return this.renderResult("true", MsgTemplateController.text("保存消息模板成功！", new String[0]));	
    }	
	
    @RequiresPermissions(value={"msg:msgTemplate:view"})	
    @RequestMapping(value={"list", ""})	
    public String list(MsgTemplate msgTemplate, Model model) {	
        void a;	
        Module module = new Module();	
        a.setStatus("0");	
        model.addAttribute("msgTemplate", msgTemplate);	
        model.addAttribute("moduleList", this.moduleService.findList(a));	
        return "modules/msg/msgTemplateList";	
    }	
	
    @ModelAttribute	
    public MsgTemplate get(String id, boolean isNewRecord) {	
        return (MsgTemplate)this.msgTemplateService.get(id, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"msg:msgTemplate:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<MsgTemplate> listData(MsgTemplate msgTemplate, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void request;	
        void response;	
        void msgTemplate2;	
        msgTemplate2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.msgTemplateService.findPage((MsgTemplate)msgTemplate2);	
    }	
	
    @RequiresPermissions(value={"msg:msgTemplate:edit"})	
    @RequestMapping(value={"checkTplKey"})	
    @ResponseBody	
    public String checkTplKey(String oldTplKey, String tplKey) {	
        MsgTemplate a = new MsgTemplate();	
        String string = tplKey;	
        a.setTplKey(string);	
        if (string != null && tplKey.equals(oldTplKey)) {	
            return "true";	
        }	
        if (tplKey != null && this.msgTemplateService.findCount(a) == 0L) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @RequiresPermissions(value={"msg:msgTemplate:view"})	
    @RequestMapping(value={"form"})	
    public String form(MsgTemplate msgTemplate, Model model) {	
        void a;	
        Module module = new Module();	
        a.setStatus("0");	
        model.addAttribute("msgTemplate", msgTemplate);	
        model.addAttribute("moduleList", this.moduleService.findList(a));	
        return "modules/msg/msgTemplateForm";	
    }	
	
    @RequiresPermissions(value={"msg:msgTemplate:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(MsgTemplate msgTemplate) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnMsg")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.msgTemplateService.delete(msgTemplate);	
        return this.renderResult("true", MsgTemplateController.text("删除消息模板成功！", new String[0]));	
    }	
}	
	
