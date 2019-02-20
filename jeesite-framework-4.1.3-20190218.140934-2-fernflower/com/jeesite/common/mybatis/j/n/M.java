package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.mybatis.mapper.MapperException;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.pager.SortAttribute;	
	
public class m extends E {	
   public static boolean ALLATORIxDEMO = true;	
	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      StringBuilder a = new StringBuilder(sql.length() + 14);	
      a.append(sql);	
      StringBuilder var10000;	
      if (rowBounds.getOffset() == 0) {	
         a.append(" LIMIT ");	
         var10000 = a;	
         a.append(rowBounds.getLimit());	
      } else {	
         a.append(" LIMIT ");	
         if (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type"))) {	
            var10000 = a;	
            a.append("1000");	
         } else {	
            var10000 = a;	
            a.append(rowBounds.getOffset());	
         }	
	
         var10000.append(",");	
         a.append(rowBounds.getLimit());	
         var10000 = a;	
      }	
	
      return var10000.toString();	
   }	
}	
