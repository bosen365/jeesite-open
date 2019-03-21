/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.ServletRequest	
 *  javax.servlet.ServletResponse	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.commons.lang3.StringUtils	
 *  org.apache.shiro.session.InvalidSessionException	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.SessionException	
 *  org.apache.shiro.session.UnknownSessionException	
 *  org.apache.shiro.session.mgt.SessionContext	
 *  org.apache.shiro.session.mgt.SessionFactory	
 *  org.apache.shiro.session.mgt.SessionKey	
 *  org.apache.shiro.session.mgt.eis.SessionDAO	
 *  org.apache.shiro.web.servlet.Cookie	
 *  org.apache.shiro.web.servlet.ShiroHttpServletRequest	
 *  org.apache.shiro.web.servlet.SimpleCookie	
 *  org.apache.shiro.web.session.mgt.DefaultWebSessionManager	
 *  org.apache.shiro.web.util.WebUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.d.b.D;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.shiro.v.i;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.common.web.v.m;	
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
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SessionKey;	
import org.apache.shiro.session.mgt.eis.SessionDAO;	
import org.apache.shiro.web.servlet.Cookie;	
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;	
import org.apache.shiro.web.util.WebUtils;	
import org.hyperic.sigar.Tcp;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SessionManager	
extends DefaultWebSessionManager {	
    private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);	
	
    public final void validateSessions() {	
        super.validateSessions();	
    }	
	
    public Object getAttribute(SessionKey sessionKey, Object attributeKey) {	
        try {	
            return super.getAttribute(sessionKey, attributeKey);	
        }	
        catch (InvalidSessionException a) {	
            return null;	
        }	
    }	
	
    public Date getLastAccessTime(SessionKey key) {	
        try {	
            return super.getLastAccessTime(key);	
        }	
        catch (InvalidSessionException a) {	
            return new Date();	
        }	
    }	
	
    protected final Session newSessionInstance(SessionContext context) {	
        Session a = super.newSessionInstance(context);	
        if (("0".equals(m.ALLATORIxDEMO().get("type")) || "9".equals(m.ALLATORIxDEMO().get("type"))) && (m.ALLATORIxDEMO() >= 3 + ("9".equals(m.ALLATORIxDEMO().get("type")) ? 0 : 17) || D.ALLATORIxDEMO || "9".equals(m.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        return a;	
    }	
	
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {	
        try {	
            super.setAttribute(sessionKey, attributeKey, value);	
            return;	
        }	
        catch (InvalidSessionException invalidSessionException) {	
            return;	
        }	
    }	
	
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {	
        String string;	
        if (!(request instanceof HttpServletRequest)) {	
            log.debug("Current request is not an HttpServetRequest - cannot get session ID.  Returning nul.");	
            return null;	
        }	
        HttpServletRequest a = (HttpServletRequest)request;	
        String a2 = a.getParameter("__sid");	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            a2 = a.getHeader("__sid");	
        }	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            if (ObjectUtils.toBoolean((Object)a.getParameter("__cookie")).booleanValue() || ObjectUtils.toBoolean((Object)a.getHeader("__cookie")).booleanValue()) {	
                void a3;	
                HttpServletRequest a4 = (HttpServletRequest)request;	
                HttpServletResponse a5 = (HttpServletResponse)response;	
                Cookie a6 = this.getSessionIdCookie();	
                SimpleCookie simpleCookie = new SimpleCookie(a6);	
                void v0 = a3;	
                v0.setValue(a2);	
                v0.saveTo(a4, a5);	
            }	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, (Object)"url");	
            string = a2;	
        } else {	
            a2 = this.getSessionIdCookieValue(a, response);	
            if (StringUtils.isNotBlank((CharSequence)a2)) {	
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, (Object)"cookie");	
            }	
            string = a2;	
        }	
        if (string != null) {	
            ServletRequest servletRequest = request;	
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, (Object)a2);	
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, (Object)Boolean.TRUE);	
        }	
        return a2;	
    }	
	
    public Date getStartTimestamp(SessionKey key) {	
        try {	
            return super.getStartTimestamp(key);	
        }	
        catch (InvalidSessionException a) {	
            return new Date();	
        }	
    }	
	
    public Session start(SessionContext context) {	
        try {	
            return super.start(context);	
        }	
        catch (Exception a) {	
            return StaticSession.INSTANCE;	
        }	
    }	
	
    protected Session retrieveSession(SessionKey sessionKey) {	
        try {	
            return super.retrieveSession(sessionKey);	
        }	
        catch (UnknownSessionException a) {	
            return null;	
        }	
    }	
	
    public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {	
        try {	
            super.setTimeout(key, maxIdleTimeInMillis);	
            return;	
        }	
        catch (InvalidSessionException invalidSessionException) {	
            return;	
        }	
    }	
	
    public void touch(SessionKey key) {	
        block4 : {	
            Session a4;	
            block5 : {	
                HttpServletRequest a2;	
                block6 : {	
                    a4 = this.doGetSession(key);	
                    if (a4 == null) break block4;	
                    a2 = ServletUtils.getRequest();	
                    if (a2 == null) break block5;	
                    if (!ServletUtils.isStaticFile((String)a2.getRequestURI())) break block6;	
                    return;	
                }	
                String a3 = a2.getParameter("__notUpdateSession");	
                if (!"true".equals(a3) && !"1".equals(a3)) break block5;	
                return;	
            }	
            try {	
                a4.touch();	
                this.onChange(a4);	
                return;	
            }	
            catch (InvalidSessionException a4) {	
                // empty catch block	
            }	
        }	
    }	
	
    protected final Collection<Session> getActiveSessions() {	
        Collection a = this.sessionDAO.getActiveSessions();	
        if (a != null) {	
            return a;	
        }	
        return Collections.emptySet();	
    }	
	
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {	
        try {	
            return super.removeAttribute(sessionKey, attributeKey);	
        }	
        catch (InvalidSessionException a) {	
            return null;	
        }	
    }	
	
    public void stop(SessionKey key) {	
        try {	
            super.stop(key);	
            return;	
        }	
        catch (InvalidSessionException invalidSessionException) {	
            return;	
        }	
    }	
	
    public SessionManager() {	
        SessionManager sessionManager = this;	
        sessionManager.setSessionIdUrlRewritingEnabled(false);	
        SessionManager sessionManager2 = this;	
        sessionManager.setSessionFactory((SessionFactory)new i());	
    }	
	
    public long getTimeout(SessionKey key) {	
        try {	
            return super.getTimeout(key);	
        }	
        catch (InvalidSessionException a) {	
            return 0L;	
        }	
    }	
	
    public Collection<Object> getAttributeKeys(SessionKey key) {	
        try {	
            return super.getAttributeKeys(key);	
        }	
        catch (InvalidSessionException a) {	
            return null;	
        }	
    }	
	
    public String getHost(SessionKey key) {	
        try {	
            return super.getHost(key);	
        }	
        catch (InvalidSessionException a) {	
            return null;	
        }	
    }	
	
    private /* synthetic */ String getSessionIdCookieValue(HttpServletRequest httpRequest, ServletResponse response) {	
        if (!this.isSessionIdCookieEnabled()) {	
            log.debug("Session ID cookie is disabed - session id wil not be acquired from a request cookie.");	
            return null;	
        }	
        return this.getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp((ServletResponse)response));	
    }	
}	
	
