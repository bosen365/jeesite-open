package com.jeesite.modules.file.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.SysInfo;	
import org.hyperic.sigar.pager.PageList;	
	
@Table(	
   name = "${_prefix}sys_file_entity",	
   alias = "a",	
   columns = {@Column(	
   name = "file_id",	
   attrName = "fileId",	
   label = "文件编号",	
   isPK = true	
), @Column(	
   name = "file_md5",	
   attrName = "fileMd5",	
   label = "文件MD5"	
), @Column(	
   name = "file_path",	
   attrName = "filePath",	
   label = "文件相对路径"	
), @Column(	
   name = "file_content_type",	
   attrName = "fileContentType",	
   label = "文件内容类型"	
), @Column(	
   name = "file_extension",	
   attrName = "fileExtension",	
   label = "文件后缀扩展名"	
), @Column(	
   name = "file_size",	
   attrName = "fileSize",	
   label = "文件大小",	
   comment = "文件大小(单位B)"	
)},	
   orderBy = "a.file_id DESC"	
)	
public class FileEntity extends DataEntity {	
   private Long fileSize;	
   private String filePath;	
   private String fileContentType;	
   private String fileExtension;	
   private String fileMd5;	
   private String fileId;	
   private FileUploadParams fileUploadParams;	
   private static final long serialVersionUID = 1L;	
	
   @NotBlank(	
      message = "文件内容类型不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 200,	
      message = "文件内容类型长度不能超过 200 个字符"	
   )	
   public String getFileContentType() {	
      return this.fileContentType;	
   }	
	
   @JsonIgnore	
   public String getFileRealPath() {	
      return Global.getUserfilesBaseDir((new StringBuilder()).insert(0, "fileupload/").append(this.filePath).append("/").toString()) + this.fileId + "." + this.fileExtension;	
   }	
	
   @NotBlank(	
      message = "文件MD5不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "文件MD5长度不能超过 64 个字符"	
   )	
   public String getFileMd5() {	
      return this.fileMd5;	
   }	
	
   public void setFileExtension(String fileExtension) {	
      this.fileExtension = fileExtension;	
   }	
	
   public String getFileSizeFormat() {	
      return this.fileSize == null ? null : ByteUtils.formatByteSize(this.fileSize);	
   }	
	
   public FileEntity() {	
   }	
	
   @JsonIgnore	
   public FileUploadParams getFileUploadParams() {	
      return this.fileUploadParams;	
   }	
	
   @NotBlank(	
      message = "文件相对路径不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 1000,	
      message = "文件相对路径长度不能超过 1000 个字符"	
   )	
   public String getFilePath() {	
      return this.filePath;	
   }	
	
   public void setFileMd5(String fileMd5) {	
      this.fileMd5 = fileMd5;	
   }	
	
   @JsonIgnore	
   public String getFileUrl() {	
      return FileUtils.path((new StringBuilder()).insert(0, "/userfiles//fileupload/").append(this.filePath).append("/").toString()) + this.fileId + "." + this.fileExtension;	
   }	
	
   public void setFilePath(String filePath) {	
      this.filePath = filePath;	
   }	
	
   public void setFileSize(Long fileSize) {	
      this.fileSize = fileSize;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "文件编号不能超过 64 个字符"	
   )	
   public String getFileId() {	
      return this.fileId;	
   }	
	
   @NotNull(	
      message = "文件大小不能为空"	
   )	
   public Long getFileSize() {	
      return this.fileSize;	
   }	
	
   @NotBlank(	
      message = "文件后缀扩展名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "文件后缀扩展名长度不能超过 100 个字符"	
   )	
   public String getFileExtension() {	
      return this.fileExtension;	
   }	
	
   public FileEntity(String var1) {	
      this.fileId = var1;	
   }	
	
   public void setFileId(String fileId) {	
      this.fileId = fileId;	
   }	
	
   public void setFileUploadParams(FileUploadParams fileUploadParams) {	
      this.fileUploadParams = fileUploadParams;	
   }	
	
   public void setFileContentType(String fileContentType) {	
      this.fileContentType = fileContentType;	
   }	
}	
