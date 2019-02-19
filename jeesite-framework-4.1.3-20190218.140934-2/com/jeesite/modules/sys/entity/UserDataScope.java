package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataScope;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(	
   name = "${_prefix}sys_user_data_scope",	
   alias = "a",	
   columns = {@Column(	
   name = "user_code",	
   attrName = "userCode",	
   label = "用户编码",	
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
   orderBy = ""	
)	
public class UserDataScope extends DataScope {	
   private static final long serialVersionUID = 1L;	
   private String userCode;	
	
   public void setUserCode(String userCode) {	
      this.userCode = userCode;	
   }	
	
   public String getUserCode() {	
      return this.userCode;	
   }	
}	
