/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.modules.msg.utils;	
	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.AppMsgContent;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.entity.content.EmailMsgContent;	
import com.jeesite.modules.msg.entity.content.PcMsgContent;	
import com.jeesite.modules.msg.entity.content.SmsMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.msg.utils.c;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class MsgPushUtils {	
    private static Logger logger = LoggerFactory.getLogger(MsgPushTask.class);	
	
    public static MsgPushService getMsgPushService() {	
        return c.ALLATORIxDEMO();	
    }	
	
    public static void readMsgByBiz(String bizKey, String bizType, String receiveUserCode) {	
        Iterator<void> iterator;	
        void a;	
        MsgPush msgPush = new MsgPush();	
        void v0 = a;	
        v0.setBizKey(bizKey);	
        v0.setBizType(bizType);	
        if (StringUtils.isBlank((CharSequence)receiveUserCode)) {	
            return;	
        }	
        a.setReceiveUserCode(receiveUserCode);	
        Iterator<void> iterator2 = iterator = c.ALLATORIxDEMO().findList(a).iterator();	
        while (iterator2.hasNext()) {	
            void a2;	
            MsgPush msgPush2 = (MsgPush)iterator.next();	
            iterator2 = iterator;	
            void v2 = a2;	
            void v3 = a2;	
            v2.setReadDate(new Date());	
            v2.setReadStatus("1");	
            c.ALLATORIxDEMO().updateMsgPush((MsgPush)a2);	
        }	
    }	
	
    public static void push(MsgPush msgPush) {	
        c.ALLATORIxDEMO().save(msgPush);	
    }	
	
    public static MsgPushTask getMsgPushTask() {	
        return c.ALLATORIxDEMO();	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate, String isMergePush) {	
        int n;	
        boolean a = StringUtils.startsWith((CharSequence)receiveUserCodes, (CharSequence)"[CODE]");	
        if (a) {	
            receiveUserCodes = StringUtils.substringAfter((String)receiveUserCodes, (String)"[CODE]");	
        }	
        MsgPush a2 = null;	
        String[] arrstring = StringUtils.split((String)receiveUserCodes, (String)",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            MsgPush msgPush;	
            String a3 = arrstring[n];	
            (msgContent.getMsgPush() != null ? (MsgPush)msgContent.getMsgPush().clone() : new MsgPush()).setMsgContentEntity(msgContent);	
            MsgPush msgPush2 = a2;	
            msgPush2.setBizKey(bizKey);	
            msgPush2.setBizType(bizType);	
            if (a) {	
                MsgPush msgPush3 = a2;	
                msgPush = msgPush3;	
                msgPush3.setReceiveCode(a3);	
            } else {	
                MsgPush msgPush4 = a2;	
                msgPush = msgPush4;	
                msgPush4.setReceiveUserCode(a3);	
            }	
            msgPush.setPlanPushDate(planPushDate);	
            MsgPush msgPush5 = a2;	
            msgPush5.setIsMergePush(isMergePush);	
            MsgPushUtils.push(msgPush5);	
            n3 = ++n;	
        }	
        return a2;	
    }	
	
    public static MsgPush push(String type, String title, String content, String bizKey, String bizType, String receiveUserCodes) {	
        BaseMsgContent baseMsgContent;	
        BaseMsgContent a = null;	
        if ("pc".equals(type)) {	
            baseMsgContent = a = new PcMsgContent();	
        } else if ("app".equals(type)) {	
            baseMsgContent = a = new AppMsgContent();	
        } else if ("sms".equals(type)) {	
            baseMsgContent = a = new SmsMsgContent();	
        } else {	
            if ("email".equals(type)) {	
                a = new EmailMsgContent();	
            }	
            baseMsgContent = a;	
        }	
        if (baseMsgContent != null) {	
            BaseMsgContent baseMsgContent2 = a;	
            a.setTitle(title);	
            baseMsgContent2.setContent(content);	
            return MsgPushUtils.push(baseMsgContent2, bizKey, bizType, receiveUserCodes, new Date(), "0");	
        }	
        return null;	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes) {	
        return MsgPushUtils.push(msgContent, bizKey, bizType, receiveUserCodes, new Date(), "0");	
    }	
	
    static /* synthetic */ Logger access$000() {	
        return logger;	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate) {	
        return MsgPushUtils.push(msgContent, bizKey, bizType, receiveUserCodes, planPushDate, "0");	
    }	
}	
	
