package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.j.e;	
import com.jeesite.common.utils.SpringUtils;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Properties;	
import java.util.concurrent.ConcurrentHashMap;	
import net.oschina.j2cache.Cache;	
import net.oschina.j2cache.CacheExpiredListener;	
import net.oschina.j2cache.CacheProvider;	
import org.hyperic.sigar.FileSystemMap;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisProvider implements CacheProvider {	
   private RedisTemplate redisTemplate;	
   private String namespace;	
   protected ConcurrentHashMap caches = new ConcurrentHashMap();	
   private String storage;	
	
   public void start(Properties props) {	
      this.namespace = props.getProperty(e.ALLATORIxDEMO("\u0006}\u0005y\u001bl\t\u007f\r"));	
      this.storage = props.getProperty("storage");	
      this.redisTemplate = (RedisTemplate)SpringUtils.getBean(e.ALLATORIxDEMO("vZ_\t\u007f\u0000y:y\fu\u001bH\rq\u0018p\th\r"));	
   }	
	
   public Cache buildCache(String region, long timeToLiveInSeconds, CacheExpiredListener listener) {	
      return this.buildCache(region, listener);	
   }	
	
   public int level() {	
      return 2;	
   }	
	
   public Cache buildCache(String region, CacheExpiredListener listener) {	
      Cache a;	
      if ((a = (Cache)this.caches.get(region)) == null) {	
         Class var4 = SpringRedisProvider.class;	
         synchronized(SpringRedisProvider.class) {	
            Object a;	
            if ((a = (Cache)this.caches.get(region)) == null) {	
               SpringRedisProvider var10000;	
               if ("hash".equalsIgnoreCase(this.storage)) {	
                  a = new SpringRedisHashCache(this.namespace, region, this.redisTemplate);	
                  var10000 = this;	
               } else {	
                  a = new SpringRedisGenericCache(this.namespace, region, this.redisTemplate);	
                  var10000 = this;	
               }	
	
               var10000.caches.put(region, a);	
            }	
	
            return (Cache)a;	
         }	
      } else {	
         return a;	
      }	
   }	
	
   public Collection regions() {	
      return Collections.emptyList();	
   }	
	
   public String name() {	
      return e.ALLATORIxDEMO("\u001ay\fu\u001b");	
   }	
	
   public void stop() {	
   }	
}	
