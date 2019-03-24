/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.interceptor;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.mybatis.l.h;	
import com.jeesite.common.mybatis.l.w.A;	
import com.jeesite.common.mybatis.l.w.H;	
import com.jeesite.common.mybatis.l.w.I;	
import com.jeesite.common.mybatis.l.w.e;	
import com.jeesite.common.mybatis.l.w.j;	
import com.jeesite.common.mybatis.l.w.l;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import java.sql.Connection;	
import java.sql.PreparedStatement;	
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.mapping.ParameterMapping;	
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.session.RowBounds;	
	
public class PaginationHelper {	
    private static final Map<String, h> dialectMap = MapUtils.newHashMap();	
	
    public static String getCountSql(String sql) {	
        return PaginationHelper.getDialect().ALLATORIxDEMO(sql);	
    }	
	
    public static h getDialect() {	
        String a = Global.getJdbcType();	
        h a2 = dialectMap.get(a);	
        if (a2 == null) {	
            if ("oracle".equals(a) || "dameng".equals(a)) {	
                a2 = new j();	
            } else if ("mysql".equals(a) || "mariadb".equals(a) || "sqlite".equals(a)) {	
                a2 = new l();	
            } else if ("mssql".equals(a)) {	
                a2 = new com.jeesite.common.mybatis.l.w.h();	
            } else if ("mssql2012".equals(a) || "derby".equals(a)) {	
                a2 = new H();	
            } else if ("hsqldb".equals(a) || "h2".equals(a) || "phoeni".equals(a) || "postgresql".equals(a) || "highgo".equals(a)) {	
                a2 = new I();	
            } else if ("informix".equals(a)) {	
                a2 = new e();	
            } else if ("db2".equals(a)) {	
                a2 = new A();	
            }	
            dialectMap.put(a, a2);	
        }	
        if (a2 == null) {	
            throw new RuntimeException("Mybatis dialect error.");	
        }	
        return a2;	
    }	
	
    public static String getPageSql(String sql, RowBounds rowBounds) {	
        return PaginationHelper.getDialect().ALLATORIxDEMO(sql, rowBounds);	
    }	
	
    public static Page<Object> getPageParameter(Object parameterObject) {	
        block5 : {	
            block4 : {	
                try {	
                    if (!(parameterObject instanceof Page)) break block4;	
                    return (Page)parameterObject;	
                }	
                catch (Exception a) {	
                    return null;	
                }	
            }	
            if (!(parameterObject instanceof Map)) break block5;	
            return (Page)((Map)parameterObject).get("page");	
        }	
        return (Page)ReflectUtils.getFieldValue(parameterObject, "page");	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public static long getTotalCount(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {	
        PreparedStatement a = null;	
        ResultSet a2 = null;	
        try {	
            String a3 = PaginationHelper.getCountSql(sql);	
            a = connection.prepareStatement(a3);	
            BoundSql a4 = new BoundSql(mappedStatement.getConfiguration(), a3, boundSql.getParameterMappings(), boundSql.getParameterObject());	
            new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), a4).setParameters(a);	
            a2 = a.executeQuery();	
            long a5 = 0L;	
            if (a2.next()) {	
                a5 = a2.getLong(1);	
            }	
            long l2 = a5;	
            return l2;	
        }	
        finally {	
            if (a2 != null) {	
                a2.close();	
            }	
            if (a != null) {	
                a.close();	
            }	
        }	
    }	
}	
	
