package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.data.redis.connection.Message;	
import org.springframework.data.redis.connection.MessageListener;	
	
public class SpringRedisActiveMessageListener implements MessageListener {	
   private String namespace;	
   private ClusterPolicy clusterPolicy;	
	
   SpringRedisActiveMessageListener(ClusterPolicy clusterPolicy, String var2) {	
      this.clusterPolicy = clusterPolicy;	
      this.namespace = var2;	
   }	
	
   public void onMessage(Message message, byte[] pattern) {	
      String a;	
      if ((a = message.toString()) != null) {	
         if (a.startsWith((new StringBuilder()).insert(0, this.namespace).append(":").toString())) {	
            String[] a;	
            if ((a = a.replaceFirst(this.namespace + ":", "").split(":", 2)).length != 2) {	
               return;	
            }	
	
            ClusterPolicy var10000 = this.clusterPolicy;	
            String var10001 = a[0];	
            String[] var10002 = new String[1];	
            boolean var10004 = true;	
            var10002[0] = a[1];	
            var10000.evict(var10001, var10002);	
         }	
	
      }	
   }	
}	
