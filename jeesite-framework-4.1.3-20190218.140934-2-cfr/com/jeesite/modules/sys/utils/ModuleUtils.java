/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.utils.H;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
	
public class ModuleUtils {	
    public static final String CACHE_MODULE_MAP = "moduleMap";	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ (2 << 2 ^ 1);	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 4 ^ (3 << 2 ^ 1);	
        2 << 3 ^ 4;	
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
	
    public static synchronized Module getModule(String moduleCode) {	
        Module a2 = ModuleUtils.getModuleList().get(moduleCode);	
        if (a2 == null) {	
            a2 = new Module();	
        }	
        return a2;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static synchronized Map<String, Module> getModuleList() {	
        a = (Map)CacheUtils.get("moduleMap");	
        if (a != null) return a;	
        a = MapUtils.newHashMap();	
        a = Global.getPropertyToBoolean("menu.updateStatusByModuleStatus", "true");	
        v0 = var2_2 = H.ALLATORIxDEMO().findList(new Module()).iterator();	
        do {	
            if (!v0.hasNext()) {	
                CacheUtils.put("moduleMap", a);	
                return a;	
            }	
            a = var2_2.next();	
            if (!a) ** GOTO lbl18	
            if (!a.getIsLoader().booleanValue()) {	
                H.ALLATORIxDEMO().disableByModuleCodes(a.getModuleCode());	
                v1 = a;	
            } else {	
                if (a.getIsEnable().booleanValue()) {	
                    H.ALLATORIxDEMO().enableByModuleCodes(a.getModuleCode());	
                }	
lbl18: // 4 sources:	
                v1 = a;	
            }	
            v1.put(a.getModuleCode(), a);	
            v0 = var2_2;	
        } while (true);	
    }	
	
    public static void clearCache() {	
        CacheUtils.remove(CACHE_MODULE_MAP);	
    }	
	
    public static synchronized List<String> getEnableModuleCodes() {	
        ArrayList a2 = ListUtils.newArrayList();	
        for (Map.Entry<String, Module> a3 : ModuleUtils.getModuleList().entrySet()) {	
            if (!a3.getValue().getIsEnable().booleanValue()) continue;	
            a2.add(a3.getKey());	
        }	
        return a2;	
    }	
}	
	
