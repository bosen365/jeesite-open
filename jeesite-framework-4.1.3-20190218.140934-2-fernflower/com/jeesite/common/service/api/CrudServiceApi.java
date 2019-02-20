package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.DataEntity;	
	
public interface CrudServiceApi extends QueryServiceApi {	
   void update(DataEntity var1);	
	
   void save(DataEntity var1);	
	
   void updateStatus(DataEntity var1);	
	
   void insert(DataEntity var1);	
	
   void delete(DataEntity var1);	
}	
