package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.entity.FileEntity;	
import java.io.File;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class FileEntityService extends CrudService {	
   public Page findPage(Page page, FileEntity fileEntity) {	
      return super.findPage(page, fileEntity);	
   }	
	
   public FileEntity get(FileEntity fileEntity) {	
      return (FileEntity)super.get(fileEntity);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(FileEntity fileEntity) {	
      super.save(fileEntity);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(FileEntity fileEntity) {	
      super.updateStatus(fileEntity);	
   }	
	
   public FileEntity getByMd5(FileEntity fileEntity) {	
      FileEntity a;	
      FileEntity var10000 = a = new FileEntity();	
      var10000.setFileMd5(fileEntity.getFileMd5());	
      Page a;	
      if (StringUtils.isNotBlank(var10000.getFileMd5()) && (a = this.findPage(new Page(1, 1, -1L), a)).getList().size() > 0) {	
         fileEntity = (FileEntity)a.getList().get(0);	
      }	
	
      fileEntity.setStatus("1");	
      if (StringUtils.isNotBlank(fileEntity.getFileId())) {	
         String a = fileEntity.getFileRealPath();	
         if ((new File(a)).exists()) {	
            fileEntity.setStatus("0");	
         }	
      }	
	
      return fileEntity;	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(FileEntity fileEntity) {	
      super.delete(fileEntity);	
   }	
}	
