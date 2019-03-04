package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hibernate.validator.constraints.NotBlank;	
	
@Table(	
   name = "${_prefix}sys_lang",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "module_code",	
   attrName = "module.moduleCode",	
   label = "归属模块"	
), @Column(	
   name = "lang_code",	
   attrName = "langCode",	
   label = "语言编码"	
), @Column(	
   name = "lang_text",	
   attrName = "langText",	
   label = "语言译文",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "lang_type",	
   attrName = "langType",	
   label = "语言类型"	
), @Column(	
   name = "create_by",	
   attrName = "createBy",	
   label = "创建者",	
   isUpdate = false	
), @Column(	
   name = "create_date",	
   attrName = "createDate",	
   label = "创建时间",	
   isUpdate = false,	
   isQuery = false	
), @Column(	
   name = "update_by",	
   attrName = "updateBy",	
   label = "更新者",	
   isUpdate = true	
), @Column(	
   name = "update_date",	
   attrName = "updateDate",	
   label = "更新时间",	
   isUpdate = true,	
   isQuery = false	
), @Column(	
   name = "remarks",	
   attrName = "remarks",	
   label = "备注信息",	
   queryType = QueryType.LIKE	
)},	
   joinTable = {@JoinTable(	
   type = JoinTable.Type.LEFT_JOIN,	
   entity = Module.class,	
   alias = "b",	
   on = "a.module_code = b.module_code",	
   attrName = "module",	
   columns = {@Column(	
   name = "module_code",	
   attrName = "moduleCode",	
   label = "模块编码",	
   isPK = true	
), @Column(	
   name = "module_name",	
   attrName = "moduleName",	
   label = "模块名称",	
   queryType = QueryType.LIKE	
)}	
)},	
   orderBy = "a.update_date DESC"	
)	
public class Lang extends DataEntity {	
   private Module module;	
   private static final long serialVersionUID = 1L;	
   private String langCode;	
   private String langType;	
   private String langText;	
	
   public void setLangCode(String langCode) {	
      this.langCode = langCode;	
   }	
	
   public void setLangType(String langType) {	
      this.langType = langType;	
   }	
	
   @NotBlank(	
      message = "语言译文不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 500,	
      message = "语言译文长度不能超过 500 个字符"	
   )	
   public String getLangText() {	
      return this.langText;	
   }	
	
   public Lang() {	
      this((String)null);	
   }	
	
   public void setModule(Module module) {	
      this.module = module;	
   }	
	
   @NotNull(	
      message = "归属模块不能为空"	
   )	
   public Module getModule() {	
      return this.module;	
   }	
	
   public void setLangText(String langText) {	
      this.langText = langText;	
   }	
	
   @NotBlank(	
      message = "语言类型不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 50,	
      message = "语言类型长度不能超过 50 个字符"	
   )	
   public String getLangType() {	
      return this.langType;	
   }	
	
   public void setLangCode_like(String langCode) {	
      this.sqlMap.getWhere().and("lang_code", QueryType.LIKE, langCode);	
   }	
	
   public String getLangCode_like() {	
      return (String)this.sqlMap.getWhere().getValue("lang_code", QueryType.LIKE);	
   }	
	
   public Lang(String id) {	
      super(id);	
   }	
	
   @NotBlank(	
      message = "语言编码不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 500,	
      message = "语言编码长度不能超过 500 个字符"	
   )	
   public String getLangCode() {	
      return this.langCode;	
   }	
}	
