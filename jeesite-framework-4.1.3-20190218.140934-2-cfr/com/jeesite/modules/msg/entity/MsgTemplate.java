/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.msg.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}sys_msg_template", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="module_code", attrName="moduleCode", label="\u5f52\u5c5e\u6a21\u5757"), @Column(name="tpl_key", attrName="tplKey", label="\u6a21\u677f\u952e\u503c"), @Column(name="tpl_name", attrName="tplName", label="\u6a21\u677f\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="tpl_type", attrName="tplType", label="\u6a21\u677f\u7c7b\u578b"), @Column(name="tpl_content", attrName="tplContent", label="\u6a21\u677f\u5185\u5bb9"), @Column(includeEntity=DataEntity.class)}, orderBy="a.update_date DESC")	
public class MsgTemplate	
extends DataEntity<MsgTemplate> {	
    private String tplType;	
    private String tplKey;	
    private String tplName;	
    private String tplContent;	
    private String moduleCode;	
    private static final long serialVersionUID = 1L;	
	
    public void setTplKey(String tplKey) {	
        this.tplKey = tplKey;	
    }	
	
    @Length(min=0, max=64, message="\u5f52\u5c5e\u6a21\u5757\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getModuleCode() {	
        return this.moduleCode;	
    }	
	
    public MsgTemplate(String id) {	
        super(id);	
    }	
	
    @NotBlank(message="\u6a21\u677f\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u6a21\u677f\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getTplName() {	
        return this.tplName;	
    }	
	
    public void setModuleCode(String moduleCode) {	
        this.moduleCode = moduleCode;	
    }	
	
    @NotBlank(message="\u6a21\u677f\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=16, message="\u6a21\u677f\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 16 \u4e2a\u5b57\u7b26")	
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
        this(null);	
    }	
	
    @NotBlank(message="\u6a21\u677f\u952e\u503c\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u6a21\u677f\u952e\u503c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getTplKey() {	
        return this.tplKey;	
    }	
	
    @NotBlank(message="\u6a21\u677f\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getTplContent() {	
        return this.tplContent;	
    }	
	
    public void setTplName(String tplName) {	
        this.tplName = tplName;	
    }	
}	
	
