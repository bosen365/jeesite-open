/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.Cache	
 *  net.oschina.j2cache.CacheChannel	
 *  net.oschina.j2cache.CacheChannel$Region	
 *  net.oschina.j2cache.CacheExpiredListener	
 *  net.oschina.j2cache.CacheProvider	
 *  org.springframework.data.redis.core.RedisTemplate	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.j.e;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisGenericCache;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisHashCache;	
import com.jeesite.common.utils.SpringUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Properties;	
import java.util.concurrent.ConcurrentHashMap;	
import net.oschina.j2cache.Cache;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheExpiredListener;	
import net.oschina.j2cache.CacheProvider;	
import org.hyperic.sigar.FileSystemMap;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisProvider	
implements CacheProvider {	
    private RedisTemplate<String, Serializable> redisTemplate;	
    private String namespace;	
    protected ConcurrentHashMap<String, Cache> caches;	
    private String storage;	
	
    public void start(Properties props) {	
        SpringRedisProvider springRedisProvider = this;	
        Properties properties = props;	
        this.namespace = properties.getProperty("namespace");	
        springRedisProvider.storage = properties.getProperty("storage");	
        springRedisProvider.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
    }	
	
    public Cache buildCache(String region, long timeToLiveInSeconds, CacheExpiredListener listener) {	
        return this.buildCache(region, listener);	
    }	
	
    public int level() {	
        return 2;	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public Cache buildCache(String region, CacheExpiredListener listener) {	
        Object a2 = this.caches.get(region);	
        if (a2 == null) {	
            Class<SpringRedisProvider> class_ = SpringRedisProvider.class;	
            synchronized (SpringRedisProvider.class) {	
                a2 = this.caches.get(region);	
                if (a2 == null) {	
                    SpringRedisProvider springRedisProvider;	
                    if ("hash".equalsIgnoreCase(this.storage)) {	
                        a2 = new SpringRedisHashCache(this.namespace, region, this.redisTemplate);	
                        springRedisProvider = this;	
                    } else {	
                        a2 = new SpringRedisGenericCache(this.namespace, region, this.redisTemplate);	
                        springRedisProvider = this;	
                    }	
                    springRedisProvider.caches.put(region, (Cache)a2);	
                }	
            }	
        }	
        return a2;	
    }	
	
    public Collection<CacheChannel.Region> regions() {	
        return Collections.emptyList();	
    }	
	
    public String name() {	
        return "redis";	
    }	
	
    public SpringRedisProvider() {	
        SpringRedisProvider springRedisProvider = this;	
        springRedisProvider.caches = new ConcurrentHashMap();	
    }	
	
    public void stop() {	
    }	
}	
	
