/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisActiveMessageListener;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisMessageListener;	
import com.jeesite.common.utils.SpringUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Properties;	
import net.oschina.j2cache.Command;	
import net.oschina.j2cache.J2CacheConfig;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.springframework.data.redis.connection.MessageListener;	
import org.springframework.data.redis.connection.RedisConnection;	
import org.springframework.data.redis.connection.RedisConnectionFactory;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.listener.PatternTopic;	
import org.springframework.data.redis.listener.RedisMessageListenerContainer;	
import org.springframework.data.redis.listener.Topic;	
	
public class SpringRedisPubSubPolicy	
implements ClusterPolicy {	
    private static boolean isActive = false;	
    private RedisTemplate<String, Serializable> redisTemplate;	
    private String channel;	
    private String cacheCleanMode;	
	
    @Override	
    public void publish(Command cmd) {	
        if (!isActive || "blend".equals(this.cacheCleanMode)) {	
            SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
            springRedisPubSubPolicy.redisTemplate.convertAndSend(springRedisPubSubPolicy.channel, cmd.json());	
        }	
    }	
	
    public SpringRedisPubSubPolicy() {	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        springRedisPubSubPolicy.cacheCleanMode = "passive";	
        springRedisPubSubPolicy.channel = "j2cache_channel";	
    }	
	
    @Override	
    public void disconnect() {	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        springRedisPubSubPolicy.redisTemplate.convertAndSend(springRedisPubSubPolicy.channel, Command.quit().json());	
    }	
	
    public void connect(Properties props) {	
        void a;	
        String a2;	
        J2CacheConfig a3 = SpringUtils.getBean(J2CacheConfig.class);	
        this.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
        String a4 = props.getProperty("cache_clean_mode");	
        if (StringUtils.isNotBlank(a4)) {	
            this.cacheCleanMode = a4;	
        }	
        if ("active".equals(this.cacheCleanMode)) {	
            isActive = true;	
        }	
        if ((a2 = a3.getL2CacheProperties().getProperty("channel")) != null && !a2.isEmpty()) {	
            this.channel = a2;	
        }	
        RedisMessageListenerContainer redisMessageListenerContainer = (RedisMessageListenerContainer)SpringUtils.getBean("j2CacheRedisMessageListenerContainer");	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        a.addMessageListener((MessageListener)new SpringRedisMessageListener(springRedisPubSubPolicy, springRedisPubSubPolicy.channel), new PatternTopic(this.channel));	
        if (isActive || "blend".equals(this.cacheCleanMode)) {	
            new ConfigureNotifyKeyspaceEventsAction().config(a.getConnectionFactory().getConnection());	
            J2CacheConfig j2CacheConfig = a3;	
            String a5 = j2CacheConfig.getL2CacheProperties().getProperty("namespace");	
            String a6 = j2CacheConfig.getL2CacheProperties().getProperty("database");	
            String a7 = new StringBuilder().insert(0, "__keyevent@").append(a6 == null || "".equals(a6) ? "0" : a6).append("__:expired").toString();	
            String a8 = new StringBuilder().insert(0, "__keyevent@").append(a6 == null || "".equals(a6) ? "0" : a6).append("__:del").toString();	
            ArrayList<PatternTopic> a9 = new ArrayList<PatternTopic>();	
            a.addMessageListener((MessageListener)new SpringRedisActiveMessageListener(this, a5), a9);	
            a9.add(new PatternTopic(a8));	
            a9.add(new PatternTopic(a7));	
        }	
    }	
}	
	
