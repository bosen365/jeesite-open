/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.entity.Module;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
@Table(name="${_prefix}sys_lang", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="module_code", attrName="module.moduleCode", label="\u5f52\u5c5e\u6a21\u5757"), @Column(name="lang_code", attrName="langCode", label="\u8bed\u8a00\u7f16\u7801"), @Column(name="lang_text", attrName="langText", label="\u8bed\u8a00\u8bd1\u6587", queryType=QueryType.LIKE), @Column(name="lang_type", attrName="langType", label="\u8bed\u8a00\u7c7b\u578b"), @Column(name="create_by", attrName="createBy", label="\u521b\u5efa\u8005", isUpdate=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false), @Column(name="update_by", attrName="updateBy", label="\u66f4\u65b0\u8005", isUpdate=true), @Column(name="update_date", attrName="updateDate", label="\u66f4\u65b0\u65f6\u95f4", isUpdate=true, isQuery=false), @Column(name="remarks", attrName="remarks", label="\u5907\u6ce8\u4fe1\u606f", queryType=QueryType.LIKE)}, joinTable={@JoinTable(type=JoinTable.Type.LEFT_JOIN, entity=Module.class, alias="b", on="a.module_code = b.module_code", attrName="module", columns={@Column(name="module_code", attrName="moduleCode", label="\u6a21\u5757\u7f16\u7801", isPK=true), @Column(name="module_name", attrName="moduleName", label="\u6a21\u5757\u540d\u79f0", queryType=QueryType.LIKE)})}, orderBy="a.update_date DESC")	
public class Lang	
extends DataEntity<Lang> {	
    private static final long serialVersionUID = 1L;	
    private String langText;	
    private String langType;	
    private String langCode;	
    private Module module;	
	
    @NotBlank(message="\u8bed\u8a00\u8bd1\u6587\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=500, message="\u8bed\u8a00\u8bd1\u6587\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getLangText() {	
        return this.langText;	
    }	
	
    public void setModule(Module module) {	
        this.module = module;	
    }	
	
    public String getLangCode_like() {	
        return (String)this.sqlMap.getWhere().getValue("lang_code", QueryType.LIKE);	
    }	
	
    public void setLangType(String langType) {	
        this.langType = langType;	
    }	
	
    public Lang() {	
        this(null);	
    }	
	
    public Lang(String id) {	
        super(id);	
    }	
	
    public void setLangCode_like(String langCode) {	
        this.sqlMap.getWhere().and("lang_code", QueryType.LIKE, langCode);	
    }	
	
    @NotBlank(message="\u8bed\u8a00\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=50, message="\u8bed\u8a00\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 50 \u4e2a\u5b57\u7b26")	
    public String getLangType() {	
        return this.langType;	
    }	
	
    public void setLangCode(String langCode) {	
        this.langCode = langCode;	
    }	
	
    public void setLangText(String langText) {	
        this.langText = langText;	
    }	
	
    @NotNull(message="\u5f52\u5c5e\u6a21\u5757\u4e0d\u80fd\u4e3a\u7a7a")	
    public Module getModule() {	
        return this.module;	
    }	
	
    @NotBlank(message="\u8bed\u8a00\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=500, message="\u8bed\u8a00\u7f16\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getLangCode() {	
        return this.langCode;	
    }	
}	
	
