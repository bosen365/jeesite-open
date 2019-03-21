/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.CacheChannel	
 *  org.springframework.boot.autoconfigure.ImportAutoConfiguration	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.shiro.d.e;	
import com.jeesite.common.shiro.d.h;	
import com.jeesite.common.shiro.d.i;	
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
    public i shiroCacheManager(CacheChannel cacheChannel) {	
        return new h(cacheChannel);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 << 3 ^ 2;	
        int n4 = n2;	
        int n5 = 4 << 3 ^ 3;	
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
    public i shiroCacheManagerRedis(CacheChannel cacheChannel) {	
        return new e(cacheChannel);	
    }	
}	
	
