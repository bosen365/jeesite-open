package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.User;	
import java.io.Serializable;	
import java.util.Map;	
import java.util.Objects;	
	
public class LoginInfo implements Serializable {	
   private Map params;	
   private String id;	
   private static final long serialVersionUID = 1L;	
   private String name;	
	
   public void setName(String name) {	
      this.name = name;	
   }	
	
   public int hashCode() {	
      return Objects.hashCode(this.id);	
   }	
	
   public String getId() {	
      return this.id;	
   }	
	
   public LoginInfo(User user, Map var2) {	
      this.id = user.getUserCode();	
      this.name = user.getUserName();	
      this.params = var2;	
   }	
	
   public LoginInfo() {	
   }	
	
   public String getParam(String key) {	
      Object a;	
      return this.params != null && (a = this.params.get(key)) != null ? a.toString() : null;	
   }	
	
   public String toString() {	
      return this.id;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 3;	
      int var10001 = (2 ^ 5) << 4 ^ 3 << 2 ^ 1;	
      int var10002 = 5 << 3 ^ 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public boolean equals(Object obj) {	
      if (this == obj) {	
         return true;	
      } else if (obj == null) {	
         return false;	
      } else if (this.getClass() != obj.getClass()) {	
         return false;	
      } else {	
         LoginInfo a = (LoginInfo)obj;	
         if (this.getId() == null) {	
            if (a.getId() != null) {	
               return false;	
            }	
         } else if (!this.getId().equals(a.getId())) {	
            return false;	
         }	
	
         return true;	
      }	
   }	
	
   public void setId(String id) {	
      this.id = id;	
   }	
	
   public String getName() {	
      return this.name;	
   }	
	
   public void setParam(String key, String value) {	
      if (this.params == null) {	
         this.params = MapUtils.newHashMap();	
      }	
	
      this.params.put(key, value);	
   }	
	
   public Map getParams() {	
      return this.params;	
   }	
	
   public void setParams(Map params) {	
      this.params = params;	
   }	
	
   public String getParam(String key, String defaultValue) {	
      String a;	
      return StringUtils.isNotBlank(a = this.getParam(key)) ? a : defaultValue;	
   }	
}	
