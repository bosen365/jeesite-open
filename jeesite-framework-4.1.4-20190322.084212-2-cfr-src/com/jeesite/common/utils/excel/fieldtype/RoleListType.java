/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.utils.excel.fieldtype;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.service.RoleService;	
import java.util.ArrayList;	
import java.util.List;	
import org.hyperic.sigar.cmd.Watch;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.core.NamedThreadLocal;	
	
public class RoleListType {	
    private static RoleService roleService = SpringUtils.getBean(RoleService.class);	
    private static ThreadLocal<List<Role>> cache = new NamedThreadLocal<List<Role>>("RoleListType");	
	
    public static void clearCache() {	
        cache.remove();	
    }	
	
    public static String setValue(Object val) {	
        if (val != null) {	
            return ListUtils.extractToString((List)val, "roleName", ", ");	
        }	
        return "";	
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
	
