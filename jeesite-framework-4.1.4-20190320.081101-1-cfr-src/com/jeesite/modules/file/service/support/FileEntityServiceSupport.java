/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.dao.FileEntityDao;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.util.List;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class FileEntityServiceSupport	
extends CrudService<FileEntityDao, FileEntity>	
implements FileEntityService {	
    @Autowired	
    private FileUploadServiceExtend fileUploadServiceExtend;	
	
    @Override	
    public FileEntity get(FileEntity fileEntity) {	
        return super.get(fileEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(FileEntity fileEntity) {	
        super.updateStatus(fileEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(FileEntity fileEntity) {	
        super.save(fileEntity);	
    }	
	
    @Override	
    public FileEntity getByMd5(FileEntity fileEntity) {	
        FileEntity a = new FileEntity();	
        a.setFileMd5(fileEntity.getFileMd5());	
        if (StringUtils.isNotBlank((CharSequence)a.getFileMd5())) {	
            FileEntity fileEntity2 = a;	
            fileEntity2.setPage(new Page(1, 1, -1L));	
            Page<FileEntity> a2 = this.findPage(fileEntity2);	
            if (a2.getList().size() > 0) {	
                fileEntity = a2.getList().get(0);	
            }	
        }	
        FileEntity fileEntity3 = fileEntity;	
        fileEntity3.setStatus("1");	
        if (StringUtils.isNotBlank((CharSequence)fileEntity3.getFileId()) && this.fileUploadServiceExtend.fileExists(fileEntity)) {	
            fileEntity.setStatus("0");	
        }	
        return fileEntity;	
    }	
	
    @Override	
    public Page<FileEntity> findPage(FileEntity fileEntity) {	
        return super.findPage(fileEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(FileEntity fileEntity) {	
        super.delete(fileEntity);	
    }	
}	
	
