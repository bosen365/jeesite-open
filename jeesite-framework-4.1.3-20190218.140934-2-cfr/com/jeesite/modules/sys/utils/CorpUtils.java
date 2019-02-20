/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.apache.shiro.session.Session	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.j.H;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.session.Session;	
	
public class CorpUtils {	
    public static final String DEFAULT_CORP_CODE = "0";	
    private static final String CORP_CACHE = "corpCache";	
    public static final String DEFAULT_CORP_NAME = "JeeSite";	
	
    public static <V> V getCache(String key, V defaultValue) {	
        V a2 = CorpUtils.getCache(key);	
        if (a2 != null) {	
            return a2;	
        }	
        return defaultValue;	
    }	
	
    public static String getCurrentCorpName() {	
        String a2 = DEFAULT_CORP_NAME;	
        if (Global.isUseCorpModel().booleanValue()) {	
            Session a3;	
            HttpServletRequest a4 = ServletUtils.getRequest();	
            if (StringUtils.isBlank((CharSequence)(a4 != null ? (a2 = (String)a4.getAttribute("corpName__")) : (a2 = ""))) && (a3 = UserUtils.getSubject().getSession(false)) != null) {	
                a2 = ObjectUtils.toString((Object)a3.getAttribute((Object)"corpName"));	
            }	
            if (StringUtils.isBlank((CharSequence)a2)) {	
                a2 = DEFAULT_CORP_NAME;	
            }	
            if (a4 != null) {	
                a4.setAttribute("corpName__", (Object)a2);	
            }	
        }	
        return ObjectUtils.toStringIgnoreNull((Object)a2);	
    }	
	
    public static void removeCache(String key) {	
        CacheUtils.removeByKeyPrefix(CORP_CACHE, new StringBuilder().insert(0, key).append("__").toString());	
    }	
	
    public static <V> V getCache(String key) {	
        return (V)CacheUtils.get(CORP_CACHE, new StringBuilder().insert(0, key).append("__").append(CorpUtils.getCurrentCorpCode()).toString());	
    }	
	
    public static <V> void putCache(String key, V value) {	
        CacheUtils.put(CORP_CACHE, new StringBuilder().insert(0, key).append("__").append(CorpUtils.getCurrentCorpCode()).toString(), value);	
    }	
	
    public static String getCurrentCorpCode() {	
        String a2 = DEFAULT_CORP_CODE;	
        if (Global.isUseCorpModel().booleanValue() && ObjectUtils.toBoolean(H.ALLATORIxDEMO().get("fnSaas")).booleanValue()) {	
            Session a3;	
            HttpServletRequest a4 = ServletUtils.getRequest();	
            if (StringUtils.isBlank((CharSequence)(a4 != null ? (a2 = (String)a4.getAttribute("corpCode__")) : (a2 = ""))) && (a3 = UserUtils.getSubject().getSession(false)) != null) {	
                a2 = ObjectUtils.toString((Object)a3.getAttribute((Object)"corpCode"));	
            }	
            if (StringUtils.isBlank((CharSequence)a2)) {	
                a2 = DEFAULT_CORP_CODE;	
            }	
            if (a4 != null) {	
                a4.setAttribute("corpCode__", (Object)a2);	
            }	
        }	
        return ObjectUtils.toStringIgnoreNull((Object)a2);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 4 ^ 4 << 1;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ 2 << 1;	
        2 << 3;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
}	
	
