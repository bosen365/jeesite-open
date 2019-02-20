/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
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
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.sys.entity.User;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcState;	
import org.hyperic.sigar.pager.PageList;	
	
@Table(name="${_prefix}gen_table_column", alias="a", columns={@Column(name="id", attrName="id", label="\u5217\u7f16\u53f7", isPK=true), @Column(name="table_name", attrName="genTable.tableName", label="\u8868\u540d"), @Column(name="column_name", attrName="columnName", label="\u5217\u540d"), @Column(name="column_sort", attrName="columnSort", label="\u5217\u6392\u5e8f", comment="\u5217\u6392\u5e8f\uff08\u5347\u5e8f\uff09"), @Column(name="column_type", attrName="columnType", label="\u5217\u7c7b\u578b"), @Column(name="column_label", attrName="columnLabel", label="\u5217\u6807\u7b7e\u540d"), @Column(name="comments", attrName="comments", label="\u8868\u8bf4\u660e"), @Column(name="attr_name", attrName="fullAttrName", label="\u7c7b\u7684\u5c5e\u6027\u540d"), @Column(name="attr_type", attrName="attrType", label="\u7c7b\u7684\u5c5e\u6027\u7c7b\u578b"), @Column(name="is_pk", attrName="isPk", label="\u662f\u5426\u4e3b\u952e"), @Column(name="is_null", attrName="isNull", label="\u662f\u53ef\u4e3a\u7a7a"), @Column(name="is_insert", attrName="isInsert", label="\u662f\u5426\u63d2\u5165\u5b57\u6bb5"), @Column(name="is_update", attrName="isUpdate", label="\u662f\u5426\u66f4\u65b0\u5b57\u6bb5"), @Column(name="is_list", attrName="isList", label="\u662f\u5426\u5217\u8868\u5b57\u5178"), @Column(name="is_query", attrName="isQuery", label="\u662f\u5426\u67e5\u8be2\u5b57\u6bb5"), @Column(name="query_type", attrName="queryType", label="\u67e5\u8be2\u65b9\u5f0f"), @Column(name="is_edit", attrName="isEdit", label="\u662f\u5426\u7f16\u8f91\u5b57\u6bb5"), @Column(name="show_type", attrName="showType", label="\u5b57\u6bb5\u663e\u793a\u65b9\u5f0f"), @Column(name="options", attrName="options", label="\u5176\u5b83\u751f\u6210\u9009\u9879")}, orderBy="a.column_sort")	
public class GenTableColumn	
extends DataEntity<GenTableColumn> {	
    private String columnType;	
    private String showType;	
    private String isNull;	
    private String isPk;	
    private String options;	
    private String isInsert;	
    private String columnName;	
    private static final long serialVersionUID = 1L;	
    private Integer columnSort;	
    private String attrName;	
    private Map<String, Object> optionMap;	
    private String comments;	
    private String attrType;	
    private String isEdit;	
    private String isQuery;	
    private String isUpdate;	
    private String queryType;	
    private String isList;	
    private GenTable genTable;	
    private String columnLabel;	
	
    public String getAttrName() {	
        String a2 = StringUtils.substringBefore((String)this.getFullAttrName(), (String)"|");	
        if (User.class.getName().equals(this.getAttrType())) {	
            a2 = new StringBuilder().insert(0, a2).append("DuserCode").toString();	
            return a2;	
        }	
        if ("com.jeesite.modules.sys.entity.Office".equals(this.getAttrType())) {	
            a2 = new StringBuilder().insert(0, a2).append("DofficeCode").toString();	
        }	
        return a2;	
    }	
	
    public GenTableColumn(String id) {	
        super(id);	
    }	
	
    public String getShowType() {	
        return this.showType;	
    }	
	
    public GenTableColumn(GenTable genTable) {	
        this.genTable = genTable;	
    }	
	
    public void setIsRequired(String isRequired) {	
        this.isNull = "1".equals(isRequired) ? "0" : "1";	
    }	
	
    public void setIsNull(String isNull) {	
        this.isNull = isNull;	
    }	
	
    public void setColumnSort(Integer columnSort) {	
        this.columnSort = columnSort;	
    }	
	
    public String getFullAttrName() {	
        return this.attrName;	
    }	
	
    public void setComments(String comments) {	
        this.comments = comments;	
    }	
	
    public void setGenTable(GenTable genTable) {	
        this.genTable = genTable;	
    }	
	
    public String getIsRequired() {	
        if ("1".equals(this.isNull)) {	
            return "0";	
        }	
        return "1";	
    }	
	
    public void setColumnName(String columnName) {	
        this.columnName = columnName;	
    }	
	
    public String getIsList() {	
        return this.isList;	
    }	
	
    public String getIsQuery() {	
        return this.isQuery;	
    }	
	
    public String getIsPk() {	
        return this.isPk;	
    }	
	
    public String getAttrType() {	
        return this.attrType;	
    }	
	
    @JsonIgnore	
    public Boolean getIsBaseEntityColumn() {	
        return this.getIsEntityColumn(BaseEntity.class);	
    }	
	
    public String getAttrName2() {	
        if (User.class.getName().equals(this.getAttrType())) {	
            return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".userName").toString();	
        }	
        if ("com.jeesite.modules.sys.entityDOffice".equals(this.getAttrType())) {	
            return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".officeName").toString();	
        }	
        String[] a2 = this.getAttrNames();	
        if (a2.length > 0) {	
            if (StringUtils.contains((CharSequence)this.getFullAttrName(), (CharSequence)".")) {	
                return new StringBuilder().insert(0, this.getSimpleAttrName()).append(".").append(a2[0]).toString();	
            }	
            return a2[0];	
        }	
        return "";	
    }	
	
    @JsonIgnore	
    public List<String> getAnnotationList() {	
        ArrayList a2 = ListUtils.newArrayList();	
        if ("This".equals(this.getAttrType())) {	
            a2.add("com.fasterxml.jackson.annotation.JsonBackReference");	
        }	
        if ("java.utilDDate".equals(this.getAttrType())) {	
            a2.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")");	
        }	
        if (!"1".equals(this.getIsPk()) && !"hidden".equals(this.getShowType())) {	
            if (!"1".equals(this.getIsNull())) {	
                if ("String".equals(this.getAttrType())) {	
                    a2.add("javax.validationDconstraintsDNotBlank(message="" + this.getColumnLabel() + "不能为空")");	
                } else {	
                    a2.add(new StringBuilder().insert(0, "javaxDvalidation.constraints.NotNull(message="").append(this.getColumnLabel()).append("不能为空")").toString());	
                }	
            }	
            if ("String".equals(this.getAttrType()) && !"0".equals(this.getDataLength()) && !"-1".equals(this.getDataLength())) {	
                a2.add(new StringBuilder().insert(0, "orgDhibernateDvalidatorDconstraintsDLength(min=0, max=").append(this.getDataLength()).append(", message="").append(this.getColumnLabel()).append("长度不能超过 ").append(this.getDataLength()).append(" 个字符")").toString());	
            }	
        }	
        return a2;	
    }	
	
    public String getComments() {	
        if (StringUtils.isBlank((CharSequence)this.comments)) {	
            return this.getColumnName();	
        }	
        return this.comments;	
    }	
	
    @JsonIgnore	
    public Boolean getIsDataEntityColumn() {	
        return this.getIsEntityColumn(DataEntity.class);	
    }	
	
    public void setOptionMap(Map<String, Object> optionMap) {	
        this.optionMap = optionMap;	
    }	
	
    public String getIsNull() {	
        return this.isNull;	
    }	
	
    @NotBlank(message="\u5217\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u5217\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getColumnName() {	
        return StringUtils.lowerCase((String)this.columnName);	
    }	
	
    @JsonIgnore	
    public String getOptions() {	
        if (this.optionMap != null) {	
            this.options = JsonMapper.toJson(this.optionMap);	
        }	
        return this.options;	
    }	
	
    public void setIsInsert(String isInsert) {	
        this.isInsert = isInsert;	
    }	
	
    @JsonIgnore	
    public List<String> getSimpleAnnotationList() {	
        Iterator<String> iterator;	
        ArrayList a2 = ListUtils.newArrayList();	
        Iterator<String> iterator2 = iterator = this.getAnnotationList().iterator();	
        while (iterator2.hasNext()) {	
            String a3 = iterator.next();	
            iterator2 = iterator;	
            a2.add(StringUtils.substringAfterLast((String)a3, (String)"."));	
        }	
        return a2;	
    }	
	
    public void setOptions(String options) {	
        if (StringUtils.isNotBlank((CharSequence)options)) {	
            this.optionMap = (Map)JsonMapper.fromJson((String)options, Map.class);	
        }	
        this.options = options;	
    }	
	
    public void setIsPk(String isPk) {	
        this.isPk = isPk;	
    }	
	
    public String getIsUpdate() {	
        return this.isUpdate;	
    }	
	
    public void setShowType(String showType) {	
        this.showType = showType;	
    }	
	
    public void setIsEdit(String isEdit) {	
        this.isEdit = isEdit;	
    }	
	
    public void setFullAttrName(String attrName) {	
        this.attrName = attrName;	
    }	
	
    public String getQueryType() {	
        return this.queryType;	
    }	
	
    public String getSimpleAttrName() {	
        if (StringUtils.contains((CharSequence)this.getFullAttrName(), (CharSequence)".")) {	
            return StringUtils.substringBefore((String)this.getFullAttrName(), (String)".");	
        }	
        return StringUtils.substringBefore((String)this.getFullAttrName(), (String)"|");	
    }	
	
    public void setAttrType(String attrType) {	
        this.attrType = attrType;	
    }	
	
    public void setIsQuery(String isQuery) {	
        this.isQuery = isQuery;	
    }	
	
    public GenTableColumn() {	
    }	
	
    public Map<String, Object> getOptionMap() {	
        if (this.optionMap == null) {	
            this.optionMap = MapUtils.newHashMap();	
        }	
        return this.optionMap;	
    }	
	
    public String getAttrNameForGetMethod() {	
        int a2;	
        Object[] a3 = StringUtils.split((String)this.getAttrName(), (String)".");	
        int n = a2 = 0;	
        while (n < a3.length) {	
            int n2 = a2;	
            Object[] arrobject = a3;	
            String string = "get" + StringUtils.cap((String)arrobject[a2]) + "()";	
            arrobject[n2] = string;	
            n = ++a2;	
        }	
        return StringUtils.join((Object[])a3, (String)".");	
    }	
	
    @JsonIgnore	
    public Boolean getIsExtendColumn() {	
        return this.getIsEntityColumn(Extend.class);	
    }	
	
    public void setQueryType(String queryType) {	
        this.queryType = queryType;	
    }	
	
    public void setColumnType(String columnType) {	
        this.columnType = columnType;	
    }	
	
    private /* synthetic */ Boolean getIsEntityColumn(Class<?> entityClass) {	
        int n;	
        String a2 = this.getColumnName();	
        Column[] arrcolumn = MapperHelper.getTable(entityClass).columns();	
        int n2 = arrcolumn.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            if (arrcolumn[n].name().equals(a2)) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    @JsonIgnore	
    public Boolean getIsTreeEntityColumn() {	
        return this.getIsEntityColumn(TreeEntity.class);	
    }	
	
    public void setIsUpdate(String isUpdate) {	
        this.isUpdate = isUpdate;	
    }	
	
    public String getColumnType() {	
        return StringUtils.lowerCase((String)this.columnType);	
    }	
	
    @JsonIgnore	
    public GenTable getGenTable() {	
        return this.genTable;	
    }	
	
    public String getColumnNameAndComments() {	
        return new StringBuilder().insert(0, this.getColumnName()).append(this.comments == null ? "" : new StringBuilder().insert(0, "  :  ").append(this.comments).toString()).toString();	
    }	
	
    public Integer getColumnSort() {	
        return this.columnSort;	
    }	
	
    public String[] getAttrNames() {	
        String[] a2 = StringUtils.split((String)StringUtils.substringAfter((String)this.getFullAttrName(), (String)"|"), (String)"|");	
        String[] a3 = new String[a2.length];	
        if (a2 != null) {	
            int a4;	
            int n = a4 = 0;	
            while (n < a2.length) {	
                int n2 = a4++;	
                a3[n2] = a2[n2];	
                n = a4;	
            }	
        }	
        return a3;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     */	
    public String getColumnLabel() {	
        GenTableColumn genTableColumn;	
        if (this.getComments() != null) {	
            if (StringUtils.contains((CharSequence)this.getComments(), (CharSequence)"(")) {	
                GenTableColumn genTableColumn2 = this;	
                genTableColumn = genTableColumn2;	
                genTableColumn2.columnLabel = StringUtils.substringBefore((String)genTableColumn2.getComments(), (String)"(");	
                return genTableColumn.columnLabel;	
            }	
            if (StringUtils.contains((CharSequence)this.getComments(), (CharSequence)"（")) {	
                GenTableColumn genTableColumn3 = this;	
                genTableColumn = genTableColumn3;	
                genTableColumn3.columnLabel = StringUtils.substringBefore((String)genTableColumn3.getComments(), (String)"（");	
                return genTableColumn.columnLabel;	
            }	
            if (StringUtils.contains((CharSequence)this.getComments(), (CharSequence)":")) {	
                this.columnLabel = StringUtils.substringBefore((String)this.getComments(), (String)":");	
                genTableColumn = this;	
                return genTableColumn.columnLabel;	
            }	
            GenTableColumn genTableColumn4 = this;	
            if (StringUtils.contains((CharSequence)this.getComments(), (CharSequence)"：")) {	
                genTableColumn4.columnLabel = StringUtils.substringBefore((String)this.getComments(), (String)"：");	
                genTableColumn = this;	
                return genTableColumn.columnLabel;	
            }	
            genTableColumn4.columnLabel = this.getComments();	
        }	
        genTableColumn = this;	
        return genTableColumn.columnLabel;	
    }	
	
    @JsonIgnore	
    public Boolean getIsSuperColumn() {	
        return this.getIsBaseEntityColumn() != false || this.getIsDataEntityColumn() != false || this.getIsTreeEntityColumn() != false;	
    }	
	
    public String getIsInsert() {	
        return this.isInsert;	
    }	
	
    public void setColumnLabel(String columnLabel) {	
        this.columnLabel = columnLabel;	
    }	
	
    public String getIsEdit() {	
        return this.isEdit;	
    }	
	
    public String getSimpleAttrType() {	
        if (this.genTable != null && "This".equals(this.getAttrType())) {	
            return StringUtils.capitalize((String)this.genTable.getClassName());	
        }	
        if (StringUtils.indexOf((CharSequence)this.getAttrType(), (CharSequence)".") != -1) {	
            return StringUtils.substringAfterLast((String)this.getAttrType(), (String)".");	
        }	
        return this.getAttrType();	
    }	
	
    public String getDataLength() {	
        String[] a2 = StringUtils.split((String)StringUtils.substringBetween((String)this.getColumnType(), (String)"(", (String)")"), (String)",");	
        if (a2 != null && a2.length == 1) {	
            return a2[0];	
        }	
        return "0";	
    }	
	
    public void setIsList(String isList) {	
        this.isList = isList;	
    }	
}	
	
