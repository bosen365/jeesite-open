package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.image.ImageUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class FileUploadServiceSupport extends CrudService implements FileUploadService {	
   @Autowired	
   private FileUploadServiceExtend fileUploadServiceExtend;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(FileUpload fileUpload) {	
      super.delete(fileUpload);	
   }	
	
   public FileUploadServiceExtend getFileUploadServiceExtend() {	
      return this.fileUploadServiceExtend;	
   }	
	
   public FileUpload get(FileUpload fileUpload) {	
      return (FileUpload)super.get(fileUpload);	
   }	
	
   public void compressImage(FileUploadParams params, File imageFile) {	
      if ("image".equals(params.getUploadType())) {	
         ImageUtils.thumbnails(imageFile, params.getImageMaxWidth(), params.getImageMaxHeight(), (String)null);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(FileUpload fileUpload) {	
      super.save(fileUpload);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(FileUpload fileUpload) {	
      super.updateStatus(fileUpload);	
   }	
	
   public Page findPage(FileUpload fileUpload) {	
      return super.findPage(fileUpload);	
   }	
}	
