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
	
   public String getName() {	
      return this.name;	
   }	
	
   public String toString() {	
      return this.id;	
   }	
	
   public String getParam(String key, String defaultValue) {	
      String a;	
      return StringUtils.isNotBlank(a = this.getParam(key)) ? a : defaultValue;	
   }	
	
   public void setName(String name) {	
      this.name = name;	
   }	
	
   public Map getParams() {	
      return this.params;	
   }	
	
   public int hashCode() {	
      return Objects.hashCode(this.id);	
   }	
	
   public void setId(String id) {	
      this.id = id;	
   }	
	
   public void setParam(String key, String value) {	
      if (this.params == null) {	
         this.params = MapUtils.newHashMap();	
      }	
	
      this.params.put(key, value);	
   }	
	
   public LoginInfo() {	
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
	
   public void setParams(Map params) {	
      this.params = params;	
   }	
	
   public String getParam(String key) {	
      Object a;	
      return this.params != null && (a = this.params.get(key)) != null ? a.toString() : null;	
   }	
	
   public LoginInfo(User user, Map var2) {	
      this.id = user.getUserCode();	
      this.name = user.getUserName();	
      this.params = var2;	
   }	
	
   public String getId() {	
      return this.id;	
   }	
}	
