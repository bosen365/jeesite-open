/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 */	
package com.jeesite.modules.file.service;	
	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public interface FileUploadServiceExtend {	
    public boolean fileExists(FileEntity var1);	
	
    public void saveUploadFile(FileUpload var1);	
	
    public String downFile(FileUpload var1, HttpServletRequest var2, HttpServletResponse var3);	
	
    public String getFileUrl(FileUpload var1);	
	
    public void uploadFile(FileEntity var1);	
}	
	
