/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.utils.e;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
	
public class ConfigUtils {	
    public static final String CACHE_CONFIG_MAP = "configMap";	
	
    public static synchronized Config getConfig(String key) {	
        Config a;	
        HashMap<String, Config> a2 = (HashMap<String, Config>)CacheUtils.get(CACHE_CONFIG_MAP);	
        if (a2 == null) {	
            Iterator<Config> iterator;	
            a2 = MapUtils.newHashMap();	
            Iterator<Config> iterator2 = iterator = e.ALLATORIxDEMO().findList(new Config()).iterator();	
            while (iterator2.hasNext()) {	
                Config a3 = iterator.next();	
                iterator2 = iterator;	
                a2.put(a3.getConfigKey(), a3);	
            }	
            CacheUtils.put(CACHE_CONFIG_MAP, a2);	
        }	
        if ((a = (Config)a2.get(key)) == null) {	
            a = new Config();	
        }	
        return a;	
    }	
	
    public static void clearCache() {	
        CacheUtils.remove(CACHE_CONFIG_MAP);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 3 ^ (3 ^ 5);	
        int n4 = n2;	
        int n5 = (3 ^ 5) << 4 ^ (3 << 2 ^ 1);	
        5 << 4 ^ (2 ^ 5) << 1;	
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
	
