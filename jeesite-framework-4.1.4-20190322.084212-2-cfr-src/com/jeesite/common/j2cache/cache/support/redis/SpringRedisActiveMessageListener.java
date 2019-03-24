/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.hyperic.sigar.ProcCredName;	
import org.hyperic.sigar.cmd.Watch;	
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
	
    @Override	
    public void onMessage(Message message, byte[] pattern) {	
        String a = message.toString();	
        if (a == null) {	
            return;	
        }	
        if (a.startsWith(new StringBuilder().insert(0, this.namespace).append(":").toString())) {	
            String[] a2 = a.replaceFirst(this.namespace + ":", "").split(":", 2);	
            if (a2.length != 2) {	
                return;	
            }	
            String[] arrstring = new String[1];	
            arrstring[0] = a2[1];	
            this.clusterPolicy.evict(a2[0], arrstring);	
        }	
    }	
}	
	
