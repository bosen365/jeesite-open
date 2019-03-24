/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import java.io.Serializable;	
import java.util.Map;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
public class FileUploadParams	
implements Serializable {	
    private Long chunks;	
    private String mediaAllowSuffixes;	
    private String bizType;	
    private Map<String, String> extend;	
    public static final String FILEUPLOAD_BASE_URL = "fileupload";	
    private String relativePath;	
    private String fileName;	
    private Long maxFileSize;	
    private String fileMd5;	
    private String imageAllowSuffixes;	
    private String fileAllowSuffixes;	
    private Long chunkSize;	
    private String allowSuffixes;	
    private String bizKey;	
    private String fileExtension;	
    private Long chunk;	
    private String uploadType;	
    private Long size;	
    private String uploadPath;	
    private static final long serialVersionUID = 1L;	
    private Integer imageMaxHeight;	
    private Integer imageMaxWidth;	
	
    public void setUploadType(String uploadType) {	
        this.uploadType = uploadType;	
    }	
	
    public Long getChunks() {	
        return this.chunks;	
    }	
	
    public String getFileName() {	
        return this.fileName;	
    }	
	
    public String getRelativePath() {	
        return this.relativePath;	
    }	
	
    public Long getChunk() {	
        return this.chunk;	
    }	
	
    public String getBizKey() {	
        return this.bizKey;	
    }	
	
    public Integer getImageMaxWidth() {	
        return this.imageMaxWidth;	
    }	
	
    public String getFileAllowSuffixes() {	
        return this.fileAllowSuffixes;	
    }	
	
    public void setChunks(Long chunks) {	
        this.chunks = chunks;	
    }	
	
    public void setMediaAllowSuffixes(String mediaAllowSuffixes) {	
        this.mediaAllowSuffixes = mediaAllowSuffixes;	
    }	
	
    public void setFileMd5(String fileMd5) {	
        this.fileMd5 = fileMd5;	
    }	
	
    public void setFileExtension(String fileExtension) {	
        this.fileExtension = fileExtension;	
    }	
	
    public String getBizType() {	
        return this.bizType;	
    }	
	
    public void setChunkSize(Long chunkSize) {	
        this.chunkSize = chunkSize;	
    }	
	
    public void setBizKey(String bizKey) {	
        this.bizKey = bizKey;	
    }	
	
    public FileUploadParams() {	
        FileUploadParams fileUploadParams = this;	
        this.uploadType = "file";	
        fileUploadParams.imageMaxWidth = -1;	
        fileUploadParams.imageMaxHeight = -1;	
        fileUploadParams.extend = MapUtils.newHashMap();	
    }	
	
    public Long getSize() {	
        return this.size;	
    }	
	
    public void setBizType(String bizType) {	
        this.bizType = bizType;	
    }	
	
    public void setUploadPath(String uploadPath) {	
        this.uploadPath = uploadPath;	
    }	
	
    public Long getChunkSize() {	
        return this.chunkSize;	
    }	
	
    public void setChunk(Long chunk) {	
        this.chunk = chunk;	
    }	
	
    public String getUploadPath() {	
        return this.uploadPath;	
    }	
	
    public String getUploadType() {	
        return this.uploadType;	
    }	
	
    public String getFileMd5() {	
        return this.fileMd5;	
    }	
	
    public Integer getImageMaxHeight() {	
        return this.imageMaxHeight;	
    }	
	
    public void setFileName(String fileName) {	
        this.fileName = fileName;	
    }	
	
    public Long getMaxFileSize() {	
        return this.maxFileSize;	
    }	
	
    public void setAllowSuffixes(String allowSuffixes) {	
        this.allowSuffixes = allowSuffixes;	
    }	
	
    public void setImageMaxWidth(Integer imageMaxWidth) {	
        this.imageMaxWidth = imageMaxWidth;	
    }	
	
    public String getAllowSuffixes() {	
        return this.allowSuffixes;	
    }	
	
    public void setFileAllowSuffixes(String fileAllowSuffixes) {	
        this.fileAllowSuffixes = fileAllowSuffixes;	
    }	
	
    public Map<String, String> getExtend() {	
        return this.extend;	
    }	
	
    public void setRelativePath(String relativePath) {	
        this.relativePath = relativePath;	
    }	
	
    public String getMediaAllowSuffixes() {	
        return this.mediaAllowSuffixes;	
    }	
	
    public String getImageAllowSuffixes() {	
        return this.imageAllowSuffixes;	
    }	
	
    public void setImageAllowSuffixes(String imageAllowSuffixes) {	
        this.imageAllowSuffixes = imageAllowSuffixes;	
    }	
	
    public void setMaxFileSize(Long maxFileSize) {	
        this.maxFileSize = maxFileSize;	
    }	
	
    public void setImageMaxHeight(Integer imageMaxHeight) {	
        this.imageMaxHeight = imageMaxHeight;	
    }	
	
    public FileUploadParams initialize() {	
        this.uploadPath = Global.getProperty("file.uploadPath", "{yyyy}{MM}/");	
        String[] a = StringUtils.substringsBetween(this.uploadPath, "{", "}");	
        if (a != null) {	
            int n;	
            String[] arrstring = a;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                String[] arrstring2 = new String[7];	
                arrstring2[0] = "yyyy";	
                arrstring2[1] = "MM";	
                arrstring2[2] = "dd";	
                arrstring2[3] = "HH";	
                arrstring2[4] = "mm";	
                arrstring2[5] = "ss";	
                arrstring2[6] = "E";	
                if (StringUtils.inString(a2, arrstring2)) {	
                    this.uploadPath = StringUtils.replace(this.uploadPath, new StringBuilder().insert(0, "{").append(a2).append("}").toString(), DateUtils.getDate(a2));	
                }	
                n3 = ++n;	
            }	
        }	
        this.relativePath = FileUtils.path(this.uploadPath);	
        String a3 = Global.getProperty("file.maxFileSize");	
        if (StringUtils.isNotBlank(a3)) {	
            int n;	
            String[] a4 = StringUtils.split(a3, "*");	
            this.maxFileSize = 1L;	
            String[] arrstring = a4;	
            int a2 = a4.length;	
            int n4 = n = 0;	
            while (n4 < a2) {	
                String a5 = arrstring[n];	
                this.maxFileSize = this.maxFileSize * Long.valueOf(a5);	
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
            fileUploadParams.fileExtension = FileUtils.getFileExtension(fileUploadParams.fileName);	
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
        if (StringUtils.contains((CharSequence)this.imageAllowSuffixes, new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "image";	
        }	
        if (StringUtils.contains((CharSequence)this.mediaAllowSuffixes, new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "media";	
        }	
        if (StringUtils.contains((CharSequence)this.fileAllowSuffixes, new StringBuilder().insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "file";	
        }	
        this.allowSuffixes = new StringBuilder().insert(0, this.imageAllowSuffixes).append(",").append(this.mediaAllowSuffixes).append(",").append(this.fileAllowSuffixes).append(",").toString();	
        return this;	
    }	
	
    public String getFileExtension() {	
        return this.fileExtension;	
    }	
	
    public void setSize(Long size) {	
        this.size = size;	
    }	
	
    public void setExtend(Map<String, String> extend) {	
        this.extend = extend;	
    }	
}	
	
