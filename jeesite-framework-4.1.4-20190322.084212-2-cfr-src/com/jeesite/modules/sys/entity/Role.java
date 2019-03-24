/*	
 * Decompiled with CFR 0.141.	
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
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.ProcState;	
	
@Table(name="${_prefix}sys_role", alias="a", columns={@Column(includeEntity=BaseEntity.class), @Column(includeEntity=DataEntity.class), @Column(name="role_code", attrName="roleCode", label="\u89d2\u8272\u7f16\u7801", isPK=true), @Column(name="role_name", attrName="roleName", label="\u89d2\u8272\u540d\u79f0"), @Column(name="role_type", attrName="roleType", label="\u89d2\u8272\u5206\u7c7b", comment="\u89d2\u8272\u5206\u7c7b\uff08\u9ad8\u7ba1\u3001\u4e2d\u5c42\u3001\u57fa\u5c42\u3001\u5176\u5b83\uff09"), @Column(name="role_sort", attrName="roleSort", label="\u89d2\u8272\u6392\u5e8f", comment="\u89d2\u8272\u6392\u5e8f\uff08\u5347\u5e8f\uff09"), @Column(name="is_sys", attrName="isSys", label="\u7cfb\u7edf\u5185\u7f6e", comment="\u7cfb\u7edf\u5185\u7f6e\uff081\u662f 0\u5426\uff09"), @Column(name="user_type", attrName="userType", label="\u7528\u6237\u7c7b\u578b", comment="\u7528\u6237\u7c7b\u578b\uff08none\u672a\u8bbe\u7f6e employee\u5458\u5de5 member\u4f1a\u5458\uff09"), @Column(name="data_scope", attrName="dataScope", label="\u6570\u636e\u8303\u56f4", comment="\u6570\u636e\u8303\u56f4\u8bbe\u7f6e\uff080\u672a\u8bbe\u7f6e 1\u5168\u90e8\u6570\u636e 2\u81ea\u5b9a\u4e49\u6570\u636e\uff09")}, extWhereKeys="dsf", orderBy="a.role_sort ASC")	
public class Role	
extends DataEntity<Role> {	
    public static final String DATA_SCOPE_NONE = "0";	
    private static final long serialVersionUID = 1L;	
    private String dataScope;	
    private Integer roleSort;	
    private List<RoleDataScope> roleDataScopeList = ListUtils.newArrayList();	
    private String roleCode;	
    private String userType;	
    public static final String CORP_ADMIN_ROLE_CODE = Global.getConfig("user.corpAdminRoleCode");	
    private String userCode;	
    private List<RoleMenu> roleMenuList = ListUtils.newArrayList();	
    private String roleType;	
    private String roleName;	
    private String isSys;	
    public static final String DATA_SCOPE_CUSTOM = "2";	
    public static final String DATA_SCOPE_ALL = "1";	
    private List<UserRole> userRoleList = ListUtils.newArrayList();	
	
    public String getRoleCode_like() {	
        return (String)this.getSqlMap().getWhere().getValue("role_code", QueryType.LIKE);	
    }	
	
    @Length(min=0, max=16, message="\u7528\u6237\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 16 \u4e2a\u5b57\u7b26")	
    public String getUserType() {	
        return this.userType;	
    }	
	
    public String getRoleCode() {	
        return this.roleCode;	
    }	
	
    @Length(min=0, max=1, message="\u7cfb\u7edf\u5185\u7f6e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsSys() {	
        return this.isSys;	
    }	
	
    public void setRoleName_like(String roleName) {	
        this.getSqlMap().getWhere().and("role_name", QueryType.LIKE, roleName);	
    }	
	
    public static boolean isAdmin(String id) {	
        return id != null && CORP_ADMIN_ROLE_CODE.equals(id);	
    }	
	
    @JsonIgnore	
    public boolean isAdmin() {	
        return Role.isAdmin(this.id);	
    }	
	
    @Length(min=0, max=100, message="\u89d2\u8272\u5206\u7c7b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getRoleType() {	
        return this.roleType;	
    }	
	
    public Role(User user) {	
        if (user != null) {	
            Role role = this;	
            role.userCode = user.getUserCode();	
            role.corpCode = user.getCorpCode();	
        }	
    }	
	
    public void setRoleSort(Integer roleSort) {	
        this.roleSort = roleSort;	
    }	
	
    public Role() {	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public void setRoleDataScopeListJson(String jsonString) {	
        List a = (List)JsonMapper.fromJson(jsonString, List.class);	
        if (a != null) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = a.iterator();	
            while (iterator2.hasNext()) {	
                void a2;	
                Map a3 = (Map)iterator.next();	
                RoleDataScope roleDataScope = new RoleDataScope();	
                void v1 = a2;	
                v1.setRoleCode(this.roleCode);	
                v1.setCtrlType((String)a3.get("ctrlType"));	
                a2.setCtrlData((String)a3.get("ctrlDaa"));	
                iterator2 = iterator;	
                void v2 = a2;	
                v2.setCtrlPermi(DATA_SCOPE_ALL);	
                v2.setIsNewRecord(true);	
                this.roleDataScopeList.add((RoleDataScope)a2);	
            }	
        }	
    }	
	
    public void setUserCode(String userCode) {	
        this.userCode = userCode;	
    }	
	
    @Length(min=0, max=1, message="\u6570\u636e\u8303\u56f4\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getDataScope() {	
        return this.dataScope;	
    }	
	
    @NotBlank(message="\u89d2\u8272\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u89d2\u8272\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getRoleName() {	
        return this.roleName;	
    }	
	
    public String getRoleName_like() {	
        return (String)this.getSqlMap().getWhere().getValue("role_name", QueryType.LIKE);	
    }	
	
    public void setIsSys(String isSys) {	
        this.isSys = isSys;	
    }	
	
    public void setRoleType(String roleType) {	
        this.roleType = roleType;	
    }	
	
    public void setRoleName(String roleName) {	
        this.roleName = roleName;	
    }	
	
    @JsonIgnore	
    public List<RoleDataScope> getRoleDataScopeList() {	
        return this.roleDataScopeList;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public void setRoleMenuListJson(String jsonString) {	
        List a = (List)JsonMapper.fromJson(jsonString, List.class);	
        if (a != null) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = a.iterator();	
            while (iterator2.hasNext()) {	
                void a2;	
                String a3 = (String)iterator.next();	
                RoleMenu roleMenu = new RoleMenu();	
                iterator2 = iterator;	
                void v1 = a2;	
                a2.setRoleCode(this.roleCode);	
                v1.setMenuCode(a3);	
                v1.setIsNewRecord(true);	
                this.roleMenuList.add((RoleMenu)a2);	
            }	
        }	
    }	
	
    public List<UserRole> getUserRoleList() {	
        return this.userRoleList;	
    }	
	
    public List<RoleMenu> getRoleMenuList() {	
        return this.roleMenuList;	
    }	
	
    public void setRoleCode_like(String roleCode) {	
        this.getSqlMap().getWhere().and("role_code", QueryType.LIKE, roleCode);	
    }	
	
    public void setDataScope(String dataScope) {	
        this.dataScope = dataScope;	
    }	
	
    public Integer getRoleSort() {	
        return this.roleSort;	
    }	
	
    public void setRoleCode(String roleCode) {	
        this.roleCode = roleCode;	
    }	
	
    public void setUserType(String userType) {	
        this.userType = userType;	
    }	
	
    public String getUserCode() {	
        return this.userCode;	
    }	
	
    public Role(String id) {	
        super(id);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public void setUserRoleString(String userCodes) {	
        String[] a = StringUtils.split(userCodes, ",");	
        if (a != null) {	
            int n;	
            String[] arrstring = a;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                if (StringUtils.isNotBlank(a2)) {	
                    void a3;	
                    UserRole userRole = new UserRole();	
                    void v1 = a3;	
                    v1.setRoleCode(this.roleCode);	
                    v1.setUserCode(a2);	
                    this.userRoleList.add((UserRole)a3);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
}	
	
