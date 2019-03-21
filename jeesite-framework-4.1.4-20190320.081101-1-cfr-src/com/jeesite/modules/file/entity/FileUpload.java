/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.constraints.NotBlank	
 *  javax.validation.constraints.NotNull	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import com.jeesite.modules.sys.entity.User;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}sys_file_upload", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="file_id", attrName="fileEntity.fileId", label="\u6587\u4ef6\u7f16\u53f7"), @Column(name="file_name", attrName="fileName", label="\u6587\u4ef6\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="file_type", attrName="fileType", label="\u6587\u4ef6\u5206\u7c7b", comment="\u6587\u4ef6\u5206\u7c7b\uff08image\u3001media\u3001file\uff09"), @Column(name="biz_key", attrName="bizKey", label="\u4e1a\u52a1\u4e3b\u952e"), @Column(name="biz_type", attrName="bizType", label="\u4e1a\u52a1\u7c7b\u578b"), @Column(includeEntity=DataEntity.class)}, joinTable={@JoinTable(type=JoinTable.Type.LEFT_JOIN, entity=FileEntity.class, alias="b", on="b.file_id = a.file_id"), @JoinTable(type=JoinTable.Type.LEFT_JOIN, entity=User.class, attrName="this", alias="u", on="u.user_code = a.create_by", columns={@Column(name="user_name", attrName="createByName", label="\u7528\u6237\u540d\u79f0", isQuery=false)})}, orderBy="a.id")	
public class FileUpload	
extends DataEntity<FileUpload> {	
    public static final String TYPE_FILE = "file";	
    private FileEntity fileEntity;	
    private static final long serialVersionUID = 1L;	
    private String fileName;	
    private String bizType;	
    public static final String TYPE_MEDIA = "media";	
    private String fileType;	
    public static final String TYPE_IMAGE = "image";	
    private String bizKey;	
	
    public FileUpload() {	
        super(null);	
    }	
	
    public void setFileEntity(FileEntity fileEntity) {	
        this.fileEntity = fileEntity;	
    }	
	
    @NotBlank(message="\u6587\u4ef6\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=500, message="\u6587\u4ef6\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getFileName() {	
        return this.fileName;	
    }	
	
    public void setFileType(String fileType) {	
        this.fileType = fileType;	
    }	
	
    @NotNull(message="\u6587\u4ef6\u5b9e\u4f53\u4e0d\u80fd\u4e3a\u7a7a")	
    public FileEntity getFileEntity() {	
        if (this.fileEntity == null) {	
            FileUpload fileUpload = this;	
            fileUpload.fileEntity = new FileEntity();	
        }	
        return this.fileEntity;	
    }	
	
    public String getFileUrl() {	
        return FileUploadUtils.getFileUploadService().getFileUploadServiceExtend().getFileUrl(this);	
    }	
	
    public void setBizType(String bizType) {	
        this.bizType = bizType;	
    }	
	
    public FileUpload(FileEntity fileEntity) {	
        super(null);	
        this.fileEntity = fileEntity;	
    }	
	
    @Length(min=0, max=64, message="\u4e1a\u52a1\u4e3b\u952e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getBizKey() {	
        return this.bizKey;	
    }	
	
    public FileUpload(String fileUploadId) {	
        super(fileUploadId);	
    }	
	
    @Length(min=0, max=64, message="\u4e1a\u52a1\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getBizType() {	
        return this.bizType;	
    }	
	
    @NotBlank(message="\u6587\u4ef6\u5206\u7c7b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=20, message="\u6587\u4ef6\u5206\u7c7b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 20 \u4e2a\u5b57\u7b26")	
    public String getFileType() {	
        return this.fileType;	
    }	
	
    public void setFileName(String fileName) {	
        this.fileName = fileName;	
    }	
	
    public void setBizKey(String bizKey) {	
        this.bizKey = bizKey;	
    }	
}	
	
