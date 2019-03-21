/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.dao.InvalidDataAccessApiUsageException	
 *  org.springframework.data.redis.connection.RedisConnection	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.validator.ValidatorUtils;	
import java.util.Iterator;	
import java.util.Properties;	
import java.util.Set;	
import org.hyperic.sigar.Tcp;	
import org.springframework.dao.InvalidDataAccessApiUsageException;	
import org.springframework.data.redis.connection.RedisConnection;	
	
public class ConfigureNotifyKeyspaceEventsAction {	
    static final String CONFIG_NOTIFY_KEYSPACE_EVENTS = "notify-keyspace-events";	
	
    private /* synthetic */ String getNotifyOptions(RedisConnection connection) {	
        Properties a;	
        block3 : {	
            try {	
                a = connection.getConfig(CONFIG_NOTIFY_KEYSPACE_EVENTS);	
                if (!a.isEmpty()) break block3;	
                return "";	
            }	
            catch (InvalidDataAccessApiUsageException a2) {	
                throw new IllegalStateException("Unabe to configure Redis to keyspace notifications. See http://docs.spring.io/spring-session/docs/current/reference/htm5/#api-redisoperationssessionrepository-sessiondestroyedevent", a2);	
            }	
        }	
        Properties properties = a;	
        return properties.getProperty(properties.stringPropertyNames().iterator().next());	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 << 3 ^ 1;	
        int n4 = n2;	
        4 << 4 ^ 4 << 1;	
        int n5 = 4 << 4 ^ (2 << 2 ^ 1);	
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
        boolean a;	
        String a2 = this.getNotifyOptions(connection);	
        String a3 = a2;	
        if (!a3.contains("E")) {	
            a3 = new StringBuilder().insert(0, a3).append("E").toString();	
        }	
        if (!(a = a3.contains("A")) && !a3.contains("g")) {	
            a3 = new StringBuilder().insert(0, a3).append("g").toString();	
        }	
        if (!a && !a3.contains("x")) {	
            a3 = new StringBuilder().insert(0, a3).append("x").toString();	
        }	
        if (!a2.equals(a3)) {	
            connection.setConfig(CONFIG_NOTIFY_KEYSPACE_EVENTS, a3);	
        }	
    }	
}	
	
