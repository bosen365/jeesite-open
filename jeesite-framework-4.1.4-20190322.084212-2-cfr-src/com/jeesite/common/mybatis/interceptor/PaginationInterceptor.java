/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.mybatis.e.I;	
import java.sql.Connection;	
import java.util.Properties;	
import org.apache.ibatis.executor.statement.StatementHandler;	
import org.apache.ibatis.plugin.Interceptor;	
import org.apache.ibatis.plugin.Intercepts;	
import org.apache.ibatis.plugin.Invocation;	
import org.apache.ibatis.plugin.Plugin;	
import org.apache.ibatis.plugin.Signature;	
	
@Intercepts(value={@Signature(type=StatementHandler.class, method="prepare", args={Connection.class, Integer.class})})	
public class PaginationInterceptor	
implements Interceptor {	
    @Override	
    public Object intercept(Invocation invocation) throws Throwable {	
        return I.ALLATORIxDEMO(invocation);	
    }	
	
    @Override	
    public Object plugin(Object target) {	
        return Plugin.wrap(target, this);	
    }	
	
    @Override	
    public void setProperties(Properties properties) {	
    }	
}	
	
