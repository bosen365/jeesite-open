package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.i.l;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import com.jeesite.modules.sys.entity.Lang;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@DependsOn({"dbUpgrade"})	
@Transactional(	
   readOnly = true	
)	
public class LangService extends CrudService {	
   @Autowired	
   private l i18nMessageSource;	
	
   public Lang get(Lang lang) {	
      return (Lang)super.get(lang);	
   }	
	
   public Page findPage(Page page, Lang lang) {	
      return super.findPage(page, lang);	
   }	
	
   public void clearCache() {	
      this.i18nMessageSource.clearCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Lang lang) {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n"))) {	
         throw new ServiceException("当前版本未开放此功能！");	
      } else {	
         super.save(lang);	
         this.clearCache();	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Lang lang) {	
      super.delete(lang);	
      this.clearCache();	
   }	
}	
