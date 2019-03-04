package com.jeesite.modules.config;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.shiro.session.CacheSessionDAO;	
import com.jeesite.common.shiro.session.JedisSessionDAO;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.shiro.session.SessionManager;	
import org.apache.shiro.cache.CacheManager;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
public class SessionConfig {	
   @Bean(	
      name = {"sessionDAO"}	
   )	
   @ConditionalOnProperty(	
      name = {"redis.cacheAndSession"},	
      havingValue = "false",	
      matchIfMissing = true	
   )	
   public SessionDAO sessionDAO(CacheManager shiroCacheManager) {	
      CacheSessionDAO a = new CacheSessionDAO();	
      a.setSessionIdGenerator(new IdGen());	
      a.setActiveSessionsCacheName("activeSessionsCache");	
      a.setCacheManager(shiroCacheManager);	
      return a;	
   }	
	
   @Bean	
   public SessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie sessionIdCookie) {	
      SessionManager a = new SessionManager();	
      a.setSessionDAO(sessionDAO);	
      a.setSessionIdUrlRewritingEnabled(false);	
      a.setGlobalSessionTimeout(ObjectUtils.toLong(Global.getProperty("session.sessionTimeout")));	
      a.setSessionValidationInterval(ObjectUtils.toLong(Global.getProperty("session.sessionTimeoutClean")));	
      a.setSessionValidationSchedulerEnabled(true);	
      a.setSessionIdCookie(sessionIdCookie);	
      a.setSessionIdCookieEnabled(true);	
      return a;	
   }	
	
   @Bean	
   public SimpleCookie sessionIdCookie() {	
      SimpleCookie var10000 = new SimpleCookie();	
      var10000.setName(Global.getProperty("session.sessionIdCookieName"));	
      return var10000;	
   }	
	
   @Bean(	
      name = {"sessionDAO"}	
   )	
   @ConditionalOnProperty(	
      name = {"redis.cacheAndSession"},	
      havingValue = "true",	
      matchIfMissing = false	
   )	
   public SessionDAO sessionDAORedis() {	
      JedisSessionDAO a;	
      JedisSessionDAO var10000 = a = new JedisSessionDAO();	
      var10000.setSessionIdGenerator(new IdGen());	
      a.setSessionKeyPrefix((new StringBuilder()).insert(0, Global.getProperty("redis.kePrefix")).append("session_").toString());	
      return var10000;	
   }	
}	
