package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.modules.config.TransactionConfig;	
	
public class K implements com.jeesite.common.mybatis.l.l {	
   public String ALLATORIxDEMO(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {	
      int a = offset > 0;	
      return (new StringBuffer(sql.length() + 10)).append(sql).insert(sql.toLowerCase().indexOf(com.jeesite.common.l.l.j.ALLATORIxDEMO ("])B)M8")) + 6, a ? (new StringBuilder()).insert(0, " limit ").append(offsetPlaceholder).append(com.jeesite.common.l.l.j.ALLATORIxDEMO ("l")).append(limitPlaceholder).toString() : (new StringBuilder()).insert(0, " top ").append(limitPlaceholder).toString()).toString();	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, Integer.toString(offset), Integer.toString(limit));	
   }	
}	
