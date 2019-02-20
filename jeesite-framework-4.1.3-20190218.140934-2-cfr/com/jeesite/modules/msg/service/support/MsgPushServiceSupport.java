/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.validation.ConstraintViolationException	
 *  javax.validation.Validator	
 *  org.slf4j.Logger	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.msg.service.support;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.msg.dao.MsgPushDao;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.msg.service.support.E;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Date;	
import java.util.List;	
import java.util.Map;	
import javax.validation.ConstraintViolationException;	
import javax.validation.Validator;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.NfsServerV3;	
import org.slf4j.Logger;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MsgPushServiceSupport	
extends CrudService<MsgPushDao, MsgPush>	
implements MsgPushService {	
    private MsgPushTask msgPushTask;	
    @Autowired	
    private Validator validator;	
    @Autowired	
    private MsgTemplateService msgTemplateService;	
	
    static /* synthetic */ Logger access$200(MsgPushServiceSupport x0) {	
        return x0.logger;	
    }	
	
    static /* synthetic */ MsgPushTask access$000(MsgPushServiceSupport x0) {	
        return x0.msgPushTask;	
    }	
	
    static /* synthetic */ MsgPushTask access$002(MsgPushServiceSupport x0, MsgPushTask x1) {	
        x0.msgPushTask = x1;	
        return x0.msgPushTask;	
    }	
	
    static /* synthetic */ Logger access$100(MsgPushServiceSupport x0) {	
        return x0.logger;	
    }	
	
    @Override	
    public Page<MsgPush> findPage(MsgPush msgPush) {	
        return super.findPage(msgPush);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(MsgPush msgPush) {	
        super.delete(msgPush);	
    }	
	
    @Override	
    public List<MsgPush> findListByMergePush(MsgPush msgPush) {	
        return ((MsgPushDao)this.dao).findListByMergePush(msgPush);	
    }	
	
    /*	
     * Enabled force condition propagation	
     * Lifted jumps to return sites	
     */	
    @Transactional(readOnly=false)	
    @Override	
    public void save(MsgPush msgPush) {	
        Object a2;	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        if (StringUtils.isNotBlank((CharSequence)msgPush.getMsgContent())) {	
            MsgPush msgPush2;	
            BaseMsgContent a3 = (BaseMsgContent)msgPush.parseMsgContent(BaseMsgContent.class);	
            if (StringUtils.isNotBlank((CharSequence)a3.getTplKey())) {	
                void a4;	
                a2 = new MsgTemplate();	
                ((MsgTemplate)a2).setTplKey(a3.getTplKey());	
                List<Object> a5 = this.msgTemplateService.findList(a2);	
                if (a5.size() <= 0) throw new ServiceException(new StringBuilder().insert(0, "模板!ey不存在：").append(a3.getTplKey()).toString());	
                String string = BeetlUtils.renderFromString(((MsgTemplate)a5.get(0)).getTplContent(), a3.getTplData());	
                MsgPush msgPush3 = msgPush;	
                msgPush2 = msgPush3;	
                a3.setContent((String)a4);	
                msgPush3.setMsgContent(JsonMapper.toJson((Object)a3));	
            } else {	
                msgPush2 = msgPush;	
            }	
            if (StringUtils.isNotBlank((CharSequence)msgPush2.getReceiveUserCode())) {	
                a2 = UserUtils.get(msgPush.getReceiveUserCode());	
                if (a2 == null) {	
                    throw new ServiceException(new StringBuilder().insert(0, "接受者用户编码不存在：").append(msgPush.getReceiveUserCode()).toString());	
                }	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveCode())) {	
                    MsgPush msgPush4;	
                    if ("pc".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush5 = msgPush;	
                        msgPush4 = msgPush5;	
                        msgPush5.setReceiveCode(((User)a2).getUserCode());	
                    } else if ("app".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush6 = msgPush;	
                        msgPush4 = msgPush6;	
                        msgPush6.setReceiveCode(((User)a2).getUserCode());	
                    } else if ("sms".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush7 = msgPush;	
                        msgPush4 = msgPush7;	
                        msgPush7.setReceiveCode(((User)a2).getMobile());	
                    } else if ("email".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush8 = msgPush;	
                        msgPush4 = msgPush8;	
                        msgPush8.setReceiveCode(((User)a2).getEmail());	
                    } else {	
                        if ("weixin".equals(msgPush.getMsgType())) {	
                            msgPush.setReceiveCode(((User)a2).getWxOpenid());	
                        }	
                        msgPush4 = msgPush;	
                    }	
                    if (StringUtils.isBlank((CharSequence)msgPush4.getReceiveCode())) {	
                        msgPush.setReceiveCode("NONE");	
                    }	
                }	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveUserName())) {	
                    msgPush.setReceiveUserName(((User)a2).getUserName());	
                }	
            }	
            if (StringUtils.isNotBlank((CharSequence)msgPush.getReceiveCode())) {	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveUserCode())) {	
                    msgPush.setReceiveUserCode("NONE");	
                }	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveUserName())) {	
                    msgPush.setReceiveUserName("NONE");	
                }	
            }	
            if (StringUtils.isBlank((CharSequence)((User)(a2 = msgPush.getCurrentUser())).getUserCode())) {	
                a2 = UserUtils.get(StringUtils.split((String)User.SUPER_ADMIN_CODE, (String)",")[0]);	
                msgPush.setCurrentUser((User)a2);	
            }	
            MsgPush msgPush9 = msgPush;	
            MsgPush msgPush10 = msgPush;	
            msgPush10.setSendUserCode(((User)a2).getUserCode());	
            msgPush9.setSendUserName(((User)a2).getUserName());	
            MsgPush msgPush11 = msgPush;	
            msgPush10.setSendDate(new Date());	
            if (StringUtils.isBlank((CharSequence)msgPush9.getIsMergePush())) {	
                msgPush.setIsMergePush("0");	
            }	
            if (msgPush.getPlanPushDate() == null) {	
                msgPush.setPlanPushDate(new Date());	
            }	
            MsgPush msgPush12 = msgPush;	
            msgPush12.setPushNumber(0);	
            msgPush12.setPushStatus("0");	
            msgPush.setReadStatus("0");	
        }	
        try {	
            ValidatorUtils.validateWithException(this.validator, msgPush, new Class[0]);	
        }	
        catch (ConstraintViolationException a6) {	
            a2 = ValidatorUtils.extractPropertyAndMessageAsList(a6, ": ");	
            throw new ServiceException(new StringBuilder().insert(0, "参数验证错误：").append(StringUtils.join((Iterable)a2, (String)"；")).toString());	
        }	
        super.save(msgPush);	
        if ("1".equals(msgPush.getIsMergePush())) {	
            return;	
        }	
        if (msgPush.getPlanPushDate().getTime() > System.currentTimeMillis()) {	
            return;	
        }	
        if (!"true".equals(Global.getProperty("msg.realtime.enabled"))) return;	
        new E(this, msgPush).run();	
    }	
	
    @Override	
    public MsgPush get(MsgPush msgPush) {	
        return super.get(msgPush);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateMsgPush(MsgPush msgPush) {	
        boolean bl;	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        boolean a2 = false;	
        if (msgPush.getPushNumber() >= Global.getPropertyToInteger("msg.pushFailNumber", "3")) {	
            msgPush.setPushStatus("2");	
            bl = a2 = true;	
        } else {	
            if ("2".equals(msgPush.getPushStatus())) {	
                a2 = true;	
            }	
            if ("1".equals(msgPush.getReadStatus())) {	
                msgPush.setReadDate(new Date());	
                a2 = true;	
            }	
            bl = a2;	
        }	
        if (!bl) {	
            this.update(msgPush);	
            return;	
        }	
        MsgPushServiceSupport msgPushServiceSupport = this;	
        msgPushServiceSupport.delete(msgPush);	
        msgPushServiceSupport.insert(new MsgPushed(msgPush));	
    }	
}	
	
