/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.utils.B;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
	
public class ConfigUtils {	
    public static final String CACHE_CONFIG_MAP = "configMap";	
	
    public static void clearCache() {	
        CacheUtils.remove(CACHE_CONFIG_MAP);	
    }	
	
    public static synchronized Config getConfig(String key) {	
        Config a2;	
        Map a3 = (Map)CacheUtils.get(CACHE_CONFIG_MAP);	
        if (a3 == null) {	
            Iterator<Config> iterator;	
            a3 = MapUtils.newHashMap();	
            Iterator<Config> iterator2 = iterator = B.ALLATORIxDEMO().findList(new Config()).iterator();	
            while (iterator2.hasNext()) {	
                Config a4 = iterator.next();	
                iterator2 = iterator;	
                a3.put(a4.getConfigKey(), a4);	
            }	
            CacheUtils.put(CACHE_CONFIG_MAP, a3);	
        }	
        if ((a2 = (Config)a3.get(key)) == null) {	
            a2 = new Config();	
        }	
        return a2;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 3;	
        int n4 = n2;	
        (3 ^ 5) << 4 ^ 3 << 1;	
        int n5 = (2 ^ 5) << 4 ^ (3 ^ 5) << 1;	
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
	
