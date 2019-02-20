/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import com.jeesite.modules.sys.entity.RoleMenu;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserRole;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.FileSystemMap;	
import org.hyperic.sigar.cmd.Watch;	
	
@Table(name="${_prefix}sys_role", alias="a", columns={@Column(includeEntity=BaseEntity.class), @Column(includeEntity=DataEntity.class), @Column(name="role_code", attrName="roleCode", label="\u89d2\u8272\u7f16\u7801", isPK=true), @Column(name="role_name", attrName="roleName", label="\u89d2\u8272\u540d\u79f0"), @Column(name="role_type", attrName="roleType", label="\u89d2\u8272\u5206\u7c7b", comment="\u89d2\u8272\u5206\u7c7b\uff08\u9ad8\u7ba1\u3001\u4e2d\u5c42\u3001\u57fa\u5c42\u3001\u5176\u5b83\uff09"), @Column(name="role_sort", attrName="roleSort", label="\u89d2\u8272\u6392\u5e8f", comment="\u89d2\u8272\u6392\u5e8f\uff08\u5347\u5e8f\uff09"), @Column(name="is_sys", attrName="isSys", label="\u7cfb\u7edf\u5185\u7f6e", comment="\u7cfb\u7edf\u5185\u7f6e\uff081\u662f 0\u5426\uff09"), @Column(name="user_type", attrName="userType", label="\u7528\u6237\u7c7b\u578b", comment="\u7528\u6237\u7c7b\u578b\uff08none\u672a\u8bbe\u7f6e employee\u5458\u5de5 member\u4f1a\u5458\uff09"), @Column(name="data_scope", attrName="dataScope", label="\u6570\u636e\u8303\u56f4", comment="\u6570\u636e\u8303\u56f4\u8bbe\u7f6e\uff080\u672a\u8bbe\u7f6e 1\u5168\u90e8\u6570\u636e 2\u81ea\u5b9a\u4e49\u6570\u636e\uff09")}, extWhereKeys="dsf", orderBy="a.role_sort ASC")	
public class Role	
extends DataEntity<Role> {	
    private String userType;	
    private static final long serialVersionUID = 1L;	
    private String roleCode;	
    public static final String DATA_SCOPE_CUSTOM = "2";	
    private List<UserRole> userRoleList = ListUtils.newArrayList();	
    private String isSys;	
    private List<RoleMenu> roleMenuList = ListUtils.newArrayList();	
    private List<RoleDataScope> roleDataScopeList = ListUtils.newArrayList();	
    private Integer roleSort;	
    public static final String DATA_SCOPE_ALL = "1";	
    private String dataScope;	
    public static final String DATA_SCOPE_NONE = "0";	
    private String userCode;	
    private String roleType;	
    private String roleName;	
    public static final String CORP_ADMIN_ROLE_CODE = Global.getConfig("user.corpAdminRoleCode");	
	
    public String getRoleName_like() {	
        return (String)this.getSqlMap().getWhere().getValue("role_name", QueryType.LIKE);	
    }	
	
    @JsonIgnore	
    public List<RoleDataScope> getRoleDataScopeList() {	
        return this.roleDataScopeList;	
    }	
	
    public String getUserCode() {	
        return this.userCode;	
    }	
	
    public void setUserType(String userType) {	
        this.userType = userType;	
    }	
	
    @Length(min=0, max=1, message="\u7cfb\u7edf\u5185\u7f6e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsSys() {	
        return this.isSys;	
    }	
	
    @Length(min=0, max=16, message="\u7528\u6237\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 16 \u4e2a\u5b57\u7b26")	
    public String getUserType() {	
        return this.userType;	
    }	
	
    public List<UserRole> getUserRoleList() {	
        return this.userRoleList;	
    }	
	
    @Length(min=0, max=100, message="\u89d2\u8272\u5206\u7c7b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getRoleType() {	
        return this.roleType;	
    }	
	
    public void setUserRoleString(String userCodes) {	
        String[] a2 = StringUtils.split((String)userCodes, (String)",");	
        if (a2 != null) {	
            int n;	
            String[] arrstring = a2;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                if (StringUtils.isNotBlank((CharSequence)a3)) {	
                    void a4;	
                    UserRole userRole = new UserRole();	
                    void v1 = a4;	
                    v1.setRoleCode(this.roleCode);	
                    v1.setUserCode(a3);	
                    this.userRoleList.add((UserRole)a4);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
	
    public static boolean isAdmin(String id) {	
        return id != null && CORP_ADMIN_ROLE_CODE.equals(id);	
    }	
	
    public Role() {	
    }	
	
    public void setRoleCode(String roleCode) {	
        this.roleCode = roleCode;	
    }	
	
    public void setUserCode(String userCode) {	
        this.userCode = userCode;	
    }	
	
    public void setRoleType(String roleType) {	
        this.roleType = roleType;	
    }	
	
    public void setDataScope(String dataScope) {	
        this.dataScope = dataScope;	
    }	
	
    public void setRoleSort(Integer roleSort) {	
        this.roleSort = roleSort;	
    }	
	
    public void setRoleDataScopeListJson(String jsonString) {	
        List a2 = (List)JsonMapper.fromJson((String)jsonString, List.class);	
        if (a2 != null) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = a2.iterator();	
            while (iterator2.hasNext()) {	
                void a3;	
                Map a4 = (Map)iterator.next();	
                RoleDataScope roleDataScope = new RoleDataScope();	
                void v1 = a3;	
                v1.setRoleCode(this.roleCode);	
                v1.setCtrlType((String)a4.get("ctrlType"));	
                a3.setCtrlData((String)a4.get("ctrlData"));	
                iterator2 = iterator;	
                void v2 = a3;	
                v2.setCtrlPermi(DATA_SCOPE_ALL);	
                v2.setIsNewRecord(true);	
                this.roleDataScopeList.add((RoleDataScope)a3);	
            }	
        }	
    }	
	
    public void setRoleMenuListJson(String jsonString) {	
        List a2 = (List)JsonMapper.fromJson((String)jsonString, List.class);	
        if (a2 != null) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = a2.iterator();	
            while (iterator2.hasNext()) {	
                void a3;	
                String a4 = (String)iterator.next();	
                RoleMenu roleMenu = new RoleMenu();	
                iterator2 = iterator;	
                void v1 = a3;	
                a3.setRoleCode(this.roleCode);	
                v1.setMenuCode(a4);	
                v1.setIsNewRecord(true);	
                this.roleMenuList.add((RoleMenu)a3);	
            }	
        }	
    }	
	
    public String getRoleCode() {	
        return this.roleCode;	
    }	
	
    public Role(String id) {	
        super(id);	
    }	
	
    public String getRoleCode_like() {	
        return (String)this.getSqlMap().getWhere().getValue("role_code", QueryType.LIKE);	
    }	
	
    @NotBlank(message="\u89d2\u8272\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u89d2\u8272\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getRoleName() {	
        return this.roleName;	
    }	
	
    public Role(User user) {	
        if (user != null) {	
            Role role = this;	
            role.userCode = user.getUserCode();	
            role.corpCode = user.getCorpCode();	
        }	
    }	
	
    public Integer getRoleSort() {	
        return this.roleSort;	
    }	
	
    public void setRoleName_like(String roleName) {	
        this.getSqlMap().getWhere().and("role_name", QueryType.LIKE, roleName);	
    }	
	
    public void setIsSys(String isSys) {	
        this.isSys = isSys;	
    }	
	
    public List<RoleMenu> getRoleMenuList() {	
        return this.roleMenuList;	
    }	
	
    @JsonIgnore	
    public boolean isAdmin() {	
        return Role.isAdmin(this.id);	
    }	
	
    public void setRoleName(String roleName) {	
        this.roleName = roleName;	
    }	
	
    public void setRoleCode_like(String roleCode) {	
        this.getSqlMap().getWhere().and("role_code", QueryType.LIKE, roleCode);	
    }	
	
    @Length(min=0, max=1, message="\u6570\u636e\u8303\u56f4\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getDataScope() {	
        return this.dataScope;	
    }	
}	
	
