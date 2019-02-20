/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.core.NamedThreadLocal	
 */	
package com.jeesite.common.utils.excel.fieldtype;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.service.RoleService;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import org.hyperic.sigar.Swap;	
import org.springframework.core.NamedThreadLocal;	
	
public class RoleListType {	
    private static RoleService roleService = SpringUtils.getBean(RoleService.class);	
    private static ThreadLocal<List<Role>> cache = new NamedThreadLocal("RoleListType");	
	
    public static void clearCache() {	
        cache.remove();	
    }	
	
    public static Object getValue(String val) {	
        int n;	
        ArrayList a2 = ListUtils.newArrayList();	
        List<Role> a3 = cache.get();	
        if (a3 == null) {	
            a3 = roleService.findList(new Role());	
            cache.set(a3);	
        }	
        String[] arrstring = StringUtils.split((String)val, (String)",");	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a4 = arrstring[n];	
            for (Role a5 : a3) {	
                if (!StringUtils.trimToEmpty((String)a4).equals(a5.getRoleName())) continue;	
                a2.add(a5);	
            }	
            n3 = ++n;	
        }	
        if (a2.size() > 0) {	
            return a2;	
        }	
        return null;	
    }	
	
    public static String setValue(Object val) {	
        if (val != null) {	
            return ListUtils.extractToString((Collection)((List)val), (String)"roleName", (String)", ");	
        }	
        return "";	
    }	
}	
	
