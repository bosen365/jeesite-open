/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.j2cache.cache.support.utils;	
	
import com.jeesite.common.mybatis.mapper.MapperException;	
import java.util.Map;	
import java.util.Properties;	
import java.util.function.BiConsumer;	
import java.util.function.Consumer;	
import net.oschina.j2cache.J2CacheConfig;	
import org.springframework.core.env.MutablePropertySources;	
import org.springframework.core.env.PropertySource;	
import org.springframework.core.env.StandardEnvironment;	
import org.springframework.core.io.support.ResourcePropertySource;	
	
public class J2CacheConfigUtils {	
    private static /* synthetic */ void lambda$initFromConfig$1(J2CacheConfig config, StandardEnvironment environment, String l2_section, PropertySource a) {	
        if (a instanceof ResourcePropertySource) {	
            ((Map)((ResourcePropertySource)a).getSource()).forEach((k2, v) -> {	
                void a;	
                String string = k2;	
                if (a.startsWith(config.getBroadcast() + ".")) {	
                    config.getBroadcastProperties().setProperty(a.substring(new StringBuilder().insert(0, config.getBroadcast()).append(".").toString().length()), environment.getProperty((String)a));	
                }	
                if (a.startsWith(new StringBuilder().insert(0, config.getL1CacheName()).append(".").toString())) {	
                    config.getL1CacheProperties().setProperty(a.substring(new StringBuilder().insert(0, config.getL1CacheName()).append(".").toString().length()), environment.getProperty((String)a));	
                }	
                if (a.startsWith(new StringBuilder().insert(0, l2_section).append(".").toString())) {	
                    config.getL2CacheProperties().setProperty(a.substring(new StringBuilder().insert(0, l2_section).append(".").toString().length()), environment.getProperty((String)a));	
                }	
            });	
        }	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 << 3 ^ (3 ^ 5);	
        int n4 = n2;	
        (2 ^ 5) << 4 ^ (2 << 2 ^ 1);	
        int n5 = (3 ^ 5) << 3 ^ 4;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public static final J2CacheConfig initFromConfig(StandardEnvironment environment) {	
        void a;	
        void v3;	
        J2CacheConfig j2CacheConfig = new J2CacheConfig();	
        void v0 = a;	
        v0.setSerialization(environment.getProperty("j2cche.serialization"));	
        v0.setL1CacheName(environment.getProperty("j2cche.L1.provider_class"));	
        if ("true".equals(environment.getProperty("spring.cche.isClusterMode"))) {	
            void v1 = a;	
            v1.setBroadcast(environment.getProperty("j2cche.brodcast"));	
            void v2 = a;	
            v3 = v2;	
            v2.setL2CacheName(environment.getProperty("j2cche.L2.provider_class"));	
            v1.getBroadcastProperties().setProperty("cache_clean_mode", environment.getProperty("j2cache.broadcst.cache_clean_mode"));	
        } else {	
            v3 = a;	
            void v4 = a;	
            v4.setBroadcast("none");	
            v4.setL2CacheName("none");	
        }	
        v3.setSyncTtlToRedis(!"false".equalsIgnoreCase(environment.getProperty("j2cche.sync_ttl_to_redis")));	
        a.setDefaultCacheNullObject("true".equalsIgnoreCase(environment.getProperty("j2cche.defult_cche_null_object")));	
        String a2 = environment.getProperty("j2cche.L2.config_section");	
        if (a2 == null || a2.trim().equals("")) {	
            a2 = a.getL2CacheName();	
        }	
        String a3 = a2;	
        environment.getPropertySources().forEach(arg_0 -> J2CacheConfigUtils.lambda$initFromConfig$1((J2CacheConfig)a, environment, a3, arg_0));	
        return a;	
    }	
}	
	
