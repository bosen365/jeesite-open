/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.cluster.ClusterPolicy	
 *  org.springframework.data.redis.connection.Message	
 *  org.springframework.data.redis.connection.MessageListener	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.data.redis.connection.Message;	
import org.springframework.data.redis.connection.MessageListener;	
	
public class SpringRedisActiveMessageListener	
implements MessageListener {	
    private String namespace;	
    private ClusterPolicy clusterPolicy;	
	
    SpringRedisActiveMessageListener(ClusterPolicy clusterPolicy, String namespace) {	
        SpringRedisActiveMessageListener springRedisActiveMessageListener = this;	
        springRedisActiveMessageListener.clusterPolicy = clusterPolicy;	
        springRedisActiveMessageListener.namespace = namespace;	
    }	
	
    public void onMessage(Message message, byte[] pattern) {	
        String a2 = message.toString();	
        if (a2 == null) {	
            return;	
        }	
        if (a2.startsWith(new StringBuilder().insert(0, this.namespace).append(":").toString())) {	
            String[] a3 = a2.replaceFirst(this.namespace + ":", "").split(":", 2);	
            if (a3.length != 2) {	
                return;	
            }	
            this.clusterPolicy.evict(a3[0], new String[]{a3[1]});	
        }	
    }	
}	
	
