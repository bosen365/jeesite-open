/*	
 * Decompiled with CFR 0.141.	
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
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(FileEntity fileEntity) {	
        super.save(fileEntity);	
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
	
    @Override	
    public FileEntity get(FileEntity fileEntity) {	
        return super.get(fileEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(FileEntity fileEntity) {	
        super.updateStatus(fileEntity);	
    }	
	
    @Override	
    public FileEntity getByMd5(FileEntity fileEntity) {	
        FileEntity a = new FileEntity();	
        a.setFileMd5(fileEntity.getFileMd5());	
        if (StringUtils.isNotBlank(a.getFileMd5())) {	
            FileEntity fileEntity2 = a;	
            fileEntity2.setPage(new Page(1, 1, -1L));	
            Page<FileEntity> a2 = this.findPage(fileEntity2);	
            if (a2.getList().size() > 0) {	
                fileEntity = a2.getList().get(0);	
            }	
        }	
        FileEntity fileEntity3 = fileEntity;	
        fileEntity3.setStatus("1");	
        if (StringUtils.isNotBlank(fileEntity3.getFileId()) && this.fileUploadServiceExtend.fileExists(fileEntity)) {	
            fileEntity.setStatus("0");	
        }	
        return fileEntity;	
    }	
}	
	
