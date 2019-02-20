/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.j.e;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import java.io.Serializable;	
import java.util.HashMap;	
import java.util.Map;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class FileUploadParams	
implements Serializable {	
    public static final String FILEUPLOAD_BASE_URL = "fileupload";	
    private static final long serialVersionUID = 1L;	
    private Long maxFileSize;	
    private String fileExtension;	
    private String fileMd5;	
    private String uploadPath;	
    private String allowSuffixes;	
    private String imageAllowSuffixes;	
    private String bizKey;	
    private String mediaAllowSuffixes;	
    private String bizType;	
    private Integer imageMaxWidth;	
    private String relativePath;	
    private Integer imageMaxHeight;	
    private String uploadType;	
    private String fileName;	
    private String fileAllowSuffixes;	
    private Map<String, String> extend;	
	
    public void setImageAllowSuffixes(String imageAllowSuffixes) {	
        this.imageAllowSuffixes = imageAllowSuffixes;	
    }	
	
    public String getAllowSuffixes() {	
        return this.allowSuffixes;	
    }	
	
    public Long getMaxFileSize() {	
        return this.maxFileSize;	
    }	
	
    public void setFileName(String fileName) {	
        this.fileName = fileName;	
    }	
	
    public Map<String, String> getExtend() {	
        return this.extend;	
    }	
	
    public void setBizKey(String bizKey) {	
        this.bizKey = bizKey;	
    }	
	
    public Integer getImageMaxWidth() {	
        return this.imageMaxWidth;	
    }	
	
    public String getFileAllowSuffixes() {	
        return this.fileAllowSuffixes;	
    }	
	
    public FileUploadParams() {	
        FileUploadParams fileUploadParams = this;	
        this.uploadType = "file";	
        fileUploadParams.imageMaxWidth = -1;	
        fileUploadParams.imageMaxHeight = -1;	
        fileUploadParams.extend = MapUtils.newHashMap();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 4 ^ 5;	
        int n4 = n2;	
        int n5 = 4;	
        (3 ^ 5) << 4 ^ (3 << 2 ^ 3);	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public String getUploadType() {	
        return this.uploadType;	
    }	
	
    public void setMaxFileSize(Long maxFileSize) {	
        this.maxFileSize = maxFileSize;	
    }	
	
    public void setAllowSuffixes(String allowSuffixes) {	
        this.allowSuffixes = allowSuffixes;	
    }	
	
    public void setImageMaxHeight(Integer imageMaxHeight) {	
        this.imageMaxHeight = imageMaxHeight;	
    }	
	
    public void setRelativePath(String relativePath) {	
        this.relativePath = relativePath;	
    }	
	
    public String getFileExtension() {	
        return this.fileExtension;	
    }	
	
    public void setUploadPath(String uploadPath) {	
        this.uploadPath = uploadPath;	
    }	
	
    public void setFileAllowSuffixes(String fileAllowSuffixes) {	
        this.fileAllowSuffixes = fileAllowSuffixes;	
    }	
	
    public void setMediaAllowSuffixes(String mediaAllowSuffixes) {	
        this.mediaAllowSuffixes = mediaAllowSuffixes;	
    }	
	
    public void setFileMd5(String fileMd5) {	
        this.fileMd5 = fileMd5;	
    }	
	
    public String getUploadPath() {	
        return this.uploadPath;	
    }	
	
    public void setFileExtension(String fileExtension) {	
        this.fileExtension = fileExtension;	
    }	
	
    public Integer getImageMaxHeight() {	
        return this.imageMaxHeight;	
    }	
	
    public String getMediaAllowSuffixes() {	
        return this.mediaAllowSuffixes;	
    }	
	
    public void setExtend(Map<String, String> extend) {	
        this.extend = extend;	
    }	
	
    public String getRelativePath() {	
        return this.relativePath;	
    }	
	
    public FileUploadParams initialize() {	
        this.uploadPath = Global.getProperty("file.uploadPath", "yyyy}MM}/");	
        String[] a2 = StringUtils.substringsBetween((String)this.uploadPath, (String)"{", (String)"}");	
        if (a2 != null) {	
            int n;	
            String[] arrstring = a2;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                if (StringUtils.inString((String)a3, (String[])new String[]{"yyyy", "MM", "dd", "HH", "mm", "ss", "E"})) {	
                    this.uploadPath = StringUtils.replace((String)this.uploadPath, (String)new StringBuilder().insert(0, "").append(a3).append("}").toString(), (String)DateUtils.getDate((String)a3));	
                }	
                n3 = ++n;	
            }	
        }	
        this.relativePath = FileUtils.path((String)this.uploadPath);	
        String a4 = Global.getProperty("file.maxFileSize");	
        if (StringUtils.isNotBlank((CharSequence)a4)) {	
            int n;	
            String[] a5 = StringUtils.split((String)a4, (String)"*");	
            this.maxFileSize = 1L;	
            String[] arrstring = a5;	
            int a3 = a5.length;	
            int n4 = n = 0;	
            while (n4 < a3) {	
                String a6 = arrstring[n];	
                this.maxFileSize = this.maxFileSize * Long.valueOf(a6);	
                n4 = ++n;	
            }	
        } else {	
            this.maxFileSize = 0L;	
        }	
        if (this.maxFileSize < 1L) {	
            this.maxFileSize = 0x500000L;	
        }	
        if (this.fileName != null) {	
            FileUploadParams fileUploadParams = this;	
            fileUploadParams.fileName = fileUploadParams.fileName.trim();	
            fileUploadParams.fileExtension = FileUtils.getFileExtension((String)fileUploadParams.fileName);	
        }	
        FileUploadParams fileUploadParams = this;	
        fileUploadParams.imageAllowSuffixes = Global.getProperty("file.imageAllowSuffixes", "-1");	
        fileUploadParams.mediaAllowSuffixes = Global.getProperty("file.mediaAllowSuffixes", "-1");	
        this.fileAllowSuffixes = Global.getProperty("file.fileAllowSuffixes", "-1");	
        if ("image".equals(this.uploadType)) {	
            FileUploadParams fileUploadParams2 = this;	
            fileUploadParams2.allowSuffixes = fileUploadParams2.imageAllowSuffixes;	
            return fileUploadParams2;	
        }	
        if ("media".equals(this.uploadType)) {	
            FileUploadParams fileUploadParams3 = this;	
            fileUploadParams3.allowSuffixes = fileUploadParams3.mediaAllowSuffixes;	
            return fileUploadParams3;	
        }	
        if ("file".equals(this.uploadType)) {	
            this.allowSuffixes = this.fileAllowSuffixes;	
            return this;	
        }	
        if (StringUtils.contains((CharSequence)this.imageAllowSuffixes, (CharSequence)new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "image";	
        }	
        if (StringUtils.contains((CharSequence)this.mediaAllowSuffixes, (CharSequence)new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "media";	
        }	
        if (StringUtils.contains((CharSequence)this.fileAllowSuffixes, (CharSequence)new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "file";	
        }	
        this.allowSuffixes = new StringBuilder().insert(0, this.imageAllowSuffixes).append(",").append(this.mediaAllowSuffixes).append(",").append(this.fileAllowSuffixes).append(",").toString();	
        return this;	
    }	
	
    public void setBizType(String bizType) {	
        this.bizType = bizType;	
    }	
	
    public String getFileName() {	
        return this.fileName;	
    }	
	
    public String getImageAllowSuffixes() {	
        return this.imageAllowSuffixes;	
    }	
	
    public void setImageMaxWidth(Integer imageMaxWidth) {	
        this.imageMaxWidth = imageMaxWidth;	
    }	
	
    public void setUploadType(String uploadType) {	
        this.uploadType = uploadType;	
    }	
	
    public String getBizType() {	
        return this.bizType;	
    }	
	
    public String getBizKey() {	
        return this.bizKey;	
    }	
	
    public String getFileMd5() {	
        return this.fileMd5;	
    }	
}	
	
