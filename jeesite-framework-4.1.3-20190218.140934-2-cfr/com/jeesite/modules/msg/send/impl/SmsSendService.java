/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  com.jeesite.common.msg.SmsUtils	
 *  org.slf4j.Logger	
 *  org.springframework.stereotype.Service	
 */	
package com.jeesite.modules.msg.send.impl;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.msg.SmsUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.entity.content.SmsMsgContent;	
import com.jeesite.modules.msg.send.MsgSendService;	
import java.util.Date;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.springframework.stereotype.Service;	
	
@Service	
public class SmsSendService	
extends BaseService	
implements MsgSendService {	
    @Override	
    public void sendMessage(MsgPush msgPush) {	
        String a2;	
        block3 : {	
            try {	
                SmsMsgContent a3 = (SmsMsgContent)msgPush.parseMsgContent(SmsMsgContent.class);	
                a2 = SmsUtils.send((String)a3.getContent(), (String)msgPush.getReceiveCode());	
                if (ObjectUtils.toInteger(((Map)JsonMapper.fromJson((String)a2, Map.class)).get("result")) != 0) break block3;	
                MsgPush msgPush2 = msgPush;	
                msgPush2.setPushStatus("1");	
                msgPush2.addPushReturnContent(a2);	
                return;	
            }	
            catch (Exception a4) {	
                Exception exception = a4;	
                this.logger.error("发送短信失败！ ", (Throwable)exception);	
                MsgPush msgPush3 = msgPush;	
                msgPush.setPushDate(new Date());	
                msgPush3.setPushStatus("2");	
                msgPush3.addPushReturnContent(ExceptionUtils.getStackTraceAsString((Throwable)exception));	
                return;	
            }	
        }	
        throw new RuntimeException(a2);	
    }	
}	
	
