/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.m;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.web.e.j;	
import java.io.InputStream;	
import java.lang.annotation.Annotation;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.TypeHandler;	
import org.apache.ibatis.type.UnknownTypeHandler;	
import org.hyperic.sigar.ProcFd;	
	
public class MapperHelper {	
    public static String getAttrName(Column c2) {	
        return StringUtils.defaultIfBlank(c2.attrName(), StringUtils.camelCase(c2.name()));	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public static String getTableName(Table t, BaseEntity<?> entity) {	
        String[] a;	
        String a2 = StringUtils.replace(t.name(), "${_prefix}", Global.getTablePrefix());	
        if (entity != null && (a = StringUtils.substringsBetween(a2, "${", "}")) != null) {	
            int n;	
            String[] arrstring = a;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                void a3;	
                String a4 = arrstring[n];	
                String string = ObjectUtils.toString(ReflectUtils.invokeGetter(entity, a4), "");	
                a2 = StringUtils.replace(a2, "${" + a4 + "}", (String)a3);	
                n3 = ++n;	
            }	
        }	
        return a2;	
    }	
	
    public static String getAttrName(JoinTable t) {	
        return StringUtils.defaultIfBlank(t.attrName(), StringUtils.uncap(t.entity().getSimpleName()));	
    }	
	
    public static String getTableName(BaseEntity<?> entity) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entity), entity);	
    }	
	
    public static final Map<String, String> ck(InputStream inputStream) {	
        HashMap<String, String> a = MapUtils.newHashMap();	
        for (Map.Entry<String, String> a2 : j.ALLATORIxDEMO(j.ALLATORIxDEMO(inputStream)).entrySet()) {	
            String[] arrstring = new String[2];	
            arrstring[0] = "type";	
            arrstring[1] = "title";	
            if (!StringUtils.inString(a2.getKey(), arrstring)) continue;	
            a.put(a2.getKey(), a2.getValue());	
        }	
        return a;	
    }	
	
    public static Table getTable(BaseEntity<?> entity) {	
        return MapperHelper.getTable(entity.getClass());	
    }	
	
    public static void addExtWhere(StringBuilder sqlWhere, BaseEntity<?> entity, Table t) {	
        String a = MapperHelper.getSqlMapValue(entity, t.extWhereKeys());	
        if (StringUtils.isNotBlank(a)) {	
            if (sqlWhere.length() == 0) {	
                if (StringUtils.startsWithIgnoreCase(a = StringUtils.trim(a), "AND ")) {	
                    a = a.substring("AND ".length());	
                }	
                sqlWhere.replace(0, sqlWhere.length(), new StringBuilder().insert(0, " ").append(a).toString());	
                return;	
            }	
            sqlWhere.append(new StringBuilder().insert(0, " ").append(a).toString());	
        }	
    }	
	
    public static String getColumnName(Column c2) {	
        if (StringUtils.equals(MapperHelper.getDbName(), "mysql")) {	
            return new StringBuilder().insert(0, "`").append(c2.name()).append("`").toString();	
        }	
        return c2.name();	
    }	
	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys) {	
        return MapperHelper.getSqlMapValue(entity, sqlMapKeys, null);	
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
	
    public static String getDbName() {	
        try {	
            return Global.getDbName();	
        }	
        catch (Exception a) {	
            return "oracle";	
        }	
    }	
	
    public static String getSqlMapValue(BaseEntity<?> entity, String sqlMapKeys, String prefix) {	
        StringBuilder a = new StringBuilder();	
        String[] a2 = StringUtils.split(sqlMapKeys, ",");	
        if (a2 != null) {	
            int n;	
            String[] arrstring = a2;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                a.append(ObjectUtils.toStringIgnoreNull(entity.getSqlMap().get(StringUtils.trim(a3))));	
                n3 = ++n;	
            }	
        }	
        String a4 = a.toString();	
        if (StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(a4)) {	
            a4 = new StringBuilder().insert(0, prefix).append(" ").append(a4).toString();	
        }	
        return a4;	
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
                    a2 = new m(a3, attrName);	
                    columns.add((Column)a2);	
                }	
                n3 = ++n;	
            }	
        }	
        return columns;	
    }	
	
    public static List<Column> getColumns(Object t, List<Column> columns) {	
        return MapperHelper.getColumns(t, columns, null);	
    }	
	
    public static String getTableName(Class<?> entityClass) {	
        return MapperHelper.getTableName(MapperHelper.getTable(entityClass), null);	
    }	
	
    public static Table getTable(Class<?> entityClass) {	
        Table a = entityClass.getAnnotation(Table.class);	
        if (a == null) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entityClass).append(" 没有定义@Table注解.").toString());	
        }	
        return a;	
    }	
}	
	
