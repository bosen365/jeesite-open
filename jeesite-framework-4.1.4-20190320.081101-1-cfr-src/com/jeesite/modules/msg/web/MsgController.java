/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.D;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.utils.MsgPcPoolUtils;	
import com.jeesite.modules.msg.utils.MsgPushUtils;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
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
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"unreadMsg"})	
    public Page<MsgPush> unreadMsg(@RequestParam(defaultValue="pc") String msgType, @RequestParam(defaultValue="1") Integer pageNo, @RequestParam(defaultValue="5") Integer pageSize, Model model) {	
        void a;	
        String a2 = UserUtils.getUser().getUserCode();	
        MsgPush msgPush = new MsgPush();	
        void v0 = a;	
        a.setMsgType(msgType);	
        v0.setReceiveUserCode(a2);	
        v0.setReadStatus("2");	
        void v1 = a;	
        v0.setPage(new Page(pageNo, pageSize));	
        Page<MsgPush> a3 = this.msgPushService.findPage((MsgPush)a);	
        if ("pc".equals(msgType)) {	
            void a4;	
            MsgPush msgPush2 = new MsgPush();	
            void v2 = a4;	
            a4.setMsgType(msgType);	
            v2.setReceiveUserCode(a2);	
            v2.setReadStatus("0");	
            List<void> a5 = this.msgPushService.findList(a4);	
            MsgPcPoolUtils.putPool(a2, a5);	
        }	
        return a3;	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"pullPoolMsg"})	
    public List<MsgPush> pullPoolMsg(Model model) {	
        String a = UserUtils.getUser().getUserCode();	
        List<MsgPush> a2 = MsgPcPoolUtils.getPool(a);	
        for (MsgPush a3 : a2) {	
            MsgPush a4 = (MsgPush)this.msgPushService.get(a3.getId());	
            if (a4 == null) continue;	
            a4.setReadStatus("2");	
            this.msgPushService.updateMsgPush(a4);	
        }	
        ArrayList<MsgPush> a5 = ListUtils.newArrayList(a2);	
        MsgPcPoolUtils.removeCache(a);	
        return a5;	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"readAllMsg"})	
    public String readAllMsg(String id) {	
        String a = UserUtils.getUser().getUserCode();	
        MsgPushUtils.readMsgByBiz(null, null, a);	
        return this.renderResult("true", "全部标记已读成功！");	
    }	
	
    @RequiresPermissions(value={"user"})	
    @ResponseBody	
    @RequestMapping(value={"listData"})	
    public Page<MsgPush> listData(MsgPush msgPush, boolean pushed, HttpServletRequest request, HttpServletResponse response, Model model) {	
        if (pushed) {	
            msgPush = new MsgPushed(msgPush);	
        }	
        if (StringUtils.isBlank(msgPush.getMsgType())) {	
            msgPush.setMsgType("pc");	
        }	
        MsgPush msgPush2 = msgPush;	
        msgPush2.setReceiveUserCode(msgPush2.getCurrentUser().getUserCode());	
        MsgPush msgPush3 = msgPush;	
        msgPush2.setPage(new Page(request, response));	
        return this.msgPushService.findPage(msgPush);	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"list"})	
    public String list(MsgPush msgPush, boolean pushed, Model model) {	
        model.addAttribute("msgPush", msgPush);	
        model.addAttribute("pushed", pushed);	
        return "modules/msg/msgList";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"readMsg"})	
    public String readMsg(MsgPush msgPush, boolean pushed, Model model) {	
        if (StringUtils.isBlank(msgPush.getId())) {	
            return this.list(msgPush, pushed, model);	
        }	
        MsgPush a = this.msgPushService.get(msgPush);	
        if (a != null && !pushed) {	
            MsgPush msgPush2 = a;	
            MsgPush msgPush3 = a;	
            msgPush2.setReadDate(new Date());	
            msgPush2.setReadStatus("1");	
            this.msgPushService.updateMsgPush(a);	
        }	
        if (a == null) {	
            msgPush = new MsgPushed(msgPush);	
            a = this.msgPushService.get(msgPush);	
        }	
        model.addAttribute("msgPush", a);	
        model.addAttribute("pushed", pushed);	
        return "modules/msg/msgForm";	
    }	
	
    @ModelAttribute	
    public MsgPush get() {	
        return new MsgPush();	
    }	
}	
	
