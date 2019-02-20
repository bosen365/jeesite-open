/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 *  org.apache.ibatis.mapping.BoundSql	
 *  org.apache.ibatis.mapping.MappedStatement	
 *  org.apache.ibatis.scripting.defaults.DefaultParameterHandler	
 *  org.apache.ibatis.session.Configuration	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.j.n.B;	
import com.jeesite.common.mybatis.j.n.D;	
import com.jeesite.common.mybatis.j.n.F;	
import com.jeesite.common.mybatis.j.n.H;	
import com.jeesite.common.mybatis.j.n.M;	
import com.jeesite.common.mybatis.j.n.e;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.sql.Connection;	
import java.sql.PreparedStatement;	
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
public class PaginationHelper {	
    private static final Map<String, com.jeesite.common.mybatis.j.e> dialectMap = MapUtils.newHashMap();	
	
    public static String getPageSql(String sql, RowBounds rowBounds) {	
        return PaginationHelper.getDialect().ALLATORIxDEMO(sql, rowBounds);	
    }	
	
    public static com.jeesite.common.mybatis.j.e getDialect() {	
        String a2 = Global.getJdbcType();	
        com.jeesite.common.mybatis.j.e a3 = dialectMap.get(a2);	
        if (a3 == null) {	
            if ("oracle".equals(a2) || "dameng".equals(a2)) {	
                a3 = new F();	
            } else if ("mysql".equals(a2) || "maradb".equals(a2) || "sqlite".equals(a2)) {	
                a3 = new m();	
            } else if ("mssql".equals(a2)) {	
                a3 = new e();	
            } else if ("mssql2012".equals(a2) || "derby".equals(a2)) {	
                a3 = new M();	
            } else if ("hsqldb".equals(a2) || "h2".equals(a2) || "phoenix".equals(a2) || "postgresql".equals(a2) || "highgo".equals(a2)) {	
                a3 = new D();	
            } else if ("nformx".equals(a2)) {	
                a3 = new H();	
            } else if ("db2".equals(a2)) {	
                a3 = new B();	
            }	
            dialectMap.put(a2, a3);	
        }	
        if (a3 == null) {	
            throw new RuntimeException("Mybatis dialect error.");	
        }	
        return a3;	
    }	
	
    public static Page<Object> getPageParameter(Object parameterObject) {	
        block5 : {	
            block4 : {	
                try {	
                    if (!(parameterObject instanceof Page)) break block4;	
                    return (Page)parameterObject;	
                }	
                catch (Exception a2) {	
                    return null;	
                }	
            }	
            if (!(parameterObject instanceof Map)) break block5;	
            return (Page)((Map)parameterObject).get("page");	
        }	
        return (Page)ReflectUtils.getFieldValue((Object)parameterObject, (String)"page");	
    }	
	
    public static String getCountSql(String sql) {	
        return PaginationHelper.getDialect().ALLATORIxDEMO(sql);	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public static long getTotalCount(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {	
        PreparedStatement a2 = null;	
        ResultSet a3 = null;	
        try {	
            String a4 = PaginationHelper.getCountSql(sql);	
            a2 = connection.prepareStatement(a4);	
            BoundSql a5 = new BoundSql(mappedStatement.getConfiguration(), a4, boundSql.getParameterMappings(), boundSql.getParameterObject());	
            new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), a5).setParameters(a2);	
            a3 = a2.executeQuery();	
            long a6 = 0L;	
            if (a3.next()) {	
                a6 = a3.getLong(1);	
            }	
            long l2 = a6;	
            return l2;	
        }	
        finally {	
            if (a3 != null) {	
                a3.close();	
            }	
            if (a2 != null) {	
                a2.close();	
            }	
        }	
    }	
}	
	
