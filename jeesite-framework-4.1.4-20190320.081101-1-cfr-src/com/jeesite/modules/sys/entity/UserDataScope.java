/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataScope;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
	
@Table(name="${_prefix}sys_user_data_scope", alias="a", columns={@Column(name="user_code", attrName="userCode", label="\u7528\u6237\u7f16\u7801", isPK=true), @Column(name="ctrl_type", attrName="ctrlType", label="\u63a7\u5236\u7c7b\u578b", isPK=true), @Column(name="ctrl_data", attrName="ctrlData", label="\u63a7\u5236\u6570\u636e", isPK=true), @Column(name="ctrl_permi", attrName="ctrlPermi", label="\u63a7\u5236\u6743\u9650", isPK=true)}, orderBy="")	
public class UserDataScope	
extends DataScope<UserDataScope> {	
    private static final long serialVersionUID = 1L;	
    private String userCode;	
	
    public void setUserCode(String userCode) {	
        this.userCode = userCode;	
    }	
	
    public String getUserCode() {	
        return this.userCode;	
    }	
}	
	
