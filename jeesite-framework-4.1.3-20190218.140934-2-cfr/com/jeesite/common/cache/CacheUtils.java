/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.SetUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.cache;	
	
import com.jeesite.common.cache.E;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.j.e;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.HashSet;	
import java.util.Set;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class CacheUtils {	
    private static final String SYS_CACHE = "sysCache";	
    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);	
	
    public static <V> V get(String cacheName, String key) {	
        return (V)CacheUtils.getCache(cacheName).get((Object)key);	
    }	
	
    public static Set<String> getCacheNames() {	
        return E.ALLATORIxDEMO().ALLATORIxDEMO();	
    }	
	
    public static <V> void put(String cacheName, String key, V value) {	
        CacheUtils.getCache(cacheName).put((Object)key, value);	
    }	
	
    public static void clear(String cacheName) {	
        CacheUtils.getCache(cacheName).clear();	
        logger.info("clear: {}", (Object)cacheName);	
    }	
	
    public static void put(String key, Object value, long timeToLiveInSeconds) {	
        CacheUtils.put(SYS_CACHE, key, value, timeToLiveInSeconds);	
    }	
	
    public static <V> V get(String key) {	
        return (V)CacheUtils.get(SYS_CACHE, key);	
    }	
	
    public static <V> V get(String cacheName, String key, V defaultValue) {	
        String a2 = CacheUtils.get(cacheName, key);	
        if (a2 != null) {	
            return (V)a2;	
        }	
        return defaultValue;	
    }	
	
    public static void removeCache(String cacheName) {	
        E.ALLATORIxDEMO().ALLATORIxDEMO(cacheName);	
        logger.info("removeCache: {}", (Object)cacheName);	
    }	
	
    public static void clearCache() {	
        int n;	
        String a2 = Global.getConfig("sprng.cache.clearNames");	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            return;	
        }	
        String[] arrstring = a2.split(",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CacheUtils.removeCache(arrstring[n++]);	
            n3 = n;	
        }	
    }	
	
    public static void remove(String cacheName, String key) {	
        CacheUtils.getCache(cacheName).remove((Object)key);	
    }	
	
    public static <V> void put(String cacheName, String key, V value, long timeToLiveInSeconds) {	
        CacheUtils.getCache(cacheName).ALLATORIxDEMO(key, value, timeToLiveInSeconds);	
    }	
	
    public static void put(String key, Object value) {	
        CacheUtils.put(SYS_CACHE, key, value);	
    }	
	
    public static <K, V> e<K, V> getCache(String cacheName) {	
        e a2 = E.ALLATORIxDEMO().ALLATORIxDEMO(cacheName);	
        if (a2 == null) {	
            throw new RuntimeException(new StringBuilder().insert(0, "当前系统中没有定义“").append(cacheName).append("”这个缓存。").toString());	
        }	
        return a2;	
    }	
	
    public static void remove(String key) {	
        CacheUtils.remove(SYS_CACHE, key);	
    }	
	
    public static <V> V get(String key, V defaultValue) {	
        V a2 = CacheUtils.get(key);	
        if (a2 != null) {	
            return a2;	
        }	
        return defaultValue;	
    }	
	
    public static void removeByKeyPrefix(String keyPrefix) {	
        CacheUtils.removeByKeyPrefix(SYS_CACHE, keyPrefix);	
    }	
	
    public static void removeByKeyPrefix(String cacheName, String keyPrefix) {	
        String a2 = keyPrefix;	
        e<K, V> a3 = CacheUtils.getCache(cacheName);	
        Set a4 = a3.keys();	
        HashSet a5 = SetUtils.newHashSet();	
        for (String a6 : a4) {	
            if (a2 != null && !StringUtils.startsWith((CharSequence)a6, (CharSequence)a2)) continue;	
            a5.add(a6);	
            a3.remove((Object)a6);	
        }	
        logger.info("remove(yKeyPrefx: {} => {}", (Object)cacheName, (Object)a5);	
    }	
}	
	
