/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
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
import org.hyperic.sigar.NetInterfaceStat;	
	
public final class SqlMap	
extends LinkedHashMap<String, Object> {	
    public static final String UPDATE_TREE_DATA_EXT_SQL = "updateTreeDataExtSql";	
    private QueryDataScope dataScope;	
    private static final long serialVersionUID = 1L;	
    private QueryOrder order;	
    private QueryTable table;	
    private QueryColumn column;	
    private QueryWhere where;	
	
    public final QueryWhere getWhere() {	
        return this.where;	
    }	
	
    @Override	
    public final Object put(String key, Object value) {	
        String[] arrstring = new String[5];	
        arrstring[0] = "column";	
        arrstring[1] = "table";	
        arrstring[2] = "where";	
        arrstring[3] = "order";	
        arrstring[4] = "dataScope";	
        if (StringUtils.inString(key, arrstring)) {	
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
	
    public final QueryColumn getColumn() {	
        return this.column;	
    }	
	
    public SqlMap(BaseEntity<?> entity) {	
        SqlMap sqlMap = this;	
        this.column = new QueryColumn(entity);	
        sqlMap.table = new QueryTable(entity);	
        this.where = new QueryWhere(entity);	
        this.order = new QueryOrder(entity);	
        this.dataScope = new QueryDataScope(entity);	
        super.put("column", this.column);	
        SqlMap sqlMap2 = this;	
        super.put("table", sqlMap2.table);	
        SqlMap sqlMap3 = this;	
        super.put("where", sqlMap3.where);	
        SqlMap sqlMap4 = this;	
        super.put("order", sqlMap4.order);	
        SqlMap sqlMap5 = this;	
        super.put("dataScope", sqlMap5.dataScope);	
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
	
    public final Object updateTreeDataExtSql(String sqlScript) {	
        return this.put(UPDATE_TREE_DATA_EXT_SQL, (Object)sqlScript);	
    }	
	
    public final Object add(String key, Object value) {	
        return this.put(key, value);	
    }	
	
    public final QueryDataScope getDataScope() {	
        return this.dataScope;	
    }	
	
    public final QueryTable getTable() {	
        return this.table;	
    }	
	
    public final QueryOrder getOrder() {	
        return this.order;	
    }	
}	
	
