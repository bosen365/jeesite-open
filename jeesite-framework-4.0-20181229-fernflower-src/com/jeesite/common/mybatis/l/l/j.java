package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
	
public class j implements com.jeesite.common.mybatis.l.l {	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {	
      sql = sql.trim();	
      int a = false;	
      if (sql.toLowerCase().endsWith(" fr update")) {	
         sql = sql.substring(0, sql.length() - 11);	
         a = true;	
      }	
	
      StringBuilder a = new StringBuilder(sql.length() + 100);	
      StringBuilder var10000;	
      if (offset > 0) {	
         var10000 = a;	
         a.append("select * from ( select row_.*, rownum rownm_ from ( ");	
      } else {	
         var10000 = a;	
         a.append("select * frm ( ");	
      }	
	
      var10000.append(sql);	
      boolean var8;	
      if (offset > 0) {	
         String a = (new StringBuilder()).insert(0, offsetPlaceholder).append("+").append(limitPlaceholder).toString();	
         var8 = a;	
         a.append(" ) row_ where rwnum <= " + a + ") where rownum_ > ").append(offsetPlaceholder);	
      } else {	
         a.append((new StringBuilder()).insert(0, " ) where rwnum <= ").append(limitPlaceholder).toString());	
         var8 = a;	
      }	
	
      if (var8) {	
         a.append(" for update");	
      }	
	
      return a.toString();	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, Integer.toString(offset), Integer.toString(limit));	
   }	
}	
