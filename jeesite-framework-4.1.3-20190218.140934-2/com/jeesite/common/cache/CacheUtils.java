package com.jeesite.common.cache;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.j.e;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Iterator;	
import java.util.Set;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class CacheUtils {	
   private static final String SYS_CACHE = "sysCache";	
   private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);	
	
   public static Object get(String cacheName, String key) {	
      return getCache(cacheName).get(getKey(key));	
   }	
	
   public static Set getCacheNames() {	
      return null.ALLATORIxDEMO().ALLATORIxDEMO();	
   }	
	
   public static void put(String cacheName, String key, Object value) {	
      getCache(cacheName).put(getKey(key), value);	
   }	
	
   public static void clear(String cacheName) {	
      getCache(cacheName).clear();	
      logger.info("clear: {}", cacheName);	
   }	
	
   public static void put(String key, Object value, long timeToLiveInSeconds) {	
      put("sysCache", key, value, timeToLiveInSeconds);	
   }	
	
   public static Object get(String key) {	
      return get("sysCache", key);	
   }	
	
   public static Object get(String cacheName, String key, Object defaultValue) {	
      Object a;	
      return (a = get(cacheName, getKey(key))) != null ? a : defaultValue;	
   }	
	
   public static void removeCache(String cacheName) {	
      null.ALLATORIxDEMO().ALLATORIxDEMO(cacheName);	
      logger.info("removeCache: {}", cacheName);	
   }	
	
   public static void clearCache() {	
      String a;	
      if (!StringUtils.isBlank(a = Global.getConfig("sprng.cache.clearNames"))) {	
         String[] var1;	
         int var2 = (var1 = a.split(",")).length;	
	
         int var3;	
         for(int var10000 = var3 = 0; var10000 < var2; var10000 = var3) {	
            removeCache(var1[var3++]);	
         }	
	
      }	
   }	
	
   public static void remove(String cacheName, String key) {	
      getCache(cacheName).remove(getKey(key));	
   }	
	
   // $FF: synthetic method	
   private static String getKey(String key) {	
      return key;	
   }	
	
   public static void put(String cacheName, String key, Object value, long timeToLiveInSeconds) {	
      getCache(cacheName).ALLATORIxDEMO(getKey(key), value, timeToLiveInSeconds);	
   }	
	
   public static void put(String key, Object value) {	
      put("sysCache", key, value);	
   }	
	
   public static e getCache(String cacheName) {	
      e a;	
      if ((a = null.ALLATORIxDEMO().ALLATORIxDEMO(cacheName)) == null) {	
         throw new RuntimeException((new StringBuilder()).insert(0, "当前系统中没有定义“").append(cacheName).append("”这个缓存。").toString());	
      } else {	
         return a;	
      }	
   }	
	
   public static void remove(String key) {	
      remove("sysCache", key);	
   }	
	
   public static Object get(String key, Object defaultValue) {	
      Object a;	
      return (a = get(key)) != null ? a : defaultValue;	
   }	
	
   public static void removeByKeyPrefix(String keyPrefix) {	
      removeByKeyPrefix("sysCache", keyPrefix);	
   }	
	
   public static void removeByKeyPrefix(String cacheName, String keyPrefix) {	
      String a = getKey(keyPrefix);	
      e a;	
      Set a = (a = getCache(cacheName)).keys();	
      Set a = SetUtils.newHashSet();	
      Iterator a = a.iterator();	
	
      while(true) {	
         String a;	
         do {	
            if (!a.hasNext()) {	
               logger.info("remove(yKeyPrefx: {} => {}", cacheName, a);	
               return;	
            }	
	
            a = (String)a.next();	
         } while(a != null && !StringUtils.startsWith(a, a));	
	
         a.remove(a);	
         a.add(a);	
      }	
   }	
}	
