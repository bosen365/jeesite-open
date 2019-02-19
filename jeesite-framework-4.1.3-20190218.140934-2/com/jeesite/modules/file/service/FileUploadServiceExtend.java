package com.jeesite.modules.file.service;	
	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public interface FileUploadServiceExtend {	
   void saveUploadFile(FileUpload var1);	
	
   String downFile(FileUpload var1, HttpServletRequest var2, HttpServletResponse var3);	
	
   String getFileUrl(FileUpload var1);	
	
   void uploadFile(FileEntity var1);	
	
   boolean fileExists(FileEntity var1);	
}	
