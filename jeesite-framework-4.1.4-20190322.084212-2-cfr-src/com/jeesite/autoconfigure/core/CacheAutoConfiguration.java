/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.shiro.l.G;	
import com.jeesite.common.shiro.l.c;	
import com.jeesite.common.shiro.l.m;	
import net.oschina.j2cache.CacheChannel;	
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ImportAutoConfiguration(value={J2CacheAutoConfiguration.class, J2CacheSpringRedisAutoConfiguration.class})	
public class CacheAutoConfiguration {	
    @Bean(name={"shiroCacheManager"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="false", matchIfMissing=true)	
    public m shiroCacheManager(CacheChannel cacheChannel) {	
        return new G(cacheChannel);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 4 ^ 1;	
        int n4 = n2;	
        int n5 = 3;	
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
	
    @Bean(name={"shiroCacheManager"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="true", matchIfMissing=false)	
    public m shiroCacheManagerRedis(CacheChannel cacheChannel) {	
        return new c(cacheChannel);	
    }	
}	
	
