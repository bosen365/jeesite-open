/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonBackReference;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.l.k;	
import java.util.List;	
import javax.validation.constraints.NotNull;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ThreadCpu;	
	
@Table(columns={@Column(name="parent_code", attrName="parentCode", label="\u7236\u7ea7\u7f16\u7801"), @Column(name="parent_codes", attrName="parentCodes", label="\u6240\u6709\u7236\u7ea7\u7f16\u7801", queryType=QueryType.LIKE), @Column(name="tree_sort", attrName="treeSort", label="\u672c\u7ea7\u6392\u5e8f\u53f7", isQuery=false), @Column(name="tree_sorts", attrName="treeSorts", label="\u6240\u6709\u7236\u7ea7\u6392\u5e8f\u53f7", isQuery=false), @Column(name="tree_leaf", attrName="treeLeaf", label="\u662f\u5426\u6700\u672b\u7ea7"), @Column(name="tree_level", attrName="treeLevel", label="\u5c42\u6b21\u7ea7\u522b"), @Column(name="tree_names", attrName="treeNames", label="\u5168\u8282\u70b9\u540d", queryType=QueryType.LIKE)})	
public abstract class TreeEntity<T extends TreeEntity<?>>	
extends DataEntity<T> {	
    protected String treeName_;	
    public static final int DEFAULT_TREE_SORT = 30;	
    public static final int TREE_SORTS_LENGTH = 10;	
    protected String parentCodes;	
    protected T parent;	
    protected Integer treeLevel;	
    public static final String ROOT_CODE = "0";	
    protected String treeNames;	
    protected List<T> childList;	
    private static final long serialVersionUID = 1L;	
    protected String treeSorts;	
    protected Boolean isQueryChildren;	
    protected Integer treeSort;	
    protected String treeLeaf;	
	
    public boolean getIsRoot() {	
        return ROOT_CODE.equals(this.getParentCode());	
    }	
	
    public List<T> getChildList() {	
        return this.childList;	
    }	
	
    public TreeEntity() {	
        this(null);	
    }	
	
    public Integer getTreeLevel() {	
        if (this.treeLeaf != null && this.treeLevel == null) {	
            this.treeLevel = this.parentCodes != null ? Integer.valueOf(this.parentCodes.replaceAll("[^,]", "").length() - 1) : null;	
        }	
        return this.treeLevel;	
    }	
	
    public Boolean getIsQueryChildren() {	
        return this.isQueryChildren;	
    }	
	
    public String getParentCode() {	
        String a = null;	
        if (this.parent != null) {	
            a = ((BaseEntity)this.parent).getId();	
        }	
        return a;	
    }	
	
    public void setTreeSorts(String treeSorts) {	
        this.treeSorts = treeSorts;	
    }	
	
    public TreeEntity(String id) {	
        super(id);	
    }	
	
    public void setTreeNames(String treeNames) {	
        this.treeNames = treeNames;	
    }	
	
    public void setTreeSort(Integer treeSort) {	
        this.treeSort = treeSort;	
    }	
	
    public abstract void setParent(T var1);	
	
    public void setParentCodes(String parentCodes) {	
        this.parentCodes = parentCodes;	
    }	
	
    public void setParentCode(String parentCode) {	
        TreeEntity treeEntity;	
        block3 : {	
            if (this.parent == null) {	
                try {	
                    this.parent = (TreeEntity)this.getClass().newInstance();	
                    treeEntity = this;	
                    break block3;	
                }	
                catch (Exception a) {	
                    a.printStackTrace();	
                }	
            }	
            treeEntity = this;	
        }	
        ((BaseEntity)treeEntity.parent).setId(parentCode);	
    }	
	
    @JsonIgnore	
    public String getTreeName_() {	
        int n;	
        if (StringUtils.isNotBlank(this.treeName_)) {	
            return this.treeName_;	
        }	
        Column[] arrcolumn = MapperHelper.getTable(this).columns();	
        int n2 = arrcolumn.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            Column a = arrcolumn[n];	
            if (a.isTreeName()) {	
                String a2 = MapperHelper.getAttrName(a);	
                this.treeName_ = (String)ReflectUtils.invokeGetter(this, a2);	
                if (this.treeName_ != null) {	
                    this.treeName_ = this.treeName_.replaceAll("/", "_");	
                }	
            }	
            n3 = ++n;	
        }	
        return this.treeName_;	
    }	
	
    public void setChildList(List<T> childList) {	
        this.childList = childList;	
    }	
	
    @JsonBackReference	
    @NotNull	
    public abstract T getParent();	
	
    public String getTreeNames() {	
        return this.treeNames;	
    }	
	
    public Integer getTreeSort() {	
        return this.treeSort;	
    }	
	
    public String getTreeLeaf() {	
        return this.treeLeaf;	
    }	
	
    @Length(min=1, max=2000)	
    public String getParentCodes() {	
        return this.parentCodes;	
    }	
	
    public String getTreeSorts() {	
        return this.treeSorts;	
    }	
	
    public void setTreeLevel(Integer treeLevel) {	
        this.treeLevel = treeLevel;	
    }	
	
    public Boolean getIsTreeLeaf() {	
        return "1".equals(this.treeLeaf);	
    }	
	
    public void setIsQueryChildren(Boolean isQueryChildren) {	
        this.isQueryChildren = isQueryChildren;	
    }	
	
    public void setTreeName_(String treeName) {	
        this.treeName_ = treeName;	
    }	
	
    public void setTreeLeaf(String treeLeaf) {	
        this.treeLeaf = treeLeaf;	
    }	
}	
	
