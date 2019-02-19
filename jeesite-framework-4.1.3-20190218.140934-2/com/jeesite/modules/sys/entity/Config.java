package com.jeesite.modules.sys.entity;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   name = "${_prefix}sys_config",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "config_name",	
   attrName = "configName",	
   label = "参数名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "config_key",	
   attrName = "configKey",	
   label = "参数键名"	
), @Column(	
   name = "config_value",	
   attrName = "configValue",	
   label = "参数键值"	
), @Column(	
   name = "is_sys",	
   attrName = "isSys",	
   label = "系统内置"	
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
   orderBy = "a.config_key"	
)	
public class Config extends DataEntity {	
   private String configKey;	
   private static final long serialVersionUID = 1L;	
   private String configValue;	
   private String configName;	
   private String isSys;	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "系统内置（1是 0否）长度不能超过 1 个字符"	
   )	
   public String getIsSys() {	
      return this.isSys;	
   }	
	
   public void setIsSys(String isSys) {	
      this.isSys = isSys;	
   }	
	
   public void setConfigValue(String configValue) {	
      this.configValue = configValue;	
   }	
	
   @NotBlank(	
      message = "参数键长度不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "参数键长度不能超过 100 个字符"	
   )	
   public String getConfigKey() {	
      return this.configKey;	
   }	
	
   @Length(	
      min = 0,	
      max = 2000,	
      message = "参数值长度不能超过 2000 个字符"	
   )	
   public String getConfigValue() {	
      return this.configValue;	
   }	
	
   public String getConfigKey_like() {	
      return (String)this.sqlMap.getWhere().getValue("config_key", QueryType.LIKE);	
   }	
	
   public void setConfigKey(String configKey) {	
      this.configKey = configKey;	
   }	
	
   @NotBlank(	
      message = "名称长度不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "名称长度不能超过 100 个字符"	
   )	
   public String getConfigName() {	
      return this.configName;	
   }	
	
   public Config() {	
      this((String)null);	
   }	
	
   public void setConfigKey_like(String configKey) {	
      this.sqlMap.getWhere().and("config_key", QueryType.LIKE, configKey);	
   }	
	
   public Config(String id) {	
      super(id);	
   }	
	
   public void setConfigName(String configName) {	
      this.configName = configName;	
   }	
}	
