/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.shiro.authc.AuthenticationException	
 *  org.apache.shiro.authc.AuthenticationInfo	
 *  org.apache.shiro.authc.AuthenticationToken	
 *  org.apache.shiro.authc.IncorrectCredentialsException	
 *  org.apache.shiro.authc.SimpleAuthenticationInfo	
 *  org.apache.shiro.util.ByteSource	
 */	
package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.d.g;	
import com.jeesite.common.shiro.x.c;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Map;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authc.AuthenticationInfo;	
import org.apache.shiro.authc.AuthenticationToken;	
import org.apache.shiro.authc.IncorrectCredentialsException;	
import org.apache.shiro.authc.SimpleAuthenticationInfo;	
import org.apache.shiro.util.ByteSource;	
	
public abstract class BaseAuthorizingRealm	
extends c {	
    public static boolean isValidCodeLogin(String loginCode, String deviceType, String operation) {	
        String a;	
        String a2;	
        Long[] a3;	
        int a222;	
        Map a4 = (Map)CacheUtils.get("loginFailedMap");	
        if (a4 == null) {	
            a4 = MapUtils.newConcurrentMap();	
        }	
        if ((a3 = (Long[])a4.get(a2 = loginCode)) == null) {	
            a3 = new Long[]{0L, 0L};	
        }	
        Long[] arrlong = a3;	
        Long a5 = arrlong[0];	
        Long a6 = arrlong[1];	
        if ("valid".equals(operation)) {	
            if (a6 != 0L) {	
                a222 = Global.getConfigToInteger("sys.login.failedNum+fterLockMinute", "20");	
                if (System.currentTimeMillis() / 60000L - a6 <= (long)a222) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.failedNumLock", String.valueOf(a222))).toString());	
                }	
                a4.put(a2, new Long[]{a5, 0L});	
            }	
        } else if ("failed".equals(operation)) {	
            Long a222 = a5;	
            Long l2 = a5 = Long.valueOf(a222 + 1L);	
            a222 = Global.getConfigToInteger("sys.login.failedNumAfterLockAccount", "200");	
            if (a5 >= (long)a222) {	
                a6 = System.currentTimeMillis() / 60000L;	
            }	
            a4.put(a2, new Long[]{a5, a6});	
        } else if ("success".equals(operation)) {	
            a4.remove(a2);	
        }	
        CacheUtils.put("loginFailedMap", a4);	
        a222 = Global.getConfigToInteger("sys.login.failedNumAfterValidCode", "100");	
        if (StringUtils.isNotBlank((CharSequence)deviceType) && StringUtils.isNotBlank((CharSequence)(a = Global.getConfig(new StringBuilder().insert(0, "sys.login.failedNumAfterValidCode.").append(deviceType).toString())))) {	
            a222 = ObjectUtils.toInteger((Object)a);	
        }	
        return a5 >= (long)a222;	
    }	
	
    public String encryptPassword(String plainPassword) {	
        return null;	
    }	
	
    /*	
     * Enabled force condition propagation	
     * Lifted jumps to return sites	
     */	
    protected void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo authcInfo) throws AuthenticationException {	
        if (!(authcToken instanceof FormToken) || !(authcInfo instanceof SimpleAuthenticationInfo)) throw new AuthenticationException("msg:不支持的授权令牌类型。");	
        SimpleAuthenticationInfo a = (SimpleAuthenticationInfo)authcInfo;	
        FormToken a2 = (FormToken)authcToken;	
        if (StringUtils.isNotBlank((CharSequence)a2.getSsoToken())) {	
            FormToken formToken = a2;	
            String a3 = UserUtils.getSsoToken(formToken.getUsername());	
            if (!StringUtils.equals((CharSequence)formToken.getSsoToken(), (CharSequence)a3)) throw new IncorrectCredentialsException("msg:登录令牌错误，请再试一次。");	
            return;	
        }	
        if (a.getCredentialsSalt() == null) {	
            if (this.validatePassword(new String(a2.getPassword()), ObjectUtils.toString((Object)a.getCredentials()))) return;	
            throw new IncorrectCredentialsException("msg:用户名或密码错误，请重试。");	
        }	
        super.assertCredentialsMatch((AuthenticationToken)a2, (AuthenticationInfo)a);	
    }	
	
    public boolean validatePassword(String plainPassword, String password) {	
        return false;	
    }	
}	
	
