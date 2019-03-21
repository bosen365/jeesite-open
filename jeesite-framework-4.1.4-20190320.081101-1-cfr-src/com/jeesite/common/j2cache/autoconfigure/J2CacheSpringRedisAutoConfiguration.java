/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.beans.factory.annotation.Qualifier	
 *  org.springframework.boot.autoconfigure.AutoConfigureAfter	
 *  org.springframework.boot.autoconfigure.AutoConfigureBefore	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.context.annotation.Primary	
 *  org.springframework.data.redis.connection.RedisConnectionFactory	
 *  org.springframework.data.redis.core.RedisTemplate	
 *  org.springframework.data.redis.listener.RedisMessageListenerContainer	
 *  org.springframework.data.redis.serializer.RedisSerializer	
 *  org.springframework.data.redis.serializer.StringRedisSerializer	
 */	
package com.jeesite.common.j2cache.autoconfigure;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheSerializer;	
import java.io.Serializable;	
import org.springframework.beans.factory.annotation.Qualifier;	
import org.springframework.boot.autoconfigure.AutoConfigureAfter;	
import org.springframework.boot.autoconfigure.AutoConfigureBefore;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.Primary;	
import org.springframework.data.redis.connection.RedisConnectionFactory;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.listener.RedisMessageListenerContainer;	
import org.springframework.data.redis.serializer.RedisSerializer;	
import org.springframework.data.redis.serializer.StringRedisSerializer;	
	
@Configuration	
@AutoConfigureAfter(value={RedisAutoConfiguration.class})	
@AutoConfigureBefore(value={J2CacheAutoConfiguration.class})	
@ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="true", matchIfMissing=false)	
public class J2CacheSpringRedisAutoConfiguration {	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 2 << 1;	
        int n4 = n2;	
        (3 ^ 5) << 3 ^ 5;	
        int n5 = (2 ^ 5) << 4 ^ (3 ^ 5) << 1;	
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
	
    @Bean(value={"j2CacheRedisMessageListenerContainer"})	
    public RedisMessageListenerContainer container(@Qualifier(value="redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {	
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();	
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);	
        return redisMessageListenerContainer;	
    }	
	
    @Bean(value={"j2CacheValueSerializer"})	
    @ConditionalOnMissingBean(name={"j2CacheValueSerializer"})	
    public RedisSerializer<Object> j2CacheValueSerializer() {	
        return new J2CacheSerializer();	
    }	
	
    @Primary	
    @Bean(value={"j2CacheRedisTemplate"})	
    public RedisTemplate<String, Serializable> j2CacheRedisTemplate(@Qualifier(value="redisConnectionFactory") RedisConnectionFactory redisConnectionFactory, @Qualifier(value="j2CacheValueSerializer") RedisSerializer<Object> j2CacheValueSerializer) {	
        RedisTemplate a;	
        RedisTemplate redisTemplate = a = new RedisTemplate();	
        RedisTemplate redisTemplate2 = a;	
        redisTemplate2.setKeySerializer((RedisSerializer)new StringRedisSerializer());	
        redisTemplate2.setHashKeySerializer((RedisSerializer)new StringRedisSerializer());	
        redisTemplate.setDefaultSerializer(j2CacheValueSerializer);	
        redisTemplate.setConnectionFactory(redisConnectionFactory);	
        return redisTemplate;	
    }	
}	
	
