/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.Swap;	
	
public class QueryColumn	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private BaseEntity<?> entity;	
	
    public String toSql() {	
        int n;	
        StringBuilder a2 = new StringBuilder();	
        Table a3 = MapperHelper.getTable(this.entity);	
        ArrayList a4 = ListUtils.newArrayList();	
        this.addEntityColumns(a2, a4, null, a3.alias());	
        JoinTable[] arrjoinTable = a3.joinTable();	
        MapperHelper.getColumns(a3, a4);	
        int n2 = arrjoinTable.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            QueryColumn queryColumn;	
            JoinTable a5 = arrjoinTable[n];	
            a4 = ListUtils.newArrayList();	
            if (a5.columns().length > 0) {	
                queryColumn = this;	
                MapperHelper.getColumns(a5, a4);	
            } else {	
                MapperHelper.getColumns(MapperHelper.getTable(a5.entity()), a4);	
                queryColumn = this;	
            }	
            queryColumn.addEntityColumns(a2, a4, MapperHelper.getAttrName(a5), a5.alias());	
            n3 = ++n;	
        }	
        StringBuilder stringBuilder = a2;	
        stringBuilder.append(MapperHelper.getSqlMapValue(this.entity, a3.extColumnKeys(), ","));	
        return stringBuilder.toString();	
    }	
	
    private /* synthetic */ void addEntityColumns(StringBuilder sql, List<Column> columns, String attrNamePrefix, String tableAlias) {	
        Iterator<Column> iterator;	
        Iterator<Column> iterator2 = iterator = columns.iterator();	
        while (iterator2.hasNext()) {	
            Column a2 = iterator.next();	
            String a3 = MapperHelper.getAttrName(a2);	
            if (StringUtils.isNotBlank((CharSequence)attrNamePrefix) && !"this".equals(attrNamePrefix)) {	
                a3 = new StringBuilder().insert(0, attrNamePrefix).append(".").append(a3).toString();	
            }	
            if (StringUtils.contains((CharSequence)sql, (CharSequence)new StringBuilder().insert(0, """).append(a3).append(""").toString())) {	
                iterator2 = iterator;	
                continue;	
            }	
            if (sql.length() != 0) {	
                sql.append(", ");	
            }	
            if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                sql.append(tableAlias + ".");	
            }	
            sql.append(""");	
            sql.append(a3);	
            sql.append(""");	
            sql.append(" AS ");	
            sql.append(a2.name());	
            iterator2 = iterator;	
        }	
    }	
	
    public QueryColumn(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 3 ^ 5;	
        int n4 = n2;	
        3 << 3 ^ 4;	
        int n5 = (2 ^ 5) << 4 ^ 4 << 1;	
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
}	
	
