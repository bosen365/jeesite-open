/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.common.beetl.v.m;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import com.jeesite.modules.msg.dao.MsgPushDao;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.msg.service.support.i;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Date;	
import java.util.List;	
import java.util.Map;	
import javax.validation.ConstraintViolationException;	
import javax.validation.Validator;	
import org.slf4j.Logger;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MsgPushServiceSupport	
extends CrudService<MsgPushDao, MsgPush>	
implements MsgPushService {	
    @Autowired	
    private MsgTemplateService msgTemplateService;	
    @Autowired	
    private Validator validator;	
	
    static /* synthetic */ Logger access$000(MsgPushServiceSupport x0) {	
        return x0.logger;	
    }	
	
    @Override	
    public List<MsgPush> findListByMergePush(MsgPush msgPush) {	
        return ((MsgPushDao)this.dao).findListByMergePush(msgPush);	
    }	
	
    @Override	
    public Page<MsgPush> findPage(MsgPush msgPush) {	
        return super.findPage(msgPush);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateMsgPush(MsgPush msgPush) {	
        boolean bl;	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        boolean a = false;	
        if (msgPush.getPushNumber() >= Global.getPropertyToInteger("msg.pushFailNumber", "3")) {	
            msgPush.setPushStatus("2");	
            bl = a = true;	
        } else {	
            if ("2".equals(msgPush.getPushStatus())) {	
                a = true;	
            }	
            if ("1".equals(msgPush.getReadStatus())) {	
                msgPush.setReadDate(new Date());	
                a = true;	
            }	
            bl = a;	
        }	
        if (!bl) {	
            this.update(msgPush);	
            return;	
        }	
        MsgPushServiceSupport msgPushServiceSupport = this;	
        msgPushServiceSupport.delete(msgPush);	
        msgPushServiceSupport.insert(new MsgPushed(msgPush));	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(MsgPush msgPush) {	
        super.delete(msgPush);	
    }	
	
    @Override	
    public MsgPush get(MsgPush msgPush) {	
        return super.get(msgPush);	
    }	
	
    /*	
     * Enabled force condition propagation	
     * Lifted jumps to return sites	
     */	
    @Transactional(readOnly=false)	
    @Override	
    public void save(MsgPush msgPush) {	
        Object a;	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        if (StringUtils.isNotBlank((CharSequence)msgPush.getMsgContent())) {	
            MsgPush msgPush2;	
            BaseMsgContent a2 = (BaseMsgContent)msgPush.parseMsgContent(BaseMsgContent.class);	
            if (StringUtils.isNotBlank((CharSequence)a2.getTplKey())) {	
                void a3;	
                a = new MsgTemplate();	
                ((MsgTemplate)a).setTplKey(a2.getTplKey());	
                List<Object> a4 = this.msgTemplateService.findList(a);	
                if (a4.size() <= 0) throw new ServiceException(new StringBuilder().insert(0, "模板Key不存在：").append(a2.getTplKey()).toString());	
                String string = BeetlUtils.renderFromString(((MsgTemplate)a4.get(0)).getTplContent(), a2.getTplData());	
                MsgPush msgPush3 = msgPush;	
                msgPush2 = msgPush3;	
                a2.setContent((String)a3);	
                msgPush3.setMsgContent(JsonMapper.toJson((Object)a2));	
            } else {	
                msgPush2 = msgPush;	
            }	
            if (StringUtils.isNotBlank((CharSequence)msgPush2.getReceiveUserCode())) {	
                a = UserUtils.get(msgPush.getReceiveUserCode());	
                if (a == null) {	
                    throw new ServiceException(new StringBuilder().insert(0, "接受者用户编码不存在：").append(msgPush.getReceiveUserCode()).toString());	
                }	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveCode())) {	
                    MsgPush msgPush4;	
                    if ("pc".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush5 = msgPush;	
                        msgPush4 = msgPush5;	
                        msgPush5.setReceiveCode(((User)a).getUserCode());	
                    } else if ("app".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush6 = msgPush;	
                        msgPush4 = msgPush6;	
                        msgPush6.setReceiveCode(((User)a).getUserCode());	
                    } else if ("sms".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush7 = msgPush;	
                        msgPush4 = msgPush7;	
                        msgPush7.setReceiveCode(((User)a).getMobile());	
                    } else if ("email".equals(msgPush.getMsgType())) {	
                        MsgPush msgPush8 = msgPush;	
                        msgPush4 = msgPush8;	
                        msgPush8.setReceiveCode(((User)a).getEmail());	
                    } else {	
                        if ("weixin".equals(msgPush.getMsgType())) {	
                            msgPush.setReceiveCode(((User)a).getWxOpenid());	
                        }	
                        msgPush4 = msgPush;	
                    }	
                    if (StringUtils.isBlank((CharSequence)msgPush4.getReceiveCode())) {	
                        msgPush.setReceiveCode("NONE");	
                    }	
                }	
                if (StringUtils.isBlank((CharSequence)msgPush.getReceiveUserName())) {	
                    msgPush.setReceiveUserName(((User)a).getUserName());	
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
            if (StringUtils.isBlank((CharSequence)((User)(a = msgPush.getCurrentUser())).getUserCode())) {	
                a = UserUtils.get(StringUtils.split((String)User.SUPER_ADMIN_CODE, (String)",")[0]);	
                msgPush.setCurrentUser((User)a);	
            }	
            MsgPush msgPush9 = msgPush;	
            MsgPush msgPush10 = msgPush;	
            msgPush10.setSendUserCode(((User)a).getUserCode());	
            msgPush9.setSendUserName(((User)a).getUserName());	
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
        catch (ConstraintViolationException a5) {	
            a = ValidatorUtils.extractPropertyAndMessageAsList(a5, ": ");	
            throw new ServiceException(new StringBuilder().insert(0, "参数验证错误：").append(StringUtils.join((Iterable)a, (String)"；")).toString());	
        }	
        super.save(msgPush);	
        if ("1".equals(msgPush.getIsMergePush())) {	
            return;	
        }	
        if (msgPush.getPlanPushDate().getTime() > System.currentTimeMillis()) {	
            return;	
        }	
        if (msgPush.getIsRealtimePush() != null && !msgPush.getIsRealtimePush().booleanValue() || !"true".equals(Global.getProperty("msg.realtime.enabled"))) return;	
        new i(this, msgPush).start();	
    }	
}	
	
