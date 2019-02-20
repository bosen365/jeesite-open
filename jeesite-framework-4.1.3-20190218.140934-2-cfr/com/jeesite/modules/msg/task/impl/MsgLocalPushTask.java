/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.slf4j.Logger	
 *  org.springframework.beans.factory.NoSuchBeanDefinitionException	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Service	
 */	
package com.jeesite.modules.msg.task.impl;	
	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.send.MsgSendService;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.msg.utils.MsgPcPoolUtils;	
import java.util.Date;	
import java.util.List;	
import org.hyperic.sigar.FileSystemMap;	
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
	
    public void setMsgPushService(MsgPushService msgPushService) {	
        this.msgPushService = msgPushService;	
    }	
	
    @Override	
    public boolean executeMsgPush(MsgPush msgPush) {	
        if (StringUtils.inString((String)msgPush.getReceiveCode(), (String[])new String[]{"NONE"})) {	
            MsgPush msgPush2 = msgPush;	
            msgPush.setPushDate(new Date());	
            msgPush2.setPushStatus("2");	
            msgPush2.addPushReturnContent("未设置接受者账号（个人信息：手机、邮箱或微信等）");	
            this.msgPushService.updateMsgPush(msgPush);	
            return false;	
        }	
        if (StringUtils.inString((String)msgPush.getMsgType(), (String[])new String[]{"pc"})) {	
            MsgPush msgPush3 = msgPush;	
            MsgPush msgPush4 = msgPush;	
            MsgPcPoolUtils.putPool(msgPush.getReceiveCode(), msgPush4);	
            msgPush4.setPushDate(new Date());	
            msgPush3.setPushStatus("1");	
            msgPush3.addPushReturnContent("推送成功！");	
            this.msgPushService.updateMsgPush(msgPush);	
        } else {	
            MsgSendService msgSendService;	
            String a2 = new StringBuilder().insert(0, "msg.").append(msgPush.getMsgType()).append(".beanName").toString();	
            String a3 = Global.getProperty(a2);	
            MsgSendService a4 = null;	
            try {	
                msgSendService = a4 = (MsgSendService)SpringUtils.getBean(a3);	
            }	
            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                msgSendService = a4;	
            }	
            if (msgSendService == null) {	
                MsgPush msgPush5 = msgPush;	
                msgPush5.setPushDate(new Date());	
                msgPush5.setPushStatus("2");	
                msgPush5.addPushReturnContent(new StringBuilder().insert(0, a2).append(" 设置的 ").append(a3).append(" (ean 未实例化。").toString());	
                this.msgPushService.updateMsgPush(msgPush);	
                return false;	
            }	
            a4.sendMessage(msgPush);	
            if ("1".equals(msgPush.getPushStatus())) {	
                if (StringUtils.isBlank((CharSequence)msgPush.getReadStatus()) || "0".equals(msgPush.getReadStatus())) {	
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
	
    @Override	
    public void execute() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        MsgPush a2 = null;	
        do {	
            void a3;	
            MsgPush msgPush = new MsgPush();	
            void v0 = a3;	
            a3.setIsMergePush("0");	
            void v1 = a3;	
            v0.setPlanPushDate_lte(new Date());	
            v0.setPushStatus("0");	
            v0.setPage(new Page(1, 30, -1L));	
            List<MsgPush> a4 = this.msgPushService.findPage((MsgPush)a3).getList();	
            if (a4.size() == 0) {	
                return;	
            }	
            int a5 = 0;	
            int n = a4.size() - 1;	
            while (n >= 0) {	
                int a6;	
                a2 = a4.get(a6);	
                if (!this.executeMsgPush(a2)) {	
                    ++a5;	
                }	
                n = --a6;	
            }	
            if (a4.size() == a5) {	
                this.logger.warn("没有一条消息能够发送成功，请检查你的消息服务配置。");	
                return;	
            }	
            if (a5 <= 0) continue;	
            this.logger.warn(new StringBuilder().insert(0, "你有“").append(a5).append("”条消息没有发送成功！").toString());	
        } while (true);	
    }	
}	
	
