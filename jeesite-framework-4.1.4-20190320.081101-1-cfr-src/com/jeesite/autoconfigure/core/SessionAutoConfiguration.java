/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.autoconfigure.core.CacheAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.session.SessionManager;	
import com.jeesite.common.shiro.v.C;	
import com.jeesite.common.shiro.v.m;	
import com.jeesite.modules.job.d.i;	
import java.util.Set;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.session.mgt.eis.SessionDAO;	
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;	
import org.apache.shiro.web.servlet.Cookie;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.slf4j.Logger;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
public class SessionAutoConfiguration {	
    @Bean(name={"sessionDAO"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="false", matchIfMissing=true)	
    public com.jeesite.common.shiro.session.SessionDAO sessionDAO() {	
        void a;	
        C c2 = new C();	
        void v0 = a;	
        void v1 = a;	
        v0.setSessionIdGenerator(new IdGen());	
        v0.ALLATORIxDEMO("sessionCache");	
        return v0;	
    }	
	
    @Bean(name={"sessionDAO"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="true", matchIfMissing=false)	
    public com.jeesite.common.shiro.session.SessionDAO sessionDAORedis(CacheChannel cacheChannel) {	
        m a;	
        m m2 = a = new m();	
        a.setSessionIdGenerator(new IdGen());	
        m2.ALLATORIxDEMO("sessionCache");	
        m2.ALLATORIxDEMO(cacheChannel);	
        return m2;	
    }	
	
    @Bean	
    public SimpleCookie sessionIdCookie() {	
        void a;	
        SimpleCookie simpleCookie = new SimpleCookie();	
        void v0 = a;	
        v0.setName(Global.getProperty("session.sessionIdCookieName"));	
        v0.setPath(Global.getProperty("session.sessionIdCookiePath"));	
        return v0;	
    }	
	
    @Bean	
    public SessionManager sessionManager(com.jeesite.common.shiro.session.SessionDAO sessionDAO, SimpleCookie sessionIdCookie) {	
        void a;	
        PropertiesUtils a2 = PropertiesUtils.getInstance();	
        Global.logger.info(new StringBuilder().insert(0, "Config files: ").append(a2.getConfigSet()).toString());	
        Global.logger.info(new StringBuilder().insert(0, "Spring cache cluster mode: ").append(a2.getProperty("spring.cache.isClusterMode")).append(", session manager name: ").append(m.class.equals(sessionDAO.getClass()) && "true".equals(com.jeesite.common.web.d.m.ALLATORIxDEMO().get("fnCluster")) ? "J2CacheSession" : "J1CacheSession").toString());	
        SessionManager sessionManager = new SessionManager();	
        void v0 = a;	
        void v1 = a;	
        void v2 = a;	
        v2.setSessionDAO(sessionDAO);	
        v2.setGlobalSessionTimeout(ObjectUtils.toLong(Global.getProperty("session.sessionTimeout")));	
        v1.setSessionValidationInterval(ObjectUtils.toLong(Global.getProperty("session.sessionTimeoutClean")));	
        v1.setSessionValidationSchedulerEnabled(true);	
        v0.setSessionIdCookie(sessionIdCookie);	
        v0.setSessionIdCookieEnabled(true);	
        return v0;	
    }	
}	
	
