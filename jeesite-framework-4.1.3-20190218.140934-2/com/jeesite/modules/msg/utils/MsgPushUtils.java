package com.jeesite.modules.msg.utils;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.AppMsgContent;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.entity.content.EmailMsgContent;	
import com.jeesite.modules.msg.entity.content.PcMsgContent;	
import com.jeesite.modules.msg.entity.content.SmsMsgContent;	
import java.util.Date;	
import java.util.Iterator;	
import org.apache.commons.lang3.StringUtils;	
	
public class MsgPushUtils {	
   public static MsgPush push(String type, String title, String content, String bizKey, String bizType, String receiveUserCodes) {	
      BaseMsgContent a = null;	
      Object var10000;	
      if ("pc".equals(type)) {	
         var10000 = a = new PcMsgContent();	
      } else if ("app".equals(type)) {	
         var10000 = a = new AppMsgContent();	
      } else if ("sms".equals(type)) {	
         var10000 = a = new SmsMsgContent();	
      } else {	
         if ("email".equals(type)) {	
            a = new EmailMsgContent();	
         }	
	
         var10000 = a;	
      }	
	
      if (var10000 != null) {	
         ((BaseMsgContent)a).setTitle(title);	
         ((BaseMsgContent)a).setContent(content);	
         return push((BaseMsgContent)a, bizKey, bizType, receiveUserCodes, (Date)(new Date()), "0");	
      } else {	
         return null;	
      }	
   }	
	
   public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes) {	
      return push(msgContent, bizKey, bizType, receiveUserCodes, new Date(), "0");	
   }	
	
   public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate, String isMergePush) {	
      boolean a;	
      if (a = StringUtils.startsWith(receiveUserCodes, "[CODE]")) {	
         receiveUserCodes = StringUtils.substringAfter(receiveUserCodes, "[CODE]");	
      }	
	
      MsgPush a = null;	
      String[] var8;	
      int var9 = (var8 = StringUtils.split(receiveUserCodes, ",")).length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         String a = var8[var10];	
         a = new MsgPush();	
         a.setMsgContentEntity(msgContent);	
         a.setBizKey(bizKey);	
         a.setBizType(bizType);	
         MsgPush var12;	
         if (a) {	
            var12 = a;	
            a.setReceiveCode(a);	
         } else {	
            var12 = a;	
            a.setReceiveUserCode(a);	
         }	
	
         var12.setPlanPushDate(planPushDate);	
         ++var10;	
         a.setIsMergePush(isMergePush);	
         push(a);	
      }	
	
      return a;	
   }	
	
   public static void readMsgByBiz(String bizKey, String bizType, String receiveUserCode) {	
      MsgPush a = new MsgPush();	
      a.setBizKey(bizKey);	
      a.setBizType(bizType);	
      if (!StringUtils.isBlank(receiveUserCode)) {	
         a.setReceiveUserCode(receiveUserCode);	
         Iterator var5;	
         Iterator var10000 = var5 = null.ALLATORIxDEMO().findList(a).iterator();	
	
         while(var10000.hasNext()) {	
            MsgPush a = (MsgPush)var5.next();	
            var10000 = var5;	
            a.setReadDate(new Date());	
            a.setReadStatus("1");	
            null.ALLATORIxDEMO().updateMsgPush(a);	
         }	
	
      }	
   }	
	
   public static void push(MsgPush msgPush) {	
      null.ALLATORIxDEMO().save(msgPush);	
   }	
	
   public static MsgPush push(BaseMsgContent msgContent, String bizKey, String bizType, String receiveUserCodes, Date planPushDate) {	
      return push(msgContent, bizKey, bizType, receiveUserCodes, planPushDate, "0");	
   }	
}	
