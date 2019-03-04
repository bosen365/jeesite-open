package com.jeesite.common.l.l;	
	
import com.jeesite.modules.config.TransactionConfig;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
	
public class K implements l {	
   private List B = new ArrayList();	
   private String G = null;	
   private boolean d = false;	
   private Map c = new HashMap();	
   private Map ALLATORIxDEMO = new HashMap();	
	
   public void ALLATORIxDEMO(l state) {	
      this.B.add(state.ALLATORIxDEMO());	
   }	
	
   public String ALLATORIxDEMO() {	
      String a = this.ALLATORIxDEMO() ? M.ALLATORIxDEMO(0) : this.G;	
      StringBuilder a = new StringBuilder();	
      a.append(j.ALLATORIxDEMO ("7\f?Z-Z)\fv\u000en") + a + """);	
      Iterator a = this.ALLATORIxDEMO.keySet().iterator();	
      Iterator var10000 = a;	
	
      while(var10000.hasNext()) {	
         a = (String)a.next();	
         var10000 = a;	
         a.append((new StringBuilder()).insert(0, j.ALLATORIxDEMO ("\u0002n")).append(a).append("": ").append(this.ALLATORIxDEMO.get(a)).toString());	
      }	
	
      for(var10000 = a = this.c.keySet().iterator(); var10000.hasNext(); var10000 = a) {	
         a = (String)a.next();	
         a.append((new StringBuilder()).insert(0, j.ALLATORIxDEMO ("\u0002n")).append(a).append("": "").append((String)this.c.get(a)).append(j.ALLATORIxDEMO ("n")).toString());	
      }	
	
      a.append(", list: [");	
      a = this.B.iterator();	
	
      for(var10000 = a; var10000.hasNext(); var10000 = a) {	
         a.append((String)a.next() + j.ALLATORIxDEMO ("`"));	
      }	
	
      if (this.B.size() > 0) {	
         a.deleteCharAt(a.length() - 1);	
      }	
	
      a.append(" ]}");	
      return com.jeesite.common.l.C.H(a.toString());	
   }	
	
   public void ALLATORIxDEMO(String name, String val) {	
      this.c.put(name, val);	
   }	
	
   public K(boolean state, int var2) {	
      this.d = state;	
      this.G = M.ALLATORIxDEMO(var2);	
   }	
	
   public void ALLATORIxDEMO(String name, long val) {	
      this.ALLATORIxDEMO.put(name, val);	
   }	
	
   public K(boolean state, String var2) {	
      this.d = state;	
      this.G = var2;	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return this.d;	
   }	
	
   public K(boolean var1) {	
      this.d = var1;	
   }	
}	
