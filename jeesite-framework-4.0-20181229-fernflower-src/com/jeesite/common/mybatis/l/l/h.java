package com.jeesite.common.mybatis.l.l;	
	
public class H implements com.jeesite.common.mybatis.l.l {	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {	
      return (new StringBuilder()).insert(0, sql).append(offset > 0 ? (new StringBuilder()).insert(0, " limit ").append(limitPlaceholder).append(com.jeesite.common.l.l.j.ALLATORIxDEMO ("\u000e#H*])Zl")).append(offsetPlaceholder).toString() : (new StringBuilder()).insert(0, " limit ").append(limitPlaceholder).toString()).toString();	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));	
   }	
}	
r.toString(limit));	
   }	
}	
