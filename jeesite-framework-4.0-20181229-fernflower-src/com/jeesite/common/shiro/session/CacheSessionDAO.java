package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.l.j;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class CacheSessionDAO extends CachingSessionDAO implements SessionDAO {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   protected void doUpdate(Session session) {	
      if (session != null && session.getId() != null) {	
         CacheSessionDAO var10000;	
         if (session instanceof ValidatingSession) {	
            if (((ValidatingSession)session).isValid()) {	
               var10000 = this;	
               this.cache(session, session.getId());	
            } else {	
               var10000 = this;	
               this.uncache(session);	
            }	
         } else {	
            var10000 = this;	
            this.cache(session, session.getId());	
         }	
	
         var10000.logger.debug("update {} ", session.getId());	
      }	
   }	
	
   public Serializable create(Session session) {	
      return this.doCreate(session);	
   }	
	
   public Collection getActiveSessions() {	
      return super.getActiveSessions();	
   }	
	
   public void delete(Session session) {	
      this.doDelete(session);	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
      String a = null;	
      if (Global.isUseCorpModel()) {	
         a = CorpUtils.getCurrentCorpCode();	
      }	
	
      Map a = MapUtils.newHashMap();	
      Iterator var8 = super.getActiveSessions().iterator();	
	
      while(true) {	
         label77:	
         while(true) {	
            Iterator var10000 = var8;	
	
            while(true) {	
               while(var10000.hasNext()) {	
                  Session a;	
                  String a = (a = (Session)var8.next()).getId().toString();	
                  if ("1".equals(a)) {	
                     continue label77;	
                  }	
	
                  if (a.equals(excludeSessionId)) {	
                     var10000 = var8;	
                  } else if (excludeLeave && DateUtils.pastMinutes(a.getLastAccessTime()) > 3L) {	
                     var10000 = var8;	
                  } else {	
                     PrincipalCollection a = (PrincipalCollection)a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                     String a = "";	
                     String a = "";	
                     String a = "";	
                     LoginInfo a = a != null ? (LoginInfo)a.getPrimaryPrincipal() : null;	
                     if (a != null) {	
                        a = a.getId();	
                        a = a.getParam("corpCode");	
                        a = a.getParam("deviceType");	
                     }	
	
                     if (a != null && !StringUtils.equals(a, a)) {	
                        var10000 = var8;	
                     } else {	
                        if (!excludeVisitor || !StringUtils.isBlank(a)) {	
                           int a = true;	
                           if (StringUtils.isNotBlank(includeSessionId) && !StringUtils.equals(includeSessionId, a)) {	
                              a = false;	
                           }	
	
                           if (StringUtils.isNotBlank(includeUserCode) && !StringUtils.equals(includeUserCode, a)) {	
                              a = false;	
                           }	
	
                           if (a) {	
                              Session a;	
                              if ((a = (Session)a.get(a + a)) != null) {	
                                 if (a.getLastAccessTime().getTime() > a.getLastAccessTime().getTime()) {	
                                    a.put((new StringBuilder()).insert(0, a).append(a).toString(), a);	
                                 }	
                              } else {	
                                 a.put((new StringBuilder()).insert(0, a).append(a).toString(), a);	
                              }	
                           }	
                           continue label77;	
                        }	
	
                        var10000 = var8;	
                     }	
                  }	
               }	
	
               return a.values();	
            }	
         }	
      }	
   }	
	
   public void update(Session session) throws UnknownSessionException {	
      this.doUpdate(session);	
   }	
	
   public Session readSession(Serializable sessionId) throws UnknownSessionException {	
      return this.doReadSession(sessionId);	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor) {	
      return this.getActiveSessions(excludeLeave, excludeVisitor, (String)null, (String)null, (String)null);	
   }	
	
   protected Serializable doCreate(Session session) {	
      HttpServletRequest a;	
      if ((a = ServletUtils.getRequest()) != null && ServletUtils.isStaticFile(a.getRequestURI())) {	
         return null;	
      } else {	
         Serializable a = this.generateSessionId(session);	
         this.assignSessionId(session, a);	
         this.update(session);	
         return a;	
      }	
   }	
	
   protected void doDelete(Session session) {	
      if (session != null && session.getId() != null) {	
         this.uncache(session);	
         this.logger.debug("delete {} ", session.getId());	
      }	
   }	
	
   protected Session doReadSession(Serializable sessionId) {	
      try {	
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
            Session a = this.getCachedSession(sessionId);	
            this.logger.debug("readSession {} {}", sessionId, a != null ? a.getRequestURI() : "");	
            if (a != null && a != null) {	
               a.setAttribute("session_" + sessionId, a);	
            }	
	
            return a;	
         }	
      } catch (UnknownSessionException var5) {	
         return null;	
      }	
   }	
}	
