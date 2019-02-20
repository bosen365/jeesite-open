package com.jeesite.modules.sys.utils;	
	
import javax.servlet.http.HttpServletRequest;	
	
public class ValidCodeUtils {	
   public static final String VALID_CODE = "validCode";	
	
   public static boolean validate(HttpServletRequest request, String validCode) {	
      return validate(request, "validCode", validCode);	
   }	
	
   public static boolean validate(HttpServletRequest request, String attributeName, String validCode, boolean isClear) {	
      String a = (String)request.getSession().getAttribute(attributeName);	
      int a = validCode != null && validCode.equalsIgnoreCase(a);	
      if (a && isClear) {	
         request.getSession().removeAttribute(attributeName);	
      }	
	
      return a;	
   }	
	
   public static boolean validate(HttpServletRequest request, String attributeName, String validCode) {	
      return validate(request, attributeName, validCode, true);	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 5 << 1;	
      int var10001 = 5 << 3;	
      int var10002 = (3 ^ 5) << 4 ^ 1;	
      int var10003 = (s = (String)s).length();	
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
}	
