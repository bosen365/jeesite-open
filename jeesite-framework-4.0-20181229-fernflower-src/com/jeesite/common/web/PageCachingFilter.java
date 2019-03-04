package com.jeesite.common.web;	
	
import com.jeesite.common.utils.SpringUtils;	
import net.sf.ehcache.CacheManager;	
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;	
	
public class PageCachingFilter extends SimplePageCachingFilter {	
   private CacheManager cacheManager;	
	
   protected CacheManager getCacheManager() {	
      if (this.cacheManager == null) {	
         this.cacheManager = (CacheManager)SpringUtils.getBean(CacheManager.class);	
      }	
	
      return this.cacheManager;	
   }	
	
   public void setCacheManager(CacheManager cacheManager) {	
      this.cacheManager = cacheManager;	
   }	
}	
