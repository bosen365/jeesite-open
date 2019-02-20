package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.FileWatcher;	
	
public class FileUploadServiceExtendSupport implements FileUploadServiceExtend {	
   public void saveUploadFile(FileUpload fileUpload) {	
   }	
	
   public void uploadFile(FileEntity fileEntity) {	
   }	
	
   public boolean fileExists(FileEntity fileEntity) {	
      String a = fileEntity.getFileRealPath();	
      return (new File(a)).exists();	
   }	
	
   public String getFileUrl(FileUpload fileUpload) {	
      return fileUpload.getFileEntity().getFileUrl();	
   }	
	
   public String downFile(FileUpload fileUpload, HttpServletRequest request, HttpServletResponse response) {	
      FileEntity a = fileUpload.getFileEntity();	
      File a;	
      if ((a = new File(a.getFileRealPath())).exists()) {	
         FileUtils.downFile(a, request, response, fileUpload.getFileName());	
         return null;	
      } else {	
         return "404";	
      }	
   }	
}	
