/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.awt.image.BufferedImage;	
import java.io.File;	
import java.util.Map;	
import javax.imageio.ImageIO;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.ProcMem;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class FileUploadServiceExtendSupport	
implements FileUploadServiceExtend {	
    protected static Logger logger = LoggerFactory.getLogger(FileUploadServiceExtend.class);	
	
    @Override	
    public void saveUploadFile(FileUpload fileUpload) {	
    }	
	
    @Override	
    public void uploadFile(FileEntity fileEntity) {	
        if (StringUtils.inString(fileEntity.getFileExtension(), "png", "jpg", "jpeg", "bmp", "ico", "gif")) {	
            try {	
                void a;	
                File a2 = new File(fileEntity.getFileRealPath());	
                BufferedImage a3 = ImageIO.read(a2);	
                Map<String, Object> map = fileEntity.getFileMetaMap();	
                void v0 = a;	
                v0.put("width", a3.getWidth());	
                v0.put("height", a3.getHeight());	
                return;	
            }	
            catch (Exception a) {	
                logger.error("图片信息获取失败！", a);	
            }	
        }	
    }	
	
    @Override	
    public String getFileUrl(FileUpload fileUpload) {	
        return fileUpload.getFileEntity().getFileUrl();	
    }	
	
    @Override	
    public boolean fileExists(FileEntity fileEntity) {	
        String a = fileEntity.getFileRealPath();	
        return new File(a).exists();	
    }	
	
    @Override	
    public String downFile(FileUpload fileUpload, HttpServletRequest request, HttpServletResponse response) {	
        FileEntity a = fileUpload.getFileEntity();	
        File a2 = new File(a.getFileRealPath());	
        if (a2.exists()) {	
            FileUtils.downFile(a2, request, response, fileUpload.getFileName());	
            return null;	
        }	
        return "404";	
    }	
}	
	
