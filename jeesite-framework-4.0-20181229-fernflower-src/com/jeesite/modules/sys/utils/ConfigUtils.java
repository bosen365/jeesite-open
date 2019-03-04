package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.sys.entity.Config;	
import java.util.Iterator;	
import java.util.Map;	
	
public class ConfigUtils {	
   public static final String CACHE_CONFIG_MAP = "configMap";	
	
   public static void clearCache() {	
      CacheUtils.remove("configMap");	
   }	
	
   public static synchronized Config getConfig(String key) {	
      Object a;	
      if ((a = (Map)CacheUtils.get("configMap")) == null) {	
         a = MapUtils.newHashMap();	
         Iterator var2;	
         Iterator var10000 = var2 = null.ALLATORIxDEMO().findList(new Config()).iterator();	
	
         while(var10000.hasNext()) {	
            Config a = (Config)var2.next();	
            var10000 = var2;	
            ((Map)a).put(a.getConfigKey(), a);	
         }	
	
         CacheUtils.put("configMap", a);	
      }	
	
      Config a;	
      if ((a = (Config)((Map)a).get(key)) == null) {	
         a = new Config();	
      }	
	
      return a;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 4 ^ 2 << 2 ^ 1;	
      int var10001 = (3 ^ 5) << 4 ^ 2 ^ 5;	
      int var10002 = 1 << 3 ^ 3 ^ 5;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
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
}	
