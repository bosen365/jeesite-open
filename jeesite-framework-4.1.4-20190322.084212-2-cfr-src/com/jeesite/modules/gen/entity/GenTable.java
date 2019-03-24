/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.entity;	
	
import com.fasterxml.jackson.annotation.JsonBackReference;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.shiro.l.k;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import java.util.ArrayList;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}gen_table", alias="a", columns={@Column(name="table_name", attrName="tableName", label="\u8868\u540d", isPK=true), @Column(name="class_name", attrName="className", label="\u5b9e\u4f53\u7c7b\u540d\u79f0"), @Column(name="comments", attrName="comments", label="\u8868\u8bf4\u660e", queryType=QueryType.LIKE), @Column(name="parent_table_name", attrName="parentTableName", label="\u5173\u8054\u7236\u8868\u7684\u8868\u540d"), @Column(name="parent_table_fk_name", attrName="parentTableFkName", label="\u672c\u8868\u5173\u8054\u7236\u8868\u7684\u5916\u952e\u540d"), @Column(name="data_source_name", attrName="dataSourceName", label="\u6570\u636e\u6e90\u540d\u79f0"), @Column(name="tpl_category", attrName="tplCategory", label="\u751f\u6210\u6a21\u677f\u5206\u7c7b"), @Column(name="package_name", attrName="packageName", label="\u751f\u6210\u5305\u8def\u5f84"), @Column(name="module_name", attrName="moduleName", label="\u751f\u6210\u6a21\u5757\u540d"), @Column(name="sub_module_name", attrName="subModuleName", label="\u751f\u6210\u5b50\u6a21\u5757\u540d"), @Column(name="function_name", attrName="functionName", label="\u751f\u6210\u529f\u80fd\u540d"), @Column(name="function_name_simple", attrName="functionNameSimple", label="\u751f\u6210\u529f\u80fd\u540d\uff08\u7b80\u5199\uff09"), @Column(name="function_author", attrName="functionAuthor", label="\u751f\u6210\u529f\u80fd\u4f5c\u8005"), @Column(name="gen_base_dir", attrName="genBaseDir", label="\u751f\u6210\u7684\u57fa\u7840\u76ee\u5f55"), @Column(name="options", attrName="options", label="\u5176\u5b83\u751f\u6210\u9009\u9879", isQuery=false), @Column(name="create_by", attrName="createBy", label="\u521b\u5efa\u8005", isUpdate=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false), @Column(name="update_by", attrName="updateBy", label="\u66f4\u65b0\u8005", isUpdate=true), @Column(name="update_date", attrName="updateDate", label="\u66f4\u65b0\u65f6\u95f4", isUpdate=true, isQuery=false), @Column(name="remarks", attrName="remarks", label="\u5907\u6ce8\u4fe1\u606f", queryType=QueryType.LIKE)}, extColumnKeys="extColumn", orderBy="a.update_date DESC")	
public class GenTable	
extends DataEntity<GenTable> {	
    private String tableName;	
    private String dataSourceName;	
    private String parentTableFkName;	
    private Boolean isBaseEntity;	
    private Boolean isTreeEntity;	
    private Boolean isExtendEntity;	
    private GenTable parent;	
    private Long childNum;	
    private String genFlag;	
    private String functionName;	
    private String replaceFile;	
    private List<GenTableColumn> pkList;	
    private String functionAuthor;	
    private String treeViewCodeAttrName;	
    private List<GenTableColumn> columnList = ListUtils.newArrayList();	
    private String subModuleName;	
    private Boolean isDataEntity;	
    private static final long serialVersionUID = 1L;	
    private String moduleName;	
    private String parentTableName;	
    private String className;	
    private String options;	
    private String comments;	
    private Map<String, Object> optionMap;	
    private List<GenTable> childList = ListUtils.newArrayList();	
    private String packageName;	
    private String treeViewNameAttrName;	
    private String tplCategory;	
    private String functionNameSimple;	
    private String genBaseDir;	
	
    public void setTableName_like(String tableName) {	
        this.sqlMap.getWhere().and("table_name", QueryType.LIKE, tableName);	
    }	
	
    public GenTable() {	
    }	
	
    public void setChildNum(Long childNum) {	
        this.childNum = childNum;	
    }	
	
    public Boolean getIsTreeEntity() {	
        if (this.isTreeEntity == null) {	
            this.isTreeEntity = this.getIsEntityClass(TreeEntity.class);	
        }	
        return this.isTreeEntity;	
    }	
	
    public String getGenFlag() {	
        return this.genFlag;	
    }	
	
    public String getTreeViewNameAttrName() {	
        String a;	
        if (this.getIsTreeEntity().booleanValue() && this.treeViewNameAttrName == null && StringUtils.isNotBlank(a = (String)this.optionMap.get("treeViewName"))) {	
            this.treeViewNameAttrName = this.getColumn(a).getAttrName();	
        }	
        return this.treeViewNameAttrName;	
    }	
	
    public void setFunctionAuthor(String functionAuthor) {	
        this.functionAuthor = functionAuthor;	
    }	
	
    private /* synthetic */ Boolean getIsEntityClass(Class<?> entityClass) {	
        int n;	
        Column[] arrcolumn = MapperHelper.getTable(entityClass).columns();	
        int n2 = arrcolumn.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            boolean bl;	
            block3 : {	
                Column a = arrcolumn[n];	
                boolean a2 = false;	
                for (GenTableColumn a3 : this.columnList) {	
                    if (!a.name().equals(a3.getColumnName())) continue;	
                    bl = a2 = true;	
                    break block3;	
                }	
                bl = a2;	
            }	
            if (!bl) {	
                return false;	
            }	
            n3 = ++n;	
        }	
        return true;	
    }	
	
    public List<GenTableColumn> getPkList() {	
        if (this.pkList == null) {	
            this.pkList = ListUtils.newArrayList();	
            for (GenTableColumn a : this.columnList) {	
                if (!"1".equals(a.getIsPk())) continue;	
                this.pkList.add(a);	
            }	
        }	
        return this.pkList;	
    }	
	
    public GenTableColumn getColumn(String columnName) {	
        for (GenTableColumn a : this.columnList) {	
            if (!a.getColumnName().equals(columnName)) continue;	
            return a;	
        }	
        return new GenTableColumn();	
    }	
	
    public Boolean getIsDataEntity() {	
        if (this.isDataEntity == null) {	
            this.isDataEntity = this.getIsEntityClass(DataEntity.class);	
        }	
        return this.isDataEntity;	
    }	
	
    public String getTableNameAndComments() {	
        return new StringBuilder().insert(0, this.getTableName()).append(this.comments == null ? "" : new StringBuilder().insert(0, "  :  ").append(this.comments).toString()).toString();	
    }	
	
    public Boolean getIsBaseEntity() {	
        if (this.isBaseEntity == null) {	
            this.isBaseEntity = this.getIsEntityClass(BaseEntity.class);	
        }	
        return this.isBaseEntity;	
    }	
	
    public List<String> getImportList() {	
        ArrayList<String> a = ListUtils.newArrayList();	
        for (GenTableColumn a2 : this.getColumnList()) {	
            if (!a2.getIsSuperColumn().booleanValue()) {	
                if (StringUtils.indexOf((CharSequence)a2.getAttrType(), ".") != -1 && !a.contains(a2.getAttrType())) {	
                    if (!a.contains(a2.getAttrType())) {	
                        a.add(a2.getAttrType());	
                    }	
                    if (!a.contains(JoinTable.class.getName())) {	
                        a.add(JoinTable.class.getName());	
                    }	
                    if (!a.contains(new StringBuilder().insert(0, JoinTable.class.getName()).append(".Type").toString())) {	
                        a.add(JoinTable.class.getName() + ".Type");	
                    }	
                }	
                if (a2.getIsExtendColumn().booleanValue() && !a.contains(Extend.class.getName())) {	
                    a.add(Extend.class.getName());	
                }	
                for (String a3 : a2.getAnnotationList()) {	
                    if (a.contains(StringUtils.substringBefore(a3, "("))) continue;	
                    a.add(StringUtils.substringBefore(a3, "("));	
                }	
                continue;	
            }	
            if (!a2.getIsBaseEntityColumn().booleanValue() || a.contains(BaseEntity.class.getName())) continue;	
            a.add(BaseEntity.class.getName());	
        }	
        if (this.getChildList() != null && this.getChildList().size() > 0) {	
            if (!a.contains("java.util.List")) {	
                a.add("java.util.List");	
            }	
            if (!a.contains("com.jeesite.common.collect.ListUtils")) {	
                a.add("com.jeesite.common.collect.ListUtils");	
            }	
        }	
        return a;	
    }	
	
    public String getFunctionName() {	
        return this.functionName;	
    }	
	
    public void setOptions(String options) {	
        if (StringUtils.isNotBlank(options)) {	
            this.optionMap = (Map)JsonMapper.fromJson(options, Map.class);	
        }	
        this.options = options;	
    }	
	
    public String getFunctionNameSimple() {	
        return this.functionNameSimple;	
    }	
	
    public String getParentTableFkName() {	
        return StringUtils.lowerCase(this.parentTableFkName);	
    }	
	
    public String getParentTableName() {	
        return StringUtils.lowerCase(this.parentTableName);	
    }	
	
    public Boolean getStatusExists() {	
        for (GenTableColumn a : this.columnList) {	
            if (!"status".equals(a.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public void setPkList(List<GenTableColumn> pkList) {	
        this.pkList = pkList;	
    }	
	
    public String getGenBaseDir() {	
        return this.genBaseDir;	
    }	
	
    public List<GenTable> getChildList() {	
        return this.childList;	
    }	
	
    public void setParentTableName_isNull(String parentTableName) {	
        if (StringUtils.isBlank(parentTableName)) {	
            this.sqlMap.getWhere().andBracket("parent_table_name", QueryType.IS_NULL, null, 2).or("parent_table_name", QueryType.EQ_FORCE, "", 3).endBracket();	
            this.setParentTableName(null);	
            return;	
        }	
        this.setParentTableName(parentTableName);	
    }	
	
    @NotBlank(message="\u5b9e\u4f53\u7c7b\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getClassName() {	
        return this.className;	
    }	
	
    public void setChildList(List<GenTable> childList) {	
        this.childList = childList;	
    }	
	
    public String getParentTableName_isNull() {	
        return this.getParentTableName();	
    }	
	
    public String getTreeViewCodeAttrName() {	
        String a;	
        if (this.getIsTreeEntity().booleanValue() && this.treeViewCodeAttrName == null && StringUtils.isNotBlank(a = (String)this.optionMap.get("treeViewCode"))) {	
            this.treeViewCodeAttrName = this.getColumn(a).getAttrName();	
        }	
        return this.treeViewCodeAttrName;	
    }	
	
    public void setTableName(String tableName) {	
        this.tableName = tableName;	
    }	
	
    public void setSubModuleName(String subModuleName) {	
        this.subModuleName = subModuleName;	
    }	
	
    public void setOptionMap(Map<String, Object> optionMap) {	
        this.optionMap = optionMap;	
    }	
	
    public void setParentTableName(String parentTableName) {	
        this.parentTableName = parentTableName;	
    }	
	
    public void setParent(GenTable parent) {	
        this.parent = parent;	
    }	
	
    public String getPackageName() {	
        return this.packageName;	
    }	
	
    public String getReplaceFile() {	
        return this.replaceFile;	
    }	
	
    public void setDataSourceName(String dataSourceName) {	
        this.dataSourceName = dataSourceName;	
    }	
	
    public void setReplaceFile(String replaceFile) {	
        this.replaceFile = replaceFile;	
    }	
	
    public GenTable(String id) {	
        super(id);	
    }	
	
    @JsonBackReference	
    public GenTable getParent() {	
        return this.parent;	
    }	
	
    @JsonIgnore	
    public String getOptions() {	
        if (this.optionMap != null) {	
            this.options = JsonMapper.toJson(this.optionMap);	
        }	
        return this.options;	
    }	
	
    public String getDataSourceName() {	
        return this.dataSourceName;	
    }	
	
    public void setGenBaseDir(String genBaseDir) {	
        this.genBaseDir = genBaseDir;	
    }	
	
    public Long getChildNum() {	
        return this.childNum;	
    }	
	
    @NotBlank(message="\u8868\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u8868\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getTableName() {	
        return StringUtils.lowerCase(this.tableName);	
    }	
	
    public String getTplCategory() {	
        return this.tplCategory;	
    }	
	
    public void setParentTableFkName(String parentTableFkName) {	
        this.parentTableFkName = parentTableFkName;	
    }	
	
    public void setClassName(String className) {	
        this.className = className;	
    }	
	
    public void setFunctionNameSimple(String functionNameSimple) {	
        this.functionNameSimple = functionNameSimple;	
    }	
	
    public void setComments(String comments) {	
        this.comments = comments;	
    }	
	
    public Boolean getUpdateDateExists() {	
        for (GenTableColumn a : this.columnList) {	
            if (!"update_date".equals(a.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    @NotBlank(message="\u529f\u80fd\u4f5c\u8005\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getFunctionAuthor() {	
        return this.functionAuthor;	
    }	
	
    public void setFunctionName(String functionName) {	
        this.functionName = functionName;	
    }	
	
    public String getGenTableName() {	
        String a = this.getTableName();	
        if (a != null && StringUtils.startsWith(a, Global.getTablePrefix())) {	
            a = new StringBuilder().insert(0, "${_prefix}").append(StringUtils.substringAfter(a, Global.getTablePrefix())).toString();	
        }	
        return a;	
    }	
	
    public void setTplCategory(String tplCategory) {	
        this.tplCategory = tplCategory;	
    }	
	
    public Boolean getCreateDateExists() {	
        for (GenTableColumn a : this.columnList) {	
            if (!"create_date".equals(a.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public void setColumnList(List<GenTableColumn> columnList) {	
        this.columnList = columnList;	
    }	
	
    public String getSubModuleName() {	
        return this.subModuleName;	
    }	
	
    public String getModuleName() {	
        return this.moduleName;	
    }	
	
    public String getTableName_like() {	
        return (String)this.sqlMap.getWhere().getValue("table_name", QueryType.LIKE);	
    }	
	
    public void setGenFlag(String genFlag) {	
        this.genFlag = genFlag;	
    }	
	
    public Boolean getIsExtendEntity() {	
        if (this.isExtendEntity == null) {	
            this.isExtendEntity = this.getIsEntityClass(Extend.class);	
        }	
        return this.isExtendEntity;	
    }	
	
    public void setPackageName(String packageName) {	
        this.packageName = packageName;	
    }	
	
    public Boolean getParentExists() {	
        return StringUtils.isNotBlank(this.parentTableName) && StringUtils.isNotBlank(this.parentTableFkName);	
    }	
	
    public Map<String, Object> getOptionMap() {	
        if (this.optionMap == null) {	
            this.optionMap = MapUtils.newHashMap();	
        }	
        return this.optionMap;	
    }	
	
    public void setModuleName(String moduleName) {	
        this.moduleName = moduleName;	
    }	
	
    public Boolean getIsPkCustom() {	
        for (GenTableColumn a : this.pkList) {	
            if ("hidden".equals(a.getShowType())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public List<GenTableColumn> getColumnList() {	
        return this.columnList;	
    }	
	
    @NotBlank(message="\u8868\u8bf4\u660e\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getComments() {	
        if (StringUtils.isBlank(this.comments)) {	
            return this.getTableName();	
        }	
        return this.comments;	
    }	
}	
	
