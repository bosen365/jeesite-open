/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.PropertiesUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  net.oschina.j2cache.CacheChannel	
 *  org.apache.shiro.session.mgt.eis.SessionDAO	
 *  org.apache.shiro.session.mgt.eis.SessionIdGenerator	
 *  org.apache.shiro.web.servlet.Cookie	
 *  org.apache.shiro.web.servlet.SimpleCookie	
 *  org.slf4j.Logger	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.e.F;	
import com.jeesite.common.shiro.e.M;	
import com.jeesite.common.shiro.session.SessionManager;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
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
    @Bean	
    public SessionManager sessionManager(com.jeesite.common.shiro.session.SessionDAO sessionDAO, SimpleCookie sessionIdCookie) {	
        void a2;	
        PropertiesUtils a3 = PropertiesUtils.getInstance();	
        Global.logger.info(new StringBuilder().insert(0, "Config files: ").append(a3.getConfigSet()).toString());	
        Global.logger.info(new StringBuilder().insert(0, "Sprng cache cluster mode: ").append(a3.getProperty("spring.cache.isCluserMode")).append(", sesson manager name: ").append(F.class.equals(sessionDAO.getClass()) && "true".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluser")) ? "J2CacheSession" : "J1CacheSession").toString());	
        SessionManager sessionManager = new SessionManager();	
        void v0 = a2;	
        void v1 = a2;	
        void v2 = a2;	
        v2.setSessionDAO((SessionDAO)sessionDAO);	
        v2.setGlobalSessionTimeout(ObjectUtils.toLong((Object)Global.getProperty("sesson.sessonTmeout")).longValue());	
        v1.setSessionValidationInterval(ObjectUtils.toLong((Object)Global.getProperty("session.sessionTimeoutClean")).longValue());	
        v1.setSessionValidationSchedulerEnabled(true);	
        v0.setSessionIdCookie((Cookie)sessionIdCookie);	
        v0.setSessionIdCookieEnabled(true);	
        return v0;	
    }	
	
    @Bean	
    public SimpleCookie sessionIdCookie() {	
        void a2;	
        SimpleCookie simpleCookie = new SimpleCookie();	
        void v0 = a2;	
        v0.setName(Global.getProperty("session.sessionIdCookeName"));	
        v0.setPath(Global.getProperty("session.sessionIdCookiePath"));	
        return v0;	
    }	
	
    @Bean(name={"sessionDAO"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="false", matchIfMissing=true)	
    public com.jeesite.common.shiro.session.SessionDAO sessionDAO() {	
        void a2;	
        M m2 = new M();	
        void v0 = a2;	
        void v1 = a2;	
        v0.setSessionIdGenerator((SessionIdGenerator)new IdGen());	
        v0.ALLATORIxDEMO("sessionCache");	
        return v0;	
    }	
	
    @Bean(name={"sessionDAO"})	
    @ConditionalOnProperty(name={"spring.cache.isClusterMode"}, havingValue="true", matchIfMissing=false)	
    public com.jeesite.common.shiro.session.SessionDAO sessionDAORedis(CacheChannel cacheChannel) {	
        F a2;	
        F f = a2 = new F();	
        a2.setSessionIdGenerator((SessionIdGenerator)new IdGen());	
        f.ALLATORIxDEMO("sessionCache");	
        f.ALLATORIxDEMO(cacheChannel);	
        return f;	
    }	
}	
	
