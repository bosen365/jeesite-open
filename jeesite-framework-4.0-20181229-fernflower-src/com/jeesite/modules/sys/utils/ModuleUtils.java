package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.sys.entity.Module;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Map.Entry;	
	
public class ModuleUtils {	
   public static final String CACHE_MODULE_MAP = "moduleMap";	
	
   public static synchronized Module getModule(String moduleCode) {	
      Module a;	
      if ((a = (Module)getModuleList().get(moduleCode)) == null) {	
         a = new Module();	
      }	
	
      return a;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 2 << 2 ^ 1;	
      int var10001 = 5 << 4;	
      int var10002 = (3 ^ 5) << 4 ^ 5 << 1;	
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
	
   public static synchronized Map getModuleList() {	
      Object a;	
      if ((a = (Map)CacheUtils.get("moduleMap")) == null) {	
         a = MapUtils.newHashMap();	
	
         Iterator var1;	
         for(Iterator var10000 = var1 = null.ALLATORIxDEMO().findList(new Module()).iterator(); var10000.hasNext(); var10000 = var1) {	
            Module a;	
            Object var3;	
            if (!(a = (Module)var1.next()).getIsLoader()) {	
               null.ALLATORIxDEMO().disableByModuleCodes(a.getModuleCode());	
               var3 = a;	
            } else {	
               if (a.getIsEnable()) {	
                  null.ALLATORIxDEMO().enableByModuleCodes(a.getModuleCode());	
               }	
	
               var3 = a;	
            }	
	
            ((Map)var3).put(a.getModuleCode(), a);	
         }	
	
         CacheUtils.put("moduleMap", a);	
      }	
	
      return (Map)a;	
   }	
	
   public static synchronized List getEnableModuleCodes() {	
      List a = ListUtils.newArrayList();	
      Iterator var2 = getModuleList().entrySet().iterator();	
	
      while(var2.hasNext()) {	
         Entry a;	
         if (((Module)(a = (Entry)var2.next()).getValue()).getIsEnable()) {	
            a.add(a.getKey());	
         }	
      }	
	
      return a;	
   }	
	
   public static void clearCache() {	
      CacheUtils.remove("moduleMap");	
   }	
}	
