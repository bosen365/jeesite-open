/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.file.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.DirUsage;	
import org.hyperic.sigar.cmd.Watch;	
	
@Table(name="${_prefix}sys_file_entity", alias="a", columns={@Column(name="file_id", attrName="fileId", label="\u6587\u4ef6\u7f16\u53f7", isPK=true), @Column(name="file_md5", attrName="fileMd5", label="\u6587\u4ef6MD5"), @Column(name="file_path", attrName="filePath", label="\u6587\u4ef6\u76f8\u5bf9\u8def\u5f84"), @Column(name="file_content_type", attrName="fileContentType", label="\u6587\u4ef6\u5185\u5bb9\u7c7b\u578b"), @Column(name="file_extension", attrName="fileExtension", label="\u6587\u4ef6\u540e\u7f00\u6269\u5c55\u540d"), @Column(name="file_size", attrName="fileSize", label="\u6587\u4ef6\u5927\u5c0f", comment="\u6587\u4ef6\u5927\u5c0f(\u5355\u4f4dB)", isQuery=false), @Column(name="file_meta", attrName="fileMeta", label="\u6587\u4ef6\u4fe1\u606f(JSON\u683c\u5f0f)")}, orderBy="a.file_id DESC")	
public class FileEntity	
extends DataEntity<FileEntity> {	
    private FileUploadParams fileUploadParams;	
    private String fileContentType;	
    private String fileExtension;	
    private String fileMd5;	
    private static final long serialVersionUID = 1L;	
    private String fileId;	
    private Long fileSize;	
    private String fileMeta;	
    private String filePath;	
    private Map<String, Object> fileMetaMap;	
	
    public String getFileMeta() {	
        if (this.fileMetaMap != null) {	
            this.fileMeta = JsonMapper.toJson(this.fileMetaMap);	
        }	
        return this.fileMeta;	
    }	
	
    @JsonIgnore	
    public String getFileUrl() {	
        return FileUtils.path(new StringBuilder().insert(0, "/userfiles//fileupload/").append(this.filePath).append("/").toString()) + this.fileId + "." + this.fileExtension;	
    }	
	
    public void setFileSize(Long fileSize) {	
        this.fileSize = fileSize;	
    }	
	
    @NotNull(message="\u6587\u4ef6\u5927\u5c0f\u4e0d\u80fd\u4e3a\u7a7a")	
    public Long getFileSize() {	
        if (this.fileSize == null) {	
            this.fileSize = 0L;	
        }	
        return this.fileSize;	
    }	
	
    @Length(min=0, max=64, message="\u6587\u4ef6\u7f16\u53f7\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getFileId() {	
        return this.fileId;	
    }	
	
    @JsonIgnore	
    public FileUploadParams getFileUploadParams() {	
        return this.fileUploadParams;	
    }	
	
    @NotBlank(message="\u6587\u4ef6\u76f8\u5bf9\u8def\u5f84\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=1000, message="\u6587\u4ef6\u76f8\u5bf9\u8def\u5f84\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1000 \u4e2a\u5b57\u7b26")	
    public String getFilePath() {	
        return this.filePath;	
    }	
	
    public Map<String, Object> getFileMetaMap() {	
        if (this.fileMetaMap == null) {	
            this.fileMetaMap = MapUtils.newHashMap();	
        }	
        return this.fileMetaMap;	
    }	
	
    @NotBlank(message="\u6587\u4ef6\u540e\u7f00\u6269\u5c55\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u6587\u4ef6\u540e\u7f00\u6269\u5c55\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getFileExtension() {	
        return this.fileExtension;	
    }	
	
    @NotBlank(message="\u6587\u4ef6MD5\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u6587\u4ef6MD5\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getFileMd5() {	
        return this.fileMd5;	
    }	
	
    @JsonIgnore	
    public String getFileRealPath() {	
        return Global.getUserfilesBaseDir(new StringBuilder().insert(0, "fileupload/").append(this.filePath).append("/").toString()) + this.fileId + "." + this.fileExtension;	
    }	
	
    public void setFileId(String fileId) {	
        this.fileId = fileId;	
    }	
	
    public void setFilePath(String filePath) {	
        this.filePath = filePath;	
    }	
	
    public void setFileExtension(String fileExtension) {	
        this.fileExtension = fileExtension;	
    }	
	
    public void setFileMetaMap(Map<String, Object> fileMetaMap) {	
        this.fileMetaMap = fileMetaMap;	
    }	
	
    public void setFileMeta(String fileMeta) {	
        if (StringUtils.isNotBlank(fileMeta)) {	
            this.fileMetaMap = (Map)JsonMapper.fromJson(fileMeta, Map.class);	
        }	
        this.fileMeta = fileMeta;	
    }	
	
    @NotBlank(message="\u6587\u4ef6\u5185\u5bb9\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=200, message="\u6587\u4ef6\u5185\u5bb9\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getFileContentType() {	
        return this.fileContentType;	
    }	
	
    public FileEntity(String fileId) {	
        this.fileId = fileId;	
    }	
	
    public void setFileUploadParams(FileUploadParams fileUploadParams) {	
        this.fileUploadParams = fileUploadParams;	
    }	
	
    public void setFileMd5(String fileMd5) {	
        this.fileMd5 = fileMd5;	
    }	
	
    public String getFileSizeFormat() {	
        if (this.fileSize == null) {	
            return null;	
        }	
        return ByteUtils.formatByteSize(this.fileSize);	
    }	
	
    public FileEntity() {	
    }	
	
    public void setFileContentType(String fileContentType) {	
        this.fileContentType = fileContentType;	
    }	
}	
	
