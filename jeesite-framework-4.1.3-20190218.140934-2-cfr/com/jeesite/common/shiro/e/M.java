/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  net.oschina.j2cache.CacheProviderHolder	
 *  net.oschina.j2cache.Level1Cache	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.SessionException	
 *  org.apache.shiro.session.UnknownSessionException	
 *  org.apache.shiro.session.mgt.ValidatingSession	
 *  org.apache.shiro.session.mgt.eis.AbstractSessionDAO	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.shiro.e;	
	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.io.Serializable;	
import java.lang.management.ManagementFactory;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.function.BiConsumer;	
import javax.servlet.http.HttpServletRequest;	
import net.oschina.j2cache.CacheProviderHolder;	
import net.oschina.j2cache.Level1Cache;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.SessionException;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class M	
extends AbstractSessionDAO	
implements SessionDAO {	
    private String c = "sessionCache";	
    private Logger ALLATORIxDEMO = LoggerFactory.getLogger(this.getClass());	
	
    public void ALLATORIxDEMO(String sessionCache) {	
        this.c = sessionCache;	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions(boolean excludeLeave, boolean excludeVisitor) {	
        return this.getActiveSessions(excludeLeave, excludeVisitor, null, null, null);	
    }	
	
    public void update(Session session) throws UnknownSessionException {	
        Level1Cache a2;	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
        } else {	
            a2 = CacheProviderHolder.getLevel1Cache((String)this.c);	
            if (StringUtils.containsAny((CharSequence)a2.getClass().getName(), (CharSequence[])new CharSequence[]{"caffeine", "ehcache"})) {	
                a2.put(ObjectUtils.toString((Object)session.getId()), (Object)session);	
            }	
        }	
        a2 = ServletUtils.getRequest();	
        if (a2 != null) {	
            a2.setAttribute("session_" + session.getId(), (Object)session);	
        }	
        this.ALLATORIxDEMO.debug("update { ", (Object)session.getId());	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions(boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
        return M.ALLATORIxDEMO(this.getActiveSessions(), excludeLeave, excludeVisitor, excludeSessionId, includeSessionId, includeUserCode);	
    }	
	
    public Serializable create(Session session) {	
        return this.doCreate(session);	
    }	
	
    public Collection<Session> getActiveSessions() {	
        ArrayList a2 = ListUtils.newArrayList();	
        Level1Cache a3 = CacheProviderHolder.getLevel1Cache((String)this.c);	
        Map a4 = a3.get(a3.keys());	
        if (a4 != null) {	
            a4.forEach((key, value) -> {	
                Session a2 = (Session)value;	
                if (a2 == null) {	
                    a3.evict(new String[]{key});	
                    return;	
                }	
                a2.add(a2);	
            });	
        }	
        return a2;	
    }	
	
    public Session readSession(Serializable sessionId) throws UnknownSessionException {	
        return this.doReadSession(sessionId);	
    }	
	
    public void delete(Session session) {	
        HttpServletRequest a2;	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        String a3 = (String)session.getAttribute((Object)"userCode");	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            CacheUtils.removeCache(new StringBuilder().insert(0, "userCache_").append(a3).toString());	
        }	
        if ((a2 = ServletUtils.getRequest()) != null) {	
            a2.removeAttribute("session_" + session.getId());	
        }	
        CacheProviderHolder.getLevel1Cache((String)this.c).evict(new String[]{ObjectUtils.toString((Object)session.getId())});	
        this.ALLATORIxDEMO.debug("delete { ", (Object)session.getId());	
    }	
	
    public static Collection<Session> ALLATORIxDEMO(Collection<Session> activeSessions, boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
        String a2 = null;	
        if (Global.isUseCorpModel().booleanValue()) {	
            a2 = CorpUtils.getCurrentCorpCode();	
        }	
        HashMap a3 = MapUtils.newHashMap();	
        Iterator<Session> iterator = activeSessions.iterator();	
        block0 : do {	
            Iterator<Session> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                LoginInfo a4;	
                HashMap hashMap;	
                Session a5 = iterator.next();	
                if (a5 == null) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a6 = ObjectUtils.toString((Object)a5.getId());	
                if ("1".equals(a6)) continue block0;	
                if (a6.equals(excludeSessionId)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                if (excludeLeave && DateUtils.pastMinutes((Date)a5.getLastAccessTime()) > 3L) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a7 = ObjectUtils.toString((Object)a5.getAttribute((Object)"corpCode"));	
                if (a2 != null && !StringUtils.equals((CharSequence)a2, (CharSequence)a7)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a8 = "";	
                String a9 = "";	
                Object a10 = a5.getAttribute((Object)DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                if (a10 != null && a10 instanceof PrincipalCollection && (a4 = (LoginInfo)((PrincipalCollection)a10).getPrimaryPrincipal()) != null) {	
                    LoginInfo loginInfo = a4;	
                    a8 = loginInfo.getId();	
                    a9 = loginInfo.getParam("deviceType");	
                }	
                if (excludeVisitor && StringUtils.isBlank((CharSequence)a8)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                boolean a22 = true;	
                if (StringUtils.isNotBlank((CharSequence)includeSessionId) && !StringUtils.equals((CharSequence)includeSessionId, (CharSequence)a6)) {	
                    a22 = false;	
                }	
                if (StringUtils.isNotBlank((CharSequence)includeUserCode) && !StringUtils.equals((CharSequence)includeUserCode, (CharSequence)a8)) {	
                    a22 = false;	
                }	
                if (!a22) continue block0;	
                String a11 = null;	
                if (StringUtils.isNotBlank((CharSequence)excludeSessionId) || StringUtils.isNotBlank((CharSequence)includeSessionId)) {	
                    a11 = ObjectUtils.toString((Object)a5.getId());	
                    hashMap = a3;	
                } else {	
                    a11 = new StringBuilder().insert(0, a8).append(a9).toString();	
                    hashMap = a3;	
                }	
                Session a12 = (Session)hashMap.get(a11);	
                if (a12 != null) {	
                    if (a12.getLastAccessTime().getTime() <= a5.getLastAccessTime().getTime()) continue block0;	
                    a3.put(a11, a5);	
                    continue block0;	
                }	
                a3.put(a11, a5);	
                continue block0;	
            }	
            break;	
        } while (true);	
        return a3.values();	
    }	
	
    protected Session doReadSession(Serializable sessionId) {	
        String a2;	
        Session a3 = null;	
        HttpServletRequest a4 = ServletUtils.getRequest();	
        if (a4 != null) {	
            a2 = a4.getRequestURI();	
            if (ServletUtils.isStaticFile((String)a2)) {	
                return StaticSession.INSTANCE;	
            }	
            a3 = (Session)a4.getAttribute(new StringBuilder().insert(0, "session_").append(sessionId).toString());	
        }	
        if (a3 != null) {	
            return a3;	
        }	
        a2 = CacheProviderHolder.getLevel1Cache((String)this.c);	
        Session a5 = (Session)a2.get(ObjectUtils.toString((Object)sessionId));	
        this.ALLATORIxDEMO.debug("readSession { {}", (Object)sessionId, (Object)(a4 != null ? a4.getRequestURI() : ""));	
        if (a4 != null && a5 != null) {	
            a4.setAttribute("session_" + sessionId, (Object)a5);	
        }	
        return a5;	
    }	
	
    protected Serializable doCreate(Session session) {	
        M m2 = this;	
        Serializable a2 = m2.generateSessionId(session);	
        m2.assignSessionId(session, a2);	
        if (("0".equals(F.ALLATORIxDEMO().get("type")) || "9".equals(F.ALLATORIxDEMO().get("type"))) && (F.ALLATORIxDEMO() >= 3 + ("9".equals(F.ALLATORIxDEMO().get("type")) ? 0 : 7) || m.ALLATORIxDEMO || "9".equals(F.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        this.update(session);	
        return a2;	
    }	
}	
	
