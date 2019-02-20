package com.jeesite.modules.state.service;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.BaseService;	
import java.util.Collection;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.Swap;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(	
   name = {"state.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class StateCacheService extends BaseService {	
   public void clearCache(String cacheName, String key) {	
      if (StringUtils.isNotBlank(key)) {	
         CacheUtils.remove(cacheName, key);	
      } else {	
         CacheUtils.removeCache(cacheName);	
      }	
   }	
	
   public List getCacheKeyList(String cacheName) {	
      List a = ListUtils.newArrayList();	
      if (StringUtils.isNotBlank(cacheName)) {	
         Iterator var4;	
         Iterator var10000 = var4 = CacheUtils.getCache(cacheName).keys().iterator();	
	
         while(var10000.hasNext()) {	
            String a = (String)var4.next();	
            HashMap a = MapUtils.newHashMap();	
            var10000 = var4;	
            a.put("id", a);	
            a.put("cacheName", cacheName);	
            a.add(a);	
         }	
      }	
	
      ListUtils.listOrderBy(a, "id");	
      return a;	
   }	
	
   public Map getCacheValue(String cacheName, String key) {	
      HashMap a;	
      (a = MapUtils.newHashMap()).put("cacheName", cacheName);	
      a.put("key", key);	
      String a = "";	
      CharSequence[] var10000 = new CharSequence[2];	
      boolean var10002 = true;	
      var10000[0] = cacheName;	
      var10000[1] = key;	
      if (StringUtils.isNoneBlank(var10000)) {	
         Object a = CacheUtils.get(cacheName, key);	
	
         try {	
            if (a instanceof Collection) {	
               a = MapUtils.toMapList((Collection)a);	
            } else if (!(a instanceof Map) && !(a instanceof CharSequence) && !(a instanceof Number)) {	
               a = MapUtils.toMap(a);	
            }	
         } catch (Exception var7) {	
            var7.printStackTrace();	
         }	
	
         String var9;	
         label35: {	
            try {	
               a = (new JsonMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(a);	
            } catch (JsonProcessingException var8) {	
               var9 = a;	
               var8.printStackTrace();	
               break label35;	
            }	
	
            var9 = a;	
         }	
	
         if (var9 == null) {	
            a = ObjectUtils.toString(a);	
         }	
      }	
	
      a.put("value", StringUtils.abbr(a, 512000));	
      return a;	
   }	
	
   public List getCacheNameList() {	
      List a = ListUtils.newArrayList();	
      Iterator var3;	
      Iterator var10000 = var3 = CacheUtils.getCacheNames().iterator();	
	
      while(var10000.hasNext()) {	
         String a = (String)var3.next();	
         HashMap a = MapUtils.newHashMap();	
         var10000 = var3;	
         a.put("id", a);	
         a.add(a);	
      }	
	
      ListUtils.listOrderBy(a, "id");	
      return a;	
   }	
}	
