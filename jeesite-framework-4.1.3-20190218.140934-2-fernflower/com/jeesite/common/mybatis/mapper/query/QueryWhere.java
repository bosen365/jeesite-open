package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.List;	
import java.util.Map;	
import java.util.Map.Entry;	
import org.apache.commons.lang3.BooleanUtils;	
import org.hyperic.sigar.FileSystemMap;	
	
public class QueryWhere extends LinkedHashMap {	
   private boolean disableAutoAddStatusWhere;	
   private boolean disableAutoAddCorpCodeWhere;	
   private BaseEntity entity;	
   private static final long serialVersionUID = 1L;	
	
   public QueryWhere andBracket(String columnName, QueryType queryType, Object value) {	
      this.add(QueryAndor.AND_BRACKET, columnName, queryType, value, 1);	
      return this;	
   }	
	
   public QueryWhere or(String columnName, QueryType queryType, Object value) {	
      this.add(QueryAndor.OR, columnName, queryType, value, 1);	
      return this;	
   }	
	
   // $FF: synthetic method	
   private QueryWhere add(QueryAndor andor, String columnName, QueryType queryType, Object value, Integer num) {	
      if (andor == null) {	
         return this;	
      } else {	
         String a = (new StringBuilder()).insert(0, columnName).append("#").append(queryType).append(num).toString();	
         this.put(a, new QueryWhereEntity(a, andor, columnName, queryType, value));	
         return this;	
      }	
   }	
	
   public QueryWhere and(String columnName, QueryType queryType, Object value, Integer num) {	
      this.add(QueryAndor.AND, columnName, queryType, value, num);	
      return this;	
   }	
	
   // $FF: synthetic method	
   private Map addEntityWhere(BaseEntity entity, Object table, StringBuilder sql, String tableAlias, String paramPrefix, boolean isMainEntity) {	
      Map a = MapUtils.newHashMap();	
      if (entity != null) {	
         int a = false;	
         List a = ListUtils.newArrayList();	
         Iterator var10 = MapperHelper.getColumns(table, a).iterator();	
	
         while(true) {	
            Column a;	
            String a;	
            Object a;	
            boolean a;	
            QueryType a;	
            label142:	
            do {	
               label123:	
               while(true) {	
                  Iterator var10000 = var10;	
	
                  while(true) {	
                     while(var10000.hasNext()) {	
                        a = (Column)var10.next();	
                        a.put(a.name(), a);	
                        if (!a.isQuery()) {	
                           continue label123;	
                        }	
	
                        if (StringUtils.equals(a.name(), "corp_code")) {	
                           if (!Global.isUseCorpModel()) {	
                              var10000 = var10;	
                              continue;	
                           }	
	
                           if (this.disableAutoAddCorpCodeWhere) {	
                              var10000 = var10;	
                              continue;	
                           }	
                        }	
	
                        if (!StringUtils.equals(a.name(), "corp_name")) {	
                           boolean var17;	
                           label131: {	
                              a = MapperHelper.getAttrName(a);	
                              a = ReflectUtils.invokeGetter(entity, a);	
                              a = false;	
                              a = a.queryType();	
                              if (a instanceof String) {	
                                 if (StringUtils.isNotBlank((String)a) || a.isForce()) {	
                                    a = true;	
                                    var17 = isMainEntity;	
                                    break label131;	
                                 }	
                              } else if (a != null) {	
                                 a = true;	
                              }	
	
                              var17 = isMainEntity;	
                           }	
	
                           if (var17 && "status".equals(a.name()) && !a && !this.disableAutoAddStatusWhere) {	
                              if (sql.length() != 0) {	
                                 sql.append(" AND ");	
                              }	
	
                              if (StringUtils.isNotBlank(tableAlias)) {	
                                 sql.append(tableAlias + ".");	
                              }	
	
                              sql.append((new StringBuilder()).insert(0, a.name()).append(" != #{").toString());	
                              if (StringUtils.isNotBlank(paramPrefix)) {	
                                 sql.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
                              }	
	
                              sql.append("STATUS_DELETE}");	
                           }	
	
                           if (a.isPK() && entity instanceof TreeEntity && BooleanUtils.toBoolean(((TreeEntity)entity).getIsQueryChildren())) {	
                              a = true;	
                           }	
                           continue label142;	
                        }	
	
                        var10000 = var10;	
                     }	
	
                     return a;	
                  }	
               }	
            } while(!a);	
	
            if (QueryType.IN == a || QueryType.NOT_IN == a || QueryType.IS_NULL == a || QueryType.IS_NOT_NULL == a) {	
               throw new MapperException("@Column(queryType=暂时不支持IN、NOT_IN、IS_NULL、IS_NOT_NULL，请使用sqlMap.getWhere()实现。)");	
            }	
	
            if (a instanceof String) {	
               String a = "";	
               if (StringUtils.isNotBlank(a.valuePrefix())) {	
                  a = (new StringBuilder()).insert(0, a).append(a.valuePrefix()).toString();	
               }	
	
               a = (new StringBuilder()).insert(0, a).append(a).toString();	
               if (StringUtils.isNotBlank(a.valueSuffux())) {	
                  a = (new StringBuilder()).insert(0, a).append(a.valueSuffux()).toString();	
               }	
	
               a = (new StringBuilder()).insert(0, "where#").append(a.name()).append("#").append(a).append("1").toString();	
               entity.getSqlMap().add(a, a);	
               a = (new StringBuilder()).insert(0, "qlMap.").append(a).toString();	
            }	
	
            if (sql.length() != 0) {	
               sql.append(" AND ");	
            }	
	
            if (a.isPK() && a) {	
               sql.append("(");	
            }	
	
            if (StringUtils.isNotBlank(tableAlias)) {	
               sql.append((new StringBuilder()).insert(0, tableAlias).append(".").toString());	
            }	
	
            sql.append((new StringBuilder()).insert(0, a.name()).append(" ").toString());	
            sql.append((new StringBuilder()).insert(0, a.operator()).append(" ").toString());	
            sql.append("#{");	
            if (StringUtils.isNotBlank(paramPrefix)) {	
               sql.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
            }	
	
            sql.append((new StringBuilder()).insert(0, a).append(MapperHelper.getColumnParamSuffix(a)).toString());	
            sql.append("}");	
            if (a.isPK() && a) {	
               a = (new StringBuilder()).insert(0, "where#").append(a.name()).append("#").append(a).append("1#ISQC").toString();	
               entity.getSqlMap().add(a, (new StringBuilder()).insert(0, "%,").append(a).append(",%").toString());	
               a = (new StringBuilder()).insert(0, "qlMap.").append(a).toString();	
               sql.append(" OR ");	
               if (StringUtils.isNotBlank(tableAlias)) {	
                  sql.append((new StringBuilder()).insert(0, tableAlias).append(".parent_code LIKE ").toString());	
               }	
	
               sql.append("#{");	
               if (StringUtils.isNotBlank(paramPrefix)) {	
                  sql.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
               }	
	
               sql.append((new StringBuilder()).insert(0, a).append(MapperHelper.getColumnParamSuffix(a)).toString());	
               sql.append("}");	
               sql.append(")");	
            }	
         }	
      } else {	
         return a;	
      }	
   }	
	
   public String toSql() {	
      Table var10001 = MapperHelper.getTable(this.entity);	
      return this.addWhere(var10001, var10001.alias(), (String)null);	
   }	
	
   public QueryWhere andBracket(String columnName, QueryType queryType, Object value, Integer num) {	
      this.add(QueryAndor.AND_BRACKET, columnName, queryType, value, num);	
      return this;	
   }	
	
   public QueryWhere endBracket() {	
      this.add(QueryAndor.END_BRACKET, (String)null, (QueryType)null, (Object)null, 1);	
      return this;	
   }	
	
   public String toSql(String tableAlias, String paramPrefix) {	
      return this.addWhere(MapperHelper.getTable(this.entity), tableAlias, paramPrefix);	
   }	
	
   public Object getValue(String columnName, QueryType queryType, Integer num) {	
      QueryWhereEntity a;	
      return (a = (QueryWhereEntity)this.get(columnName + "#" + queryType + num)) != null ? a.getValue() : null;	
   }	
	
   public Object getValue(String columnName, QueryType queryType) {	
      return this.getValue(columnName, queryType, 1);	
   }	
	
   public QueryWhere disableAutoAddCorpCodeWhere() {	
      this.disableAutoAddCorpCodeWhere = true;	
      return this;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 5;	
      int var10001 = 5 << 3 ^ 1;	
      int var10002 = 5 << 4 ^ (2 ^ 5) << 1;	
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
	
   public QueryWhere and(String columnName, QueryType queryType, Object value) {	
      this.add(QueryAndor.AND, columnName, queryType, value, 1);	
      return this;	
   }	
	
   public QueryWhere disableAutoAddStatusWhere() {	
      this.disableAutoAddStatusWhere = true;	
      return this;	
   }	
	
   public QueryWhere orBracket(String columnName, QueryType queryType, Object value) {	
      this.add(QueryAndor.OR_BRACKET, columnName, queryType, value, 1);	
      return this;	
   }	
	
   // $FF: synthetic method	
   private String addWhere(Table t, String tableAlias, String paramPrefix) {	
      Map a = null;	
      StringBuilder a = new StringBuilder();	
      a = this.addEntityWhere(this.entity, t, a, tableAlias, paramPrefix, true);	
      Iterator var6;	
      Iterator var10000 = var6 = this.entrySet().iterator();	
	
      while(var10000.hasNext()) {	
         QueryWhereEntity a = (QueryWhereEntity)((Entry)var6.next()).getValue();	
         var10000 = var6;	
         a.addSql(a, paramPrefix, tableAlias, a);	
      }	
	
      JoinTable[] var7 = t.joinTable();	
      int var15 = var7.length;	
	
      int var9;	
      for(int var16 = var9 = 0; var16 < var15; var16 = var9) {	
         JoinTable a;	
         String a = MapperHelper.getAttrName(a = var7[var9]);	
         BaseEntity a = null;	
         BaseEntity var17;	
         if ("this".equals(a)) {	
            a = "";	
            var17 = a = this.entity;	
         } else {	
            var17 = a = (BaseEntity)ReflectUtils.invokeGetter(this.entity, a);	
         }	
	
         if (var17 != null) {	
            if (StringUtils.isNotBlank(paramPrefix)) {	
               a = (new StringBuilder()).insert(0, paramPrefix).append(".").append(a).toString();	
            }	
	
            StringBuilder a = new StringBuilder();	
            a = this.addEntityWhere(a, a, a, a.alias(), a, false);	
	
            Iterator var14;	
            for(var10000 = var14 = a.getSqlMap().getWhere().entrySet().iterator(); var10000.hasNext(); var10000 = var14) {	
               ((QueryWhereEntity)((Entry)var14.next()).getValue()).addSql(a, a, a.alias(), a);	
            }	
	
            if (StringUtils.isNotBlank(a)) {	
               if (StringUtils.isNotBlank(a.toString())) {	
                  a.append(" AND ");	
               }	
	
               a.append(a);	
            }	
         }	
	
         ++var9;	
      }	
	
      MapperHelper.addExtWhere(a, this.entity, t);	
      return a.toString();	
   }	
	
   public QueryWhere or(String columnName, QueryType queryType, Object value, Integer num) {	
      this.add(QueryAndor.OR, columnName, queryType, value, num);	
      return this;	
   }	
	
   public QueryWhere endBracket(Integer num) {	
      this.add(QueryAndor.END_BRACKET, (String)null, (QueryType)null, (Object)null, num);	
      return this;	
   }	
	
   public QueryWhere(BaseEntity var1) {	
      this.entity = var1;	
   }	
	
   public QueryWhere orBracket(String columnName, QueryType queryType, Object value, Integer num) {	
      this.add(QueryAndor.OR_BRACKET, columnName, queryType, value, num);	
      return this;	
   }	
}	
