package com.jeesite.common.l;	
	
public class C {	
   public static String H(String input) {	
      StringBuilder a = new StringBuilder();	
      char[] var3;	
      int var4 = (var3 = input.toCharArray()).length;	
	
      int var5;	
      for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
         char a;	
         if ((a = var3[var5]) < 256) {	
            a.append(a);	
         } else {	
            a.append((new StringBuilder()).insert(0, com.jeesite.common.l.l.j.ALLATORIxDEMO ("r9")).append(Integer.toHexString(a & '\uffff')).toString());	
         }	
	
         ++var5;	
      }	
	
      return a.toString();	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 3 << 1;	
      int var10001 = 3 << 3 ^ 2 ^ 5;	
      int var10002 = (2 ^ 5) << 3;	
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
}	
