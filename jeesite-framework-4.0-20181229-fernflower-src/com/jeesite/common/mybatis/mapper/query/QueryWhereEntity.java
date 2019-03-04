package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.Serializable;	
import java.util.List;	
	
public class QueryWhereEntity implements Serializable {	
   private String columnName;	
   private Object value;	
   private String key;	
   private Object val;	
   private QueryAndor andor;	
   private QueryType queryType;	
   private static final long serialVersionUID = 1L;	
	
   public Object getVal() {	
      return this.val;	
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
	
   public void addSql(StringBuilder sql, String paramPrefix, String tableAlias) {	
      StringBuilder a = new StringBuilder();	
      if (QueryAndor.isLastBracket(sql.toString()) && QueryAndor.END_BRACKET == this.getAndor()) {	
         QueryAndor.removeLastBracket(sql);	
      } else {	
         QueryWhereEntity var10000;	
         StringBuilder var10002;	
         label103: {	
            if (!QueryAndor.isLastBracket(sql.toString())) {	
               if (sql.length() != 0) {	
                  var10002 = (new StringBuilder()).append(" ");	
                  var10000 = this;	
                  a.append(var10002.append(this.getAndor().value()).append(" ").toString());	
                  break label103;	
               }	
	
               if (this.getAndor() == QueryAndor.AND_BRACKET || this.getAndor() == QueryAndor.OR_BRACKET) {	
                  a.append(" ( ");	
               }	
            }	
	
            var10000 = this;	
         }	
	
         StringBuilder var9;	
         label92: {	
            if (var10000.getQueryType() != null) {	
               if (this.getQueryType() == QueryType.IS_NULL || this.getQueryType() == QueryType.IS_NOT_NULL) {	
                  if (StringUtils.isNotBlank(tableAlias)) {	
                     a.append((new StringBuilder()).insert(0, tableAlias).append(".").toString());	
                  }	
	
                  var9 = a;	
                  a.append(this.getColumnName());	
                  a.append((new StringBuilder()).insert(0, " ").append(this.getQueryType().operator()).toString());	
                  break label92;	
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
	
                     for(int var10 = a; var10 < a.length; var10 = a) {	
                        if (a != 0) {	
                           a.append(",");	
                        }	
	
                        a.append(" #{");	
                        if (StringUtils.isNotBlank(paramPrefix)) {	
                           a.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
                        }	
	
                        a.append((new StringBuilder()).insert(0, "sqlMap.where.").append(this.key).toString());	
                        var10002 = (new StringBuilder()).insert(0, ".val[").append(a);	
                        String var10003 = "]}";	
                        ++a;	
                        a.append(var10002.append(var10003).toString());	
                     }	
	
                     var9 = a;	
                     a.append(" )");	
                     break label92;	
                  }	
	
                  a.append(" #{");	
                  if (StringUtils.isNotBlank(paramPrefix)) {	
                     a.append((new StringBuilder()).insert(0, paramPrefix).append(".").toString());	
                  }	
	
                  a.append((new StringBuilder()).insert(0, "sqlMap.where.").append(this.key).toString());	
                  a.append(".val");	
               }	
            }	
	
            var9 = a;	
         }	
	
         String a;	
         if (!QueryAndor.isOnlyAndor(a = var9.toString())) {	
            sql.append(a);	
         }	
	
      }	
   }	
	
   public QueryAndor getAndor() {	
      return this.andor;	
   }	
	
   public String getColumnName() {	
      return this.columnName;	
   }	
	
   public QueryType getQueryType() {	
      return this.queryType;	
   }	
}	
