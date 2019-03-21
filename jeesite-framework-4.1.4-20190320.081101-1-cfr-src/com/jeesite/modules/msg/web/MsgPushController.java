/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.web.bind.annotation.ModelAttribute	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.service.MsgPushService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.DirStat;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/msg/msgPush"})	
@ConditionalOnProperty(name={"msg.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgPushController	
extends BaseController {	
    @Autowired	
    private MsgPushService msgPushService;	
	
    @RequiresPermissions(value={"msg:msgPush:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(MsgPush msgPush, boolean pushed) {	
        if (pushed) {	
            msgPush = new MsgPushed(msgPush);	
        }	
        this.msgPushService.delete(msgPush);	
        return this.renderResult("true", MsgPushController.text("删除消息推送成功！", new String[0]));	
    }	
	
    @RequiresPermissions(value={"msg:msgPush:view"})	
    @RequestMapping(value={"form"})	
    public String form(MsgPush msgPush, boolean pushed, Model model) {	
        model.addAttribute("msgPush", (Object)msgPush);	
        model.addAttribute("pushed", (Object)pushed);	
        return "modules/msg/msgPushForm";	
    }	
	
    @RequiresPermissions(value={"msg:msgPush:view"})	
    @RequestMapping(value={"list", ""})	
    public String list(MsgPush msgPush, boolean pushed, Model model) {	
        model.addAttribute("msgPush", (Object)msgPush);	
        model.addAttribute("pushed", (Object)pushed);	
        return "modules/msg/msgPushList";	
    }	
	
    @RequiresPermissions(value={"msg:msgPush:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<MsgPush> listData(MsgPush msgPush, boolean pushed, HttpServletRequest request, HttpServletResponse response) {	
        if (pushed) {	
            msgPush = new MsgPushed(msgPush);	
        }	
        msgPush.setPage(new Page(request, response));	
        return this.msgPushService.findPage(msgPush);	
    }	
	
    @ModelAttribute	
    public MsgPush get(String id, boolean isNewRecord, boolean pushed) {	
        MsgPush a = null;	
        if (StringUtils.isNotBlank((CharSequence)id)) {	
            a = new MsgPush(id);	
            if (pushed) {	
                a = new MsgPushed(a);	
            }	
            a = this.msgPushService.get(a);	
        }	
        if (a == null) {	
            a = new MsgPush();	
        }	
        return a;	
    }	
}	
	
