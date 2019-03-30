/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.interceptor.PaginationHelper;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.l.j;	
import java.lang.annotation.Annotation;	
import java.lang.reflect.InvocationTargetException;	
import java.sql.Connection;	
import java.util.ArrayList;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.executor.parameter.ParameterHandler;	
import org.apache.ibatis.executor.statement.StatementHandler;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.plugin.Invocation;	
import org.apache.ibatis.reflection.MetaObject;	
import org.apache.ibatis.reflection.SystemMetaObject;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.SysInfo;	
	
public final class I {	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static Object ALLATORIxDEMO(Invocation invocation) throws Throwable {	
        block7 : {	
            a = (StatementHandler)invocation.getTarget();	
            a = SystemMetaObject.forObject(a);	
            a = (MappedStatement)a.getValue("delegate.mappedStatement");	
            a = a.getBoundSql();	
            a = a.getSql();	
            if (a.contains("/*return count*/")) {	
                a = PaginationHelper.getCountSql(a);	
                return I.ALLATORIxDEMO(invocation, a, a);	
            }	
            a = null;	
            a = a.getParameterHandler().getParameterObject();	
            if (a != null && j.ALLATORIxDEMO(null)) {	
                a = PaginationHelper.getPageParameter(a);	
            }	
            if (a == null) return invocation.proceed();	
            if (a.isNotPaging() != false) return invocation.proceed();	
            a = -1;	
            if (a.isNotCount()) break block7;	
            a = a.isOnlyCount();	
            a = (Connection)invocation.getArgs()[0];	
            v0 = a;	
            v0.setCount(PaginationHelper.getTotalCount(a, (Connection)a, a, a));	
            if (v0.getCount() <= 100L || !"0".equals(j.ALLATORIxDEMO().get("type")) && !"9".equals(j.ALLATORIxDEMO().get("type"))) ** GOTO lbl32	
            a = a.getClass().getAnnotation(Table.class);	
            if (a != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
                a = 100;	
                v1 = a;	
                v2 = v1;	
                v1.setCount(100L);	
            } else {	
                if (a.getCount() > 500L && "9".equals(j.ALLATORIxDEMO().get("type"))) {	
                    a = 500;	
                    a.setCount(500L);	
                }	
lbl32: // 4 sources:	
                v2 = a;	
            }	
            v2.initialize();	
            if (a) {	
                return new ArrayList<E>();	
            }	
        }	
        if (a != -1 && a.getCount() >= (long)a && a.getPageNo() % 10 == 0) {	
            return new ArrayList<E>();	
        }	
        a = new RowBounds(a.getFirstResult(), a.getMaxResults());	
        a = PaginationHelper.getPageSql(a, a);	
        return I.ALLATORIxDEMO(invocation, a, (String)a);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private static /* synthetic */ Object ALLATORIxDEMO(Invocation invocation, MetaObject metaObject, String string) throws InvocationTargetException, IllegalAccessException {	
        void sql;	
        void metaStatementHandler;	
        void v0 = metaStatementHandler;	
        metaStatementHandler.setValue("delegate.rowBounds.offset", 0);	
        v0.setValue("delegate.rowBounds.limit", Integer.MAX_VALUE);	
        v0.setValue("delegate.boundSql.sql", sql);	
        return invocation.proceed();	
    }	
}	
	
