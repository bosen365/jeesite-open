package com.jeesite.common.shiro.e;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.Serializable;	
import java.lang.management.ManagementFactory;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.SessionException;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class F extends M {	
   private String D = "sessionCache";	
   private Logger c = LoggerFactory.getLogger(this.getClass());	
   private CacheChannel ALLATORIxDEMO;	
	
   protected Session doReadSession(Serializable sessionId) {	
      if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.doReadSession(sessionId);	
      } else {	
         Session a = null;	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            if (ServletUtils.isStaticFile(a.getRequestURI())) {	
               return StaticSession.INSTANCE;	
            }	
	
            a = (Session)a.getAttribute((new StringBuilder()).insert(0, "session_").append(sessionId).toString());	
         }	
	
         if (a != null) {	
            return a;	
         } else {	
            CacheChannel var10000 = this.ALLATORIxDEMO;	
            String var10001 = this.D;	
            String var10002 = ObjectUtils.toString(sessionId);	
            boolean[] var10003 = new boolean[0];	
            boolean var10005 = true;	
            Session a = (Session)var10000.get(var10001, var10002, var10003).getValue();	
            this.c.debug("readSession {} {}", sessionId, a != null ? a.getRequestURI() : "");	
            if (a != null && a != null) {	
               a.setAttribute("session_" + sessionId, a);	
            }	
	
            return a;	
         }	
      }	
   }	
	
   public Session readSession(Serializable sessionId) throws UnknownSessionException {	
      return this.doReadSession(sessionId);	
   }	
	
   public void ALLATORIxDEMO(CacheChannel channel) {	
      this.ALLATORIxDEMO = channel;	
   }	
	
   public void delete(Session session) {	
      if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster"))) {	
         super.delete(session);	
      } else if (session != null && session.getId() != null) {	
         String a;	
         if (StringUtils.isNotBlank(a = (String)session.getAttribute("userCode"))) {	
            CacheUtils.removeCache((new StringBuilder()).insert(0, "userCache_").append(a).toString());	
         }	
	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            a.removeAttribute("session_" + session.getId());	
         }	
	
         CacheChannel var10000 = this.ALLATORIxDEMO;	
         String var10001 = this.D;	
         String[] var10002 = new String[1];	
         boolean var10004 = true;	
         var10002[0] = ObjectUtils.toString(session.getId());	
         var10000.evict(var10001, var10002);	
         this.c.debug("delete {} ", session.getId());	
      }	
   }	
	
   public void update(Session session) throws UnknownSessionException {	
      if (!ObjectUtils.toBoolean(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("fnCluster"))) {	
         super.update(session);	
      } else if (session != null && session.getId() != null) {	
         if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
         } else {	
            this.ALLATORIxDEMO.set(this.D, ObjectUtils.toString(session.getId()), session);	
         }	
	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            a.setAttribute("session_" + session.getId(), session);	
         }	
	
         this.c.debug("update {} ", session.getId());	
      }	
   }	
	
   public Serializable create(Session session) {	
      return this.doCreate(session);	
   }	
	
   protected Serializable doCreate(Session session) {	
      Serializable a = this.generateSessionId(session);	
      this.assignSessionId(session, a);	
      if (!"0".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) && !"9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) || com.jeesite.modules.gen.service.M.ALLATORIxDEMO() < 3 + ("9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) ? 0 : 7) && !m.ALLATORIxDEMO && (!"9".equals(com.jeesite.modules.gen.service.M.ALLATORIxDEMO().get("type")) || ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L <= 1L)) {	
         this.update(session);	
         return a;	
      } else {	
         throw new SessionException();	
      }	
   }	
	
   public Collection getActiveSessions() {	
      ArrayList a = ListUtils.newArrayList();	
      Collection a = this.ALLATORIxDEMO.keys(this.D);	
      Map a;	
      if ((a = this.ALLATORIxDEMO.get(this.D, a)) != null) {	
         a.forEach((key, value) -> {	
            Session ax;	
            if ((ax = (Session)value.getValue()) == null) {	
               CacheChannel var10000 = this.ALLATORIxDEMO;	
               String var10001 = this.D;	
               String[] var10002 = new String[1];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10000.evict(var10001, var10002);	
            } else {	
               a.add(ax);	
            }	
         });	
      }	
	
      return a;	
   }	
}	
