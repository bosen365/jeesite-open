package com.jeesite.common.j2cache.cache.support.redis;	
	
import java.io.UnsupportedEncodingException;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import net.oschina.j2cache.Level2Cache;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.pager.PageFetchException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisGenericCache implements Level2Cache {	
   private static final Logger log = LoggerFactory.getLogger(SpringRedisGenericCache.class);	
   private String namespace;	
   private RedisTemplate redisTemplate;	
   private String region;	
	
   public boolean exists(String key) {	
      return (Boolean)this.redisTemplate.execute((redis) -> {	
         return redis.exists(this._key(key));	
      });	
   }	
	
   public List getBytes(Collection keys) {	
      return (List)this.redisTemplate.execute((redis) -> {	
         byte[][] a = (byte[][])keys.stream().map((k) -> {	
            return this._key(k);	
         }).toArray((x$0) -> {	
            byte[][] var10000 = new byte[x$0][];	
            boolean var10002 = true;	
            return var10000;	
         });	
         return redis.mGet(a);	
      });	
   }	
	
   public SpringRedisGenericCache(String namespace, String region, RedisTemplate redisTemplate) {	
      if (region == null || region.isEmpty()) {	
         region = "_";	
      }	
	
      this.namespace = namespace;	
      this.redisTemplate = redisTemplate;	
      this.region = this.getRegionName(region);	
   }	
	
   public void setBytes(Map bytes) {	
      bytes.forEach((k, v) -> {	
         this.setBytes(k, v);	
      });	
   }	
	
   public void clear() {	
      this.keys().stream().forEach((k) -> {	
         this.redisTemplate.delete(k);	
      });	
   }	
	
   // $FF: synthetic method	
   private String getRegionName(String region) {	
      if (this.namespace != null && !this.namespace.isEmpty()) {	
         region = (new StringBuilder()).insert(0, this.namespace).append(":").append(region).toString();	
      }	
	
      return region;	
   }	
	
   public void setBytes(String key, byte[] bytes) {	
      this.redisTemplate.execute((redis) -> {	
         redis.set(this._key(key), bytes);	
         return null;	
      });	
   }	
	
   public void evict(String... keys) {	
      String[] var2 = keys;	
      int var3 = keys.length;	
	
      int var4;	
      for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
         String a = var2[var4];	
         ++var4;	
         this.redisTemplate.execute((redis) -> {	
            byte[][] var10001 = new byte[1][];	
            boolean var10003 = true;	
            var10001[0] = this._key(a);	
            return redis.del(var10001);	
         });	
      }	
	
   }	
	
   // $FF: synthetic method	
   private byte[] _key(String key) {	
      try {	
         byte[] var2 = (new StringBuilder()).insert(0, this.region).append(":").append(key).toString().getBytes("utf-8");	
         return var2;	
      } catch (UnsupportedEncodingException var4) {	
         var4.printStackTrace();	
         return (new StringBuilder()).insert(0, this.region).append(":").append(key).toString().getBytes();	
      }	
   }	
	
   public byte[] getBytes(String key) {	
      return (byte[])this.redisTemplate.execute((redis) -> {	
         return redis.get(this._key(key));	
      });	
   }	
	
   public void setBytes(String key, byte[] bytes, long timeToLiveInSeconds) {	
      if (timeToLiveInSeconds <= 0L) {	
         Logger var10000 = log;	
         String var10001 = "Invalid timeToLiveInSeconds value : Od , skipped it.";	
         Object[] var10002 = new Object[1];	
         boolean var10004 = true;	
         var10002[0] = timeToLiveInSeconds;	
         var10000.debug(String.format(var10001, var10002));	
         this.setBytes(key, bytes);	
      } else {	
         this.redisTemplate.opsForValue().getOperations().execute((redis) -> {	
            redis.setEx(this._key(key), (long)((int)timeToLiveInSeconds), bytes);	
            return null;	
         });	
      }	
   }	
	
   public void setBytes(Map bytes, long timeToLiveInSeconds) {	
      bytes.forEach((k, v) -> {	
         this.setBytes(k, v, timeToLiveInSeconds);	
      });	
   }	
	
   public Collection keys() {	
      Set a = this.redisTemplate.keys((new StringBuilder()).insert(0, this.region).append(":*").toString());	
      List a = new ArrayList(a.size());	
      Iterator var3;	
      Iterator var10000 = var3 = a.iterator();	
	
      while(var10000.hasNext()) {	
         String a = (String)var3.next();	
         var10000 = var3;	
         a.add(a);	
      }	
	
      return a;	
   }	
}	
