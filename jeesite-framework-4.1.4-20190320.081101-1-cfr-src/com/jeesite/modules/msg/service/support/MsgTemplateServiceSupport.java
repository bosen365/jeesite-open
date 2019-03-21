/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.service.support;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.gen.service.C;	
import com.jeesite.modules.msg.dao.MsgTemplateDao;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import org.hyperic.sigar.FileWatcher;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MsgTemplateServiceSupport	
extends CrudService<MsgTemplateDao, MsgTemplate>	
implements MsgTemplateService {	
    @Override	
    public Page<MsgTemplate> findPage(MsgTemplate msgTemplate) {	
        return super.findPage(msgTemplate);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(MsgTemplate msgTemplate) {	
        super.updateStatus(msgTemplate);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(MsgTemplate msgTemplate) {	
        if (!ObjectUtils.toBoolean(C.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        super.save(msgTemplate);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(MsgTemplate msgTemplate) {	
        if (!ObjectUtils.toBoolean(C.ALLATORIxDEMO().get("fnMsg")).booleanValue() && !Global.isTestProfileActive()) {	
            return;	
        }	
        super.delete(msgTemplate);	
    }	
	
    @Override	
    public MsgTemplate get(MsgTemplate msgTemplate) {	
        return super.get(msgTemplate);	
    }	
}	
	
