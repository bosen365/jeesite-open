package com.jeesite.common.j2cache.cache.support.redis;	
	
import com.jeesite.common.cache.JedisUtils;	
import java.util.Properties;	
import org.hyperic.sigar.FileSystemMap;	
import org.springframework.dao.InvalidDataAccessApiUsageException;	
import org.springframework.data.redis.connection.RedisConnection;	
	
public class ConfigureNotifyKeyspaceEventsAction {	
   static final String CONFIG_NOTIFY_KEYSPACE_EVENTS = "notify-keyspace-events";	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 3 ^ 3;	
      int var10001 = 2 << 3 ^ 2 ^ 5;	
      int var10002 = (3 ^ 5) << 4 ^ 2 << 2 ^ 3;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public void config(RedisConnection connection) {	
      String a;	
      String a = a = this.getNotifyOptions(connection);	
      if (!a.contains("E")) {	
         a = (new StringBuilder()).insert(0, a).append("E").toString();	
      }	
	
      boolean a;	
      if (!(a = a.contains("A")) && !a.contains("g")) {	
         a = (new StringBuilder()).insert(0, a).append("g").toString();	
      }	
	
      if (!a && !a.contains("x")) {	
         a = (new StringBuilder()).insert(0, a).append("x").toString();	
      }	
	
      if (!a.equals(a)) {	
         connection.setConfig("notify-keyspace-events", a);	
      }	
	
   }	
	
   // $FF: synthetic method	
   private String getNotifyOptions(RedisConnection connection) {	
      try {	
         Properties a;	
         return (a = connection.getConfig("notify-keyspace-events")).isEmpty() ? "" : a.getProperty((String)a.stringPropertyNames().iterator().next());	
      } catch (InvalidDataAccessApiUsageException var3) {	
         throw new IllegalStateException("Unable to configure Redis to keyspace notifications. See http://docs.spring.io/spring-session/docs/current/reference/html_/#api-redisoperationssessionrepository-sessiondestroyedevent", var3);	
      }	
   }	
}	
