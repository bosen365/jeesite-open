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
      Global.logger.info((new StringBuilder()).insert(0, TransactionAutoConfiguration.ALLATORIxDEMO (">\u0013\u0013\u001a\u0014\u001b]\u001a\u0014\u0010\u0018\u000fG\\")).append(a.getConfigSet()).toString());	
      Global.logger.info((new StringBuilder()).insert(0, "Sprng cache cluster mode: ").append(a.getProperty(TransactionAutoConfiguration.ALLATORIxDEMO ("\u000e\f\u000f\u0015\u0013\u001bS\u001f\u001c\u001f\u0015\u0019S\u0015\u000e?\u0011\t\u000e\b\u0018\u000e0\u0013\u0019\u0019"))).append(", sesson manager name: ").append(F.class.equals(sessionDAO.getClass()) && "true".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get(TransactionAutoConfiguration.ALLATORIxDEMO ("\u001a\u0013?\u0011\t\u000e\b\u0018\u000e"))) ? "J2CacheSession" : TransactionAutoConfiguration.ALLATORIxDEMO ("7M>\u001d\u001e\u0014\u0018/\u0018\u000f\u000e\u0015\u0012\u0012")).toString());	
      SessionManager a = new SessionManager();	
      a.setSessionDAO(sessionDAO);	
      a.setGlobalSessionTimeout(ObjectUtils.toLong(Global.getProperty("sesson.sessonTmeout")));	
      a.setSessionValidationInterval(ObjectUtils.toLong(Global.getProperty(TransactionAutoConfiguration.ALLATORIxDEMO ("\u000f\u0018\u000f\u000e\u0015\u0012\u0012S\u000f\u0018\u000f\u000e\u0015\u0012\u0012)\u0015\u0010\u0019\u0012\t\t?\u0011\u0019\u001c\u0012"))));	
      a.setSessionValidationSchedulerEnabled(true);	
      a.setSessionIdCookie(sessionIdCookie);	
      a.setSessionIdCookieEnabled(true);	
      return a;	
   }	
	
   @Bean	
   public SimpleCookie sessionIdCookie() {	
      SimpleCookie a = new SimpleCookie();	
      a.setName(Global.getProperty("session.sessionIdCookeName"));	
      a.setPath(Global.getProperty(TransactionAutoConfiguration.ALLATORIxDEMO ("\u000f\u0018\u000f\u000e\u0015\u0012\u0012S\u000f\u0018\u000f\u000e\u0015\u0012\u00124\u0018>\u0013\u0012\u0017\u0014\u0019-\u001d\t\u0014")));	
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
