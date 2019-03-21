/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.v;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.d.b.L;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.shiro.v.C;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
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
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class m	
extends C {	
    private CacheChannel i;	
    private String b = "sessionCache";	
    private Logger ALLATORIxDEMO = LoggerFactory.getLogger(this.getClass());	
	
    @Override	
    public Session readSession(Serializable sessionId) throws UnknownSessionException {	
        return this.doReadSession(sessionId);	
    }	
	
    @Override	
    protected Serializable doCreate(Session session) {	
        m m2 = this;	
        Serializable a = m2.generateSessionId(session);	
        m2.assignSessionId(session, a);	
        if (("0".equals(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("type")) || "9".equals(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("type"))) && (com.jeesite.modules.gen.service.C.ALLATORIxDEMO() >= 3 + ("9".equals(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("type")) ? 0 : 17) || L.ALLATORIxDEMO || "9".equals(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            throw new SessionException();	
        }	
        this.update(session);	
        return a;	
    }	
	
    private /* synthetic */ void ALLATORIxDEMO(List values, String key, CacheObject value) {	
        Session a = (Session)value.getValue();	
        if (a == null) {	
            m m2 = this;	
            m2.i.evict(m2.b, key);	
            return;	
        }	
        values.add(a);	
    }	
	
    @Override	
    protected Session doReadSession(Serializable sessionId) {	
        Object a;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
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
        m m2 = this;	
        a = (Session)m2.i.get(m2.b, ObjectUtils.toString(sessionId), new boolean[0]).getValue();	
        this.ALLATORIxDEMO.debug("readSession {} {}", (Object)sessionId, (Object)(a3 != null ? a3.getRequestURI() : ""));	
        if (a3 != null && a != null) {	
            a3.setAttribute("session_" + sessionId, a);	
        }	
        return a;	
    }	
	
    @Override	
    public void delete(Session session) {	
        HttpServletRequest a;	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.delete(session);	
            return;	
        }	
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
        m m2 = this;	
        m2.i.evict(m2.b, ObjectUtils.toString(session.getId()));	
        this.ALLATORIxDEMO.debug("delete {} ", (Object)session.getId());	
    }	
	
    public void ALLATORIxDEMO(CacheChannel channel) {	
        this.i = channel;	
    }	
	
    @Override	
    public Serializable create(Session session) {	
        return this.doCreate(session);	
    }	
	
    @Override	
    public void update(Session session) throws UnknownSessionException {	
        if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.C.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.update(session);	
            return;	
        }	
        if (session == null || session.getId() == null) {	
            return;	
        }	
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
        } else {	
            m m2 = this;	
            m2.i.set(m2.b, ObjectUtils.toString(session.getId()), session);	
        }	
        HttpServletRequest a = ServletUtils.getRequest();	
        if (a != null) {	
            a.setAttribute("session_" + session.getId(), session);	
        }	
        this.ALLATORIxDEMO.debug("update {} ", (Object)session.getId());	
    }	
	
    @Override	
    public Collection<Session> getActiveSessions() {	
        void a;	
        ArrayList arrayList = ListUtils.newArrayList();	
        m m2 = this;	
        Collection<String> a2 = this.i.keys(m2.b);	
        Map<String, CacheObject> a3 = m2.i.get(this.b, a2);	
        if (a3 != null) {	
            a3.forEach((arg_0, arg_1) -> this.ALLATORIxDEMO((List)a, arg_0, arg_1));	
        }	
        return a;	
    }	
}	
	
