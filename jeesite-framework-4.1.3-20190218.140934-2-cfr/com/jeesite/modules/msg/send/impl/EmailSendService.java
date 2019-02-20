/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.commons.mail.Email	
 *  org.apache.commons.mail.HtmlEmail	
 *  org.slf4j.Logger	
 *  org.springframework.stereotype.Service	
 */	
package com.jeesite.modules.msg.send.impl;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.entity.content.EmailMsgContent;	
import com.jeesite.modules.msg.send.MsgSendService;	
import org.apache.commons.mail.Email;	
import org.apache.commons.mail.HtmlEmail;	
import org.hyperic.sigar.pager.PageControl;	
import org.slf4j.Logger;	
import org.springframework.stereotype.Service;	
	
@Service	
public class EmailSendService	
extends BaseService	
implements MsgSendService {	
    @Override	
    public void sendMessage(MsgPush msgPush) {	
        void a2;	
        block8 : {	
            int n;	
            EmailMsgContent a3;	
            String[] arrstring;	
            int n2;	
            String a4;	
            block7 : {	
                String a5 = Global.getProperty("msg.email.fromAddress");	
                String a6 = Global.getProperty("msg.email.fromPassword");	
                String a7 = Global.getProperty("msg.email.fromHostName");	
                Integer a8 = Global.getPropertyToInteger("msg.email.smtpPort", "25");	
                String a9 = Global.getProperty("msg.email.sslOnConnect", "false");	
                String a10 = Global.getProperty("msg.email.sslSmtpPort", "465");	
                HtmlEmail htmlEmail = new HtmlEmail();	
                void v0 = a2;	
                v0.setCharset("utf-8");	
                void v1 = a2;	
                a2.setAuthentication(a5, a6);	
                v1.setHostName(a7);	
                v1.setSmtpPort(a8.intValue());	
                v0.setFrom(a5);	
                if ("true".equals(a9)) {	
                    a2.setSslSmtpPort(a10);	
                    a2.setSSLOnConnect(true);	
                }	
                a2.addTo(msgPush.getReceiveCode(), msgPush.getReceiveUserName());	
                EmailMsgContent emailMsgContent = a3 = (EmailMsgContent)msgPush.parseMsgContent(EmailMsgContent.class);	
                a2.setMsg(emailMsgContent.getContent());	
                a2.setSubject(a3.getTitle());	
                if (!StringUtils.isNotBlank((CharSequence)emailMsgContent.getCc())) break block7;	
                arrstring = a3.getCc().split(";");	
                n2 = arrstring.length;	
                int n3 = n = 0;	
                while (n3 < n2) {	
                    String string = arrstring[n];	
                    a2.addCc(a4);	
                    n3 = ++n;	
                }	
            }	
            try {	
                if (!StringUtils.isNotBlank((CharSequence)a3.getBcc())) break block8;	
                arrstring = a3.getBcc().split(";");	
                n2 = arrstring.length;	
                int n4 = n = 0;	
                while (n4 < n2) {	
                    a4 = arrstring[n];	
                    a2.addBcc(a4);	
                    n4 = ++n;	
                }	
            }	
            catch (Exception a11) {	
                Exception exception = a11;	
                this.logger.error("发送邮件失败！ ", (Throwable)exception);	
                MsgPush msgPush2 = msgPush;	
                msgPush2.setPushStatus("2");	
                msgPush2.addPushReturnContent(ExceptionUtils.getStackTraceAsString((Throwable)exception));	
                return;	
            }	
        }	
        String a12 = a2.send();	
        MsgPush msgPush3 = msgPush;	
        msgPush3.setPushStatus("1");	
        msgPush3.addPushReturnContent(a12);	
    }	
}	
	
