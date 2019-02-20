/*	
 * Decompiled with CFR 0.139.	
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
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.j.n.D;	
import com.jeesite.common.shiro.e.E;	
import com.jeesite.common.shiro.session.StaticSession;	
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
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SessionKey;	
import org.apache.shiro.session.mgt.eis.SessionDAO;	
import org.apache.shiro.web.servlet.Cookie;	
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;	
import org.apache.shiro.web.servlet.SimpleCookie;	
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;	
import org.apache.shiro.web.util.WebUtils;	
import org.hyperic.sigar.ProcFd;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SessionManager	
extends DefaultWebSessionManager {	
    private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);	
	
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {	
        try {	
            super.setAttribute(sessionKey, attributeKey, value);	
            return;	
        }	
        catch (InvalidSessionException invalidSessionException) {	
            return;	
        }	
    }	
	
    public Date getLastAccessTime(SessionKey key) {	
        try {	
            return super.getLastAccessTime(key);	
        }	
        catch (InvalidSessionException a2) {	
            return new Date();	
        }	
    }	
	
    public void touch(SessionKey key) {	
        block4 : {	
            Session a22;	
            block5 : {	
                HttpServletRequest a3;	
                block6 : {	
                    a22 = this.doGetSession(key);	
                    if (a22 == null) break block4;	
                    a3 = ServletUtils.getRequest();	
                    if (a3 == null) break block5;	
                    if (!ServletUtils.isStaticFile((String)a3.getRequestURI())) break block6;	
                    return;	
                }	
                String a4 = a3.getParameter("__notUpdateSesion");	
                if (!"true".equals(a4) && !"1".equals(a4)) break block5;	
                return;	
            }	
            try {	
                a22.touch();	
                this.onChange(a22);	
                return;	
            }	
            catch (InvalidSessionException a22) {	
                // empty catch block	
            }	
        }	
    }	
	
    private /* synthetic */ String getSessionIdCookieValue(HttpServletRequest httpRequest, ServletResponse response) {	
        if (!this.isSessionIdCookieEnabled()) {	
            log.debug("Session ID cookie is disabled - session id will not be acquired from a request cookie.");	
            return null;	
        }	
        return this.getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp((ServletResponse)response));	
    }	
	
    public SessionManager() {	
        SessionManager sessionManager = this;	
        sessionManager.setSessionIdUrlRewritingEnabled(false);	
        SessionManager sessionManager2 = this;	
        sessionManager.setSessionFactory((SessionFactory)new E());	
    }	
	
    public long getTimeout(SessionKey key) {	
        try {	
            return super.getTimeout(key);	
        }	
        catch (InvalidSessionException a2) {	
            return 0L;	
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
	
    protected final Collection<Session> getActiveSessions() {	
        Collection a2 = this.sessionDAO.getActiveSessions();	
        if (a2 != null) {	
            return a2;	
        }	
        return Collections.emptySet();	
    }	
	
    public Session start(SessionContext context) {	
        try {	
            return super.start(context);	
        }	
        catch (Exception a2) {	
            return StaticSession.INSTANCE;	
        }	
    }	
	
    public String getHost(SessionKey key) {	
        try {	
            return super.getHost(key);	
        }	
        catch (InvalidSessionException a2) {	
            return null;	
        }	
    }	
	
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {	
        try {	
            return super.removeAttribute(sessionKey, attributeKey);	
        }	
        catch (InvalidSessionException a2) {	
            return null;	
        }	
    }	
	
    protected final Session newSessionInstance(SessionContext context) {	
        Session a2 = super.newSessionInstance(context);	
        if (("0".equals(F.ALLATORIxDEMO().get("type")) || "9".equals(F.ALLATORIxDEMO().get("type"))) && (F.ALLATORIxDEMO() >= 3 + ("9".equals(F.ALLATORIxDEMO().get("type")) ? 0 : 7) || D.ALLATORIxDEMO || "9".equals(F.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        return a2;	
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
	
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {	
        String string;	
        if (!(request instanceof HttpServletRequest)) {	
            log.debug("Current request is not an HttpServletRequet - cannot get esion ID.  Returning null.");	
            return null;	
        }	
        HttpServletRequest a2 = (HttpServletRequest)request;	
        String a3 = a2.getParameter("__sid");	
        if (StringUtils.isBlank((CharSequence)a3)) {	
            a3 = a2.getHeader("__sid");	
        }	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            if (ObjectUtils.toBoolean((Object)a2.getParameter("__cookie")).booleanValue() || ObjectUtils.toBoolean((Object)a2.getHeader("__cookie")).booleanValue()) {	
                void a4;	
                HttpServletRequest a5 = (HttpServletRequest)request;	
                HttpServletResponse a6 = (HttpServletResponse)response;	
                Cookie a7 = this.getSessionIdCookie();	
                SimpleCookie simpleCookie = new SimpleCookie(a7);	
                void v0 = a4;	
                v0.setValue(a3);	
                v0.saveTo(a5, a6);	
            }	
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, (Object)"url");	
            string = a3;	
        } else {	
            a3 = this.getSessionIdCookieValue(a2, response);	
            if (StringUtils.isNotBlank((CharSequence)a3)) {	
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, (Object)"cookie");	
            }	
            string = a3;	
        }	
        if (string != null) {	
            ServletRequest servletRequest = request;	
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, (Object)a3);	
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, (Object)Boolean.TRUE);	
        }	
        return a3;	
    }	
	
    public Object getAttribute(SessionKey sessionKey, Object attributeKey) {	
        try {	
            return super.getAttribute(sessionKey, attributeKey);	
        }	
        catch (InvalidSessionException a2) {	
            return null;	
        }	
    }	
	
    public Date getStartTimestamp(SessionKey key) {	
        try {	
            return super.getStartTimestamp(key);	
        }	
        catch (InvalidSessionException a2) {	
            return new Date();	
        }	
    }	
	
    public final void validateSessions() {	
        super.validateSessions();	
    }	
	
    public Collection<Object> getAttributeKeys(SessionKey key) {	
        try {	
            return super.getAttributeKeys(key);	
        }	
        catch (InvalidSessionException a2) {	
            return null;	
        }	
    }	
	
    protected Session retrieveSession(SessionKey sessionKey) {	
        try {	
            return super.retrieveSession(sessionKey);	
        }	
        catch (UnknownSessionException a2) {	
            return null;	
        }	
    }	
}	
	
