package com.jeesite.modules.msg.send.impl;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.msg.SmsUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.SmsMsgContent;	
import com.jeesite.modules.msg.send.MsgSendService;	
import java.util.Date;	
import java.util.Map;	
import org.springframework.stereotype.Service;	
	
@Service	
public class SmsSendService extends BaseService implements MsgSendService {	
   public void sendMessage(MsgPush msgPush) {	
      try {	
         String a;	
         if (ObjectUtils.toInteger(((Map)JsonMapper.fromJson(a = SmsUtils.send(((SmsMsgContent)msgPush.parseMsgContent(SmsMsgContent.class)).getContent(), msgPush.getReceiveCode()), Map.class)).get("result")) == 0) {	
            msgPush.setPushStatus("1");	
            msgPush.addPushReturnContent(a);	
         } else {	
            throw new RuntimeException(a);	
         }	
      } catch (Exception var4) {	
         this.logger.error("发送短信失败！ ", var4);	
         msgPush.setPushDate(new Date());	
         msgPush.setPushStatus("2");	
         msgPush.addPushReturnContent(ExceptionUtils.getStackTraceAsString(var4));	
      }	
   }	
}	
