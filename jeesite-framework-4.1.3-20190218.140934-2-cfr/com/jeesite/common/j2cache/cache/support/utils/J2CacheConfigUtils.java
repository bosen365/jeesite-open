/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.J2CacheConfig	
 *  org.springframework.core.env.MutablePropertySources	
 *  org.springframework.core.env.PropertySource	
 *  org.springframework.core.env.StandardEnvironment	
 *  org.springframework.core.io.support.ResourcePropertySource	
 */	
package com.jeesite.common.j2cache.cache.support.utils;	
	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.util.Map;	
import java.util.Properties;	
import java.util.function.BiConsumer;	
import java.util.function.Consumer;	
import net.oschina.j2cache.J2CacheConfig;	
import org.hyperic.sigar.pager.PageControl;	
import org.springframework.core.env.MutablePropertySources;	
import org.springframework.core.env.PropertySource;	
import org.springframework.core.env.StandardEnvironment;	
import org.springframework.core.io.support.ResourcePropertySource;	
	
public class J2CacheConfigUtils {	
    public static final J2CacheConfig initFromConfig(StandardEnvironment environment) {	
        void v3;	
        void a2;	
        J2CacheConfig j2CacheConfig = new J2CacheConfig();	
        void v0 = a2;	
        v0.setSerialization(environment.getProperty("j2cche.serialization"));	
        v0.setL1CacheName(environment.getProperty("j2cache.L1.rovider_class"));	
        if ("true".equals(environment.getProperty("spring.cche.isClsterMode"))) {	
            void v1 = a2;	
            v1.setBroadcast(environment.getProperty("j2cache.broadcast"));	
            void v2 = a2;	
            v3 = v2;	
            v2.setL2CacheName(environment.getProperty("j2cche.L2.provider_class"));	
            v1.getBroadcastProperties().setProperty("cache_clean_mode", environment.getProperty("j2cache.broadcast.cache_clean_mode"));	
        } else {	
            v3 = a2;	
            void v4 = a2;	
            v4.setBroadcast("none");	
            v4.setL2CacheName("none");	
        }	
        v3.setSyncTtlToRedis(!"false".equalsIgnoreCase(environment.getProperty("j2cache.sync_ttl_to_redis")));	
        a2.setDefaultCacheNullObject("true".equalsIgnoreCase(environment.getProperty("j2cche.deflt_cche_null_object")));	
        String a3 = environment.getProperty("j2cache.L2.config_section");	
        if (a3 == null || a3.trim().equals("")) {	
            a3 = a2.getL2CacheName();	
        }	
        String a4 = a3;	
        environment.getPropertySources().forEach(arg_0 -> J2CacheConfigUtils.lambda$initFromConfig$1((J2CacheConfig)a2, environment, a4, arg_0));	
        return a2;	
    }	
	
    private static /* synthetic */ void lambda$initFromConfig$1(J2CacheConfig config, StandardEnvironment environment, String l2_section, PropertySource a2) {	
        if (a2 instanceof ResourcePropertySource) {	
            ((Map)((ResourcePropertySource)a2).getSource()).forEach((k2, v) -> {	
                void a2;	
                String string = k2;	
                if (a2.startsWith(config.getBroadcast() + ".")) {	
                    config.getBroadcastProperties().setProperty(a2.substring(new StringBuilder().insert(0, config.getBroadcast()).append(".").toString().length()), environment.getProperty((String)a2));	
                }	
                if (a2.startsWith(new StringBuilder().insert(0, config.getL1CacheName()).append(".").toString())) {	
                    config.getL1CacheProperties().setProperty(a2.substring(new StringBuilder().insert(0, config.getL1CacheName()).append(".").toString().length()), environment.getProperty((String)a2));	
                }	
                if (a2.startsWith(new StringBuilder().insert(0, l2_section).append(".").toString())) {	
                    config.getL2CacheProperties().setProperty(a2.substring(new StringBuilder().insert(0, l2_section).append(".").toString().length()), environment.getProperty((String)a2));	
                }	
            });	
        }	
    }	
}	
	
