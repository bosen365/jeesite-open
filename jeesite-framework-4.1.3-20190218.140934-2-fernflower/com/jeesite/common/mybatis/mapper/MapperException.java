package com.jeesite.common.mybatis.mapper;	
	
public class MapperException extends RuntimeException {	
   private static final long serialVersionUID = 1L;	
	
   public MapperException(Throwable cause) {	
      super(cause);	
   }	
	
   public MapperException() {	
   }	
	
   public MapperException(String message, Throwable cause) {	
      super(message, cause);	
   }	
	
   public MapperException(String message) {	
      super(message);	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 3 ^ 3;	
      int var10001 = 5 << 4 ^ 1 << 1;	
      int var10002 = (3 ^ 5) << 4 ^ 3 << 1;	
      int var10003 = (s = (String)s).length();	
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
}	
