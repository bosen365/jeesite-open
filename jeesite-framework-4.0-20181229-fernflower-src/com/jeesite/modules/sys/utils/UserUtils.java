package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.codec.Md5Utils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.j;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import java.lang.reflect.Constructor;	
import java.lang.reflect.Method;	
import java.util.List;	
import java.util.Map;	
import java.util.concurrent.locks.Lock;	
import java.util.concurrent.locks.ReentrantLock;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.SecurityUtils;	
import org.apache.shiro.UnavailableSecurityManagerException;	
import org.apache.shiro.authz.AuthorizationInfo;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.SimpleSession;	
import org.apache.shiro.subject.Subject;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UserUtils {	
   private static Lock userCacheLock = new ReentrantLock();	
   private static final String USER_CACHE_LOGIN_CODE_ = "login_";	
   private static Logger logger = LoggerFactory.getLogger(UserUtils.class);	
   public static final String CACHE_MENU_LIST = "menuList";	
   private static final String USER_CACHE_CODE_ = "code_";	
   private static final String USER_CACHE = "userCache";	
   public static final String CACHE_AUTH_INFO = "authInfo";	
   private static final String USER_CACHE_USER_TYPE_AND_REF_CODE_ = "type_ref_";	
	
   public static User getByTypeAndRef(String userType, String refCode) {	
      if (!StringUtils.isBlank(userType) && !StringUtils.isBlank(refCode)) {	
         String a = (new StringBuilder()).insert(0, "type_ref_").append(userType).append("_").append(refCode).toString();	
         String a;	
         if ((a = (String)CacheUtils.get("userCache", a)) != null) {	
            return get(a);	
         } else {	
            userCacheLock.lock();	
	
            User a;	
            try {	
               if ((a = (String)CacheUtils.get("userCache", a)) == null) {	
                  a = new User();	
                  a.setUserType(userType);	
                  a.setRefCode(refCode);	
                  if ((a = returnUser(null.ALLATORIxDEMO().getByUserTypeAndRefCode(a))) == null) {	
                     return null;	
                  }	
	
                  User var5 = (User)a.clone();	
                  return var5;	
               }	
	
               a = get(a);	
            } finally {	
               userCacheLock.unlock();	
            }	
	
            return a;	
         }	
      } else {	
         return null;	
      }	
   }	
	
   public static String getSsoToken(String username) {	
      String a;	
      if (StringUtils.isBlank(a = Global.getConfig("shiro.sso.secretKey"))) {	
         logger.warn("属性文件 shiro.sso.secretKey 为空，请设置后再操作。");	
         return null;	
      } else {	
         if (ObjectUtils.toBoolean(Global.getConfig("shiro.sso.encryptKey", "true"))) {	
            a = Global.getPropertyDecodeAndEncode("f6ac0085022dd47f1fe58e0dff6f0883", "shiro.sso.secretKey", a);	
         }	
	
         return Md5Utils.md5((new StringBuilder()).insert(0, a).append(username).append(DateUtils.getDate("yyyyMMdd")).toString());	
      }	
   }	
	
   // $FF: synthetic method	
   private static User returnUser(User user) {	
      if (user == null) {	
         return null;	
      } else {	
         loadRefObj(user);	
         user.setRoleList(null.ALLATORIxDEMO().findListByUserCode(new Role(user)));	
         CacheUtils.put("userCache", (new StringBuilder()).insert(0, "code_").append(user.getId()).toString(), user);	
         CacheUtils.put("userCache", (new StringBuilder()).insert(0, "login_").append(user.getCorpCode()).append("_").append(user.getLoginCode()).toString(), user.getUserCode());	
         CacheUtils.put("userCache", (new StringBuilder()).insert(0, "type_ref_").append(user.getUserType()).append("_").append(user.getRefCode()).toString(), user.getUserCode());	
         return user;	
      }	
   }	
	
   public static User get(String userCode) {	
      if (StringUtils.isBlank(userCode)) {	
         return null;	
      } else {	
         HttpServletRequest a;	
         User a;	
         if ((a = ServletUtils.getRequest()) != null && (a = (User)a.getAttribute("__user__" + userCode)) != null) {	
            return a;	
         } else {	
            String a = (new StringBuilder()).insert(0, "code_").append(userCode).toString();	
            User a;	
            if ((a = (User)CacheUtils.get("userCache", a)) != null) {	
               return (User)a.clone();	
            } else {	
               userCacheLock.lock();	
	
               User a;	
               try {	
                  if ((a = (User)CacheUtils.get("userCache", a)) == null) {	
                     a = new User(userCode);	
                     a = returnUser(null.ALLATORIxDEMO().get(a));	
                     if (a != null) {	
                        a.setAttribute((new StringBuilder()).insert(0, "__user__").append(userCode).toString(), a);	
                     }	
	
                     User var5;	
                     if (a != null) {	
                        var5 = (User)a.clone();	
                        return var5;	
                     }	
	
                     var5 = null;	
                     return var5;	
                  }	
	
                  a = (User)a.clone();	
               } finally {	
                  userCacheLock.unlock();	
               }	
	
               return a;	
            }	
         }	
      }	
   }	
	
   public static User getUser() {	
      LoginInfo a;	
      User a;	
      return (a = getLoginInfo()) != null && (a = get(a.getId())) != null ? a : new User();	
   }	
	
   public static List getMenuList() {	
      return getMenuListByParentCode((String)null);	
   }	
	
   public static Object getCache(String key, Object defaultValue) {	
      Object a;	
      return (a = getCache(key)) != null ? a : defaultValue;	
   }	
	
   public static Session getSession() {	
      try {	
         Subject a;	
         Session a;	
         if ((a = (a = getSubject()).getSession(false)) == null) {	
            a = a.getSession();	
         }	
	
         if (a != null) {	
            return a;	
         }	
      } catch (UnavailableSecurityManagerException var2) {	
      } catch (InvalidSessionException var3) {	
      }	
	
      return new SimpleSession();	
   }	
	
   public static AuthorizationInfo getAuthInfo() {	
      return (AuthorizationInfo)getCache("authInfo");	
   }	
	
   public static List getMenuTree() {	
      List a = ListUtils.newArrayList();	
      List a = getMenuList();	
      null.ALLATORIxDEMO().convertChildList(a, a, "0");	
      return a;	
   }	
	
   public static void clearCache() {	
      removeCache("authInfo");	
      removeCache("menuList");	
      clearCache(getUser());	
   }	
	
   public static Object getCache(String key) {	
      return getSession().getAttribute(key);	
   }	
	
   public static void removeCache(String key) {	
      getSession().removeAttribute(key);	
   }	
	
   public static void putCache(String key, Object value) {	
      getSession().setAttribute(key, value);	
   }	
	
   public static Subject getSubject() {	
      return SecurityUtils.getSubject();	
   }	
	
   public static void loadRefObj(User user) {	
      String a;	
      if (user.getRefObj() == null && StringUtils.isNotBlank(user.getUserType()) && StringUtils.isNotBlank(user.getRefCode()) && StringUtils.isNotBlank(a = getUserTypeValue(user.getUserType(), "dao"))) {	
         try {	
            Object a;	
            Class[] var3;	
            int var4 = (var3 = (a = SpringUtils.getBean(a)).getClass().getInterfaces()).length;	
            int var5;	
            int var10000 = var5 = 0;	
	
            User var10;	
            Object[] var15;	
            Class[] var10002;	
            boolean var10004;	
            while(true) {	
               if (var10000 >= var4) {	
                  var10 = user;	
                  break;	
               }	
	
               MyBatisDao a;	
               if ((a = (MyBatisDao)var3[var5].getAnnotation(MyBatisDao.class)) != null) {	
                  Class var10001 = a.entity();	
                  var10002 = new Class[1];	
                  var10004 = true;	
                  var10002[0] = String.class;	
                  Constructor var11 = var10001.getConstructor(var10002);	
                  var15 = new Object[1];	
                  var10004 = true;	
                  var15[0] = user.getRefCode();	
                  user.setRefObj(var11.newInstance(var15));	
                  var10 = user;	
                  break;	
               }	
	
               ++var5;	
               var10000 = var5;	
            }	
	
            if (var10.getRefObj() != null) {	
               Class var12 = a.getClass();	
               String var13 = "get";	
               var10002 = new Class[1];	
               var10004 = true;	
               var10002[0] = Object.class;	
               Method var14 = var12.getMethod(var13, var10002);	
               var15 = new Object[1];	
               var10004 = true;	
               var15[0] = user.getRefObj();	
               Object a;	
               if ((a = var14.invoke(a, var15)) != null) {	
                  user.setRefObj(a);	
                  return;	
               }	
            }	
         } catch (Exception var8) {	
            var8.printStackTrace();	
         }	
      }	
	
   }	
	
   public static List getMenuListByParentCode(String parentCode) {	
      Object a;	
      if ((a = (Map)getCache("menuList")) == null) {	
         a = MapUtils.newConcurrentMap();	
         putCache("menuList", a);	
      }	
	
      List a = null;	
      if (parentCode != null) {	
         a = (List)((Map)a).get(parentCode);	
      }	
	
      if (a == null) {	
         Menu a;	
         (a = new Menu()).setParentCode(parentCode);	
         LoginInfo a;	
         if ((a = getLoginInfo()) != null) {	
            a.setSysCode(a.getParam("sysCode", "default"));	
         } else {	
            a.setSysCode("default");	
         }	
	
         String var10000;	
         User a;	
         if (User.isSuperAdmin((a = getUser()).getUserCode())) {	
            a.setWeight_gte(Menu.SUPER_ADMIN_GET_MENU_MIN_WEIGHT);	
            a = null.ALLATORIxDEMO().findList(a);	
            var10000 = parentCode;	
         } else if ("1".equals(a.getMgrType())) {	
            a.setRoleCode(Role.CORP_ADMIN_ROLE_CODE);	
            a = null.ALLATORIxDEMO().findByRoleCode(a);	
            var10000 = parentCode;	
         } else {	
            String a;	
            if (!"none".equals(a.getUserType()) && StringUtils.isNotBlank(a = Global.getConfig((new StringBuilder()).insert(0, "sys.user.defaultRoleCodes.").append(a.getUserType()).toString()))) {	
               a.setDefaultRoleCodes(StringUtils.split(a, ','));	
            }	
	
            a.setUserCode(a.getId());	
            a = null.ALLATORIxDEMO().findByUserCode(a);	
            var10000 = parentCode;	
         }	
	
         if (var10000 != null) {	
            ((Map)a).put(parentCode, a);	
         }	
      }	
	
      return a;	
   }	
	
   public static void clearCache(User user) {	
      CacheUtils.remove("userCache", (new StringBuilder()).insert(0, "code_").append(user.getUserCode()).toString());	
      CacheUtils.remove("userCache", (new StringBuilder()).insert(0, "login_").append(StringUtils.isNotBlank(user.getCorpCode()) ? (new StringBuilder()).insert(0, user.getCorpCode()).append("_").toString() : "").append(user.getLoginCode()).toString());	
      CacheUtils.remove("userCache", (new StringBuilder()).insert(0, "type_ref_").append(user.getUserType()).append("_").append(user.getRefCode()).toString());	
      clearFoxbpmCache();	
   }	
	
   public static String getUserTypeValue(String userType, String key) {	
      if (null.ALLATORIxDEMO() == null) {	
         throw new ServiceException("jeesite.yml中的user.userTypeMap配置不正确或没有定义。");	
      } else if ("none".equals(userType)) {	
         return null;	
      } else {	
         Map a;	
         if ((a = (Map)null.ALLATORIxDEMO().get(userType)) != null && a.get(key) != null) {	
            return (String)a.get(key);	
         } else {	
            logger.warn((new StringBuilder()).insert(0, "jeesite.yml中的user.userTypeMap没有配置“").append(userType).append("”用户类型的“").append(key).append("”值。").toString());	
            return null;	
         }	
      }	
   }	
	
   public static LoginInfo getLoginInfo() {	
      try {	
         LoginInfo a;	
         if ((a = (LoginInfo)getSubject().getPrincipal()) != null) {	
            return a;	
         }	
      } catch (UnavailableSecurityManagerException var2) {	
      } catch (InvalidSessionException var3) {	
      }	
	
      return null;	
   }	
	
   public static void clearFoxbpmCache() {	
      try {	
         Class a = Class.forName("org.foxbpm.engine.impl.cache.CacheUtil");	
	
         try {	
            String var10001 = "clearIdentityCache";	
            Class[] var10002 = new Class[0];	
            boolean var10004 = true;	
            Method var10000 = a.getMethod(var10001, var10002);	
            Object[] var4 = new Object[0];	
            var10004 = true;	
            var10000.invoke((Object)null, var4);	
         } catch (Exception var2) {	
            var2.printStackTrace();	
         }	
      } catch (ClassNotFoundException var3) {	
      }	
   }	
	
   public static List findCorpList() {	
      User a = new User();	
      return null.ALLATORIxDEMO().findCorpList(a);	
   }	
	
   public static User getByLoginCode(String loginCode) {	
      if (StringUtils.isBlank(loginCode)) {	
         return null;	
      } else {	
         String a = (new StringBuilder()).insert(0, "login_").append(loginCode).toString();	
         String a;	
         if ((a = (String)CacheUtils.get("userCache", a)) != null) {	
            return get(a);	
         } else {	
            userCacheLock.lock();	
	
            User var4;	
            try {	
               User a;	
               if ((a = (String)CacheUtils.get("userCache", a)) != null) {	
                  a = get(a);	
                  return a;	
               }	
	
               (a = new User()).setLoginCode(loginCode);	
               if ((a = returnUser(null.ALLATORIxDEMO().getByLoginCode(a))) == null) {	
                  return null;	
               }	
	
               var4 = (User)a.clone();	
            } finally {	
               userCacheLock.unlock();	
            }	
	
            return var4;	
         }	
      }	
   }	
}	
