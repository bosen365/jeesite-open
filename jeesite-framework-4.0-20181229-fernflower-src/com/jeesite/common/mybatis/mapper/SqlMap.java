package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.l.C;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.mybatis.mapper.query.QueryTable;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.Map;	
import java.util.Map.Entry;	
	
public final class SqlMap extends LinkedHashMap {	
   public static final String UPDATE_TREE_DATA_EXT_SQL = "updateTreeDataExtSql";	
   private QueryWhere where;	
   private QueryColumn column;	
   private QueryDataScope dataScope;	
   private static final long serialVersionUID = 1L;	
   private QueryTable table;	
   private QueryOrder order;	
	
   public final Object add(String key, Object value) {	
      String[] var10001 = new String[5];	
      boolean var10003 = true;	
      var10001[0] = "column";	
      var10001[1] = "table";	
      var10001[2] = "where";	
      var10001[3] = "order";	
      var10001[4] = "dataScope";	
      if (StringUtils.inString(key, var10001)) {	
         if ("column".equals(key) && this.column == null) {	
            return super.put(key, value);	
         } else if ("table".equals(key) && this.table == null) {	
            return super.put(key, value);	
         } else if ("where".equals(key) && this.where == null) {	
            return super.put(key, value);	
         } else if ("order".equals(key) && this.order == null) {	
            return super.put(key, value);	
         } else {	
            return "dataScope".equals(key) && this.dataScope == null ? super.put(key, value) : null;	
         }	
      } else {	
         return super.put(key, value);	
      }	
   }	
	
   public final QueryWhere getWhere() {	
      return this.where;	
   }	
	
   public final QueryOrder getOrder() {	
      return this.order;	
   }	
	
   public final void putAll(Map m) {	
      Iterator var2;	
      if (m != null) {	
         for(Iterator var10000 = var2 = m.entrySet().iterator(); var10000.hasNext(); var10000 = var2) {	
            Entry a = (Entry)var2.next();	
            this.put((String)a.getKey(), a.getValue());	
         }	
      }	
	
   }	
	
   public final QueryColumn getColumn() {	
      return this.column;	
   }	
	
   public final Object updateTreeDataExtSql(String sqlScript) {	
      return this.add("updateTreeDataExtSql", sqlScript);	
   }	
	
   public final QueryTable getTable() {	
      return this.table;	
   }	
	
   public final QueryDataScope getDataScope() {	
      return this.dataScope;	
   }	
	
   public SqlMap(BaseEntity entity) {	
      this.column = new QueryColumn(entity);	
      this.table = new QueryTable(entity);	
      this.where = new QueryWhere(entity);	
      this.order = new QueryOrder(entity);	
      this.dataScope = new QueryDataScope(entity);	
      super.put("column", this.column);	
      super.put("table", this.table);	
      super.put("where", this.where);	
      super.put("order", this.order);	
      super.put("dataScope", this.dataScope);	
   }	
	
   public final Object put(String key, Object value) {	
      return this.add(key, value);	
   }	
}	
