package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.validator.PatternValue;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import javax.xml.bind.annotation.XmlTransient;	
import org.hyperic.sigar.SysInfo;	
	
@Table(	
   columns = {@Column(	
   name = "corp_code",	
   attrName = "corpCode",	
   label = "租户代码",	
   isUpdate = false	
), @Column(	
   name = "corp_name",	
   attrName = "corpName",	
   label = "租户名称",	
   isUpdate = false,	
   isQuery = false	
)}	
)	
public abstract class BaseEntity implements Serializable {	
   protected String idColumnName;	
   protected User currentUser;	
   private static final long serialVersionUID = 1L;	
   protected boolean isNewRecord;	
   protected String idAttrName;	
   protected String corpName;	
   protected Page page;	
   protected String id;	
   protected SqlMap sqlMap;	
   protected String corpCode;	
	
   @PatternValue(	
      value = "web.validator.id",	
      regexp = "[a-zA-Z0-9_\\-/#一-龥]{0,64}",	
      message = "编码长度不能超过 64 个字符，并且只能包含字母、数字、下划线、减号、斜杠、井号或中文"	
   )	
   public String getId() {	
      BaseEntity var10000;	
      if (StringUtils.isBlank(this.id)) {	
         label68: {	
            this.idAttrName = this.idColumnName = "";	
            this.id = "";	
            Table a = MapperHelper.getTable(this);	
            List a = ListUtils.newArrayList();	
            Iterator var3 = MapperHelper.getColumns(a, a).iterator();	
	
            while(true) {	
               Column a;	
               do {	
                  if (!var3.hasNext()) {	
                     break label68;	
                  }	
               } while(!(a = (Column)var3.next()).isPK());	
	
               try {	
                  this.columnNameAndAttrName(a);	
                  if (StringUtils.equals(this.idAttrName, "id")) {	
                     break;	
                  }	
	
                  String a = this.idAttrName;	
                  if (StringUtils.contains(this.idAttrName, "#")) {	
                     a = StringUtils.substringAfterLast(this.idAttrName, "#");	
                  }	
	
                  String a;	
                  if ((a = ObjectUtils.toString(ReflectUtils.invokeGetter(this, a))) != null) {	
                     if (this.id.length() != 0) {	
                        this.id = this.id + "#";	
                     }	
	
                     this.id = this.id + a;	
                  }	
               } catch (Exception var7) {	
                  var10000 = this;	
                  return StringUtils.isBlank(var10000.id) ? null : this.id;	
               }	
            }	
	
            var10000 = this;	
            return StringUtils.isBlank(var10000.id) ? null : this.id;	
         }	
      }	
	
      var10000 = this;	
      return StringUtils.isBlank(var10000.id) ? null : this.id;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public User getCurrentUser() {	
      if (this.currentUser == null) {	
         this.currentUser = UserUtils.getUser();	
      }	
	
      return this.currentUser;	
   }	
	
   public boolean equals(Object obj) {	
      if (null == obj) {	
         return false;	
      } else if (this == obj) {	
         return true;	
      } else if (!this.getClass().equals(obj.getClass())) {	
         return false;	
      } else {	
         BaseEntity a = (BaseEntity)obj;	
         return null == this.getId() ? false : this.getId().equals(a.getId());	
      }	
   }	
	
   public String getOrderBy() {	
      return this.page != null ? this.page.getOrderBy() : null;	
   }	
	
   public BaseEntity(String id) {	
      this.sqlMap = new SqlMap(this);	
      this.isNewRecord = false;	
      if (id != null) {	
         this.setId(id);	
      }	
	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public Global getGlobal() {	
      return Global.getInstance();	
   }	
	
   public void setId_in(String[] ids) {	
      this.sqlMap.getWhere().and(this.getIdColumnName(), QueryType.IN, ids);	
   }	
	
   public boolean getIsNewRecord() {	
      this.isNewRecord = this.isNewRecord || StringUtils.isBlank(this.getId());	
      return this.isNewRecord;	
   }	
	
   public Object clone() {	
      return ObjectUtils.cloneBean(this);	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getCorpName() {	
      if (StringUtils.isBlank(this.corpName)) {	
         this.corpName = CorpUtils.getCurrentCorpName();	
      }	
	
      return this.corpName;	
   }	
	
   public void setPage(Page page) {	
      this.page = page;	
   }	
	
   public void setCurrentUser(User currentUser) {	
      this.currentUser = currentUser;	
   }	
	
   public void setPageSize(Integer pageSize) {	
      if (pageSize != null) {	
         if (this.page == null) {	
            this.page = new Page();	
         }	
	
         this.page.setPageSize(pageSize);	
      }	
	
   }	
	
   public Integer getPageNo() {	
      return this.page != null ? this.page.getPageNo() : null;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getIdColumnName() {	
      if (this.idColumnName == null) {	
         this.getId();	
      }	
	
      return this.idColumnName;	
   }	
	
   public Integer getPageSize() {	
      return this.page != null ? this.page.getPageSize() : null;	
   }	
	
   // $FF: synthetic method	
   private void columnNameAndAttrName(Column c) {	
      if (this.idColumnName.length() != 0) {	
         this.idColumnName = this.idColumnName + "#";	
      }	
	
      this.idColumnName = this.idColumnName + MapperHelper.getColumnName(c);	
      if (this.idAttrName.length() != 0) {	
         this.idAttrName = this.idAttrName + "#";	
      }	
	
      this.idAttrName = this.idAttrName + MapperHelper.getAttrName(c);	
   }	
	
   public BaseEntity() {	
      this((String)null);	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public SqlMap getSqlMap() {	
      return this.sqlMap;	
   }	
	
   public void setId(String id) {	
      String[] a;	
      if ((a = StringUtils.split(id, "#")) == null || a.length <= 0) {	
         String[] var10000 = new String[1];	
         boolean var10002 = true;	
         var10000[0] = null;	
         a = var10000;	
      }	
	
      int a = 0;	
      this.idColumnName = "";	
      this.idAttrName = "";	
      Table a = MapperHelper.getTable(this);	
      List a = ListUtils.newArrayList();	
      Iterator var6 = MapperHelper.getColumns(a, a).iterator();	
	
      BaseEntity var10;	
      while(true) {	
         if (var6.hasNext()) {	
            Column a;	
            if (!(a = (Column)var6.next()).isPK()) {	
               continue;	
            }	
	
            try {	
               this.columnNameAndAttrName(a);	
               if (!StringUtils.equals(this.idAttrName, "id")) {	
                  String var11 = a[a];	
                  ++a;	
                  ReflectUtils.invokeSetter(this, this.idAttrName, var11);	
                  continue;	
               }	
            } catch (Exception var9) {	
               var10 = this;	
               break;	
            }	
	
            var10 = this;	
            break;	
         }	
	
         var10 = this;	
         break;	
      }	
	
      var10.id = id;	
   }	
	
   public abstract void preInsert();	
	
   public void setIsNewRecord(boolean isNewRecord) {	
      this.isNewRecord = isNewRecord;	
   }	
	
   public void setCorpName(String corpName) {	
      this.corpName = corpName;	
   }	
	
   public abstract void preUpdate();	
	
   public String toString() {	
      return this.getId() != null ? this.getId() : Integer.toHexString(this.hashCode());	
   }	
	
   public void setOrderBy(String orderBy) {	
      if (StringUtils.isNotBlank(orderBy)) {	
         if (this.page == null) {	
            this.page = new Page();	
         }	
	
         this.page.setOrderBy(orderBy);	
      }	
	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public Page getPage() {	
      return this.page;	
   }	
	
   public void setPageNo(Integer pageNo) {	
      if (pageNo != null) {	
         if (this.page == null) {	
            this.page = new Page();	
         }	
	
         this.page.setPageNo(pageNo);	
      }	
	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getIdAttrName() {	
      if (this.idAttrName == null) {	
         this.getId();	
      }	
	
      return this.idAttrName;	
   }	
	
   public String[] getId_in() {	
      return (String[])this.sqlMap.getWhere().getValue(this.getIdColumnName(), QueryType.IN);	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getCorpCode() {	
      if (StringUtils.isBlank(this.corpCode)) {	
         this.corpCode = CorpUtils.getCurrentCorpCode();	
      }	
	
      return this.corpCode;	
   }	
	
   public void setCorpCode(String corpCode) {	
      this.corpCode = corpCode;	
   }	
}	
