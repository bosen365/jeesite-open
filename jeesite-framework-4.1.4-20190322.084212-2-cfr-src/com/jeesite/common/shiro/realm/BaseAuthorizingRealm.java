/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.shiro.c.h;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.concurrent.ConcurrentMap;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authc.AuthenticationInfo;	
import org.apache.shiro.authc.AuthenticationToken;	
import org.apache.shiro.authc.IncorrectCredentialsException;	
import org.apache.shiro.authc.SimpleAuthenticationInfo;	
import org.apache.shiro.util.ByteSource;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.ProcFd;	
	
public abstract class BaseAuthorizingRealm	
extends h {	
    public boolean validatePassword(String plainPassword, String password) {	
        return false;	
    }	
	
    /*	
     * Enabled force condition propagation	
     * Lifted jumps to return sites	
     */	
    @Override	
    protected void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo authcInfo) throws AuthenticationException {	
        if (!(authcToken instanceof FormToken) || !(authcInfo instanceof SimpleAuthenticationInfo)) throw new AuthenticationException("msg:不支持的授权令牌类型。");	
        SimpleAuthenticationInfo a = (SimpleAuthenticationInfo)authcInfo;	
        FormToken a2 = (FormToken)authcToken;	
        if (StringUtils.isNotBlank(a2.getSsoToken())) {	
            FormToken formToken = a2;	
            String a3 = UserUtils.getSsoToken(formToken.getUsername());	
            if (!StringUtils.equals(formToken.getSsoToken(), a3)) throw new IncorrectCredentialsException("msg:登录令牌错误，请再试一次。");	
            return;	
        }	
        if (a.getCredentialsSalt() == null) {	
            if (this.validatePassword(new String(a2.getPassword()), ObjectUtils.toString(a.getCredentials()))) return;	
            throw new IncorrectCredentialsException("msg:用户名或密码错误，请重试。");	
        }	
        super.assertCredentialsMatch(a2, a);	
    }	
	
    public String encryptPassword(String plainPassword) {	
        return null;	
    }	
	
    public static boolean isValidCodeLogin(String loginCode, String deviceType, String operation) {	
        int a22;	
        String a;	
        String a3;	
        Long[] a4;	
        ConcurrentMap<String, Long[]> a5 = (ConcurrentMap<String, Long[]>)CacheUtils.get("loginFailedMap");	
        if (a5 == null) {	
            a5 = MapUtils.newConcurrentMap();	
        }	
        if ((a4 = (Long[])a5.get(a3 = loginCode)) == null) {	
            Long[] arrlong = new Long[2];	
            arrlong[0] = 0L;	
            arrlong[1] = 0L;	
            a4 = arrlong;	
        }	
        Long[] arrlong = a4;	
        Long a6 = arrlong[0];	
        Long a7 = arrlong[1];	
        if ("valid".equals(operation)) {	
            if (a7 != 0L) {	
                a22 = Global.getConfigToInteger("sys.login.failedNumAfterLockMinue", "20");	
                if (System.currentTimeMillis() / 60000L - a7 <= (long)a22) {	
                    String[] arrstring = new String[1];	
                    arrstring[0] = String.valueOf(a22);	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.login.failedNumLock", arrstring)).toString());	
                }	
                Long[] arrlong2 = new Long[2];	
                arrlong2[0] = a6;	
                arrlong2[1] = 0L;	
                a5.put(a3, arrlong2);	
            }	
        } else if ("failed".equals(operation)) {	
            Long a22 = a6;	
            Long l2 = a6 = Long.valueOf(a22 + 1L);	
            a22 = Global.getConfigToInteger("sys.login.failedNumAferLockAccount", "200");	
            if (a6 >= (long)a22) {	
                a7 = System.currentTimeMillis() / 60000L;	
            }	
            Long[] arrlong3 = new Long[2];	
            arrlong3[0] = a6;	
            arrlong3[1] = a7;	
            a5.put(a3, arrlong3);	
        } else if ("success".equals(operation)) {	
            a5.remove(a3);	
        }	
        CacheUtils.put("loginFailedMap", a5);	
        a22 = Global.getConfigToInteger("sys.login.failedNumAferValidCode", "100");	
        if (StringUtils.isNotBlank(deviceType) && StringUtils.isNotBlank(a = Global.getConfig(new StringBuilder().insert(0, "sys.login.failedNumAfterValidCode.").append(deviceType).toString()))) {	
            a22 = ObjectUtils.toInteger(a);	
        }	
        return a6 >= (long)a22;	
    }	
}	
	
