package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.modules.config.CoreConfig;	
import org.apache.commons.lang3.StringUtils;	
	
public class C implements com.jeesite.common.mybatis.l.l {	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(String querySqlString, int offset, int limit, String limitPlaceholder) {	
      StringBuilder a = new StringBuilder();	
      String a = ALLATORIxDEMO(querySqlString);	
      String a = "";	
      String a = querySqlString.toLowerCase();	
      String a = querySqlString;	
      if (a.trim().startsWith("select")) {	
         int a = 6;	
         if (a.startsWith("select distinct")) {	
            a = "DISTINCT ";	
            a = 15;	
         }	
	
         a = querySqlString.substring(a);	
      }	
	
      a.append(a);	
      if (StringUtils.isEmpty(a)) {	
         a = "ORDER BY CURRENT_TIMESTAMP";	
      }	
	
      StringBuilder var10000 = new StringBuilder();	
      var10000.append("WITH query AS (SELECT ").append(a).append("TOP 100 PERCENT ").append(" ROW_NUMBER() OVER (").append(a).append(") as __row_number__, ").append(a).append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset + 1).append(" AND ").append(offset + limit).append(" ORDER BY __row_number__");	
      return var10000.toString();	
   }	
	
   static String ALLATORIxDEMO(String sql) {	
      int a;	
      return (a = sql.toLowerCase().indexOf("order by")) != -1 ? sql.substring(a) : "";	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, limit, Integer.toString(limit));	
   }	
}	
