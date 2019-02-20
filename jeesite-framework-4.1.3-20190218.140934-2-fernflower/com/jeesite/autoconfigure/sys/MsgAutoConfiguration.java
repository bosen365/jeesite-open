package com.jeesite.autoconfigure.sys;	
	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.msg.service.support.MsgPushServiceSupport;	
import com.jeesite.modules.msg.service.support.MsgTemplateServiceSupport;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ConditionalOnProperty(	
   name = {"msg.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgAutoConfiguration {	
   @Bean	
   @ConditionalOnMissingBean	
   public MsgTemplateService msgTemplateService() {	
      return new MsgTemplateServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public MsgPushService msgPushService() {	
      return new MsgPushServiceSupport();	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ (3 ^ 5) << 1;	
      int var10001 = 4 << 4 ^ 3;	
      int var10002 = 2 ^ 5;	
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
