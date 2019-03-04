package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.l.i.g;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.util.Properties;	
import org.apache.ibatis.cache.CacheKey;	
import org.apache.ibatis.executor.Executor;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.plugin.Interceptor;	
import org.apache.ibatis.plugin.Intercepts;	
import org.apache.ibatis.plugin.Invocation;	
import org.apache.ibatis.plugin.Plugin;	
import org.apache.ibatis.plugin.Signature;	
import org.apache.ibatis.session.ResultHandler;	
import org.apache.ibatis.session.RowBounds;	
	
@Intercepts({@Signature(	
   type = Executor.class,	
   method = "update",	
   args = {MappedStatement.class, Object.class}	
), @Signature(	
   type = Executor.class,	
   method = "query",	
   args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}	
), @Signature(	
   type = Executor.class,	
   method = "query",	
   args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}	
)})	
public class DataSourceInterceptor implements Interceptor {	
   public Object plugin(Object target) {	
      return Plugin.wrap(target, this);	
   }	
	
   public Object intercept(Invocation invocation) throws Throwable {	
      if (StringUtils.isNotBlank(Global.getProperty("jdbc.dataSourceNames"))) {	
         String a = null;	
         int a = false;	
         String a = StringUtils.substringBeforeLast(((MappedStatement)invocation.getArgs()[0]).getId(), ".");	
	
         try {	
            MyBatisDao a;	
            if (StringUtils.isNotBlank((a = (MyBatisDao)Class.forName(a).getAnnotation(MyBatisDao.class)).dataSourceName())) {	
               a = DataSourceHolder.getDataSourceName();	
               DataSourceHolder.setDataSourceName(a.dataSourceName());	
               a = true;	
            }	
         } catch (ClassNotFoundException var8) {	
            var8.printStackTrace();	
         }	
	
         Object a = invocation.proceed();	
         if (a) {	
            DataSourceHolder.setDataSourceName(a);	
         }	
	
         return a;	
      } else {	
         return invocation.proceed();	
      }	
   }	
	
   public void setProperties(Properties properties) {	
   }	
}	
