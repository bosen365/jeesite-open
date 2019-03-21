/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.i;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.web.v.m;	
import java.io.InputStream;	
import java.lang.annotation.Annotation;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.TypeHandler;	
import org.apache.ibatis.type.UnknownTypeHandler;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class MapperHelper {	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys, String prefix) {	
        StringBuilder a = new StringBuilder();	
        String[] a2 = StringUtils.split((String)sqlMapKeys, (String)",");	
        if (a2 != null) {	
            int n;	
            String[] arrstring = a2;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                a.append(ObjectUtils.toStringIgnoreNull(entity.getSqlMap().get(StringUtils.trim((String)a3))));	
                n3 = ++n;	
            }	
        }	
        String a4 = a.toString();	
        if (StringUtils.isNotBlank((CharSequence)prefix) && StringUtils.isNotBlank((CharSequence)a4)) {	
            a4 = new StringBuilder().insert(0, prefix).append(" ").append(a4).toString();	
        }	
        return a4;	
    }	
	
    public static void addExtWhere(StringBuilder sqlWhere, BaseEntity<?> entity, Table t) {	
        String a = MapperHelper.getSqlMapValue(entity, t.extWhereKeys());	
        if (StringUtils.isNotBlank((CharSequence)a)) {	
            if (sqlWhere.length() == 0) {	
                if (StringUtils.startsWithIgnoreCase((CharSequence)(a = StringUtils.trim((String)a)), (CharSequence)"AND ")) {	
                    a = a.substring("AND ".length());	
                }	
                sqlWhere.replace(0, sqlWhere.length(), new StringBuilder().insert(0, " ").append(a).toString());	
                return;	
            }	
            sqlWhere.append(new StringBuilder().insert(0, " ").append(a).toString());	
        }	
    }	
	
    public static String getAttrName(Column c2) {	
        return (String)StringUtils.defaultIfBlank((CharSequence)c2.attrName(), (CharSequence)StringUtils.camelCase((String)c2.name()));	
    }	
	
    public static List<Column> getColumns(Object t, List<Column> columns, String attrName) {	
        Column[] arrcolumn;	
        Column[] a = null;	
        if (t instanceof Table) {	
            arrcolumn = a = ((Table)t).columns();	
        } else {	
            if (t instanceof JoinTable) {	
                a = ((JoinTable)t).columns();	
            }	
            arrcolumn = a;	
        }	
        if (arrcolumn != null) {	
            int n;	
            Column[] arrcolumn2 = a;	
            int n2 = arrcolumn2.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                Annotation a2;	
                Column a3 = arrcolumn2[n];	
                if (a3.includeEntity() != Class.class) {	
                    a2 = a3.includeEntity().getAnnotation(Table.class);	
                    MapperHelper.getColumns(a2, columns, a3.attrName());	
                } else {	
                    a2 = new i(a3, attrName);	
                    columns.add((Column)a2);	
                }	
                n3 = ++n;	
            }	
        }	
        return columns;	
    }	
	
    public static Table getTable(Class<?> entityClass) {	
        Table a = entityClass.getAnnotation(Table.class);	
        if (a == null) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entityClass).append(" 没有定义@Table注解.").toString());	
        }	
        return a;	
    }	
	
    public static String getColumnName(Column c2) {	
        if (StringUtils.equals((CharSequence)MapperHelper.getDbName(), (CharSequence)"mysql")) {	
            return new StringBuilder().insert(0, "`").append(c2.name()).append("`").toString();	
        }	
        return c2.name();	
    }	
	
    public static String getColumnParamSuffix(Column c2) {	
        StringBuilder a = new StringBuilder();	
        if (c2.javaType() != Void.TYPE) {	
            a.append(",javaType=" + c2.javaType().getName());	
        }	
        if (c2.jdbcType() != JdbcType.UNDEFINED) {	
            a.append(new StringBuilder().insert(0, ",jdbcType=").append((Object)c2.jdbcType()).toString());	
        }	
        if (c2.typeHandler() != UnknownTypeHandler.class) {	
            a.append(new StringBuilder().insert(0, ",typeHandler=").append(c2.typeHandler().getName()).toString());	
        }	
        return a.toString();	
    }	
	
    public static final Map<String, String> ck(InputStream inputStream) {	
        HashMap a = MapUtils.newHashMap();	
        for (Map.Entry<String, String> a2 : m.ALLATORIxDEMO(m.ALLATORIxDEMO(inputStream)).entrySet()) {	
            if (!StringUtils.inString((String)a2.getKey(), (String[])new String[]{"type", "title"})) continue;	
            a.put(a2.getKey(), a2.getValue());	
        }	
        return a;	
    }	
	
    public static String getAttrName(JoinTable t) {	
        return (String)StringUtils.defaultIfBlank((CharSequence)t.attrName(), (CharSequence)StringUtils.uncap((String)t.entity().getSimpleName()));	
    }	
	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys) {	
        return MapperHelper.getSqlMapValue(entity, sqlMapKeys, null);	
    }	
	
    public static String getTableName(Class<?> entityClass) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entityClass), null);	
    }	
	
    public static String getTableName(Table t, BaseEntity<?> entity) {	
        String[] a;	
        String a2 = StringUtils.replace((String)t.name(), (String)"${_prefix}", (String)Global.getTablePrefix());	
        if (entity != null && (a = StringUtils.substringsBetween((String)a2, (String)"${", (String)"}")) != null) {	
            int n;	
            String[] arrstring = a;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                void a3;	
                String a4 = arrstring[n];	
                String string = ObjectUtils.toString((Object)ReflectUtils.invokeGetter(entity, (String)a4), (String)"");	
                a2 = StringUtils.replace((String)a2, (String)("${" + a4 + "}"), (String)a3);	
                n3 = ++n;	
            }	
        }	
        return a2;	
    }	
	
    public static List<Column> getColumns(Object t, List<Column> columns) {	
        return MapperHelper.getColumns(t, columns, null);	
    }	
	
    public static String getDbName() {	
        try {	
            return Global.getDbName();	
        }	
        catch (Exception a) {	
            return "oracle";	
        }	
    }	
	
    public static Table getTable(BaseEntity<?> entity) {	
        return MapperHelper.getTable(entity.getClass());	
    }	
	
    public static String getTableName(BaseEntity<?> entity) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entity), entity);	
    }	
}	
	
