package com.jeesite.modules.config;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import redis.clients.jedis.JedisPool;	
import redis.clients.jedis.JedisPoolConfig;	
	
@Configuration	
@ConditionalOnProperty(	
   name = {"redis.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class JedisConfig {	
   @Bean	
   public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {	
      return new JedisPool(jedisPoolConfig, Global.getProperty("redis.host"), ObjectUtils.toInteger(Global.getProperty("redis.port")), ObjectUtils.toInteger(Global.getProperty("redis.timeout")), Global.getProperty("redis.password"), ObjectUtils.toInteger(Global.getProperty("redis.database")), Global.getProperty("redis.clientName", ""), ObjectUtils.toBoolean(Global.getProperty("redis.isSSL")));	
   }	
	
   @Bean	
   public JedisPoolConfig jedisPoolConfig() {	
      JedisPoolConfig a = new JedisPoolConfig();	
      a.setMaxIdle(ObjectUtils.toInteger(Global.getProperty("redis.pool.maxIdle")));	
      a.setMaxTotal(ObjectUtils.toInteger(Global.getProperty("redis.pool.maxTotal")));	
      a.setMaxWaitMillis(10000L);	
      a.setTestWhileIdle(true);	
      a.setTestOnBorrow(false);	
      a.setTestOnReturn(false);	
      return a;	
   }	
}	
