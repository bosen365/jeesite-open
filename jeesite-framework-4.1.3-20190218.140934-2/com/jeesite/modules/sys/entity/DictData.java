package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonView;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   name = "${_prefix}sys_dict_data",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = BaseEntity.class	
), @Column(	
   includeEntity = DataEntity.class	
), @Column(	
   includeEntity = TreeEntity.class	
), @Column(	
   name = "dict_code",	
   attrName = "dictCode",	
   label = "字典编码",	
   isPK = true	
), @Column(	
   name = "dict_label",	
   attrName = "dictLabelOrig",	
   label = "字典标签",	
   queryType = QueryType.LIKE,	
   isTreeName = true	
), @Column(	
   name = "dict_value",	
   attrName = "dictValue",	
   label = "字典键值",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "dict_type",	
   attrName = "dictType",	
   label = "字典类型"	
), @Column(	
   name = "is_sys",	
   attrName = "isSys",	
   label = "系统内置",	
   comment = "系统内置（1是 0否）"	
), @Column(	
   name = "description",	
   attrName = "description",	
   label = "字典描述"	
), @Column(	
   name = "css_style",	
   attrName = "cssStyle",	
   label = "css样式",	
   comment = "css样式（如：color:red)"	
), @Column(	
   name = "css_class",	
   attrName = "cssClass",	
   label = "css类名",	
   comment = "css类名（如：red）"	
), @Column(	
   includeEntity = Extend.class,	
   attrName = "extend"	
)},	
   orderBy = "a.tree_sorts, a.dict_code"	
)	
public class DictData extends TreeEntity {	
   private String isSys;	
   private String description;	
   private String cssStyle;	
   private Extend extend;	
   private String dictValue;	
   private String dictLabelOrig;	
   private static final long serialVersionUID = 1L;	
   private String cssClass;	
   private String dictCode;	
   private String dictType;	
	
   @NotBlank(	
      message = "系统内置不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 1,	
      message = "系统内置长度不能超过 1 个字符"	
   )	
   public String getIsSys() {	
      return this.isSys;	
   }	
	
   @JsonView({DictData.SimpleView.class})	
   @Length(	
      min = 0,	
      max = 500,	
      message = "css样式长度不能超过 500 个字符"	
   )	
   public String getCssStyle() {	
      return this.cssStyle;	
   }	
	
   public void setIsSys(String isSys) {	
      this.isSys = isSys;	
   }	
	
   public Extend getExtend() {	
      return this.extend;	
   }	
	
   public void setDictType(String dictType) {	
      this.dictType = dictType;	
   }	
	
   public void setDictValue(String dictValue) {	
      this.dictValue = dictValue;	
   }	
	
   public DictData getParent() {	
      return (DictData)this.parent;	
   }	
	
   public DictData(String id) {	
      super(id);	
   }	
	
   @JsonView({DictData.SimpleView.class})	
   public String getDictLabel() {	
      String var10000 = this.dictLabelOrig;	
      String[] var10001 = new String[0];	
      boolean var10003 = true;	
      return Global.getText(var10000, var10001);	
   }	
	
   public void setExtend(Extend extend) {	
      this.extend = extend;	
   }	
	
   public void setDescription(String description) {	
      this.description = description;	
   }	
	
   @NotBlank(	
      message = "字典键值不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "字典键值长度不能超过 100 个字符"	
   )	
   @JsonView({DictData.SimpleView.class})	
   public String getDictValue() {	
      return this.dictValue;	
   }	
	
   public void setDictLabelOrig(String dictLabel) {	
      this.dictLabelOrig = dictLabel;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "字典描述长度不能超过 500 个字符"	
   )	
   public String getDescription() {	
      return this.description;	
   }	
	
   public void setCssStyle(String cssStyle) {	
      this.cssStyle = cssStyle;	
   }	
	
   public DictData() {	
      this((String)null);	
   }	
	
   @NotBlank(	
      message = "字典类型不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "字典类型长度不能超过 100 个字符"	
   )	
   public String getDictType() {	
      return this.dictType;	
   }	
	
   public String getDictCode() {	
      return this.dictCode;	
   }	
	
   @JsonView({DictData.SimpleView.class})	
   @Length(	
      min = 0,	
      max = 500,	
      message = "css类名长度不能超过 500 个字符"	
   )	
   public String getCssClass() {	
      return this.cssClass;	
   }	
	
   @NotBlank(	
      message = "字典标签不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "字典标签长度不能超过 100 个字符"	
   )	
   public String getDictLabelOrig() {	
      return this.dictLabelOrig;	
   }	
	
   public void setDictCode(String dictCode) {	
      this.dictCode = dictCode;	
   }	
	
   public void setParent(DictData parent) {	
      this.parent = parent;	
   }	
	
   public void setCssClass(String cssClass) {	
      this.cssClass = cssClass;	
   }	
	
   public interface SimpleView {	
   }	
}	
