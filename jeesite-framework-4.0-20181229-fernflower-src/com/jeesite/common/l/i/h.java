package com.jeesite.common.l.i;	
	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
	
public class h {	
   private Map c = null;	
   private HttpServletRequest ALLATORIxDEMO = null;	
	
   public final com.jeesite.common.l.l.l ALLATORIxDEMO() {	
      String a = (String)this.c.get("fieldName");	
      com.jeesite.common.l.l.l a = null;	
      return "true".equals(this.c.get("isBase64")) ? D.ALLATORIxDEMO(this.ALLATORIxDEMO, this.ALLATORIxDEMO.getParameter(a), this.c) : g.ALLATORIxDEMO(this.ALLATORIxDEMO, this.c);	
   }	
	
   public h(HttpServletRequest request, Map var2) {	
      this.ALLATORIxDEMO = request;	
      this.c = var2;	
   }	
}	
