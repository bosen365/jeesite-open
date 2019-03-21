/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.utils.D;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class ModuleUtils {	
    public static final String CACHE_MODULE_MAP = "moduleMap";	
	
    public static synchronized Module getModule(String moduleCode) {	
        Module a = ModuleUtils.getModuleList().get(moduleCode);	
        if (a == null) {	
            a = new Module();	
        }	
        return a;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static synchronized Map<String, Module> getModuleList() {	
        a = (HashMap<String, Module>)CacheUtils.get("moduleMap");	
        if (a != null) return a;	
        a = MapUtils.newHashMap();	
        a = Global.getPropertyToBoolean("menu.updateStatusByModuleStatus", "true");	
        v0 = var2_2 = D.ALLATORIxDEMO().findList(new Module()).iterator();	
        do {	
            if (!v0.hasNext()) {	
                CacheUtils.put("moduleMap", a);	
                return a;	
            }	
            a = var2_2.next();	
            if (!a) ** GOTO lbl18	
            if (!a.getIsLoader().booleanValue()) {	
                D.ALLATORIxDEMO().disableByModuleCodes(a.getModuleCode());	
                v1 = a;	
            } else {	
                if (a.getIsEnable().booleanValue()) {	
                    D.ALLATORIxDEMO().enableByModuleCodes(a.getModuleCode());	
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
        ArrayList<String> a = ListUtils.newArrayList();	
        for (Map.Entry<String, Module> a2 : ModuleUtils.getModuleList().entrySet()) {	
            if (!a2.getValue().getIsEnable().booleanValue()) continue;	
            a.add(a2.getKey());	
        }	
        return a;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ 3 << 1;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ (3 ^ 5) << 1;	
        4 << 3 ^ 5;	
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
	
