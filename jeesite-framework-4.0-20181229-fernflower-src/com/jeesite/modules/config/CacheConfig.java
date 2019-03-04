package com.jeesite.modules.config;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.shiro.l.I;	
import com.jeesite.modules.sys.web.AdviceController;	
import org.apache.shiro.cache.CacheManager;	
import org.apache.shiro.cache.ehcache.EhCacheManager;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.core.io.ClassPathResource;	
	
@Configuration	
public class CacheConfig {	
   @Bean(	
      name = {"shiroCacheManager"}	
   )	
   @ConditionalOnProperty(	
      name = {"redis.cacheAndSession"},	
      havingValue = "true",	
      matchIfMissing = false	
   )	
   public CacheManager shiroCacheManagerRedis() {	
      I a = new I();	
      a.ALLATORIxDEMO(Global.getProperty("redis.keyPrefix") + "cache_");	
      return a;	
   }	
	
   @Bean(	
      name = {"shiroCacheManager"}	
   )	
   @ConditionalOnProperty(	
      name = {"redis.cacheAndSession"},	
      havingValue = "false",	
      matchIfMissing = true	
   )	
   public CacheManager shiroCacheManager(EhCacheManagerFactoryBean ehCacheManager) {	
      EhCacheManager var10000 = new EhCacheManager();	
      var10000.setCacheManager(ehCacheManager.getObject());	
      return var10000;	
   }	
	
   @Bean(	
      name = {"ehCacheManager"}	
   )	
   public EhCacheManagerFactoryBean ehCacheManager() {	
      EhCacheManagerFactoryBean a = new EhCacheManagerFactoryBean();	
      a.setConfigLocation(new ClassPathResource(Global.getProperty("ehcache.configFile")));	
      return a;	
   }	
}	
