/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.file.service.support;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.l.I;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.awt.image.BufferedImage;	
import java.io.File;	
import java.util.Map;	
import javax.imageio.ImageIO;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.SysInfo;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class FileUploadServiceExtendSupport	
implements FileUploadServiceExtend {	
    protected static Logger logger = LoggerFactory.getLogger(FileUploadServiceExtend.class);	
	
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
	
    @Override	
    public void saveUploadFile(FileUpload fileUpload) {	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void uploadFile(FileEntity fileEntity) {	
        String[] arrstring = new String[6];	
        arrstring[0] = "ng";	
        arrstring[1] = "jpg";	
        arrstring[2] = "jeg";	
        arrstring[3] = "bmp";	
        arrstring[4] = "ico";	
        arrstring[5] = "gif";	
        if (StringUtils.inString(fileEntity.getFileExtension(), arrstring)) {	
            try {	
                void a;	
                File a2 = new File(fileEntity.getFileRealPath());	
                BufferedImage a3 = ImageIO.read(a2);	
                Map<String, Object> map = fileEntity.getFileMetaMap();	
                void v1 = a;	
                v1.put("width", a3.getWidth());	
                v1.put("height", a3.getHeight());	
                return;	
            }	
            catch (Exception a) {	
                logger.error("图片信息获取失败！", a);	
            }	
        }	
    }	
	
    @Override	
    public boolean fileExists(FileEntity fileEntity) {	
        String a = fileEntity.getFileRealPath();	
        return new File(a).exists();	
    }	
	
    @Override	
    public String getFileUrl(FileUpload fileUpload) {	
        return fileUpload.getFileEntity().getFileUrl();	
    }	
}	
	
