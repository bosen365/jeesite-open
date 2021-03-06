package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import com.jeesite.modules.sys.dao.DictDataDao;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.DictUtils;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.util.List;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class DictDataService extends TreeService {	
   public DictData get(DictData dictData) {	
      return (DictData)super.get(dictData);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(DictData dictData) {	
      super.save((TreeEntity)dictData);	
      DictUtils.clearDictCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(DictData dictData) {	
      super.updateStatus((TreeEntity)dictData);	
      DictUtils.clearDictCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateDictTypeByDictType(String newDictType, String oldDictType) {	
      if (!StringUtils.isBlank(newDictType) && !StringUtils.isBlank(oldDictType)) {	
         DictData a;	
         (a = new DictData()).setDictType(newDictType);	
         DictData a = new DictData();	
         a.setDictType(oldDictType);	
         ((DictDataDao)this.dao).updateByEntity(a, a);	
         DictUtils.clearDictCache();	
      }	
   }	
	
   public List findList(DictData dictData) {	
      dictData.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
      dictData.getSqlMap().getWhere().andBracket("is_sys", QueryType.EQ, "1").or("corp_code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
      return super.findList(dictData);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(DictData dictData) {	
      super.delete((TreeEntity)dictData);	
      DictUtils.clearDictCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void deleteByDictType(String dictType) {	
      if (!StringUtils.isBlank(dictType)) {	
         DictData a = new DictData();	
         a.setDictType(dictType);	
         ((DictDataDao)this.dao).deleteByEntity(a);	
         DictUtils.clearDictCache();	
      }	
   }	
}	
