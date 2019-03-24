/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataScope;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(name="${_prefix}sys_role_data_scope", alias="a", columns={@Column(name="role_code", attrName="roleCode", label="\u63a7\u5236\u89d2\u8272\u7f16\u7801", isPK=true), @Column(name="ctrl_type", attrName="ctrlType", label="\u63a7\u5236\u7c7b\u578b", isPK=true), @Column(name="ctrl_data", attrName="ctrlData", label="\u63a7\u5236\u6570\u636e", isPK=true), @Column(name="ctrl_permi", attrName="ctrlPermi", label="\u63a7\u5236\u6743\u9650", isPK=true)}, orderBy="a.role_code ASC")	
public class RoleDataScope	
extends DataScope<RoleDataScope> {	
    private String roleCode;	
    private static final long serialVersionUID = 1L;	
	
    public void setRoleCode(String roleCode) {	
        this.roleCode = roleCode;	
    }	
	
    public String getRoleCode() {	
        return this.roleCode;	
    }	
}	
	
