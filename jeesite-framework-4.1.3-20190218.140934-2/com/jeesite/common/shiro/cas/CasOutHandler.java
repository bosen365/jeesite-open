package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.shiro.realm.LoginInfo;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.NfsClientV3;	
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;	
import org.jasig.cas.client.session.SessionMappingStorage;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.util.XmlUtils;	
	
public final class CasOutHandler {	
   private String artifactParameterName = "ticket";	
   private String logoutParameterName = "logoutRequest";	
   private final Log log = LogFactory.getLog(this.getClass());	
   private SessionMappingStorage sessionMappingStorage = new HashMapBackedSessionMappingStorage();	
	
   public SessionMappingStorage getSessionMappingStorage() {	
      return this.sessionMappingStorage;	
   }	
	
   public boolean isLogoutRequest(HttpServletRequest request) {	
      return "POST".equals(request.getMethod()) && !this.isMultipartRequest(request) && CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.logoutParameterName));	
   }	
	
   public void init() {	
      CommonUtils.assertNotNull(this.artifactParameterName, "artifactParameterName cannot be null.");	
      CommonUtils.assertNotNull(this.logoutParameterName, "logoutParameterName cannot be null.");	
      CommonUtils.assertNotNull(this.sessionMappingStorage, "sessionMappingStorage cannote be null.");	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 1 << 3 ^ 2;	
      int var10001 = (2 ^ 5) << 4 ^ 2 << 2 ^ 3;	
      int var10002 = 2 ^ 5;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public boolean isTokenRequest(HttpServletRequest request) {	
      return CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.artifactParameterName));	
   }	
	
   public void recordSession(HttpServletRequest request) {	
      this.recordSession(request, (String)null);	
   }	
	
   // $FF: synthetic method	
   private boolean isMultipartRequest(HttpServletRequest request) {	
      return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart");	
   }	
	
   public LoginInfo destroySession(HttpServletRequest request) {	
      String a = CommonUtils.safeGetParameter(request, this.logoutParameterName);	
      if (this.log.isTraceEnabled()) {	
         this.log.trace((new StringBuilder()).insert(0, "Logout request:\n").append(a).toString());	
      }	
	
      LoginInfo a = null;	
      String a;	
      HttpSession a;	
      if (CommonUtils.isNotBlank(a = XmlUtils.getTextForElement(a, "SessionIndex")) && (a = this.sessionMappingStorage.removeSessionByMappingId(a)) != null) {	
         String a = a.getId();	
         if (this.log.isDebugEnabled()) {	
            this.log.debug((new StringBuilder()).insert(0, "Invalidating session [").append(a).append("] for token [").append(a).append("]").toString());	
         }	
	
         try {	
            PrincipalCollection a;	
            a = (a = (PrincipalCollection)a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null ? (LoginInfo)a.getPrimaryPrincipal() : null;	
            a.invalidate();	
            return a;	
         } catch (IllegalStateException var8) {	
            this.log.debug("Error invalidating session.", var8);	
         }	
      }	
	
      return a;	
   }	
	
   public void setSessionMappingStorage(SessionMappingStorage storage) {	
      this.sessionMappingStorage = storage;	
   }	
	
   public void recordSession(HttpServletRequest request, String ticket) {	
      HttpSession a = request.getSession();	
      CasOutHandler var10000;	
      String a;	
      if (ticket != null) {	
         a = ticket;	
         var10000 = this;	
      } else {	
         a = CommonUtils.safeGetParameter(request, this.artifactParameterName);	
         var10000 = this;	
      }	
	
      if (var10000.log.isDebugEnabled()) {	
         this.log.debug((new StringBuilder()).insert(0, "Recording session for toen ").append(a).toString());	
      }	
	
      label20: {	
         try {	
            this.sessionMappingStorage.removeBySessionById(a.getId());	
         } catch (Exception var6) {	
            var10000 = this;	
            break label20;	
         }	
	
         var10000 = this;	
      }	
	
      var10000.sessionMappingStorage.addSessionById(a, a);	
   }	
	
   public void setLogoutParameterName(String name) {	
      this.logoutParameterName = name;	
   }	
	
   public void setArtifactParameterName(String name) {	
      this.artifactParameterName = name;	
   }	
}	
