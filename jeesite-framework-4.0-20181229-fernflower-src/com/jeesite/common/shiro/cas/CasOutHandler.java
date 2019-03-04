package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.l.C;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;	
import org.jasig.cas.client.session.SessionMappingStorage;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.util.XmlUtils;	
	
public final class CasOutHandler {	
   private final Log log = LogFactory.getLog(this.getClass());	
   private String logoutParameterName = "logoutRequest";	
   private String artifactParameterName = "ticket";	
   private SessionMappingStorage sessionMappingStorage = new HashMapBackedSessionMappingStorage();	
	
   // $FF: synthetic method	
   private boolean isMultipartRequest(HttpServletRequest request) {	
      return request.getContentType() != null && request.getContentType().toLowerCase().startsWith(ALLATORIxDEMO("\u00031\u00020\u00074\u000f6\u001a"));	
   }	
	
   public void init() {	
      CommonUtils.assertNotNull(this.artifactParameterName, "artifactParameterName cannot be null.");	
      CommonUtils.assertNotNull(this.logoutParameterName, ALLATORIxDEMO("\u0002+\t+\u001b0>%\u001c%\u0003!\u001a!\u001c\n\u000f)\u000bd\r%\u0000*\u00010N&\u000bd\u00001\u0002(@"));	
      CommonUtils.assertNotNull(this.sessionMappingStorage, "sessionMappingStorage cannote be null.");	
   }	
	
   public void setLogoutParameterName(String name) {	
      this.logoutParameterName = name;	
   }	
	
   public User destroySession(HttpServletRequest request, String logoutRequest) {	
      CasOutHandler var10000;	
      String a;	
      if (logoutRequest == null) {	
         a = CommonUtils.safeGetParameter(request, this.logoutParameterName);	
         var10000 = this;	
      } else {	
         a = logoutRequest;	
         var10000 = this;	
      }	
	
      if (var10000.log.isTraceEnabled()) {	
         this.log.trace((new StringBuilder()).insert(0, ALLATORIxDEMO("\b\u0001#\u00011\u001ad\u001c!\u001f1\u000b7\u001a~d")).append(a).toString());	
      }	
	
      User a = null;	
      String a;	
      HttpSession a;	
      if (CommonUtils.isNotBlank(a = XmlUtils.getTextForElement(a, "SessionIndex")) && (a = this.sessionMappingStorage.removeSessionByMappingId(a)) != null) {	
         String a = a.getId();	
         if (this.log.isDebugEnabled()) {	
            this.log.debug((new StringBuilder()).insert(0, ALLATORIxDEMO("\r\u00002\u000f(\u0007 \u000f0\u0007*\td\u001d!\u001d7\u0007+\u0000d5")).append(a).append("] for token [").append(a).append(ALLATORIxDEMO("3")).toString());	
         }	
	
         try {	
            PrincipalCollection a;	
            LoginInfo a = (a = (PrincipalCollection)a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null ? (LoginInfo)a.getPrimaryPrincipal() : null;	
            if (a != null) {	
               a = UserUtils.get(a.getId());	
            }	
	
            a.invalidate();	
            return a;	
         } catch (IllegalStateException var10) {	
            this.log.debug("Error invalidating session.", var10);	
         }	
      }	
	
      return a;	
   }	
	
   public void setSessionMappingStorage(SessionMappingStorage storage) {	
      this.sessionMappingStorage = storage;	
   }	
	
   public boolean isTokenRequest(HttpServletRequest request) {	
      return CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.artifactParameterName));	
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
         this.log.debug((new StringBuilder()).insert(0, ALLATORIxDEMO("\u0016\u000b'\u00016\n-\u0000#N7\u000b7\u001d-\u0001*N\"\u00016N0\u0001/\u000b*N")).append(a).toString());	
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
	
   public SessionMappingStorage getSessionMappingStorage() {	
      return this.sessionMappingStorage;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 3 ^ 2 ^ 5;	
      int var10001 = (3 ^ 5) << 4 ^ (2 ^ 5) << 1;	
      int var10002 = 4 << 4 ^ 2 << 1;	
      int var10003 = s.length();	
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
	
   public boolean isLogoutRequest(HttpServletRequest request) {	
      return "POST".equals(request.getMethod()) && !this.isMultipartRequest(request) && CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.logoutParameterName));	
   }	
	
   public void setArtifactParameterName(String name) {	
      this.artifactParameterName = name;	
   }	
	
   public void destroySession(HttpServletRequest request) {	
      this.destroySession(request, (String)null);	
   }	
	
   public void recordSession(HttpServletRequest request) {	
      this.recordSession(request, (String)null);	
   }	
}	
