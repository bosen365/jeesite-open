/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.i18n.I18nMessageSource;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.gen.service.M;	
import com.jeesite.modules.sys.dao.LangDao;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class LangServiceSupport	
extends CrudService<LangDao, Lang>	
implements LangService {	
    @Autowired	
    private I18nMessageSource i18nMessageSource;	
	
    @Override	
    public void clearCache() {	
        this.i18nMessageSource.clearCache();	
    }	
	
    @Override	
    public Page<Lang> findPage(Lang lang) {	
        return super.findPage(lang);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Lang lang) {	
        LangServiceSupport langServiceSupport = this;	
        super.delete(lang);	
        langServiceSupport.clearCache();	
    }	
	
    @Override	
    public Lang get(Lang lang) {	
        return super.get(lang);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Lang lang) {	
        if (!ObjectUtils.toBoolean(M.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
            return;	
        }	
        LangServiceSupport langServiceSupport = this;	
        super.save(lang);	
        langServiceSupport.clearCache();	
    }	
}	
	
