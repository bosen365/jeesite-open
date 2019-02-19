package com.jeesite.common.utils.excel.fieldtype;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.service.RoleService;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.Swap;	
import org.springframework.core.NamedThreadLocal;	
	
public class RoleListType {	
   private static RoleService roleService = (RoleService)SpringUtils.getBean(RoleService.class);	
   private static ThreadLocal cache = new NamedThreadLocal("RoleListType");	
	
   public static void clearCache() {	
      cache.remove();	
   }	
	
   public static Object getValue(String val) {	
      List a = ListUtils.newArrayList();	
      List a;	
      if ((a = (List)cache.get()) == null) {	
         a = roleService.findList(new Role());	
         cache.set(a);	
      }	
	
      String[] var3;	
      int var4 = (var3 = StringUtils.split(val, ",")).length;	
	
      int var5;	
      for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
         String a = var3[var5];	
         Iterator var7 = a.iterator();	
	
         while(var7.hasNext()) {	
            Role a = (Role)var7.next();	
            if (StringUtils.trimToEmpty(a).equals(a.getRoleName())) {	
               a.add(a);	
            }	
         }	
	
         ++var5;	
      }	
	
      if (a.size() > 0) {	
         return a;	
      } else {	
         return null;	
      }	
   }	
	
   public static String setValue(Object val) {	
      return val != null ? ListUtils.extractToString((List)val, "roleName", ", ") : "";	
   }	
}	
