package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.Who;	
	
public class H extends E {	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      RowBounds var10000;	
      StringBuilder a;	
      label21: {	
         a = new StringBuilder(sql.length() + 40);	
         a.append("SELECT ");	
         if (rowBounds.getOffset() > 0) {	
            a.append(" SKIP ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type"))) {	
               var10000 = rowBounds;	
               a.append("10Z0");	
               break label21;	
            }	
	
            a.append(rowBounds.getOffset());	
         }	
	
         var10000 = rowBounds;	
      }	
	
      if (var10000.getLimit() > 0) {	
         a.append(" FIRST ");	
         a.append(rowBounds.getLimit());	
      }	
	
      a.append(" * FROM ( ");	
      a.append(sql);	
      a.append(" ) TEMP_T");	
      return a.toString();	
   }	
}	
