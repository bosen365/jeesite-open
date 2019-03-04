package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.l.i.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.sql.Connection;	
import java.util.ArrayList;	
import java.util.Properties;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.executor.statement.StatementHandler;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.mapping.SqlCommandType;	
import org.apache.ibatis.plugin.Interceptor;	
import org.apache.ibatis.plugin.Intercepts;	
import org.apache.ibatis.plugin.Invocation;	
import org.apache.ibatis.plugin.Plugin;	
import org.apache.ibatis.plugin.Signature;	
import org.apache.ibatis.reflection.MetaObject;	
import org.apache.ibatis.reflection.SystemMetaObject;	
	
@Intercepts({@Signature(	
   type = StatementHandler.class,	
   method = "prepare",	
   args = {Connection.class, Integer.class}	
)})	
public class PaginationInterceptor implements Interceptor {	
   public Object intercept(Invocation invocation) throws Throwable {	
      StatementHandler a;	
      MetaObject a;	
      MappedStatement a = (MappedStatement)(a = SystemMetaObject.forObject(a = (StatementHandler)invocation.getTarget())).getValue("delegate.mappedStatement");	
      HttpServletRequest a;	
      if ((a = ServletUtils.getRequest()) != null) {	
         SqlCommandType a = a.getSqlCommandType();	
         String a;	
         if (StringUtils.isNotBlank(a = ObjectUtils.toString(a.getAttribute(SqlCommandType.class.getName())))) {	
            a = (new StringBuilder()).insert(0, a).append(",").toString();	
         }	
	
         a = (new StringBuilder()).insert(0, a).append(a.toString()).toString();	
         a.setAttribute(SqlCommandType.class.getName(), a);	
      }	
	
      Page a = null;	
      Object a;	
      if ((a = a.getParameterHandler().getParameterObject()) != null && j.ALLATORIxDEMO((HttpServletRequest)null)) {	
         a = PaginationHelper.getPageParameter(a);	
      }	
	
      if (a != null && !a.isNotPaging()) {	
         BoundSql a;	
         String a = (a = a.getBoundSql()).getSql();	
         int a = -1;	
         if (!a.isNotCount()) {	
            int a = a.isOnlyCount();	
            Connection a = (Connection)invocation.getArgs()[0];	
            a.setCount((long)PaginationHelper.getTotalCount(a, a, a, a));	
            Table a;	
            if (a.getCount() > 100L && StringUtils.equals((CharSequence)j.ALLATORIxDEMO().get("type"), "0") && (a = (Table)a.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_uer")) {	
               a.setCount(100L);	
               a = 100;	
            }	
	
            if (a) {	
               return new ArrayList();	
            }	
         }	
	
         if (a != -1 && a.getCount() >= (long)a && a.getPageNo() % 10 == 0) {	
            return new ArrayList();	
         }	
	
         String a = PaginationHelper.getPageSql(a, a);	
         a.setValue("delegate.rowBounds.offset", 0);	
         a.setValue("delegate.rowBound.limit", Integer.MAX_VALUE);	
         a.setValue("delegate.boundSql.sql", a);	
      }	
	
      return invocation.proceed();	
   }	
	
   public void setProperties(Properties properties) {	
   }	
	
   public Object plugin(Object target) {	
      return Plugin.wrap(target, this);	
   }	
}	
