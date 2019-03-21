/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import java.io.Serializable;	
import java.io.UnsupportedEncodingException;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.function.BiConsumer;	
import java.util.function.Consumer;	
import java.util.function.Function;	
import java.util.function.IntFunction;	
import java.util.stream.Stream;	
import net.oschina.j2cache.Level2Cache;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.ProcFd;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.dao.DataAccessException;	
import org.springframework.data.redis.connection.RedisConnection;	
import org.springframework.data.redis.core.RedisCallback;	
import org.springframework.data.redis.core.RedisOperations;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.core.ValueOperations;	
	
public class SpringRedisGenericCache	
implements Level2Cache {	
    private String region;	
    private static final Logger log = LoggerFactory.getLogger(SpringRedisGenericCache.class);	
    private String namespace;	
    private RedisTemplate<String, Serializable> redisTemplate;	
	
    @Override	
    public void clear() {	
        this.keys().stream().forEach(k2 -> this.redisTemplate.delete((String)k2));	
    }	
	
    @Override	
    public boolean exists(String key) {	
        return this.redisTemplate.execute(redis -> redis.exists(this._key(key)));	
    }	
	
    @Override	
    public void setBytes(Map<String, byte[]> bytes) {	
        bytes.forEach((k2, v) -> this.setBytes((String)k2, (byte[])v));	
    }	
	
    public SpringRedisGenericCache(String namespace, String region, RedisTemplate<String, Serializable> redisTemplate) {	
        if (region == null || region.isEmpty()) {	
            region = "_";	
        }	
        this.namespace = namespace;	
        this.redisTemplate = redisTemplate;	
        this.region = this.getRegionName(region);	
    }	
	
    @Override	
    public /* varargs */ void evict(String ... keys) {	
        int n;	
        String[] arrstring = keys;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a = arrstring[n];	
            this.redisTemplate.execute(redis -> redis.del(new byte[][]{this._key(a)}));	
            n3 = ++n;	
        }	
    }	
	
    @Override	
    public void setBytes(Map<String, byte[]> bytes, long timeToLiveInSeconds) {	
        bytes.forEach((k2, v) -> this.setBytes((String)k2, (byte[])v, timeToLiveInSeconds));	
    }	
	
    private /* synthetic */ byte[] _key(String key) {	
        try {	
            byte[] a = new StringBuilder().insert(0, this.region).append(":").append(key).toString().getBytes("utf-8");	
            return a;	
        }	
        catch (UnsupportedEncodingException a) {	
            a.printStackTrace();	
            byte[] a2 = new StringBuilder().insert(0, this.region).append(":").append(key).toString().getBytes();	
            return a2;	
        }	
    }	
	
    @Override	
    public Collection<String> keys() {	
        Iterator<String> iterator;	
        Set<String> a = this.redisTemplate.keys(new StringBuilder().insert(0, this.region).append(":*").toString());	
        ArrayList<String> a2 = new ArrayList<String>(a.size());	
        Iterator<String> iterator2 = iterator = a.iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            String string = iterator.next();	
            iterator2 = iterator;	
            a2.add((String)a3);	
        }	
        return a2;	
    }	
	
    @Override	
    public byte[] getBytes(String key) {	
        return this.redisTemplate.execute(redis -> redis.get(this._key(key)));	
    }	
	
    private /* synthetic */ String getRegionName(String region) {	
        if (this.namespace != null && !this.namespace.isEmpty()) {	
            region = new StringBuilder().insert(0, this.namespace).append(":").append(region).toString();	
        }	
        return region;	
    }	
	
    @Override	
    public void setBytes(String key, byte[] bytes, long timeToLiveInSeconds) {	
        if (timeToLiveInSeconds <= 0L) {	
            log.debug(String.format("Invalid timeToLiveInSeconds value : %d , skipped it.", timeToLiveInSeconds));	
            this.setBytes(key, bytes);	
            return;	
        }	
        this.redisTemplate.opsForValue().getOperations().execute(redis -> {	
            redis.setEx(this._key(key), (int)timeToLiveInSeconds, bytes);	
            return null;	
        });	
    }	
	
    @Override	
    public List<byte[]> getBytes(Collection<String> keys) {	
        return this.redisTemplate.execute(redis -> {	
            byte[][] a = (byte[][])keys.stream().map(k2 -> this._key((String)k2)).toArray(x$0 -> new byte[x$0][]);	
            return redis.mGet(a);	
        });	
    }	
	
    @Override	
    public void setBytes(String key, byte[] bytes) {	
        this.redisTemplate.execute(redis -> {	
            redis.set(this._key(key), bytes);	
            return null;	
        });	
    }	
}	
	
