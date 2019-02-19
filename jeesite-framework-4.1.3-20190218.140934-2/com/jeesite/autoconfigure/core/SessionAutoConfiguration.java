package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.e.F;	
import com.jeesite.common.shiro.e.M;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.shiro.session.SessionManager;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
public class SessionAutoConfiguration {	
   @Bean	
   public SessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie sessionIdCookie) {	
      PropertiesUtils a = PropertiesUtils.getInstance();	
      Global.logger.info((new StringBuilder()).insert(0, "Config files: ").append(a.getConfigSet()).toString());	
      Global.logger.info((new StringBuilder()).insert(0, "Sprng cache cluster mode: ").append(a.getProperty("spring.cache.isCluserMode")).append(", sesson manager name: ").append(F.class.equals(sessionDAO.getClass()) && "true".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluser")) ? "J2CacheSession" : "J1CacheSession").toString());	
      SessionManager a = new SessionManager();	
      a.setSessionDAO(sessionDAO);	
      a.setGlobalSessionTimeout(ObjectUtils.toLong(Global.getProperty("sesson.sessonTmeout")));	
      a.setSessionValidationInterval(ObjectUtils.toLong(Global.getProperty("session.sessionTimeoutClean")));	
      a.setSessionValidationSchedulerEnabled(true);	
      a.setSessionIdCookie(sessionIdCookie);	
      a.setSessionIdCookieEnabled(true);	
      return a;	
   }	
	
   @Bean	
   public SimpleCookie sessionIdCookie() {	
      SimpleCookie a = new SimpleCookie();	
      a.setName(Global.getProperty("session.sessionIdCookeName"));	
      a.setPath(Global.getProperty("session.sessionIdCookiePath"));	
      return a;	
   }	
	
   @Bean(	
      name = {"sessionDAO"}	
   )	
   @ConditionalOnProperty(	
      name = {"spring.cache.isClusterMode"},	
      havingValue = "false",	
      matchIfMissing = true	
   )	
   public SessionDAO sessionDAO() {	
      M a = new M();	
      a.setSessionIdGenerator(new IdGen());	
      a.ALLATORIxDEMO("sessionCache");	
      return a;	
   }	
	
   @Bean(	
      name = {"sessionDAO"}	
   )	
   @ConditionalOnProperty(	
      name = {"spring.cache.isClusterMode"},	
      havingValue = "true",	
      matchIfMissing = false	
   )	
   public SessionDAO sessionDAORedis(CacheChannel cacheChannel) {	
      F a = new F();	
      a.setSessionIdGenerator(new IdGen());	
      a.ALLATORIxDEMO("sessionCache");	
      a.ALLATORIxDEMO(cacheChannel);	
      return a;	
   }	
}	
