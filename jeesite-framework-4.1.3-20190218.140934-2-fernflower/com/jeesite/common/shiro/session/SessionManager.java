package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.j.n.D;	
import com.jeesite.common.shiro.e.E;	
import com.jeesite.common.web.e.F;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.Serializable;	
import java.lang.management.ManagementFactory;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Date;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.SessionException;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionKey;	
import org.apache.shiro.web.servlet.Cookie;	
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;	
import org.apache.shiro.web.util.WebUtils;	
import org.hyperic.sigar.ProcFd;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SessionManager extends DefaultWebSessionManager {	
   private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);	
	
   public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {	
      try {	
         super.setAttribute(sessionKey, attributeKey, value);	
      } catch (InvalidSessionException var5) {	
      }	
   }	
	
   public Date getLastAccessTime(SessionKey key) {	
      try {	
         return super.getLastAccessTime(key);	
      } catch (InvalidSessionException var3) {	
         return new Date();	
      }	
   }	
	
   public void touch(SessionKey key) {	
      try {	
         Session a;	
         if ((a = this.doGetSession(key)) != null) {	
            HttpServletRequest a;	
            if ((a = ServletUtils.getRequest()) != null) {	
               if (ServletUtils.isStaticFile(a.getRequestURI())) {	
                  return;	
               }	
	
               String a = a.getParameter("__notUpdateSesion");	
               if ("true".equals(a) || "1".equals(a)) {	
                  return;	
               }	
            }	
	
            a.touch();	
            this.onChange(a);	
            return;	
         }	
      } catch (InvalidSessionException var6) {	
      }	
	
   }	
	
   // $FF: synthetic method	
   private String getSessionIdCookieValue(HttpServletRequest httpRequest, ServletResponse response) {	
      if (!this.isSessionIdCookieEnabled()) {	
         log.debug("Session ID cookie is disabled - session id will not be acquired from a request cookie.");	
         return null;	
      } else {	
         return this.getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp(response));	
      }	
   }	
	
   public SessionManager() {	
      this.setSessionIdUrlRewritingEnabled(false);	
      this.setSessionFactory(new E());	
   }	
	
   public long getTimeout(SessionKey key) {	
      try {	
         return super.getTimeout(key);	
      } catch (InvalidSessionException var3) {	
         return 0L;	
      }	
   }	
	
   public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {	
      try {	
         super.setTimeout(key, maxIdleTimeInMillis);	
      } catch (InvalidSessionException var5) {	
      }	
   }	
	
   protected final Collection getActiveSessions() {	
      Collection a;	
      return (Collection)((a = this.sessionDAO.getActiveSessions()) != null ? a : Collections.emptySet());	
   }	
	
   public Session start(SessionContext context) {	
      try {	
         return super.start(context);	
      } catch (Exception var3) {	
         return StaticSession.INSTANCE;	
      }	
   }	
	
   public String getHost(SessionKey key) {	
      try {	
         return super.getHost(key);	
      } catch (InvalidSessionException var3) {	
         return null;	
      }	
   }	
	
   public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {	
      try {	
         return super.removeAttribute(sessionKey, attributeKey);	
      } catch (InvalidSessionException var4) {	
         return null;	
      }	
   }	
	
   protected final Session newSessionInstance(SessionContext context) {	
      Session a = super.newSessionInstance(context);	
      if (!"0".equals(F.ALLATORIxDEMO().get("type")) && !"9".equals(F.ALLATORIxDEMO().get("type")) || F.ALLATORIxDEMO() < 3 + ("9".equals(F.ALLATORIxDEMO().get("type")) ? 0 : 7) && !D.ALLATORIxDEMO && (!"9".equals(F.ALLATORIxDEMO().get("type")) || ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L <= 1L)) {	
         return a;	
      } else {	
         throw new SessionException();	
      }	
   }	
	
   public void stop(SessionKey key) {	
      try {	
         super.stop(key);	
      } catch (InvalidSessionException var3) {	
      }	
   }	
	
   protected Serializable getSessionId(ServletRequest request, ServletResponse response) {	
      if (!(request instanceof HttpServletRequest)) {	
         log.debug("Current request is not an HttpServletRequet - cannot get esion ID.  Returning null.");	
         return null;	
      } else {	
         HttpServletRequest a;	
         String a;	
         if (StringUtils.isBlank(a = (a = (HttpServletRequest)request).getParameter("__sid"))) {	
            a = a.getHeader("__sid");	
         }	
	
         String var10000;	
         if (StringUtils.isNotBlank(a)) {	
            if (ObjectUtils.toBoolean(a.getParameter("__cookie")) || ObjectUtils.toBoolean(a.getHeader("__cookie"))) {	
               HttpServletRequest a = (HttpServletRequest)request;	
               HttpServletResponse a = (HttpServletResponse)response;	
               Cookie a = this.getSessionIdCookie();	
               SimpleCookie a = new SimpleCookie(a);	
               a.setValue(a);	
               a.saveTo(a, a);	
            }	
	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "url");	
            var10000 = a;	
         } else {	
            if (StringUtils.isNotBlank(a = this.getSessionIdCookieValue(a, response))) {	
               request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 != null) {	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, a);	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);	
         }	
	
         return a;	
      }	
   }	
	
   public Object getAttribute(SessionKey sessionKey, Object attributeKey) {	
      try {	
         return super.getAttribute(sessionKey, attributeKey);	
      } catch (InvalidSessionException var4) {	
         return null;	
      }	
   }	
	
   public Date getStartTimestamp(SessionKey key) {	
      try {	
         return super.getStartTimestamp(key);	
      } catch (InvalidSessionException var3) {	
         return new Date();	
      }	
   }	
	
   public final void validateSessions() {	
      super.validateSessions();	
   }	
	
   public Collection getAttributeKeys(SessionKey key) {	
      try {	
         return super.getAttributeKeys(key);	
      } catch (InvalidSessionException var3) {	
         return null;	
      }	
   }	
	
   protected Session retrieveSession(SessionKey sessionKey) {	
      try {	
         return super.retrieveSession(sessionKey);	
      } catch (UnknownSessionException var3) {	
         return null;	
      }	
   }	
}	
