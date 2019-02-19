package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.service.api.TreeServiceApi;	
import com.jeesite.modules.sys.entity.DictData;	
import java.util.List;	
	
public interface DictDataService extends TreeServiceApi {	
   void updateStatus(DictData var1);	
	
   List findList(DictData var1);	
	
   DictData get(DictData var1);	
	
   void updateDictTypeByDictType(String var1, String var2);	
	
   void save(DictData var1);	
	
   void deleteByDictType(String var1);	
	
   void delete(DictData var1);	
}	
