package com.jeesite.modules.msg.task.impl;	
	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.msg.utils.MsgPushUtils;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.ProcTime;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(	
   name = {"msg.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgLocalMergePushTask extends BaseService implements MsgPushTask {	
   @Autowired	
   private MsgPushService msgPushService;	
	
   public void setMsgPushService(MsgPushService msgPushService) {	
      this.msgPushService = msgPushService;	
   }	
	
   public boolean executeMsgPush(MsgPush msgPush) {	
      msgPush.setPushDate(new Date());	
      msgPush.setPushStatus("1");	
      msgPush.addPushReturnContent("合并推送成功！");	
      msgPush.setReadDate(new Date());	
      msgPush.setReadStatus("1");	
      this.msgPushService.updateMsgPush(msgPush);	
      return true;	
   }	
	
   public void execute() {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         MsgPush a = null;	
         Iterator var3 = this.msgPushService.findListByMergePush(new MsgPush()).iterator();	
	
         while(true) {	
            label55:	
            while(true) {	
               for(Iterator var10000 = var3; var10000.hasNext(); var10000 = var3) {	
                  final MsgPush a;	
                  if (ObjectUtils.toLong((a = (MsgPush)var3.next()).getMergePushCount()) > 0L) {	
                     BaseMsgContent a = new BaseMsgContent() {	
                        private static final long D = 1L;	
	
                        public String getMsgType() {	
                           return a.getMsgType();	
                        }	
                     };	
                     a.setTitle("合并消息提醒");	
                     a.setContent("您有 " + a.getMergePushCount() + " 条新消息，请注意查收。");	
                     MsgPushUtils.push(a, (String)null, (String)null, a.getReceiveUserCode());	
	
                     while(true) {	
                        MsgPush a = new MsgPush();	
                        a.setMsgType(a.getMsgType());	
                        a.setReceiveUserCode(a.getReceiveUserCode());	
                        a.setIsMergePush("1");	
                        a.setPushStatus("0");	
                        a.setPage(new Page(1, 30, -1L));	
                        List a;	
                        if ((a = this.msgPushService.findPage(a).getList()).size() == 0) {	
                           continue label55;	
                        }	
	
                        int a = 0;	
	
                        int a;	
                        for(int var11 = a = a.size() - 1; var11 >= 0; var11 = a) {	
                           a = (MsgPush)a.get(a);	
                           if (!this.executeMsgPush(a)) {	
                              ++a;	
                           }	
	
                           --a;	
                        }	
	
                        if (a.size() == a) {	
                           this.logger.warn("没有一条消息能够发送成功，请检查你的消息服务配置。");	
                           continue label55;	
                        }	
	
                        if (a > 0) {	
                           this.logger.warn((new StringBuilder()).insert(0, "你有“").append(a).append("”条消息没有发送成功！").toString());	
                        }	
                     }	
                  }	
               }	
	
               return;	
            }	
         }	
      }	
   }	
}	
