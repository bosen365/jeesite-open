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
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hibernate.validator.constraints.Length;	
import org.hibernate.validator.constraints.NotBlank;	
	
@Table(	
   name = "${_prefix}sys_role",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = BaseEntity.class	
), @Column(	
   includeEntity = DataEntity.class	
), @Column(	
   name = "role_code",	
   attrName = "roleCode",	
   label = "角色编码",	
   isPK = true	
), @Column(	
   name = "role_name",	
   attrName = "roleName",	
   label = "角色名称"	
), @Column(	
   name = "role_type",	
   attrName = "roleType",	
   label = "角色分类",	
   comment = "角色分类（高管、中层、基层、其它）"	
), @Column(	
   name = "role_sort",	
   attrName = "roleSort",	
   label = "角色排序",	
   comment = "角色排序（升序）"	
), @Column(	
   name = "is_sys",	
   attrName = "isSys",	
   label = "系统内置",	
   comment = "系统内置（1是 0否）"	
), @Column(	
   name = "user_type",	
   attrName = "userType",	
   label = "用户类型",	
   comment = "用户类型（none未设置 employee员工 member会员）"	
), @Column(	
   name = "data_scope",	
   attrName = "dataScope",	
   label = "数据范围",	
   comment = "数据范围设置（0未设置  1全部数据权限 2自定义数据权限）"	
)},	
   orderBy = "a.role_sort ASC",	
   extWhereKeys = "dsf"	
)	
public class Role extends DataEntity {	
   private String dataScope;	
   public static final String DATA_SCOPE_NONE = "0";	
   private String userCode;	
   private static final long serialVersionUID = 1L;	
   public static final String DATA_SCOPE_ALL = "1";	
   public static final String DATA_SCOPE_CUSTOM = "2";	
   private String roleCode;	
   public static final String CORP_ADMIN_ROLE_CODE = Global.getConfig("user.corpAdminRole)ode");	
   private String userType;	
   private String isSys;	
   private List roleMenuList = ListUtils.newArrayList();	
   private String roleName;	
   private String roleType;	
   private List roleDataScopeList = ListUtils.newArrayList();	
   private List userRoleList = ListUtils.newArrayList();	
   private Integer roleSort;	
	
   public void setUserType(String userType) {	
      this.userType = userType;	
   }	
	
   public void setDataScope(String dataScope) {	
      this.dataScope = dataScope;	
   }	
	
   public void setRoleCode_like(String roleCode) {	
      this.getSqlMap().getWhere().and("role_code", QueryType.LIKE, roleCode);	
   }	
	
   public void setUserCode(String userCode) {	
      this.userCode = userCode;	
   }	
	
   public void setRoleName(String roleName) {	
      this.roleName = roleName;	
   }	
	
   public Role(User user) {	
      if (user != null) {	
         this.userCode = user.getUserCode();	
         this.corpCode = user.getCorpCode();	
      }	
	
   }	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   @JsonIgnore	
   public boolean isAdmin() {	
      return isAdmin(this.id);	
   }	
	
   public static boolean isAdmin(String id) {	
      return id != null && CORP_ADMIN_ROLE_CODE.equals(id);	
   }	
	
   public String getRoleCode_like() {	
      return (String)this.getSqlMap().getWhere().getValue("role_code", QueryType.LIKE);	
   }	
	
   public Role() {	
   }	
	
   public void setRoleSort(Integer roleSort) {	
      this.roleSort = roleSort;	
   }	
	
   public void setRoleDataScopeListJson(String jsonString) {	
      List a;	
      if ((a = (List)JsonMapper.fromJson(jsonString, List.class)) != null) {	
         Iterator var3;	
         Iterator var10000 = var3 = a.iterator();	
	
         while(var10000.hasNext()) {	
            Map a = (Map)var3.next();	
            RoleDataScope a = new RoleDataScope();	
            a.setRoleCode(this.roleCode);	
            a.setCtrlType((String)a.get("ctrlType"));	
            a.setCtrlData((String)a.get("ctrlData"));	
            var10000 = var3;	
            a.setIsNewRecord(true);	
            this.roleDataScopeList.add(a);	
         }	
      }	
	
   }	
	
   public void setRoleName_like(String roleName) {	
      this.getSqlMap().getWhere().and("role_name", QueryType.LIKE, roleName);	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "系统内置长度不能超过 1 个字符"	
   )	
   public String getIsSys() {	
      return this.isSys;	
   }	
	
   public Integer getRoleSort() {	
      return this.roleSort;	
   }	
	
   public String getRoleName_like() {	
      return (String)this.getSqlMap().getWhere().getValue("role_name", QueryType.LIKE);	
   }	
	
   @NotBlank(	
      message = "角色名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "角色名称长度不能超过 100 个字符"	
   )	
   public String getRoleName() {	
      return this.roleName;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "角色分类长度不能超过 100 个字符"	
   )	
   public String getRoleType() {	
      return this.roleType;	
   }	
	
   public List getRoleMenuList() {	
      return this.roleMenuList;	
   }	
	
   public List getUserRoleList() {	
      return this.userRoleList;	
   }	
	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
	
   public String getUserCode() {	
      return this.userCode;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "数据范围长度不能超过 1 个字符"	
   )	
   public String getDataScope() {	
      return this.dataScope;	
   }	
	
   public void setIsSys(String isSys) {	
      this.isSys = isSys;	
   }	
	
   @JsonIgnore	
   public List getRoleDataScopeList() {	
      return this.roleDataScopeList;	
   }	
	
   @Length(	
      min = 0,	
      max = 16,	
      message = "用户类型长度不能超过 16 个字符"	
   )	
   public String getUserType() {	
      return this.userType;	
   }	
	
   public void setUserRoleString(String userCodes) {	
      String[] a;	
      if ((a = StringUtils.split(userCodes, ",")) != null) {	
         String[] var3 = a;	
         int var4 = a.length;	
	
         int var5;	
         for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
            String a;	
            if (StringUtils.isNotBlank(a = var3[var5])) {	
               UserRole a = new UserRole();	
               a.setRoleCode(this.roleCode);	
               a.setUserCode(a);	
               this.userRoleList.add(a);	
            }	
	
            ++var5;	
         }	
      }	
	
   }	
	
   public Role(String id) {	
      super(id);	
   }	
	
   public void setRoleMenuListJson(String jsonString) {	
      List a;	
      if ((a = (List)JsonMapper.fromJson(jsonString, List.class)) != null) {	
         Iterator var3;	
         Iterator var10000 = var3 = a.iterator();	
	
         while(var10000.hasNext()) {	
            String a = (String)var3.next();	
            RoleMenu a = new RoleMenu();	
            var10000 = var3;	
            a.setRoleCode(this.roleCode);	
            a.setMenuCode(a);	
            a.setIsNewRecord(true);	
            this.roleMenuList.add(a);	
         }	
      }	
	
   }	
	
   public void setRoleType(String roleType) {	
      this.roleType = roleType;	
   }	
}	
