/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonBackReference	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
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
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}gen_table", alias="a", columns={@Column(name="table_name", attrName="tableName", label="\u8868\u540d", isPK=true), @Column(name="class_name", attrName="className", label="\u5b9e\u4f53\u7c7b\u540d\u79f0"), @Column(name="comments", attrName="comments", label="\u8868\u8bf4\u660e", queryType=QueryType.LIKE), @Column(name="parent_table_name", attrName="parentTableName", label="\u5173\u8054\u7236\u8868\u7684\u8868\u540d"), @Column(name="parent_table_fk_name", attrName="parentTableFkName", label="\u672c\u8868\u5173\u8054\u7236\u8868\u7684\u5916\u952e\u540d"), @Column(name="data_source_name", attrName="dataSourceName", label="\u6570\u636e\u6e90\u540d\u79f0"), @Column(name="tpl_category", attrName="tplCategory", label="\u751f\u6210\u6a21\u677f\u5206\u7c7b"), @Column(name="package_name", attrName="packageName", label="\u751f\u6210\u5305\u8def\u5f84"), @Column(name="module_name", attrName="moduleName", label="\u751f\u6210\u6a21\u5757\u540d"), @Column(name="sub_module_name", attrName="subModuleName", label="\u751f\u6210\u5b50\u6a21\u5757\u540d"), @Column(name="function_name", attrName="functionName", label="\u751f\u6210\u529f\u80fd\u540d"), @Column(name="function_name_simple", attrName="functionNameSimple", label="\u751f\u6210\u529f\u80fd\u540d\uff08\u7b80\u5199\uff09"), @Column(name="function_author", attrName="functionAuthor", label="\u751f\u6210\u529f\u80fd\u4f5c\u8005"), @Column(name="gen_base_dir", attrName="genBaseDir", label="\u751f\u6210\u7684\u57fa\u7840\u76ee\u5f55"), @Column(name="options", attrName="options", label="\u5176\u5b83\u751f\u6210\u9009\u9879", isQuery=false), @Column(name="create_by", attrName="createBy", label="\u521b\u5efa\u8005", isUpdate=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false), @Column(name="update_by", attrName="updateBy", label="\u66f4\u65b0\u8005", isUpdate=true), @Column(name="update_date", attrName="updateDate", label="\u66f4\u65b0\u65f6\u95f4", isUpdate=true, isQuery=false), @Column(name="remarks", attrName="remarks", label="\u5907\u6ce8\u4fe1\u606f", queryType=QueryType.LIKE)}, extColumnKeys="extColumn", orderBy="a.update_date DESC")	
public class GenTable	
extends DataEntity<GenTable> {	
    private Boolean isBaseEntity;	
    private List<GenTableColumn> columnList = ListUtils.newArrayList();	
    private String className;	
    private String tplCategory;	
    private Long childNum;	
    private String subModuleName;	
    private GenTable parent;	
    private String genFlag;	
    private Boolean isTreeEntity;	
    private String parentTableName;	
    private String comments;	
    private String treeViewCodeAttrName;	
    private String moduleName;	
    private String parentTableFkName;	
    private Map<String, Object> optionMap;	
    private List<GenTableColumn> pkList;	
    private static final long serialVersionUID = 1L;	
    private String treeViewNameAttrName;	
    private String functionNameSimple;	
    private Boolean isExtendEntity;	
    private String replaceFile;	
    private String functionName;	
    private String genBaseDir;	
    private String options;	
    private String dataSourceName;	
    private String tableName;	
    private Boolean isDataEntity;	
    private String functionAuthor;	
    private List<GenTable> childList = ListUtils.newArrayList();	
    private String packageName;	
	
    public String getTplCategory() {	
        return this.tplCategory;	
    }	
	
    public void setGenFlag(String genFlag) {	
        this.genFlag = genFlag;	
    }	
	
    public void setFunctionNameSimple(String functionNameSimple) {	
        this.functionNameSimple = functionNameSimple;	
    }	
	
    public void setOptionMap(Map<String, Object> optionMap) {	
        this.optionMap = optionMap;	
    }	
	
    public String getReplaceFile() {	
        return this.replaceFile;	
    }	
	
    public void setModuleName(String moduleName) {	
        this.moduleName = moduleName;	
    }	
	
    public void setParentTableName(String parentTableName) {	
        this.parentTableName = parentTableName;	
    }	
	
    public void setPackageName(String packageName) {	
        this.packageName = packageName;	
    }	
	
    public void setFunctionAuthor(String functionAuthor) {	
        this.functionAuthor = functionAuthor;	
    }	
	
    public Boolean getCreateDateExists() {	
        for (GenTableColumn a2 : this.columnList) {	
            if (!"create_date".equals(a2.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public void setChildList(List<GenTable> childList) {	
        this.childList = childList;	
    }	
	
    public String getGenTableName() {	
        String a2 = this.getTableName();	
        if (a2 != null && StringUtils.startsWith((CharSequence)a2, (CharSequence)Global.getTablePrefix())) {	
            a2 = new StringBuilder().insert(0, "${_prefix}").append(StringUtils.substringAfter((String)a2, (String)Global.getTablePrefix())).toString();	
        }	
        return a2;	
    }	
	
    public Boolean getIsPkCustom() {	
        for (GenTableColumn a2 : this.pkList) {	
            if ("hidden".equals(a2.getShowType())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public String getSubModuleName() {	
        return this.subModuleName;	
    }	
	
    public void setOptions(String options) {	
        if (StringUtils.isNotBlank((CharSequence)options)) {	
            this.optionMap = (Map)JsonMapper.fromJson((String)options, Map.class);	
        }	
        this.options = options;	
    }	
	
    public void setParent(GenTable parent) {	
        this.parent = parent;	
    }	
	
    public void setTableName(String tableName) {	
        this.tableName = tableName;	
    }	
	
    public String getPackageName() {	
        return this.packageName;	
    }	
	
    public void setGenBaseDir(String genBaseDir) {	
        this.genBaseDir = genBaseDir;	
    }	
	
    public Boolean getUpdateDateExists() {	
        for (GenTableColumn a2 : this.columnList) {	
            if (!"update_date".equals(a2.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    @JsonBackReference	
    public GenTable getParent() {	
        return this.parent;	
    }	
	
    public String getTableName_like() {	
        return (String)this.sqlMap.getWhere().getValue("table_name", QueryType.LIKE);	
    }	
	
    public void setTplCategory(String tplCategory) {	
        this.tplCategory = tplCategory;	
    }	
	
    public Boolean getIsBaseEntity() {	
        if (this.isBaseEntity == null) {	
            this.isBaseEntity = this.getIsEntityClass(BaseEntity.class);	
        }	
        return this.isBaseEntity;	
    }	
	
    private /* synthetic */ Boolean getIsEntityClass(Class<?> entityClass) {	
        int n;	
        Column[] arrcolumn = MapperHelper.getTable(entityClass).columns();	
        int n2 = arrcolumn.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            boolean bl;	
            block3 : {	
                Column a2 = arrcolumn[n];	
                boolean a3 = false;	
                for (GenTableColumn a4 : this.columnList) {	
                    if (!a2.name().equals(a4.getColumnName())) continue;	
                    bl = a3 = true;	
                    break block3;	
                }	
                bl = a3;	
            }	
            if (!bl) {	
                return false;	
            }	
            n3 = ++n;	
        }	
        return true;	
    }	
	
    public String getGenFlag() {	
        return this.genFlag;	
    }	
	
    @NotBlank(message="\u8868\u8bf4\u660e\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getComments() {	
        if (StringUtils.isBlank((CharSequence)this.comments)) {	
            return this.getTableName();	
        }	
        return this.comments;	
    }	
	
    public String getFunctionName() {	
        return this.functionName;	
    }	
	
    public void setParentTableName_isNull(String parentTableName) {	
        if (StringUtils.isBlank((CharSequence)parentTableName)) {	
            this.setParentTableName(null);	
            this.sqlMap.getWhere().andBracket("parent_table_name", QueryType.IS_NULL, null, 2).or("parent_table_name", QueryType.EQ_FORCE, "", 3).endBracket();	
            return;	
        }	
        this.setParentTableName(parentTableName);	
    }	
	
    public void setClassName(String className) {	
        this.className = className;	
    }	
	
    @NotBlank(message="\u529f\u80fd\u4f5c\u8005\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getFunctionAuthor() {	
        return this.functionAuthor;	
    }	
	
    public String getParentTableName_isNull() {	
        return this.getParentTableName();	
    }	
	
    public List<GenTable> getChildList() {	
        return this.childList;	
    }	
	
    public Boolean getIsExtendEntity() {	
        if (this.isExtendEntity == null) {	
            this.isExtendEntity = this.getIsEntityClass(Extend.class);	
        }	
        return this.isExtendEntity;	
    }	
	
    public Boolean getIsDataEntity() {	
        if (this.isDataEntity == null) {	
            this.isDataEntity = this.getIsEntityClass(DataEntity.class);	
        }	
        return this.isDataEntity;	
    }	
	
    public List<String> getImportList() {	
        ArrayList a2 = ListUtils.newArrayList();	
        for (GenTableColumn a3 : this.getColumnList()) {	
            if (!a3.getIsSuperColumn().booleanValue()) {	
                if (StringUtils.indexOf((CharSequence)a3.getAttrType(), (CharSequence)".") != -1 && !a2.contains(a3.getAttrType())) {	
                    if (!a2.contains(a3.getAttrType())) {	
                        a2.add(a3.getAttrType());	
                    }	
                    if (!a2.contains(JoinTable.class.getName())) {	
                        a2.add(JoinTable.class.getName());	
                    }	
                    if (!a2.contains(new StringBuilder().insert(0, JoinTable.class.getName()).append(".Type").toString())) {	
                        a2.add(JoinTable.class.getName() + ".Type");	
                    }	
                }	
                if (a3.getIsExtendColumn().booleanValue() && !a2.contains(Extend.class.getName())) {	
                    a2.add(Extend.class.getName());	
                }	
                for (String a4 : a3.getAnnotationList()) {	
                    if (a2.contains(StringUtils.substringBefore((String)a4, (String)"("))) continue;	
                    a2.add(StringUtils.substringBefore((String)a4, (String)"("));	
                }	
                continue;	
            }	
            if (!a3.getIsBaseEntityColumn().booleanValue() || a2.contains(BaseEntity.class.getName())) continue;	
            a2.add(BaseEntity.class.getName());	
        }	
        if (this.getChildList() != null && this.getChildList().size() > 0) {	
            if (!a2.contains("java.util.List")) {	
                a2.add("java.util.List");	
            }	
            if (!a2.contains("com.jeeste.common.collect.LstUtils")) {	
                a2.add("\tom.jeesite.\tommon.colle\tt.ListUtils");	
            }	
        }	
        return a2;	
    }	
	
    public Boolean getStatusExists() {	
        for (GenTableColumn a2 : this.columnList) {	
            if (!"status".equals(a2.getColumnName())) continue;	
            return true;	
        }	
        return false;	
    }	
	
    public String getModuleName() {	
        return this.moduleName;	
    }	
	
    public void setReplaceFile(String replaceFile) {	
        this.replaceFile = replaceFile;	
    }	
	
    public void setChildNum(Long childNum) {	
        this.childNum = childNum;	
    }	
	
    public Boolean getParentExists() {	
        return StringUtils.isNotBlank((CharSequence)this.parentTableName) && StringUtils.isNotBlank((CharSequence)this.parentTableFkName);	
    }	
	
    public void setParentTableFkName(String parentTableFkName) {	
        this.parentTableFkName = parentTableFkName;	
    }	
	
    public String getParentTableFkName() {	
        return StringUtils.lowerCase((String)this.parentTableFkName);	
    }	
	
    public String getDataSourceName() {	
        return this.dataSourceName;	
    }	
	
    @JsonIgnore	
    public String getOptions() {	
        if (this.optionMap != null) {	
            this.options = JsonMapper.toJson(this.optionMap);	
        }	
        return this.options;	
    }	
	
    public void setDataSourceName(String dataSourceName) {	
        this.dataSourceName = dataSourceName;	
    }	
	
    public String getFunctionNameSimple() {	
        return this.functionNameSimple;	
    }	
	
    public List<GenTableColumn> getColumnList() {	
        return this.columnList;	
    }	
	
    public String getTreeViewNameAttrName() {	
        String a2;	
        if (this.getIsTreeEntity().booleanValue() && this.treeViewNameAttrName == null && StringUtils.isNotBlank((CharSequence)(a2 = (String)this.optionMap.get("treeViewName")))) {	
            this.treeViewNameAttrName = this.getColumn(a2).getAttrName();	
        }	
        return this.treeViewNameAttrName;	
    }	
	
    @NotBlank(message="\u5b9e\u4f53\u7c7b\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getClassName() {	
        return this.className;	
    }	
	
    public Boolean getIsTreeEntity() {	
        if (this.isTreeEntity == null) {	
            this.isTreeEntity = this.getIsEntityClass(TreeEntity.class);	
        }	
        return this.isTreeEntity;	
    }	
	
    public String getTreeViewCodeAttrName() {	
        String a2;	
        if (this.getIsTreeEntity().booleanValue() && this.treeViewCodeAttrName == null && StringUtils.isNotBlank((CharSequence)(a2 = (String)this.optionMap.get("treeViewCode")))) {	
            this.treeViewCodeAttrName = this.getColumn(a2).getAttrName();	
        }	
        return this.treeViewCodeAttrName;	
    }	
	
    public String getGenBaseDir() {	
        return this.genBaseDir;	
    }	
	
    public void setTableName_like(String tableName) {	
        this.sqlMap.getWhere().and("table_name", QueryType.LIKE, tableName);	
    }	
	
    public void setColumnList(List<GenTableColumn> columnList) {	
        this.columnList = columnList;	
    }	
	
    public GenTable(String id) {	
        super(id);	
    }	
	
    public GenTable() {	
    }	
	
    public String getParentTableName() {	
        return StringUtils.lowerCase((String)this.parentTableName);	
    }	
	
    public List<GenTableColumn> getPkList() {	
        if (this.pkList == null) {	
            this.pkList = ListUtils.newArrayList();	
            for (GenTableColumn a2 : this.columnList) {	
                if (!"1".equals(a2.getIsPk())) continue;	
                this.pkList.add(a2);	
            }	
        }	
        return this.pkList;	
    }	
	
    public String getTableNameAndComments() {	
        return new StringBuilder().insert(0, this.getTableName()).append(this.comments == null ? "" : new StringBuilder().insert(0, "  :  ").append(this.comments).toString()).toString();	
    }	
	
    public GenTableColumn getColumn(String columnName) {	
        for (GenTableColumn a2 : this.columnList) {	
            if (!a2.getColumnName().equals(columnName)) continue;	
            return a2;	
        }	
        return new GenTableColumn();	
    }	
	
    public Map<String, Object> getOptionMap() {	
        if (this.optionMap == null) {	
            this.optionMap = MapUtils.newHashMap();	
        }	
        return this.optionMap;	
    }	
	
    public void setPkList(List<GenTableColumn> pkList) {	
        this.pkList = pkList;	
    }	
	
    public void setComments(String comments) {	
        this.comments = comments;	
    }	
	
    public void setFunctionName(String functionName) {	
        this.functionName = functionName;	
    }	
	
    public void setSubModuleName(String subModuleName) {	
        this.subModuleName = subModuleName;	
    }	
	
    @NotBlank(message="\u8868\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u8868\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getTableName() {	
        return StringUtils.lowerCase((String)this.tableName);	
    }	
	
    public Long getChildNum() {	
        return this.childNum;	
    }	
}	
	
