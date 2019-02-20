/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.dao.InvalidDataAccessApiUsageException	
 *  org.springframework.data.redis.connection.RedisConnection	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.cache.JedisUtils;	
import java.util.Iterator;	
import java.util.Properties;	
import java.util.Set;	
import org.hyperic.sigar.FileSystemMap;	
import org.springframework.dao.InvalidDataAccessApiUsageException;	
import org.springframework.data.redis.connection.RedisConnection;	
	
public class ConfigureNotifyKeyspaceEventsAction {	
    static final String CONFIG_NOTIFY_KEYSPACE_EVENTS = "notify-keyspace-events";	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ (2 << 2 ^ 3);	
        int n4 = n2;	
        2 << 3 ^ (2 ^ 5);	
        int n5 = 4 << 3 ^ 3;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public void config(RedisConnection connection) {	
        boolean a2;	
        String a3 = this.getNotifyOptions(connection);	
        String a4 = a3;	
        if (!a4.contains("E")) {	
            a4 = new StringBuilder().insert(0, a4).append("E").toString();	
        }	
        if (!(a2 = a4.contains("A")) && !a4.contains("g")) {	
            a4 = new StringBuilder().insert(0, a4).append("g").toString();	
        }	
        if (!a2 && !a4.contains("x")) {	
            a4 = new StringBuilder().insert(0, a4).append("x").toString();	
        }	
        if (!a3.equals(a4)) {	
            connection.setConfig(CONFIG_NOTIFY_KEYSPACE_EVENTS, a4);	
        }	
    }	
	
    private /* synthetic */ String getNotifyOptions(RedisConnection connection) {	
        Properties a2;	
        block3 : {	
            try {	
                a2 = connection.getConfig(CONFIG_NOTIFY_KEYSPACE_EVENTS);	
                if (!a2.isEmpty()) break block3;	
                return "";	
            }	
            catch (InvalidDataAccessApiUsageException a3) {	
                throw new IllegalStateException("Unable to configure Redis to keyspace notifications. See http://docs.spring.io/spring-session/docs/current/reference/html_/#api-redisoperationssessionrepository-sessiondestroyedevent", (Throwable)a3);	
            }	
        }	
        Properties properties = a2;	
        return properties.getProperty(properties.stringPropertyNames().iterator().next());	
    }	
}	
	
