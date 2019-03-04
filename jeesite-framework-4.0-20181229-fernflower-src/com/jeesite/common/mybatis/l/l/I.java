package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.common.entity.Extend;	
	
public class I implements com.jeesite.common.mybatis.l.l {	
   public boolean ALLATORIxDEMO() {	
      return true;	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, int limit) {	
      return this.ALLATORIxDEMO(sql, offset, Integer.toString(offset), Integer.toString(limit));	
   }	
	
   public String ALLATORIxDEMO(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {	
      StringBuilder a = (new StringBuilder()).insert(0, sql);	
      a = offset <= 0 ? a.append(com.jeesite.common.l.j.ALLATORIxDEMO ("y>0?0&y")).append(limitPlaceholder) : a.append(" limit ").append(limitPlaceholder).append(com.jeesite.common.l.j.ALLATORIxDEMO ("r64?!<&y")).append(offsetPlaceholder);	
      return a.toString();	
   }	
}	
