package com.jeesite.common.mybatis.mapper.query;	
	
import com.beust.jcommander.internal.Lists;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
	
public class QueryDataScope implements Serializable {	
   private static final long serialVersionUID = 1L;	
   private BaseEntity entity;	
   private static UserDataScopeDao userDataScopeDao;	
   public static final String USER_CACHE_USER_DATA_SCOPE_ = "userDataScope_";	
	
   // $FF: synthetic method	
   private void genUserDataScopeSql(StringBuilder fromSql, StringBuilder whereSql, StringBuilder existsSql, User user, List roleList, String dataScopeTableAlias, String ctrlType, String ctrlPermi, String bizCtrlDataField, String bizCtrlUserField) {	
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
	
      whereSql.append(" AND (");	
      existsSql.append(" AND (");	
      List var10000;	
      if (a == 0L) {	
         if (StringUtils.isNotBlank(bizCtrlUserField)) {	
            var10000 = roleList;	
            whereSql.append(bizCtrlUserField + " = '" + user.getUserCode() + "'");	
            existsSql.append((new StringBuilder()).insert(0, bizCtrlUserField).append(" = '").append(user.getUserCode()).append("'").toString());	
         } else {	
            whereSql.append("1=2");	
            existsSql.append("1=2");	
            var10000 = roleList;	
         }	
      } else {	
         fromSql.append((new StringBuilder()).insert(0, " JOIN ").append(Global.getTablePrefix()).append("sys_user_data_scope ").append(dataScopeTableAlias).append(" ON ").append(dataScopeTableAlias).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
         whereSql.append((new StringBuilder()).insert(0, dataScopeTableAlias).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         whereSql.append((new StringBuilder()).insert(0, " AND ").append(dataScopeTableAlias).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
         whereSql.append((new StringBuilder()).insert(0, " AN. ").append(dataScopeTableAlias).append(".ser_code = '").append(user.getUserCode()).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_user_data_scope").toString());	
         existsSql.append((new StringBuilder()).insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND ser_code = '").append(user.getUserCode()).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
         var10000 = roleList;	
      }	
	
      if (var10000.size() > 0) {	
         dataScopeTableAlias = (new StringBuilder()).insert(0, "b").append(dataScopeTableAlias.substring(1)).toString();	
         fromSql.append((new StringBuilder()).insert(0, " JOIN ").append(Global.getTablePrefix()).append("sys_role_data_scope ").append(dataScopeTableAlias).append(" ON ").append(dataScopeTableAlias).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
         whereSql.append((new StringBuilder()).insert(0, " OR (").append(dataScopeTableAlias).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         whereSql.append((new StringBuilder()).insert(0, " AND ").append(dataScopeTableAlias).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
         whereSql.append((new StringBuilder()).insert(0, " AN. ").append(dataScopeTableAlias).append(".role_code IN ('").append(StringUtils.join(roleList, "','")).append("'))").toString());	
         existsSql.append((new StringBuilder()).insert(0, " OR EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_role_data_scope").toString());	
         existsSql.append((new StringBuilder()).insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND role_code IN ('").append(StringUtils.join(roleList, "','")).append("')").toString());	
         existsSql.append((new StringBuilder()).insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
      }	
	
      whereSql.append(")");	
      existsSql.append(")");	
   }	
	
   public QueryDataScope(BaseEntity var1) {	
      this.entity = var1;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 1 << 1;	
      int var10001 = (3 ^ 5) << 3 ^ 2 ^ 5;	
      int var10002 = (3 ^ 5) << 3 ^ 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
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
	
   // $FF: synthetic method	
   private void genRoleDataScopeSql(StringBuilder fromSql, StringBuilder whereSql, StringBuilder existsSql, User user, String dataScopeTableAlias, String bizCtrlDataField) {	
      fromSql.append(" JOIN " + Global.getTablePrefix() + "sys_user_role " + dataScopeTableAlias + " ON " + dataScopeTableAlias + ".role_code = " + bizCtrlDataField);	
      whereSql.append((new StringBuilder()).insert(0, " AND ").append(dataScopeTableAlias).append(".user_code = '").append(user.getUserCode()).append("'").toString());	
      existsSql.append((new StringBuilder()).insert(0, " AN. EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_ser_role WHERE ser_code = '").append(user.getUserCode()).append("' AN. role_code = ").append(bizCtrlDataField).append(")").toString());	
   }	
	
   public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String ctrlPermi) {	
      this.addFilter(sqlMapKey, ctrlTypes, bizCtrlDataFields, (String)null, ctrlPermi);	
      return this;	
   }	
	
   public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String bizCtrlUserField, String ctrlPermi) {	
      User a;	
      if ((a = this.entity.getCurrentUser()).isAdmin()) {	
         return this;	
      } else {	
         List a = Lists.newArrayList();	
         Iterator var8 = a.getRoleList().iterator();	
	
         label59:	
         while(true) {	
            for(Iterator var10000 = var8; var10000.hasNext(); var10000 = var8) {	
               Role a = (Role)var8.next();	
               if (!"0".equals(a.getDataScope())) {	
                  if ("1".equals(a.getDataScope())) {	
                     return this;	
                  }	
	
                  if ("2".equals(a.getDataScope())) {	
                     a.add(a.getRoleCode());	
                  }	
                  continue label59;	
               }	
            }	
	
            StringBuilder a = new StringBuilder();	
            StringBuilder a = new StringBuilder();	
            StringBuilder a = new StringBuilder();	
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
               for(int var17 = a = 0; var17 < a.length; var17 = a) {	
                  String a = (new StringBuilder()).insert(0, "a").append(IdGen.randomBase62(4)).toString();	
                  if ("Role".equals(a[a]) && "1".equals(ctrlPermi)) {	
                     this.genRoleDataScopeSql(a, a, a, a, a, a[a]);	
                  } else {	
                     this.genUserDataScopeSql(a, a, a, a, a, a, a[a], ctrlPermi, a[a], bizCtrlUserField);	
                  }	
	
                  ++a;	
               }	
	
               this.entity.getSqlMap().add((new StringBuilder()).insert(0, sqlMapKey).append("From").toString(), a.toString());	
               this.entity.getSqlMap().add((new StringBuilder()).insert(0, sqlMapKey).append("Where").toString(), a.toString());	
               this.entity.getSqlMap().add(sqlMapKey, a.toString());	
               return this;	
            }	
	
            throw new ServiceException((new StringBuilder()).insert(0, "注意ctrlTypes和bizCtrl.ataFields使用“,”分隔，长度必须相等。ctrlTypes: ").append(ctrlTypes).append(" bizCtrlDataFields: ").append(bizCtrlDataFields).toString());	
         }	
      }	
   }	
}	
