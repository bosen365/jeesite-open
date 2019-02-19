package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class FileEntityServiceSupport extends CrudService implements FileEntityService {	
   @Autowired	
   private FileUploadServiceExtend fileUploadServiceExtend;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(FileEntity fileEntity) {	
      super.save(fileEntity);	
   }	
	
   public FileEntity getByMd5(FileEntity fileEntity) {	
      FileEntity a;	
      FileEntity var10000 = a = new FileEntity();	
      var10000.setFileMd5(fileEntity.getFileMd5());	
      if (StringUtils.isNotBlank(var10000.getFileMd5())) {	
         a.setPage(new Page(1, 1, -1L));	
         Page a;	
         if ((a = this.findPage(a)).getList().size() > 0) {	
            fileEntity = (FileEntity)a.getList().get(0);	
         }	
      }	
	
      fileEntity.setStatus("1");	
      if (StringUtils.isNotBlank(fileEntity.getFileId()) && this.fileUploadServiceExtend.fileExists(fileEntity)) {	
         fileEntity.setStatus("0");	
      }	
	
      return fileEntity;	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(FileEntity fileEntity) {	
      super.delete(fileEntity);	
   }	
	
   public FileEntity get(FileEntity fileEntity) {	
      return (FileEntity)super.get(fileEntity);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(FileEntity fileEntity) {	
      super.updateStatus(fileEntity);	
   }	
	
   public Page findPage(FileEntity fileEntity) {	
      return super.findPage(fileEntity);	
   }	
}	
