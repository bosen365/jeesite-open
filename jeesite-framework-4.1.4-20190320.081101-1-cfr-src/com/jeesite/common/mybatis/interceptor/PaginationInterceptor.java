/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.executor.statement.StatementHandler	
 *  org.apache.ibatis.plugin.Interceptor	
 *  org.apache.ibatis.plugin.Intercepts	
 *  org.apache.ibatis.plugin.Invocation	
 *  org.apache.ibatis.plugin.Plugin	
 *  org.apache.ibatis.plugin.Signature	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.mybatis.v.D;	
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
    public void setProperties(Properties properties) {	
    }	
	
    public Object plugin(Object target) {	
        return Plugin.wrap((Object)target, (Interceptor)this);	
    }	
	
    public Object intercept(Invocation invocation) throws Throwable {	
        return D.ALLATORIxDEMO(invocation);	
    }	
}	
	
