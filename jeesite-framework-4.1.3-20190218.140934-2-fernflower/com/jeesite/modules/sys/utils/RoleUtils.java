package com.jeesite.modules.sys.utils;	
	
import com.jeesite.modules.sys.entity.Role;	
import java.util.Iterator;	
	
public class RoleUtils {	
   public static boolean hasUserRole(String userCode, String roleCode) {	
      Iterator var3 = UserUtils.get(userCode).getRoleList().iterator();	
	
      do {	
         if (!var3.hasNext()) {	
            return false;	
         }	
      } while(!((Role)var3.next()).getRoleCode().equals(roleCode));	
	
      return true;	
   }	
	
   public static boolean hasCurrentUserRole(String roleCode) {	
      return UserUtils.getSubject().hasRole(roleCode);	
   }	
}	
