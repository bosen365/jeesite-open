/*	
 * Decompiled with CFR 0.141.	
 */	
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
	
@Table(name="${_prefix}sys_dict_data", alias="a", columns={@Column(includeEntity=BaseEntity.class), @Column(includeEntity=DataEntity.class), @Column(includeEntity=TreeEntity.class), @Column(name="dict_code", attrName="dictCode", label="\u5b57\u5178\u7f16\u7801", isPK=true), @Column(name="dict_label", attrName="dictLabelOrig", label="\u5b57\u5178\u6807\u7b7e", queryType=QueryType.LIKE, isTreeName=true), @Column(name="dict_value", attrName="dictValue", label="\u5b57\u5178\u952e\u503c", queryType=QueryType.LIKE), @Column(name="dict_type", attrName="dictType", label="\u5b57\u5178\u7c7b\u578b"), @Column(name="is_sys", attrName="isSys", label="\u7cfb\u7edf\u5185\u7f6e", comment="\u7cfb\u7edf\u5185\u7f6e\uff081\u662f 0\u5426\uff09"), @Column(name="description", attrName="description", label="\u5b57\u5178\u63cf\u8ff0"), @Column(name="css_style", attrName="cssStyle", label="css\u6837\u5f0f", comment="css\u6837\u5f0f\uff08\u5982\uff1acolor:red)"), @Column(name="css_class", attrName="cssClass", label="css\u7c7b\u540d", comment="css\u7c7b\u540d\uff08\u5982\uff1ared\uff09"), @Column(includeEntity=Extend.class, attrName="extend")}, orderBy="a.tree_sorts, a.dict_code")	
public class DictData	
extends TreeEntity<DictData> {	
    private String isSys;	
    private String cssStyle;	
    private Extend extend;	
    private String description;	
    private String dictType;	
    private String dictLabelOrig;	
    private String cssClass;	
    private String dictCode;	
    private static final long serialVersionUID = 1L;	
    private String dictValue;	
	
    public void setDictCode(String dictCode) {	
        this.dictCode = dictCode;	
    }	
	
    @NotBlank(message="\u7cfb\u7edf\u5185\u7f6e\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=1, message="\u7cfb\u7edf\u5185\u7f6e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsSys() {	
        return this.isSys;	
    }	
	
    public void setCssStyle(String cssStyle) {	
        this.cssStyle = cssStyle;	
    }	
	
    @JsonView(value={SimpleView.class})	
    public String getDictLabel() {	
        return Global.getText(this.dictLabelOrig, new String[0]);	
    }	
	
    @JsonView(value={SimpleView.class})	
    @Length(min=0, max=500, message="css\u6837\u5f0f\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getCssStyle() {	
        return this.cssStyle;	
    }	
	
    public DictData(String id) {	
        super(id);	
    }	
	
    @Length(min=0, max=500, message="\u5b57\u5178\u63cf\u8ff0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getDescription() {	
        return this.description;	
    }	
	
    public void setCssClass(String cssClass) {	
        this.cssClass = cssClass;	
    }	
	
    public String getDictCode() {	
        return this.dictCode;	
    }	
	
    public void setDictLabelOrig(String dictLabel) {	
        this.dictLabelOrig = dictLabel;	
    }	
	
    @Override	
    public void setParent(DictData parent) {	
        this.parent = parent;	
    }	
	
    public void setIsSys(String isSys) {	
        this.isSys = isSys;	
    }	
	
    @NotBlank(message="\u5b57\u5178\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u5b57\u5178\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getDictType() {	
        return this.dictType;	
    }	
	
    @NotBlank(message="\u5b57\u5178\u6807\u7b7e\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u5b57\u5178\u6807\u7b7e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getDictLabelOrig() {	
        return this.dictLabelOrig;	
    }	
	
    public void setDictType(String dictType) {	
        this.dictType = dictType;	
    }	
	
    @NotBlank(message="\u5b57\u5178\u952e\u503c\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u5b57\u5178\u952e\u503c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    @JsonView(value={SimpleView.class})	
    public String getDictValue() {	
        return this.dictValue;	
    }	
	
    public void setDictValue(String dictValue) {	
        this.dictValue = dictValue;	
    }	
	
    public void setExtend(Extend extend) {	
        this.extend = extend;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    @JsonView(value={SimpleView.class})	
    @Length(min=0, max=500, message="css\u7c7b\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getCssClass() {	
        return this.cssClass;	
    }	
	
    @Override	
    public DictData getParent() {	
        return (DictData)this.parent;	
    }	
	
    public Extend getExtend() {	
        return this.extend;	
    }	
	
    public DictData() {	
        this(null);	
    }	
	
    public static interface SimpleView {	
    }	
	
}	
	
