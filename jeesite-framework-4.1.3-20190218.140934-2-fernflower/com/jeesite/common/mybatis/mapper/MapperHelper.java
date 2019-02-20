package com.jeesite.common.mybatis.mapper;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.web.e.F;	
import java.io.InputStream;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Map.Entry;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.UnknownTypeHandler;	
import org.hyperic.sigar.cmd.Tail;	
import org.hyperic.sigar.ptql.ProcessFinder;	
	
public class MapperHelper {	
   public static String getAttrName(Column c) {	
      return (String)StringUtils.defaultIfBlank(c.attrName(), StringUtils.camelCase(c.name()));	
   }	
	
   public static String getTableName(Table t, BaseEntity entity) {	
      String a = StringUtils.replace(t.name(), "${_preix}", Global.getTablePrefix());	
      String[] a;	
      if (entity != null && (a = StringUtils.substringsBetween(a, "${", "}")) != null) {	
         String[] var4 = a;	
         int var5 = a.length;	
	
         int var6;	
         for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
            String a = var4[var6];	
            String a = ObjectUtils.toString(ReflectUtils.invokeGetter(entity, a), "");	
            String var10001 = "${" + a + "}";	
            ++var6;	
            a = StringUtils.replace(a, var10001, a);	
         }	
      }	
	
      return a;	
   }	
	
   public static Table getTable(BaseEntity entity) {	
      return getTable(entity.getClass());	
   }	
	
   public static String getTableName(Class entityClass) {	
      return getTableName(getTable(entityClass), (BaseEntity)null);	
   }	
	
   public static String getColumnParamSuffix(Column c) {	
      StringBuilder a = new StringBuilder();	
      if (c.javaType() != Void.TYPE) {	
         a.append(",javaType=" + c.javaType().getName());	
      }	
	
      if (c.jdbcType() != JdbcType.UNDEFINED) {	
         a.append((new StringBuilder()).insert(0, ",jdbcType=").append(c.jdbcType()).toString());	
      }	
	
      if (c.typeHandler() != UnknownTypeHandler.class) {	
         a.append((new StringBuilder()).insert(0, ",tpeHandler=").append(c.typeHandler().getName()).toString());	
      }	
	
      return a.toString();	
   }	
	
   public static void addExtWhere(StringBuilder sqlWhere, BaseEntity entity, Table t) {	
      String a;	
      if (StringUtils.isNotBlank(a = getSqlMapValue(entity, t.extWhereKeys()))) {	
         if (sqlWhere.length() == 0) {	
            if (StringUtils.startsWithIgnoreCase(a = StringUtils.trim(a), "AND ")) {	
               a = a.substring("AND ".length());	
            }	
	
            sqlWhere.replace(0, sqlWhere.length(), (new StringBuilder()).insert(0, " ").append(a).toString());	
            return;	
         }	
	
         sqlWhere.append((new StringBuilder()).insert(0, " ").append(a).toString());	
      }	
	
   }	
	
   public static String getSqlMapValue(BaseEntity entity, String sqlMapKeys) {	
      return getSqlMapValue(entity, sqlMapKeys, (String)null);	
   }	
	
   public static String getAttrName(JoinTable t) {	
      return (String)StringUtils.defaultIfBlank(t.attrName(), StringUtils.uncap(t.entity().getSimpleName()));	
   }	
	
   public static String getColumnName(Column c) {	
      return StringUtils.equals(getDbName(), "mysql") ? (new StringBuilder()).insert(0, "`").append(c.name()).append("`").toString() : c.name();	
   }	
	
   public static String getDbName() {	
      try {	
         return Global.getDbName();	
      } catch (Exception var1) {	
         return "oracle";	
      }	
   }	
	
   public static String getTableName(BaseEntity entity) {	
      return getTableName(getTable(entity), entity);	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ (3 ^ 5) << 1;	
      int var10001 = (2 ^ 5) << 3 ^ 1;	
      int var10002 = 2 << 3 ^ 4;	
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
	
   public static Table getTable(Class entityClass) {	
      Table a;	
      if ((a = (Table)entityClass.getAnnotation(Table.class)) == null) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entityClass).append(" 没有定义@Table注解.").toString());	
      } else {	
         return a;	
      }	
   }	
	
   public static List getColumns(Object t, List columns, final String attrName) {	
      Column[] a = null;	
      Column[] var10000;	
      if (t instanceof Table) {	
         var10000 = a = ((Table)t).columns();	
      } else {	
         if (t instanceof JoinTable) {	
            a = ((JoinTable)t).columns();	
         }	
	
         var10000 = a;	
      }	
	
      if (var10000 != null) {	
         Column[] var4 = a;	
         int var5 = a.length;	
	
         int var6;	
         for(int var9 = var6 = 0; var9 < var5; var9 = var6) {	
            final Column a;	
            if ((a = var4[var6]).includeEntity() != Class.class) {	
               getColumns((Table)a.includeEntity().getAnnotation(Table.class), columns, a.attrName());	
            } else {	
               Column a = new Column() {	
                  public QueryType queryType() {	
                     return a.queryType();	
                  }	
	
                  public boolean isTreeName() {	
                     return a.isTreeName();	
                  }	
	
                  public Class annotationType() {	
                     return a.annotationType();	
                  }	
	
                  public JdbcType jdbcType() {	
                     return a.jdbcType();	
                  }	
	
                  public String comment() {	
                     return a.comment();	
                  }	
	
                  public boolean isUpdateForce() {	
                     return a.isUpdateForce();	
                  }	
	
                  public Class includeEntity() {	
                     return a.includeEntity();	
                  }	
	
                  public Class javaType() {	
                     return a.javaType();	
                  }	
	
                  public boolean isInsert() {	
                     return a.isInsert();	
                  }	
	
                  public String attrName() {	
                     return StringUtils.isNotBlank(attrName) ? (new StringBuilder()).insert(0, attrName).append(".").append(MapperHelper.getAttrName(a)).toString() : a.attrName();	
                  }	
	
                  public String name() {	
                     return a.name();	
                  }	
	
                  public boolean isUpdate() {	
                     return a.isUpdate();	
                  }	
	
                  public boolean isQuery() {	
                     return a.isQuery();	
                  }	
	
                  public boolean isPK() {	
                     return a.isPK();	
                  }	
	
                  public String label() {	
                     return a.label();	
                  }	
	
                  public Class typeHandler() {	
                     return a.typeHandler();	
                  }	
               };	
               columns.add(a);	
            }	
	
            ++var6;	
         }	
      }	
	
      return columns;	
   }	
	
   public static List getColumns(Object t, List columns) {	
      return getColumns(t, columns, (String)null);	
   }	
	
   public static String getSqlMapValue(BaseEntity entity, String sqlMapKeys, String prefix) {	
      StringBuilder a = new StringBuilder();	
      String[] a;	
      if ((a = StringUtils.split(sqlMapKeys, ",")) != null) {	
         String[] var5 = a;	
         int var6 = a.length;	
	
         int var7;	
         for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
            String a = var5[var7];	
            SqlMap var10001 = entity.getSqlMap();	
            String var10002 = StringUtils.trim(a);	
            ++var7;	
            a.append(ObjectUtils.toStringIgnoreNull(var10001.get(var10002)));	
         }	
      }	
	
      String a = a.toString();	
      if (StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(a)) {	
         a = (new StringBuilder()).insert(0, prefix).append(" ").append(a).toString();	
      }	
	
      return a;	
   }	
	
   public static final Map ck(InputStream inputStream) {	
      Map a = MapUtils.newHashMap();	
      Iterator var3 = F.ALLATORIxDEMO(F.ALLATORIxDEMO(inputStream)).entrySet().iterator();	
	
      while(var3.hasNext()) {	
         Entry a;	
         String var10000 = (String)(a = (Entry)var3.next()).getKey();	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "type";	
         var10001[1] = "title";	
         if (StringUtils.inString(var10000, var10001)) {	
            a.put(a.getKey(), a.getValue());	
         }	
      }	
	
      return a;	
   }	
}	
