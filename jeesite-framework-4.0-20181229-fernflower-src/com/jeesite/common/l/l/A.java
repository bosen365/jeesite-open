package com.jeesite.common.l.l;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.Map;	
	
public class A implements l {	
   private Map d = new HashMap();	
   private boolean c = false;	
   private String ALLATORIxDEMO = null;	
	
   public void ALLATORIxDEMO(String info) {	
      this.ALLATORIxDEMO = info;	
   }	
	
   public A(boolean var1) {	
      this.ALLATORIxDEMO(var1);	
   }	
	
   public String toString() {	
      String a = null;	
      String a = this.ALLATORIxDEMO() ? M.ALLATORIxDEMO(0) : this.ALLATORIxDEMO;	
      StringBuilder a = new StringBuilder();	
      a.append("{"state": "" + a + """);	
      Iterator a = this.d.keySet().iterator();	
	
      for(Iterator var10000 = a; var10000.hasNext(); var10000 = a) {	
         a = (String)a.next();	
         a.append((new StringBuilder()).insert(0, ","").append(a).append("": "").append((String)this.d.get(a)).append(""").toString());	
      }	
	
      a.append("}");	
      return com.jeesite.common.l.C.H(a.toString());	
   }	
	
   public A() {	
      this.c = true;	
   }	
	
   public void ALLATORIxDEMO(int infoCode) {	
      this.ALLATORIxDEMO = M.ALLATORIxDEMO(infoCode);	
   }	
	
   public void ALLATORIxDEMO(boolean state) {	
      this.c = state;	
   }	
	
   public String ALLATORIxDEMO() {	
      return this.toString();	
   }	
	
   public void ALLATORIxDEMO(String name, long val) {	
      this.ALLATORIxDEMO(name, val + "");	
   }	
	
   public void ALLATORIxDEMO(String name, String val) {	
      this.d.put(name, val);	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return this.c;	
   }	
	
   public A(boolean state, int var2) {	
      this.ALLATORIxDEMO(state);	
      this.ALLATORIxDEMO = M.ALLATORIxDEMO(var2);	
   }	
	
   public A(boolean state, String var2) {	
      this.ALLATORIxDEMO(state);	
      this.ALLATORIxDEMO = var2;	
   }	
}	
