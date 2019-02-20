/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 */	
package com.jeesite.modules.msg.utils;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.AppMsgContent;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.entity.content.EmailMsgContent;	
import com.jeesite.modules.msg.entity.content.PcMsgContent;	
import com.jeesite.modules.msg.entity.content.SmsMsgContent;	
import com.jeesite.modules.msg.utils.E;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
	
public class MsgPushUtils {	
    public static MsgPush push(String type, String title, String content, String bizKey, String bizType, String receiveUserCodes) {	
        BaseMsgContent baseMsgContent;	
        BaseMsgContent a2 = null;	
        if ("pc".equals(type)) {	
            baseMsgContent = a2 = new PcMsgContent();	
        } else if ("app".equals(type)) {	
            baseMsgContent = a2 = new AppMsgContent();	
        } else if ("sms".equals(type)) {	
            baseMsgContent = a2 = new SmsMsgContent();	
        } else {	
            if ("email".equals(type)) {	
                a2 = new EmailMsgContent();	
            }	
            baseMsgContent = a2;	
        }	
        if (baseMsgContent != null) {	
            BaseMsgContent baseMsgContent2 = a2;	
            a2.setTitle(title);	
            baseMsgContent2.setContent(content);	
            return MsgPushUtils.push(baseMsgContent2, bizKey, bizType, receiveUserCodes, new Date(), "0");	
        }	
        return null;	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes) {	
        return MsgPushUtils.push(msgContent, bizKey, bizType, receiveUserCodes, new Date(), "0");	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate, String isMergePush) {	
        int n;	
        boolean a2 = StringUtils.startsWith((CharSequence)receiveUserCodes, (CharSequence)"[CODE]");	
        if (a2) {	
            receiveUserCodes = StringUtils.substringAfter((String)receiveUserCodes, (String)"[CODE]");	
        }	
        MsgPush a3 = null;	
        String[] arrstring = StringUtils.split((String)receiveUserCodes, (String)",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            MsgPush msgPush;	
            String a4 = arrstring[n];	
            MsgPush msgPush2 = a3 = new MsgPush();	
            a3.setMsgContentEntity(msgContent);	
            msgPush2.setBizKey(bizKey);	
            msgPush2.setBizType(bizType);	
            if (a2) {	
                MsgPush msgPush3 = a3;	
                msgPush = msgPush3;	
                msgPush3.setReceiveCode(a4);	
            } else {	
                MsgPush msgPush4 = a3;	
                msgPush = msgPush4;	
                msgPush4.setReceiveUserCode(a4);	
            }	
            msgPush.setPlanPushDate(planPushDate);	
            MsgPush msgPush5 = a3;	
            msgPush5.setIsMergePush(isMergePush);	
            MsgPushUtils.push(msgPush5);	
            n3 = ++n;	
        }	
        return a3;	
    }	
	
    public static void readMsgByBiz(String bizKey, String bizType, String receiveUserCode) {	
        Iterator<void> iterator;	
        void a2;	
        MsgPush msgPush = new MsgPush();	
        void v0 = a2;	
        v0.setBizKey(bizKey);	
        v0.setBizType(bizType);	
        if (StringUtils.isBlank((CharSequence)receiveUserCode)) {	
            return;	
        }	
        a2.setReceiveUserCode(receiveUserCode);	
        Iterator<void> iterator2 = iterator = E.ALLATORIxDEMO().findList(a2).iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            MsgPush msgPush2 = (MsgPush)iterator.next();	
            iterator2 = iterator;	
            void v2 = a3;	
            void v3 = a3;	
            v2.setReadDate(new Date());	
            v2.setReadStatus("1");	
            E.ALLATORIxDEMO().updateMsgPush((MsgPush)a3);	
        }	
    }	
	
    public static void push(MsgPush msgPush) {	
        E.ALLATORIxDEMO().save(msgPush);	
    }	
	
    public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate) {	
        return MsgPushUtils.push(msgContent, bizKey, bizType, receiveUserCodes, planPushDate, "0");	
    }	
}	
	
