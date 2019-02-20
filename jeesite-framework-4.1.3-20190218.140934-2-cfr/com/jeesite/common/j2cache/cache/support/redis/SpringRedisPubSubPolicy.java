/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.Command	
 *  net.oschina.j2cache.J2CacheConfig	
 *  net.oschina.j2cache.cluster.ClusterPolicy	
 *  org.apache.commons.lang3.StringUtils	
 *  org.springframework.data.redis.connection.MessageListener	
 *  org.springframework.data.redis.connection.RedisConnection	
 *  org.springframework.data.redis.connection.RedisConnectionFactory	
 *  org.springframework.data.redis.core.RedisTemplate	
 *  org.springframework.data.redis.listener.PatternTopic	
 *  org.springframework.data.redis.listener.RedisMessageListenerContainer	
 *  org.springframework.data.redis.listener.Topic	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisActiveMessageListener;	
import com.jeesite.common.j2cache.cache.support.redis.SpringRedisMessageListener;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Properties;	
import net.oschina.j2cache.Command;	
import net.oschina.j2cache.J2CacheConfig;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.data.redis.connection.MessageListener;	
import org.springframework.data.redis.connection.RedisConnection;	
import org.springframework.data.redis.connection.RedisConnectionFactory;	
import org.springframework.data.redis.core.RedisTemplate;	
import org.springframework.data.redis.listener.PatternTopic;	
import org.springframework.data.redis.listener.RedisMessageListenerContainer;	
import org.springframework.data.redis.listener.Topic;	
	
public class SpringRedisPubSubPolicy	
implements ClusterPolicy {	
    private RedisTemplate<String, Serializable> redisTemplate;	
    private static boolean isActive = false;	
    private String channel;	
    private String cacheCleanMode;	
	
    public void disconnect() {	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        springRedisPubSubPolicy.redisTemplate.convertAndSend(springRedisPubSubPolicy.channel, (Object)Command.quit().json());	
    }	
	
    public void publish(Command cmd) {	
        if (!isActive || "blend".equals(this.cacheCleanMode)) {	
            SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
            springRedisPubSubPolicy.redisTemplate.convertAndSend(springRedisPubSubPolicy.channel, (Object)cmd.json());	
        }	
    }	
	
    public SpringRedisPubSubPolicy() {	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        springRedisPubSubPolicy.cacheCleanMode = "passive";	
        springRedisPubSubPolicy.channel = "j2cache_channel";	
    }	
	
    public void connect(Properties props) {	
        void a2;	
        String a3;	
        J2CacheConfig a4 = SpringUtils.getBean(J2CacheConfig.class);	
        this.redisTemplate = (RedisTemplate)SpringUtils.getBean("j2CacheRedisTemplate");	
        String a5 = props.getProperty("cache_clean_mode");	
        if (StringUtils.isNotBlank((CharSequence)a5)) {	
            this.cacheCleanMode = a5;	
        }	
        if ("active".equals(this.cacheCleanMode)) {	
            isActive = true;	
        }	
        if ((a3 = a4.getL2CacheProperties().getProperty("channel")) != null && !a3.isEmpty()) {	
            this.channel = a3;	
        }	
        RedisMessageListenerContainer redisMessageListenerContainer = (RedisMessageListenerContainer)SpringUtils.getBean("j2CacheRedisMessageListenerContainer");	
        SpringRedisPubSubPolicy springRedisPubSubPolicy = this;	
        a2.addMessageListener((MessageListener)new SpringRedisMessageListener(springRedisPubSubPolicy, springRedisPubSubPolicy.channel), (Topic)new PatternTopic(this.channel));	
        if (isActive || "blend".equals(this.cacheCleanMode)) {	
            new ConfigureNotifyKeyspaceEventsAction().config(a2.getConnectionFactory().getConnection());	
            J2CacheConfig j2CacheConfig = a4;	
            String a6 = j2CacheConfig.getL2CacheProperties().getProperty("namespace");	
            String a7 = j2CacheConfig.getL2CacheProperties().getProperty("database");	
            String a8 = new StringBuilder().insert(0, "__keyevent@").append(a7 == null || "".equals(a7) ? "0" : a7).append("__:expired").toString();	
            String a9 = new StringBuilder().insert(0, "__keyevent@").append(a7 == null || "".equals(a7) ? "0" : a7).append("__:del").toString();	
            ArrayList<PatternTopic> a10 = new ArrayList<PatternTopic>();	
            a2.addMessageListener((MessageListener)new SpringRedisActiveMessageListener(this, a6), a10);	
            a10.add(new PatternTopic(a9));	
            a10.add(new PatternTopic(a8));	
        }	
    }	
}	
	
