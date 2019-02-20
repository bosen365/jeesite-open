/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.validation.constraints.NotBlank	
 *  javax.validation.constraints.NotNull	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.DiskUsage;	
	
@Table(name="${_prefix}sys_menu", alias="a", columns={@Column(includeEntity=DataEntity.class), @Column(includeEntity=TreeEntity.class), @Column(name="menu_code", attrName="menuCode", label="\u83dc\u5355\u7f16\u7801", isPK=true), @Column(name="menu_name", attrName="menuNameOrig", label="\u83dc\u5355\u540d\u79f0", queryType=QueryType.LIKE, isTreeName=true), @Column(name="menu_type", attrName="menuType", label="\u83dc\u5355\u7c7b\u578b", comment="\uff081\u83dc\u5355 2\u6743\u9650\uff09"), @Column(name="menu_href", attrName="menuHref", label="\u94fe\u63a5"), @Column(name="menu_target", attrName="menuTarget", label="\u76ee\u6807"), @Column(name="menu_icon", attrName="menuIcon", label="\u56fe\u6807"), @Column(name="menu_color", attrName="menuColor", label="\u989c\u8272"), @Column(name="permission", attrName="permission", label="\u6743\u9650\u6807\u8bc6"), @Column(name="weight", attrName="weight", label="\u83dc\u5355\u6743\u91cd", comment="\u6743\u91cd\u8d8a\u5927\u8d8a\u91cd\u8981"), @Column(name="is_show", attrName="isShow", label="\u662f\u5426\u663e\u793a", comment="\uff081\u663e\u793a 0\u9690\u85cf\uff09"), @Column(name="sys_code", attrName="sysCode", label="\u5f52\u5c5e\u7cfb\u7edf", comment="\uff08default:\u4e3b\u5bfc\u822a\u83dc\u5355\u3001mobileApp:APP\u83dc\u5355\uff09"), @Column(name="module_codes", attrName="moduleCodes", label="\u5f52\u5c5e\u6a21\u5757", comment="\uff08\u591a\u4e2a\u7528\u9017\u53f7\u9694\u5f00\uff09", queryType=QueryType.LIKE), @Column(includeEntity=Extend.class, attrName="extend")}, orderBy="a.sys_code, a.tree_sorts, a.menu_code")	
public class Menu	
extends TreeEntity<Menu> {	
    private String menuType;	
    private String moduleCodes;	
    private String menuNameOrig;	
    private String isShow;	
    public static final String SYS_CODE_DEFAULT = "default";	
    public static final Integer WEIGHT_SUPER_ADMIN;	
    public static final Integer WEIGHT_DEFAULT;	
    private String userCode;	
    public static final String SYS_CODE_MOBILE_APP = "mobileApp";	
    private String menuColor;	
    public static final String TYPE_MENU = "1";	
    private static final long serialVersionUID = 1L;	
    private String permission;	
    private String menuHref;	
    private String roleCode;	
    private String menuCode;	
    private String menuTarget;	
    private Extend extend;	
    private String sysCode;	
    private String menuIcon;	
    public static final String TYPE_PERM = "2";	
    public static final Integer WEIGHT_CORP_ADMIN;	
    private Integer weight;	
    public static final Integer SUPER_ADMIN_GET_MENU_MIN_WEIGHT;	
    private String[] defaultRoleCodes;	
    public static final Integer WEIGHT_SEC_ADMIN;	
	
    public String getPermissionAbbr() {	
        return StringUtils.abbr((String)this.getPermission(), (int)30);	
    }	
	
    public void setExtend(Extend extend) {	
        this.extend = extend;	
    }	
	
    @Length(min=0, max=20, message="\u76ee\u6807\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 20 \u4e2a\u5b57\u7b26")	
    public String getMenuTarget() {	
        return this.menuTarget;	
    }	
	
    @NotBlank(message="\u662f\u5426\u663e\u793a\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=1, message="\u662f\u5426\u663e\u793a\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsShow() {	
        return this.isShow;	
    }	
	
    @Length(min=0, max=2000, message="\u6743\u9650\u6807\u8bc6\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 2000 \u4e2a\u5b57\u7b26")	
    public String getPermission() {	
        return this.permission;	
    }	
	
    public void setIsShow(String isShow) {	
        this.isShow = isShow;	
    }	
	
    public void setModuleCodes(String moduleCodes) {	
        this.moduleCodes = moduleCodes;	
    }	
	
    public String getHrefAbbr() {	
        return StringUtils.abbr((String)this.getMenuHref(), (int)30);	
    }	
	
    public void setWeight(Integer weight) {	
        this.weight = weight;	
    }	
	
    @Length(min=0, max=2000, message="\u94fe\u63a5\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 2000 \u4e2a\u5b57\u7b26")	
    public String getMenuHref() {	
        return this.menuHref;	
    }	
	
    @NotBlank(message="\u83dc\u5355\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u83dc\u5355\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getMenuNameOrig() {	
        return this.menuNameOrig;	
    }	
	
    public Integer getWeight_lt() {	
        return (Integer)this.sqlMap.getWhere().getValue("weight", QueryType.LT);	
    }	
	
    static {	
        WEIGHT_DEFAULT = 20;	
        WEIGHT_SEC_ADMIN = 40;	
        WEIGHT_CORP_ADMIN = 60;	
        WEIGHT_SUPER_ADMIN = 80;	
        SUPER_ADMIN_GET_MENU_MIN_WEIGHT = Global.getPropertyToInteger("user.superAdminGetMenuMinWeight", "40");	
    }	
	
    public String getRoleCode() {	
        return this.roleCode;	
    }	
	
    public String getMenuCode() {	
        return this.menuCode;	
    }	
	
    public void setMenuIcon(String menuIcon) {	
        this.menuIcon = menuIcon;	
    }	
	
    public Boolean getIsPerm() {	
        return TYPE_PERM.equals(this.menuType);	
    }	
	
    @Override	
    public void setParent(Menu parent) {	
        this.parent = parent;	
    }	
	
    public Menu(String id) {	
        super(id);	
    }	
	
    public void setMenuHref(String menuHref) {	
        this.menuHref = menuHref;	
    }	
	
    public void setMenuNameOrig(String menuName) {	
        this.menuNameOrig = menuName;	
    }	
	
    @NotBlank(message="\u83dc\u5355\u7c7b\u578b\uff081\u83dc\u5355 2\u6743\u9650\uff09\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=1, message="\u83dc\u5355\u7c7b\u578b\uff081\u83dc\u5355 2\u6743\u9650\uff09\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getMenuType() {	
        return this.menuType;	
    }	
	
    public Menu() {	
        this(null);	
    }	
	
    public Extend getExtend() {	
        return this.extend;	
    }	
	
    public void setMenuColor(String menuColor) {	
        this.menuColor = menuColor;	
    }	
	
    @Override	
    public Menu getParent() {	
        return (Menu)this.parent;	
    }	
	
    public void setMenuTarget(String menuTarget) {	
        this.menuTarget = menuTarget;	
    }	
	
    public void setUserCode(String userCode) {	
        this.userCode = userCode;	
    }	
	
    @NotNull(message="\u83dc\u5355\u6743\u91cd\u4e0d\u80fd\u4e3a\u7a7a")	
    public Integer getWeight() {	
        return this.weight;	
    }	
	
    public Integer getWeight_gte() {	
        return (Integer)this.sqlMap.getWhere().getValue("weight", QueryType.GTE);	
    }	
	
    public String[] getDefaultRoleCodes() {	
        return this.defaultRoleCodes;	
    }	
	
    @Length(min=0, max=50, message="\u989c\u8272\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 50 \u4e2a\u5b57\u7b26")	
    public String getMenuColor() {	
        return this.menuColor;	
    }	
	
    public void setMenuType(String menuType) {	
        this.menuType = menuType;	
    }	
	
    public void setMenuCode(String menuCode) {	
        this.menuCode = menuCode;	
    }	
	
    @NotBlank(message="\u5f52\u5c5e\u7cfb\u7edf\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u5f52\u5c5e\u7cfb\u7edf\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getSysCode() {	
        return this.sysCode;	
    }	
	
    @Length(min=0, max=100, message="\u56fe\u6807\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getMenuIcon() {	
        return this.menuIcon;	
    }	
	
    public void setPermission(String permission) {	
        this.permission = permission;	
    }	
	
    public void setSysCode(String sysCode) {	
        this.sysCode = sysCode;	
    }	
	
    public void setRoleCode(String roleCode) {	
        this.roleCode = roleCode;	
    }	
	
    @NotBlank(message="\u5f52\u5c5e\u6a21\u5757\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=500, message="\u5f52\u5c5e\u6a21\u5757\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getModuleCodes() {	
        return this.moduleCodes;	
    }	
	
    public String getMenuName() {	
        return Global.getText(this.menuNameOrig, new String[0]);	
    }	
	
    public String getMenuUrl() {	
        String a2 = this.getMenuHref();	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            return "";	
        }	
        String[] a3 = StringUtils.substringsBetween((String)this.getMenuHref(), (String)"", (String)"}");	
        if (a3 != null) {	
            int n;	
            String[] arrstring = a3;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a4 = arrstring[n];	
                if ("ssoToken".equals(a4)) {	
                    void a5;	
                    String a6 = UserUtils.getUser().getLoginCode();	
                    String string = UserUtils.getSsoToken(a6);	
                    a2 = StringUtils.replace((String)a2, (String)("{" + a4 + "}"), (String)new StringBuilder().insert(0, a6).append("/").append((String)a5).toString());	
                } else if ("userCode".equals(a4)) {	
                    a2 = StringUtils.replace((String)a2, (String)new StringBuilder().insert(0, "{").append(a4).append("}").toString(), (String)this.getCurrentUser().getUserCode());	
                } else if ("userName".equals(a4)) {	
                    a2 = StringUtils.replace((String)a2, (String)new StringBuilder().insert(0, "").append(a4).append("}").toString(), (String)this.getCurrentUser().getUserName());	
                } else if ("userType".equals(a4)) {	
                    a2 = StringUtils.replace((String)a2, (String)new StringBuilder().insert(0, "{").append(a4).append("}").toString(), (String)this.getCurrentUser().getUserType());	
                } else if ("menuCode".equals(a4)) {	
                    a2 = StringUtils.replace((String)a2, (String)new StringBuilder().insert(0, "").append(a4).append("}").toString(), (String)this.getMenuCode());	
                } else if ("menuParentCode".equals(a4)) {	
                    a2 = StringUtils.replace((String)a2, (String)new StringBuilder().insert(0, "{").append(a4).append("}").toString(), (String)this.getParentCode());	
                } else {	
                    String string = a2;	
                    a2 = "menuParentCodes".equals(a4) ? StringUtils.replace((String)string, (String)new StringBuilder().insert(0, "").append(a4).append("}").toString(), (String)this.getParentCodes()) : StringUtils.replace((String)string, (String)new StringBuilder().insert(0, "").append(a4).append("}").toString(), (String)Global.getConfig(a4));	
                }	
                n3 = ++n;	
            }	
        }	
        if (StringUtils.startsWith((CharSequence)a2, (CharSequence)"///")) {	
            return StringUtils.substring((String)a2, (int)2);	
        }	
        if (StringUtils.startsWith((CharSequence)a2, (CharSequence)"//")) {	
            return new StringBuilder().insert(0, ServletUtils.getRequest().getContextPath()).append(StringUtils.substring((String)a2, (int)1)).toString();	
        }	
        if (StringUtils.startsWith((CharSequence)a2, (CharSequence)"/")) {	
            return new StringBuilder().insert(0, ServletUtils.getRequest().getContextPath()).append(Global.getAdminPath()).append(a2).toString();	
        }	
        return a2;	
    }	
	
    public void setWeight_lt(Integer weight) {	
        this.sqlMap.getWhere().and("weight", QueryType.LT, weight);	
    }	
	
    public Boolean getIsMenu() {	
        return TYPE_MENU.equals(this.menuType);	
    }	
	
    public String getUserCode() {	
        return this.userCode;	
    }	
	
    public void setDefaultRoleCodes(String[] defaultRoleCodes) {	
        this.defaultRoleCodes = defaultRoleCodes;	
    }	
	
    public void setWeight_gte(Integer weight) {	
        this.sqlMap.getWhere().and("weight", QueryType.GTE, weight);	
    }	
}	
	
