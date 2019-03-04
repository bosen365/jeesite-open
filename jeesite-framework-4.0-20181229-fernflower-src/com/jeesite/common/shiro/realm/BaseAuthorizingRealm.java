package com.jeesite.common.shiro.realm;	
	
import com.beust.jcommander.internal.Maps;	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.codec.Sha1Utils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.j;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.shiro.cas.CasCreateUser;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ValidationException;	
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
import org.apache.shiro.cas.CasToken;	
import org.apache.shiro.realm.AuthorizingRealm;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.apache.shiro.util.ByteSource.Util;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.validation.Assertion;	
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;	
import org.jasig.cas.client.validation.TicketValidationException;	
import org.jasig.cas.client.validation.TicketValidator;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
	
public abstract class BaseAuthorizingRealm extends AuthorizingRealm {	
   private CasOutHandler casOutHandler;	
   private String casServerCallbackUrl;	
   public static final int SALT_SIZE = 8;	
   private String casServerUrl;	
   public static final String HASH_ALGORITHM = "SHA-1";	
   private SessionDAO sessionDAO;	
   public static final int HASH_INTERATIONS = 1024;	
   private TicketValidator ticketValidator;	
	
   public void setCasServerUrl(String casServerUrl) {	
      this.casServerUrl = casServerUrl;	
   }	
	
   public BaseAuthorizingRealm() {	
      HashedCredentialsMatcher var10001 = new HashedCredentialsMatcher("SHA-1");	
      var10001.setHashIterations(1024);	
      this.setCredentialsMatcher(var10001);	
   }	
	
   public static boolean validatePassword(String plainPassword, String password) {	
      try {	
         String var2 = EncodeUtils.decodeHtml(plainPassword);	
         byte[] a = EncodeUtils.decodeHex(password.substring(0, 16));	
         byte[] a = Sha1Utils.sha1(var2.getBytes(), a, 1024);	
         return password.equals(EncodeUtils.encodeHex(a) + EncodeUtils.encodeHex(a));	
      } catch (Exception var5) {	
         return false;	
      }	
   }	
	
   protected final boolean isPermitted(Permission permission, AuthorizationInfo info) {	
      try {	
         String a;	
         if ((a = StringUtils.substringBefore(permission.toString(), ":")) != null) {	
            String a = (String)j.ALLATORIxDEMO().get("modules");	
            if (StringUtils.contains(a, "," + a + ",") && !StringUtils.contains((String)j.ALLATORIxDEMO().get("penModules"), (new StringBuilder()).insert(0, ",").append(a).append(",").toString())) {	
               throw new RuntimeException((new StringBuilder()).insert(0, "[").append(a).append("]模块没有授权！").toString());	
            }	
         }	
      } catch (Exception var5) {	
         throw new UnauthorizedException((new StringBuilder()).insert(0, "msg:").append(var5.getMessage()).toString());	
      }	
	
      return super.isPermitted(permission, info);	
   }	
	
   public static String encryptPassword(String plainPassword) {	
      String a = EncodeUtils.decodeHtml(plainPassword);	
      byte[] a = Sha1Utils.genSalt(8);	
      byte[] a = Sha1Utils.sha1(a.getBytes(), a, 1024);	
      return (new StringBuilder()).insert(0, EncodeUtils.encodeHex(a)).append(EncodeUtils.encodeHex(a)).toString();	
   }	
	
   public final boolean supports(AuthenticationToken token) {	
      if (token != null && FormToken.class.isAssignableFrom(token.getClass())) {	
         this.setAuthenticationTokenClass(FormToken.class);	
         return true;	
      } else if (token != null && CasToken.class.isAssignableFrom(token.getClass())) {	
         this.setAuthenticationTokenClass(CasToken.class);	
         return true;	
      } else {	
         return false;	
      }	
   }	
	
   public void setCasServerCallbackUrl(String casServerCallbackUrl) {	
      this.casServerCallbackUrl = casServerCallbackUrl;	
   }	
	
   protected final AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {	
      HttpServletRequest a = ServletUtils.getRequest();	
      if (this.casOutHandler.isLogoutRequest(a)) {	
         String a = CommonUtils.safeGetParameter(a, "logoutRequest");	
         this.onLogoutSuccess(this.casOutHandler.destroySession(a, a), a);	
         return null;	
      } else if (authcToken == null) {	
         return null;	
      } else {	
         FormToken a = null;	
         Map a = null;	
         FormToken var10000;	
         StringBuilder var10002;	
         String var10003;	
         String[] var10004;	
         boolean var10006;	
         if (authcToken instanceof FormToken) {	
            var10000 = a = (FormToken)authcToken;	
         } else {	
            if (!(authcToken instanceof CasToken) || !ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnCas"))) {	
               var10002 = (new StringBuilder()).insert(0, "msg:");	
               var10003 = "sys.login.typeUnknwn";	
               var10004 = new String[0];	
               var10006 = true;	
               throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
            }	
	
            CasToken a;	
            String a = (String)(a = (CasToken)authcToken).getCredentials();	
            if (this.ticketValidator == null) {	
               this.ticketValidator = new Cas20ServiceTicketValidator(this.casServerUrl);	
               ((Cas20ServiceTicketValidator)this.ticketValidator).setEncoding("UTF-8");	
            }	
	
            Assertion a = null;	
	
            try {	
               a = this.ticketValidator.validate(a, this.casServerCallbackUrl);	
            } catch (TicketValidationException var12) {	
               return null;	
            }	
	
            TicketValidationException a = a.getPrincipal();	
            a.setUserId(a.getName());	
            a = a.getAttributes();	
            (a = new FormToken()).setUsername(a.getName());	
            Map a;	
            (a = Maps.newHashMap()).put("ticket", a);	
            var10000 = a;	
            a.setParams(a);	
         }	
	
         if (StringUtils.isBlank(var10000.getUsername())) {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "sys.lgin.accuntIsBlank";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         } else {	
            if (StringUtils.isBlank(a.getSsoToken()) && !(authcToken instanceof CasToken)) {	
               String a = (String)((String)(a.getParams() == null ? null : a.getParams().get("deviceType")));	
               if (isValidCodeLogin(a.getUsername(), a, "valid")) {	
                  Session a;	
                  String a = (String)(a = UserUtils.getSession()).getAttribute("validCode");	
                  if (a.getCaptcha() == null || !a.getCaptcha().toUpperCase().equals(a)) {	
                     var10002 = (new StringBuilder()).insert(0, "msg:");	
                     var10003 = "sys.lgin.validCodeError";	
                     var10004 = new String[0];	
                     var10006 = true;	
                     throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
                  }	
	
                  a.removeAttribute("validCode");	
               }	
            }	
	
            if (!j.ALLATORIxDEMO(a)) {	
               throw new AuthenticationException("msg:登录失败，请联系管理员获取许可！");	
            } else {	
               User a;	
               if ((a = UserUtils.getByLoginCode(a.getUsername())) != null) {	
                  return this.newAuthenticationInfo(a, a);	
               } else {	
                  if (authcToken instanceof CasToken) {	
                     if (!ObjectUtils.toBoolean(a.get("isAllowClientCreateUser"))) {	
                        throw new AuthenticationException((new StringBuilder()).insert(0, "msg:用户 “").append(a.getUsername()).append("” 在本系统中不存在, 请联系管理员.").toString());	
                     }	
	
                     a = new User(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("userCode"))));	
                     a.setLoginCode(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("loginCde"))));	
                     a.setPassword(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("password"))));	
                     a.setUserName(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("userName"))));	
                     a.setEmail(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("email"))));	
                     a.setMobile(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("mbile"))));	
                     a.setPhone(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("phone"))));	
                     a.setUserType(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("userType"))));	
                     a.setRefCode(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("refCode"))));	
                     a.setRefName(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("refName"))));	
                     a.setMgrType(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("mgrType"))));	
                     a.setStatus(EncodeUtils.decodeUrl(ObjectUtils.toString(a.get("status"))));	
                     if ("employee".equals(a.getUserType())) {	
                        try {	
                           this.casCreateEmpUser(a, a);	
                        } catch (ValidationException var11) {	
                           throw new AuthenticationException((new StringBuilder()).insert(0, "msg:").append(var11.getMessage()).toString());	
                        }	
	
                        if ((a = UserUtils.getByLoginCode(a.getUsername())) != null) {	
                           return this.newAuthenticationInfo(a, a);	
                        }	
                     } else {	
                        try {	
                           CasCreateUser a;	
                           if ((a = (CasCreateUser)SpringUtils.getBean(CasCreateUser.class)) != null) {	
                              a.createUser(a, a);	
                           }	
                        } catch (NoSuchBeanDefinitionException var10) {	
                           throw new AuthenticationException((new StringBuilder()).insert(0, "msg:用户 “").append(a.getUsername()).append("”, 类型 “").append(a.getUserType()).append("” 在本系统中不存在, 请联系管理员.").toString());	
                        }	
                     }	
                  }	
	
                  return null;	
               }	
            }	
         }	
      }	
   }	
	
   public void onLoginSuccess(PrincipalCollection principals) {	
   }	
	
   public static boolean isValidCodeLogin(String loginCode, String deviceType, String operation) {	
      Map a;	
      if ((a = (Map)CacheUtils.get("loginFailedMap")) == null) {	
         a = Maps.newHashMap();	
         CacheUtils.put("lginFailedMap", a);	
      }	
	
      Long[] a;	
      if ((a = (Long[])a.get(loginCode)) == null) {	
         Long[] var10000 = new Long[2];	
         boolean var10002 = true;	
         var10000[0] = 0L;	
         var10000[1] = 0L;	
         a = var10000;	
      }	
	
      Long a = a[0];	
      Long a = a[1];	
      int a;	
      Long[] var10;	
      boolean var10004;	
      if ("valid".equals(operation)) {	
         if (a != 0L) {	
            a = ObjectUtils.toInteger(Global.getConfig("sys.lgin.failedNumAfterLckMinute", "20"));	
            if (System.currentTimeMillis() / 60000L - a <= (long)a) {	
               StringBuilder var11 = (new StringBuilder()).insert(0, "msg:");	
               String var10003 = "sys.login.failedNumLock";	
               String[] var12 = new String[1];	
               boolean var10006 = true;	
               var12[0] = String.valueOf(a);	
               throw new AuthenticationException(var11.append(Global.getText(var10003, var12)).toString());	
            }	
	
            var10 = new Long[2];	
            var10004 = true;	
            var10[0] = a;	
            var10[1] = 0L;	
            a.put(loginCode, var10);	
         }	
      } else if ("failed".equals(operation)) {	
         a = a + 1L;	
         a = ObjectUtils.toInteger(Global.getConfig("sys.login.failedNumAfterLockAccoun", "200"));	
         if (a >= (long)a) {	
            a = System.currentTimeMillis() / 60000L;	
         }	
	
         var10 = new Long[2];	
         var10004 = true;	
         var10[0] = a;	
         var10[1] = a;	
         a.put(loginCode, var10);	
      } else if ("success".equals(operation)) {	
         a.remove(loginCode);	
      }	
	
      a = ObjectUtils.toInteger(Global.getConfig("sys.login.failedNumAfterValidCde", "100"));	
      String a;	
      if (StringUtils.isNotBlank(deviceType) && StringUtils.isNotBlank(a = Global.getConfig((new StringBuilder()).insert(0, "sys.lgin.failedNumAfterValidCode.").append(deviceType).toString()))) {	
         a = ObjectUtils.toInteger(a);	
      }	
	
      return a >= (long)a;	
   }	
	
   protected final AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {	
      LoginInfo a;	
      String a;	
      if (StringUtils.isNotBlank(a = (a = (LoginInfo)this.getAvailablePrincipal(principals)).getParam("deviceType"))) {	
         long a = ObjectUtils.toLong(Global.getProperty("sessin.sessinTimeut"));	
         long a = ObjectUtils.toLong(Global.getProperty((new StringBuilder()).insert(0, "session.").append(a).append("SessinTimeut").toString()));	
         UserUtils.getSession().setTimeout(a > 0L ? a : a);	
      }	
	
      long a = ServletUtils.getRequest();	
      String a = a.getParam("ticket");	
      this.casOutHandler.recordSession(a, a);	
      if (!ObjectUtils.toBoolean(Global.getConfig("shiro.isAllowMuliAddrLogin", "true"))) {	
         String a = UserUtils.getSession().getId().toString();	
         Collection a;	
         if ((a = this.sessionDAO.getActiveSessions(false, true, a, (String)null, a.getId())).size() > 0) {	
            if (!UserUtils.getSubject().isAuthenticated()) {	
               UserUtils.getSubject().logout();	
               StringBuilder var10002 = (new StringBuilder()).insert(0, "msg:");	
               String var10003 = "shiro.muliAddrMessage";	
               String[] var10004 = new String[0];	
               boolean var10006 = true;	
               throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
            }	
	
            Iterator var8 = a.iterator();	
	
            while(var8.hasNext()) {	
               Session a;	
               PrincipalCollection a;	
               LoginInfo a = (a = (PrincipalCollection)(a = (Session)var8.next()).getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null ? (LoginInfo)a.getPrimaryPrincipal() : null;	
               if (a != null && StringUtils.equals(a.getParam("deviceType"), a.getParam("deviceType"))) {	
                  this.sessionDAO.delete(a);	
               }	
            }	
         }	
      }	
	
      User a;	
      if ((a = UserUtils.get(a.getId())) == null) {	
         return null;	
      } else {	
         Session a = UserUtils.getSession();	
         a.setAttribute("userCde", a.getUserCode());	
         a.setAttribute("userName", a.getUserName());	
         a.setAttribute("userType", a.getUserType());	
         SimpleAuthorizationInfo a = new SimpleAuthorizationInfo();	
         Iterator var21 = UserUtils.getMenuList().iterator();	
	
         while(true) {	
            Menu a;	
            do {	
               if (!var21.hasNext()) {	
                  a.addStringPermission("user");	
                  Iterator var24 = var21 = a.getRoleList().iterator();	
	
                  while(var24.hasNext()) {	
                     Role a = (Role)var21.next();	
                     var24 = var21;	
                     a.addRole(a.getRoleCode());	
                  }	
	
                  return a;	
               }	
            } while(!StringUtils.isNotBlank((a = (Menu)var21.next()).getPermission()));	
	
            String[] var12;	
            int var13 = (var12 = StringUtils.split(a.getPermission(), ",")).length;	
	
            int var14;	
            for(int var10000 = var14 = 0; var10000 < var13; var10000 = var14) {	
               String a = var12[var14];	
               ++var14;	
               a.addStringPermission(a);	
            }	
         }	
      }	
   }	
	
   protected abstract void casCreateEmpUser(User var1, Map var2);	
	
   protected final void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) throws AuthenticationException {	
      if (authcToken instanceof FormToken) {	
         FormToken a;	
         if (StringUtils.isNotBlank((a = (FormToken)authcToken).getSsoToken())) {	
            String a = UserUtils.getSsoToken(a.getUsername());	
            if (!StringUtils.equals(a.getSsoToken(), a)) {	
               throw new IncorrectCredentialsException("msg:授权令牌错误，请再试一次或联系管理员。");	
            }	
         } else {	
            super.assertCredentialsMatch(a, info);	
         }	
      } else if (!(authcToken instanceof CasToken)) {	
         throw new IncorrectCredentialsException((new StringBuilder()).insert(0, "msg:不支持的授权令牌类型：").append(authcToken).toString());	
      }	
   }	
	
   // $FF: synthetic method	
   private final SimpleAuthenticationInfo newAuthenticationInfo(User user, FormToken token) {	
      if (Global.isUseCorpModel()) {	
         token.getParams().put("corpCode", user.getCorpCode_());	
      }	
	
      if (StringUtils.equals((CharSequence)j.ALLATORIxDEMO().get("type"), "0") && !user.isAdmin() && this.sessionDAO.getActiveSessions(true, true).size() >= 10) {	
         throw new AuthenticationException("msg:登录失败，您的当前登录人数已超过限制，请联系管理员！");	
      } else if (!"0".equals(user.getStatus())) {	
         StringBuilder var10002;	
         String var10003;	
         String[] var10004;	
         boolean var10006;	
         if ("2".equals(user.getStatus())) {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "shiro.accounDisabled";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         } else if ("3".equals(user.getStatus())) {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "shiro.accountFreezed";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         } else if ("4".equals(user.getStatus())) {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "shiro.accountAudied";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         } else {	
            var10002 = (new StringBuilder()).insert(0, "msg:");	
            var10003 = "shiro.accountInvalid";	
            var10004 = new String[0];	
            var10006 = true;	
            throw new AuthenticationException(var10002.append(Global.getText(var10003, var10004)).toString());	
         }	
      } else {	
         byte[] a = EncodeUtils.decodeHex(user.getPassword().substring(0, 16));	
         return new SimpleAuthenticationInfo(new LoginInfo(user, token.getParams()), user.getPassword().substring(16), Util.bytes(a), this.getName());	
      }	
   }	
	
   protected final AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {	
      if (principals == null) {	
         return null;	
      } else {	
         AuthorizationInfo a = null;	
         if ((a = (AuthorizationInfo)UserUtils.getCache("authInfo")) == null && (a = this.doGetAuthorizationInfo(principals)) != null) {	
            UserUtils.putCache("authInfo", a);	
         }	
	
         return a;	
      }	
   }	
	
   public void setSessionDAO(SessionDAO sessionDAO) {	
      this.sessionDAO = sessionDAO;	
   }	
	
   public void onLogoutSuccess(User logoutUser, HttpServletRequest request) {	
   }	
	
   public void setCasOutHandler(CasOutHandler casOutHandler) {	
      this.casOutHandler = casOutHandler;	
   }	
}	
