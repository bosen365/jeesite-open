package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.entity.FileUpload;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class FileUploadService extends CrudService {	
   @Transactional(	
      readOnly = false	
   )	
   public void save(FileUpload fileUpload) {	
      super.save(fileUpload);	
   }	
	
   public FileUpload get(FileUpload fileUpload) {	
      return (FileUpload)super.get(fileUpload);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(FileUpload fileUpload) {	
      super.updateStatus(fileUpload);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(FileUpload fileUpload) {	
      super.delete(fileUpload);	
   }	
	
   public Page findPage(Page page, FileUpload fileUpload) {	
      return super.findPage(page, fileUpload);	
   }	
}	
