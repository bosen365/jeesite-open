package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.io.File;	
	
public interface FileUploadService extends CrudServiceApi {	
   void delete(FileUpload var1);	
	
   void save(FileUpload var1);	
	
   Page findPage(FileUpload var1);	
	
   FileUploadServiceExtend getFileUploadServiceExtend();	
	
   void compressImage(FileUploadParams var1, File var2);	
	
   FileUpload get(FileUpload var1);	
	
   void updateStatus(FileUpload var1);	
}	
