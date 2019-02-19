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
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   name = "${_prefix}gen_table",	
   alias = "a",	
   columns = {@Column(	
   name = "table_name",	
   attrName = "tableName",	
   label = "表名",	
   isPK = true	
), @Column(	
   name = "class_name",	
   attrName = "className",	
   label = "实体类名称"	
), @Column(	
   name = "comments",	
   attrName = "comments",	
   label = "表说明",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "parent_table_name",	
   attrName = "parentTableName",	
   label = "关联父表的表名"	
), @Column(	
   name = "parent_table_fk_name",	
   attrName = "parentTableFkName",	
   label = "本表关联父表的外键名"	
), @Column(	
   name = "data_source_name",	
   attrName = "dataSourceName",	
   label = "数据源名称"	
), @Column(	
   name = "tpl_category",	
   attrName = "tplCategory",	
   label = "生成模板分类"	
), @Column(	
   name = "package_name",	
   attrName = "packageName",	
   label = "生成包路径"	
), @Column(	
   name = "module_name",	
   attrName = "moduleName",	
   label = "生成模块名"	
), @Column(	
   name = "sub_module_name",	
   attrName = "subModuleName",	
   label = "生成子模块名"	
), @Column(	
   name = "function_name",	
   attrName = "functionName",	
   label = "生成功能名"	
), @Column(	
   name = "function_name_simple",	
   attrName = "functionNameSimple",	
   label = "生成功能名（简写）"	
), @Column(	
   name = "function_author",	
   attrName = "functionAuthor",	
   label = "生成功能作者"	
), @Column(	
   name = "gen_base_dir",	
   attrName = "genBaseDir",	
   label = "生成的基础目录"	
), @Column(	
   name = "options",	
   attrName = "options",	
   label = "其它生成选项",	
   isQuery = false	
), @Column(	
   name = "create_by",	
   attrName = "createBy",	
   label = "创建者",	
   isUpdate = false	
), @Column(	
   name = "create_date",	
   attrName = "createDate",	
   label = "创建时间",	
   isUpdate = false,	
   isQuery = false	
), @Column(	
   name = "update_by",	
   attrName = "updateBy",	
   label = "更新者",	
   isUpdate = true	
), @Column(	
   name = "update_date",	
   attrName = "updateDate",	
   label = "更新时间",	
   isUpdate = true,	
   isQuery = false	
), @Column(	
   name = "remarks",	
   attrName = "remarks",	
   label = "备注信息",	
   queryType = QueryType.LIKE	
)},	
   extColumnKeys = "extColumn",	
   orderBy = "a.update_date DESC"	
)	
public class GenTable extends DataEntity {	
   private Boolean isBaseEntity;	
   private List columnList = ListUtils.newArrayList();	
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
   private Map optionMap;	
   private List pkList;	
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
   private List childList = ListUtils.newArrayList();	
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
	
   public void setOptionMap(Map optionMap) {	
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
      Iterator var1 = this.columnList.iterator();	
	
      GenTableColumn a;	
      do {	
         if (!var1.hasNext()) {	
            return false;	
         }	
	
         a = (GenTableColumn)var1.next();	
      } while(!"create_date".equals(a.getColumnName()));	
	
      return true;	
   }	
	
   public void setChildList(List childList) {	
      this.childList = childList;	
   }	
	
   public String getGenTableName() {	
      String a;	
      if ((a = this.getTableName()) != null && StringUtils.startsWith(a, Global.getTablePrefix())) {	
         a = (new StringBuilder()).insert(0, "${_prefix}").append(StringUtils.substringAfter(a, Global.getTablePrefix())).toString();	
      }	
	
      return a;	
   }	
	
   public Boolean getIsPkCustom() {	
      Iterator var1 = this.pkList.iterator();	
	
      GenTableColumn a;	
      do {	
         if (!var1.hasNext()) {	
            return false;	
         }	
	
         a = (GenTableColumn)var1.next();	
      } while("hidden".equals(a.getShowType()));	
	
      return true;	
   }	
	
   public String getSubModuleName() {	
      return this.subModuleName;	
   }	
	
   public void setOptions(String options) {	
      if (StringUtils.isNotBlank(options)) {	
         this.optionMap = (Map)JsonMapper.fromJson(options, Map.class);	
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
      Iterator var1 = this.columnList.iterator();	
	
      GenTableColumn a;	
      do {	
         if (!var1.hasNext()) {	
            return false;	
         }	
	
         a = (GenTableColumn)var1.next();	
      } while(!"update_date".equals(a.getColumnName()));	
	
      return true;	
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
	
   // $FF: synthetic method	
   private Boolean getIsEntityClass(Class entityClass) {	
      Column[] var3;	
      int var4 = (var3 = MapperHelper.getTable(entityClass).columns()).length;	
	
      int var5;	
      for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
         Column a = var3[var5];	
         int a = false;	
         Iterator var8 = this.columnList.iterator();	
	
         boolean var10;	
         while(true) {	
            if (!var8.hasNext()) {	
               var10 = a;	
               break;	
            }	
	
            GenTableColumn a = (GenTableColumn)var8.next();	
            if (a.name().equals(a.getColumnName())) {	
               var10 = a = true;	
               break;	
            }	
         }	
	
         if (!var10) {	
            return false;	
         }	
	
         ++var5;	
      }	
	
      return true;	
   }	
	
   public String getGenFlag() {	
      return this.genFlag;	
   }	
	
   @NotBlank(	
      message = "表说明不能为空"	
   )	
   public String getComments() {	
      return StringUtils.isBlank(this.comments) ? this.getTableName() : this.comments;	
   }	
	
   public String getFunctionName() {	
      return this.functionName;	
   }	
	
   public void setParentTableName_isNull(String parentTableName) {	
      if (StringUtils.isBlank(parentTableName)) {	
         this.sqlMap.getWhere().andBracket("parent_table_name", QueryType.IS_NULL, (Object)null, 2).or("parent_table_name", QueryType.EQ_FORCE, "", 3).endBracket();	
         this.setParentTableName((String)null);	
      } else {	
         this.setParentTableName(parentTableName);	
      }	
   }	
	
   public void setClassName(String className) {	
      this.className = className;	
   }	
	
   @NotBlank(	
      message = "功能作者不能为空"	
   )	
   public String getFunctionAuthor() {	
      return this.functionAuthor;	
   }	
	
   public String getParentTableName_isNull() {	
      return this.getParentTableName();	
   }	
	
   public List getChildList() {	
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
	
   public List getImportList() {	
      List a = ListUtils.newArrayList();	
      Iterator var2 = this.getColumnList().iterator();	
	
      while(true) {	
         while(var2.hasNext()) {	
            GenTableColumn a;	
            if (!(a = (GenTableColumn)var2.next()).getIsSuperColumn()) {	
               if (StringUtils.indexOf(a.getAttrType(), ".") != -1 && !a.contains(a.getAttrType())) {	
                  if (!a.contains(a.getAttrType())) {	
                     a.add(a.getAttrType());	
                  }	
	
                  if (!a.contains(JoinTable.class.getName())) {	
                     a.add(JoinTable.class.getName());	
                  }	
	
                  if (!a.contains((new StringBuilder()).insert(0, JoinTable.class.getName()).append(".Type").toString())) {	
                     a.add(JoinTable.class.getName() + ".Type");	
                  }	
               }	
	
               if (a.getIsExtendColumn() && !a.contains(Extend.class.getName())) {	
                  a.add(Extend.class.getName());	
               }	
	
               Iterator var4 = a.getAnnotationList().iterator();	
	
               while(var4.hasNext()) {	
                  String a = (String)var4.next();	
                  if (!a.contains(StringUtils.substringBefore(a, "("))) {	
                     a.add(StringUtils.substringBefore(a, "("));	
                  }	
               }	
            } else if (a.getIsBaseEntityColumn() && !a.contains(BaseEntity.class.getName())) {	
               a.add(BaseEntity.class.getName());	
            }	
         }	
	
         if (this.getChildList() != null && this.getChildList().size() > 0) {	
            if (!a.contains("java.util.List")) {	
               a.add("java.util.List");	
            }	
	
            if (!a.contains("com.jeeste.common.collect.LstUtils")) {	
               a.add("\tom.jeesite.\tommon.colle\tt.ListUtils");	
            }	
         }	
	
         return a;	
      }	
   }	
	
   public Boolean getStatusExists() {	
      Iterator var1 = this.columnList.iterator();	
	
      GenTableColumn a;	
      do {	
         if (!var1.hasNext()) {	
            return false;	
         }	
	
         a = (GenTableColumn)var1.next();	
      } while(!"status".equals(a.getColumnName()));	
	
      return true;	
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
      return StringUtils.isNotBlank(this.parentTableName) && StringUtils.isNotBlank(this.parentTableFkName);	
   }	
	
   public void setParentTableFkName(String parentTableFkName) {	
      this.parentTableFkName = parentTableFkName;	
   }	
	
   public String getParentTableFkName() {	
      return StringUtils.lowerCase(this.parentTableFkName);	
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
	
   public List getColumnList() {	
      return this.columnList;	
   }	
	
   public String getTreeViewNameAttrName() {	
      String a;	
      if (this.getIsTreeEntity() && this.treeViewNameAttrName == null && StringUtils.isNotBlank(a = (String)this.optionMap.get("treeViewName"))) {	
         this.treeViewNameAttrName = this.getColumn(a).getAttrName();	
      }	
	
      return this.treeViewNameAttrName;	
   }	
	
   @NotBlank(	
      message = "实体类名不能为空"	
   )	
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
      String a;	
      if (this.getIsTreeEntity() && this.treeViewCodeAttrName == null && StringUtils.isNotBlank(a = (String)this.optionMap.get("treeViewCode"))) {	
         this.treeViewCodeAttrName = this.getColumn(a).getAttrName();	
      }	
	
      return this.treeViewCodeAttrName;	
   }	
	
   public String getGenBaseDir() {	
      return this.genBaseDir;	
   }	
	
   public void setTableName_like(String tableName) {	
      this.sqlMap.getWhere().and("table_name", QueryType.LIKE, tableName);	
   }	
	
   public void setColumnList(List columnList) {	
      this.columnList = columnList;	
   }	
	
   public GenTable(String id) {	
      super(id);	
   }	
	
   public GenTable() {	
   }	
	
   public String getParentTableName() {	
      return StringUtils.lowerCase(this.parentTableName);	
   }	
	
   public List getPkList() {	
      if (this.pkList == null) {	
         this.pkList = ListUtils.newArrayList();	
         Iterator var1 = this.columnList.iterator();	
	
         while(var1.hasNext()) {	
            GenTableColumn a = (GenTableColumn)var1.next();	
            if ("1".equals(a.getIsPk())) {	
               this.pkList.add(a);	
            }	
         }	
      }	
	
      return this.pkList;	
   }	
	
   public String getTableNameAndComments() {	
      return (new StringBuilder()).insert(0, this.getTableName()).append(this.comments == null ? "" : (new StringBuilder()).insert(0, "  :  ").append(this.comments).toString()).toString();	
   }	
	
   public GenTableColumn getColumn(String columnName) {	
      Iterator var2 = this.columnList.iterator();	
	
      GenTableColumn a;	
      do {	
         if (!var2.hasNext()) {	
            return new GenTableColumn();	
         }	
      } while(!(a = (GenTableColumn)var2.next()).getColumnName().equals(columnName));	
	
      return a;	
   }	
	
   public Map getOptionMap() {	
      if (this.optionMap == null) {	
         this.optionMap = MapUtils.newHashMap();	
      }	
	
      return this.optionMap;	
   }	
	
   public void setPkList(List pkList) {	
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
	
   @NotBlank(	
      message = "表名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "表名长度不能超过 64 个字符"	
   )	
   public String getTableName() {	
      return StringUtils.lowerCase(this.tableName);	
   }	
	
   public Long getChildNum() {	
      return this.childNum;	
   }	
}	
