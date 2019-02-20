package com.jeesite.common.j2cache.autoconfigure;	
	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import java.io.IOException;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.J2Cache;	
import net.oschina.j2cache.J2CacheBuilder;	
import net.oschina.j2cache.J2CacheConfig;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.context.annotation.PropertySource;	
import org.springframework.core.env.StandardEnvironment;	
	
@Configuration	
@ConditionalOnClass({J2Cache.class})	
@PropertySource({"classpath:config/j2cache.properties"})	
public class J2CacheAutoConfiguration {	
   @Bean	
   @DependsOn({"springUtils", "j2CacheConfig"})	
   public CacheChannel cacheChannel(J2CacheConfig j2CacheConfig) throws IOException {	
      return J2CacheBuilder.init(j2CacheConfig).getChannel();	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 4 ^ 3;	
      int var10001 = 4 << 4 ^ (3 ^ 5) << 1;	
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
	
   @Bean	
   public J2CacheConfig j2CacheConfig(StandardEnvironment environment) throws IOException {	
      return J2CacheConfigUtils.initFromConfig(environment);	
   }	
}	
