/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
	
public class RoleUtils {	
    public static boolean hasCurrentUserRole(String roleCode) {	
        return UserUtils.getSubject().hasRole(roleCode);	
    }	
	
    public static boolean hasUserRole(String userCode, String roleCode) {	
        Iterator<Role> iterator = UserUtils.get(userCode).getRoleList().iterator();	
        while (iterator.hasNext()) {	
            if (!iterator.next().getRoleCode().equals(roleCode)) continue;	
            return true;	
        }	
        return false;	
    }	
}	
	
