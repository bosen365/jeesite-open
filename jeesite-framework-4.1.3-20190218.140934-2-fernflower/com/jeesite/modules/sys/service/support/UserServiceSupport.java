package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.network.IpUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.dao.UserDao;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.dao.UserRoleDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.entity.UserRole;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public class UserServiceSupport extends CrudService implements UserService {	
   @Autowired	
   private UserDataScopeDao userDataScopeDao;	
   @Autowired	
   private UserRoleDao userRoleDao;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateUserInfo(User user) {	
      User var10000;	
      label15: {	
         String a;	
         if (StringUtils.isNotBlank(a = user.getAvatarBase64())) {	
            if ("EMPTY".equals(a)) {	
               var10000 = user;	
               user.setAvatar("");	
               break label15;	
            }	
	
            String a;	
            FileUtils.writeToFileByImageBase64(Global.getUserfilesBaseDir(a = (new StringBuilder()).insert(0, "avatar/").append(user.getCorpCode()).append("/").append(user.getUserType()).append("/").append(user.getUserCode()).append(".").append(FileUtils.getFileExtensionByImageBase64(a)).toString()), a);	
            user.setAvatar("/userfiles/" + a);	
         }	
	
         var10000 = user;	
      }	
	
      var10000.preUpdate();	
      ((UserDao)this.dao).updateUserInfo(user);	
      UserUtils.clearCache(user);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updatePassword(String userCode, String newPassword) {	
      User a;	
      if ((a = UserUtils.get(userCode)) == null) {	
         StringBuilder var10002 = (new StringBuilder()).insert(0, userCode);	
         String var11 = "sys.user.userCodeNotExists";	
         String[] var10005 = new String[0];	
         boolean var10007 = true;	
         throw new ServiceException(var10002.append(this.text(var11, var10005)).toString());	
      } else {	
         User a = new User();	
         a.setUserCode(a.getUserCode());	
         a.setCorpCode(a.getCorpCode_());	
         if (StringUtils.isBlank(newPassword)) {	
            newPassword = Global.getConfig("sys.user.initPassword");	
         }	
	
         int a;	
         if ((a = PwdUtils.getPwdSecurityLevel(newPassword)) != 0) {	
            int a = Global.getConfigToInteger("sys.user.passwordModifyNotRepeatNum", "1");	
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
               if (PwdUtils.validatePassword(newPassword, (String)a.get(0))) {	
                  String var10 = "sys.user.passwordModifyNotRepeat";	
                  String[] var10004 = new String[1];	
                  boolean var10006 = true;	
                  var10004[0] = String.valueOf(a);	
                  throw new ServiceException(this.text(var10, var10004));	
               }	
            }	
	
            a.setPassword(PwdUtils.encryptPassword(newPassword));	
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
            a.setPassword(PwdUtils.encryptPassword(newPassword));	
            a.setPwdUpdateRecord(a.getPwdUpdateRecord());	
         }	
	
         Global.assertDemoMode();	
         a.setPwdSecurityLevel(a);	
         a.setPwdUpdateDate(new Date());	
         ((UserDao)this.dao).updatePassword(a);	
         UserUtils.clearCache(a);	
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
	
   public List findDataScopeList(UserDataScope userDataScope) {	
      return this.userDataScopeDao.findList(userDataScope);	
   }	
	
   public User getByUserTypeAndRefCode(User user) {	
      return ((UserDao)this.dao).getByUserTypeAndRefCode(user);	
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
            String var10001 = "sys.user.loginCodeExists";	
            String[] var10002 = new String[0];	
            boolean var10004 = true;	
            throw newValidationException(this.text(var10001, var10002));	
         }	
	
         String a;	
         if (StringUtils.isBlank(a = user.getPassword())) {	
            a = Global.getConfig("sys.user.initPassword");	
         }	
	
         user.setPassword(PwdUtils.encryptPassword(a));	
         user.setPwdSecurityLevel(PwdUtils.getPwdSecurityLevel(a));	
         user.setPwdUpdateDate(new Date());	
         if (StringUtils.isBlank(user.getUserType())) {	
            user.setUserType("none");	
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
         List a = user.getUserDataScopeList();	
         a.forEach((e) -> {	
            e.setCtrlPermi(a.getCtrlPermi());	
         });	
         ListUtils.pageList(a, 100, new MethodCallback() {	
            public Object execute(Object... ax) {	
               UserServiceSupport.this.userDataScopeDao.insertBatch((List)ax[0]);	
               return null;	
            }	
         });	
         UserUtils.clearCache(user);	
      }	
   }	
	
   public List findList(User user) {	
      return super.findList(user);	
   }	
	
   public User getByLoginCode(User user) {	
      return ((UserDao)this.dao).getByLoginCode(user);	
   }	
	
   public User get(User user) {	
      return (User)super.get(user);	
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
   public void updateQuestion(User user) {	
      Global.assertDemoMode();	
      user.setPwdQuestUpdateDate(new Date());	
      ((UserDao)this.dao).updateQuestion(user);	
      UserUtils.clearCache(user);	
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
   public void updateStatus(User user) {	
      super.updateStatus(user);	
      UserUtils.clearCache(user);	
   }	
	
   public Page findPage(User user) {	
      return super.findPage(user);	
   }	
	
   public List findCorpList(User user) {	
      return ((UserDao)this.dao).findCorpList(user);	
   }	
}	
