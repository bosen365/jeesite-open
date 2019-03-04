package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.network.IpUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import com.jeesite.modules.sys.dao.UserDao;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.dao.UserRoleDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.entity.UserRole;	
import com.jeesite.modules.sys.utils.UserUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.regex.Pattern;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
import org.springframework.ui.Model;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class UserService extends CrudService {	
   @Autowired	
   private UserDataScopeDao userDataScopeDao;	
   @Autowired	
   private UserRoleDao userRoleDao;	
   private static Pattern upperCaseExp = Pattern.compile("[A-Z]");	
   private static Pattern numberExp = Pattern.compile("[0-9]");	
   private static Pattern specialExp = Pattern.compile("[~!@#$%\^&\*(C_+\{\}:"\|<>?`\-=\[\];\'\\,\./]");	
   private static Pattern lowerCaseExp = Pattern.compile("[a-z]");	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updatePassword(String userCode, String newPassword) {	
      User a;	
      if ((a = UserUtils.get(userCode)) == null) {	
         StringBuilder var10 = (new StringBuilder()).insert(0, userCode);	
         String var12 = "sys.user.userCodeNotExists";	
         String[] var10004 = new String[0];	
         boolean var10006 = true;	
         throw new ServiceException(var10.append(text(var12, var10004)).toString());	
      } else {	
         User a = new User();	
         a.setUserCode(a.getUserCode());	
         a.setCorpCode(a.getCorpCode_());	
         if (StringUtils.isBlank(newPassword)) {	
            newPassword = Global.getConfig("sys.user.initPassword");	
         }	
	
         int a;	
         if ((a = getPwdSecurityLevel(newPassword)) != 0) {	
            int a = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifyNotRepeatNum", "1"));	
            Object a;	
            String[] var10001;	
            boolean var10003;	
            if ((a = (List)JsonMapper.fromJson(a.getPwdUpdateRecord(), List.class)) == null) {	
               a = ListUtils.newArrayList();	
               if (a.getPwdUpdateDate() == null) {	
                  a.setPwdUpdateDate(new Date());	
               }	
	
               var10001 = new String[2];	
               var10003 = true;	
               var10001[0] = a.getPassword();	
               var10001[1] = DateUtils.formatDateTime(a.getPwdUpdateDate());	
               ((List)a).add(ListUtils.newArrayList(var10001));	
            }	
	
            Iterator var8 = ((List)a).iterator();	
	
            while(var8.hasNext()) {	
               List a = (List)var8.next();	
               if (validatePassword(newPassword, (String)a.get(0))) {	
                  String var10002 = "sys.user.passwordModifyNotRepeat";	
                  String[] var11 = new String[1];	
                  boolean var10005 = true;	
                  var11[0] = String.valueOf(a);	
                  throw new ServiceException(text(var10002, var11));	
               }	
            }	
	
            a.setPassword(encryptPassword(newPassword));	
            var10001 = new String[2];	
            var10003 = true;	
            var10001[0] = a.getPassword();	
            var10001[1] = DateUtils.getDateTime();	
            ((List)a).add(ListUtils.newArrayList(var10001));	
            if (((List)a).size() > a) {	
               ((List)a).subList(0, ((List)a).size() - a).clear();	
            }	
	
            a.setPwdUpdateRecord(JsonMapper.toJson(a));	
         } else {	
            a.setPassword(encryptPassword(newPassword));	
            a.setPwdUpdateRecord(a.getPwdUpdateRecord());	
         }	
	
         Global.assertDemoMode();	
         a.setPwdSecurityLevel(a);	
         a.setPwdUpdateDate(new Date());	
         ((UserDao)this.dao).updatePassword(a);	
         UserUtils.clearCache(a);	
      }	
   }	
	
   public Page findPage(Page page, User user) {	
      return super.findPage(page, user);	
   }	
	
   public List findCorpList(User user) {	
      return ((UserDao)this.dao).findCorpList(user);	
   }	
	
   public List findDataScopeList(UserDataScope userDataScope) {	
      return this.userDataScopeDao.findList(userDataScope);	
   }	
	
   public User get(User user) {	
      return (User)super.get(user);	
   }	
	
   public List findList(User user) {	
      return super.findList(user);	
   }	
	
   public List findListByRoleCode(User user) {	
      return ((UserDao)this.dao).findListByRoleCode(user);	
   }	
	
   public String checkLoginCode(String oldLoginCode, String loginCode) {	
      User a;	
      (a = new User()).setLoginCode(loginCode);	
      if (loginCode != null && loginCode.equals(oldLoginCode)) {	
         return "true";	
      } else {	
         return loginCode != null && this.getByLoginCode(a) == null ? "true" : "false";	
      }	
   }	
	
   public static String encryptPassword(String plainPassword) {	
      return BaseAuthorizingRealm.encryptPassword(plainPassword);	
   }	
	
   public User getByUserTypeAndRefCode(User user) {	
      return ((UserDao)this.dao).getByUserTypeAndRefCode(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateUserInfo(User user) {	
      user.preUpdate();	
      ((UserDao)this.dao).updateUserInfo(user);	
      UserUtils.clearCache(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(User user) {	
      if (user.getIsNewRecord()) {	
         if (StringUtils.isBlank(user.getUserCode())) {	
            this.genId(user, user.getLoginCode());	
         }	
	
         if (((UserDao)this.dao).get(user) != null) {	
            String var10000 = "sys.user.loginCodeExists";	
            String[] var10001 = new String[0];	
            boolean var10003 = true;	
            throw newValidationException(text(var10000, var10001));	
         }	
	
         if (StringUtils.isBlank(user.getPassword())) {	
            user.setPassword(encryptPassword(Global.getConfig("sys.user.initPassword")));	
            user.setPwdSecurityLevel(0);	
            user.setPwdUpdateDate(new Date());	
         }	
	
         if (StringUtils.isBlank(user.getUserType())) {	
            user.setUserType("employee");	
         }	
	
         if (StringUtils.isBlank(user.getMgrType())) {	
            user.setMgrType("0");	
         }	
      }	
	
      super.save(user);	
      UserUtils.clearCache(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void saveAuthDataScope(User user) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(user.getUserCode())) {	
         UserDataScope a = new UserDataScope();	
         a.setUserCode(user.getUserCode());	
         a.setCtrlPermi("2".equals(user.getMgrType()) ? "2" : "1");	
         this.userDataScopeDao.deleteByEntity(a);	
         List a;	
         Iterator var4 = (a = user.getUserDataScopeList()).iterator();	
	
         for(Iterator var10000 = var4; var10000.hasNext(); var10000 = var4) {	
            ((UserDataScope)var4.next()).setCtrlPermi(a.getCtrlPermi());	
         }	
	
         ListUtils.pageList(a, 100, new MethodCallback() {	
            public Object execute(Object... ax) {	
               UserService.this.userDataScopeDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         UserUtils.clearCache(user);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateMgrType(User user) {	
      user.preUpdate();	
      ((UserDao)this.dao).updateMgrType(user);	
      UserUtils.clearCache(user);	
   }	
	
   // $FF: synthetic method	
   private static int getPwdSecurityLevel(String newPassword) {	
      int a = 0;	
      if (!StringUtils.equals(newPassword, Global.getConfig("sys.user.initPassword"))) {	
         int a = newPassword.length() >= 8 ? 1 : 0;	
         int a = upperCaseExp.matcher(newPassword).find() ? 1 : 0;	
         int a = lowerCaseExp.matcher(newPassword).find() ? 1 : 0;	
         int a = numberExp.matcher(newPassword).find() ? 1 : 0;	
         int a = specialExp.matcher(newPassword).find() ? 1 : 0;	
         int a;	
         if ((a = a + a + a + a + a) == 0) {	
            a = 0;	
         } else if (a == 1) {	
            a = 1;	
         } else if (a == 2) {	
            a = 2;	
         } else if (a != 3 && a != 4) {	
            a = 4;	
         } else {	
            a = 3;	
         }	
	
         int a;	
         if ((a = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifySecurityLevel"))) > 0 && a > a) {	
            String var10002 = "sys.user.passwordModifySecurity&evel";	
            String[] var10003 = new String[0];	
            boolean var10005 = true;	
            throw new ServiceException(text(var10002, var10003));	
         }	
      }	
	
      return a;	
   }	
	
   public static String passwordModifyValid(User user, Model model) {	
      String a = null;	
      String a = Global.getConfig("sys.user.initPasswordModify");	
      String var10000;	
      String[] var10001;	
      boolean var10003;	
      if (!"0".equals(a) && Boolean.valueOf(validatePassword(Global.getConfig("sys.user.initPassword"), user.getPassword()))) {	
         var10000 = "sys.user.initPasswordModifyTip";	
         var10001 = new String[0];	
         var10003 = true;	
         String a = text(var10000, var10001);	
         if ("1".equals(a)) {	
            model.addAttribute("modifyPasswordTip", a);	
         } else if ("2".equals(a)) {	
            a = a;	
         }	
      }	
	
      Boolean a = Global.getConfig("sys.user.passwordModify");	
      if (a != null && !"0".equals(a)) {	
         int a = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifyCycle"));	
         long a = user.getPwdUpdateDate() == null ? 0L : DateUtils.pastDays(user.getPwdUpdateDate());	
         if (user.getPwdUpdateDate() == null || a >= (long)a) {	
            String a = null;	
            if (a == 0L) {	
               var10000 = "sys.user.initPasswordModifyTip";	
               var10001 = new String[0];	
               var10003 = true;	
               a = text(var10000, var10001);	
            } else {	
               var10000 = "sys.user.passwordModifyTip";	
               var10001 = new String[1];	
               var10003 = true;	
               var10001[0] = String.valueOf(a);	
               a = text(var10000, var10001);	
            }	
	
            if ("1".equals(a)) {	
               var10000 = a;	
               model.addAttribute("modifyPasswordTip", a);	
               return var10000 != null ? (new StringBuilder()).insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString() : null;	
            }	
	
            if ("2".equals(a)) {	
               a = a;	
            }	
         }	
      }	
	
      var10000 = a;	
      return var10000 != null ? (new StringBuilder()).insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString() : null;	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void saveAuth(User user) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(user.getUserCode())) {	
         UserRole a = new UserRole();	
         a.setUserCode(user.getUserCode());	
         this.userRoleDao.deleteByEntity(a);	
         List a = user.getUserRoleList();	
         Iterator var12;	
         if (!user.getCurrentUser().isAdmin()) {	
            List a = ListUtils.extractToList(a, "roleCode", (String)null, true);	
            UserDataScope a = new UserDataScope();	
            a.setUserCode(user.getCurrentUser().getUserCode());	
            a.setCtrlType(Role.class.getSimpleName());	
            String[] var10002 = new String[a.size()];	
            boolean var10004 = true;	
            a.setCtrlData_in((String[])a.toArray(var10002));	
            a.setCtrlPermi("2");	
            List var10000 = this.userDataScopeDao.findList(a);	
            a.clear();	
            Iterator var7;	
            var12 = var7 = var10000.iterator();	
	
            while(var12.hasNext()) {	
               UserDataScope a = (UserDataScope)var7.next();	
               UserRole a = new UserRole();	
               var12 = var7;	
               a.setUserCode(user.getUserCode());	
               a.setRoleCode(a.getCtrlData());	
               a.add(a);	
            }	
         } else {	
            Iterator var10;	
            var12 = var10 = a.iterator();	
	
            while(var12.hasNext()) {	
               UserRole a = (UserRole)var10.next();	
               var12 = var10;	
               a.setUserCode(user.getUserCode());	
            }	
         }	
	
         if (ListUtils.isNotEmpty(a)) {	
            this.userRoleDao.insertBatch(user.getUserRoleList());	
         }	
	
         UserUtils.clearCache(user);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateUserLoginInfo(User user) {	
      user.setOldLastLoginIp(user.getLastLoginIp());	
      user.setOldLastLoginDate(user.getLastLoginDate());	
      user.setLastLoginIp(IpUtils.getRemoteAddr(ServletUtils.getRequest()));	
      user.setLastLoginDate(new Date());	
      ((UserDao)this.dao).updateLoginInfo(user);	
   }	
	
   public static boolean validatePassword(String plainPassword, String password) {	
      return BaseAuthorizingRealm.validatePassword(plainPassword, password);	
   }	
	
   public User getByLoginCode(User user) {	
      return ((UserDao)this.dao).getByLoginCode(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(User user) {	
      super.delete(user);	
      UserUtils.clearCache(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateQuestion(User user) {	
      Global.assertDemoMode();	
      user.setPwdQuestUpdateDate(new Date());	
      ((UserDao)this.dao).updateQuestion(user);	
      UserUtils.clearCache(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(User user) {	
      super.updateStatus(user);	
      UserUtils.clearCache(user);	
   }	
}	
