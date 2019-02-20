package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.ptql.QueryLoadException;	
	
public class QueryWhereEntity implements Serializable {	
   private static final long serialVersionUID = 1L;	
   private Object value;	
   private QueryAndor andor;	
   private String columnName;	
   private Object val;	
   private QueryType queryType;	
   private String key;	
	
   public Object getVal() {	
      return this.val;	
   }	
	
   public QueryType getQueryType() {	
      return this.queryType;	
   }	
	
   public QueryAndor getAndor() {	
      return this.andor;	
   }	
	
   public void addSql(StringBuilder sql, String paramPrefix, String tableAlias, Map columnMap) {	
      StringBuilder a = new StringBuilder();	
      if (QueryAndor.isLastBracket(sql.toString()) && QueryAndor.END_BRACKET == this.getAndor()) {	
         QueryAndor.removeLastBracket(sql);	
      } else {	
         if (!QueryAndor.isLastBracket(sql.toString())) {	
            QueryAndor.addAndor(sql, a, this.andor);	
         }	
	
         StringBuilder var10000;	
         label95: {	
            if (this.getQueryType() != null) {	
               if (this.getQueryType() == QueryType.IS_NULL || this.getQueryType() == QueryType.IS_NOT_NULL) {	
                  if (StringUtils.isNotBlank(tableAlias)) {	
                     a.append(tableAlias + ".");	
                  }	
	
                  var10000 = a;	
                  a.append(this.getColumnName());	
                  a.append((new StringBuilder()).insert(0, " ").append(this.getQueryType().operator()).toString());	
                  break label95;	
               }	
	
               boolean a;	
               if ((a = StringUtils.isNotBlank(ObjectUtils.toString(this.getVal()))) && this.getValue() instanceof List) {	
                  this.value = ((List)this.getValue()).toArray();	
               }	
	
               if (a && this.getValue() instanceof Object[]) {	
                  a = ((Object[])((Object[])this.getValue())).length > 0;	
               }	
	
               if (a || this.getQueryType().isForce()) {	
                  if (StringUtils.isNotBlank(tableAlias)) {	
                     a.append((new StringBuilder()).insert(0, tableAlias).append(".").toString());	
                  }	
	
                  a.append(this.getColumnName());	
                  a.append((new StringBuilder()).insert(0, " ").append(this.getQueryType().operator()).toString());	
                  if (this.getValue() instanceof Object[]) {	
                     Object[] a = (Object[])((Object[])this.getValue());	
                     a.append(" (");	
                     int a = 0;	
	
                     for(int var12 = a; var12 < a.length; var12 = a) {	
                        if (a != 0) {	
                           a.append(",");	
                        }	
	
                        a.append(" #{");	
                        if (StringUtils.isNotBlank(paramPrefix)) {	
                           a.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
                        }	
	
                        a.append((new StringBuilder()).insert(0, "sqlMap.where.").append(this.key).toString());	
                        a.append((new StringBuilder()).insert(0, ".val[").append(a).append("]").toString());	
                        Column a;	
                        if ((a = (Column)columnMap.get(this.getColumnName())) != null) {	
                           a.append(MapperHelper.getColumnParamSuffix(a));	
                        }	
	
                        ++a;	
                        a.append("}");	
                     }	
	
                     var10000 = a;	
                     a.append(" )");	
                     break label95;	
                  }	
	
                  a.append(" #{");	
                  if (StringUtils.isNotBlank(paramPrefix)) {	
                     a.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
                  }	
	
                  a.append((new StringBuilder()).insert(0, "sqlMap.where.").append(this.key).toString());	
                  a.append(".val");	
                  Column a;	
                  if ((a = (Column)columnMap.get(this.getColumnName())) != null) {	
                     a.append(MapperHelper.getColumnParamSuffix(a));	
                  }	
	
                  a.append("}");	
               }	
            }	
	
            var10000 = a;	
         }	
	
         String a;	
         if (!QueryAndor.isOnlyAndor(a = var10000.toString())) {	
            sql.append(a);	
         }	
	
      }	
   }	
	
   public String getColumnName() {	
      return this.columnName;	
   }	
	
   public Object getValue() {	
      return this.value;	
   }	
	
   public QueryWhereEntity(String key, QueryAndor andor, String columnName, QueryType queryType, Object value) {	
      this.key = key;	
      this.andor = andor;	
      this.columnName = EncodeUtils.sqlFilter(columnName);	
      this.queryType = queryType;	
      this.value = value;	
      if (queryType != null && value != null && value instanceof String) {	
         String a = "";	
         if (StringUtils.isNotBlank((String)value)) {	
            if (StringUtils.isNotBlank(queryType.valuePrefix())) {	
               a = (new StringBuilder()).insert(0, a).append(queryType.valuePrefix()).toString();	
            }	
	
            a = (new StringBuilder()).insert(0, a).append(value).toString();	
            if (StringUtils.isNotBlank(queryType.valueSuffux())) {	
               a = (new StringBuilder()).insert(0, a).append(queryType.valueSuffux()).toString();	
            }	
         }	
	
         this.val = a;	
      } else {	
         this.val = value;	
      }	
   }	
}	
