/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisActiveMessageListener;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisMessageListener;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.utils.SpringUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Properties;	
import net.oschina.j2cache.Command;	
import net.oschina.j2cache.J2CacheConfig;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.apache.commons.lang3.StringUtils;	
import org.springframework.data.redis.connection.MessageListener;	
import org.springframework.data.redis.connection.RedisConnection;	
import org.springframework.data.redis.connection.RedisConnectionFactory;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.listener.PatternTopic;	
import org.springframework.data.redis.listener.RedisMessageListenerContainer;	
import org.springframework.data.redis.listener.Topic;	
	
public class SpringRedisPubSubPolicy	
implements ClusterPolicy {	
    private String cacheCleanMode;	
    private static boolean isActive = false;	
    private String channel;	
    private RedisTemplate<String, Serializable> redisTemplate;	
	
    /*	
     * WARNING - void declaration	
     */	
    public void connect(Properties props) {	
        String a;	
        void a2;	
        J2CacheConfig a3 = SpringUtils.getBean(J2CacheConfig.class);	
        this.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
        String a4 = props.getProperty("cache_clean_mode");	
        if (StringUtils.isNotBlank(a4)) {	
            this.cacheCleanMode = a4;	
        }	
        if ("active".equals(this.cacheCleanMode)) {	
            isActive = true;	
        }	
        if ((a = a3.getL2CacheProperties().getProperty("channel")) != null && !a.isEmpty()) {	
            this.channel = a;	
        }	
        RedisMessageListenerContainer redisMessageListenerContainer = (RedisMessageListenerContainer)SpringUtils.getBean("j2CacheRedisMessageListenerContainer");	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        a2.addMessageListener((MessageListener)new SpringRedisMessageListener(springRedisPubSubPolicy, springRedisPubSubPolicy.channel), new PatternTopic(this.channel));	
        if (isActive || "blend".equals(this.cacheCleanMode)) {	
            new ConfigureNotifyKeyspaceEventsAction().config(a2.getConnectionFactory().getConnection());	
            J2CacheConfig j2CacheConfig = a3;	
            String a5 = j2CacheConfig.getL2CacheProperties().getProperty("namespace");	
            String a6 = j2CacheConfig.getL2CacheProperties().getProperty("database");	
            String a7 = new StringBuilder().insert(0, "__keyevent@").append(a6 == null || "".equals(a6) ? "0" : a6).append("__:expired").toString();	
            String a8 = new StringBuilder().insert(0, "__keyevent@").append(a6 == null || "".equals(a6) ? "0" : a6).append("__:del").toString();	
            ArrayList<PatternTopic> a9 = new ArrayList<PatternTopic>();	
            a9.add(new PatternTopic(a7));	
            a9.add(new PatternTopic(a8));	
            a2.addMessageListener((MessageListener)new SpringRedisActiveMessageListener(this, a5), a9);	
        }	
    }	
	
    @Override	
    public void disconnect() {	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        springRedisPubSubPolicy.redisTemplate.convertAndSend(springRedisPubSubPolicy.channel, Command.quit().json());	
    }	
	
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
}	
	
