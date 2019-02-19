package com.jeesite.common.j2cache.autoconfigure;	
	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheSerializer;	
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
@AutoConfigureAfter({RedisAutoConfiguration.class})	
@AutoConfigureBefore({J2CacheAutoConfiguration.class})	
@ConditionalOnProperty(	
   name = {"spring.cache.isClusterMode"},	
   havingValue = "true",	
   matchIfMissing = false	
)	
public class J2CacheSpringRedisAutoConfiguration {	
   @Bean({"j2CacheValueSerializer"})	
   @ConditionalOnMissingBean(	
      name = {"j2CacheValueSerializer"}	
   )	
   public RedisSerializer j2CacheValueSerializer() {	
      return new J2CacheSerializer();	
   }	
	
   @Primary	
   @Bean({"j2CacheRedisTemplate"})	
   public RedisTemplate j2CacheRedisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory, @Qualifier("j2CacheValueSerializer") RedisSerializer j2CacheValueSerializer) {	
      RedisTemplate a = new RedisTemplate();	
      a.setKeySerializer(new StringRedisSerializer());	
      a.setHashKeySerializer(new StringRedisSerializer());	
      a.setDefaultSerializer(j2CacheValueSerializer);	
      a.setConnectionFactory(redisConnectionFactory);	
      return a;	
   }	
	
   @Bean({"j2CacheRedisMessageListenerContainer"})	
   public RedisMessageListenerContainer container(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {	
      RedisMessageListenerContainer var10000 = new RedisMessageListenerContainer();	
      var10000.setConnectionFactory(redisConnectionFactory);	
      return var10000;	
   }	
}	
