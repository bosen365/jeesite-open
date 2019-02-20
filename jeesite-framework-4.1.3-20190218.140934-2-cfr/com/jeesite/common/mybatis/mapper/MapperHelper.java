/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 *  org.apache.ibatis.type.JdbcType	
 *  org.apache.ibatis.type.TypeHandler	
 *  org.apache.ibatis.type.UnknownTypeHandler	
 */	
package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.E;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.web.e.F;	
import java.io.InputStream;	
import java.lang.annotation.Annotation;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.TypeHandler;	
import org.apache.ibatis.type.UnknownTypeHandler;	
import org.hyperic.sigar.cmd.Tail;	
import org.hyperic.sigar.ptql.ProcessFinder;	
	
public class MapperHelper {	
    public static String getAttrName(Column c2) {	
        return (String)StringUtils.defaultIfBlank((CharSequence)c2.attrName(), (CharSequence)StringUtils.camelCase((String)c2.name()));	
    }	
	
    public static String getTableName(Table t, BaseEntity<?> entity) {	
        String[] a2;	
        String a3 = StringUtils.replace((String)t.name(), (String)"${_preix}", (String)Global.getTablePrefix());	
        if (entity != null && (a2 = StringUtils.substringsBetween((String)a3, (String)"${", (String)"}")) != null) {	
            int n;	
            String[] arrstring = a2;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                void a4;	
                String a5 = arrstring[n];	
                String string = ObjectUtils.toString((Object)ReflectUtils.invokeGetter(entity, (String)a5), (String)"");	
                a3 = StringUtils.replace((String)a3, (String)("${" + a5 + "}"), (String)a4);	
                n3 = ++n;	
            }	
        }	
        return a3;	
    }	
	
    public static Table getTable(BaseEntity<?> entity) {	
        return MapperHelper.getTable(entity.getClass());	
    }	
	
    public static String getTableName(Class<?> entityClass) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entityClass), null);	
    }	
	
    public static String getColumnParamSuffix(Column c2) {	
        StringBuilder a2 = new StringBuilder();	
        if (c2.javaType() != Void.TYPE) {	
            a2.append(",javaType=" + c2.javaType().getName());	
        }	
        if (c2.jdbcType() != JdbcType.UNDEFINED) {	
            a2.append(new StringBuilder().insert(0, ",jdbcType=").append((Object)c2.jdbcType()).toString());	
        }	
        if (c2.typeHandler() != UnknownTypeHandler.class) {	
            a2.append(new StringBuilder().insert(0, ",tpeHandler=").append(c2.typeHandler().getName()).toString());	
        }	
        return a2.toString();	
    }	
	
    public static void addExtWhere(StringBuilder sqlWhere, BaseEntity<?> entity, Table t) {	
        String a2 = MapperHelper.getSqlMapValue(entity, t.extWhereKeys());	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            if (sqlWhere.length() == 0) {	
                if (StringUtils.startsWithIgnoreCase((CharSequence)(a2 = StringUtils.trim((String)a2)), (CharSequence)"AND ")) {	
                    a2 = a2.substring("AND ".length());	
                }	
                sqlWhere.replace(0, sqlWhere.length(), new StringBuilder().insert(0, " ").append(a2).toString());	
                return;	
            }	
            sqlWhere.append(new StringBuilder().insert(0, " ").append(a2).toString());	
        }	
    }	
	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys) {	
        return MapperHelper.getSqlMapValue(entity, sqlMapKeys, null);	
    }	
	
    public static String getAttrName(JoinTable t) {	
        return (String)StringUtils.defaultIfBlank((CharSequence)t.attrName(), (CharSequence)StringUtils.uncap((String)t.entity().getSimpleName()));	
    }	
	
    public static String getColumnName(Column c2) {	
        if (StringUtils.equals((CharSequence)MapperHelper.getDbName(), (CharSequence)"mysql")) {	
            return new StringBuilder().insert(0, "`").append(c2.name()).append("`").toString();	
        }	
        return c2.name();	
    }	
	
    public static String getDbName() {	
        try {	
            return Global.getDbName();	
        }	
        catch (Exception a2) {	
            return "oracle";	
        }	
    }	
	
    public static String getTableName(BaseEntity<?> entity) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entity), entity);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 << 3 ^ 4;	
        int n4 = n2;	
        (2 ^ 5) << 3 ^ 1;	
        int n5 = 4 << 4 ^ (3 ^ 5) << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public static Table getTable(Class<?> entityClass) {	
        Table a2 = entityClass.getAnnotation(Table.class);	
        if (a2 == null) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entityClass).append(" 没有定义@Table注解.").toString());	
        }	
        return a2;	
    }	
	
    public static List<Column> getColumns(Object t, List<Column> columns, String attrName) {	
        Column[] arrcolumn;	
        Column[] a2 = null;	
        if (t instanceof Table) {	
            arrcolumn = a2 = ((Table)t).columns();	
        } else {	
            if (t instanceof JoinTable) {	
                a2 = ((JoinTable)t).columns();	
            }	
            arrcolumn = a2;	
        }	
        if (arrcolumn != null) {	
            int n;	
            Column[] arrcolumn2 = a2;	
            int n2 = arrcolumn2.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                Annotation a3;	
                Column a4 = arrcolumn2[n];	
                if (a4.includeEntity() != Class.class) {	
                    a3 = a4.includeEntity().getAnnotation(Table.class);	
                    MapperHelper.getColumns(a3, columns, a4.attrName());	
                } else {	
                    a3 = new E(a4, attrName);	
                    columns.add((Column)a3);	
                }	
                n3 = ++n;	
            }	
        }	
        return columns;	
    }	
	
    public static List<Column> getColumns(Object t, List<Column> columns) {	
        return MapperHelper.getColumns(t, columns, null);	
    }	
	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys, String prefix) {	
        StringBuilder a2 = new StringBuilder();	
        String[] a3 = StringUtils.split((String)sqlMapKeys, (String)",");	
        if (a3 != null) {	
            int n;	
            String[] arrstring = a3;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a4 = arrstring[n];	
                a2.append(ObjectUtils.toStringIgnoreNull(entity.getSqlMap().get(StringUtils.trim((String)a4))));	
                n3 = ++n;	
            }	
        }	
        String a5 = a2.toString();	
        if (StringUtils.isNotBlank((CharSequence)prefix) && StringUtils.isNotBlank((CharSequence)a5)) {	
            a5 = new StringBuilder().insert(0, prefix).append(" ").append(a5).toString();	
        }	
        return a5;	
    }	
	
    public static final Map<String, String> ck(InputStream inputStream) {	
        HashMap a2 = MapUtils.newHashMap();	
        for (Map.Entry<String, String> a3 : F.ALLATORIxDEMO(F.ALLATORIxDEMO(inputStream)).entrySet()) {	
            if (!StringUtils.inString((String)a3.getKey(), (String[])new String[]{"type", "title"})) continue;	
            a2.put(a3.getKey(), a3.getValue());	
        }	
        return a2;	
    }	
}	
	
