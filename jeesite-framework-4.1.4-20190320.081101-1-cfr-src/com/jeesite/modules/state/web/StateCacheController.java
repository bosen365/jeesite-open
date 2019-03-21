/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.state.web;	
	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.state.service.StateCacheService;	
import java.util.List;	
import java.util.Map;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/state/cache"})	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateCacheController	
extends BaseController {	
    @Autowired	
    public StateCacheService stateCacheService;	
	
    @RequiresPermissions(value={"sys:stste:cache"})	
    @RequestMapping(value={"index"})	
    public String index(Model model) {	
        return "modules/state/cacheIndex";	
    }	
	
    @RequiresPermissions(value={"sys:stste:cache"})	
    @RequestMapping(value={"cacheKeyList"})	
    @ResponseBody	
    public List<Map<String, String>> cacheKeyList(String cacheName) {	
        return this.stateCacheService.getCacheKeyList(cacheName);	
    }	
	
    @RequiresPermissions(value={"sys:stste:cache"})	
    @RequestMapping(value={"cacheNameList"})	
    @ResponseBody	
    public List<Map<String, String>> cacheNameList() {	
        return this.stateCacheService.getCacheNameList();	
    }	
	
    @RequiresPermissions(value={"sys:stste:cache"})	
    @RequestMapping(value={"cacheValue"})	
    @ResponseBody	
    public Map<String, String> cacheValue(String cacheName, String key) {	
        return this.stateCacheService.getCacheValue(cacheName, key);	
    }	
	
    @RequiresPermissions(value={"sys:stste:cache"})	
    @RequestMapping(value={"clearCache"})	
    @ResponseBody	
    public String clearCache(String cacheName, String key) {	
        StateCacheController stateCacheController = this;	
        stateCacheController.stateCacheService.clearCache(cacheName, key);	
        return stateCacheController.renderResult("true", "清理缓存完成");	
    }	
}	
	
