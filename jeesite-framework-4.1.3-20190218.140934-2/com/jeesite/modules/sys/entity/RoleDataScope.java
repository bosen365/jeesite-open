package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataScope;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(	
   name = "${_prefix}sys_role_data_scope",	
   alias = "a",	
   columns = {@Column(	
   name = "role_code",	
   attrName = "roleCode",	
   label = "控制角色编码",	
   isPK = true	
), @Column(	
   name = "ctrl_type",	
   attrName = "ctrlType",	
   label = "控制类型",	
   isPK = true	
), @Column(	
   name = "ctrl_data",	
   attrName = "ctrlData",	
   label = "控制数据",	
   isPK = true	
), @Column(	
   name = "ctrl_permi",	
   attrName = "ctrlPermi",	
   label = "控制权限",	
   isPK = true	
)},	
   orderBy = "a.role_code ASC"	
)	
public class RoleDataScope extends DataScope {	
   private String roleCode;	
   private static final long serialVersionUID = 1L;	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
}	
