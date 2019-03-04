package com.jeesite.common.shiro.l;	
	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.cache.CacheManager;	
	
public class I implements CacheManager {	
   private String ALLATORIxDEMO = "jeesite_cache_";	
	
   public void ALLATORIxDEMO(String cacheKeyPrefix) {	
      this.ALLATORIxDEMO = cacheKeyPrefix;	
   }	
	
   public Cache getCache(String name) throws CacheException {	
      return new j((new StringBuilder()).insert(0, this.ALLATORIxDEMO).append(name).toString());	
   }	
	
   public String ALLATORIxDEMO() {	
      return this.ALLATORIxDEMO;	
   }	
}	
