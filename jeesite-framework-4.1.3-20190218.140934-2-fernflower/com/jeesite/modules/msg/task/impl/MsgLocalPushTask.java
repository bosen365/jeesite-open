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
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(	
   name = {"msg.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgLocalPushTask extends BaseService implements MsgPushTask {	
   @Autowired	
   private MsgPushService msgPushService;	
	
   public void setMsgPushService(MsgPushService msgPushService) {	
      this.msgPushService = msgPushService;	
   }	
	
   public boolean executeMsgPush(MsgPush msgPush) {	
      String var10000 = msgPush.getReceiveCode();	
      String[] var10001 = new String[1];	
      boolean var10003 = true;	
      var10001[0] = "NONE";	
      if (StringUtils.inString(var10000, var10001)) {	
         msgPush.setPushDate(new Date());	
         msgPush.setPushStatus("2");	
         msgPush.addPushReturnContent("未设置接受者账号（个人信息：手机、邮箱或微信等）");	
         this.msgPushService.updateMsgPush(msgPush);	
         return false;	
      } else {	
         var10000 = msgPush.getMsgType();	
         var10001 = new String[1];	
         var10003 = true;	
         var10001[0] = "pc";	
         if (StringUtils.inString(var10000, var10001)) {	
            MsgPcPoolUtils.putPool(msgPush.getReceiveCode(), msgPush);	
            msgPush.setPushDate(new Date());	
            msgPush.setPushStatus("1");	
            msgPush.addPushReturnContent("推送成功！");	
            this.msgPushService.updateMsgPush(msgPush);	
         } else {	
            String a;	
            String a = Global.getProperty(a = (new StringBuilder()).insert(0, "msg.").append(msgPush.getMsgType()).append(".beanName").toString());	
            MsgSendService a = null;	
	
            MsgSendService var7;	
            label37: {	
               try {	
                  a = (MsgSendService)SpringUtils.getBean(a);	
               } catch (NoSuchBeanDefinitionException var6) {	
                  var7 = a;	
                  break label37;	
               }	
	
               var7 = a;	
            }	
	
            if (var7 == null) {	
               msgPush.setPushDate(new Date());	
               msgPush.setPushStatus("2");	
               msgPush.addPushReturnContent((new StringBuilder()).insert(0, a).append(" 设置的 ").append(a).append(" (ean 未实例化。").toString());	
               this.msgPushService.updateMsgPush(msgPush);	
               return false;	
            }	
	
            a.sendMessage(msgPush);	
            if (!"1".equals(msgPush.getPushStatus())) {	
               msgPush.setPushDate(new Date());	
               msgPush.setPushStatus("0");	
               this.msgPushService.updateMsgPush(msgPush);	
               return false;	
            }	
	
            if (StringUtils.isBlank(msgPush.getReadStatus()) || "0".equals(msgPush.getReadStatus())) {	
               msgPush.setReadDate(new Date());	
               msgPush.setReadStatus("1");	
            }	
	
            msgPush.setPushDate(new Date());	
            msgPush.setPushStatus("1");	
            this.msgPushService.updateMsgPush(msgPush);	
         }	
	
         return true;	
      }	
   }	
	
   public void execute() {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         MsgPush a = null;	
	
         while(true) {	
            MsgPush a = new MsgPush();	
            a.setIsMergePush("0");	
            a.setPlanPushDate_lte(new Date());	
            a.setPushStatus("0");	
            a.setPage(new Page(1, 30, -1L));	
            List a;	
            if ((a = this.msgPushService.findPage(a).getList()).size() == 0) {	
               return;	
            }	
	
            int a = 0;	
	
            int a;	
            for(int var10000 = a = a.size() - 1; var10000 >= 0; var10000 = a) {	
               a = (MsgPush)a.get(a);	
               if (!this.executeMsgPush(a)) {	
                  ++a;	
               }	
	
               --a;	
            }	
	
            if (a.size() == a) {	
               this.logger.warn("没有一条消息能够发送成功，请检查你的消息服务配置。");	
               return;	
            }	
	
            if (a > 0) {	
               this.logger.warn((new StringBuilder()).insert(0, "你有“").append(a).append("”条消息没有发送成功！").toString());	
            }	
         }	
      }	
   }	
}	
