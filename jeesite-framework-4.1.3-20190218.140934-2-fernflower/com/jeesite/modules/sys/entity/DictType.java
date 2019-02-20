package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.Pattern;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcState;	
	
@Table(	
   name = "${_prefix}sys_dict_type",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = DataEntity.class	
), @Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "dict_name",	
   attrName = "dictName",	
   label = "字典名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "dict_type",	
   attrName = "dictType",	
   label = "字典类型"	
), @Column(	
   name = "is_sys",	
   attrName = "isSys",	
   label = "是否系统字典"	
)},	
   orderBy = "a.dict_type, a.update_date DESC"	
)	
public class DictType extends DataEntity {	
   private String dictType;	
   private String dictName;	
   private String isSys;	
   private static final long serialVersionUID = 1L;	
	
   public String getDictType_like() {	
      return (String)this.sqlMap.getWhere().getValue("dict_type", QueryType.LIKE);	
   }	
	
   public void setIsSys(String isSys) {	
      this.isSys = isSys;	
   }	
	
   public void setDictType_like(String dictType) {	
      this.sqlMap.getWhere().and("dict_type", QueryType.LIKE, dictType);	
   }	
	
   @NotBlank(	
      message = "字典类型不能为空"	
   )	
   @Pattern(	
      regexp = "[a-zA-Z0-9_]{3,64}",	
      message = "字典分类长度应为 3 到 64 个字符，并且只能包含字母、数字、下划线"	
   )	
   public String getDictType() {	
      return this.dictType;	
   }	
	
   public DictType() {	
      this((String)null);	
   }	
	
   @NotBlank(	
      message = "是否系统字典不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 1,	
      message = "是否系统字典长度不能超过 1 个字符"	
   )	
   public String getIsSys() {	
      return this.isSys;	
   }	
	
   public void setDictType(String dictType) {	
      this.dictType = dictType;	
   }	
	
   public void setDictName(String dictName) {	
      this.dictName = dictName;	
   }	
	
   @NotBlank(	
      message = "字典名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "字典类型名称长度不能超过 100 个字符"	
   )	
   public String getDictName() {	
      return this.dictName;	
   }	
	
   public DictType(String id) {	
      super(id);	
   }	
}	
