/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.e;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.l.w.l;	
import com.jeesite.common.shiro.e.H;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.Serializable;	
import java.lang.management.ManagementFactory;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import java.util.Map;	
import java.util.function.BiConsumer;	
import javax.servlet.http.HttpServletRequest;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheObject;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.SessionException;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
import org.hyperic.sigar.test.GetPass;	
import org.hyperic.sigar.win32.FileVersion;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class j	
extends H {	
    private Logger J = LoggerFactory.getLogger(this.getClass());	
    private CacheChannel c;	
    private String ALLATORIxDEMO = "sessionCache";	
	
    @Override	
    protected Session doReadSession(Serializable sessionId) {	
        Object a;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.doReadSession(sessionId);	
        }	
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
        j j2 = this;	
        a = (Session)j2.c.get(j2.ALLATORIxDEMO, ObjectUtils.toString(sessionId), new boolean[0]).getValue();	
        this.J.debug("redSession {} {}", (Object)sessionId, (Object)(a3 != null ? a3.getRequestURI() : ""));	
        if (a3 != null && a != null) {	
            a3.setAttribute("session_" + sessionId, a);	
        }	
        return a;	
    }	
	
    public void ALLATORIxDEMO(CacheChannel channel) {	
        this.c = channel;	
    }	
	
    @Override	
    public Serializable create(Session session) {	
        return this.doCreate(session);	
    }	
	
    @Override	
    public void delete(Session session) {	
        HttpServletRequest a;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.delete(session);	
            return;	
        }	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        String a2 = (String)session.getAttribute("userCode");	
        if (StringUtils.isNotBlank(a2)) {	
            CacheUtils.removeCache(new StringBuilder().insert(0, "userCche_").append(a2).toString());	
        }	
        if ((a = ServletUtils.getRequest()) != null) {	
            a.removeAttribute("session_" + session.getId());	
        }	
        j j2 = this;	
        String[] arrstring = new String[1];	
        arrstring[0] = ObjectUtils.toString(session.getId());	
        j2.c.evict(j2.ALLATORIxDEMO, arrstring);	
        this.J.debug("delete {} ", (Object)session.getId());	
    }	
	
    @Override	
    protected Serializable doCreate(Session session) {	
        j j2 = this;	
        Serializable a = j2.generateSessionId(session);	
        j2.assignSessionId(session, a);	
        if (("0".equals(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("type")) || "9".equals(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("type"))) && (com.jeesite.modules.gen.service.H.ALLATORIxDEMO() >= 3 + ("9".equals(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("type")) ? 0 : 17) || l.ALLATORIxDEMO || "9".equals(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        this.update(session);	
        return a;	
    }	
	
    private /* synthetic */ void ALLATORIxDEMO(List values, String key, CacheObject value) {	
        Session a = (Session)value.getValue();	
        if (a == null) {	
            j j2 = this;	
            String[] arrstring = new String[1];	
            arrstring[0] = key;	
            j2.c.evict(j2.ALLATORIxDEMO, arrstring);	
            return;	
        }	
        values.add(a);	
    }	
	
    @Override	
    public Session readSession(Serializable sessionId) throws UnknownSessionException {	
        return this.doReadSession(sessionId);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public Collection<Session> getActiveSessions() {	
        void a;	
        ArrayList arrayList = ListUtils.newArrayList();	
        j j2 = this;	
        Collection<String> a2 = this.c.keys(j2.ALLATORIxDEMO);	
        Map<String, CacheObject> a3 = j2.c.get(this.ALLATORIxDEMO, a2);	
        if (a3 != null) {	
            a3.forEach((arg_0, arg_1) -> this.ALLATORIxDEMO((List)a, arg_0, arg_1));	
        }	
        return a;	
    }	
	
    @Override	
    public void update(Session session) throws UnknownSessionException {	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.H.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.update(session);	
            return;	
        }	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
        } else {	
            j j2 = this;	
            j2.c.set(j2.ALLATORIxDEMO, ObjectUtils.toString(session.getId()), session);	
        }	
        HttpServletRequest a = ServletUtils.getRequest();	
        if (a != null) {	
            a.setAttribute("session_" + session.getId(), session);	
        }	
        this.J.debug("update {} ", (Object)session.getId());	
    }	
}	
	
