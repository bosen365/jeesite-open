/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.mybatis.interceptor.m;	
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
	
@Intercepts(value={@Signature(type=Executor.class, method="update", args={MappedStatement.class, Object.class}), @Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}), @Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})	
public class DataSourceInterceptor	
implements Interceptor {	
    @Override	
    public void setProperties(Properties properties) {	
    }	
	
    @Override	
    public Object plugin(Object target) {	
        return Plugin.wrap(target, this);	
    }	
	
    @Override	
    public Object intercept(Invocation invocation) throws Throwable {	
        return m.ALLATORIxDEMO(invocation);	
    }	
}	
	
