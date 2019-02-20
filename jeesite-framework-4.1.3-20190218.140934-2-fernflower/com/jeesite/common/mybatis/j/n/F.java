package com.jeesite.common.mybatis.j.n;	
	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.ProcExe;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class F extends E {	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      int a = rowBounds.getOffset();	
      int a = rowBounds.getOffset() + rowBounds.getLimit();	
      StringBuilder a = new StringBuilder(sql.length() + 120);	
      if (a > 0) {	
         a.append("SELECT * FROM ( ");	
      }	
	
      if (a > 0) {	
         a.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");	
      }	
	
      a.append(sql);	
      if (a > 0) {	
         a.append(" ) TMP_PAGE WHERE ROWNUM <= ");	
         a.append(a);	
      }	
	
      StringBuilder var10000;	
      if (a > 0) {	
         a.append(" ) WHERE ROW_ID > ");	
         if (a > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type"))) {	
            var10000 = a;	
            a.append("1000");	
            return var10000.toString();	
         }	
	
         a.append(a);	
      }	
	
      var10000 = a;	
      return var10000.toString();	
   }	
}	
