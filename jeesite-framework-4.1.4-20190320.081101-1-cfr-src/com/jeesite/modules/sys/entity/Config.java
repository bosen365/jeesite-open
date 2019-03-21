/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.FileWatcher;	
	
@Table(name="${_prefix}sys_config", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="config_name", attrName="configName", label="\u53c2\u6570\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="config_key", attrName="configKey", label="\u53c2\u6570\u952e\u540d"), @Column(name="config_value", attrName="configValue", label="\u53c2\u6570\u952e\u503c"), @Column(name="is_sys", attrName="isSys", label="\u7cfb\u7edf\u5185\u7f6e"), @Column(name="create_by", attrName="createBy", label="\u521b\u5efa\u8005", isUpdate=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false), @Column(name="update_by", attrName="updateBy", label="\u66f4\u65b0\u8005", isUpdate=true), @Column(name="update_date", attrName="updateDate", label="\u66f4\u65b0\u65f6\u95f4", isUpdate=true, isQuery=false), @Column(name="remarks", attrName="remarks", label="\u5907\u6ce8\u4fe1\u606f", queryType=QueryType.LIKE)}, orderBy="a.config_key")	
public class Config	
extends DataEntity<Config> {	
    private String configKey;	
    private String configName;	
    private String isSys;	
    private static final long serialVersionUID = 1L;	
    private String configValue;	
	
    @Length(min=0, max=2000, message="\u53c2\u6570\u503c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 2000 \u4e2a\u5b57\u7b26")	
    public String getConfigValue() {	
        return this.configValue;	
    }	
	
    @Length(min=0, max=1, message="\u7cfb\u7edf\u5185\u7f6e\uff081\u662f 0\u5426\uff09\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsSys() {	
        return this.isSys;	
    }	
	
    public Config(String id) {	
        super(id);	
    }	
	
    public String getConfigKey_like() {	
        return (String)this.sqlMap.getWhere().getValue("config_key", QueryType.LIKE);	
    }	
	
    @NotBlank(message="\u53c2\u6570\u952e\u957f\u5ea6\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u53c2\u6570\u952e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getConfigKey() {	
        return this.configKey;	
    }	
	
    public void setConfigKey(String configKey) {	
        this.configKey = configKey;	
    }	
	
    public void setConfigKey_like(String configKey) {	
        this.sqlMap.getWhere().and("config_key", QueryType.LIKE, configKey);	
    }	
	
    @NotBlank(message="\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getConfigName() {	
        return this.configName;	
    }	
	
    public void setConfigName(String configName) {	
        this.configName = configName;	
    }	
	
    public void setIsSys(String isSys) {	
        this.isSys = isSys;	
    }	
	
    public void setConfigValue(String configValue) {	
        this.configValue = configValue;	
    }	
	
    public Config() {	
        this(null);	
    }	
}	
	
