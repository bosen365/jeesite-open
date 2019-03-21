/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonFormat	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.validation.constraints.Email	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.validator.PatternValue;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.entity.UserRole;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.Email;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcMem;	
import org.hyperic.sigar.cmd.Watch;	
	
@Table(name="${_prefix}sys_user", alias="a", columns={@Column(includeEntity=BaseEntity.class), @Column(includeEntity=DataEntity.class), @Column(name="user_code", attrName="userCode", label="\u7528\u6237\u7f16\u7801", isPK=true), @Column(name="login_code", attrName="loginCode", label="\u767b\u5f55\u8d26\u53f7", queryType=QueryType.LIKE), @Column(name="user_name", attrName="userName", label="\u7528\u6237\u6635\u79f0", queryType=QueryType.LIKE), @Column(name="password", attrName="password", label="\u767b\u5f55\u5bc6\u7801"), @Column(name="email", attrName="email", label="\u7535\u5b50\u90ae\u7bb1", queryType=QueryType.LIKE), @Column(name="mobile", attrName="mobile", label="\u624b\u673a\u53f7\u7801", queryType=QueryType.LIKE), @Column(name="phone", attrName="phone", label="\u529e\u516c\u7535\u8bdd", queryType=QueryType.LIKE), @Column(name="sex", attrName="sex", label="\u7528\u6237\u6027\u522b"), @Column(name="avatar", attrName="avatar", label="\u5934\u50cf\u8def\u5f84"), @Column(name="sign", attrName="sign", label="\u4e2a\u6027\u7b7e\u540d"), @Column(name="wx_openid", attrName="wxOpenid", label="\u7ed1\u5b9a\u7684\u5fae\u4fe1\u53f7"), @Column(name="mobile_imei", attrName="mobileImei", label="\u7ed1\u5b9a\u7684\u624b\u673a\u4e32\u53f7"), @Column(name="user_type", attrName="userType", label="\u7528\u6237\u7c7b\u578b", isUpdate=false, comment="\u7528\u6237\u7c7b\u578b"), @Column(name="ref_code", attrName="refCode", label="\u7528\u6237\u7c7b\u578b\u5f15\u7528\u7f16\u53f7", isUpdate=false), @Column(name="ref_name", attrName="refName", label="\u7528\u6237\u7c7b\u578b\u5f15\u7528\u59d3\u540d", queryType=QueryType.LIKE), @Column(name="mgr_type", attrName="mgrType", label="\u7ba1\u7406\u5458\u7c7b\u578b", isUpdate=false, comment="\u7ba1\u7406\u5458\u7c7b\u578b\uff080\u975e\u7ba1\u7406\u5458 1\u7cfb\u7edf\u7ba1\u7406\u5458  2\u4e8c\u7ea7\u7ba1\u7406\u5458\uff09"), @Column(name="pwd_security_level", attrName="pwdSecurityLevel", label="\u5bc6\u7801\u5b89\u5168\u7ea7\u522b", isUpdate=false, comment="\u5bc6\u7801\u5b89\u5168\u7ea7\u522b\uff080\u521d\u59cb 1\u5f88\u5f31 2\u5f31 3\u5b89\u5168 4\u5f88\u5b89\u5168\uff09"), @Column(name="pwd_update_date", attrName="pwdUpdateDate", label="\u5bc6\u7801\u6700\u540e\u66f4\u65b0\u65f6\u95f4", isUpdate=false), @Column(name="pwd_update_record", attrName="pwdUpdateRecord", label="\u5bc6\u7801\u4fee\u6539\u8bb0\u5f55", isUpdate=false), @Column(name="pwd_question", attrName="pwdQuestion", label="\u5bc6\u4fdd\u95ee\u9898", isUpdate=false), @Column(name="pwd_question_answer", attrName="pwdQuestionAnswer", label="\u5bc6\u4fdd\u95ee\u9898\u7b54\u6848", isUpdate=false), @Column(name="pwd_question_2", attrName="pwdQuestion2", label="\u5bc6\u4fdd\u95ee\u98982", isUpdate=false), @Column(name="pwd_question_answer_2", attrName="pwdQuestionAnswer2", label="\u5bc6\u4fdd\u95ee\u9898\u7b54\u68482", isUpdate=false), @Column(name="pwd_question_3", attrName="pwdQuestion3", label="\u5bc6\u4fdd\u95ee\u98983", isUpdate=false), @Column(name="pwd_question_answer_3", attrName="pwdQuestionAnswer3", label="\u5bc6\u4fdd\u95ee\u9898\u7b54\u68483", isUpdate=false), @Column(name="pwd_quest_update_date", attrName="pwdQuestUpdateDate", label="\u5bc6\u7801\u95ee\u9898\u4fee\u6539\u65f6\u95f4", isUpdate=false), @Column(name="last_login_ip", attrName="lastLoginIp", label="\u6700\u540e\u767b\u9646IP", isUpdate=false), @Column(name="last_login_date", attrName="lastLoginDate", label="\u6700\u540e\u767b\u9646\u65f6\u95f4", isUpdate=false), @Column(name="freeze_date", attrName="freezeDate", label="\u51bb\u7ed3\u65f6\u95f4", isUpdate=false), @Column(name="freeze_cause", attrName="freezeCause", label="\u51bb\u7ed3\u539f\u56e0", isUpdate=false), @Column(name="user_weight", attrName="userWeight", label="\u7528\u6237\u6743\u91cd", comment="\u7528\u6237\u6743\u91cd\uff08\u964d\u5e8f\uff09"), @Column(includeEntity=Extend.class, attrName="extend")}, orderBy="a.user_weight DESC, a.update_date DESC")	
public class User	
extends DataEntity<User> {	
    private String email;	
    public static final String USER_TYPE_EXPERT = "expert";	
    private String pwdQuestionAnswer;	
    private List<UserRole> userRoleList = ListUtils.newArrayList();	
    public static final String SUPER_ADMIN_CODE = Global.getProperty("user.superAdminCode", "system");	
    public static final String MGR_TYPE_NOT_ADMIN = "0";	
    private String mgrType;	
    private String userCode;	
    public static final String MGR_TYPE_SEC_ADMIN = "2";	
    private Date oldLastLoginDate;	
    private String pwdQuestion;	
    private String loginCode;	
    private String refName;	
    public static final int PWD_SECURITY_LEVEL_MEDIUM = 3;	
    private String oldLastLoginIp;	
    private String freezeCause;	
    public static final int PWD_SECURITY_LEVEL_VERYWEAK = 1;	
    private String mobileImei;	
    public static final String USER_TYPE_BTYPE = "btype";	
    private List<Role> roleList = ListUtils.newArrayList();	
    private String phone;	
    private String sign;	
    private String mobile;	
    private List<UserDataScope> userDataScopeList = ListUtils.newArrayList();	
    private Date pwdQuestUpdateDate;	
    private String pwdUpdateRecord;	
    public static final String USER_TYPE_MEMBER = "member";	
    public static final int PWD_SECURITY_LEVEL_INITPWD = 0;	
    private static final long serialVersionUID = 1L;	
    private Integer pwdSecurityLevel;	
    public static final String MGR_TYPE_CORP_ADMIN = "1";	
    private Object refObj;	
    public static final int PWD_SECURITY_LEVEL_WEAK = 2;	
    private String pwdQuestion2;	
    private Date lastLoginDate;	
    private String sex;	
    private String lastLoginIp;	
    private String pwdQuestionAnswer2;	
    private Date freezeDate;	
    private String avatarBase64;	
    private Date pwdUpdateDate;	
    private String pwdQuestion3;	
    public static final int PWD_SECURITY_LEVEL_STRONG = 4;	
    private String password;	
    private Extend extend;	
    private String wxOpenid;	
    private String pwdQuestionAnswer3;	
    public static final String USER_TYPE_EMPLOYEE = "employee";	
    private Integer userWeight;	
    public static final String USER_TYPE_PERSION = "persion";	
    private String refCode;	
    private String userName;	
    private String roleCode;	
    public static final String USER_TYPE_NONE = "none";	
    private String userType;	
    private String avatar;	
	
    public void setSex(String sex) {	
        this.sex = sex;	
    }	
	
    public void setPwdQuestionAnswer3(String pwdQuestionAnswer3) {	
        this.pwdQuestionAnswer3 = pwdQuestionAnswer3;	
    }	
	
    public Extend getExtend() {	
        return this.extend;	
    }	
	
    @Length(min=0, max=100, message="\u7b7e\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getSign() {	
        return this.sign;	
    }	
	
    public void setUserDataScopeListJson(String jsonString) {	
        List a = (List)JsonMapper.fromJson((String)jsonString, List.class);	
        if (a != null) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = a.iterator();	
            while (iterator2.hasNext()) {	
                void a2;	
                Map a3 = (Map)iterator.next();	
                UserDataScope userDataScope = new UserDataScope();	
                void v1 = a2;	
                v1.setUserCode(this.userCode);	
                v1.setCtrlType((String)a3.get("ctrlType"));	
                a2.setCtrlData((String)a3.get("ctrlData"));	
                iterator2 = iterator;	
                this.userDataScopeList.add((UserDataScope)a2);	
            }	
        }	
    }	
	
    public void setPhone(String phone) {	
        this.phone = phone;	
    }	
	
    public static boolean isSuperAdmin(String userCode) {	
        return userCode != null && StringUtils.inString((String)userCode, (String[])SUPER_ADMIN_CODE.split(","));	
    }	
	
    public void setPwdUpdateRecord(String pwdUpdateRecord) {	
        this.pwdUpdateRecord = pwdUpdateRecord;	
    }	
	
    @Length(min=0, max=100, message="\u7528\u6237\u7c7b\u578b\u5f15\u7528\u59d3\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getRefName() {	
        return this.refName;	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=100, message="\u5bc6\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getPassword() {	
        return this.password;	
    }	
	
    @Length(min=0, max=16, message="\u7528\u6237\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 16 \u4e2a\u5b57\u7b26")	
    public String getUserType() {	
        return this.userType;	
    }	
	
    @JsonIgnore	
    public Date getPwdQuestUpdateDate() {	
        return this.pwdQuestUpdateDate;	
    }	
	
    public User(String id) {	
        super(id);	
    }	
	
    @JsonIgnore	
    public boolean isSuperAdmin() {	
        return User.isSuperAdmin(this.userCode);	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=200, message="\u5bc6\u4fdd\u95ee\u9898\u7b54\u6848\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPwdQuestionAnswer() {	
        return this.pwdQuestionAnswer;	
    }	
	
    public Object getRefObj() {	
        return this.refObj;	
    }	
	
    @Length(min=0, max=100, message="\u7ed1\u5b9a\u7684\u624b\u673a\u4e32\u53f7\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getMobileImei() {	
        return this.mobileImei;	
    }	
	
    public void setFreezeDate(Date freezeDate) {	
        this.freezeDate = freezeDate;	
    }	
	
    public void setCorpCode_(String corpCode) {	
        this.corpCode = corpCode;	
    }	
	
    public String getAvatarBase64() {	
        return this.avatarBase64;	
    }	
	
    public void setPwdQuestUpdateDate(Date pwdQuestUpdateDate) {	
        this.pwdQuestUpdateDate = pwdQuestUpdateDate;	
    }	
	
    public void setPassword(String password) {	
        this.password = password;	
    }	
	
    @Email(message="\u90ae\u7bb1\u683c\u5f0f\u4e0d\u6b63\u786e")	
    @Length(min=0, max=100, message="\u90ae\u7bb1\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getEmail() {	
        return this.email;	
    }	
	
    @JsonIgnore	
    public Integer getPwdSecurityLevel() {	
        return this.pwdSecurityLevel;	
    }	
	
    public void setUserWeight(Integer userWeight) {	
        this.userWeight = userWeight;	
    }	
	
    @Length(min=0, max=30, message="\u529e\u516c\u7535\u8bdd\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 30 \u4e2a\u5b57\u7b26")	
    public String getPhone() {	
        return this.phone;	
    }	
	
    @Length(min=0, max=64, message="\u7528\u6237\u7c7b\u578b\u5f15\u7528\u7f16\u53f7\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getRefCode() {	
        return this.refCode;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getLastLoginDate() {	
        return this.lastLoginDate;	
    }	
	
    public User() {	
    }	
	
    public void setPwdQuestionAnswer2(String pwdQuestionAnswer2) {	
        this.pwdQuestionAnswer2 = pwdQuestionAnswer2;	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=200, message="\u5bc6\u4fdd\u95ee\u9898\u7b54\u68482\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPwdQuestionAnswer2() {	
        return this.pwdQuestionAnswer2;	
    }	
	
    public void setAvatarBase64(String avatarBase64) {	
        this.avatarBase64 = avatarBase64;	
    }	
	
    public void setUserRoleString(String roleCodes) {	
        String[] a = StringUtils.split((String)roleCodes, (String)",");	
        if (a != null) {	
            int n;	
            String[] arrstring = a;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                if (StringUtils.isNotBlank((CharSequence)a2) && !Role.isAdmin(a2)) {	
                    void a3;	
                    UserRole userRole = new UserRole();	
                    a3.setRoleCode(a2);	
                    this.userRoleList.add((UserRole)a3);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
	
    @JsonIgnore	
    public Date getPwdUpdateDate() {	
        return this.pwdUpdateDate;	
    }	
	
    @JsonIgnore	
    public String getPwdQuestion() {	
        return this.pwdQuestion;	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=200, message="\u5bc6\u4fdd\u95ee\u9898\u7b54\u68483\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPwdQuestionAnswer3() {	
        return this.pwdQuestionAnswer3;	
    }	
	
    @Override	
    public String toString() {	
        return this.userCode;	
    }	
	
    @Length(min=0, max=1, message="\u6027\u522b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getSex() {	
        return this.sex;	
    }	
	
    @JsonIgnore	
    public boolean isAdmin() {	
        return User.isSuperAdmin(this.userCode) || MGR_TYPE_CORP_ADMIN.equals(this.mgrType);	
    }	
	
    @JsonIgnore	
    public List<Role> getRoleList() {	
        return this.roleList;	
    }	
	
    public void setAvatar(String avatar) {	
        this.avatar = avatar;	
    }	
	
    public void setWxOpenid(String wxOpenid) {	
        this.wxOpenid = wxOpenid;	
    }	
	
    public void setRefObj(Object refObj) {	
        this.refObj = refObj;	
    }	
	
    public void setUserName(String userName) {	
        this.userName = userName;	
    }	
	
    public Integer getUserWeight() {	
        return this.userWeight;	
    }	
	
    @Length(min=0, max=600, message="\u5934\u50cf\u8def\u5f84\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 600 \u4e2a\u5b57\u7b26")	
    public String getAvatar() {	
        return this.avatar;	
    }	
	
    @NotBlank(message="\u767b\u5f55\u8d26\u53f7\u4e0d\u80fd\u4e3a\u7a7a")	
    @PatternValue(value="web.validator.user.loginCode", regexp="[a-zA-Z0-9_\u4e00-\u9fa5]{4,20}", message="\u767b\u5f55\u8d26\u53f7\u957f\u5ea6\u5e94\u4e3a 4 \u5230 20 \u4e2a\u5b57\u7b26\uff0c\u5e76\u4e14\u53ea\u80fd\u5305\u542b\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf\u6216\u4e2d\u6587")	
    public String getLoginCode() {	
        return this.loginCode;	
    }	
	
    public String getUserCode() {	
        return this.userCode;	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=200, message="\u5bc6\u4fdd\u95ee\u98982\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPwdQuestion2() {	
        return this.pwdQuestion2;	
    }	
	
    public void setLoginCode(String loginCode) {	
        this.loginCode = loginCode;	
    }	
	
    @NotBlank(message="\u7528\u6237\u6635\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u7528\u6237\u6635\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getUserName() {	
        return this.userName;	
    }	
	
    public void setPwdQuestion2(String pwdQuestion2) {	
        this.pwdQuestion2 = pwdQuestion2;	
    }	
	
    public void setMgrType(String mgrType) {	
        this.mgrType = mgrType;	
    }	
	
    public Date getFreezeDate() {	
        return this.freezeDate;	
    }	
	
    public void setEmail(String email) {	
        this.email = email;	
    }	
	
    public void setRefCode(String refCode) {	
        this.refCode = refCode;	
    }	
	
    public User(Role role) {	
        this();	
        if (role != null) {	
            User user = this;	
            user.roleCode = role.getRoleCode();	
            user.corpCode = role.getCorpCode();	
        }	
    }	
	
    public void setPwdQuestion3(String pwdQuestion3) {	
        this.pwdQuestion3 = pwdQuestion3;	
    }	
	
    @JsonIgnore	
    public String getRoleCode() {	
        return this.roleCode;	
    }	
	
    @Length(min=0, max=1, message="\u7ba1\u7406\u5458\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getMgrType() {	
        return this.mgrType;	
    }	
	
    public void setMobile(String mobile) {	
        this.mobile = mobile;	
    }	
	
    public void setLastLoginIp(String lastLoginIp) {	
        this.lastLoginIp = lastLoginIp;	
    }	
	
    public void setSign(String sign) {	
        this.sign = sign;	
    }	
	
    public void setPwdSecurityLevel(Integer pwdSecurityLevel) {	
        this.pwdSecurityLevel = pwdSecurityLevel;	
    }	
	
    @JsonIgnore	
    @Length(min=0, max=200, message="\u5bc6\u4fdd\u95ee\u98983\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPwdQuestion3() {	
        return this.pwdQuestion3;	
    }	
	
    public void setUserType(String userType) {	
        this.userType = userType;	
    }	
	
    public void setRoleList(List<Role> roleList) {	
        this.roleList = roleList;	
    }	
	
    public void setOldLastLoginDate(Date oldLastLoginDate) {	
        this.oldLastLoginDate = oldLastLoginDate;	
    }	
	
    public void setFreezeCause(String freezeCause) {	
        this.freezeCause = freezeCause;	
    }	
	
    public String getPwdUpdateRecord() {	
        return this.pwdUpdateRecord;	
    }	
	
    public String getAvatarUrl() {	
        if (StringUtils.isNotBlank((CharSequence)this.avatar)) {	
            return new StringBuilder().insert(0, "/ctxPath").append(this.avatar).toString();	
        }	
        String a = "/ctxPath/static/images/user";	
        a = StringUtils.isBlank((CharSequence)this.sex) ? new StringBuilder().insert(0, a).append(MGR_TYPE_CORP_ADMIN).toString() : new StringBuilder().insert(0, a).append(this.sex).toString();	
        a = new StringBuilder().insert(0, a).append(".jpg").toString();	
        return a;	
    }	
	
    @JsonIgnore	
    public List<UserRole> getUserRoleList() {	
        return this.userRoleList;	
    }	
	
    public void setOldLastLoginIp(String oldLastLoginIp) {	
        this.oldLastLoginIp = oldLastLoginIp;	
    }	
	
    public void setLastLoginDate(Date lastLoginDate) {	
        this.lastLoginDate = lastLoginDate;	
    }	
	
    public void setMobileImei(String mobileImei) {	
        this.mobileImei = mobileImei;	
    }	
	
    @Length(min=0, max=100, message="\u7ed1\u5b9a\u7684\u5fae\u4fe1\u53f7\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getWxOpenid() {	
        return this.wxOpenid;	
    }	
	
    public String getFreezeCause() {	
        return this.freezeCause;	
    }	
	
    @Length(min=0, max=100, message="\u6700\u540e\u767b\u9646IP\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getLastLoginIp() {	
        return this.lastLoginIp;	
    }	
	
    public String getCorpName_() {	
        return this.corpName;	
    }	
	
    public void setPwdQuestionAnswer(String pwdQuestionAnswer) {	
        this.pwdQuestionAnswer = pwdQuestionAnswer;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getOldLoginDate() {	
        if (this.oldLastLoginDate == null) {	
            return this.lastLoginDate;	
        }	
        return this.oldLastLoginDate;	
    }	
	
    public void setPwdQuestion(String pwdQuestion) {	
        this.pwdQuestion = pwdQuestion;	
    }	
	
    public void setExtend(Extend extend) {	
        this.extend = extend;	
    }	
	
    public String getCorpCode_() {	
        return this.corpCode;	
    }	
	
    public void setRefName(String refName) {	
        this.refName = refName;	
    }	
	
    public void setRoleCode(String roleCode) {	
        this.roleCode = roleCode;	
    }	
	
    public void setPwdUpdateDate(Date pwdUpdateDate) {	
        this.pwdUpdateDate = pwdUpdateDate;	
    }	
	
    public String getOldLastLoginIp() {	
        if (this.oldLastLoginIp == null) {	
            return this.lastLoginIp;	
        }	
        return this.oldLastLoginIp;	
    }	
	
    @Length(min=0, max=30, message="\u624b\u673a\u53f7\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 30 \u4e2a\u5b57\u7b26")	
    public String getMobile() {	
        return this.mobile;	
    }	
	
    @JsonIgnore	
    public List<UserDataScope> getUserDataScopeList() {	
        return this.userDataScopeList;	
    }	
	
    public void setUserCode(String userCode) {	
        this.userCode = userCode;	
    }	
	
    public void setCorpName_(String corpName) {	
        this.corpName = corpName;	
    }	
}	
	
