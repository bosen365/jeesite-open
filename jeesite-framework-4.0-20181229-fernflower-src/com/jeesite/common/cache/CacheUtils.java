package com.jeesite.common.cache;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.l.i.g;	
import com.jeesite.common.lang.StringUtils;	
import java.util.Iterator;	
import java.util.Set;	
import org.apache.shiro.cache.Cache;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class CacheUtils {	
   private static final String SYS_CACHE = "sysCache";	
   private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);	
	
   public static void remove(String key) {	
      remove("sysCache", key);	
   }	
	
   public static void clearCache() {	
      String a;	
      if (!StringUtils.isBlank(a = Global.getConfig("ehcche.clerNames"))) {	
         String[] var1;	
         int var2 = (var1 = a.split(",")).length;	
	
         int var3;	
         for(int var10000 = var3 = 0; var10000 < var2; var10000 = var3) {	
            removeAll(var1[var3++]);	
         }	
	
      }	
   }	
	
   public static Object get(String key) {	
      return get("sysCache", key);	
   }	
	
   public static void remove(String cacheName, String key) {	
      getCache(cacheName).remove(getKey(key));	
   }	
	
   public static Object get(String cacheName, String key) {	
      return getCache(cacheName).get(getKey(key));	
   }	
	
   // $FF: synthetic method	
   private static String getKey(String key) {	
      String a;	
      return StringUtils.isNotBlank(a = DataSourceHolder.getDataSourceName()) ? (new StringBuilder()).insert(0, a).append("_").append(key).toString() : key;	
   }	
	
   public static void removeByKeyPrefix(String cacheName, String keyPrefix) {	
      String a = getKey(keyPrefix);	
      Cache a;	
      Set a = (a = getCache(cacheName)).keys();	
      Set a = SetUtils.newHashSet();	
      Iterator a = a.iterator();	
	
      while(a.hasNext()) {	
         String a;	
         if (StringUtils.startsWith(a = (String)a.next(), a)) {	
            a.remove(a);	
            a.add(a);	
         }	
      }	
	
      logger.info("清理缓存： {} => {}", cacheName, a);	
   }	
	
   public static void put(String key, Object value) {	
      put("sysCache", key, value);	
   }	
	
   public static void removeAll(String cacheName) {	
      Cache a;	
      Set a;	
      Iterator a;	
      Iterator var10000 = a = (a = (a = getCache(cacheName)).keys()).iterator();	
	
      while(var10000.hasNext()) {	
         var10000 = a;	
         a.remove(a.next());	
      }	
	
      logger.info("清理缓存： {} => {}", cacheName, a);	
   }	
	
   public static Object get(String key, Object defaultValue) {	
      Object a;	
      return (a = get(key)) != null ? a : defaultValue;	
   }	
	
   // $FF: synthetic method	
   private static Cache getCache(String cacheName) {	
      Cache a;	
      if ((a = null.ALLATORIxDEMO().getCache(cacheName)) == null) {	
         throw new RuntimeException((new StringBuilder()).insert(0, "当前系统中没有定义“").append(cacheName).append("”这个缓存。").toString());	
      } else {	
         return a;	
      }	
   }	
	
   public static void put(String cacheName, String key, Object value) {	
      getCache(cacheName).put(getKey(key), value);	
   }	
	
   public static Object get(String cacheName, String key, Object defaultValue) {	
      Object a;	
      return (a = get(cacheName, getKey(key))) != null ? a : defaultValue;	
   }	
}	
