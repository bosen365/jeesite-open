package com.jeesite.common.shiro.l;	
	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.cache.CacheManager;	
	
public class l implements CacheManager {	
   public Cache getCache(String name) throws CacheException {	
      return new C(name);	
   }	
}	
