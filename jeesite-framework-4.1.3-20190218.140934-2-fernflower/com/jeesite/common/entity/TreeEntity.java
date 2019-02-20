package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonBackReference;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.autoconfigure.sys.Sys0AutoConfiguration;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.reflect.ReflectUtils;	
import java.util.List;	
import javax.validation.constraints.NotNull;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcTime;	
	
@Table(	
   columns = {@Column(	
   name = "parent_code",	
   attrName = "parentCode",	
   label = "父级编码"	
), @Column(	
   name = "parent_codes",	
   attrName = "parentCodes",	
   label = "所有父级编码",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "tree_sort",	
   attrName = "treeSort",	
   label = "本级排序号",	
   isQuery = false	
), @Column(	
   name = "tree_sorts",	
   attrName = "treeSorts",	
   label = "所有父级排序号",	
   isQuery = false	
), @Column(	
   name = "tree_leaf",	
   attrName = "treeLeaf",	
   label = "是否最末级"	
), @Column(	
   name = "tree_level",	
   attrName = "treeLevel",	
   label = "层次级别"	
), @Column(	
   name = "tree_names",	
   attrName = "treeNames",	
   label = "全节点名",	
   queryType = QueryType.LIKE	
)}	
)	
public abstract class TreeEntity extends DataEntity {	
   protected Integer treeLevel;	
   public static final int TREE_SORTS_LENGTH = 10;	
   protected String treeSorts;	
   protected String treeName_;	
   protected String parentCodes;	
   protected String treeLeaf;	
   protected List childList;	
   public static final String ROOT_CODE = "0";	
   protected Integer treeSort;	
   protected Boolean isQueryChildren;	
   private static final long serialVersionUID = 1L;	
   protected TreeEntity parent;	
   protected String treeNames;	
   public static final int DEFAULT_TREE_SORT = 30;	
	
   public String getTreeNames() {	
      return this.treeNames;	
   }	
	
   public abstract void setParent(TreeEntity var1);	
	
   public Boolean getIsQueryChildren() {	
      return this.isQueryChildren;	
   }	
	
   public Integer getTreeLevel() {	
      if (this.treeLeaf != null && this.treeLevel == null) {	
         this.treeLevel = this.parentCodes != null ? this.parentCodes.replaceAll("[^,]", "").length() - 1 : null;	
      }	
	
      return this.treeLevel;	
   }	
	
   public Integer getTreeSort() {	
      return this.treeSort;	
   }	
	
   public String getParentCode() {	
      String a = null;	
      if (this.parent != null) {	
         a = this.parent.getId();	
      }	
	
      return a;	
   }	
	
   public Boolean getIsTreeLeaf() {	
      return "1".equals(this.treeLeaf);	
   }	
	
   public boolean getIsRoot() {	
      return "0".equals(this.getParentCode());	
   }	
	
   public void setTreeLevel(Integer treeLevel) {	
      this.treeLevel = treeLevel;	
   }	
	
   public void setTreeLeaf(String treeLeaf) {	
      this.treeLeaf = treeLeaf;	
   }	
	
   public TreeEntity(String id) {	
      super(id);	
   }	
	
   public void setTreeNames(String treeNames) {	
      this.treeNames = treeNames;	
   }	
	
   public String getTreeLeaf() {	
      return this.treeLeaf;	
   }	
	
   @JsonBackReference	
   @NotNull	
   public abstract TreeEntity getParent();	
	
   public void setIsQueryChildren(Boolean isQueryChildren) {	
      this.isQueryChildren = isQueryChildren;	
   }	
	
   public void setParentCode(String parentCode) {	
      TreeEntity var10000;	
      label18: {	
         if (this.parent == null) {	
            label16: {	
               try {	
                  this.parent = (TreeEntity)this.getClass().newInstance();	
               } catch (Exception var3) {	
                  var3.printStackTrace();	
                  break label16;	
               }	
	
               var10000 = this;	
               break label18;	
            }	
         }	
	
         var10000 = this;	
      }	
	
      var10000.parent.setId(parentCode);	
   }	
	
   @Length(	
      min = 1,	
      max = 2000	
   )	
   public String getParentCodes() {	
      return this.parentCodes;	
   }	
	
   public void setTreeSorts(String treeSorts) {	
      this.treeSorts = treeSorts;	
   }	
	
   public TreeEntity() {	
      this((String)null);	
   }	
	
   @JsonIgnore	
   public String getTreeName_() {	
      if (StringUtils.isNotBlank(this.treeName_)) {	
         return this.treeName_;	
      } else {	
         Column[] var2;	
         int var3 = (var2 = MapperHelper.getTable((BaseEntity)this).columns()).length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            Column a;	
            if ((a = var2[var4]).isTreeName()) {	
               String a = MapperHelper.getAttrName(a);	
               this.treeName_ = (String)ReflectUtils.invokeGetter(this, a);	
               if (this.treeName_ != null) {	
                  this.treeName_ = this.treeName_.replaceAll("/", "_");	
               }	
            }	
	
            ++var4;	
         }	
	
         return this.treeName_;	
      }	
   }	
	
   public void setParentCodes(String parentCodes) {	
      this.parentCodes = parentCodes;	
   }	
	
   public void setTreeSort(Integer treeSort) {	
      this.treeSort = treeSort;	
   }	
	
   public String getTreeSorts() {	
      return this.treeSorts;	
   }	
	
   public void setTreeName_(String treeName) {	
      this.treeName_ = treeName;	
   }	
	
   public void setChildList(List childList) {	
      this.childList = childList;	
   }	
	
   public List getChildList() {	
      return this.childList;	
   }	
}	
