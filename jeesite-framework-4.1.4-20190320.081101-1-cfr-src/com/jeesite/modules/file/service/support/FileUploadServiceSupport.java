/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.image.ImageUtils	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.image.ImageUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.file.dao.FileUploadDao;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class FileUploadServiceSupport	
extends CrudService<FileUploadDao, FileUpload>	
implements FileUploadService {	
    @Autowired	
    private FileUploadServiceExtend fileUploadServiceExtend;	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(FileUpload fileUpload) {	
        super.updateStatus(fileUpload);	
    }	
	
    @Override	
    public FileUploadServiceExtend getFileUploadServiceExtend() {	
        return this.fileUploadServiceExtend;	
    }	
	
    @Override	
    public void compressImage(FileUploadParams params, File imageFile) {	
        if (!"image".equals(params.getUploadType())) {	
            return;	
        }	
        ImageUtils.thumbnails((File)imageFile, (int)params.getImageMaxWidth(), (int)params.getImageMaxHeight(), null);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(FileUpload fileUpload) {	
        super.delete(fileUpload);	
    }	
	
    @Override	
    public FileUpload get(FileUpload fileUpload) {	
        return super.get(fileUpload);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(FileUpload fileUpload) {	
        super.save(fileUpload);	
    }	
	
    @Override	
    public Page<FileUpload> findPage(FileUpload fileUpload) {	
        return super.findPage(fileUpload);	
    }	
}	
	
