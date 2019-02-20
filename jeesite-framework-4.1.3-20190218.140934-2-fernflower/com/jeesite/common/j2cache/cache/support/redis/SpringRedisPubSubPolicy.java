package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.ArrayList;	
import java.util.Properties;	
import net.oschina.j2cache.Command;	
import net.oschina.j2cache.J2CacheConfig;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.listener.PatternTopic;	
import org.springframework.data.redis.listener.RedisMessageListenerContainer;	
	
public class SpringRedisPubSubPolicy implements ClusterPolicy {	
   private RedisTemplate redisTemplate;	
   private static boolean isActive = false;	
   private String channel = "j2cache_channel";	
   private String cacheCleanMode = "passive";	
	
   public void disconnect() {	
      this.redisTemplate.convertAndSend(this.channel, Command.quit().json());	
   }	
	
   public void publish(Command cmd) {	
      if (!isActive || "blend".equals(this.cacheCleanMode)) {	
         this.redisTemplate.convertAndSend(this.channel, cmd.json());	
      }	
	
   }	
	
   public void connect(Properties props) {	
      J2CacheConfig a = (J2CacheConfig)SpringUtils.getBean(J2CacheConfig.class);	
      this.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
      String a;	
      if (StringUtils.isNotBlank(a = props.getProperty("cache_clean_mode"))) {	
         this.cacheCleanMode = a;	
      }	
	
      if ("active".equals(this.cacheCleanMode)) {	
         isActive = true;	
      }	
	
      String a;	
      if ((a = a.getL2CacheProperties().getProperty("channel")) != null && !a.isEmpty()) {	
         this.channel = a;	
      }	
	
      RedisMessageListenerContainer a = (RedisMessageListenerContainer)SpringUtils.getBean("j2CacheRedisMessageListenerContainer");	
      a.addMessageListener(new SpringRedisMessageListener(this, this.channel), new PatternTopic(this.channel));	
      if (isActive || "blend".equals(this.cacheCleanMode)) {	
         (new ConfigureNotifyKeyspaceEventsAction()).config(a.getConnectionFactory().getConnection());	
         String a = a.getL2CacheProperties().getProperty("namespace");	
         String a = a.getL2CacheProperties().getProperty("database");	
         String a = (new StringBuilder()).insert(0, "__keyevent@").append(a != null && !"".equals(a) ? a : "0").append("__:expired").toString();	
         String a = (new StringBuilder()).insert(0, "__keyevent@").append(a != null && !"".equals(a) ? a : "0").append("__:del").toString();	
         ArrayList a;	
         (a = new ArrayList()).add(new PatternTopic(a));	
         a.add(new PatternTopic(a));	
         a.addMessageListener(new SpringRedisActiveMessageListener(this, a), a);	
      }	
	
   }	
}	
