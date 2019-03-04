package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public abstract class BaseService {	
   protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 4 << 1;	
      int var10001 = (2 ^ 5) << 4 ^ 1 << 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var3;	
      int var10002 = var3 = var10003 - 1;	
      char[] var1 = var10004;	
      byte var4 = 4;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var5 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var5 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public static String text(String code, String... params) {	
      return Global.getText(code, params);	
   }	
}	
