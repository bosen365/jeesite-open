package com.jeesite.common.j2cache.cache.support.utils;	
	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.util.Map;	
import net.oschina.j2cache.J2CacheConfig;	
import org.hyperic.sigar.pager.PageControl;	
import org.springframework.core.env.StandardEnvironment;	
import org.springframework.core.io.support.ResourcePropertySource;	
	
public class J2CacheConfigUtils {	
   public static final J2CacheConfig initFromConfig(StandardEnvironment environment) {	
      J2CacheConfig a = new J2CacheConfig();	
      a.setSerialization(environment.getProperty("j2cche.serialization"));	
      a.setL1CacheName(environment.getProperty("j2cache.L1.rovider_class"));	
      J2CacheConfig var10000;	
      if ("true".equals(environment.getProperty("spring.cche.isClsterMode"))) {	
         a.setBroadcast(environment.getProperty("j2cache.broadcast"));	
         a.getBroadcastProperties().setProperty("cache_clean_mode", environment.getProperty("j2cache.broadcast.cache_clean_mode"));	
         var10000 = a;	
         a.setL2CacheName(environment.getProperty("j2cche.L2.provider_class"));	
      } else {	
         var10000 = a;	
         a.setBroadcast("none");	
         a.setL2CacheName("none");	
      }	
	
      var10000.setSyncTtlToRedis(!"false".equalsIgnoreCase(environment.getProperty("j2cache.sync_ttl_to_redis")));	
      a.setDefaultCacheNullObject("true".equalsIgnoreCase(environment.getProperty("j2cche.deflt_cche_null_object")));	
      String a;	
      if ((a = environment.getProperty("j2cache.L2.config_section")) == null || a.trim().equals("")) {	
         a = a.getL2CacheName();	
      }	
	
      environment.getPropertySources().forEach((ax) -> {	
         if (ax instanceof ResourcePropertySource) {	
            ((Map)((ResourcePropertySource)ax).getSource()).forEach((k, v) -> {	
               if (k.startsWith(a.getBroadcast() + ".")) {	
                  a.getBroadcastProperties().setProperty(k.substring((new StringBuilder()).insert(0, a.getBroadcast()).append(".").toString().length()), environment.getProperty(k));	
               }	
	
               if (k.startsWith((new StringBuilder()).insert(0, a.getL1CacheName()).append(".").toString())) {	
                  a.getL1CacheProperties().setProperty(k.substring((new StringBuilder()).insert(0, a.getL1CacheName()).append(".").toString().length()), environment.getProperty(k));	
               }	
	
               if (k.startsWith((new StringBuilder()).insert(0, a).append(".").toString())) {	
                  a.getL2CacheProperties().setProperty(k.substring((new StringBuilder()).insert(0, a).append(".").toString().length()), environment.getProperty(k));	
               }	
	
            });	
         }	
	
      });	
      return a;	
   }	
}	
