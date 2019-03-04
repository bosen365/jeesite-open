package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(	
   name = "${_prefix}sys_user_role",	
   alias = "a",	
   columns = {@Column(	
   name = "user_code",	
   attrName = "userCode",	
   label = "用户编码",	
   isPK = true	
), @Column(	
   name = "role_code",	
   attrName = "roleCode",	
   label = "角色编码",	
   isPK = true	
)},	
   orderBy = "a.user_code ASC"	
)	
public class UserRole extends DataEntity {	
   private String userCode;	
   private static final long serialVersionUID = 1L;	
   private String roleCode;	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   public UserRole() {	
      this((String)null);	
   }	
	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
	
   public UserRole(String id) {	
      super(id);	
   }	
	
   public String getUserCode() {	
      return this.userCode;	
   }	
	
   public void setUserCode(String userCode) {	
      this.userCode = userCode;	
   }	
}	
