/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
	
public interface FileUploadService	
extends CrudServiceApi<FileUpload> {	
    public FileUploadServiceExtend getFileUploadServiceExtend();	
	
    @Override	
    public void delete(FileUpload var1);	
	
    @Override	
    public void updateStatus(FileUpload var1);	
	
    public void compressImage(FileUploadParams var1, File var2);	
	
    @Override	
    public Page<FileUpload> findPage(FileUpload var1);	
	
    @Override	
    public FileUpload get(FileUpload var1);	
	
    @Override	
    public void save(FileUpload var1);	
}	
	
