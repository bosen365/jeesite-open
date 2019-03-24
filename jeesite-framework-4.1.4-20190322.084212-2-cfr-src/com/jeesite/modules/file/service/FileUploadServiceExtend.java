/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.file.service;	
	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public interface FileUploadServiceExtend {	
    public String getFileUrl(FileUpload var1);	
	
    public void uploadFile(FileEntity var1);	
	
    public void saveUploadFile(FileUpload var1);	
	
    public boolean fileExists(FileEntity var1);	
	
    public String downFile(FileUpload var1, HttpServletRequest var2, HttpServletResponse var3);	
}	
	
