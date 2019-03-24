/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.win32.FileVersion;	
	
public class QueryColumn	
implements Serializable {	
    private BaseEntity<?> entity;	
    private static final long serialVersionUID = 1L;	
	
    public QueryColumn(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 3 ^ 2;	
        (3 ^ 5) << 4 ^ 1 << 1;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ (2 << 2 ^ 1);	
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
	
    private /* synthetic */ void addEntityColumns(StringBuilder sql, List<Column> columns, String attrNamePrefix, String tableAlias) {	
        Iterator<Column> iterator;	
        Iterator<Column> iterator2 = iterator = columns.iterator();	
        while (iterator2.hasNext()) {	
            Column a = iterator.next();	
            String a2 = MapperHelper.getAttrName(a);	
            if (StringUtils.isNotBlank(attrNamePrefix) && !"this".equals(attrNamePrefix)) {	
                a2 = new StringBuilder().insert(0, attrNamePrefix).append(".").append(a2).toString();	
            }	
            if (StringUtils.contains((CharSequence)sql, new StringBuilder().insert(0, """).append(a2).append(""").toString())) {	
                iterator2 = iterator;	
                continue;	
            }	
            if (sql.length() != 0) {	
                sql.append(", ");	
            }	
            if (StringUtils.isNotBlank(tableAlias)) {	
                sql.append(tableAlias + ".");	
            }	
            sql.append(a.name());	
            sql.append(" AS ");	
            sql.append(""");	
            sql.append(a2);	
            sql.append(""");	
            iterator2 = iterator;	
        }	
    }	
	
    public String toSql() {	
        int n;	
        StringBuilder a = new StringBuilder();	
        Table a2 = MapperHelper.getTable(this.entity);	
        ArrayList<Column> a3 = ListUtils.newArrayList();	
        MapperHelper.getColumns(a2, a3);	
        this.addEntityColumns(a, a3, null, a2.alias());	
        JoinTable[] arrjoinTable = a2.joinTable();	
        int n2 = arrjoinTable.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            QueryColumn queryColumn;	
            JoinTable a4 = arrjoinTable[n];	
            a3 = ListUtils.newArrayList();	
            if (a4.columns().length > 0) {	
                queryColumn = this;	
                MapperHelper.getColumns(a4, a3);	
            } else {	
                MapperHelper.getColumns(MapperHelper.getTable(a4.entity()), a3);	
                queryColumn = this;	
            }	
            queryColumn.addEntityColumns(a, a3, MapperHelper.getAttrName(a4), a4.alias());	
            n3 = ++n;	
        }	
        StringBuilder stringBuilder = a;	
        stringBuilder.append(MapperHelper.getSqlMapValue(this.entity, a2.extColumnKeys(), ","));	
        return stringBuilder.toString();	
    }	
}	
	
