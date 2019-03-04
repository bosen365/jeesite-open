package com.jeesite.common.l.i;	
	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.l.l.E;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.codec.binary.Base64;	
	
public final class D {	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(byte[] data, long length) {	
      return (long)data.length <= length;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 4 ^ (3 ^ 5) << 1;	
      int var10001 = (3 ^ 5) << 4 ^ 3 << 2 ^ 1;	
      int var10002 = 1 << 3 ^ 2 ^ 5;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
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
	
   // $FF: synthetic method	
   private static byte[] ALLATORIxDEMO(String content) {	
      return Base64.decodeBase64(content);	
   }	
	
   public static com.jeesite.common.l.l.l ALLATORIxDEMO(HttpServletRequest request, String content, Map conf) {	
      byte[] a = ALLATORIxDEMO(content);	
      long a = (Long)conf.get(g.ALLATORIxDEMO ("\u0004b\u0011P\u0000y\f"));	
      if (!ALLATORIxDEMO(a, a)) {	
         return new A(false, 1);	
      } else {	
         String a = E.H("JPG");	
         String a = com.jeesite.common.l.l.ALLATORIxDEMO((String)conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("]-X)~-Z$")), (String)conf.get(g.ALLATORIxDEMO ("e\u0000o\fm\bn\f")));	
         a = (new StringBuilder()).insert(0, a).append(a).toString();	
         String a = (String)conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("\\#A8~-Z$")) + a;	
         com.jeesite.common.l.l.l a;	
         if ((a = K.ALLATORIxDEMO(a, a)).ALLATORIxDEMO()) {	
            String a = request.getContextPath();	
            a.ALLATORIxDEMO(g.ALLATORIxDEMO ("\u001cq\u0005"), (new StringBuilder()).insert(0, a).append(com.jeesite.common.l.l.F(a)).toString());	
            a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("Z5^)"), a);	
            a.ALLATORIxDEMO(g.ALLATORIxDEMO ("l\u001bj\u000ej\u0007b\u0005"), "");	
         }	
	
         return a;	
      }	
   }	
}	
