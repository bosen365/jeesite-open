package com.jeesite.common.shiro.d;	
	
import com.jeesite.common.shiro.session.SessionDAO;	
import org.apache.shiro.realm.AuthorizingRealm;	
	
public abstract class E extends AuthorizingRealm {	
   protected SessionDAO sessionDAO;	
	
   public void setSessionDAO(SessionDAO sessionDAO) {	
      this.sessionDAO = sessionDAO;	
   }	
}	
atis.j.n.D;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.shiro.e.M;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.lang.management.ManagementFactory;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authc.AuthenticationInfo;	
import org.apache.shiro.authc.AuthenticationToken;	
import org.apache.shiro.authc.IncorrectCredentialsException;	
import org.apache.shiro.authc.SimpleAuthenticationInfo;	
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;	
import org.apache.shiro.authz.AuthorizationInfo;	
import org.apache.shiro.authz.Permission;	
import org.apache.shiro.authz.SimpleAuthorizationInfo;	
import org.apache.shiro.authz.UnauthorizedException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.Subject;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.apache.shiro.util.ByteSource;	
import org.apache.shiro.util.ByteSource.Util;	
import org.hyperic.sigar.pager.PageFetchException;	
	
public abstract class e extends E {	
   public void onLoginSuccess(LoginInfo loginInfo, HttpServletRequest request) {	
      if (StringUtils.isNotBlank(loginInfo.getId())) {	
         CacheUtils.removeCache((new StringBuilder()).insert(0, "userCache_").append(loginInfo.getId()).toString());	
      }	
	
      request.getSession().setAttribute("__login", "true");	
   }	
	
   protected FormToken getFormToken(AuthenticationToken authcToken) {	
      return authcToken instanceof FormToken ? (FormToken)authcToken : null;	
   }	
	
   protected final boolean isPermitted(Permission permission, AuthorizationInfo info) {	
      try {	
         String a;	
         if ((a = StringUtils.substringBefore(permission.toString(), ":")) != null) {	
            String a = (String)F.ALLATORIxDEMO().get("modules");	
            if (StringUtils.contains(a, "," + a + ",") && !StringUtils.contains((String)F.ALLATORIxDEMO().get("openModules"), (new StringBuilder()).insert(0, ",").append(a).append(",").toString())) {	
               throw new RuntimeException((new StringBuilder()).insert(0, "[").append(a).append("]模块没有授权！").toString());	
            }	
         }	
      } catch (Exception var5) {	
         throw new UnauthorizedException((new StringBuilder()).insert(0, "msg:").append(var5.getMessage()).toString());	
      }	
	
      return super.isPermitted(permission, info);	
   }	
	
   public e() {	
      this.setCachingEnabled(false);	
      this.setAuthenticationTokenClass(FormToken.class);	
   }	
	
   protected void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) throws AuthenticationException {	
      if (authcToken instanceof FormToken) {	
         FormToken a;	
         if (StringUtils.isNotBlank((a = (FormToken)authcToken).getSsoToken())) {	
            String a = UserUtils.getSsoToken(a.getUsername());	
            if (!StringUtils.equals(a.getSsoToken(), a)) {	
               throw new IncorrectCredentialsException("msg:登录令牌错误，请再试一次。");	
            }	
         } else {	
            super.assertCredentialsMatch(a, info);	
         }	
      } else {	
         throw new AuthenticationException("msg:不支持的授权令牌类型。");	
      }	
   }	
	
   protected final AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {	
      FormToken a;	
      if ((a = this.getFormToken(authcToken)) == null) {	
         return null;	
      } else if (!(authcToken instanceof FormToken) && !ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCas"))) {	
         return null;	
      } else {	
         StringBuilder var10002;	
         String var10003;	
         String[] var10004;	
         boolean var10006;	
         if (StringUtils.isBlank(a.getUsername())) {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "sys.login.accountIsBlank";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         } else {	
            if (StringUtils.isBlank(a.getSsoToken())) {	
               String a = (String)((String)(a.getParams() == null ? null : a.getParams().get("deviceType")));	
               if (BaseAuthorizingRealm.isValidCodeLogin(a.getUsername(), a, "valid")) {	
                  String a = a.getCaptcha();	
                  Session a;	
                  String a = (String)(a = UserUtils.getSession()).getAttribute("validCode");	
                  if (a == null || !a.equalsIgnoreCase(a)) {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.login.validCodeError";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  }	
	
                  a.removeAttribute("validCode");	
               }	
            }	
	
            if (!F.ALLATORIxDEMO(ServletUtils.getRequest())) {	
               throw new AuthenticationException("msg:登录失败，请联系管理员获取许可！");	
            } else if (!(this.sessionDAO instanceof M) && !ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluser"))) {	
               return null;	
            } else {	
               User a;	
               if ((a = this.getUserInfo(a)) == null) {	
                  return null;	
               } else if (("0".equals(F.ALLATORIxDEMO().get("type")) || "9".equals(F.ALLATORIxDEMO().get("type"))) && (F.ALLATORIxDEMO() >= 3 + ("9".equals(F.ALLATORIxDEMO().get("type")) ? 0 : 7) || m.ALLATORIxDEMO || "9".equals(F.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
                  throw new AuthenticationException((new StringBuilder()).insert(0, "msg:登录失败，当前登录的人数过多，").append("9".equals(F.ALLATORIxDEMO().get("type")) ? "当前版本为开发版，请切换到正式版本再试！" : "请联系管理员！").append("谢谢使用！").toString());	
               } else if (!"0".equals(a.getStatus())) {	
                  if ("2".equals(a.getStatus())) {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.login.accounDisabled";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  } else if ("3".equals(a.getStatus())) {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.login.accountFreezed";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  } else if ("4".equals(a.getStatus())) {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.login.accountAudied";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  } else {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.login.accountInvalid";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  }	
               } else {	
                  String a;	
                  if ((a = a.getPassword()) == null) {	
                     UserUtils.clearCache(a);	
                     return null;	
                  } else {	
                     ByteSource a = null;	
                     if (this.getCredentialsMatcher() instanceof HashedCredentialsMatcher) {	
                        a = Util.bytes(EncodeUtils.decodeHex(a.substring(0, 16)));	
                        a = a.substring(16);	
                     }	
	
                     return new SimpleAuthenticationInfo(new LoginInfo(a, a.getParams()), a, a, this.getName());	
                  }	
               }	
            }	
         }	
      }	
   }	
	
   protected final AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {	
      if (principals == null) {	
         return null;	
      } else {	
         Session a = UserUtils.getSession();	
         AuthorizationInfo a;	
         if ((a = (AuthorizationInfo)UserUtils.getCache((new StringBuilder()).insert(0, "authInfo_").append(a.getId()).toString())) == null && (a = this.doGetAuthorizationInfo(principals)) != null) {	
            UserUtils.putCache((new StringBuilder()).insert(0, "auhInfo_").append(a.getId()).toString(), a);	
         }	
	
         return a;	
      }	
   }	
	
   protected User getUserInfo(FormToken token) {	
      return UserUtils.getByLoginCode(token.getUsername());	
   }	
	
   public void onLogoutSuccess(LoginInfo loginInfo, HttpServletRequest request) {	
   }	
	
   protected final AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {	
      LoginInfo a;	
      User a;	
      if ((a = UserUtils.get((a = (LoginInfo)this.getAvailablePrincipal(principals)).getId())) == null) {	
         return null;	
      } else {	
         Subject a = UserUtils.getSubject();	
         Session a = UserUtils.getSession();	
         String a;	
         if (StringUtils.isNotBlank(a = a.getParam("deviceType"))) {	
            long a = ObjectUtils.toLong(Global.getProperty("session.sessionTimeou"));	
            long a = ObjectUtils.toLong(Global.getProperty((new StringBuilder()).insert(0, "session.").append(a).append("SessionTimeou").toString()));	
            a.setTimeout(a > 0L ? a : a);	
         }	
	
         String a;	
         Map a;	
         StringBuilder var10002;	
         String var10003;	
         String[] var10004;	
         boolean var10006;	
         if ((a = (Map)CacheUtils.get("onlineTickOutMap")) != null) {	
            a = (new StringBuilder()).insert(0, a.getId()).append("_").append(a.getParam("deviceType", "PC")).toString();	
            if (a.containsKey(a)) {	
               a.remove(a);	
               CacheUtils.put("onlineTickOutMap", a);	
               if (!a.isAuthenticated() && a.isRemembered()) {	
                  a.logout();	
                  var10002 = (new StringBuilder()).insert(0, "msg:");	
                  var10003 = "sys.login.tickOutMessage";	
                  var10004 = new String[0];	
                  var10006 = true;	
                  throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
               }	
            }	
         }	
	
         if (!Global.getPropertyToBoolean("shiro.isAllowMuliAddrLogin", "true")) {	
            a = ObjectUtils.toString(a.getId());	
            Collection a;	
            if ((a = this.sessionDAO.getActiveSessions(false, true, a, (String)null, a.getId())).size() > 0) {	
               if (!a.isAuthenticated() && a.isRemembered()) {	
                  a.logout();	
                  var10002 = (new StringBuilder()).insert(0, "msg:");	
                  var10003 = "sys.login.muliAddrMessage";	
                  var10004 = new String[0];	
                  var10006 = true;	
                  throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
               }	
	
               Iterator var10 = a.iterator();	
	
               while(var10.hasNext()) {	
                  Session a;	
                  PrincipalCollection a;	
                  LoginInfo a = (a = (PrincipalCollection)(a = (Session)var10.next()).getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null ? (LoginInfo)a.getPrimaryPrincipal() : null;	
                  if (a != null) {	
                     if (Global.getPropertyToBoolean("shiro.isAllowMultiDeviceLogin", "true")) {	
                        if (StringUtils.equals(a.getParam("deviceType"), a.getParam("deviceType"))) {	
                           this.sessionDAO.delete(a);	
                        }	
                     } else {	
                        this.sessionDAO.delete(a);	
                     }	
                  }	
               }	
            }	
         }	
	
         if (!"0".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("type")) && !"9".equals(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("type")) || com.jeesite.common.web.j.F.ALLATORIxDEMO() < 4 + ("9".equals(F.ALLATORIxDEMO().get("type")) ? 0 : 7) && !D.ALLATORIxDEMO && (!"9".equals(F.ALLATORIxDEMO().get("type")) || ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L <= 1L)) {	
            BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), a, "success");	
            a.setAttribute("userCode", a.getUserCode());	
            a.setAttribute("userName", a.getUserName());	
            a.setAttribute("userType", a.getUserType());	
            a.setAttribute("corpCode", a.getCorpCode_());	
            a.setAttribute("corpName", a.getCorpName_());	
            a = ObjectUtils.toString(a.getAttribute("sysCode"));	
            if (StringUtils.isBlank(a)) {	
               a = a.getParam("sysCode", "default");	
               a.setAttribute("sysCode", a);	
            }	
	
            SimpleAuthorizationInfo a = new SimpleAuthorizationInfo();	
            Iterator var20 = UserUtils.getMenuList().iterator();	
	
            while(true) {	
               Menu a;	
               do {	
                  if (!var20.hasNext()) {	
                     a.addStringPermission("user");	
                     Iterator var24 = var20 = a.getRoleList().iterator();	
	
                     while(var24.hasNext()) {	
                        Role a = (Role)var20.next();	
                        var24 = var20;	
                        a.addRole(a.getRoleCode());	
                     }	
	
                     return a;	
                  }	
               } while(!StringUtils.isNotBlank((a = (Menu)var20.next()).getPermission()));	
	
               String[] var23;	
               int var14 = (var23 = StringUtils.split(a.getPermission(), ",")).length;	
	
               int var15;	
               for(int var10000 = var15 = 0; var10000 < var14; var10000 = var15) {	
                  String a = var23[var15];	
                  ++var15;	
                  a.addStringPermission(a);	
               }	
            }	
         } else {	
            return null;	
         }	
      }	
   }	
}	
