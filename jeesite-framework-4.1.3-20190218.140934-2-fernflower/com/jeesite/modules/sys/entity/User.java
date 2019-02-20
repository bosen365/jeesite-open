package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.validator.PatternValue;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.validation.constraints.Email;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.pager.SortAttribute;	
	
@Table(	
   name = "${_prefix}sys_user",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = BaseEntity.class	
), @Column(	
   includeEntity = DataEntity.class	
), @Column(	
   name = "user_code",	
   attrName = "userCode",	
   label = "用户编码",	
   isPK = true	
), @Column(	
   name = "login_code",	
   attrName = "loginCode",	
   label = "登录账号",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "user_name",	
   attrName = "userName",	
   label = "用户昵称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "password",	
   attrName = "password",	
   label = "登录密码"	
), @Column(	
   name = "email",	
   attrName = "email",	
   label = "电子邮箱",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "mobile",	
   attrName = "mobile",	
   label = "手机号码",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "phone",	
   attrName = "phone",	
   label = "办公电话",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "sex",	
   attrName = "sex",	
   label = "用户性别"	
), @Column(	
   name = "avatar",	
   attrName = "avatar",	
   label = "头像路径"	
), @Column(	
   name = "sign",	
   attrName = "sign",	
   label = "个性签名"	
), @Column(	
   name = "wx_openid",	
   attrName = "wxOpenid",	
   label = "绑定的微信号"	
), @Column(	
   name = "mobile_imei",	
   attrName = "mobileImei",	
   label = "绑定的手机串号"	
), @Column(	
   name = "user_type",	
   attrName = "userType",	
   label = "用户类型",	
   isUpdate = false,	
   comment = "用户类型"	
), @Column(	
   name = "ref_code",	
   attrName = "refCode",	
   label = "用户类型引用编号",	
   isUpdate = false	
), @Column(	
   name = "ref_name",	
   attrName = "refName",	
   label = "用户类型引用姓名",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "mgr_type",	
   attrName = "mgrType",	
   label = "管理员类型",	
   isUpdate = false,	
   comment = "管理员类型（0非管理员 1系统管理员  2二级管理员）"	
), @Column(	
   name = "pwd_security_level",	
   attrName = "pwdSecurityLevel",	
   label = "密码安全级别",	
   isUpdate = false,	
   comment = "密码安全级别（0初始 1很弱 2弱 3安全 4很安全）"	
), @Column(	
   name = "pwd_update_date",	
   attrName = "pwdUpdateDate",	
   label = "密码最后更新时间",	
   isUpdate = false	
), @Column(	
   name = "pwd_update_record",	
   attrName = "pwdUpdateRecord",	
   label = "密码修改记录",	
   isUpdate = false	
), @Column(	
   name = "pwd_question",	
   attrName = "pwdQuestion",	
   label = "密保问题",	
   isUpdate = false	
), @Column(	
   name = "pwd_question_answer",	
   attrName = "pwdQuestionAnswer",	
   label = "密保问题答案",	
   isUpdate = false	
), @Column(	
   name = "pwd_question_2",	
   attrName = "pwdQuestion2",	
   label = "密保问题2",	
   isUpdate = false	
), @Column(	
   name = "pwd_question_answer_2",	
   attrName = "pwdQuestionAnswer2",	
   label = "密保问题答案2",	
   isUpdate = false	
), @Column(	
   name = "pwd_question_3",	
   attrName = "pwdQuestion3",	
   label = "密保问题3",	
   isUpdate = false	
), @Column(	
   name = "pwd_question_answer_3",	
   attrName = "pwdQuestionAnswer3",	
   label = "密保问题答案3",	
   isUpdate = false	
), @Column(	
   name = "pwd_quest_update_date",	
   attrName = "pwdQuestUpdateDate",	
   label = "密码问题修改时间",	
   isUpdate = false	
), @Column(	
   name = "last_login_ip",	
   attrName = "lastLoginIp",	
   label = "最后登陆IP",	
   isUpdate = false	
), @Column(	
   name = "last_login_date",	
   attrName = "lastLoginDate",	
   label = "最后登陆时间",	
   isUpdate = false	
), @Column(	
   name = "freeze_date",	
   attrName = "freezeDate",	
   label = "冻结时间",	
   isUpdate = false	
), @Column(	
   name = "freeze_cause",	
   attrName = "freezeCause",	
   label = "冻结原因",	
   isUpdate = false	
), @Column(	
   name = "user_weight",	
   attrName = "userWeight",	
   label = "用户权重",	
   comment = "用户权重（降序）"	
), @Column(	
   includeEntity = Extend.class,	
   attrName = "extend"	
)},	
   orderBy = "a.user_weight DESC, a.update_date DESC"	
)	
public class User extends DataEntity {	
   private String refName;	
   private static final long serialVersionUID = 1L;	
   public static final String USER_TYPE_EMPLOYEE = "employee";	
   private Integer pwdSecurityLevel;	
   private String oldLastLoginIp;	
   private Date pwdUpdateDate;	
   private String phone;	
   private Date lastLoginDate;	
   private Date oldLastLoginDate;	
   private String pwdQuestion;	
   public static final int PWD_SECURITY_LEVEL_VERYWEAK = 1;	
   private String loginCode;	
   private String pwdQuestion2;	
   private List roleList;	
   private String wxOpenid;	
   private String mobile;	
   private Object refObj;	
   public static final String USER_TYPE_PERSION = "persion";	
   public static final String USER_TYPE_MEMBER = "member";	
   public static final String USER_TYPE_EXPERT = "expert";	
   private String lastLoginIp;	
   public static final String USER_TYPE_BTYPE = "btype";	
   private String avatarBase64;	
   public static final int PWD_SECURITY_LEVEL_STRONG = 4;	
   private String avatar;	
   public static final String USER_TYPE_NONE = "none";	
   private Extend extend;	
   private String pwdQuestionAnswer3;	
   private String userType;	
   private String pwdQuestionAnswer2;	
   public static final String SUPER_ADMIN_CODE = Global.getProperty("user.superAdminCode", "system");	
   private String pwdQuestion3;	
   private String pwdUpdateRecord;	
   public static final String MGR_TYPE_CORP_ADMIN = "1";	
   private Date freezeDate;	
   private String freezeCause;	
   private String password;	
   private List userDataScopeList;	
   private String pwdQuestionAnswer;	
   private String sign;	
   public static final String MGR_TYPE_NOT_ADMIN = "0";	
   public static final String MGR_TYPE_SEC_ADMIN = "2";	
   public static final int PWD_SECURITY_LEVEL_WEAK = 2;	
   private String userName;	
   private String userCode;	
   private String mgrType;	
   private String refCode;	
   public static final int PWD_SECURITY_LEVEL_MEDIUM = 3;	
   private String mobileImei;	
   private List userRoleList;	
   private Integer userWeight;	
   public static final int PWD_SECURITY_LEVEL_INITPWD = 0;	
   private String roleCode;	
   private String sex;	
   private String email;	
   private Date pwdQuestUpdateDate;	
	
   public void setPhone(String phone) {	
      this.phone = phone;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "用户类型引用编号长度不能超过 64 个字符"	
   )	
   public String getRefCode() {	
      return this.refCode;	
   }	
	
   public void setMgrType(String mgrType) {	
      this.mgrType = mgrType;	
   }	
	
   public void setCorpName_(String corpName) {	
      this.corpName = corpName;	
   }	
	
   public void setPwdQuestion2(String pwdQuestion2) {	
      this.pwdQuestion2 = pwdQuestion2;	
   }	
	
   @JsonIgnore	
   public String getRoleCode() {	
      return this.roleCode;	
   }	
	
   @NotBlank(	
      message = "登录账号不能为空"	
   )	
   @PatternValue(	
      value = "web.validator.user.loginCode",	
      regexp = "[a-zA-Z0-9_一-龥]{4,20}",	
      message = "登录账号长度应为 4 到 20 个字符，并且只能包含字母、数字、下划线或中文"	
   )	
   public String getLoginCode() {	
      return this.loginCode;	
   }	
	
   @JsonIgnore	
   public boolean isSuperAdmin() {	
      return isSuperAdmin(this.userCode);	
   }	
	
   public void setPwdQuestion(String pwdQuestion) {	
      this.pwdQuestion = pwdQuestion;	
   }	
	
   public void setSex(String sex) {	
      this.sex = sex;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "绑定的微信号长度不能超过 100 个字符"	
   )	
   public String getWxOpenid() {	
      return this.wxOpenid;	
   }	
	
   public String getPwdUpdateRecord() {	
      return this.pwdUpdateRecord;	
   }	
	
   public void setPwdSecurityLevel(Integer pwdSecurityLevel) {	
      this.pwdSecurityLevel = pwdSecurityLevel;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "用户类型引用姓名长度不能超过 100 个字符"	
   )	
   public String getRefName() {	
      return this.refName;	
   }	
	
   public void setRoleList(List roleList) {	
      this.roleList = roleList;	
   }	
	
   public String getFreezeCause() {	
      return this.freezeCause;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 200,	
      message = "密保问题答案长度不能超过 200 个字符"	
   )	
   public String getPwdQuestionAnswer() {	
      return this.pwdQuestionAnswer;	
   }	
	
   public void setUserRoleString(String roleCodes) {	
      String[] a;	
      if ((a = StringUtils.split(roleCodes, ",")) != null) {	
         String[] var3 = a;	
         int var4 = a.length;	
	
         int var5;	
         for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
            String a;	
            if (StringUtils.isNotBlank(a = var3[var5]) && !Role.isAdmin(a)) {	
               UserRole a = new UserRole();	
               a.setRoleCode(a);	
               this.userRoleList.add(a);	
            }	
	
            ++var5;	
         }	
      }	
	
   }	
	
   public User(Role role) {	
      this();	
      if (role != null) {	
         this.roleCode = role.getRoleCode();	
         this.corpCode = role.getCorpCode();	
      }	
	
   }	
	
   public void setPwdUpdateRecord(String pwdUpdateRecord) {	
      this.pwdUpdateRecord = pwdUpdateRecord;	
   }	
	
   public String getCorpCode_() {	
      return this.corpCode;	
   }	
	
   @Length(	
      min = 0,	
      max = 30,	
      message = "办公电话长度不能超过 30 个字符"	
   )	
   public String getPhone() {	
      return this.phone;	
   }	
	
   public void setRefCode(String refCode) {	
      this.refCode = refCode;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "管理员类型长度不能超过 1 个字符"	
   )	
   public String getMgrType() {	
      return this.mgrType;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 200,	
      message = "密保问题3长度不能超过 200 个字符"	
   )	
   public String getPwdQuestion3() {	
      return this.pwdQuestion3;	
   }	
	
   public void setAvatar(String avatar) {	
      this.avatar = avatar;	
   }	
	
   public void setFreezeDate(Date freezeDate) {	
      this.freezeDate = freezeDate;	
   }	
	
   public void setFreezeCause(String freezeCause) {	
      this.freezeCause = freezeCause;	
   }	
	
   public void setEmail(String email) {	
      this.email = email;	
   }	
	
   public void setPwdQuestion3(String pwdQuestion3) {	
      this.pwdQuestion3 = pwdQuestion3;	
   }	
	
   public void setUserName(String userName) {	
      this.userName = userName;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 200,	
      message = "密保问题答案2长度不能超过 200 个字符"	
   )	
   public String getPwdQuestionAnswer2() {	
      return this.pwdQuestionAnswer2;	
   }	
	
   public Date getFreezeDate() {	
      return this.freezeDate;	
   }	
	
   public void setExtend(Extend extend) {	
      this.extend = extend;	
   }	
	
   @Length(	
      min = 0,	
      max = 600,	
      message = "头像路径长度不能超过 600 个字符"	
   )	
   public String getAvatar() {	
      return this.avatar;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "绑定的手机串号长度不能超过 100 个字符"	
   )	
   public String getMobileImei() {	
      return this.mobileImei;	
   }	
	
   @Length(	
      min = 0,	
      max = 30,	
      message = "手机号码长度不能超过 30 个字符"	
   )	
   public String getMobile() {	
      return this.mobile;	
   }	
	
   public Object getRefObj() {	
      return this.refObj;	
   }	
	
   public void setRefObj(Object refObj) {	
      this.refObj = refObj;	
   }	
	
   public String getAvatarBase64() {	
      return this.avatarBase64;	
   }	
	
   public String toString() {	
      return this.userCode;	
   }	
	
   public void setPassword(String password) {	
      this.password = password;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "性别长度不能超过 1 个字符"	
   )	
   public String getSex() {	
      return this.sex;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 200,	
      message = "密保问题答案3长度不能超过 200 个字符"	
   )	
   public String getPwdQuestionAnswer3() {	
      return this.pwdQuestionAnswer3;	
   }	
	
   public String getAvatarUrl() {	
      if (StringUtils.isNotBlank(this.avatar)) {	
         return (new StringBuilder()).insert(0, "/ctxPath").append(this.avatar).toString();	
      } else {	
         String a = "/ctxPath/static/images/user";	
         if (StringUtils.isBlank(this.sex)) {	
            a = (new StringBuilder()).insert(0, a).append("1").toString();	
         } else {	
            a = (new StringBuilder()).insert(0, a).append(this.sex).toString();	
         }	
	
         return (new StringBuilder()).insert(0, a).append(".jpg").toString();	
      }	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getLastLoginDate() {	
      return this.lastLoginDate;	
   }	
	
   @Email(	
      message = "邮箱格式不正确"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "邮箱长度不能超过 100 个字符"	
   )	
   public String getEmail() {	
      return this.email;	
   }	
	
   public void setLoginCode(String loginCode) {	
      this.loginCode = loginCode;	
   }	
	
   public void setPwdQuestionAnswer2(String pwdQuestionAnswer2) {	
      this.pwdQuestionAnswer2 = pwdQuestionAnswer2;	
   }	
	
   public void setPwdUpdateDate(Date pwdUpdateDate) {	
      this.pwdUpdateDate = pwdUpdateDate;	
   }	
	
   public static boolean isSuperAdmin(String userCode) {	
      return userCode != null && StringUtils.inString(userCode, SUPER_ADMIN_CODE.split(","));	
   }	
	
   public void setPwdQuestionAnswer3(String pwdQuestionAnswer3) {	
      this.pwdQuestionAnswer3 = pwdQuestionAnswer3;	
   }	
	
   public String getUserCode() {	
      return this.userCode;	
   }	
	
   public void setRefName(String refName) {	
      this.refName = refName;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 200,	
      message = "密保问题2长度不能超过 200 个字符"	
   )	
   public String getPwdQuestion2() {	
      return this.pwdQuestion2;	
   }	
	
   public void setUserDataScopeListJson(String jsonString) {	
      List a;	
      if ((a = (List)JsonMapper.fromJson(jsonString, List.class)) != null) {	
         Iterator var3;	
         Iterator var10000 = var3 = a.iterator();	
	
         while(var10000.hasNext()) {	
            Map a = (Map)var3.next();	
            UserDataScope a = new UserDataScope();	
            a.setUserCode(this.userCode);	
            a.setCtrlType((String)a.get("ctrlType"));	
            a.setCtrlData((String)a.get("ctrlData"));	
            var10000 = var3;	
            this.userDataScopeList.add(a);	
         }	
      }	
	
   }	
	
   public void setMobileImei(String mobileImei) {	
      this.mobileImei = mobileImei;	
   }	
	
   public void setUserType(String userType) {	
      this.userType = userType;	
   }	
	
   public void setOldLastLoginDate(Date oldLastLoginDate) {	
      this.oldLastLoginDate = oldLastLoginDate;	
   }	
	
   public String getCorpName_() {	
      return this.corpName;	
   }	
	
   public void setAvatarBase64(String avatarBase64) {	
      this.avatarBase64 = avatarBase64;	
   }	
	
   @JsonIgnore	
   public boolean isAdmin() {	
      return isSuperAdmin(this.userCode) || "1".equals(this.mgrType);	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "签名长度不能超过 100 个字符"	
   )	
   public String getSign() {	
      return this.sign;	
   }	
	
   public void setWxOpenid(String wxOpenid) {	
      this.wxOpenid = wxOpenid;	
   }	
	
   public User(String id) {	
      super(id);	
      this.userRoleList = ListUtils.newArrayList();	
      this.userDataScopeList = ListUtils.newArrayList();	
      this.roleList = ListUtils.newArrayList();	
   }	
	
   @JsonIgnore	
   public List getUserRoleList() {	
      return this.userRoleList;	
   }	
	
   public Extend getExtend() {	
      return this.extend;	
   }	
	
   public void setRoleCode(String roleCode) {	
      this.roleCode = roleCode;	
   }	
	
   public void setPwdQuestionAnswer(String pwdQuestionAnswer) {	
      this.pwdQuestionAnswer = pwdQuestionAnswer;	
   }	
	
   public void setUserCode(String userCode) {	
      this.userCode = userCode;	
   }	
	
   public void setMobile(String mobile) {	
      this.mobile = mobile;	
   }	
	
   @JsonIgnore	
   public Date getPwdUpdateDate() {	
      return this.pwdUpdateDate;	
   }	
	
   public void setCorpCode_(String corpCode) {	
      this.corpCode = corpCode;	
   }	
	
   public void setSign(String sign) {	
      this.sign = sign;	
   }	
	
   @Length(	
      min = 0,	
      max = 16,	
      message = "用户类型长度不能超过 16 个字符"	
   )	
   public String getUserType() {	
      return this.userType;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getOldLoginDate() {	
      return this.oldLastLoginDate == null ? this.lastLoginDate : this.oldLastLoginDate;	
   }	
	
   @JsonIgnore	
   public Date getPwdQuestUpdateDate() {	
      return this.pwdQuestUpdateDate;	
   }	
	
   public void setPwdQuestUpdateDate(Date pwdQuestUpdateDate) {	
      this.pwdQuestUpdateDate = pwdQuestUpdateDate;	
   }	
	
   public void setUserWeight(Integer userWeight) {	
      this.userWeight = userWeight;	
   }	
	
   @JsonIgnore	
   public String getPwdQuestion() {	
      return this.pwdQuestion;	
   }	
	
   @JsonIgnore	
   public List getUserDataScopeList() {	
      return this.userDataScopeList;	
   }	
	
   @JsonIgnore	
   public Integer getPwdSecurityLevel() {	
      return this.pwdSecurityLevel;	
   }	
	
   public void setLastLoginDate(Date lastLoginDate) {	
      this.lastLoginDate = lastLoginDate;	
   }	
	
   public String getOldLastLoginIp() {	
      return this.oldLastLoginIp == null ? this.lastLoginIp : this.oldLastLoginIp;	
   }	
	
   @JsonIgnore	
   @Length(	
      min = 0,	
      max = 100,	
      message = "密码长度不能超过 100 个字符"	
   )	
   public String getPassword() {	
      return this.password;	
   }	
	
   public Integer getUserWeight() {	
      return this.userWeight;	
   }	
	
   @JsonIgnore	
   public List getRoleList() {	
      return this.roleList;	
   }	
	
   @NotBlank(	
      message = "用户昵称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "用户昵称长度不能超过 100 个字符"	
   )	
   public String getUserName() {	
      return this.userName;	
   }	
	
   public User() {	
      this.userRoleList = ListUtils.newArrayList();	
      this.userDataScopeList = ListUtils.newArrayList();	
      this.roleList = ListUtils.newArrayList();	
   }	
	
   public void setLastLoginIp(String lastLoginIp) {	
      this.lastLoginIp = lastLoginIp;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "最后登陆IP长度不能超过 100 个字符"	
   )	
   public String getLastLoginIp() {	
      return this.lastLoginIp;	
   }	
	
   public void setOldLastLoginIp(String oldLastLoginIp) {	
      this.oldLastLoginIp = oldLastLoginIp;	
   }	
}	
