package com.jeesite.common.mybatis.e;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.interceptor.PaginationHelper;	
import java.lang.reflect.InvocationTargetException;	
import java.sql.Connection;	
import java.util.ArrayList;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.executor.statement.StatementHandler;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.plugin.Invocation;	
import org.apache.ibatis.reflection.MetaObject;	
import org.apache.ibatis.reflection.SystemMetaObject;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.pager.PageFetchException;	
	
public final class D {	
   // $FF: synthetic method	
   private static Object ALLATORIxDEMO(Invocation var0, MetaObject metaStatementHandler, String sql) throws InvocationTargetException, IllegalAccessException {	
      metaStatementHandler.setValue("delegate.rowBounds.offset", 0);	
      metaStatementHandler.setValue("delegate.rowBonds.limi", Integer.MAX_VALUE);	
      metaStatementHandler.setValue("delegate.boundSql.sql", sql);	
      return var0.proceed();	
   }	
	
   public static Object ALLATORIxDEMO(Invocation invocation) throws Throwable {	
      StatementHandler a;	
      MetaObject a;	
      MappedStatement a = (MappedStatement)(a = SystemMetaObject.forObject(a = (StatementHandler)invocation.getTarget())).getValue("delegate.mappedStatemen");	
      BoundSql a;	
      String a;	
      if ((a = (a = a.getBoundSql()).getSql()).contains("/*return count*/")) {	
         String a = PaginationHelper.getCountSql(a);	
         return ALLATORIxDEMO(invocation, a, a);	
      } else {	
         String a = null;	
         Object a;	
         if ((a = a.getParameterHandler().getParameterObject()) != null && com.jeesite.common.web.j.F.ALLATORIxDEMO((HttpServletRequest)null)) {	
            a = PaginationHelper.getPageParameter(a);	
         }	
	
         if (a != null && !a.isNotPaging()) {	
            int a = -1;	
            if (!a.isNotCount()) {	
               boolean a;	
               Page var10000;	
               label50: {	
                  a = a.isOnlyCount();	
                  Connection a = (Connection)invocation.getArgs()[0];	
                  a.setCount(PaginationHelper.getTotalCount(a, a, a, a));	
                  if (a.getCount() > 100L && ("0".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("type")) || "9".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("type")))) {	
                     Table a;	
                     if ((a = (Table)a.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
                        a = 100;	
                        var10000 = a;	
                        a.setCount(100L);	
                        break label50;	
                     }	
	
                     if (a.getCount() > 500L && "9".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("type"))) {	
                        a = 500;	
                        a.setCount(500L);	
                     }	
                  }	
	
                  var10000 = a;	
               }	
	
               var10000.initialize();	
               if (a) {	
                  return new ArrayList();	
               }	
            }	
	
            if (a != -1 && a.getCount() >= (long)a && a.getPageNo() % 10 == 0) {	
               return new ArrayList();	
            } else {	
               RowBounds a = new RowBounds(a.getFirstResult(), a.getMaxResults());	
               String a = PaginationHelper.getPageSql(a, a);	
               return ALLATORIxDEMO(invocation, a, a);	
            }	
         } else {	
            return invocation.proceed();	
         }	
      }	
   }	
}	
