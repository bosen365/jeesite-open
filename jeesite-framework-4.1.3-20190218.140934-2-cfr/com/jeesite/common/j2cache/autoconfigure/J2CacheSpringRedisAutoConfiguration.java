/*	
 * Decompiled with CFR 0.139.	
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
    @Bean(value={"j2CacheValueSerializer"})	
    @ConditionalOnMissingBean(name={"j2CacheValueSerializer"})	
    public RedisSerializer<Object> j2CacheValueSerializer() {	
        return new J2CacheSerializer();	
    }	
	
    @Primary	
    @Bean(value={"j2CacheRedisTemplate"})	
    public RedisTemplate<String, Serializable> j2CacheRedisTemplate(@Qualifier(value="redisConnectionFactory") RedisConnectionFactory redisConnectionFactory, @Qualifier(value="j2CacheValueSerializer") RedisSerializer<Object> j2CacheValueSerializer) {	
        RedisTemplate a2;	
        RedisTemplate redisTemplate = a2 = new RedisTemplate();	
        RedisTemplate redisTemplate2 = a2;	
        redisTemplate2.setKeySerializer((RedisSerializer)new StringRedisSerializer());	
        redisTemplate2.setHashKeySerializer((RedisSerializer)new StringRedisSerializer());	
        redisTemplate.setDefaultSerializer(j2CacheValueSerializer);	
        redisTemplate.setConnectionFactory(redisConnectionFactory);	
        return redisTemplate;	
    }	
	
    @Bean(value={"j2CacheRedisMessageListenerContainer"})	
    public RedisMessageListenerContainer container(@Qualifier(value="redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {	
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();	
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);	
        return redisMessageListenerContainer;	
    }	
}	
	
