package com.jeesite.common.l;	
	
import com.jeesite.common.l.d.K;	
import com.jeesite.common.l.i.h;	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.l.l.i;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
	
public class j {	
   private HttpServletRequest B;	
   private String G;	
   private I d;	
   private String c;	
   private String ALLATORIxDEMO;	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 2 ^ 5;	
      int var10001 = 5 << 4 ^ 2 << 2 ^ 1;	
      int var10002 = 5 << 4 ^ 1 << 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public boolean ALLATORIxDEMO(String name) {	
      return name.matches("^[a-zA-Z_]A[\w0-9_]*$");	
   }	
	
   public j(HttpServletRequest request, String rootPath) {	
      this(request, rootPath, request.getParameter("action"));	
   }	
	
   public String H() {	
      if (this.ALLATORIxDEMO != null && i.G.containsKey(this.ALLATORIxDEMO)) {	
         if (this.d != null && this.d.ALLATORIxDEMO()) {	
            com.jeesite.common.l.l.l a = null;	
            int a = i.ALLATORIxDEMO(this.ALLATORIxDEMO);	
            Map a = null;	
            com.jeesite.common.l.l.l var10000;	
            switch(a) {	
            case 0:	
               while(false) {	
               }	
	
               return this.d.ALLATORIxDEMO().toString();	
            case 1:	
            case 2:	
            case 3:	
            case 4:	
               a = this.d.ALLATORIxDEMO(a);	
               var10000 = (new h(this.B, a)).ALLATORIxDEMO();	
               break;	
            case 5:	
               a = this.d.ALLATORIxDEMO(a);	
               String[] a = this.B.getParameterValues((String)a.get("fieldName"));	
               var10000 = (new com.jeesite.common.l.d.h(this.B, a)).ALLATORIxDEMO(a);	
               break;	
            case 6:	
            case 7:	
               a = this.d.ALLATORIxDEMO(a);	
               int a = this.ALLATORIxDEMO();	
               a = (new K(a)).ALLATORIxDEMO(this.B, a);	
            default:	
               var10000 = a;	
            }	
	
            return var10000.ALLATORIxDEMO();	
         } else {	
            return (new A(false, 102)).ALLATORIxDEMO();	
         }	
      } else {	
         return (new A(false, 101)).ALLATORIxDEMO();	
      }	
   }	
	
   public String ALLATORIxDEMO() {	
      String a;	
      if ((a = this.B.getParameter("callback")) != null) {	
         return !this.ALLATORIxDEMO(a) ? (new A(false, 401)).ALLATORIxDEMO() : (new StringBuilder()).insert(0, a).append("(").append(this.H()).append(");").toString();	
      } else {	
         return this.H();	
      }	
   }	
	
   public int ALLATORIxDEMO() {	
      String a = this.B.getParameter("start");	
	
      try {	
         return Integer.parseInt(a);	
      } catch (Exception var3) {	
         return 0;	
      }	
   }	
	
   public j(HttpServletRequest request, String var2, String var3) {	
      this.B = null;	
      this.c = null;	
      this.G = null;	
      this.ALLATORIxDEMO = null;	
      this.d = null;	
      this.B = request;	
      this.c = var2;	
      this.ALLATORIxDEMO = var3;	
      this.G = request.getContextPath();	
      this.d = I.ALLATORIxDEMO(this.c, this.G, request.getRequestURI());	
   }	
}	
