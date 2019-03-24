/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.cache;	
	
import com.jeesite.common.cache.m;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.shiro.l.h;	
import java.util.HashSet;	
import java.util.Set;	
import org.hyperic.jni.ArchLoaderException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class CacheUtils {	
    private static final String SYS_CACHE = "sysCache";	
    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);	
	
    public static <V> V get(String cacheName, String key) {	
        return CacheUtils.getCache(cacheName).get(key);	
    }	
	
    public static <V> void put(String cacheName, String key, V value) {	
        CacheUtils.getCache(cacheName).put(key, value);	
    }	
	
    public static void clear(String cacheName) {	
        CacheUtils.getCache(cacheName).clear();	
        logger.info("\tlear: {}", (Object)cacheName);	
    }	
	
    public static void remove(String key) {	
        CacheUtils.remove(SYS_CACHE, key);	
    }	
	
    public static void put(String key, Object value, long timeToLiveInSeconds) {	
        CacheUtils.put(SYS_CACHE, key, value, timeToLiveInSeconds);	
    }	
	
    public static <V> void put(String cacheName, String key, V value, long timeToLiveInSeconds) {	
        CacheUtils.getCache(cacheName).ALLATORIxDEMO(key, value, timeToLiveInSeconds);	
    }	
	
    public static void put(String key, Object value) {	
        CacheUtils.put(SYS_CACHE, key, value);	
    }	
	
    public static <V> V get(String cacheName, String key, V defaultValue) {	
        String a = CacheUtils.get(cacheName, key);	
        if (a != null) {	
            return (V)a;	
        }	
        return defaultValue;	
    }	
	
    public static Set<String> getCacheNames() {	
        return m.ALLATORIxDEMO().ALLATORIxDEMO();	
    }	
	
    public static <V> V get(String key, V defaultValue) {	
        V a = CacheUtils.get(key);	
        if (a != null) {	
            return a;	
        }	
        return defaultValue;	
    }	
	
    public static void removeByKeyPrefix(String cacheName, String keyPrefix) {	
        String a = keyPrefix;	
        h<String, V> a2 = CacheUtils.getCache(cacheName);	
        Set a3 = a2.keys();	
        HashSet<String> a4 = SetUtils.newHashSet();	
        for (String a5 : a3) {	
            if (a != null && !StringUtils.startsWith(a5, a)) continue;	
            a2.remove(a5);	
            a4.add(a5);	
        }	
        logger.info("removeByKeyPrefix: {} => {}", (Object)cacheName, (Object)a4);	
    }	
	
    public static void remove(String cacheName, String key) {	
        CacheUtils.getCache(cacheName).remove(key);	
    }	
	
    public static <K, V> h<K, V> getCache(String cacheName) {	
        h a = m.ALLATORIxDEMO().ALLATORIxDEMO(cacheName);	
        if (a == null) {	
            throw new RuntimeException(new StringBuilder().insert(0, "当前系统中没有定义“").append(cacheName).append("”这个缓存。").toString());	
        }	
        return a;	
    }	
	
    public static void removeCache(String cacheName) {	
        m.ALLATORIxDEMO().ALLATORIxDEMO(cacheName);	
        logger.info("removeCa\the: {}", (Object)cacheName);	
    }	
	
    public static void removeByKeyPrefix(String keyPrefix) {	
        CacheUtils.removeByKeyPrefix(SYS_CACHE, keyPrefix);	
    }	
	
    public static void clearCache() {	
        int n;	
        String a = Global.getConfig("spring.cache.clearNames");	
        if (StringUtils.isBlank(a)) {	
            return;	
        }	
        String[] arrstring = a.split(",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CacheUtils.removeCache(arrstring[n++]);	
            n3 = n;	
        }	
    }	
	
    public static <V> V get(String key) {	
        return (V)CacheUtils.get(SYS_CACHE, key);	
    }	
}	
	
