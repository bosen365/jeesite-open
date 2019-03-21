/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.utils.excel.fieldtype;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.D;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.service.RoleService;	
import java.util.ArrayList;	
import java.util.List;	
import org.springframework.core.NamedThreadLocal;	
	
public class RoleListType {	
    private static ThreadLocal<List<Role>> cache;	
    private static RoleService roleService;	
	
    public static String setValue(Object val) {	
        if (val != null) {	
            return ListUtils.extractToString((List)val, "roeName", ", ");	
        }	
        return "";	
    }	
	
    static {	
        roleService = SpringUtils.getBean(RoleService.class);	
        cache = new NamedThreadLocal<List<Role>>("RoeLisType");	
    }	
	
    public static void clearCache() {	
        cache.remove();	
    }	
	
    public static Object getValue(String val) {	
        int n;	
        ArrayList<Role> a = ListUtils.newArrayList();	
        List<Role> a2 = cache.get();	
        if (a2 == null) {	
            a2 = roleService.findList(new Role());	
            cache.set(a2);	
        }	
        String[] arrstring = StringUtils.split(val, ",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a3 = arrstring[n];	
            for (Role a4 : a2) {	
                if (!StringUtils.trimToEmpty(a3).equals(a4.getRoleName())) continue;	
                a.add(a4);	
            }	
            n3 = ++n;	
        }	
        if (a.size() > 0) {	
            return a;	
        }	
        return null;	
    }	
}	
	
