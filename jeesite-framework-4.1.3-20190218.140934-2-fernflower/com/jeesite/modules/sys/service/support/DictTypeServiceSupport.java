package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.dao.DictTypeDao;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.service.DictDataService;	
import com.jeesite.modules.sys.service.DictTypeService;	
import com.jeesite.modules.sys.utils.DictUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class DictTypeServiceSupport extends CrudService implements DictTypeService {	
   @Autowired	
   private DictDataService dictDataService;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(DictType dictType) {	
      super.delete(dictType);	
      this.dictDataService.deleteByDictType(dictType.getDictType());	
      DictUtils.clearDictCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(DictType dictType, DictType old) {	
      super.save(dictType);	
      if (!StringUtils.equals(dictType.getDictType(), old.getDictType())) {	
         this.dictDataService.updateDictTypeByDictType(dictType.getDictType(), old.getDictType());	
      }	
	
      DictUtils.clearDictCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(DictType dictType) {	
      super.updateStatus(dictType);	
      DictUtils.clearDictCache();	
   }	
	
   public DictType get(DictType dictType) {	
      if (dictType == null) {	
         return null;	
      } else if (StringUtils.isNotBlank(dictType.getDictType())) {	
         DictType a = new DictType();	
         a.setDictType(dictType.getDictType());	
         return (DictType)((DictTypeDao)this.dao).getByEntity(a);	
      } else {	
         return (DictType)super.get(dictType);	
      }	
   }	
	
   public Page findPage(DictType dictType) {	
      return super.findPage(dictType);	
   }	
}	
