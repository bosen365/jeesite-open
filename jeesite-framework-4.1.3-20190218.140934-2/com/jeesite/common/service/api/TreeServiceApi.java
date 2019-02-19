package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.TreeEntity;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public interface TreeServiceApi extends TreeQueryServiceApi {	
   void fixTreeData();	
	
   void delete(TreeEntity var1);	
	
   void save(TreeEntity var1);	
	
   void fixTreeData(String var1);	
	
   void updateStatus(TreeEntity var1);	
	
   void updateTreeSort(TreeEntity var1);	
}	
