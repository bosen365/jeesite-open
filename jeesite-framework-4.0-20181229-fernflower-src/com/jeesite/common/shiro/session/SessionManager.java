package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.network.IpUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Date;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionKey;	
import org.apache.shiro.session.mgt.SimpleSession;	
import org.apache.shiro.web.servlet.Cookie;	
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;	
import org.apache.shiro.web.util.WebUtils;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SessionManager extends DefaultWebSessionManager {	
   private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);	
	
   public void validateSessions() {	
      super.validateSessions();	
   }	
	
   public Session start(SessionContext context) {	
      try {	
         return super.start(context);	
      } catch (Exception var3) {	
         return StaticSession.INSTANCE;	
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
	
               String a = a.getParameter("__notUpdteSession");	
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
	
   public Object getAttribute(SessionKey sessionKey, Object attributeKey) {	
      try {	
         return super.getAttribute(sessionKey, attributeKey);	
      } catch (InvalidSessionException var4) {	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   private String getSessionIdCookieValue(ServletRequest request, ServletResponse response) {	
      if (!this.isSessionIdCookieEnabled()) {	
         log.debug("Session ID cookie is disabled - session id will not be acquired from a request cookie.");	
         return null;	
      } else if (!(request instanceof HttpServletRequest)) {	
         log.debug("Current request is not n HttpServletRequest - cannot get session ID cookie.  Returning null.");	
         return null;	
      } else {	
         HttpServletRequest a = (HttpServletRequest)request;	
         return this.getSessionIdCookie().readValue(a, WebUtils.toHttp(response));	
      }	
   }	
	
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
	
   protected Session newSessionInstance(SessionContext context) {	
      Session a;	
      if ((a = super.newSessionInstance(context)) instanceof SimpleSession) {	
         ((SimpleSession)a).setHost(IpUtils.getRemoteAddr(ServletUtils.getRequest()));	
      }	
	
      return a;	
   }	
	
   public void stop(SessionKey key) {	
      try {	
         super.stop(key);	
      } catch (InvalidSessionException var3) {	
      }	
   }	
	
   protected Serializable getSessionId(ServletRequest request, ServletResponse response) {	
      String var10000;	
      String a;	
      if (StringUtils.isNotBlank(a = this.getSessionIdCookieValue(request, response))) {	
         var10000 = a;	
         request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");	
      } else {	
         if (StringUtils.isNotBlank(a = request.getParameter("__sid"))) {	
            if (WebUtils.isTrue(request, "__cookie")) {	
               HttpServletRequest a = (HttpServletRequest)request;	
               HttpServletResponse a = (HttpServletResponse)response;	
               Cookie a = this.getSessionIdCookie();	
               SimpleCookie a = new SimpleCookie(a);	
               a.setValue(a);	
               a.saveTo(a, a);	
            }	
	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "url");	
         }	
	
         var10000 = a;	
      }	
	
      if (var10000 != null) {	
         request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, a);	
         request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);	
      }	
	
      return a;	
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
	
   public Date getStartTimestamp(SessionKey key) {	
      try {	
         return super.getStartTimestamp(key);	
      } catch (InvalidSessionException var3) {	
         return new Date();	
      }	
   }	
	
   public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {	
      try {	
         super.setTimeout(key, maxIdleTimeInMillis);	
      } catch (InvalidSessionException var5) {	
      }	
   }	
	
   public long getTimeout(SessionKey key) {	
      try {	
         return super.getTimeout(key);	
      } catch (InvalidSessionException var3) {	
         return 0L;	
      }	
   }	
	
   public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {	
      try {	
         return super.removeAttribute(sessionKey, attributeKey);	
      } catch (InvalidSessionException var4) {	
         return null;	
      }	
   }	
	
   public String getHost(SessionKey key) {	
      try {	
         return super.getHost(key);	
      } catch (InvalidSessionException var3) {	
         return null;	
      }	
   }	
}	
