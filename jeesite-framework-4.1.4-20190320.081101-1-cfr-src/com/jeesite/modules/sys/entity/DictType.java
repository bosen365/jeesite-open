/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.Pattern;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.NetInterfaceStat;	
import org.hyperic.sigar.SysInfo;	
	
@Table(name="${_prefix}sys_dict_type", alias="a", columns={@Column(includeEntity=DataEntity.class), @Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="dict_name", attrName="dictName", label="\u5b57\u5178\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="dict_type", attrName="dictType", label="\u5b57\u5178\u7c7b\u578b"), @Column(name="is_sys", attrName="isSys", label="\u662f\u5426\u7cfb\u7edf\u5b57\u5178")}, orderBy="a.dict_type, a.update_date DESC")	
public class DictType	
extends DataEntity<DictType> {	
    private String dictName;	
    private String dictType;	
    private String isSys;	
    private static final long serialVersionUID = 1L;	
	
    @NotBlank(message="\u5b57\u5178\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Pattern(regexp="[a-zA-Z0-9_]{3,64}", message="\u5b57\u5178\u5206\u7c7b\u957f\u5ea6\u5e94\u4e3a 3 \u5230 64 \u4e2a\u5b57\u7b26\uff0c\u5e76\u4e14\u53ea\u80fd\u5305\u542b\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf")	
    public String getDictType() {	
        return this.dictType;	
    }	
	
    public void setIsSys(String isSys) {	
        this.isSys = isSys;	
    }	
	
    public DictType(String id) {	
        super(id);	
    }	
	
    public void setDictName(String dictName) {	
        this.dictName = dictName;	
    }	
	
    public void setDictType(String dictType) {	
        this.dictType = dictType;	
    }	
	
    public void setDictType_like(String dictType) {	
        this.sqlMap.getWhere().and("dict_type", QueryType.LIKE, dictType);	
    }	
	
    @NotBlank(message="\u5b57\u5178\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u5b57\u5178\u7c7b\u578b\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getDictName() {	
        return this.dictName;	
    }	
	
    @NotBlank(message="\u662f\u5426\u7cfb\u7edf\u5b57\u5178\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=1, message="\u662f\u5426\u7cfb\u7edf\u5b57\u5178\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsSys() {	
        return this.isSys;	
    }	
	
    public DictType() {	
        this(null);	
    }	
	
    public String getDictType_like() {	
        return (String)this.sqlMap.getWhere().getValue("dict_type", QueryType.LIKE);	
    }	
}	
	
