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
import java.util.Map.Entry;	
import org.hyperic.sigar.FileSystemMap;	
	
public final class SqlMap extends LinkedHashMap {	
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
      return this.put((String)"updateTreeDataExtSql", sqlScript);	
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
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 3 ^ 2;	
      int var10001 = (2 ^ 5) << 3 ^ 2 ^ 5;	
      int var10002 = 1 << 3;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public final Object add(String key, Object value) {	
      return this.put(key, value);	
   }	
	
   public final QueryTable getTable() {	
      return this.table;	
   }	
	
   public final Object put(String key, Object value) {	
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
	
   public final QueryWhere getWhere() {	
      return this.where;	
   }	
}	
