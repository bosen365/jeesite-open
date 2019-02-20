package com.jeesite.common.shiro.e;	
	
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
import java.util.Collection;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
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
	
public class M extends AbstractSessionDAO implements SessionDAO {	
   private String c = "sessionCache";	
   private Logger ALLATORIxDEMO = LoggerFactory.getLogger(this.getClass());	
	
   public void ALLATORIxDEMO(String sessionCache) {	
      this.c = sessionCache;	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor) {	
      return this.getActiveSessions(excludeLeave, excludeVisitor, (String)null, (String)null, (String)null);	
   }	
	
   public void update(Session session) throws UnknownSessionException {	
      if (session != null && session.getId() != null) {	
         if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {	
            this.delete(session);	
         } else {	
            Level1Cache a;	
            String var10000 = (a = CacheProviderHolder.getLevel1Cache(this.c)).getClass().getName();	
            CharSequence[] var10001 = new CharSequence[2];	
            boolean var10003 = true;	
            var10001[0] = "caffeine";	
            var10001[1] = "ehcache";	
            if (StringUtils.containsAny(var10000, var10001)) {	
               a.put(ObjectUtils.toString(session.getId()), session);	
            }	
         }	
	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            a.setAttribute("session_" + session.getId(), session);	
         }	
	
         this.ALLATORIxDEMO.debug("update { ", session.getId());	
      }	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
      return ALLATORIxDEMO(this.getActiveSessions(), excludeLeave, excludeVisitor, excludeSessionId, includeSessionId, includeUserCode);	
   }	
	
   public Serializable create(Session session) {	
      return this.doCreate(session);	
   }	
	
   public Collection getActiveSessions() {	
      List a = ListUtils.newArrayList();	
      Level1Cache a;	
      Level1Cache var10000 = a = CacheProviderHolder.getLevel1Cache(this.c);	
      Map a;	
      if ((a = var10000.get(var10000.keys())) != null) {	
         a.forEach((key, value) -> {	
            Session ax;	
            if ((ax = (Session)value) == null) {	
               String[] var10001 = new String[1];	
               boolean var10003 = true;	
               var10001[0] = key;	
               a.evict(var10001);	
            } else {	
               a.add(ax);	
            }	
         });	
      }	
	
      return a;	
   }	
	
   public Session readSession(Serializable sessionId) throws UnknownSessionException {	
      return this.doReadSession(sessionId);	
   }	
	
   public void delete(Session session) {	
      if (session != null && session.getId() != null) {	
         String a;	
         if (StringUtils.isNotBlank(a = (String)session.getAttribute("userCode"))) {	
            CacheUtils.removeCache((new StringBuilder()).insert(0, "userCache_").append(a).toString());	
         }	
	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            a.removeAttribute("session_" + session.getId());	
         }	
	
         Level1Cache var10000 = CacheProviderHolder.getLevel1Cache(this.c);	
         String[] var10001 = new String[1];	
         boolean var10003 = true;	
         var10001[0] = ObjectUtils.toString(session.getId());	
         var10000.evict(var10001);	
         this.ALLATORIxDEMO.debug("delete { ", session.getId());	
      }	
   }	
	
   public static Collection ALLATORIxDEMO(Collection activeSessions, boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
      String a = null;	
      if (Global.isUseCorpModel()) {	
         a = CorpUtils.getCurrentCorpCode();	
      }	
	
      Map a = MapUtils.newHashMap();	
      Iterator var8 = activeSessions.iterator();	
	
      while(true) {	
         Session a;	
         String a;	
         String a;	
         boolean a;	
         label89:	
         do {	
            label80:	
            while(true) {	
               Iterator var10000 = var8;	
	
               while(true) {	
                  while(var10000.hasNext()) {	
                     if ((a = (Session)var8.next()) == null) {	
                        var10000 = var8;	
                     } else {	
                        String a = ObjectUtils.toString(a.getId());	
                        if ("1".equals(a)) {	
                           continue label80;	
                        }	
	
                        if (a.equals(excludeSessionId)) {	
                           var10000 = var8;	
                        } else if (excludeLeave && DateUtils.pastMinutes(a.getLastAccessTime()) > 3L) {	
                           var10000 = var8;	
                        } else {	
                           String a = ObjectUtils.toString(a.getAttribute("corpCode"));	
                           if (a != null && !StringUtils.equals(a, a)) {	
                              var10000 = var8;	
                           } else {	
                              a = "";	
                              a = "";	
                              Object a;	
                              LoginInfo a;	
                              if ((a = a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null && a instanceof PrincipalCollection && (a = (LoginInfo)((PrincipalCollection)a).getPrimaryPrincipal()) != null) {	
                                 a = a.getId();	
                                 a = a.getParam("deviceType");	
                              }	
	
                              if (!excludeVisitor || !StringUtils.isBlank(a)) {	
                                 a = true;	
                                 if (StringUtils.isNotBlank(includeSessionId) && !StringUtils.equals(includeSessionId, a)) {	
                                    a = false;	
                                 }	
	
                                 if (StringUtils.isNotBlank(includeUserCode) && !StringUtils.equals(includeUserCode, a)) {	
                                    a = false;	
                                 }	
                                 continue label89;	
                              }	
	
                              var10000 = var8;	
                           }	
                        }	
                     }	
                  }	
	
                  return a.values();	
               }	
            }	
         } while(!a);	
	
         String a = null;	
         HashMap var19;	
         if (!StringUtils.isNotBlank(excludeSessionId) && !StringUtils.isNotBlank(includeSessionId)) {	
            a = (new StringBuilder()).insert(0, a).append(a).toString();	
            var19 = a;	
         } else {	
            a = ObjectUtils.toString(a.getId());	
            var19 = a;	
         }	
	
         Session a;	
         if ((a = (Session)var19.get(a)) != null) {	
            if (a.getLastAccessTime().getTime() > a.getLastAccessTime().getTime()) {	
               a.put(a, a);	
            }	
         } else {	
            a.put(a, a);	
         }	
      }	
   }	
	
   protected Session doReadSession(Serializable sessionId) {	
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
         Session a = (Session)CacheProviderHolder.getLevel1Cache(this.c).get(ObjectUtils.toString(sessionId));	
         this.ALLATORIxDEMO.debug("readSession { {}", sessionId, a != null ? a.getRequestURI() : "");	
         if (a != null && a != null) {	
            a.setAttribute("session_" + sessionId, a);	
         }	
	
         return a;	
      }	
   }	
	
   protected Serializable doCreate(Session session) {	
      Serializable a = this.generateSessionId(session);	
      this.assignSessionId(session, a);	
      if (!"0".equals(com.jeesite.common.beetl.e.F.ALLATORIxDEMO().get("type")) && !"9".equals(com.jeesite.common.beetl.e.F.ALLATORIxDEMO().get("type")) || com.jeesite.common.beetl.e.F.ALLATORIxDEMO() < 3 + ("9".equals(com.jeesite.common.beetl.e.F.ALLATORIxDEMO().get("type")) ? 0 : 7) && !m.ALLATORIxDEMO && (!"9".equals(com.jeesite.common.beetl.e.F.ALLATORIxDEMO().get("type")) || ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L <= 1L)) {	
         this.update(session);	
         return a;	
      } else {	
         throw new SessionException();	
      }	
   }	
}	
