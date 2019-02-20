package com.jeesite.modules.msg.service.support;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.gen.service.M;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.hyperic.sigar.util.PrintfFormat;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class MsgTemplateServiceSupport extends CrudService implements MsgTemplateService {	
   public MsgTemplate get(MsgTemplate msgTemplate) {	
      return (MsgTemplate)super.get(msgTemplate);	
   }	
	
   public Page findPage(MsgTemplate msgTemplate) {	
      return super.findPage(msgTemplate);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(MsgTemplate msgTemplate) {	
      if (ObjectUtils.toBoolean(M.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         super.delete(msgTemplate);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(MsgTemplate msgTemplate) {	
      if (ObjectUtils.toBoolean(M.ALLATORIxDEMO().get("fnMsg")) || Global.isTestProfileActive()) {	
         super.save(msgTemplate);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(MsgTemplate msgTemplate) {	
      super.updateStatus(msgTemplate);	
   }	
}	
