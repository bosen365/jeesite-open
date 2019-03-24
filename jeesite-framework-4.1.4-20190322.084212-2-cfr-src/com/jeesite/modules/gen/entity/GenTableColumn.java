/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}gen_table_column", alias="a", columns={@Column(name="id", attrName="id", label="\u5217\u7f16\u53f7", isPK=true), @Column(name="table_name", attrName="genTable.tableName", label="\u8868\u540d"), @Column(name="column_name", attrName="columnName", label="\u5217\u540d"), @Column(name="column_sort", attrName="columnSort", label="\u5217\u6392\u5e8f", comment="\u5217\u6392\u5e8f\uff08\u5347\u5e8f\uff09"), @Column(name="column_type", attrName="columnType", label="\u5217\u7c7b\u578b"), @Column(name="column_label", attrName="columnLabel", label="\u5217\u6807\u7b7e\u540d"), @Column(name="comments", attrName="comments", label="\u8868\u8bf4\u660e"), @Column(name="attr_name", attrName="fullAttrName", label="\u7c7b\u7684\u5c5e\u6027\u540d"), @Column(name="attr_type", attrName="attrType", label="\u7c7b\u7684\u5c5e\u6027\u7c7b\u578b"), @Column(name="is_pk", attrName="isPk", label="\u662f\u5426\u4e3b\u952e"), @Column(name="is_null", attrName="isNull", label="\u662f\u53ef\u4e3a\u7a7a"), @Column(name="is_insert", attrName="isInsert", label="\u662f\u5426\u63d2\u5165\u5b57\u6bb5"), @Column(name="is_update", attrName="isUpdate", label="\u662f\u5426\u66f4\u65b0\u5b57\u6bb5"), @Column(name="is_list", attrName="isList", label="\u662f\u5426\u5217\u8868\u5b57\u5178"), @Column(name="is_query", attrName="isQuery", label="\u662f\u5426\u67e5\u8be2\u5b57\u6bb5"), @Column(name="query_type", attrName="queryType", label="\u67e5\u8be2\u65b9\u5f0f"), @Column(name="is_edit", attrName="isEdit", label="\u662f\u5426\u7f16\u8f91\u5b57\u6bb5"), @Column(name="show_type", attrName="showType", label="\u5b57\u6bb5\u663e\u793a\u65b9\u5f0f"), @Column(name="options", attrName="options", label="\u5176\u5b83\u751f\u6210\u9009\u9879")}, orderBy="a.column_sort")	
public class GenTableColumn	
extends DataEntity<GenTableColumn> {	
    private static final long serialVersionUID = 1L;	
    private String columnLabel;	
    private String attrName;	
    private String columnType;	
    private String options;	
    private Map<String, Object> optionMap;	
    private String columnName;	
    private String queryType;	
    private String comments;	
    private String isQuery;	
    private String isList;	
    private String isUpdate;	
    private String showType;	
    private String isNull;	
    private Integer columnSort;	
    private GenTable genTable;	
    private String isEdit;	
    private String isInsert;	
    private String isPk;	
    private String attrType;	
	
    public String getAttrName2() {	
        if (User.class.getName().equals(this.getAttrType())) {	
            return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".userName").toString();	
        }	
        if ("com.jeesite.modules.sys.etity.Office".equals(this.getAttrType())) {	
            return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".officeName").toString();	
        }	
        String[] a = this.getAttrNames();	
        if (a.length > 0) {	
            if (StringUtils.contains((CharSequence)this.getFullAttrName(), ".")) {	
                return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".").append(a[0]).toString();	
            }	
            return a[0];	
        }	
        return "";	
    }	
	
    public void setComments(String comments) {	
        this.comments = comments;	
    }	
	
    public String getColumnNameAndComments() {	
        return new StringBuilder().insert(0, this.getColumnName()).append(this.comments == null ? "" : new StringBuilder().insert(0, "  :  ").append(this.comments).toString()).toString();	
    }	
	
    public void setColumnSort(Integer columnSort) {	
        this.columnSort = columnSort;	
    }	
	
    @JsonIgnore	
    public List<String> getSimpleAnnotationList() {	
        Iterator<String> iterator;	
        ArrayList<String> a = ListUtils.newArrayList();	
        Iterator<String> iterator2 = iterator = this.getAnnotationList().iterator();	
        while (iterator2.hasNext()) {	
            String a2 = iterator.next();	
            iterator2 = iterator;	
            a.add(StringUtils.substringAfterLast(a2, "."));	
        }	
        return a;	
    }	
	
    public void setIsPk(String isPk) {	
        this.isPk = isPk;	
    }	
	
    public String[] getAttrNames() {	
        String[] a = StringUtils.split(StringUtils.substringAfter(this.getFullAttrName(), "|"), "|");	
        String[] a2 = new String[a.length];	
        if (a != null) {	
            int a3;	
            int n = a3 = 0;	
            while (n < a.length) {	
                int n2 = a3++;	
                a2[n2] = a[n2];	
                n = a3;	
            }	
        }	
        return a2;	
    }	
	
    public String getAttrNameForGetMethod() {	
        int a;	
        Object[] a2 = StringUtils.split(this.getAttrName(), ".");	
        int n = a = 0;	
        while (n < a2.length) {	
            int n2 = a;	
            Object[] arrobject = a2;	
            String string = "get" + StringUtils.cap((String)arrobject[a]) + "()";	
            arrobject[n2] = string;	
            n = ++a;	
        }	
        return StringUtils.join(a2, ".");	
    }	
	
    public String getShowType() {	
        return this.showType;	
    }	
	
    public void setColumnType(String columnType) {	
        this.columnType = columnType;	
    }	
	
    public String getIsEdit() {	
        return this.isEdit;	
    }	
	
    public void setIsRequired(String isRequired) {	
        this.isNull = "1".equals(isRequired) ? "0" : "1";	
    }	
	
    public void setOptionMap(Map<String, Object> optionMap) {	
        this.optionMap = optionMap;	
    }	
	
    public String getColumnType() {	
        return StringUtils.lowerCase(this.columnType);	
    }	
	
    private /* synthetic */ Boolean getIsEntityColumn(Class<?> entityClass) {	
        int n;	
        String a = this.getColumnName();	
        Column[] arrcolumn = MapperHelper.getTable(entityClass).columns();	
        int n2 = arrcolumn.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            if (arrcolumn[n].name().equals(a)) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    public GenTableColumn(String id) {	
        super(id);	
    }	
	
    public void setIsQuery(String isQuery) {	
        this.isQuery = isQuery;	
    }	
	
    @JsonIgnore	
    public GenTable getGenTable() {	
        return this.genTable;	
    }	
	
    public String getDataLength() {	
        String[] a = StringUtils.split(StringUtils.substringBetween(this.getColumnType(), "(", ")"), ",");	
        if (a != null && a.length == 1) {	
            return a[0];	
        }	
        return "0";	
    }	
	
    @JsonIgnore	
    public String getOptions() {	
        if (this.optionMap != null) {	
            this.options = JsonMapper.toJson(this.optionMap);	
        }	
        return this.options;	
    }	
	
    public String getIsInsert() {	
        return this.isInsert;	
    }	
	
    @NotBlank(message="\u5217\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u5217\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getColumnName() {	
        return StringUtils.lowerCase(this.columnName);	
    }	
	
    public void setColumnName(String columnName) {	
        this.columnName = columnName;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     */	
    public String getColumnLabel() {	
        GenTableColumn genTableColumn;	
        if (this.getComments() != null) {	
            if (StringUtils.contains((CharSequence)this.getComments(), "(")) {	
                GenTableColumn genTableColumn2 = this;	
                genTableColumn = genTableColumn2;	
                genTableColumn2.columnLabel = StringUtils.substringBefore(genTableColumn2.getComments(), "(");	
                return genTableColumn.columnLabel;	
            }	
            if (StringUtils.contains((CharSequence)this.getComments(), "（")) {	
                GenTableColumn genTableColumn3 = this;	
                genTableColumn = genTableColumn3;	
                genTableColumn3.columnLabel = StringUtils.substringBefore(genTableColumn3.getComments(), "（");	
                return genTableColumn.columnLabel;	
            }	
            if (StringUtils.contains((CharSequence)this.getComments(), ":")) {	
                this.columnLabel = StringUtils.substringBefore(this.getComments(), ":");	
                genTableColumn = this;	
                return genTableColumn.columnLabel;	
            }	
            GenTableColumn genTableColumn4 = this;	
            if (StringUtils.contains((CharSequence)this.getComments(), "：")) {	
                genTableColumn4.columnLabel = StringUtils.substringBefore(this.getComments(), "：");	
                genTableColumn = this;	
                return genTableColumn.columnLabel;	
            }	
            genTableColumn4.columnLabel = this.getComments();	
        }	
        genTableColumn = this;	
        return genTableColumn.columnLabel;	
    }	
	
    public String getIsNull() {	
        return this.isNull;	
    }	
	
    public String getIsUpdate() {	
        return this.isUpdate;	
    }	
	
    public String getIsRequired() {	
        if ("1".equals(this.isNull)) {	
            return "0";	
        }	
        return "1";	
    }	
	
    public void setIsUpdate(String isUpdate) {	
        this.isUpdate = isUpdate;	
    }	
	
    public String getComments() {	
        if (StringUtils.isBlank(this.comments)) {	
            return this.getColumnName();	
        }	
        return this.comments;	
    }	
	
    public String getAttrName() {	
        String a = StringUtils.substringBefore(this.getFullAttrName(), "|");	
        if (User.class.getName().equals(this.getAttrType())) {	
            a = new StringBuilder().insert(0, a).append(".userCode").toString();	
            return a;	
        }	
        if ("com.jeesite.modules.sys.entity.Office".equals(this.getAttrType())) {	
            a = new StringBuilder().insert(0, a).append(".officeCode").toString();	
        }	
        return a;	
    }	
	
    public void setIsInsert(String isInsert) {	
        this.isInsert = isInsert;	
    }	
	
    public void setIsList(String isList) {	
        this.isList = isList;	
    }	
	
    public String getSimpleAttrType() {	
        if (this.genTable != null && "This".equals(this.getAttrType())) {	
            return StringUtils.capitalize(this.genTable.getClassName());	
        }	
        if (StringUtils.indexOf((CharSequence)this.getAttrType(), ".") != -1) {	
            return StringUtils.substringAfterLast(this.getAttrType(), ".");	
        }	
        return this.getAttrType();	
    }	
	
    public String getIsPk() {	
        return this.isPk;	
    }	
	
    public void setShowType(String showType) {	
        this.showType = showType;	
    }	
	
    @JsonIgnore	
    public Boolean getIsSuperColumn() {	
        return this.getIsBaseEntityColumn() != false || this.getIsDataEntityColumn() != false || this.getIsTreeEntityColumn() != false;	
    }	
	
    public void setGenTable(GenTable genTable) {	
        this.genTable = genTable;	
    }	
	
    public GenTableColumn(GenTable genTable) {	
        this.genTable = genTable;	
    }	
	
    @JsonIgnore	
    public List<String> getAnnotationList() {	
        ArrayList<String> a = ListUtils.newArrayList();	
        if ("This".equals(this.getAttrType())) {	
            a.add("com.fasterxml.jackson.annotation.JsonBackReference");	
        }	
        if ("java.util.Date".equals(this.getAttrType())) {	
            a.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")");	
        }	
        if (!"1".equals(this.getIsPk()) && !"hidden".equals(this.getShowType())) {	
            if (!"1".equals(this.getIsNull())) {	
                if ("String".equals(this.getAttrType())) {	
                    a.add("javax.validatio.costraits.NotBlak(message="" + this.getColumnLabel() + "不能为空")");	
                } else {	
                    a.add(new StringBuilder().insert(0, "javax.validation.constraints.NotNull(message="").append(this.getColumnLabel()).append("不能为空")").toString());	
                }	
            }	
            if ("Strig".equals(this.getAttrType()) && !"0".equals(this.getDataLength()) && !"-1".equals(this.getDataLength())) {	
                a.add(new StringBuilder().insert(0, "org.hibernate.validator.costraits.Legth(min=0, max=").append(this.getDataLength()).append(", message="").append(this.getColumnLabel()).append("长度不能超过 ").append(this.getDataLength()).append(" 个字符")").toString());	
            }	
        }	
        return a;	
    }	
	
    public Integer getColumnSort() {	
        return this.columnSort;	
    }	
	
    public String getAttrType() {	
        return this.attrType;	
    }	
	
    public String getSimpleAttrName() {	
        if (StringUtils.contains((CharSequence)this.getFullAttrName(), ".")) {	
            return StringUtils.substringBefore(this.getFullAttrName(), ".");	
        }	
        return StringUtils.substringBefore(this.getFullAttrName(), "|");	
    }	
	
    @JsonIgnore	
    public Boolean getIsTreeEntityColumn() {	
        return this.getIsEntityColumn(TreeEntity.class);	
    }	
	
    public void setIsEdit(String isEdit) {	
        this.isEdit = isEdit;	
    }	
	
    public void setQueryType(String queryType) {	
        this.queryType = queryType;	
    }	
	
    @JsonIgnore	
    public Boolean getIsDataEntityColumn() {	
        return this.getIsEntityColumn(DataEntity.class);	
    }	
	
    public void setAttrType(String attrType) {	
        this.attrType = attrType;	
    }	
	
    public void setOptions(String options) {	
        if (StringUtils.isNotBlank(options)) {	
            this.optionMap = (Map)JsonMapper.fromJson(options, Map.class);	
        }	
        this.options = options;	
    }	
	
    public String getIsQuery() {	
        return this.isQuery;	
    }	
	
    public GenTableColumn() {	
    }	
	
    public String getQueryType() {	
        return this.queryType;	
    }	
	
    public void setFullAttrName(String attrName) {	
        this.attrName = attrName;	
    }	
	
    public String getFullAttrName() {	
        return this.attrName;	
    }	
	
    public String getIsList() {	
        return this.isList;	
    }	
	
    public Map<String, Object> getOptionMap() {	
        if (this.optionMap == null) {	
            this.optionMap = MapUtils.newHashMap();	
        }	
        return this.optionMap;	
    }	
	
    public void setIsNull(String isNull) {	
        this.isNull = isNull;	
    }	
	
    @JsonIgnore	
    public Boolean getIsExtendColumn() {	
        return this.getIsEntityColumn(Extend.class);	
    }	
	
    public void setColumnLabel(String columnLabel) {	
        this.columnLabel = columnLabel;	
    }	
	
    @JsonIgnore	
    public Boolean getIsBaseEntityColumn() {	
        return this.getIsEntityColumn(BaseEntity.class);	
    }	
}	
	
