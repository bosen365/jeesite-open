package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.shiro.j.A;	
import com.jeesite.common.shiro.j.c;	
import net.oschina.j2cache.CacheChannel;	
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ImportAutoConfiguration({J2CacheAutoConfiguration.class, J2CacheSpringRedisAutoConfiguration.class})	
public class CacheAutoConfiguration {	
   @Bean(	
      name = {"shiroCacheManager"}	
   )	
   @ConditionalOnProperty(	
      name = {"spring.cache.isClusterMode"},	
      havingValue = "true",	
      matchIfMissing = false	
   )	
   public com.jeesite.common.shiro.j.E shiroCacheManagerRedis(CacheChannel cacheChannel) {	
      return new A(cacheChannel);	
   }	
	
   @Bean(	
      name = {"shiroCacheManager"}	
   )	
   @ConditionalOnProperty(	
      name = {"spring.cache.isClusterMode"},	
      havingValue = "false",	
      matchIfMissing = true	
   )	
   public com.jeesite.common.shiro.j.E shiroCacheManager(CacheChannel cacheChannel) {	
      return new c(cacheChannel);	
   }	
}	
