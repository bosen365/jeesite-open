package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.i18n.I18nMessageSource;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class LangServiceSupport extends CrudService implements LangService {	
   @Autowired	
   private I18nMessageSource i18nMessageSource;	
	
   public void clearCache() {	
      this.i18nMessageSource.clearCache();	
   }	
	
   public Page findPage(Lang lang) {	
      return super.findPage(lang);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Lang lang) {	
      super.delete(lang);	
      this.clearCache();	
   }	
	
   public Lang get(Lang lang) {	
      return (Lang)super.get(lang);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Lang lang) {	
      if (ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnI18n"))) {	
         super.save(lang);	
         this.clearCache();	
      }	
   }	
}	
