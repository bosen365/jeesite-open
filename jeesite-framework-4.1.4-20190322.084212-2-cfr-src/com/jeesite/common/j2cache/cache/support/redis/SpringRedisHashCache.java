/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.autoconfigure.core.CacheAutoConfiguration;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.function.BiConsumer;	
import java.util.function.Function;	
import java.util.function.IntFunction;	
import java.util.stream.Stream;	
import net.oschina.j2cache.Level2Cache;	
import org.hyperic.sigar.Mem;	
import org.springframework.dao.DataAccessException;	
import org.springframework.data.redis.connection.RedisConnection;	
import org.springframework.data.redis.core.HashOperations;	
import org.springframework.data.redis.core.RedisCallback;	
import org.springframework.data.redis.core.RedisOperations;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisHashCache	
implements Level2Cache {	
    private RedisTemplate<String, Serializable> redisTemplate;	
    private String namespace;	
    private String region;	
	
    @Override	
    public void setBytes(String key, byte[] bytes) {	
        this.redisTemplate.opsForHash().getOperations().execute(redis -> {	
            redis.set(this._key(key).getBytes(), bytes);	
            redis.hSet(this.region.getBytes(), key.getBytes(), bytes);	
            return null;	
        });	
    }	
	
    public SpringRedisHashCache(String namespace, String region, RedisTemplate<String, Serializable> redisTemplate) {	
        if (region == null || region.isEmpty()) {	
            region = "_";	
        }	
        this.namespace = namespace;	
        this.redisTemplate = redisTemplate;	
        this.region = this.getRegionName(region);	
    }	
	
    @Override	
    public Collection<String> keys() {	
        Iterator iterator;	
        Set a = this.redisTemplate.opsForHash().keys(this.region);	
        ArrayList<String> a2 = new ArrayList<String>(a.size());	
        Iterator iterator2 = iterator = a.iterator();	
        while (iterator2.hasNext()) {	
            Object a3 = iterator.next();	
            a2.add((String)a3);	
            iterator2 = iterator;	
        }	
        return a2;	
    }	
	
    private /* synthetic */ String _key(String key) {	
        return new StringBuilder().insert(0, this.region).append(":").append(key).toString();	
    }	
	
    @Override	
    public void evict(String ... keys) {	
        int n;	
        String[] arrstring = keys;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a = arrstring[n];	
            if (!a.equals("null")) {	
                Object[] arrobject = new Object[1];	
                arrobject[0] = a;	
                this.redisTemplate.opsForHash().delete(this.region, arrobject);	
            } else {	
                SpringRedisHashCache springRedisHashCache = this;	
                springRedisHashCache.redisTemplate.delete(springRedisHashCache.region);	
            }	
            n3 = ++n;	
        }	
    }	
	
    @Override	
    public void put(String key, Object value) {	
        this.redisTemplate.opsForHash().put(this.region, key, value);	
    }	
	
    @Override	
    public byte[] getBytes(String key) {	
        return this.redisTemplate.opsForHash().getOperations().execute(redis -> redis.hGet(this.region.getBytes(), key.getBytes()));	
    }	
	
    @Override	
    public void setBytes(Map<String, byte[]> bytes) {	
        bytes.forEach((k2, v) -> this.setBytes((String)k2, (byte[])v));	
    }	
	
    private /* synthetic */ String getRegionName(String region) {	
        if (this.namespace != null && !this.namespace.isEmpty()) {	
            region = new StringBuilder().insert(0, this.namespace).append(":").append(region).toString();	
        }	
        return region;	
    }	
	
    @Override	
    public List<byte[]> getBytes(Collection<String> keys) {	
        return this.redisTemplate.opsForHash().getOperations().execute(redis -> {	
            byte[][] a = (byte[][])keys.stream().map(k2 -> k2.getBytes()).toArray(x$0 -> new byte[x$0][]);	
            return redis.hMGet(this.region.getBytes(), a);	
        });	
    }	
	
    @Override	
    public void clear() {	
        SpringRedisHashCache springRedisHashCache = this;	
        springRedisHashCache.redisTemplate.delete(springRedisHashCache.region);	
    }	
	
    @Override	
    public void put(String key, Object value, long timeToLiveInSeconds) {	
        this.redisTemplate.opsForHash().put(this.region, key, value);	
    }	
	
    @Override	
    public boolean exists(String key) {	
        return this.redisTemplate.opsForHash().hasKey(this.region, key);	
    }	
}	
	
