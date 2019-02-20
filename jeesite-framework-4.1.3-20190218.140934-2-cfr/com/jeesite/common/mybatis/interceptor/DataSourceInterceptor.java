/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.cache.CacheKey	
 *  org.apache.ibatis.executor.Executor	
 *  org.apache.ibatis.mapping.BoundSql	
 *  org.apache.ibatis.mapping.MappedStatement	
 *  org.apache.ibatis.plugin.Interceptor	
 *  org.apache.ibatis.plugin.Intercepts	
 *  org.apache.ibatis.plugin.Invocation	
 *  org.apache.ibatis.plugin.Plugin	
 *  org.apache.ibatis.plugin.Signature	
 *  org.apache.ibatis.session.ResultHandler	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.mybatis.interceptor.E;	
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
    public void setProperties(Properties properties) {	
    }	
	
    public Object plugin(Object target) {	
        return Plugin.wrap((Object)target, (Interceptor)this);	
    }	
	
    public Object intercept(Invocation invocation) throws Throwable {	
        return E.ALLATORIxDEMO(invocation);	
    }	
}	
	
