package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import net.oschina.j2cache.Level2Cache;	
import org.hyperic.sigar.ProcExe;	
import org.springframework.data.redis.core.HashOperations;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisHashCache implements Level2Cache {	
   private String region;	
   private String namespace;	
   private RedisTemplate redisTemplate;	
	
   public void setBytes(String key, byte[] bytes) {	
      this.redisTemplate.opsForHash().getOperations().execute((redis) -> {	
         redis.set(this._key(key).getBytes(), bytes);	
         redis.hSet(this.region.getBytes(), key.getBytes(), bytes);	
         return null;	
      });	
   }	
	
   public List getBytes(Collection keys) {	
      return (List)this.redisTemplate.opsForHash().getOperations().execute((redis) -> {	
         byte[][] a = (byte[][])keys.stream().map((k) -> {	
            return k.getBytes();	
         }).toArray((x$0) -> {	
            byte[][] var10000 = new byte[x$0][];	
            boolean var10002 = true;	
            return var10000;	
         });	
         return redis.hMGet(this.region.getBytes(), a);	
      });	
   }	
	
   public SpringRedisHashCache(String namespace, String region, RedisTemplate redisTemplate) {	
      if (region == null || region.isEmpty()) {	
         region = "_";	
      }	
	
      this.namespace = namespace;	
      this.redisTemplate = redisTemplate;	
      this.region = this.getRegionName(region);	
   }	
	
   public void evict(String... keys) {	
      String[] var2 = keys;	
      int var3 = keys.length;	
	
      int var4;	
      for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
         String a;	
         if (!(a = var2[var4]).equals("null")) {	
            HashOperations var6 = this.redisTemplate.opsForHash();	
            String var10001 = this.region;	
            Object[] var10002 = new Object[1];	
            boolean var10004 = true;	
            var10002[0] = a;	
            var6.delete(var10001, var10002);	
         } else {	
            this.redisTemplate.delete(this.region);	
         }	
	
         ++var4;	
      }	
	
   }	
	
   public boolean exists(String key) {	
      return this.redisTemplate.opsForHash().hasKey(this.region, key);	
   }	
	
   public Collection keys() {	
      Set a = this.redisTemplate.opsForHash().keys(this.region);	
      List a = new ArrayList(a.size());	
	
      Iterator var3;	
      for(Iterator var10000 = var3 = a.iterator(); var10000.hasNext(); var10000 = var3) {	
         Object a = var3.next();	
         a.add((String)a);	
      }	
	
      return a;	
   }	
	
   public void put(String key, Object value, long timeToLiveInSeconds) {	
      this.redisTemplate.opsForHash().put(this.region, key, value);	
   }	
	
   // $FF: synthetic method	
   private String _key(String key) {	
      return (new StringBuilder()).insert(0, this.region).append(":").append(key).toString();	
   }	
	
   public void put(String key, Object value) {	
      this.redisTemplate.opsForHash().put(this.region, key, value);	
   }	
	
   public byte[] getBytes(String key) {	
      return (byte[])this.redisTemplate.opsForHash().getOperations().execute((redis) -> {	
         return redis.hGet(this.region.getBytes(), key.getBytes());	
      });	
   }	
	
   // $FF: synthetic method	
   private String getRegionName(String region) {	
      if (this.namespace != null && !this.namespace.isEmpty()) {	
         region = (new StringBuilder()).insert(0, this.namespace).append(":").append(region).toString();	
      }	
	
      return region;	
   }	
	
   public void setBytes(Map bytes) {	
      bytes.forEach((k, v) -> {	
         this.setBytes(k, v);	
      });	
   }	
	
   public void clear() {	
      this.redisTemplate.delete(this.region);	
   }	
}	
