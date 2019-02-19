package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.file.entity.FileEntity;	
	
public interface FileEntityService extends CrudServiceApi {	
   void updateStatus(FileEntity var1);	
	
   void delete(FileEntity var1);	
	
   void save(FileEntity var1);	
	
   FileEntity getByMd5(FileEntity var1);	
	
   Page findPage(FileEntity var1);	
	
   FileEntity get(FileEntity var1);	
}	
