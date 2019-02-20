package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import com.jeesite.modules.sys.entity.User;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   name = "${_prefix}sys_file_upload",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "file_id",	
   attrName = "fileEntity.fileId",	
   label = "文件编号"	
), @Column(	
   name = "file_name",	
   attrName = "fileName",	
   label = "文件名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "file_type",	
   attrName = "fileType",	
   label = "文件分类",	
   comment = "文件分类（image、media、file）"	
), @Column(	
   name = "biz_key",	
   attrName = "bizKey",	
   label = "业务主键"	
), @Column(	
   name = "biz_type",	
   attrName = "bizType",	
   label = "业务类型"	
), @Column(	
   includeEntity = DataEntity.class	
)},	
   joinTable = {@JoinTable(	
   type = JoinTable.Type.LEFT_JOIN,	
   entity = FileEntity.class,	
   alias = "b",	
   on = "b.file_id = a.file_id"	
), @JoinTable(	
   type = JoinTable.Type.LEFT_JOIN,	
   entity = User.class,	
   attrName = "this",	
   alias = "u",	
   on = "u.user_code = a.create_by",	
   columns = {@Column(	
   name = "user_name",	
   attrName = "createByName",	
   label = "用户名称",	
   isQuery = false	
)}	
)},	
   orderBy = "a.create_date"	
)	
public class FileUpload extends DataEntity {	
   public static final String TYPE_FILE = "file";	
   private String fileName;	
   private String bizKey;	
   private static final long serialVersionUID = 1L;	
   private String fileType;	
   private String bizType;	
   private FileEntity fileEntity;	
   public static final String TYPE_IMAGE = "image";	
   public static final String TYPE_MEDIA = "media";	
	
   public String getFileUrl() {	
      return FileUploadUtils.getFileUploadService().getFileUploadServiceExtend().getFileUrl(this);	
   }	
	
   public void setBizType(String bizType) {	
      this.bizType = bizType;	
   }	
	
   public void setFileEntity(FileEntity fileEntity) {	
      this.fileEntity = fileEntity;	
   }	
	
   @NotBlank(	
      message = "文件名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 500,	
      message = "文件名称长度不能超过 500 个字符"	
   )	
   public String getFileName() {	
      return this.fileName;	
   }	
	
   @NotBlank(	
      message = "文件分类不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 20,	
      message = "文件分类长度不能超过 20 个字符"	
   )	
   public String getFileType() {	
      return this.fileType;	
   }	
	
   public FileUpload() {	
      super((String)null);	
   }	
	
   @NotNull(	
      message = "文件实体不能为空"	
   )	
   public FileEntity getFileEntity() {	
      if (this.fileEntity == null) {	
         this.fileEntity = new FileEntity();	
      }	
	
      return this.fileEntity;	
   }	
	
   public void setFileType(String fileType) {	
      this.fileType = fileType;	
   }	
	
   public FileUpload(FileEntity var1) {	
      super((String)null);	
      this.fileEntity = var1;	
   }	
	
   public void setBizKey(String bizKey) {	
      this.bizKey = bizKey;	
   }	
	
   public FileUpload(String fileUploadId) {	
      super(fileUploadId);	
   }	
	
   public void setFileName(String fileName) {	
      this.fileName = fileName;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "业务主键长度不能超过 64 个字符"	
   )	
   public String getBizKey() {	
      return this.bizKey;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "业务类型长度不能超过 64 个字符"	
   )	
   public String getBizType() {	
      return this.bizType;	
   }	
}	
