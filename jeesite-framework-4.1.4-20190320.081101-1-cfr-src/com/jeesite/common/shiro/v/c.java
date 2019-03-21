/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.v;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.beetl.v.m;	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.d.b.L;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
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
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class C	
extends AbstractSessionDAO	
implements SessionDAO {	
    private Logger b = LoggerFactory.getLogger(this.getClass());	
    private String ALLATORIxDEMO = "sessionCache";	
	
    @Override	
    public Collection<Session> getActiveSessions(boolean excludeLeave, boolean excludeVisitor) {	
        return this.getActiveSessions(excludeLeave, excludeVisitor, null, null, null);	
    }	
	
    @Override	
    protected Session doReadSession(Serializable sessionId) {	
        Object a;	
        Session a2 = null;	
        HttpServletRequest a3 = ServletUtils.getRequest();	
        if (a3 != null) {	
            a = a3.getRequestURI();	
            if (ServletUtils.isStaticFile((String)a)) {	
                return StaticSession.INSTANCE;	
            }	
            a2 = (Session)a3.getAttribute(new StringBuilder().insert(0, "session_").append(sessionId).toString());	
        }	
        if (a2 != null) {	
            return a2;	
        }	
        a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO);	
        Session a4 = (Session)a.get(ObjectUtils.toString(sessionId));	
        this.b.debug("readSession {} {}", (Object)sessionId, (Object)(a3 != null ? a3.getRequestURI() : ""));	
        if (a3 != null && a4 != null) {	
            a3.setAttribute("session_" + sessionId, a4);	
        }	
        return a4;	
    }	
	
    @Override	
    public Serializable create(Session session) {	
        return this.doCreate(session);	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions(boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
        return C.ALLATORIxDEMO(this.getActiveSessions(), excludeLeave, excludeVisitor, excludeSessionId, includeSessionId, includeUserCode);	
    }	
	
    public static Collection<Session> ALLATORIxDEMO(Collection<Session> activeSessions, boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
        String a = null;	
        if (Global.isUseCorpModel().booleanValue()) {	
            a = CorpUtils.getCurrentCorpCode();	
        }	
        HashMap<String, Session> a2 = MapUtils.newHashMap();	
        Iterator<Session> iterator = activeSessions.iterator();	
        block0 : do {	
            Iterator<Session> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                LoginInfo a3;	
                HashMap<String, Session> hashMap;	
                Session a4 = iterator.next();	
                if (a4 == null) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a5 = ObjectUtils.toString(a4.getId());	
                if ("1".equals(a5)) continue block0;	
                if (a5.equals(excludeSessionId)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                if (excludeLeave && DateUtils.pastMinutes(a4.getLastAccessTime()) > 3L) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a6 = ObjectUtils.toString(a4.getAttribute("corpCode"));	
                if (a != null && !StringUtils.equals(a, a6)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a7 = "";	
                String a8 = "";	
                Object a9 = a4.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                if (a9 != null && a9 instanceof PrincipalCollection && (a3 = (LoginInfo)((PrincipalCollection)a9).getPrimaryPrincipal()) != null) {	
                    LoginInfo loginInfo = a3;	
                    a7 = loginInfo.getId();	
                    a8 = loginInfo.getParam("deviceType");	
                }	
                if (excludeVisitor && StringUtils.isBlank(a7)) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                boolean a22 = true;	
                if (StringUtils.isNotBlank(includeSessionId) && !StringUtils.equals(includeSessionId, a5)) {	
                    a22 = false;	
                }	
                if (StringUtils.isNotBlank(includeUserCode) && !StringUtils.equals(includeUserCode, a7)) {	
                    a22 = false;	
                }	
                if (!a22) continue block0;	
                String a10 = null;	
                if (StringUtils.isNotBlank(excludeSessionId) || StringUtils.isNotBlank(includeSessionId)) {	
                    a10 = ObjectUtils.toString(a4.getId());	
                    hashMap = a2;	
                } else {	
                    a10 = new StringBuilder().insert(0, a7).append(a8).toString();	
                    hashMap = a2;	
                }	
                Session a11 = (Session)hashMap.get(a10);	
                if (a11 != null) {	
                    if (a11.getLastAccessTime().getTime() <= a4.getLastAccessTime().getTime()) continue block0;	
                    a2.put(a10, a4);	
                    continue block0;	
                }	
                a2.put(a10, a4);	
                continue block0;	
            }	
            break;	
        } while (true);	
        return a2.values();	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions() {	
        ArrayList<Session> a = ListUtils.newArrayList();	
        Level1Cache a2 = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO);	
        Map<String, Object> a3 = a2.get(a2.keys());	
        if (a3 != null) {	
            a3.forEach((key, value) -> {	
                Session a = (Session)value;	
                if (a == null) {	
                    a2.evict((String)key);	
                    return;	
                }	
                a.add(a);	
            });	
        }	
        return a;	
    }	
	
    @Override	
    public void update(Session session) throws UnknownSessionException {	
        Object a;	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
        } else {	
            a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO);	
            if (StringUtils.containsAny((CharSequence)a.getClass().getName(), "caffeine", "ehcache")) {	
                a.put(ObjectUtils.toString(session.getId()), session);	
            }	
        }	
        a = ServletUtils.getRequest();	
        if (a != null) {	
            a.setAttribute("session_" + session.getId(), session);	
        }	
        this.b.debug("update {} ", (Object)session.getId());	
    }	
	
    public void ALLATORIxDEMO(String sessionCache) {	
        this.ALLATORIxDEMO = sessionCache;	
    }	
	
    @Override	
    public void delete(Session session) {	
        HttpServletRequest a;	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        String a2 = (String)session.getAttribute("userCode");	
        if (StringUtils.isNotBlank(a2)) {	
            CacheUtils.removeCache(new StringBuilder().insert(0, "userCache_").append(a2).toString());	
        }	
        if ((a = ServletUtils.getRequest()) != null) {	
            a.removeAttribute("session_" + session.getId());	
        }	
        CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).evict(ObjectUtils.toString(session.getId()));	
        this.b.debug("delete {} ", (Object)session.getId());	
    }	
	
    @Override	
    public Session readSession(Serializable sessionId) throws UnknownSessionException {	
        return this.doReadSession(sessionId);	
    }	
	
    @Override	
    protected Serializable doCreate(Session session) {	
        C c2 = this;	
        Serializable a = c2.generateSessionId(session);	
        c2.assignSessionId(session, a);	
        if (("0".equals(m.ALLATORIxDEMO().get("type")) || "9".equals(m.ALLATORIxDEMO().get("type"))) && (m.ALLATORIxDEMO() >= 3 + ("9".equals(m.ALLATORIxDEMO().get("type")) ? 0 : 17) || L.ALLATORIxDEMO || "9".equals(m.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        this.update(session);	
        return a;	
    }	
}	
	
