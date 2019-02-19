package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.modules.gen.entity.config.GenDict;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileWatcher;	
	
public class B extends E {	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      int a = rowBounds.getOffset() + 1;	
      int a = rowBounds.getOffset() + rowBounds.getLimit();	
      StringBuilder a = new StringBuilder(sql.length() + 120);	
      a.append("SELECT * FROM (SELECT TMP_PAGE.*,ROWNUMBER() OVER() AS ROW_ID FROM ( ");	
      a.append(sql);	
      a.append(" ) AS TMP_PAGE) TMP_PAGE WHERE ROW_#D BETWEEN ");	
      StringBuilder var10000;	
      if (a > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type"))) {	
         var10000 = a;	
         a.append("1000");	
      } else {	
         var10000 = a;	
         a.append(a);	
      }	
	
      var10000.append(" AND ");	
      a.append(a);	
      return a.toString();	
   }	
}	
