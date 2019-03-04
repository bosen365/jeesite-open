package com.jeesite.common.shiro.session;	
	
import com.beust.jcommander.internal.Maps;	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.j;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.job.l.l;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Map.Entry;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.session.ExpiredSessionException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.UnknownSessionException;	
import org.apache.shiro.session.mgt.SimpleSession;	
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.SimplePrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import redis.clients.jedis.Jedis;	
	
public class JedisSessionDAO extends AbstractSessionDAO implements SessionDAO {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
   private String sessionKeyPrefix = "jeesite_session_";	
	
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
         Session a = null;	
         Jedis a = null;	
	
         HttpServletRequest var12;	
         label100: {	
            label99: {	
               try {	
                  try {	
                     a = JedisUtils.getResource();	
                     a = (Session)JedisUtils.toObject(a.get(JedisUtils.getBytesKey(this.sessionKeyPrefix + sessionId)));	
                     this.logger.debug("doReadSession {} {}", sessionId, a != null ? a.getRequestURI() : "");	
                     break label99;	
                  } catch (Exception var10) {	
                     Logger var10000 = this.logger;	
                     String var10001 = "doReadSession {} {}";	
                     Object[] var10002 = new Object[3];	
                     boolean var10004 = true;	
                     var10002[0] = sessionId;	
                     var10002[1] = a != null ? a.getRequestURI() : "";	
                     var10002[2] = var10;	
                     var10000.error(var10001, var10002);	
                  }	
               } catch (Throwable var11) {	
                  JedisUtils.returnResource(a);	
                  throw var11;	
               }	
	
               var12 = a;	
               JedisUtils.returnResource(a);	
               break label100;	
            }	
	
            var12 = a;	
            JedisUtils.returnResource(a);	
         }	
	
         if (var12 != null && a != null) {	
            a.setAttribute((new StringBuilder()).insert(0, "session_").append(sessionId).toString(), a);	
         }	
	
         return a;	
      }	
   }	
	
   public Collection getActiveSessions() {	
      Map a = Maps.newHashMap();	
      Jedis a = null;	
	
      Map var10000;	
      label136: {	
         try {	
            try {	
               a = JedisUtils.getResource();	
               Iterator var4;	
               Iterator var17 = var4 = a.hgetAll(this.sessionKeyPrefix + "all").entrySet().iterator();	
	
               while(true) {	
                  while(var17.hasNext()) {	
                     Entry a;	
                     String var10001;	
                     String[] var10002;	
                     boolean var10004;	
                     if (!StringUtils.isBlank((CharSequence)(a = (Entry)var4.next()).getKey()) && !StringUtils.isBlank((CharSequence)a.getValue())) {	
                        SimpleSession a;	
                        try {	
                           a = this.jsonToSession((String)a.getKey(), (String)a.getValue());	
                        } catch (Exception var14) {	
                           var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                           var10002 = new String[1];	
                           var10004 = true;	
                           var10002[0] = (String)a.getKey();	
                           a.hdel(var10001, var10002);	
                           a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                           var17 = var4;	
                           continue;	
                        }	
	
                        SimpleSession var18 = a;	
	
                        try {	
                           var18.validate();	
                        } catch (ExpiredSessionException var13) {	
                           var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                           var10002 = new String[1];	
                           var10004 = true;	
                           var10002[0] = (String)a.getKey();	
                           a.hdel(var10001, var10002);	
                           a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                           var17 = var4;	
                           continue;	
                        }	
	
                        ExpiredSessionException a = a.getId().toString();	
                        var17 = var4;	
                        a.put(a, a);	
                     } else {	
                        var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                        var10002 = new String[1];	
                        var10004 = true;	
                        var10002[0] = (String)a.getKey();	
                        a.hdel(var10001, var10002);	
                        a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                        var17 = var4;	
                     }	
                  }	
	
                  this.logger.info("getActiveSessions size: {} ", a.size());	
                  break label136;	
               }	
            } catch (Exception var15) {	
               this.logger.error("getActiveSessions", var15);	
            }	
         } catch (Throwable var16) {	
            JedisUtils.returnResource(a);	
            throw var16;	
         }	
	
         var10000 = a;	
         JedisUtils.returnResource(a);	
         return var10000.values();	
      }	
	
      var10000 = a;	
      JedisUtils.returnResource(a);	
      return var10000.values();	
   }	
	
   public String getSessionKeyPrefix() {	
      return this.sessionKeyPrefix;	
   }	
	
   public Session readSession(Serializable sessionId) throws UnknownSessionException {	
      return this.doReadSession(sessionId);	
   }	
	
   public void delete(Session session) {	
      if (session != null && session.getId() != null) {	
         if (ObjectUtils.toBoolean(com.jeesite.common.l.i.j.ALLATORIxDEMO().get("fnCluster"))) {	
            Jedis a = null;	
	
            label54: {	
               try {	
                  try {	
                     a = JedisUtils.getResource();	
                     String var10001 = this.sessionKeyPrefix + "all";	
                     String[] var10002 = new String[1];	
                     boolean var10004 = true;	
                     var10002[0] = session.getId().toString();	
                     a.hdel(var10001, var10002);	
                     a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append(session.getId()).toString()));	
                     this.logger.debug("delete {} ", session.getId());	
                     break label54;	
                  } catch (Exception var7) {	
                     this.logger.error("delete {} ", session.getId(), var7);	
                  }	
               } catch (Throwable var8) {	
                  JedisUtils.returnResource(a);	
                  throw var8;	
               }	
	
               JedisUtils.returnResource(a);	
               return;	
            }	
	
            JedisUtils.returnResource(a);	
         }	
      }	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor, String excludeSessionId, String includeSessionId, String includeUserCode) {	
      String a = null;	
      if (Global.isUseCorpModel()) {	
         a = CorpUtils.getCurrentCorpCode();	
      }	
	
      Map a = MapUtils.newHashMap();	
      Jedis a = null;	
	
      HashMap var10000;	
      label286: {	
         try {	
            try {	
               a = JedisUtils.getResource();	
               Iterator var10 = a.hgetAll(this.sessionKeyPrefix + "all").entrySet().iterator();	
	
               while(true) {	
                  label251:	
                  while(true) {	
                     Iterator var30 = var10;	
	
                     while(true) {	
                        while(true) {	
                           SimpleSession a;	
                           while(true) {	
                              Entry a;	
                              String var10001;	
                              String[] var10002;	
                              boolean var10004;	
                              label229:	
                              while(true) {	
                                 while(var30.hasNext()) {	
                                    if (!StringUtils.isBlank((CharSequence)(a = (Entry)var10.next()).getKey()) && !StringUtils.isBlank((CharSequence)a.getValue())) {	
                                       try {	
                                          a = this.jsonToSession((String)a.getKey(), (String)a.getValue());	
                                          break label229;	
                                       } catch (Exception var26) {	
                                          var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                                          var10002 = new String[1];	
                                          var10004 = true;	
                                          var10002[0] = (String)a.getKey();	
                                          a.hdel(var10001, var10002);	
                                          a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                                          var30 = var10;	
                                       }	
                                    } else {	
                                       var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                                       var10002 = new String[1];	
                                       var10004 = true;	
                                       var10002[0] = (String)a.getKey();	
                                       a.hdel(var10001, var10002);	
                                       a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                                       var30 = var10;	
                                    }	
                                 }	
	
                                 this.logger.info("getActiveSessions size: {} ", a.size());	
                                 break label286;	
                              }	
	
                              SimpleSession var31 = a;	
	
                              try {	
                                 var31.validate();	
                                 break;	
                              } catch (ExpiredSessionException var27) {	
                                 var10001 = (new StringBuilder()).insert(0, this.sessionKeyPrefix).append("all").toString();	
                                 var10002 = new String[1];	
                                 var10004 = true;	
                                 var10002[0] = (String)a.getKey();	
                                 a.hdel(var10001, var10002);	
                                 a.del(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append((String)a.getKey()).toString()));	
                                 var30 = var10;	
                              }	
                           }	
	
                           ExpiredSessionException a = a.getId().toString();	
                           if ("1".equals(a)) {	
                              continue label251;	
                           }	
	
                           if (a.equals(excludeSessionId)) {	
                              var30 = var10;	
                           } else if (excludeLeave && DateUtils.pastMinutes(a.getLastAccessTime()) > 3L) {	
                              var30 = var10;	
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
                                 var30 = var10;	
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
                                       if ((a = (Session)a.get((new StringBuilder()).insert(0, a).append(a).toString())) != null) {	
                                          if (a.getLastAccessTime().getTime() > a.getLastAccessTime().getTime()) {	
                                             a.put((new StringBuilder()).insert(0, a).append(a).toString(), a);	
                                          }	
                                       } else {	
                                          a.put((new StringBuilder()).insert(0, a).append(a).toString(), a);	
                                       }	
                                    }	
                                    continue label251;	
                                 }	
	
                                 var30 = var10;	
                              }	
                           }	
                        }	
                     }	
                  }	
               }	
            } catch (Exception var28) {	
               this.logger.error("getActiveSessions", var28);	
            }	
         } catch (Throwable var29) {	
            JedisUtils.returnResource(a);	
            throw var29;	
         }	
	
         var10000 = a;	
         JedisUtils.returnResource(a);	
         return var10000.values();	
      }	
	
      var10000 = a;	
      JedisUtils.returnResource(a);	
      return var10000.values();	
   }	
	
   public Serializable create(Session session) {	
      return this.doCreate(session);	
   }	
	
   protected Serializable doCreate(Session session) {	
      HttpServletRequest a;	
      if ((a = ServletUtils.getRequest()) != null) {	
         if (ServletUtils.isStaticFile(a.getRequestURI())) {	
            return null;	
         }	
	
         if (!ObjectUtils.toBoolean(com.jeesite.common.l.i.j.ALLATORIxDEMO().get("fnCluster"))) {	
            return null;	
         }	
      }	
	
      Serializable a = this.generateSessionId(session);	
      this.assignSessionId(session, a);	
      this.update(session);	
      return a;	
   }	
	
   public void setSessionKeyPrefix(String sessionKeyPrefix) {	
      this.sessionKeyPrefix = sessionKeyPrefix;	
   }	
	
   // $FF: synthetic method	
   private SimpleSession jsonToSession(String sessionKey, String jsonString) {	
      Map a = (Map)JsonMapper.fromJson(jsonString, Map.class);	
      SimpleSession a = new SimpleSession();	
      a.setId(sessionKey);	
      a.setTimeout(ObjectUtils.toLong(a.get("timeout")));	
      a.setStartTimestamp(new Date(ObjectUtils.toLong(a.get("startTimestamp"))));	
      a.setLastAccessTime(new Date(ObjectUtils.toLong(a.get("lastAccessTime"))));	
      if (a.get("realmName") != null && a.get("principal") != null) {	
         LoginInfo a = (LoginInfo)JsonMapper.fromJson((String)a.get("principal"), LoginInfo.class);	
         SimplePrincipalCollection a = new SimplePrincipalCollection(a, (String)a.get("realmName"));	
         a.setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, a);	
      }	
	
      return a;	
   }	
	
   public Collection getActiveSessions(boolean excludeLeave, boolean excludeVisitor) {	
      return this.getActiveSessions(excludeLeave, excludeVisitor, (String)null, (String)null, (String)null);	
   }	
	
   public void update(Session session) throws UnknownSessionException {	
      if (session != null && session.getId() != null) {	
         if (ObjectUtils.toBoolean(com.jeesite.common.l.i.j.ALLATORIxDEMO().get("fnCluster"))) {	
            Jedis a = null;	
	
            label66: {	
               try {	
                  try {	
                     a = JedisUtils.getResource();	
                     Map a;	
                     (a = Maps.newLinkedHashMap()).put("timeout", session.getTimeout());	
                     a.put("startTimestamp", session.getStartTimestamp());	
                     a.put("lastAccessTime", session.getLastAccessTime());	
                     PrincipalCollection a;	
                     if ((a = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null) {	
                        a.put("realmName", a.getRealmNames().iterator().next());	
                        a.put("principal", JsonMapper.toJson(a.getPrimaryPrincipal()));	
                     }	
	
                     String a = JsonMapper.toJson(a);	
                     a.hset(this.sessionKeyPrefix + "all", session.getId().toString(), a);	
                     a.set(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append(session.getId()).toString()), JedisUtils.toBytes(session));	
                     int a = (int)(session.getTimeout() / 1000L);	
                     a.expire(JedisUtils.getBytesKey((new StringBuilder()).insert(0, this.sessionKeyPrefix).append(session.getId()).toString()), a);	
                     this.logger.debug("update {} ", session.getId());	
                     break label66;	
                  } catch (Exception var10) {	
                     this.logger.error("update {} ", session.getId(), var10);	
                  }	
               } catch (Throwable var11) {	
                  JedisUtils.returnResource(a);	
                  throw var11;	
               }	
	
               JedisUtils.returnResource(a);	
               return;	
            }	
	
            JedisUtils.returnResource(a);	
         }	
      }	
   }	
}	
