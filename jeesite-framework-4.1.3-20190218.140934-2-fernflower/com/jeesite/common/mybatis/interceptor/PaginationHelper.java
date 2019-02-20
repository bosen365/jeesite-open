package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.j.e;	
import com.jeesite.common.mybatis.j.n.B;	
import com.jeesite.common.mybatis.j.n.D;	
import com.jeesite.common.mybatis.j.n.F;	
import com.jeesite.common.mybatis.j.n.H;	
import com.jeesite.common.mybatis.j.n.M;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.sql.Connection;	
import java.sql.PreparedStatement;	
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.Map;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
public class PaginationHelper {	
   private static final Map dialectMap = MapUtils.newHashMap();	
	
   public static String getPageSql(String sql, RowBounds rowBounds) {	
      return getDialect().ALLATORIxDEMO(sql, rowBounds);	
   }	
	
   public static e getDialect() {	
      String a = Global.getJdbcType();	
      Object a;	
      if ((a = (e)dialectMap.get(a)) == null) {	
         if (!"oracle".equals(a) && !"dameng".equals(a)) {	
            if (!"mysql".equals(a) && !"maradb".equals(a) && !"sqlite".equals(a)) {	
               if ("mssql".equals(a)) {	
                  a = new com.jeesite.common.mybatis.j.n.e();	
               } else if (!"mssql2012".equals(a) && !"derby".equals(a)) {	
                  if (!"hsqldb".equals(a) && !"h2".equals(a) && !"phoenix".equals(a) && !"postgresql".equals(a) && !"highgo".equals(a)) {	
                     if ("nformx".equals(a)) {	
                        a = new H();	
                     } else if ("db2".equals(a)) {	
                        a = new B();	
                     }	
                  } else {	
                     a = new D();	
                  }	
               } else {	
                  a = new M();	
               }	
            } else {	
               a = new m();	
            }	
         } else {	
            a = new F();	
         }	
	
         dialectMap.put(a, a);	
      }	
	
      if (a == null) {	
         throw new RuntimeException("Mybatis dialect error.");	
      } else {	
         return (e)a;	
      }	
   }	
	
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
	
   public static String getCountSql(String sql) {	
      return getDialect().ALLATORIxDEMO(sql);	
   }	
	
   public static long getTotalCount(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {	
      PreparedStatement a = null;	
      ResultSet a = null;	
	
      long var11;	
      try {	
         String a = getCountSql(sql);	
         a = connection.prepareStatement(a);	
         BoundSql a = new BoundSql(mappedStatement.getConfiguration(), a, boundSql.getParameterMappings(), boundSql.getParameterObject());	
         (new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), a)).setParameters(a);	
         a = a.executeQuery();	
         long a = 0L;	
         if (a.next()) {	
            a = a.getLong(1);	
         }	
	
         var11 = a;	
      } finally {	
         if (a != null) {	
            a.close();	
         }	
	
         if (a != null) {	
            a.close();	
         }	
	
      }	
	
      return var11;	
   }	
}	
