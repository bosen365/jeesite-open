/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.mybatis.mapper.query.QueryTable;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.FileSystemMap;	
	
public final class SqlMap	
extends LinkedHashMap<String, Object> {	
    public static final String UPDATE_TREE_DATA_EXT_SQL = "updateTreeDataExtSql";	
    private static final long serialVersionUID = 1L;	
    private QueryOrder order;	
    private QueryColumn column;	
    private QueryWhere where;	
    private QueryDataScope dataScope;	
    private QueryTable table;	
	
    public final QueryOrder getOrder() {	
        return this.order;	
    }	
	
    public final QueryDataScope getDataScope() {	
        return this.dataScope;	
    }	
	
    public final Object updateTreeDataExtSql(String sqlScript) {	
        return this.put(UPDATE_TREE_DATA_EXT_SQL, (Object)sqlScript);	
    }	
	
    public SqlMap(BaseEntity<?> entity) {	
        SqlMap sqlMap = this;	
        this.column = new QueryColumn(entity);	
        sqlMap.table = new QueryTable(entity);	
        this.where = new QueryWhere(entity);	
        this.order = new QueryOrder(entity);	
        this.dataScope = new QueryDataScope(entity);	
        SqlMap sqlMap2 = this;	
        SqlMap sqlMap3 = this;	
        SqlMap sqlMap4 = this;	
        SqlMap sqlMap5 = this;	
        super.put("dataScope", sqlMap5.dataScope);	
        super.put("order", sqlMap4.order);	
        super.put("where", sqlMap3.where);	
        super.put("table", sqlMap2.table);	
        super.put("column", this.column);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 1 << 3;	
        int n4 = n2;	
        (2 ^ 5) << 3 ^ (2 ^ 5);	
        int n5 = (2 ^ 5) << 3 ^ 2;	
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
	
    public final Object add(String key, Object value) {	
        return this.put(key, value);	
    }	
	
    public final QueryTable getTable() {	
        return this.table;	
    }	
	
    @Override	
    public final Object put(String key, Object value) {	
        if (StringUtils.inString((String)key, (String[])new String[]{"column", "table", "where", "order", "dataScope"})) {	
            if ("column".equals(key) && this.column == null) {	
                return super.put(key, value);	
            }	
            if ("table".equals(key) && this.table == null) {	
                return super.put(key, value);	
            }	
            if ("where".equals(key) && this.where == null) {	
                return super.put(key, value);	
            }	
            if ("order".equals(key) && this.order == null) {	
                return super.put(key, value);	
            }	
            if ("dataScope".equals(key) && this.dataScope == null) {	
                return super.put(key, value);	
            }	
            return null;	
        }	
        return super.put(key, value);	
    }	
	
    @Override	
    public final void putAll(Map<? extends String, ? extends Object> m2) {	
        if (m2 != null) {	
            Iterator<Map.Entry<? extends String, ? extends Object>> iterator;	
            Iterator<Map.Entry<? extends String, ? extends Object>> iterator2 = iterator = m2.entrySet().iterator();	
            while (iterator2.hasNext()) {	
                Map.Entry<? extends String, ? extends Object> a2 = iterator.next();	
                this.put(a2.getKey(), a2.getValue());	
                iterator2 = iterator;	
            }	
        }	
    }	
	
    public final QueryColumn getColumn() {	
        return this.column;	
    }	
	
    public final QueryWhere getWhere() {	
        return this.where;	
    }	
}	
	
