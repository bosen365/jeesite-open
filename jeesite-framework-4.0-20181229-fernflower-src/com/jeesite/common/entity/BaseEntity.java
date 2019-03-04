package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
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
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import javax.validation.constraints.Pattern;	
import javax.xml.bind.annotation.XmlTransient;	
	
@Table(	
   columns = {@Column(	
   name = "corp_code",	
   attrName = "corpCode",	
   label = "集团编码",	
   isUpdate = false	
), @Column(	
   name = "corp_name",	
   attrName = "corpName",	
   label = "集团名称",	
   isUpdate = false,	
   isQuery = false	
)}	
)	
public abstract class BaseEntity implements Serializable {	
   protected Page page;	
   protected String idColumnName;	
   protected boolean isNewRecord;	
   protected String corpCode;	
   protected User currentUser;	
   protected String corpName;	
   private static final long serialVersionUID = 1L;	
   protected String idAttrName;	
   protected String id;	
   protected SqlMap sqlMap;	
	
   public void setCorpName(String corpName) {	
      this.corpName = corpName;	
   }	
	
   public abstract void preUpdate();	
	
   public void setId_in(String[] ids) {	
      this.sqlMap.getWhere().and(this.getIdColumnName(), QueryType.IN, ids);	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getCorpName() {	
      if (StringUtils.isBlank(this.corpName)) {	
         this.corpName = CorpUtils.getCurrentCorpName();	
      }	
	
      return this.corpName;	
   }	
	
   public BaseEntity(String id) {	
      this.sqlMap = new SqlMap(this);	
      this.isNewRecord = false;	
      if (id != null) {	
         this.setId(id);	
      }	
	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 4 ^ 2 << 2 ^ 3;	
      int var10001 = 5 << 4 ^ 3;	
      int var10002 = 4 << 4 ^ (3 ^ 5) << 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public void setIsNewRecord(boolean isNewRecord) {	
      this.isNewRecord = isNewRecord;	
   }	
	
   public abstract void preInsert();	
	
   public BaseEntity() {	
      this((String)null);	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getCorpCode() {	
      if (StringUtils.isBlank(this.corpCode)) {	
         this.corpCode = CorpUtils.getCurrentCorpCode();	
      }	
	
      return this.corpCode;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public Global getGlobal() {	
      return Global.getInstance();	
   }	
	
   public Page setPage(Page page) {	
      return this.page = page;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public SqlMap getSqlMap() {	
      return this.sqlMap;	
   }	
	
   public void setCurrentUser(User currentUser) {	
      this.currentUser = currentUser;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public String getIdColumnName() {	
      if (this.idColumnName == null) {	
         this.getId();	
      }	
	
      return this.idColumnName;	
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
	
   @JsonIgnore	
   @XmlTransient	
   public boolean getIsNewRecord() {	
      this.isNewRecord = this.isNewRecord || StringUtils.isBlank(this.getId());	
      return this.isNewRecord;	
   }	
	
   public String toString() {	
      return this.getId() != null ? this.getId() : Integer.toHexString(this.hashCode());	
   }	
	
   public String[] getId_in() {	
      return (String[])this.sqlMap.getWhere().getValue(this.getIdColumnName(), QueryType.IN);	
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
	
   public Object clone() {	
      return ObjectUtils.cloneBean(this);	
   }	
	
   @Pattern(	
      regexp = "[a-zA-Z0-9_\\-/一-龥]{0,64}",	
      message = "编码长度不能超过 64 个字符，并且只能包含字母、数字、下划线、减号、斜杠或中文"	
   )	
   public String getId() {	
      BaseEntity var10000;	
      if (StringUtils.isBlank(this.id)) {	
         label63: {	
            this.idAttrName = this.idColumnName = "";	
            this.id = "";	
            Table a = MapperHelper.getTable(this);	
            List a = ListUtils.newArrayList();	
            Iterator var3 = MapperHelper.getColumns(a, a).iterator();	
	
            while(true) {	
               Column a;	
               do {	
                  if (!var3.hasNext()) {	
                     break label63;	
                  }	
               } while(!(a = (Column)var3.next()).isPK());	
	
               try {	
                  this.columnNameAndAttrName(a);	
                  if (StringUtils.equals(this.idAttrName, "id")) {	
                     break;	
                  }	
	
                  String a;	
                  if ((a = (String)ReflectUtils.invokeGetter(this, this.idAttrName)) != null) {	
                     if (this.id.length() != 0) {	
                        this.id = this.id + "#";	
                     }	
	
                     this.id = a;	
                  }	
               } catch (Exception var6) {	
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
   public String getIdAttrName() {	
      if (this.idAttrName == null) {	
         this.getId();	
      }	
	
      return this.idAttrName;	
   }	
	
   @JsonIgnore	
   @XmlTransient	
   public Page getPage() {	
      if (this.page == null) {	
         this.page = new Page();	
      }	
	
      return this.page;	
   }	
	
   public void setCorpCode(String corpCode) {	
      this.corpCode = corpCode;	
   }	
}	
