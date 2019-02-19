package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
public class QueryDataScope implements Serializable {	
   public static final String USER_CACHE_USER_DATA_SCOPE_ = "userDataScope_";	
   private static UserDataScopeDao userDataScopeDao;	
   private static final long serialVersionUID = 1L;	
   public static final String USER_CACHE_ROLE_EXTEND_DATA_SCOPES = "roleExtendDataScopes";	
   private Map roleExtendDataScopes;	
   private BaseEntity entity;	
	
   public QueryDataScope clearFilter(String sqlMapKey) {	
      SqlMap a;	
      (a = this.entity.getSqlMap()).remove(sqlMapKey);	
      a.remove(sqlMapKey + "From");	
      a.remove((new StringBuilder()).insert(0, sqlMapKey).append("Where").toString());	
      return this;	
   }	
	
   // $FF: synthetic method	
   private void addRoleDataScope(String sqlMapKey, List roleList, String ctrlType, String ctrlPermi, String bizCtrlDataField, String bizCtrlUserField) {	
      if (roleList.size() > 0) {	
         StringBuilder a = new StringBuilder();	
         StringBuilder a = new StringBuilder();	
         String a = (new StringBuilder()).insert(0, "a").append(IdGen.randomBase62(4)).toString();	
         a.append(" JO#N " + Global.getTablePrefix() + "sys_role_data_scope " + a);	
         a.append((new StringBuilder()).insert(0, " ON ").append(a).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
         a.append((new StringBuilder()).insert(0, " OR (").append(a).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ").append(a).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ").append(a).append(".role_code IN ('").append(StringUtils.join(roleList, "','")).append("'))").toString());	
         this.addJoinFrom(sqlMapKey, a.toString());	
         this.addJoinWhere(sqlMapKey, a.toString());	
         StringBuilder a;	
         (a = new StringBuilder()).append((new StringBuilder()).insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_role_data_scope").toString());	
         a.append((new StringBuilder()).insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND role_code #N ('").append(StringUtils.join(roleList, "','")).append("')").toString());	
         a.append((new StringBuilder()).insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
         this.addFilter(sqlMapKey, a.toString());	
      }	
	
   }	
	
   // $FF: synthetic method	
   private QueryDataScope addJoinWhere(String sqlMapKey, String sqlWhere) {	
      return this.addFilter(sqlMapKey + "Where", sqlWhere);	
   }	
	
   // $FF: synthetic method	
   private String removeTheStartingAndOr(String sql) {	
      if (StringUtils.startsWithIgnoreCase(sql = StringUtils.trim(sql), "AND ")) {	
         sql = sql.substring("AND ".length());	
      }	
	
      if (StringUtils.startsWithIgnoreCase(sql, "OR ")) {	
         sql = sql.substring("OR ".length());	
      }	
	
      return sql;	
   }	
	
   public QueryDataScope addFilter(String sqlMapKey, String sqlWhere) {	
      SqlMap a;	
      String a;	
      if (StringUtils.isNotBlank(a = ObjectUtils.toString((a = this.entity.getSqlMap()).get(sqlMapKey)))) {	
         a.add(sqlMapKey, "AND ((" + this.removeTheStartingAndOr(a) + ") OR (" + this.removeTheStartingAndOr(sqlWhere) + "))");	
         return this;	
      } else {	
         a.add(sqlMapKey, (new StringBuilder()).insert(0, "AND ").append(sqlWhere).toString());	
         return this;	
      }	
   }	
	
   public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String ctrlPermi) {	
      this.addFilter(sqlMapKey, ctrlTypes, bizCtrlDataFields, (String)null, ctrlPermi);	
      return this;	
   }	
	
   // $FF: synthetic method	
   private void addUserDataScope(String sqlMapKey, User user, String ctrlType, String ctrlPermi, String bizCtrlDataField, String bizCtrlUserField) {	
      Long a;	
      if ((a = (Long)UserUtils.getCache((new StringBuilder()).insert(0, "userDataScope_").append(ctrlType).append(ctrlPermi).toString())) == null) {	
         UserDataScope a = new UserDataScope();	
         a.setUserCode(user.getUserCode());	
         a.setCtrlType(ctrlType);	
         a.setCtrlPermi(ctrlPermi);	
         if (userDataScopeDao == null) {	
            userDataScopeDao = (UserDataScopeDao)SpringUtils.getBean(UserDataScopeDao.class);	
         }	
	
         a = userDataScopeDao.findCount(a);	
         UserUtils.putCache((new StringBuilder()).insert(0, "userDataScope_").append(ctrlType).append(ctrlPermi).toString(), a);	
      }	
	
      if (a == 0L) {	
         if (StringUtils.isNotBlank(bizCtrlUserField)) {	
            this.addJoinWhere(sqlMapKey, bizCtrlUserField + " = '" + user.getUserCode() + "'");	
            this.addFilter(sqlMapKey, (new StringBuilder()).insert(0, bizCtrlUserField).append(" = '").append(user.getUserCode()).append("'").toString());	
         } else {	
            this.addJoinWhere(sqlMapKey, "1=2");	
            this.addFilter(sqlMapKey, "1=2");	
         }	
      } else {	
         StringBuilder a = new StringBuilder();	
         StringBuilder a = new StringBuilder();	
         String a = (new StringBuilder()).insert(0, "a").append(IdGen.randomBase62(4)).toString();	
         a.append((new StringBuilder()).insert(0, " JOIN ").append(Global.getTablePrefix()).append("sys_user_data_scope ").append(a).append(" ON ").append(a).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
         a.append((new StringBuilder()).insert(0, a).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ").append(a).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ").append(a).append(".user_code = '").append(user.getUserCode()).append("'").toString());	
         this.addJoinFrom(sqlMapKey, a.toString());	
         this.addJoinWhere(sqlMapKey, a.toString());	
         StringBuilder a;	
         (a = new StringBuilder()).append((new StringBuilder()).insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_user_data_scope").toString());	
         a.append((new StringBuilder()).insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND user_code = '").append(user.getUserCode()).append("'").toString());	
         a.append((new StringBuilder()).insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
         this.addFilter(sqlMapKey, a.toString());	
      }	
   }	
	
   // $FF: synthetic method	
   private QueryDataScope addJoinFrom(String sqlMapKey, String sqlFrom) {	
      SqlMap a = this.entity.getSqlMap();	
      String a;	
      if (StringUtils.isNotBlank(a = ObjectUtils.toString(a.get(sqlMapKey + "From")))) {	
         a.add((new StringBuilder()).insert(0, sqlMapKey).append("From").toString(), (new StringBuilder()).insert(0, a).append(" ").append(sqlFrom).toString());	
         return this;	
      } else {	
         a.add((new StringBuilder()).insert(0, sqlMapKey).append("From").toString(), sqlFrom);	
         return this;	
      }	
   }	
	
   // $FF: synthetic method	
   private void addRoleExtendDataScope(String sqlMapKey, Set extendSet, String ctrlType, String bizCtrlDataField) {	
      if (this.roleExtendDataScopes == null) {	
         this.roleExtendDataScopes = (Map)UserUtils.getCache("roleExtendDataScopes");	
         if (this.roleExtendDataScopes == null) {	
            this.roleExtendDataScopes = (Map)JsonMapper.fromJson(Global.getProperty("role.extendDataScopes", "{}"), Map.class);	
            UserUtils.putCache("roleExtendDataScopes", this.roleExtendDataScopes);	
         }	
      }	
	
      Iterator a = extendSet.iterator();	
	
      label70:	
      while(true) {	
         Iterator var10000 = a;	
	
         while(var10000.hasNext()) {	
            Map a;	
            if ((a = (Map)this.roleExtendDataScopes.get(a.next())) == null) {	
               var10000 = a;	
            } else {	
               Map a;	
               if ((a = (Map)a.get(ctrlType)) != null) {	
                  try {	
                     String a = (String)a.get("ctrlTypeClass");	
                     String a = (String)a.get("ctrlDataAttrName");	
                     String a = (String)a.get("ctrlDataParentCodesAttrName");	
                     if ("NONE".equals(a)) {	
                        this.clearFilter(sqlMapKey);	
                        return;	
                     }	
	
                     Table var24 = MapperHelper.getTable(Class.forName(a));	
                     String a = MapperHelper.getTableName(var24, (BaseEntity)null);	
                     String a = null;	
                     Iterator var15 = MapperHelper.getColumns(var24, ListUtils.newArrayList()).iterator();	
	
                     String var25;	
                     while(true) {	
                        if (var15.hasNext()) {	
                           Column a;	
                           if (!(a = (Column)var15.next()).isPK()) {	
                              continue;	
                           }	
	
                           var25 = a = a.name();	
                           break;	
                        }	
	
                        var25 = a;	
                        break;	
                     }	
	
                     if (StringUtils.isBlank(var25)) {	
                        throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a).append(" 定义@Table没有isPK=true的列.").toString());	
                     }	
	
                     String a = (String)ReflectUtils.invokeGetter(this.entity, a);	
                     StringBuilder a = new StringBuilder();	
                     StringBuilder a = new StringBuilder();	
                     String a = (new StringBuilder()).insert(0, "a").append(IdGen.randomBase62(4)).toString();	
                     a.append(" JO#N " + a + " " + a);	
                     a.append((new StringBuilder()).insert(0, " ON ").append(a).append(".").append(a).append(" = ").append(bizCtrlDataField).toString());	
                     a.append((new StringBuilder()).insert(0, "(").append(a).append(".").append(a).append(" = '").append(a).append("'").toString());	
                     if (StringUtils.isNotBlank(a)) {	
                        String a = (String)ReflectUtils.invokeGetter(this.entity, a);	
                        a.append((new StringBuilder()).insert(0, " OR ").append(a).append(".parent_codes L#KE '").append(a).append(a).append(",%'").toString());	
                     }	
	
                     a.append(")");	
                     this.addJoinFrom(sqlMapKey, a.toString());	
                     this.addJoinWhere(sqlMapKey, a.toString());	
                     StringBuilder a;	
                     (a = new StringBuilder()).append((new StringBuilder()).insert(0, " EXISTS (SELECT 1 FROM ").append(a).toString());	
                     a.append((new StringBuilder()).insert(0, " WHERE (").append(a).append(" = '").append(a).append("'").toString());	
                     if (StringUtils.isNotBlank(a)) {	
                        String a = (String)ReflectUtils.invokeGetter(this.entity, a);	
                        a.append((new StringBuilder()).insert(0, " OR parent_codes LIKE '").append(a).append(a).append(",%')").toString());	
                     }	
	
                     a.append((new StringBuilder()).insert(0, " AND ").append(a).append(" = ").append(bizCtrlDataField).append(")").toString());	
                     this.addFilter(sqlMapKey, a.toString());	
                     continue label70;	
                  } catch (ClassNotFoundException var21) {	
                     throw ExceptionUtils.unchecked(var21);	
                  }	
               }	
	
               var10000 = a;	
            }	
         }	
	
         return;	
      }	
   }	
	
   // $FF: synthetic method	
   private void addRoleDataScope(String sqlMapKey, User user, String bizCtrlDataField) {	
      String a = (new StringBuilder()).insert(0, "a").append(IdGen.randomBase62(4)).toString();	
      this.addJoinFrom(sqlMapKey, " JOIN " + Global.getTablePrefix() + "sys_user_role " + a + " ON " + a + ".role_code = " + bizCtrlDataField);	
      this.addJoinWhere(sqlMapKey, (new StringBuilder()).insert(0, a).append(".user_code = '").append(user.getUserCode()).append("'").toString());	
      this.addFilter(sqlMapKey, (new StringBuilder()).insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_user_role WHERE user_code = '").append(user.getUserCode()).append("' AND role_code = ").append(bizCtrlDataField).append(")").toString());	
   }	
	
   public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String bizCtrlUserField, String ctrlPermi) {	
      User a;	
      if ((a = this.entity.getCurrentUser()).isAdmin()) {	
         return this;	
      } else {	
         List a = ListUtils.newArrayList();	
         Set a = SetUtils.newHashSet();	
         Iterator var9 = a.getRoleList().iterator();	
	
         label62:	
         while(true) {	
            for(Iterator var10000 = var9; var10000.hasNext(); var10000 = var9) {	
               Role a = (Role)var9.next();	
               if (!"0".equals(a.getDataScope())) {	
                  if ("1".equals(a.getDataScope())) {	
                     return this;	
                  }	
	
                  if ("2".equals(a.getDataScope())) {	
                     a.add(a.getRoleCode());	
                  } else {	
                     a.add(a.getDataScope());	
                  }	
                  continue label62;	
               }	
            }	
	
            String[] a = StringUtils.split(ctrlTypes, ",");	
            String[] a = StringUtils.split(bizCtrlDataFields, ",");	
            if (a != null && a != null && a.length == a.length) {	
               String[] var10001 = new String[2];	
               boolean var10003 = true;	
               var10001[0] = "1";	
               var10001[1] = "2";	
               if (!StringUtils.inString(ctrlPermi, var10001)) {	
                  ctrlPermi = "1";	
               }	
	
               int a;	
               for(int var14 = a = 0; var14 < a.length; var14 = a) {	
                  if ("Role".equals(a[a]) && "1".equals(ctrlPermi)) {	
                     this.addRoleDataScope(sqlMapKey, a, a[a]);	
                  } else {	
                     this.addUserDataScope(sqlMapKey, a, a[a], ctrlPermi, a[a], bizCtrlUserField);	
                     this.addRoleDataScope(sqlMapKey, a, a[a], ctrlPermi, a[a], bizCtrlUserField);	
                     if ("1".equals(ctrlPermi)) {	
                        this.addRoleExtendDataScope(sqlMapKey, a, a[a], a[a]);	
                     }	
                  }	
	
                  ++a;	
               }	
	
               return this;	
            }	
	
            throw new ServiceException((new StringBuilder()).insert(0, "注意ctrlTypes和bizCtrlDataFields使用“,”分隔，长度必须相等。ctrlTypes: ").append(ctrlTypes).append(" bizCtrlDataFields: ").append(bizCtrlDataFields).toString());	
         }	
      }	
   }	
	
   public QueryDataScope(BaseEntity var1) {	
      this.entity = var1;	
   }	
}	
