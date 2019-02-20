package com.jeesite.modules.state.web;	
	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.state.service.StateCacheService;	
import java.util.List;	
import java.util.Map;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.ProcState;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/state/cache"})	
@ConditionalOnProperty(	
   name = {"state.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class StateCacheController extends BaseController {	
   @Autowired	
   public StateCacheService stateCacheService;	
	
   @RequiresPermissions({"sys:stste:cache"})	
   @RequestMapping({"cacheNameList"})	
   @ResponseBody	
   public List cacheNameList() {	
      return this.stateCacheService.getCacheNameList();	
   }	
	
   @RequiresPermissions({"sys:stste:cache"})	
   @RequestMapping({"clearCache"})	
   @ResponseBody	
   public String clearCache(String cacheName, String key) {	
      this.stateCacheService.clearCache(cacheName, key);	
      return this.renderResult("true", "清理缓存完成");	
   }	
	
   @RequiresPermissions({"sys:stste:cache"})	
   @RequestMapping({"cacheKeyList"})	
   @ResponseBody	
   public List cacheKeyList(String cacheName) {	
      return this.stateCacheService.getCacheKeyList(cacheName);	
   }	
	
   @RequiresPermissions({"sys:stste:cache"})	
   @RequestMapping({"index"})	
   public String index(Model model) {	
      return "modules/state/cacheIndex";	
   }	
	
   @RequiresPermissions({"sys:stste:cache"})	
   @RequestMapping({"cacheValue"})	
   @ResponseBody	
   public Map cacheValue(String cacheName, String key) {	
      return this.stateCacheService.getCacheValue(cacheName, key);	
   }	
}	
