package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import net.oschina.j2cache.Command;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import net.oschina.j2cache.util.SerializationUtils;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.data.redis.connection.Message;	
import org.springframework.data.redis.connection.MessageListener;	
	
public class SpringRedisMessageListener implements MessageListener {	
   private ClusterPolicy clusterPolicy;	
   private String channel;	
   private static Logger logger = LoggerFactory.getLogger(SpringRedisMessageListener.class);	
	
   public void onMessage(Message message, byte[] pattern) {	
      byte[] a = message.getChannel();	
      byte[] a = message.getBody();	
      if (a != null && a != null) {	
         try {	
            Command a;	
            if ((a = Command.parse(String.valueOf(SerializationUtils.deserialize(a)))) != null && !a.isLocal()) {	
               switch(a.getOperator()) {	
               case 1:	
                  Logger var10000 = logger;	
	
                  while(false) {	
                  }	
	
                  var10000.info((new StringBuilder()).insert(0, "Node-").append(a.getSrc()).append(" joined to ").append(this.channel).toString());	
                  return;	
               case 2:	
                  this.clusterPolicy.evict(a.getRegion(), a.getKeys());	
                  logger.debug((new StringBuilder()).insert(0, "Received cache evict message, region=").append(a.getRegion()).append(",key=").append(String.join(",", a.getKeys())).toString());	
                  return;	
               case 3:	
                  this.clusterPolicy.clear(a.getRegion());	
                  logger.debug((new StringBuilder()).insert(0, "Received cache clear message, region=").append(a.getRegion()).toString());	
                  return;	
               case 4:	
                  logger.info((new StringBuilder()).insert(0, "Node-").append(a.getSrc()).append(" quit to ").append(this.channel).toString());	
                  return;	
               default:	
                  logger.warn((new StringBuilder()).insert(0, "Unknown message type = ").append(a.getOperator()).toString());	
               }	
            }	
         } catch (Exception var6) {	
            logger.error("Failed to handle received msg", var6);	
         }	
      }	
   }	
	
   SpringRedisMessageListener(ClusterPolicy clusterPolicy, String var2) {	
      this.clusterPolicy = clusterPolicy;	
      this.channel = var2;	
   }	
}	
