/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.FileUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 */	
package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.FileWatcher;	
	
public class FileUploadServiceExtendSupport	
implements FileUploadServiceExtend {	
    @Override	
    public void saveUploadFile(FileUpload fileUpload) {	
    }	
	
    @Override	
    public void uploadFile(FileEntity fileEntity) {	
    }	
	
    @Override	
    public boolean fileExists(FileEntity fileEntity) {	
        String a2 = fileEntity.getFileRealPath();	
        return new File(a2).exists();	
    }	
	
    @Override	
    public String getFileUrl(FileUpload fileUpload) {	
        return fileUpload.getFileEntity().getFileUrl();	
    }	
	
    @Override	
    public String downFile(FileUpload fileUpload, HttpServletRequest request, HttpServletResponse response) {	
        FileEntity a2 = fileUpload.getFileEntity();	
        File a3 = new File(a2.getFileRealPath());	
        if (a3.exists()) {	
            FileUtils.downFile((File)a3, (HttpServletRequest)request, (HttpServletResponse)response, (String)fileUpload.getFileName());	
            return null;	
        }	
        return "404";	
    }	
}	
	
