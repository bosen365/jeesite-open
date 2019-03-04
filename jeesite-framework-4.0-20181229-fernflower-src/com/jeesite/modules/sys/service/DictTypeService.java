package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.dao.DictTypeDao;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.utils.DictUtils;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class DictTypeService extends CrudService {	
   @Autowired	
   private DictDataService dictDataService;	
	
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
	
   public Page findPage(Page page, DictType dictType) {	
      return super.findPage(page, dictType);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(DictType dictType) {	
      super.delete(dictType);	
      this.dictDataService.deleteByDictType(dictType.getDictType());	
      DictUtils.clearDictCache();	
   }	
}	
