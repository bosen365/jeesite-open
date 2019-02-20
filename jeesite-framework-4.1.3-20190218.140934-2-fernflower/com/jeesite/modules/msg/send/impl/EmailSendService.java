package com.jeesite.modules.msg.send.impl;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.EmailMsgContent;	
import com.jeesite.modules.msg.send.MsgSendService;	
import org.apache.commons.mail.HtmlEmail;	
import org.hyperic.sigar.pager.PageControl;	
import org.springframework.stereotype.Service;	
	
@Service	
public class EmailSendService extends BaseService implements MsgSendService {	
   public void sendMessage(MsgPush msgPush) {	
      try {	
         String a = Global.getProperty("msg.email.fromAddress");	
         String a = Global.getProperty("msg.email.fromPassword");	
         String a = Global.getProperty("msg.email.fromHostName");	
         Integer a = Global.getPropertyToInteger("msg.email.smtpPort", "25");	
         String a = Global.getProperty("msg.email.sslOnConnect", "false");	
         String a = Global.getProperty("msg.email.sslSmtpPort", "465");	
         HtmlEmail a = new HtmlEmail();	
         a.setCharset("utf-8");	
         a.setFrom(a);	
         a.setAuthentication(a, a);	
         a.setHostName(a);	
         a.setSmtpPort(a);	
         if ("true".equals(a)) {	
            a.setSSLOnConnect(true);	
            a.setSslSmtpPort(a);	
         }	
	
         a.addTo(msgPush.getReceiveCode(), msgPush.getReceiveUserName());	
         EmailMsgContent a = (EmailMsgContent)msgPush.parseMsgContent(EmailMsgContent.class);	
         a.setSubject(a.getTitle());	
         a.setMsg(a.getContent());	
         String[] var10;	
         int var11;	
         int var12;	
         String a;	
         int var10000;	
         if (StringUtils.isNotBlank(a.getCc())) {	
            var11 = (var10 = a.getCc().split(";")).length;	
	
            for(var10000 = var12 = 0; var10000 < var11; var10000 = var12) {	
               a = var10[var12];	
               ++var12;	
               a.addCc(a);	
            }	
         }	
	
         if (StringUtils.isNotBlank(a.getBcc())) {	
            var11 = (var10 = a.getBcc().split(";")).length;	
	
            for(var10000 = var12 = 0; var10000 < var11; var10000 = var12) {	
               a = var10[var12];	
               ++var12;	
               a.addBcc(a);	
            }	
         }	
	
         String var10001 = a.send();	
         msgPush.setPushStatus("1");	
         msgPush.addPushReturnContent(var10001);	
      } catch (Exception var14) {	
         this.logger.error("发送邮件失败！ ", var14);	
         msgPush.setPushStatus("2");	
         msgPush.addPushReturnContent(ExceptionUtils.getStackTraceAsString(var14));	
      }	
   }	
}	
