package com.jeesite.modules.msg.service.support;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.msg.dao.MsgPushDao;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.msg.task.MsgPushTask;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Date;	
import java.util.List;	
import javax.validation.ConstraintViolationException;	
import javax.validation.Validator;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.SigarException;	
import org.hyperic.sigar.Swap;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class MsgPushServiceSupport extends CrudService implements MsgPushService {	
   private MsgPushTask msgPushTask;	
   @Autowired	
   private Validator validator;	
   @Autowired	
   private MsgTemplateService msgTemplateService;	
	
   public Page findPage(MsgPush msgPush) {	
      return super.findPage(msgPush);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(MsgPush msgPush) {	
      super.delete(msgPush);	
   }	
	
   public List findListByMergePush(MsgPush msgPush) {	
      return ((MsgPushDao)this.dao).findListByMergePush(msgPush);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(final MsgPush msgPush) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         if (StringUtils.isNotBlank(msgPush.getMsgContent())) {	
            BaseMsgContent a;	
            MsgPush var10000;	
            if (StringUtils.isNotBlank((a = (BaseMsgContent)msgPush.parseMsgContent(BaseMsgContent.class)).getTplKey())) {	
               MsgTemplate a = new MsgTemplate();	
               a.setTplKey(a.getTplKey());	
               List a;	
               if ((a = this.msgTemplateService.findList(a)).size() <= 0) {	
                  throw new ServiceException((new StringBuilder()).insert(0, "模板!ey不存在：").append(a.getTplKey()).toString());	
               }	
	
               String a = BeetlUtils.renderFromString(((MsgTemplate)a.get(0)).getTplContent(), a.getTplData());	
               var10000 = msgPush;	
               a.setContent(a);	
               msgPush.setMsgContent(JsonMapper.toJson(a));	
            } else {	
               var10000 = msgPush;	
            }	
	
            User a;	
            if (StringUtils.isNotBlank(var10000.getReceiveUserCode())) {	
               if ((a = UserUtils.get(msgPush.getReceiveUserCode())) == null) {	
                  throw new ServiceException((new StringBuilder()).insert(0, "接受者用户编码不存在：").append(msgPush.getReceiveUserCode()).toString());	
               }	
	
               if (StringUtils.isBlank(msgPush.getReceiveCode())) {	
                  if ("pc".equals(msgPush.getMsgType())) {	
                     var10000 = msgPush;	
                     msgPush.setReceiveCode(a.getUserCode());	
                  } else if ("app".equals(msgPush.getMsgType())) {	
                     var10000 = msgPush;	
                     msgPush.setReceiveCode(a.getUserCode());	
                  } else if ("sms".equals(msgPush.getMsgType())) {	
                     var10000 = msgPush;	
                     msgPush.setReceiveCode(a.getMobile());	
                  } else if ("email".equals(msgPush.getMsgType())) {	
                     var10000 = msgPush;	
                     msgPush.setReceiveCode(a.getEmail());	
                  } else {	
                     if ("weixin".equals(msgPush.getMsgType())) {	
                        msgPush.setReceiveCode(a.getWxOpenid());	
                     }	
	
                     var10000 = msgPush;	
                  }	
	
                  if (StringUtils.isBlank(var10000.getReceiveCode())) {	
                     msgPush.setReceiveCode("NONE");	
                  }	
               }	
	
               if (StringUtils.isBlank(msgPush.getReceiveUserName())) {	
                  msgPush.setReceiveUserName(a.getUserName());	
               }	
            }	
	
            if (StringUtils.isNotBlank(msgPush.getReceiveCode())) {	
               if (StringUtils.isBlank(msgPush.getReceiveUserCode())) {	
                  msgPush.setReceiveUserCode("NONE");	
               }	
	
               if (StringUtils.isBlank(msgPush.getReceiveUserName())) {	
                  msgPush.setReceiveUserName("NONE");	
               }	
            }	
	
            if (StringUtils.isBlank((a = msgPush.getCurrentUser()).getUserCode())) {	
               a = UserUtils.get(StringUtils.split(User.SUPER_ADMIN_CODE, ",")[0]);	
               msgPush.setCurrentUser(a);	
            }	
	
            msgPush.setSendUserCode(a.getUserCode());	
            msgPush.setSendUserName(a.getUserName());	
            msgPush.setSendDate(new Date());	
            if (StringUtils.isBlank(msgPush.getIsMergePush())) {	
               msgPush.setIsMergePush("0");	
            }	
	
            if (msgPush.getPlanPushDate() == null) {	
               msgPush.setPlanPushDate(new Date());	
            }	
	
            msgPush.setPushNumber(0);	
            msgPush.setPushStatus("0");	
            msgPush.setReadStatus("0");	
         }	
	
         try {	
            Validator var10 = this.validator;	
            Class[] var10002 = new Class[0];	
            boolean var10004 = true;	
            ValidatorUtils.validateWithException(var10, msgPush, var10002);	
         } catch (ConstraintViolationException var7) {	
            List a = ValidatorUtils.extractPropertyAndMessageAsList(var7, ": ");	
            throw new ServiceException((new StringBuilder()).insert(0, "参数验证错误：").append(StringUtils.join(a, "；")).toString());	
         }	
	
         super.save(msgPush);	
         if (!"1".equals(msgPush.getIsMergePush())) {	
            if (msgPush.getPlanPushDate().getTime() <= System.currentTimeMillis()) {	
               if ("true".equals(Global.getProperty("msg.realtime.enabled"))) {	
                  (new Thread() {	
                     public void run() {	
                        try {	
                           if (MsgPushServiceSupport.this.msgPushTask == null) {	
                              MsgPushServiceSupport.this.msgPushTask = (MsgPushTask)SpringUtils.getBean(Global.getProperty("msg.raltim.beanName", "msgLocalPushTask"));	
                           }	
	
                           if (MsgPushServiceSupport.this.msgPushTask != null) {	
                              MsgPushServiceSupport.this.msgPushTask.executeMsgPush(msgPush);	
                           } else {	
                              MsgPushServiceSupport.this.logger.error((new StringBuilder()).insert(0, "未找到 ").append(MsgPushTask.class.getName()).append(" 的实现类。").toString());	
                           }	
                        } catch (Exception var2) {	
                           MsgPushServiceSupport.this.logger.error("实时消息发送失败，推送服务配置不正确。", var2);	
                        }	
                     }	
                  }).run();	
               }	
	
            }	
         }	
      }	
   }	
	
   public MsgPush get(MsgPush msgPush) {	
      return (MsgPush)super.get(msgPush);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateMsgPush(MsgPush msgPush) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         int a = false;	
         boolean var10000;	
         if (msgPush.getPushNumber() >= Global.getPropertyToInteger("msg.pushFailNumber", "3")) {	
            var10000 = true;	
            msgPush.setPushStatus("2");	
            a = true;	
         } else {	
            if ("2".equals(msgPush.getPushStatus())) {	
               a = true;	
            }	
	
            if ("1".equals(msgPush.getReadStatus())) {	
               msgPush.setReadDate(new Date());	
               a = true;	
            }	
	
            var10000 = a;	
         }	
	
         if (!var10000) {	
            this.update(msgPush);	
         } else {	
            this.delete(msgPush);	
            this.insert(new MsgPushed(msgPush));	
         }	
      }	
   }	
}	
