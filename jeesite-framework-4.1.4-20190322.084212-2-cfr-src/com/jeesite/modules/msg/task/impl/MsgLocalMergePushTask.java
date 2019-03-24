/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.task.impl;	
	
import com.jeesite.common.beetl.e.j;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.msg.task.impl.m;	
import com.jeesite.modules.msg.utils.MsgPushUtils;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.pager.PageFetcher;	
import org.slf4j.Logger;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"msg.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgLocalMergePushTask	
extends BaseService	
implements MsgPushTask {	
    @Autowired	
    private MsgPushService msgPushService;	
	
    public void setMsgPushService(MsgPushService msgPushService) {	
        this.msgPushService = msgPushService;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void execute() {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        MsgPush a = null;	
        Iterator<MsgPush> iterator = this.msgPushService.findListByMergePush(new MsgPush()).iterator();	
        block0 : do {	
            Iterator<MsgPush> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                void a2;	
                MsgPush a3 = iterator.next();	
                if (ObjectUtils.toLong(a3.getMergePushCount()) <= 0L) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                m m2 = new m(this, a3);	
                void v1 = a2;	
                v1.setTitle("合并消息提醒");	
                void v2 = a2;	
                v1.setContent("您有 " + a3.getMergePushCount() + " 条新消息，请注意查收。");	
                MsgPushUtils.push((BaseMsgContent)v1, null, null, a3.getReceiveUserCode());	
                do {	
                    void a4;	
                    MsgPush msgPush = new MsgPush();	
                    void v3 = a4;	
                    void v4 = a4;	
                    v4.setMsgType(a3.getMsgType());	
                    v4.setReceiveUserCode(a3.getReceiveUserCode());	
                    v3.setIsMergePush("1");	
                    v3.setPushStatus("0");	
                    v3.setPage(new Page(1, 30, -1L));	
                    List<MsgPush> a5 = this.msgPushService.findPage((MsgPush)a4).getList();	
                    if (a5.size() == 0) continue block0;	
                    int a6 = 0;	
                    int n = a5.size() - 1;	
                    while (n >= 0) {	
                        int a7;	
                        a = a5.get(a7);	
                        if (!this.executeMsgPush(a)) {	
                            ++a6;	
                        }	
                        n = --a7;	
                    }	
                    if (a5.size() == a6) {	
                        this.logger.warn("没有一条消息能够发送成功，请检查你的消息服务配置。");	
                        continue block0;	
                    }	
                    if (a6 <= 0) continue;	
                    this.logger.warn(new StringBuilder().insert(0, "你有“").append(a6).append("”条消息没有发送成功！").toString());	
                } while (true);	
            }	
            break;	
        } while (true);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public boolean executeMsgPush(MsgPush msgPush) {	
        void msgPush2;	
        void v0 = msgPush2;	
        void v1 = msgPush2;	
        void v2 = msgPush2;	
        v1.setPushDate(new Date());	
        v1.setPushStatus("1");	
        v0.addPushReturnContent("合并推送成功！");	
        v0.setReadDate(new Date());	
        v0.setReadStatus("1");	
        this.msgPushService.updateMsgPush((MsgPush)msgPush2);	
        return true;	
    }	
}	
	
