package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(	
   name = "${_prefix}sys_role_menu",	
   alias = "a",	
   columns = {@Column(	
   name = "role_code",	
   attrName = "roleCode",	
   label = "角色编码",	
   isPK = true	
), @Column(	
   name = "menu_code",	
   attrName = "menuCode",	
   label = "菜单编码",	
   isPK = true	
)},	
   orderBy = ""	
)	
public class RoleMenu extends DataEntity {	
   private String menuCode;	
   private String roleCode;	
   private static final long serialVersionUID = 1L;	
	
   public String getMenuCode() {	
      return this.menuCode;	
   }	
	
   public RoleMenu(String id) {	
      super(id);	
   }	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
	
   public RoleMenu() {	
      this((String)null);	
   }	
	
   public void setMenuCode(String menuCode) {	
      this.menuCode = menuCode;	
   }	
}	
