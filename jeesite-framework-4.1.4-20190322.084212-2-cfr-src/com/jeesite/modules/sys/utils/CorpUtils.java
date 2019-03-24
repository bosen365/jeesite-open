/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.l.e;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.session.Session;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class CorpUtils {	
    public static final String DEFAULT_CORP_NAME = "JeeSite";	
    public static final String DEFAULT_CORP_CODE = "0";	
    private static final String CORP_CACHE = "corpCache";	
	
    public static <V> V getCache(String key, V defaultValue) {	
        V a = CorpUtils.getCache(key);	
        if (a != null) {	
            return a;	
        }	
        return defaultValue;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 4 ^ 1;	
        5 << 3 ^ (3 ^ 5);	
        int n4 = n2;	
        int n5 = 2 << 3 ^ (2 ^ 5);	
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
	
    public static void removeCache(String key) {	
        CacheUtils.removeByKeyPrefix(CORP_CACHE, new StringBuilder().insert(0, key).append("__").toString());	
    }	
	
    public static String getCurrentCorpCode() {	
        String a = DEFAULT_CORP_CODE;	
        if (Global.isUseCorpModel().booleanValue() && ObjectUtils.toBoolean(e.ALLATORIxDEMO().get("fnSaas")).booleanValue()) {	
            Session a2;	
            HttpServletRequest a3 = ServletUtils.getRequest();	
            if (StringUtils.isBlank(a3 != null ? (a = (String)a3.getAttribute("corpCode__")) : (a = "")) && (a2 = UserUtils.getSubject().getSession(false)) != null) {	
                a = ObjectUtils.toString(a2.getAttribute("corpCode"));	
            }	
            if (StringUtils.isBlank(a)) {	
                a = DEFAULT_CORP_CODE;	
            }	
            if (a3 != null) {	
                a3.setAttribute("corpCode__", a);	
            }	
        }	
        return ObjectUtils.toStringIgnoreNull(a);	
    }	
	
    public static String getCurrentCorpName() {	
        String a = DEFAULT_CORP_NAME;	
        if (Global.isUseCorpModel().booleanValue()) {	
            Session a2;	
            HttpServletRequest a3 = ServletUtils.getRequest();	
            if (StringUtils.isBlank(a3 != null ? (a = (String)a3.getAttribute("corpName__")) : (a = "")) && (a2 = UserUtils.getSubject().getSession(false)) != null) {	
                a = ObjectUtils.toString(a2.getAttribute("corpName"));	
            }	
            if (StringUtils.isBlank(a)) {	
                a = DEFAULT_CORP_NAME;	
            }	
            if (a3 != null) {	
                a3.setAttribute("corpName__", a);	
            }	
        }	
        return ObjectUtils.toStringIgnoreNull(a);	
    }	
	
    public static <V> void putCache(String key, V value) {	
        CacheUtils.put(CORP_CACHE, new StringBuilder().insert(0, key).append("__").append(CorpUtils.getCurrentCorpCode()).toString(), value);	
    }	
	
    public static <V> V getCache(String key) {	
        return (V)CacheUtils.get(CORP_CACHE, new StringBuilder().insert(0, key).append("__").append(CorpUtils.getCurrentCorpCode()).toString());	
    }	
}	
	
