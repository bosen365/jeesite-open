/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  net.oschina.j2cache.CacheChannel	
 *  net.oschina.j2cache.CacheObject	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.SessionException	
 *  org.apache.shiro.session.UnknownSessionException	
 *  org.apache.shiro.session.mgt.ValidatingSession	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.shiro.e;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.shiro.e.M;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
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
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class F	
extends M {	
    private String D = "sessionCache";	
    private Logger c = LoggerFactory.getLogger(this.getClass());	
    private CacheChannel ALLATORIxDEMO;	
	
    @Override	
    protected Session doReadSession(Serializable sessionId) {	
        String a2;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.doReadSession(sessionId);	
        }	
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
        F f = this;	
        a2 = (Session)f.ALLATORIxDEMO.get(f.D, ObjectUtils.toString((Object)sessionId), new boolean[0]).getValue();	
        this.c.debug("readSession {} {}", (Object)sessionId, (Object)(a4 != null ? a4.getRequestURI() : ""));	
        if (a4 != null && a2 != null) {	
            a4.setAttribute("session_" + sessionId, (Object)a2);	
        }	
        return a2;	
    }	
	
    @Override	
    public Session readSession(Serializable sessionId) throws UnknownSessionException {	
        return this.doReadSession(sessionId);	
    }	
	
    public void ALLATORIxDEMO(CacheChannel channel) {	
        this.ALLATORIxDEMO = channel;	
    }	
	
    @Override	
    public void delete(Session session) {	
        HttpServletRequest a2;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.delete(session);	
            return;	
        }	
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
        F f = this;	
        f.ALLATORIxDEMO.evict(f.D, new String[]{ObjectUtils.toString((Object)session.getId())});	
        this.c.debug("delete {} ", (Object)session.getId());	
    }	
	
    @Override	
    public void update(Session session) throws UnknownSessionException {	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.update(session);	
            return;	
        }	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
        } else {	
            F f = this;	
            f.ALLATORIxDEMO.set(f.D, ObjectUtils.toString((Object)session.getId()), (Object)session);	
        }	
        HttpServletRequest a2 = ServletUtils.getRequest();	
        if (a2 != null) {	
            a2.setAttribute("session_" + session.getId(), (Object)session);	
        }	
        this.c.debug("update {} ", (Object)session.getId());	
    }	
	
    @Override	
    public Serializable create(Session session) {	
        return this.doCreate(session);	
    }	
	
    @Override	
    protected Serializable doCreate(Session session) {	
        F f = this;	
        Serializable a2 = f.generateSessionId(session);	
        f.assignSessionId(session, a2);	
        if (("0".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) || "9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type"))) && (com.jeesite.modules.gen.service.M.ALLATORIxDEMO() >= 3 + ("9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) ? 0 : 7) || m.ALLATORIxDEMO || "9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        this.update(session);	
        return a2;	
    }	
	
    private /* synthetic */ void ALLATORIxDEMO(List values, String key, CacheObject value) {	
        Session a2 = (Session)value.getValue();	
        if (a2 == null) {	
            F f = this;	
            f.ALLATORIxDEMO.evict(f.D, new String[]{key});	
            return;	
        }	
        values.add(a2);	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions() {	
        void a2;	
        ArrayList arrayList = ListUtils.newArrayList();	
        F f = this;	
        Collection a3 = this.ALLATORIxDEMO.keys(f.D);	
        Map a4 = f.ALLATORIxDEMO.get(this.D, a3);	
        if (a4 != null) {	
            a4.forEach((arg_0, arg_1) -> this.ALLATORIxDEMO((List)a2, arg_0, arg_1));	
        }	
        return a2;	
    }	
}	
	
