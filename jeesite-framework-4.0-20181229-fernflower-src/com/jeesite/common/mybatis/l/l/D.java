package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.modules.sys.utils.ConfigUtils;	
	
public class D implements com.jeesite.common.mybatis.l.l {	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, Integer.toString(offset), Integer.toString(limit));	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String sql) {	
      StringBuilder a = (new StringBuilder(50)).append("rownumber() over(");	
      int a;	
      if ((a = sql.toLowerCase().indexOf("order by")) > 0 && !ALLATORIxDEMO(sql)) {	
         a.append(sql.substring(a));	
      }	
	
      a.append(") as rownumber_,");	
      return a.toString();	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {	
      int a = sql.toLowerCase().indexOf("select");	
      StringBuilder a = (new StringBuilder(sql.length() + 100)).append(sql.substring(0, a)).append("select * from ( select ").append(ALLATORIxDEMO(sql));	
      StringBuilder var10000;	
      if (ALLATORIxDEMO(sql)) {	
         var10000 = a;	
         a.append(" rw_.* frm ( ").append(sql.substring(a)).append(" ) as row_");	
      } else {	
         var10000 = a;	
         a.append(sql.substring(a + 6));	
      }	
	
      var10000.append(" ) as temp_ where rownumber_ ");	
      if (offset > 0) {	
         String a = (new StringBuilder()).insert(0, offsetPlaceholder).append("+").append(limitPlaceholder).toString();	
         var10000 = a;	
         a.append("between ").append(offsetPlaceholder).append("+1 and ").append(a);	
      } else {	
         var10000 = a;	
         a.append("<= ").append(limitPlaceholder);	
      }	
	
      return var10000.toString();	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String sql) {	
      return sql.toLowerCase().contains("select disinc");	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
}	
