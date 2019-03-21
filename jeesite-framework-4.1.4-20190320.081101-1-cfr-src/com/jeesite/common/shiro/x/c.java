/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpSession	
 *  org.apache.shiro.authc.AuthenticationException	
 *  org.apache.shiro.authc.AuthenticationInfo	
 *  org.apache.shiro.authc.AuthenticationToken	
 *  org.apache.shiro.authc.SimpleAuthenticationInfo	
 *  org.apache.shiro.authc.credential.CredentialsMatcher	
 *  org.apache.shiro.authc.credential.HashedCredentialsMatcher	
 *  org.apache.shiro.authz.AuthorizationInfo	
 *  org.apache.shiro.authz.Permission	
 *  org.apache.shiro.authz.SimpleAuthorizationInfo	
 *  org.apache.shiro.authz.UnauthorizedException	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.Subject	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 *  org.apache.shiro.util.ByteSource	
 *  org.apache.shiro.util.ByteSource$Util	
 */	
package com.jeesite.common.shiro.x;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.d.b.D;	
import com.jeesite.common.mybatis.d.b.L;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.shiro.v.C;	
import com.jeesite.common.shiro.x.i;	
import com.jeesite.common.web.d.m;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.lang.management.ManagementFactory;	
import java.util.Collection;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authc.AuthenticationInfo;	
import org.apache.shiro.authc.AuthenticationToken;	
import org.apache.shiro.authc.SimpleAuthenticationInfo;	
import org.apache.shiro.authc.credential.CredentialsMatcher;	
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
import org.hyperic.sigar.cmd.Watch;	
	
public abstract class c	
extends i {	
    protected final boolean isPermitted(Permission permission, AuthorizationInfo info) {	
        try {	
            String a = StringUtils.substringBefore((String)permission.toString(), (String)":");	
            if (a != null) {	
                void a2;	
                String string = (String)com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("modules");	
                if (StringUtils.contains((CharSequence)a2, (CharSequence)("," + a + ",")) && !StringUtils.contains((CharSequence)((String)com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("openModules")), (CharSequence)new StringBuilder().insert(0, ",").append(a).append(",").toString())) {	
                    throw new RuntimeException(new StringBuilder().insert(0, "[").append(a).append("]模块没有授权！").toString());	
                }	
            }	
        }	
        catch (Exception a) {	
            throw new UnauthorizedException(new StringBuilder().insert(0, "msg:").append(a.getMessage()).toString());	
        }	
        return super.isPermitted(permission, info);	
    }	
	
    protected final AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {	
        if (principals == null) {	
            return null;	
        }	
        Session a = UserUtils.getSession();	
        AuthorizationInfo a2 = (AuthorizationInfo)UserUtils.getCache(new StringBuilder().insert(0, "authInfo_").append(a.getId()).toString());	
        if (a2 == null && (a2 = this.doGetAuthorizationInfo(principals)) != null) {	
            UserUtils.putCache(new StringBuilder().insert(0, "authInfo_").append(a.getId()).toString(), a2);	
        }	
        return a2;	
    }	
	
    public void onLogoutSuccess(LoginInfo loginInfo, HttpServletRequest request) {	
    }	
	
    protected final AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {	
        Object a;	
        Object a2;	
        String a3;	
        Object a4;	
        FormToken a5 = this.getFormToken(authcToken);	
        if (a5 == null) {	
            return null;	
        }	
        if (!(authcToken instanceof FormToken) && !ObjectUtils.toBoolean(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("fnCas")).booleanValue()) {	
            return null;	
        }	
        if (StringUtils.isBlank((CharSequence)a5.getUsername())) {	
            throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.accountIsBlank", new String[0])).toString());	
        }	
        if (StringUtils.isBlank((CharSequence)a5.getSsoToken())) {	
            a = (String)(a5.getParams() == null ? null : a5.getParams().get("deviceType"));	
            if (BaseAuthorizingRealm.isValidCodeLogin(a5.getUsername(), (String)a, "valid")) {	
                a4 = a5.getCaptcha();	
                a2 = UserUtils.getSession();	
                a3 = (String)a2.getAttribute((Object)"validCode");	
                if (a4 == null || !((String)a4).equalsIgnoreCase(a3)) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.validCodeError", new String[0])).toString());	
                }	
                a2.removeAttribute((Object)"validCode");	
            }	
        }	
        if (!com.jeesite.common.mybatis.v.m.ALLATORIxDEMO(a = ServletUtils.getRequest())) {	
            throw new AuthenticationException("msg:登录失败，请联系管理员获取许可！");	
        }	
        if (!(this.sessionDAO instanceof C) && !ObjectUtils.toBoolean(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return null;	
        }	
        a4 = this.getUserInfo(a5);	
        if (a4 != null) {	
            if (("0".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) || "9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type"))) && (com.jeesite.common.mybatis.v.m.ALLATORIxDEMO() >= 3 + ("9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) ? 0 : 17) || L.ALLATORIxDEMO || "9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
                throw new AuthenticationException(new StringBuilder().insert(0, "msg:登录失败，当前登录的人数过多，").append("9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) ? "当前版本为开发版，请切换到正式版本再试！" : "请联系管理员！").append("谢谢使用！").toString());	
            }	
            if (!"0".equals(((DataEntity)a4).getStatus())) {	
                if ("2".equals(((DataEntity)a4).getStatus())) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.accountDisabled", new String[0])).toString());	
                }	
                if ("3".equals(((DataEntity)a4).getStatus())) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.accountFreezed", new String[0])).toString());	
                }	
                if ("4".equals(((DataEntity)a4).getStatus())) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.accountAudited", new String[0])).toString());	
                }	
                throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.accountInvalid", new String[0])).toString());	
            }	
            a2 = ((User)a4).getPassword();	
            if (a2 == null) {	
                UserUtils.clearCache((User)a4);	
                return null;	
            }	
            a3 = null;	
            if (this.getCredentialsMatcher() instanceof HashedCredentialsMatcher) {	
                Object object = a2;	
                a3 = ByteSource.Util.bytes((byte[])EncodeUtils.decodeHex((String)((String)object).substring(0, 16)));	
                a2 = ((String)object).substring(16);	
            }	
            return new SimpleAuthenticationInfo((Object)new LoginInfo((User)a4, a5.getParams()), a2, (ByteSource)a3, this.getName());	
        }	
        return null;	
    }	
	
    protected final AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {	
        String a;	
        Collection<Session> a4;	
        Map a3;	
        Iterator<Role> a522;	
        String[] a6;	
        String a2;	
        LoginInfo a7 = (LoginInfo)this.getAvailablePrincipal(principals);	
        User a8 = UserUtils.get(a7.getId());	
        if (a8 == null) {	
            return null;	
        }	
        Subject a9 = UserUtils.getSubject();	
        Session a10 = UserUtils.getSession();	
        String a11 = a7.getParam("deviceType");	
        if (StringUtils.isNotBlank((CharSequence)a11)) {	
            long a12 = ObjectUtils.toLong((Object)Global.getProperty("session.sessionTimeout"));	
            long a13 = ObjectUtils.toLong((Object)Global.getProperty(new StringBuilder().insert(0, "session.").append(a11).append("SessionTimeout").toString()));	
            a10.setTimeout(a13 > 0L ? a13 : a12);	
        }	
        if ((a3 = (Map)CacheUtils.get("onlineTickOutMap")) != null && a3.containsKey(a = new StringBuilder().insert(0, a7.getId()).append("_").append(a7.getParam("deviceType", "PC")).toString())) {	
            CacheUtils.put("onlineTickOutMap", a3);	
            a3.remove(a);	
            if (!a9.isAuthenticated() && a9.isRemembered()) {	
                a9.logout();	
                throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.tickOutMessage", new String[0])).toString());	
            }	
        }	
        if (!Global.getPropertyToBoolean("shiro.isAllowMultiAddrLogin", "true").booleanValue() && (a4 = this.sessionDAO.getActiveSessions(false, true, a2 = ObjectUtils.toString((Object)a10.getId()), null, a7.getId())).size() > 0) {	
            if (!a9.isAuthenticated() && a9.isRemembered()) {	
                a9.logout();	
                throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.multiAddrMessage", new String[0])).toString());	
            }	
            for (Session a522 : a4) {	
                PrincipalCollection a15 = (PrincipalCollection)a522.getAttribute((Object)DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                a6 = a15 != null ? (String[])a15.getPrimaryPrincipal() : null;	
                if (a6 == null) continue;	
                if (Global.getPropertyToBoolean("shiro.isAllowMulti.eviceLogin", "true").booleanValue()) {	
                    if (!StringUtils.equals((CharSequence)a6.getParam("deviceType"), (CharSequence)a7.getParam("deviceType"))) continue;	
                    this.sessionDAO.delete(a522);	
                    continue;	
                }	
                this.sessionDAO.delete(a522);	
            }	
        }	
        if (("0".equals(m.ALLATORIxDEMO().get("type")) || "9".equals(m.ALLATORIxDEMO().get("type"))) && (m.ALLATORIxDEMO() >= 4 + ("9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) ? 0 : 17) || D.ALLATORIxDEMO || "9".equals(com.jeesite.common.mybatis.v.m.ALLATORIxDEMO().get("type")) && ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L)) {	
            return null;	
        }	
        Session session = a10;	
        Session session2 = a10;	
        Session session3 = a10;	
        session3.setAttribute((Object)"userCode", (Object)a8.getUserCode());	
        session3.setAttribute((Object)"userName", (Object)a8.getUserName());	
        session2.setAttribute((Object)"userType", (Object)a8.getUserType());	
        session2.setAttribute((Object)"corpCode", (Object)a8.getCorpCode_());	
        session.setAttribute((Object)"corpName", (Object)a8.getCorpName_());	
        a2 = ObjectUtils.toString((Object)session.getAttribute((Object)"sysCode"));	
        BaseAuthorizingRealm.isValidCodeLogin(a8.getLoginCode(), a11, "success");	
        if (StringUtils.isBlank((CharSequence)a)) {	
            a = a7.getParam("sysCode", "default");	
            a10.setAttribute((Object)"sysCode", (Object)a);	
        }	
        SimpleAuthorizationInfo a22 = new SimpleAuthorizationInfo();	
        List<Menu> a14 = UserUtils.getMenuList();	
        for (Menu a5 : a14) {	
            int n;	
            if (!StringUtils.isNotBlank((CharSequence)a5.getPermission())) continue;	
            a6 = StringUtils.split((String)a5.getPermission(), (String)",");	
            int n2 = a6.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                void a16;	
                String string = a6[n];	
                a22.addStringPermission((String)a16);	
                n3 = ++n;	
            }	
        }	
        a22.addStringPermission("user");	
        Iterator<Role> iterator = a522 = a8.getRoleList().iterator();	
        while (iterator.hasNext()) {	
            Role a5 = a522.next();	
            iterator = a522;	
            a22.addRole(a5.getRoleCode());	
        }	
        return a22;	
    }	
	
    public c() {	
        c c2 = this;	
        c2.setCachingEnabled(false);	
        c2.setAuthenticationTokenClass(FormToken.class);	
    }	
	
    public void onLoginSuccess(LoginInfo loginInfo, HttpServletRequest request) {	
        if (StringUtils.isNotBlank((CharSequence)loginInfo.getId())) {	
            CacheUtils.removeCache(new StringBuilder().insert(0, "userCache_").append(loginInfo.getId()).toString());	
        }	
        request.getSession().setAttribute("__login", (Object)"true");	
    }	
	
    protected FormToken getFormToken(AuthenticationToken authcToken) {	
        if (authcToken instanceof FormToken) {	
            return (FormToken)authcToken;	
        }	
        return null;	
    }	
	
    protected User getUserInfo(FormToken token) {	
        return UserUtils.getByLoginCode(token.getUsername());	
    }	
}	
	
