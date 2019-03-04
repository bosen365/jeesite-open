package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.l.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hibernate.validator.constraints.NotBlank;	
	
@Table(	
   name = "${_prefix}sys_menu",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = DataEntity.class	
), @Column(	
   includeEntity = TreeEntity.class	
), @Column(	
   name = "menu_code",	
   attrName = "menuCode",	
   label = "菜单编码",	
   isPK = true	
), @Column(	
   name = "menu_name",	
   attrName = "menuNameOrig",	
   label = "菜单名称",	
   queryType = QueryType.LIKE,	
   isTreeName = true	
), @Column(	
   name = "menu_type",	
   attrName = "menuType",	
   label = "菜单类型",	
   comment = "（1菜单 2权限）"	
), @Column(	
   name = "menu_href",	
   attrName = "menuHref",	
   label = "链接"	
), @Column(	
   name = "menu_target",	
   attrName = "menuTarget",	
   label = "目标"	
), @Column(	
   name = "menu_icon",	
   attrName = "menuIcon",	
   label = "图标"	
), @Column(	
   name = "menu_color",	
   attrName = "menuColor",	
   label = "颜色"	
), @Column(	
   name = "permission",	
   attrName = "permission",	
   label = "权限标识"	
), @Column(	
   name = "weight",	
   attrName = "weight",	
   label = "菜单权重",	
   comment = "权重越大越重要"	
), @Column(	
   name = "is_show",	
   attrName = "isShow",	
   label = "是否显示",	
   comment = "（1显示 0隐藏）"	
), @Column(	
   name = "sys_code",	
   attrName = "sysCode",	
   label = "归属系统",	
   comment = "（default:主导航菜单、mobileApp:APP菜单）"	
), @Column(	
   name = "module_codes",	
   attrName = "moduleCodes",	
   label = "归属模块",	
   comment = "（多个用逗号隔开）",	
   queryType = QueryType.LIKE	
), @Column(	
   includeEntity = Extend.class,	
   attrName = "extend"	
)},	
   orderBy = "a.sys_code, a.tree_sorts, a.menu_code"	
)	
public class Menu extends TreeEntity {	
   private String userCode;	
   private String menuHref;	
   public static final String TYPE_MENU = "1";	
   private String roleCode;	
   private Extend extend;	
   public static final Integer WEIGHT_DEFAULT = 20;	
   private String menuCode;	
   private String permission;	
   public static final Integer WEIGHT_SEC_ADMIN = 40;	
   public static final String SYS_CODE_DEFAULT = "default";	
   private String menuColor;	
   private static final long serialVersionUID = 1L;	
   private String menuIcon;	
   private String menuType;	
   public static final Integer WEIGHT_CORP_ADMIN = 60;	
   private String isShow;	
   private String sysCode;	
   public static final String SYS_CODE_MOBILE_APP = "mobileApp";	
   private String[] defaultRoleCodes;	
   public static final String TYPE_PERM = "2";	
   private Integer weight;	
   public static final Integer WEIGHT_SUPER_ADMIN = 80;	
   private String menuTarget;	
   public static final Integer SUPER_ADMIN_GET_MENU_MIN_WEIGHT = ObjectUtils.toInteger(Global.getProperty("user.superAdminGetMenuMinWeight"));	
   private String moduleCodes;	
   private String menuNameOrig;	
	
   public void setMenuCode(String menuCode) {	
      this.menuCode = menuCode;	
   }	
	
   public Boolean getIsPerm() {	
      return "2".equals(this.menuType);	
   }	
	
   public void setSysCode(String sysCode) {	
      this.sysCode = sysCode;	
   }	
	
   @NotBlank(	
      message = "归属模块不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 500,	
      message = "归属模块长度不能超过 500 个字符"	
   )	
   public String getModuleCodes() {	
      return this.moduleCodes;	
   }	
	
   public void setMenuTarget(String menuTarget) {	
      this.menuTarget = menuTarget;	
   }	
	
   @Length(	
      min = 0,	
      max = 50,	
      message = "颜色长度不能超过 50 个字符"	
   )	
   public String getMenuColor() {	
      return this.menuColor;	
   }	
	
   public Menu() {	
      this((String)null);	
   }	
	
   public String[] getDefaultRoleCodes() {	
      return this.defaultRoleCodes;	
   }	
	
   @NotBlank(	
      message = "菜单类型（1菜单 2权限）不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 1,	
      message = "菜单类型（1菜单 2权限）长度不能超过 1 个字符"	
   )	
   public String getMenuType() {	
      return this.menuType;	
   }	
	
   @NotBlank(	
      message = "归属系统不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "归属系统长度不能超过 64 个字符"	
   )	
   public String getSysCode() {	
      return this.sysCode;	
   }	
	
   @Length(	
      min = 0,	
      max = 20,	
      message = "目标长度不能超过 20 个字符"	
   )	
   public String getMenuTarget() {	
      return this.menuTarget;	
   }	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   public void setMenuColor(String menuColor) {	
      this.menuColor = menuColor;	
   }	
	
   public void setMenuHref(String menuHref) {	
      this.menuHref = menuHref;	
   }	
	
   public Menu(String id) {	
      super(id);	
   }	
	
   public Integer getWeight_lt() {	
      return (Integer)this.sqlMap.getWhere().getValue("weight", QueryType.LT);	
   }	
	
   public Boolean getIsMenu() {	
      return "1".equals(this.menuType);	
   }	
	
   public void setPermission(String permission) {	
      this.permission = permission;	
   }	
	
   public String getMenuName() {	
      String var10000 = this.menuNameOrig;	
      String[] var10001 = new String[0];	
      boolean var10003 = true;	
      return Global.getText(var10000, var10001);	
   }	
	
   public void setMenuType(String menuType) {	
      this.menuType = menuType;	
   }	
	
   public void setWeight_lt(Integer weight) {	
      this.sqlMap.getWhere().and("weight", QueryType.LT, weight);	
   }	
	
   public void setParent(Menu parent) {	
      this.parent = parent;	
   }	
	
   public void setIsShow(String isShow) {	
      this.isShow = isShow;	
   }	
	
   public void setMenuIcon(String menuIcon) {	
      this.menuIcon = menuIcon;	
   }	
	
   public void setUserCode(String userCode) {	
      this.userCode = userCode;	
   }	
	
   @Length(	
      min = 0,	
      max = 2000,	
      message = "权限标识长度不能超过 2000 个字符"	
   )	
   public String getPermission() {	
      return this.permission;	
   }	
	
   public String getPermissionAbbr() {	
      return StringUtils.abbr(this.getPermission(), 30);	
   }	
	
   public Extend getExtend() {	
      return this.extend;	
   }	
	
   public void setWeight_gte(Integer weight) {	
      this.sqlMap.getWhere().and("weight", QueryType.GTE, weight);	
   }	
	
   public Menu getParent() {	
      return (Menu)this.parent;	
   }	
	
   public void setWeight(Integer weight) {	
      this.weight = weight;	
   }	
	
   @Length(	
      min = 0,	
      max = 2000,	
      message = "链接长度不能超过 2000 个字符"	
   )	
   public String getMenuHref() {	
      return this.menuHref;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "图标长度不能超过 100 个字符"	
   )	
   public String getMenuIcon() {	
      return this.menuIcon;	
   }	
	
   public String getMenuUrl() {	
      String a;	
      if (StringUtils.isBlank(a = this.getMenuHref())) {	
         return "";	
      } else {	
         String[] a;	
         if ((a = StringUtils.substringsBetween(this.getMenuHref(), "{", "}")) != null) {	
            String[] var3 = a;	
            int var4 = a.length;	
	
            int var5;	
            for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
               String a = var3[var5];	
               if ("soToken".equals(a)) {	
                  String a;	
                  String a = UserUtils.getSsoToken(a = UserUtils.getUser().getUserCode());	
                  a = StringUtils.replace(a, "{" + a + "}", (new StringBuilder()).insert(0, a).append("/").append(a).toString());	
               } else if ("uerCode".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getCurrentUser().getUserCode());	
               } else if ("userName".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getCurrentUser().getUserName());	
               } else if ("uerType".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getCurrentUser().getUserType());	
               } else if ("menuCode".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getMenuCode());	
               } else if ("menuParentCode".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getParentCode());	
               } else if ("menuParentCodes".equals(a)) {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), this.getParentCodes());	
               } else {	
                  a = StringUtils.replace(a, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), Global.getConfig(a));	
               }	
	
               ++var5;	
            }	
         }	
	
         if (StringUtils.startsWith(a, "///")) {	
            return StringUtils.substring(a, 2);	
         } else if (StringUtils.startsWith(a, "//")) {	
            return (new StringBuilder()).insert(0, ServletUtils.getRequest().getContextPath()).append(StringUtils.substring(a, 1)).toString();	
         } else {	
            return StringUtils.startsWith(a, "/") ? (new StringBuilder()).insert(0, ServletUtils.getRequest().getContextPath()).append(Global.getAdminPath()).append(a).toString() : a;	
         }	
      }	
   }	
	
   public String getHrefAbbr() {	
      return StringUtils.abbr(this.getMenuHref(), 30);	
   }	
	
   public void setModuleCodes(String moduleCodes) {	
      this.moduleCodes = moduleCodes;	
   }	
	
   @NotBlank(	
      message = "菜单名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "菜单名称长度不能超过 100 个字符"	
   )	
   public String getMenuNameOrig() {	
      return this.menuNameOrig;	
   }	
	
   public Integer getWeight_gte() {	
      return (Integer)this.sqlMap.getWhere().getValue("weight", QueryType.GTE);	
   }	
	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
	
   public String getMenuCode() {	
      return this.menuCode;	
   }	
	
   public void setExtend(Extend extend) {	
      this.extend = extend;	
   }	
	
   public void setDefaultRoleCodes(String[] defaultRoleCodes) {	
      this.defaultRoleCodes = defaultRoleCodes;	
   }	
	
   @NotBlank(	
      message = "是否显示不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 1,	
      message = "是否显示长度不能超过 1 个字符"	
   )	
   public String getIsShow() {	
      return this.isShow;	
   }	
	
   public String getUserCode() {	
      return this.userCode;	
   }	
	
   public void setMenuNameOrig(String menuName) {	
      this.menuNameOrig = menuName;	
   }	
	
   @NotNull(	
      message = "菜单权重不能为空"	
   )	
   public Integer getWeight() {	
      return this.weight;	
   }	
}	
