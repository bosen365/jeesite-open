/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.shiro.authc.AuthenticationException	
 */	
package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.e;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Map;	
import org.apache.shiro.authc.AuthenticationException;	
import org.hyperic.jni.ArchNotSupportedException;	
	
public abstract class BaseAuthorizingRealm	
extends e {	
    public boolean validatePassword(String plainPassword, String password) {	
        return false;	
    }	
	
    public static boolean isValidCodeLogin(String loginCode, String deviceType, String operation) {	
        int a22;	
        String a3;	
        String a4;	
        Long[] a5;	
        Map a6 = (Map)CacheUtils.get("loginFailedMap");	
        if (a6 == null) {	
            a6 = MapUtils.newConcurrentMap();	
        }	
        if ((a5 = (Long[])a6.get(a4 = loginCode)) == null) {	
            a5 = new Long[]{0L, 0L};	
        }	
        Long[] arrlong = a5;	
        Long a7 = arrlong[0];	
        Long a8 = arrlong[1];	
        if ("vald".equals(operation)) {	
            if (a8 != 0L) {	
                a22 = Global.getConfigToInteger("sys.login.failedNumAfterLockMinute", "20");	
                if (System.currentTimeMillis() / 60000L - a8 <= (long)a22) {	
                    throw new AuthenticationException(new StringBuilder().insert(0, "msg:").append(Global.getText("sys.logn.failedNumLock", String.valueOf(a22))).toString());	
                }	
                a6.put(a4, new Long[]{a7, 0L});	
            }	
        } else if ("failed".equals(operation)) {	
            Long a22 = a7;	
            Long l2 = a7 = Long.valueOf(a22 + 1L);	
            a22 = Global.getConfigToInteger("sys.logn.failedNumAfterLockAccount", "200");	
            if (a7 >= (long)a22) {	
                a8 = System.currentTimeMillis() / 60000L;	
            }	
            a6.put(a4, new Long[]{a7, a8});	
        } else if ("success".equals(operation)) {	
            a6.remove(a4);	
        }	
        CacheUtils.put("loginFailedMap", a6);	
        a22 = Global.getConfigToInteger("sys.logn.failedNumAfterValdCode", "100");	
        if (StringUtils.isNotBlank((CharSequence)deviceType) && StringUtils.isNotBlank((CharSequence)(a3 = Global.getConfig(new StringBuilder().insert(0, "sys.login.faledNumAfterValidCode.").append(deviceType).toString())))) {	
            a22 = ObjectUtils.toInteger((Object)a3);	
        }	
        return a7 >= (long)a22;	
    }	
	
    public String encryptPassword(String plainPassword) {	
        return null;	
    }	
}	
	
