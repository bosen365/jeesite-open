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
import com.jeesite.modules.sys.entity.User;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcState;	
import org.hyperic.sigar.pager.PageList;	
	
@Table(	
   name = "${_prefix}gen_table_column",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "列编号",	
   isPK = true	
), @Column(	
   name = "table_name",	
   attrName = "genTable.tableName",	
   label = "表名"	
), @Column(	
   name = "column_name",	
   attrName = "columnName",	
   label = "列名"	
), @Column(	
   name = "column_sort",	
   attrName = "columnSort",	
   label = "列排序",	
   comment = "列排序（升序）"	
), @Column(	
   name = "column_type",	
   attrName = "columnType",	
   label = "列类型"	
), @Column(	
   name = "column_label",	
   attrName = "columnLabel",	
   label = "列标签名"	
), @Column(	
   name = "comments",	
   attrName = "comments",	
   label = "表说明"	
), @Column(	
   name = "attr_name",	
   attrName = "fullAttrName",	
   label = "类的属性名"	
), @Column(	
   name = "attr_type",	
   attrName = "attrType",	
   label = "类的属性类型"	
), @Column(	
   name = "is_pk",	
   attrName = "isPk",	
   label = "是否主键"	
), @Column(	
   name = "is_null",	
   attrName = "isNull",	
   label = "是可为空"	
), @Column(	
   name = "is_insert",	
   attrName = "isInsert",	
   label = "是否插入字段"	
), @Column(	
   name = "is_update",	
   attrName = "isUpdate",	
   label = "是否更新字段"	
), @Column(	
   name = "is_list",	
   attrName = "isList",	
   label = "是否列表字典"	
), @Column(	
   name = "is_query",	
   attrName = "isQuery",	
   label = "是否查询字段"	
), @Column(	
   name = "query_type",	
   attrName = "queryType",	
   label = "查询方式"	
), @Column(	
   name = "is_edit",	
   attrName = "isEdit",	
   label = "是否编辑字段"	
), @Column(	
   name = "show_type",	
   attrName = "showType",	
   label = "字段显示方式"	
), @Column(	
   name = "options",	
   attrName = "options",	
   label = "其它生成选项"	
)},	
   orderBy = "a.column_sort"	
)	
public class GenTableColumn extends DataEntity {	
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
   private Map optionMap;	
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
      String a = StringUtils.substringBefore(this.getFullAttrName(), "|");	
      if (User.class.getName().equals(this.getAttrType())) {	
         return (new StringBuilder()).insert(0, a).append("DuserCode").toString();	
      } else {	
         if ("com.jeesite.modules.sys.entity.Office".equals(this.getAttrType())) {	
            a = (new StringBuilder()).insert(0, a).append("DofficeCode").toString();	
         }	
	
         return a;	
      }	
   }	
	
   public GenTableColumn(String id) {	
      super(id);	
   }	
	
   public String getShowType() {	
      return this.showType;	
   }	
	
   public GenTableColumn(GenTable var1) {	
      this.genTable = var1;	
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
      return "1".equals(this.isNull) ? "0" : "1";	
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
         return (new StringBuilder()).insert(0, this.getSimpleAttrName()).append(".userName").toString();	
      } else if ("com.jeesite.modules.sys.entityDOffice".equals(this.getAttrType())) {	
         return (new StringBuilder()).insert(0, this.getSimpleAttrName()).append(".officeName").toString();	
      } else {	
         String[] a;	
         if ((a = this.getAttrNames()).length > 0) {	
            return StringUtils.contains(this.getFullAttrName(), ".") ? (new StringBuilder()).insert(0, this.getSimpleAttrName()).append(".").append(a[0]).toString() : a[0];	
         } else {	
            return "";	
         }	
      }	
   }	
	
   @JsonIgnore	
   public List getAnnotationList() {	
      List a = ListUtils.newArrayList();	
      if ("This".equals(this.getAttrType())) {	
         a.add("com.fasterxml.jackson.annotation.JsonBackReference");	
      }	
	
      if ("java.utilDDate".equals(this.getAttrType())) {	
         a.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")");	
      }	
	
      if (!"1".equals(this.getIsPk()) && !"hidden".equals(this.getShowType())) {	
         if (!"1".equals(this.getIsNull())) {	
            if ("String".equals(this.getAttrType())) {	
               a.add("javax.validationDconstraintsDNotBlank(message="" + this.getColumnLabel() + "不能为空")");	
            } else {	
               a.add((new StringBuilder()).insert(0, "javaxDvalidation.constraints.NotNull(message="").append(this.getColumnLabel()).append("不能为空")").toString());	
            }	
         }	
	
         if ("String".equals(this.getAttrType()) && !"0".equals(this.getDataLength()) && !"-1".equals(this.getDataLength())) {	
            a.add((new StringBuilder()).insert(0, "orgDhibernateDvalidatorDconstraintsDLength(min=0, max=").append(this.getDataLength()).append(", message="").append(this.getColumnLabel()).append("长度不能超过 ").append(this.getDataLength()).append(" 个字符")").toString());	
         }	
      }	
	
      return a;	
   }	
	
   public String getComments() {	
      return StringUtils.isBlank(this.comments) ? this.getColumnName() : this.comments;	
   }	
	
   @JsonIgnore	
   public Boolean getIsDataEntityColumn() {	
      return this.getIsEntityColumn(DataEntity.class);	
   }	
	
   public void setOptionMap(Map optionMap) {	
      this.optionMap = optionMap;	
   }	
	
   public String getIsNull() {	
      return this.isNull;	
   }	
	
   @NotBlank(	
      message = "列名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "列名长度不能超过 64 个字符"	
   )	
   public String getColumnName() {	
      return StringUtils.lowerCase(this.columnName);	
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
   public List getSimpleAnnotationList() {	
      List a = ListUtils.newArrayList();	
      Iterator var2;	
      Iterator var10000 = var2 = this.getAnnotationList().iterator();	
	
      while(var10000.hasNext()) {	
         String a = (String)var2.next();	
         var10000 = var2;	
         a.add(StringUtils.substringAfterLast(a, "."));	
      }	
	
      return a;	
   }	
	
   public void setOptions(String options) {	
      if (StringUtils.isNotBlank(options)) {	
         this.optionMap = (Map)JsonMapper.fromJson(options, Map.class);	
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
      return StringUtils.contains(this.getFullAttrName(), ".") ? StringUtils.substringBefore(this.getFullAttrName(), ".") : StringUtils.substringBefore(this.getFullAttrName(), "|");	
   }	
	
   public void setAttrType(String attrType) {	
      this.attrType = attrType;	
   }	
	
   public void setIsQuery(String isQuery) {	
      this.isQuery = isQuery;	
   }	
	
   public GenTableColumn() {	
   }	
	
   public Map getOptionMap() {	
      if (this.optionMap == null) {	
         this.optionMap = MapUtils.newHashMap();	
      }	
	
      return this.optionMap;	
   }	
	
   public String getAttrNameForGetMethod() {	
      String[] a = StringUtils.split(this.getAttrName(), ".");	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.length; var10000 = a) {	
         StringBuilder var10002 = (new StringBuilder()).append("get");	
         int var10001 = a;	
         String var3 = var10002.append(StringUtils.cap(a[a])).append("()").toString();	
         ++a;	
         a[var10001] = var3;	
      }	
	
      return StringUtils.join(a, ".");	
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
	
   // $FF: synthetic method	
   private Boolean getIsEntityColumn(Class entityClass) {	
      String a = this.getColumnName();	
      Column[] var4;	
      int var5 = (var4 = MapperHelper.getTable(entityClass).columns()).length;	
	
      int var6;	
      for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
         if (var4[var6].name().equals(a)) {	
            return true;	
         }	
	
         ++var6;	
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
      return StringUtils.lowerCase(this.columnType);	
   }	
	
   @JsonIgnore	
   public GenTable getGenTable() {	
      return this.genTable;	
   }	
	
   public String getColumnNameAndComments() {	
      return (new StringBuilder()).insert(0, this.getColumnName()).append(this.comments == null ? "" : (new StringBuilder()).insert(0, "  :  ").append(this.comments).toString()).toString();	
   }	
	
   public Integer getColumnSort() {	
      return this.columnSort;	
   }	
	
   public String[] getAttrNames() {	
      String[] a;	
      String[] var10000 = new String[(a = StringUtils.split(StringUtils.substringAfter(this.getFullAttrName(), "|"), "|")).length];	
      boolean var10002 = true;	
      String[] a = var10000;	
      int a;	
      if (a != null) {	
         for(int var4 = a = 0; var4 < a.length; var4 = a) {	
            int var10001 = a;	
            String var5 = a[a];	
            ++a;	
            a[var10001] = var5;	
         }	
      }	
	
      return a;	
   }	
	
   public String getColumnLabel() {	
      GenTableColumn var10000;	
      if (this.getComments() != null) {	
         if (StringUtils.contains(this.getComments(), "(")) {	
            var10000 = this;	
            this.columnLabel = StringUtils.substringBefore(this.getComments(), "(");	
            return var10000.columnLabel;	
         }	
	
         if (StringUtils.contains(this.getComments(), "（")) {	
            var10000 = this;	
            this.columnLabel = StringUtils.substringBefore(this.getComments(), "（");	
            return var10000.columnLabel;	
         }	
	
         if (StringUtils.contains(this.getComments(), ":")) {	
            this.columnLabel = StringUtils.substringBefore(this.getComments(), ":");	
            var10000 = this;	
            return var10000.columnLabel;	
         }	
	
         if (StringUtils.contains(this.getComments(), "：")) {	
            this.columnLabel = StringUtils.substringBefore(this.getComments(), "：");	
            var10000 = this;	
            return var10000.columnLabel;	
         }	
	
         this.columnLabel = this.getComments();	
      }	
	
      var10000 = this;	
      return var10000.columnLabel;	
   }	
	
   @JsonIgnore	
   public Boolean getIsSuperColumn() {	
      return this.getIsBaseEntityColumn() || this.getIsDataEntityColumn() || this.getIsTreeEntityColumn();	
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
         return StringUtils.capitalize(this.genTable.getClassName());	
      } else {	
         return StringUtils.indexOf(this.getAttrType(), ".") != -1 ? StringUtils.substringAfterLast(this.getAttrType(), ".") : this.getAttrType();	
      }	
   }	
	
   public String getDataLength() {	
      String[] a;	
      return (a = StringUtils.split(StringUtils.substringBetween(this.getColumnType(), "(", ")"), ",")) != null && a.length == 1 ? a[0] : "0";	
   }	
	
   public void setIsList(String isList) {	
      this.isList = isList;	
   }	
}	
