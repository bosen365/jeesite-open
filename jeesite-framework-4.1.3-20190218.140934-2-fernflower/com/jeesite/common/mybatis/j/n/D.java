package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.cache.JedisUtils;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class D extends E {	
   public static boolean ALLATORIxDEMO = true;	
	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      StringBuilder a = new StringBuilder(sql.length() + 20);	
      a.append(sql);	
      if (rowBounds.getLimit() > 0) {	
         a.append(" LIMIT ");	
         a.append(rowBounds.getLimit());	
      }	
	
      StringBuilder var10000;	
      if (rowBounds.getOffset() > 0) {	
         a.append(" OFFSET ");	
         if (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type"))) {	
            var10000 = a;	
            a.append("1000");	
            return var10000.toString();	
         }	
	
         a.append(rowBounds.getOffset());	
      }	
	
      var10000 = a;	
      return var10000.toString();	
   }	
}	
