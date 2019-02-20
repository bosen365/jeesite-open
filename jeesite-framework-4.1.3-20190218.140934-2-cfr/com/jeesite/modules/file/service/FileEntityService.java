/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.file.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.file.entity.FileEntity;	
	
public interface FileEntityService	
extends CrudServiceApi<FileEntity> {	
    @Override	
    public void updateStatus(FileEntity var1);	
	
    @Override	
    public void delete(FileEntity var1);	
	
    @Override	
    public void save(FileEntity var1);	
	
    public FileEntity getByMd5(FileEntity var1);	
	
    @Override	
    public Page<FileEntity> findPage(FileEntity var1);	
	
    @Override	
    public FileEntity get(FileEntity var1);	
}	
	
