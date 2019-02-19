package com.jeesite.modules.msg.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   name = "${_prefix}sys_msg_template",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "module_code",	
   attrName = "moduleCode",	
   label = "归属模块"	
), @Column(	
   name = "tpl_key",	
   attrName = "tplKey",	
   label = "模板键值"	
), @Column(	
   name = "tpl_name",	
   attrName = "tplName",	
   label = "模板名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "tpl_type",	
   attrName = "tplType",	
   label = "模板类型"	
), @Column(	
   name = "tpl_content",	
   attrName = "tplContent",	
   label = "模板内容"	
), @Column(	
   includeEntity = DataEntity.class	
)},	
   orderBy = "a.update_date DESC"	
)	
public class MsgTemplate extends DataEntity {	
   private String tplType;	
   private String tplKey;	
   private String tplName;	
   private String tplContent;	
   private String moduleCode;	
   private static final long serialVersionUID = 1L;	
	
   public void setTplKey(String tplKey) {	
      this.tplKey = tplKey;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "归属模块长度不能超过 64 个字符"	
   )	
   public String getModuleCode() {	
      return this.moduleCode;	
   }	
	
   public MsgTemplate(String id) {	
      super(id);	
   }	
	
   @NotBlank(	
      message = "模板名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "模板名称长度不能超过 100 个字符"	
   )	
   public String getTplName() {	
      return this.tplName;	
   }	
	
   public void setModuleCode(String moduleCode) {	
      this.moduleCode = moduleCode;	
   }	
	
   @NotBlank(	
      message = "模板类型不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 16,	
      message = "模板类型长度不能超过 16 个字符"	
   )	
   public String getTplType() {	
      return this.tplType;	
   }	
	
   public void setTplType(String tplType) {	
      this.tplType = tplType;	
   }	
	
   public void setTplContent(String tplContent) {	
      this.tplContent = tplContent;	
   }	
	
   public MsgTemplate() {	
      this((String)null);	
   }	
	
   @NotBlank(	
      message = "模板键值不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "模板键值长度不能超过 100 个字符"	
   )	
   public String getTplKey() {	
      return this.tplKey;	
   }	
	
   @NotBlank(	
      message = "模板内容不能为空"	
   )	
   public String getTplContent() {	
      return this.tplContent;	
   }	
	
   public void setTplName(String tplName) {	
      this.tplName = tplName;	
   }	
}	
