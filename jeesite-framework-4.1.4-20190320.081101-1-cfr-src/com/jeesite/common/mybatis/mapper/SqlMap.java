/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper;	
	
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
import org.hyperic.sigar.DirStat;	
	
public final class SqlMap	
extends LinkedHashMap<String, Object> {	
    private QueryDataScope dataScope;	
    private QueryColumn column;	
    public static final String UPDATE_TREE_DATA_EXT_SQL = "updateTreeDataExtSql";	
    private QueryTable table;	
    private QueryWhere where;	
    private QueryOrder order;	
    private static final long serialVersionUID = 1L;	
	
    public final QueryOrder getOrder() {	
        return this.order;	
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
	
    public final QueryTable getTable() {	
        return this.table;	
    }	
	
    public final QueryColumn getColumn() {	
        return this.column;	
    }	
	
    public final Object add(String key, Object value) {	
        return this.put(key, value);	
    }	
	
    public final Object updateTreeDataExtSql(String sqlScript) {	
        return this.put(UPDATE_TREE_DATA_EXT_SQL, (Object)sqlScript);	
    }	
	
    @Override	
    public final void putAll(Map<? extends String, ? extends Object> m2) {	
        if (m2 != null) {	
            Iterator<Map.Entry<? extends String, ? extends Object>> iterator;	
            Iterator<Map.Entry<? extends String, ? extends Object>> iterator2 = iterator = m2.entrySet().iterator();	
            while (iterator2.hasNext()) {	
                Map.Entry<? extends String, ? extends Object> a = iterator.next();	
                this.put(a.getKey(), a.getValue());	
                iterator2 = iterator;	
            }	
        }	
    }	
	
    public final QueryWhere getWhere() {	
        return this.where;	
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
	
    public final QueryDataScope getDataScope() {	
        return this.dataScope;	
    }	
}	
	
