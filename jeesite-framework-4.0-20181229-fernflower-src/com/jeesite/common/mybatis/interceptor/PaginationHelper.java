package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.l.l;	
import com.jeesite.common.mybatis.l.l.C;	
import com.jeesite.common.mybatis.l.l.D;	
import com.jeesite.common.mybatis.l.l.H;	
import com.jeesite.common.mybatis.l.l.I;	
import com.jeesite.common.mybatis.l.l.K;	
import com.jeesite.common.mybatis.l.l.g;	
import com.jeesite.common.mybatis.l.l.h;	
import com.jeesite.common.mybatis.l.l.j;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import java.sql.Connection;	
import java.sql.PreparedStatement;	
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.Map;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;	
	
public class PaginationHelper {	
   private static final Map dialectMap = MapUtils.newHashMap();	
   private static Pattern p1 = Pattern.compile("order[\s　]*by[\w|\W|\s|\S]*", 2);	
	
   public static Page getPageParameter(Object parameterObject) {	
      try {	
         if (parameterObject instanceof Page) {	
            return (Page)parameterObject;	
         } else {	
            return parameterObject instanceof Map ? (Page)((Map)parameterObject).get("page") : (Page)ReflectUtils.getFieldValue(parameterObject, "page");	
         }	
      } catch (Exception var2) {	
         return null;	
      }	
   }	
	
   public static l getDialect() {	
      String a = Global.getJdbcType();	
      Object a;	
      if ((a = (l)dialectMap.get(a)) == null) {	
         if ("oracle".equals(a)) {	
            a = new j();	
         } else if (com.jeesite.common.l.l.j.ALLATORIxDEMO ("!W?_ ").equals(a)) {	
            a = new h();	
         } else if (!"mssql".equals(a) && !com.jeesite.common.l.l.j.ALLATORIxDEMO ("?_ ])\\:K>").equals(a)) {	
            if (!"postgresql".equals(a) && !com.jeesite.common.l.l.j.ALLATORIxDEMO ("F%I$I#").equals(a)) {	
               if ("db2".equals(a)) {	
                  a = new D();	
               } else if (com.jeesite.common.l.l.j.ALLATORIxDEMO ("(K>L5").equals(a)) {	
                  a = new g();	
               } else if ("h2".equals(a)) {	
                  a = new H();	
               } else if (com.jeesite.common.l.l.j.ALLATORIxDEMO ("F?_ ").equals(a)) {	
                  a = new K();	
               } else if ("sybase".equals(a)) {	
                  a = new com.jeesite.common.mybatis.l.l.l();	
               }	
            } else {	
               a = new I();	
            }	
         } else {	
            a = new C();	
         }	
	
         dialectMap.put(a, a);	
      }	
	
      if (a == null) {	
         throw new RuntimeException(com.jeesite.common.l.l.j.ALLATORIxDEMO ("C5L-Z%]lJ%O K/ZlK>\\#\\b"));	
      } else {	
         return (l)a;	
      }	
   }	
	
   // $FF: synthetic method	
   private static String removeSelect(String qlString) {	
      return qlString.substring(qlString.toLowerCase().indexOf("from"));	
   }	
	
   public static String getPageSql(String sql, Page page) {	
      l a;	
      return (a = getDialect()).ALLATORIxDEMO() ? a.ALLATORIxDEMO(sql, page.getFirstResult(), page.getMaxResults()) : sql;	
   }	
	
   // $FF: synthetic method	
   private static String removeOrders(String qlString) {	
      Matcher a = p1.matcher(qlString);	
      StringBuffer a = new StringBuffer();	
      Matcher var10000 = a;	
	
      while(var10000.find()) {	
         var10000 = a;	
         a.appendReplacement(a, "");	
      }	
	
      a.appendTail(a);	
      return a.toString();	
   }	
	
   public static String getCountSql(String sql) {	
      return StringUtils.equals(Global.getDbName(), com.jeesite.common.l.l.j.ALLATORIxDEMO ("A>O/B)")) ? (new StringBuilder()).insert(0, "SE&ECT count(1) FROM ( ").append(sql).append(com.jeesite.common.l.l.j.ALLATORIxDEMO ("\u000ee\u000e8C<q/A9@8")).toString() : (new StringBuilder()).insert(0, "SE&ECT count(1) FROM ( ").append(removeOrders(sql)).append(com.jeesite.common.l.l.j.ALLATORIxDEMO ("\u000ee\u000e8C<q/A9@8")).toString();	
   }	
	
   public static int getTotalCount(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {	
      PreparedStatement a = null;	
      ResultSet a = null;	
	
      int var10;	
      try {	
         String a = getCountSql(sql);	
         a = connection.prepareStatement(a.toString());	
         BoundSql a = new BoundSql(mappedStatement.getConfiguration(), a.toString(), boundSql.getParameterMappings(), boundSql.getParameterObject());	
         (new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), a)).setParameters(a);	
         a = a.executeQuery();	
         int a = 0;	
         if (a.next()) {	
            a = a.getInt(1);	
         }	
	
         var10 = a;	
      } finally {	
         if (a != null) {	
            a.close();	
         }	
	
         if (a != null) {	
            a.close();	
         }	
	
      }	
	
      return var10;	
   }	
}	
