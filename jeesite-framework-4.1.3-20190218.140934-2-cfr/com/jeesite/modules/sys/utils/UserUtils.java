/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.Md5Utils	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.SetUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.apache.shiro.SecurityUtils	
 *  org.apache.shiro.UnavailableSecurityManagerException	
 *  org.apache.shiro.authz.AuthorizationInfo	
 *  org.apache.shiro.session.InvalidSessionException	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.subject.Subject	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.codec.Md5Utils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.StaticSession;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.F;	
import java.io.Serializable;	
import java.lang.annotation.Annotation;	
import java.lang.reflect.Constructor;	
import java.lang.reflect.Method;	
import java.util.ArrayList;	
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
import org.apache.shiro.subject.Subject;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UserUtils {	
    private static final String USER_CACHE_USER_TYPE_AND_REF_CODE_ = "type_ref_";	
    public static final String CACHE_MENU_LIST = "menuList";	
    private static Logger logger;	
    public static final String USER_CACHE = "userCache";	
    private static Lock userCacheLock;	
    private static final String USER_CACHE_USER_CODE_ = "code_";	
    public static final String CACHE_AUTH_INFO = "authInfo";	
    private static final String USER_CACHE_LOGIN_CODE_ = "login_";	
	
    public static List<Menu> getMenuTree() {	
        ArrayList a2 = ListUtils.newArrayList();	
        List<Menu> a3 = UserUtils.getMenuList();	
        F.ALLATORIxDEMO().convertChildList(a3, a2, "0");	
        return a3;	
    }	
	
    public static void removeCacheByKeyPrefix(String keyPrefix) {	
        LoginInfo a2 = UserUtils.getLoginInfo();	
        if (a2 != null) {	
            String a3 = a2.getId();	
            CacheUtils.removeByKeyPrefix(new StringBuilder().insert(0, "userCache_").append(a3).toString(), keyPrefix);	
        }	
    }	
	
    public static AuthorizationInfo getAuthInfo() {	
        Session a2 = UserUtils.getSession();	
        return (AuthorizationInfo)UserUtils.getCache(new StringBuilder().insert(0, "authIfo_").append(a2.getId()).toString());	
    }	
	
    public static void clearCache(User user) {	
        if (user == null || StringUtils.isBlank((CharSequence)user.getUserCode())) {	
            return;	
        }	
        User a2 = (User)((Object)CacheUtils.get(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_CODE_).append(user.getUserCode()).toString()));	
        if (a2 != null && StringUtils.isNotBlank((CharSequence)a2.getUserCode())) {	
            CacheUtils.remove(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_CODE_).append(a2.getUserCode()).toString());	
            CacheUtils.remove(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_LOGIN_CODE_).append(a2.getLoginCode()).toString());	
            if (StringUtils.isNotBlank((CharSequence)a2.getUserType()) && StringUtils.isNotBlank((CharSequence)a2.getRefCode())) {	
                CacheUtils.remove(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_TYPE_AND_REF_CODE_).append(a2.getUserType()).append("_").append(a2.getRefCode()).toString());	
            }	
            CacheUtils.removeCache(new StringBuilder().insert(0, "userCache_").append(a2.getUserCode()).toString());	
        }	
    }	
	
    public static User getUser() {	
        User a2;	
        LoginInfo a3 = UserUtils.getLoginInfo();	
        if (a3 != null && (a2 = UserUtils.get(a3.getId())) != null) {	
            return a2;	
        }	
        return new User();	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public static User getByLoginCode(String loginCode) {	
        if (StringUtils.isBlank((CharSequence)loginCode)) {	
            return null;	
        }	
        String a2 = new StringBuilder().insert(0, USER_CACHE_LOGIN_CODE_).append(loginCode).toString();	
        String a3 = CacheUtils.get(USER_CACHE, a2);	
        if (a3 != null) {	
            return UserUtils.get(a3);	
        }	
        userCacheLock.lock();	
        try {	
            a3 = CacheUtils.get(USER_CACHE, a2);	
            if (a3 != null) {	
                User user = UserUtils.get(a3);	
                return user;	
            }	
            User a4 = new User();	
            a4.setLoginCode(loginCode);	
            a4 = F.ALLATORIxDEMO().getByLoginCode(a4);	
            a4 = UserUtils.returnUser(a4);	
            if (a4 != null) {	
                User user = (User)a4.clone();	
                return user;	
            }	
        }	
        finally {	
            userCacheLock.unlock();	
        }	
        return null;	
    }	
	
    public static void removeCache(String key) {	
        LoginInfo a2 = UserUtils.getLoginInfo();	
        if (a2 != null) {	
            String a3 = a2.getId();	
            CacheUtils.remove(new StringBuilder().insert(0, "userCache_").append(a3).toString(), key);	
            return;	
        }	
        UserUtils.getSession().removeAttribute((Object)key);	
    }	
	
    static {	
        userCacheLock = new ReentrantLock();	
        logger = LoggerFactory.getLogger(UserUtils.class);	
    }	
	
    public static Subject getSubject() {	
        return SecurityUtils.getSubject();	
    }	
	
    private static /* synthetic */ User returnUser(User user) {	
        if (user == null) {	
            return null;	
        }	
        if (!"none".equals(user.getUserType())) {	
            UserUtils.loadRefObj(user);	
        }	
        user.setRoleList(F.ALLATORIxDEMO().findListByUserCode(new Role(user)));	
        CacheUtils.put(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_CODE_).append(user.getId()).toString(), user);	
        CacheUtils.put(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_LOGIN_CODE_).append(user.getLoginCode()).toString(), user.getUserCode());	
        if (StringUtils.isNotBlank((CharSequence)user.getUserType()) && StringUtils.isNotBlank((CharSequence)user.getRefCode())) {	
            CacheUtils.put(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_TYPE_AND_REF_CODE_).append(user.getUserType()).append("_").append(user.getRefCode()).toString(), user.getUserCode());	
        }	
        return user;	
    }	
	
    public static LoginInfo getLoginInfo() {	
        try {	
            Subject a2 = UserUtils.getSubject();	
            LoginInfo a3 = (LoginInfo)a2.getPrincipal();	
            if (a3 != null) {	
                return a3;	
            }	
        }	
        catch (UnavailableSecurityManagerException a2) {	
        }	
        catch (InvalidSessionException a2) {	
            // empty catch block	
        }	
        return null;	
    }	
	
    public static String getUserTypeValue(String userType, String key) {	
        if (F.ALLATORIxDEMO() == null) {	
            throw new ServiceException("jeesite.yml中的user.userTypeMap配置不正确或没有定义。");	
        }	
        if ("none".equals(userType)) {	
            return null;	
        }	
        Map a2 = (Map)F.ALLATORIxDEMO().get(userType);	
        if (a2 != null && a2.get(key) != null) {	
            return (String)a2.get(key);	
        }	
        logger.warn(new StringBuilder().insert(0, "jeesite.yml中的user.userTypeMap没有配置“").append(userType).append("”用户类型的“").append(key).append("”值。").toString());	
        return null;	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public static User get(String userCode) {	
        Object a2;	
        if (StringUtils.isBlank((CharSequence)userCode)) {	
            return null;	
        }	
        HttpServletRequest a3 = ServletUtils.getRequest();	
        if (a3 != null && (a2 = (User)a3.getAttribute("__user__" + userCode)) != null) {	
            return a2;	
        }	
        a2 = new StringBuilder().insert(0, USER_CACHE_USER_CODE_).append(userCode).toString();	
        User a4 = (User)((Object)CacheUtils.get(USER_CACHE, a2));	
        if (a4 != null) {	
            return (User)a4.clone();	
        }	
        userCacheLock.lock();	
        try {	
            a4 = (User)CacheUtils.get(USER_CACHE, a2);	
            if (a4 != null) {	
                User user = (User)a4.clone();	
                return user;	
            }	
            User a5 = new User(userCode);	
            a4 = F.ALLATORIxDEMO().get(a5);	
            a4 = UserUtils.returnUser(a4);	
            if (a3 != null) {	
                a3.setAttribute(new StringBuilder().insert(0, "__user__").append(userCode).toString(), (Object)a4);	
            }	
            if (a4 != null) {	
                User user = (User)a4.clone();	
                return user;	
            }	
            User user = null;	
            return user;	
        }	
        finally {	
            userCacheLock.unlock();	
        }	
    }	
	
    public static void clearCache() {	
        LoginInfo a2 = UserUtils.getLoginInfo();	
        if (a2 != null) {	
            String a3 = a2.getId();	
            User a4 = (User)((Object)CacheUtils.get(USER_CACHE, new StringBuilder().insert(0, USER_CACHE_USER_CODE_).append(a3).toString()));	
            if (a4 != null) {	
                UserUtils.clearCache(a4);	
            }	
        }	
    }	
	
    public static List<Menu> getMenuList() {	
        return UserUtils.getMenuListByParentCode(null);	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static List<Menu> getMenuListByParentCode(String parentCode) {	
        block4 : {	
            block3 : {	
                a = ObjectUtils.toStringIgnoreNull((Object)UserUtils.getSession().getAttribute((Object)"sysCode"), (String)"default");	
                a = new StringBuilder().insert(0, "menuList_").append(a).append("_").append(parentCode).toString();	
                a = (List<Menu>)UserUtils.getCache(a);	
                if (a != null) return a;	
                var5_4 = new Menu();	
                v0 = a;	
                v0.setSysCode(a);	
                v0.setParentCode(parentCode);	
                a = UserUtils.getUser();	
                if (!User.isSuperAdmin(a.getUserCode())) break block3;	
                a.setWeight_gte(Menu.SUPER_ADMIN_GET_MENU_MIN_WEIGHT);	
                a = F.ALLATORIxDEMO().findList((Menu)a);	
                v1 = a;	
                break block4;	
            }	
            if (!"none".equals(a.getUserType()) && StringUtils.isNotBlank((CharSequence)(a = Global.getConfig(new StringBuilder().insert(0, "sys.user.defaultRoleCodes.").append(a.getUserType()).toString())))) {	
                a.setDefaultRoleCodes(StringUtils.split((String)a, (char)','));	
            }	
            if (!"1".equals(a.getMgrType())) ** GOTO lbl27	
            if (a.getDefaultRoleCodes() != null) {	
                v2 = a;	
                a = SetUtils.newHashSet((Object[])v2.getDefaultRoleCodes());	
                a.add(Role.CORP_ADMIN_ROLE_CODE);	
                v2.setDefaultRoleCodes(a.toArray(new String[a.size()]));	
                v3 = a;	
            } else {	
                a.setDefaultRoleCodes(new String[]{Role.CORP_ADMIN_ROLE_CODE});	
lbl27: // 2 sources:	
                v3 = a;	
            }	
            v3.setUserCode(a.getId());	
            a = F.ALLATORIxDEMO().findByUserCode((Menu)a);	
            v1 = a;	
        }	
        UserUtils.putCache(v1, a);	
        return a;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static void loadRefObj(User user) {	
        if (user.getRefObj() != null) return;	
        if (!StringUtils.isNotBlank((CharSequence)user.getUserType())) return;	
        if (!StringUtils.isNotBlank((CharSequence)user.getRefCode())) return;	
        String a2 = UserUtils.getUserTypeValue(user.getUserType(), "dao");	
        if (!StringUtils.isNotBlank((CharSequence)a2)) return;	
        try {	
            User user2;	
            Object a3;	
            block6 : {	
                int n;	
                a3 = SpringUtils.getBean(a2);	
                Class<?>[] arrclass = a3.getClass().getInterfaces();	
                int n2 = arrclass.length;	
                int n3 = n = 0;	
                while (n3 < n2) {	
                    MyBatisDao a4 = arrclass[n].getAnnotation(MyBatisDao.class);	
                    if (a4 != null) {	
                        user.setRefObj(a4.entity().getConstructor(String.class).newInstance(user.getRefCode()));	
                        user2 = user;	
                        break block6;	
                    }	
                    n3 = ++n;	
                }	
                user2 = user;	
            }	
            if (user2.getRefObj() == null) return;	
            Object a5 = a3.getClass().getMethod("get", Object.class).invoke(a3, user.getRefObj());	
            if (a5 == null) return;	
            user.setRefObj(a5);	
            return;	
        }	
        catch (Exception a6) {	
            a6.printStackTrace();	
        }	
    }	
	
    public static String getSsoToken(String username) {	
        String a2 = Global.getConfig("shiro.sso.secretKey");	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            logger.warn("属性文件 shiro.sso.secretKey 为空，请设置后再操作。");	
            return null;	
        }	
        if (Global.getConfigToBoolean("shiro.sso.encryptKey", "true").booleanValue()) {	
            a2 = Global.getPropertyDecodeAndEncode("f6ac0085022dd47f1fe58e0dff6f0883", "shiro.sso.secretKey", a2);	
        }	
        return Md5Utils.md5((String)new StringBuilder().insert(0, a2).append(username).append(DateUtils.getDate((String)"yyyyMMdd")).toString());	
    }	
	
    public static Session getSession() {	
        try {	
            Subject a2 = UserUtils.getSubject();	
            Session a3 = a2.getSession(false);	
            if (a3 == null) {	
                a3 = a2.getSession(true);	
            }	
            if (a3 != null) {	
                return a3;	
            }	
        }	
        catch (UnavailableSecurityManagerException a2) {	
        }	
        catch (InvalidSessionException a2) {	
            // empty catch block	
        }	
        return StaticSession.INSTANCE;	
    }	
	
    public static <V> V getCache(String key, V defaultValue) {	
        V a2 = UserUtils.getCache(key);	
        if (a2 != null) {	
            return a2;	
        }	
        return defaultValue;	
    }	
	
    public static <V> void putCache(String key, V value) {	
        if (StringUtils.isNotBlank((CharSequence)key)) {	
            LoginInfo a2 = UserUtils.getLoginInfo();	
            if (a2 != null) {	
                String a3 = a2.getId();	
                CacheUtils.put(new StringBuilder().insert(0, "userCache_").append(a3).toString(), key, value);	
                return;	
            }	
            UserUtils.getSession().setAttribute((Object)key, value);	
        }	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    public static User getByTypeAndRef(String userType, String refCode) {	
        if (StringUtils.isBlank((CharSequence)userType) || StringUtils.isBlank((CharSequence)refCode)) {	
            return null;	
        }	
        String a2 = new StringBuilder().insert(0, USER_CACHE_USER_TYPE_AND_REF_CODE_).append(userType).append("_").append(refCode).toString();	
        String a3 = CacheUtils.get(USER_CACHE, a2);	
        if (a3 != null) {	
            return UserUtils.get(a3);	
        }	
        userCacheLock.lock();	
        try {	
            User a4;	
            a3 = CacheUtils.get(USER_CACHE, a2);	
            if (a3 != null) {	
                User user = UserUtils.get(a3);	
                return user;	
            }	
            User user = new User();	
            void v0 = a4;	
            v0.setUserType(userType);	
            v0.setRefCode(refCode);	
            a4 = F.ALLATORIxDEMO().getByUserTypeAndRefCode(a4);	
            a4 = UserUtils.returnUser(a4);	
            if (a4 != null) {	
                User user2 = (User)a4.clone();	
                return user2;	
            }	
        }	
        finally {	
            userCacheLock.unlock();	
        }	
        return null;	
    }	
	
    public static <V> V getCache(String key) {	
        LoginInfo a2 = UserUtils.getLoginInfo();	
        if (a2 != null) {	
            String a3 = a2.getId();	
            return (V)CacheUtils.get(new StringBuilder().insert(0, "userCache_").append(a3).toString(), key);	
        }	
        return (V)UserUtils.getSession().getAttribute((Object)key);	
    }	
}	
	
