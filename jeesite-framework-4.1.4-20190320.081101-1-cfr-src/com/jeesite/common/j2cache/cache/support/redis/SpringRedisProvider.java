/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisGenericCache;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisHashCache;	
import com.jeesite.common.shiro.d.g;	
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
import org.hyperic.sigar.pager.PageList;	
import org.springframework.data.redis.core.RedisTemplate;	
	
public class SpringRedisProvider	
implements CacheProvider {	
    protected ConcurrentHashMap<String, Cache> caches;	
    private RedisTemplate<String, Serializable> redisTemplate;	
    private String storage;	
    private String namespace;	
	
    @Override	
    public void stop() {	
    }	
	
    @Override	
    public void start(Properties props) {	
        SpringRedisProvider springRedisProvider = this;	
        Properties properties = props;	
        this.namespace = properties.getProperty("namespace");	
        springRedisProvider.storage = properties.getProperty("storage");	
        springRedisProvider.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
    }	
	
    @Override	
    public Collection<CacheChannel.Region> regions() {	
        return Collections.emptyList();	
    }	
	
    public SpringRedisProvider() {	
        SpringRedisProvider springRedisProvider = this;	
        springRedisProvider.caches = new ConcurrentHashMap();	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    @Override	
    public Cache buildCache(String region, CacheExpiredListener listener) {	
        Cache a = this.caches.get(region);	
        if (a == null) {	
            Class<SpringRedisProvider> class_ = SpringRedisProvider.class;	
            synchronized (SpringRedisProvider.class) {	
                a = this.caches.get(region);	
                if (a == null) {	
                    SpringRedisProvider springRedisProvider;	
                    if ("hash".equalsIgnoreCase(this.storage)) {	
                        a = new SpringRedisHashCache(this.namespace, region, this.redisTemplate);	
                        springRedisProvider = this;	
                    } else {	
                        a = new SpringRedisGenericCache(this.namespace, region, this.redisTemplate);	
                        springRedisProvider = this;	
                    }	
                    springRedisProvider.caches.put(region, a);	
                }	
            }	
        }	
        return a;	
    }	
	
    @Override	
    public int level() {	
        return 2;	
    }	
	
    @Override	
    public Cache buildCache(String region, long timeToLiveInSeconds, CacheExpiredListener listener) {	
        return this.buildCache(region, listener);	
    }	
	
    @Override	
    public String name() {	
        return "redis";	
    }	
}	
	
