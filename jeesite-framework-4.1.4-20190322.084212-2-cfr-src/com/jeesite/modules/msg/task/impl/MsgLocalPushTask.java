/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.task.impl;	
	
import com.jeesite.common.beetl.e.j;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.shiro.l.I;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.send.MsgSendService;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.msg.utils.MsgPcPoolUtils;	
import java.util.Date;	
import java.util.List;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"msg.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgLocalPushTask	
extends BaseService	
implements MsgPushTask {	
    @Autowired	
    private MsgPushService msgPushService;	
	
    @Override	
    public boolean executeMsgPush(MsgPush msgPush) {	
        String[] arrstring = new String[1];	
        arrstring[0] = "NONE";	
        if (StringUtils.inString(msgPush.getReceiveCode(), arrstring)) {	
            MsgPush msgPush2 = msgPush;	
            msgPush.setPushDate(new Date());	
            msgPush2.setPushStatus("2");	
            msgPush2.addPushReturnContent("未设置接受者账号（个人信息：手机、邮箱或微信等）");	
            this.msgPushService.updateMsgPush(msgPush);	
            return false;	
        }	
        String[] arrstring2 = new String[1];	
        arrstring2[0] = "pc";	
        if (StringUtils.inString(msgPush.getMsgType(), arrstring2)) {	
            MsgPush msgPush3 = msgPush;	
            MsgPush msgPush4 = msgPush;	
            MsgPcPoolUtils.putPool(msgPush.getReceiveCode(), msgPush4);	
            msgPush4.setPushDate(new Date());	
            msgPush3.setPushStatus("1");	
            msgPush3.addPushReturnContent("推送成功！");	
            this.msgPushService.updateMsgPush(msgPush);	
        } else {	
            MsgSendService msgSendService;	
            String a = new StringBuilder().insert(0, "msg.").append(msgPush.getMsgType()).append(".beanName").toString();	
            String a2 = Global.getProperty(a);	
            MsgSendService a3 = null;	
            try {	
                msgSendService = a3 = (MsgSendService)SpringUtils.getBean(a2);	
            }	
            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                msgSendService = a3;	
            }	
            if (msgSendService == null) {	
                MsgPush msgPush5 = msgPush;	
                msgPush5.setPushDate(new Date());	
                msgPush5.setPushStatus("2");	
                msgPush5.addPushReturnContent(new StringBuilder().insert(0, a).append(" 设置的 ").append(a2).append(" 名称未实例化。").toString());	
                this.msgPushService.updateMsgPush(msgPush);	
                return false;	
            }	
            a3.sendMessage(msgPush);	
            if ("1".equals(msgPush.getPushStatus())) {	
                if (StringUtils.isBlank(msgPush.getReadStatus()) || "0".equals(msgPush.getReadStatus())) {	
                    msgPush.setReadDate(new Date());	
                    msgPush.setReadStatus("1");	
                }	
                msgPush.setPushDate(new Date());	
                msgPush.setPushStatus("1");	
                this.msgPushService.updateMsgPush(msgPush);	
            } else {	
                msgPush.setPushDate(new Date());	
                msgPush.setPushStatus("0");	
                this.msgPushService.updateMsgPush(msgPush);	
                return false;	
            }	
        }	
        return true;	
    }	
	
    public void setMsgPushService(MsgPushService msgPushService) {	
        this.msgPushService = msgPushService;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void execute() {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        MsgPush a = null;	
        do {	
            void a2;	
            MsgPush msgPush = new MsgPush();	
            void v0 = a2;	
            a2.setIsMergePush("0");	
            void v1 = a2;	
            v0.setPlanPushDate_lte(new Date());	
            v0.setPushStatus("0");	
            v0.setPage(new Page(1, 30, -1L));	
            List<MsgPush> a3 = this.msgPushService.findPage((MsgPush)a2).getList();	
            if (a3.size() == 0) {	
                return;	
            }	
            int a4 = 0;	
            int n = a3.size() - 1;	
            while (n >= 0) {	
                int a5;	
                a = a3.get(a5);	
                if (!this.executeMsgPush(a)) {	
                    ++a4;	
                }	
                n = --a5;	
            }	
            if (a3.size() == a4) {	
                this.logger.warn("没有一条消息能够发送成功，请检查你的消息服务配置。");	
                return;	
            }	
            if (a4 <= 0) continue;	
            this.logger.warn(new StringBuilder().insert(0, "你有“").append(a4).append("”条消息没有发送成功！").toString());	
        } while (true);	
    }	
}	
	
