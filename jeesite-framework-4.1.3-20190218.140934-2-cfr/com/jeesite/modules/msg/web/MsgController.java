/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
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
 *  org.springframework.web.bind.annotation.RequestParam	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j.E;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.utils.MsgPcPoolUtils;	
import com.jeesite.modules.msg.utils.MsgPushUtils;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.ArrayList;	
import java.util.Date;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/msg"})	
@ConditionalOnProperty(name={"msg.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgController	
extends BaseController {	
    @Autowired	
    private MsgPushService msgPushService;	
	
    @ModelAttribute	
    public MsgPush get() {	
        return new MsgPush();	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"unreadMsg"})	
    public Page<MsgPush> unreadMsg(@RequestParam(defaultValue="pc") String msgType, @RequestParam(defaultValue="1") Integer pageNo, @RequestParam(defaultValue="5") Integer pageSize, Model model) {	
        void a2;	
        String a3 = UserUtils.getUser().getUserCode();	
        MsgPush msgPush = new MsgPush();	
        void v0 = a2;	
        a2.setMsgType(msgType);	
        v0.setReceiveUserCode(a3);	
        v0.setReadStatus("2");	
        void v1 = a2;	
        v0.setPage(new Page(pageNo, pageSize));	
        Page<MsgPush> a4 = this.msgPushService.findPage((MsgPush)a2);	
        if ("pc".equals(msgType)) {	
            void a5;	
            MsgPush msgPush2 = new MsgPush();	
            void v2 = a5;	
            a5.setMsgType(msgType);	
            v2.setReceiveUserCode(a3);	
            v2.setReadStatus("0");	
            List<void> a6 = this.msgPushService.findList(a5);	
            MsgPcPoolUtils.putPool(a3, a6);	
        }	
        return a4;	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"pullPoolMsg"})	
    public List<MsgPush> pullPoolMsg(Model model) {	
        String a2 = UserUtils.getUser().getUserCode();	
        List<MsgPush> a3 = MsgPcPoolUtils.getPool(a2);	
        for (MsgPush a4 : a3) {	
            MsgPush a5 = (MsgPush)this.msgPushService.get(a4.getId());	
            if (a5 == null) continue;	
            a5.setReadStatus("2");	
            this.msgPushService.updateMsgPush(a5);	
        }	
        ArrayList a6 = ListUtils.newArrayList(a3);	
        MsgPcPoolUtils.removeCache(a2);	
        return a6;	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"listData"})	
    public Page<MsgPush> listData(MsgPush msgPush, boolean pushed, HttpServletRequest request, HttpServletResponse response, Model model) {	
        if (pushed) {	
            msgPush = new MsgPushed(msgPush);	
        }	
        if (StringUtils.isBlank((CharSequence)msgPush.getMsgType())) {	
            msgPush.setMsgType("pc");	
        }	
        MsgPush msgPush2 = msgPush;	
        msgPush2.setReceiveUserCode(msgPush2.getCurrentUser().getUserCode());	
        MsgPush msgPush3 = msgPush;	
        msgPush2.setPage(new Page(request, response));	
        return this.msgPushService.findPage(msgPush);	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"readAllMsg"})	
    public String readAllMsg(String id) {	
        String a2 = UserUtils.getUser().getUserCode();	
        MsgPushUtils.readMsgByBiz(null, null, a2);	
        return this.renderResult("true", "全部标记已读成功！");	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"list"})	
    public String list(MsgPush msgPush, boolean pushed, Model model) {	
        model.addAttribute("msgPush", (Object)msgPush);	
        model.addAttribute("pushed", (Object)pushed);	
        return "modules/msg/msgList";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"readMsg"})	
    public String readMsg(MsgPush msgPush, boolean pushed, Model model) {	
        if (StringUtils.isBlank((CharSequence)msgPush.getId())) {	
            return this.list(msgPush, pushed, model);	
        }	
        MsgPush a2 = this.msgPushService.get(msgPush);	
        if (a2 != null && !pushed) {	
            MsgPush msgPush2 = a2;	
            MsgPush msgPush3 = a2;	
            msgPush2.setReadDate(new Date());	
            msgPush2.setReadStatus("1");	
            this.msgPushService.updateMsgPush(a2);	
        }	
        if (a2 == null) {	
            msgPush = new MsgPushed(msgPush);	
            a2 = this.msgPushService.get(msgPush);	
        }	
        model.addAttribute("msgPush", (Object)a2);	
        model.addAttribute("pushed", (Object)pushed);	
        return "modules/msg/msgForm";	
    }	
}	
	
