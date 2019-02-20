/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.callback.MethodCallback	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  com.jeesite.common.network.IpUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
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
import com.jeesite.modules.sys.service.support.E;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.function.Consumer;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class UserServiceSupport	
extends CrudService<UserDao, User>	
implements UserService {	
    @Autowired	
    private UserDataScopeDao userDataScopeDao;	
    @Autowired	
    private UserRoleDao userRoleDao;	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @Transactional(readOnly=false)	
    @Override	
    public void updateUserInfo(User user) {	
        a = user.getAvatarBase64();	
        if (!StringUtils.isNotBlank((CharSequence)a)) ** GOTO lbl11	
        if ("EMPTY".equals(a)) {	
            v0 = user;	
            v1 = v0;	
            v0.setAvatar("");	
        } else {	
            a = new StringBuilder().insert(0, "avatar/").append(user.getCorpCode()).append("/").append(user.getUserType()).append("/").append(user.getUserCode()).append(".").append(FileUtils.getFileExtensionByImageBase64((String)a)).toString();	
            FileUtils.writeToFileByImageBase64((String)Global.getUserfilesBaseDir(a), (String)a);	
            user.setAvatar("/userfiles/" + a);	
lbl11: // 2 sources:	
            v1 = user;	
        }	
        v1.preUpdate();	
        UserUtils.clearCache(user);	
        ((UserDao)this.dao).updateUserInfo(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updatePassword(String userCode, String newPassword) {	
        void a2;	
        int a3;	
        User a4 = UserUtils.get(userCode);	
        if (a4 == null) {	
            throw new ServiceException(new StringBuilder().insert(0, userCode).append(this.text("sys.user.userCodeNotExists", new String[0])).toString());	
        }	
        User user = new User();	
        void v0 = a2;	
        v0.setUserCode(a4.getUserCode());	
        v0.setCorpCode(a4.getCorpCode_());	
        if (StringUtils.isBlank((CharSequence)newPassword)) {	
            newPassword = Global.getConfig("sys.user.initPassword");	
        }	
        if ((a3 = PwdUtils.getPwdSecurityLevel(newPassword)) != 0) {	
            int a5 = Global.getConfigToInteger("sys.user.passwordModifyNotRepeatNum", "1");	
            List a6 = (List)JsonMapper.fromJson((String)a4.getPwdUpdateRecord(), List.class);	
            if (a6 == null) {	
                a6 = ListUtils.newArrayList();	
                if (a4.getPwdUpdateDate() == null) {	
                    a4.setPwdUpdateDate(new Date());	
                }	
                a6.add(ListUtils.newArrayList((Object[])new String[]{a4.getPassword(), DateUtils.formatDateTime((Date)a4.getPwdUpdateDate())}));	
            }	
            for (List a7 : a6) {	
                if (!PwdUtils.validatePassword(newPassword, (String)a7.get(0))) continue;	
                throw new ServiceException(this.text("sys.user.passwordModifyNotRepeat", String.valueOf(a5)));	
            }	
            a2.setPassword(PwdUtils.encryptPassword(newPassword));	
            a6.add(ListUtils.newArrayList((Object[])new String[]{a2.getPassword(), DateUtils.getDateTime()}));	
            if (a6.size() > a5) {	
                List list = a6;	
                list.subList(0, list.size() - a5).clear();	
            }	
            a2.setPwdUpdateRecord(JsonMapper.toJson((Object)a6));	
        } else {	
            a2.setPassword(PwdUtils.encryptPassword(newPassword));	
            a2.setPwdUpdateRecord(a4.getPwdUpdateRecord());	
        }	
        Global.assertDemoMode();	
        void v2 = a2;	
        v2.setPwdSecurityLevel(a3);	
        v2.setPwdUpdateDate(new Date());	
        UserUtils.clearCache((User)a2);	
        ((UserDao)this.dao).updatePassword((User)a2);	
    }	
	
    private static /* synthetic */ void lambda$saveAuthDataScope$0(UserDataScope where, UserDataScope e2) {	
        e2.setCtrlPermi(where.getCtrlPermi());	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateMgrType(User user) {	
        user.preUpdate();	
        UserUtils.clearCache(user);	
        ((UserDao)this.dao).updateMgrType(user);	
    }	
	
    @Override	
    public List<UserDataScope> findDataScopeList(UserDataScope userDataScope) {	
        return this.userDataScopeDao.findList(userDataScope);	
    }	
	
    static /* synthetic */ UserDataScopeDao access$000(UserServiceSupport x0) {	
        return x0.userDataScopeDao;	
    }	
	
    @Override	
    public User getByUserTypeAndRefCode(User user) {	
        return ((UserDao)this.dao).getByUserTypeAndRefCode(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(User user) {	
        if (user.getIsNewRecord()) {	
            if (StringUtils.isBlank((CharSequence)user.getUserCode())) {	
                User user2 = user;	
                this.genId(user2, user2.getLoginCode());	
            }	
            if (((UserDao)this.dao).get(user) != null) {	
                throw UserServiceSupport.newValidationException(this.text("sys.user.loginCodeExists", new String[0]));	
            }	
            String a2 = user.getPassword();	
            if (StringUtils.isBlank((CharSequence)a2)) {	
                a2 = Global.getConfig("sys.user.initPassword");	
            }	
            User user3 = user;	
            User user4 = user;	
            user4.setPassword(PwdUtils.encryptPassword(a2));	
            user3.setPwdSecurityLevel(PwdUtils.getPwdSecurityLevel(a2));	
            User user5 = user;	
            user4.setPwdUpdateDate(new Date());	
            if (StringUtils.isBlank((CharSequence)user3.getUserType())) {	
                user.setUserType("none");	
            }	
            if (StringUtils.isBlank((CharSequence)user.getMgrType())) {	
                user.setMgrType("0");	
            }	
        }	
        super.save(user);	
        UserUtils.clearCache(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuthDataScope(User user) {	
        void a2;	
        Global.assertDemoMode();	
        if (StringUtils.isBlank((CharSequence)user.getUserCode())) {	
            return;	
        }	
        UserDataScope userDataScope = new UserDataScope();	
        void v0 = a2;	
        v0.setUserCode(user.getUserCode());	
        v0.setCtrlPermi("2".equals(user.getMgrType()) ? "2" : "1");	
        List<UserDataScope> a3 = user.getUserDataScopeList();	
        this.userDataScopeDao.deleteByEntity(a2);	
        a3.forEach(arg_0 -> UserServiceSupport.lambda$saveAuthDataScope$0((UserDataScope)a2, arg_0));	
        ListUtils.pageList(a3, (int)100, (MethodCallback)new E(this));	
        UserUtils.clearCache(user);	
    }	
	
    @Override	
    public List<User> findList(User user) {	
        return super.findList(user);	
    }	
	
    @Override	
    public User getByLoginCode(User user) {	
        return ((UserDao)this.dao).getByLoginCode(user);	
    }	
	
    @Override	
    public User get(User user) {	
        return super.get(user);	
    }	
	
    @Override	
    public List<User> findListByRoleCode(User user) {	
        return ((UserDao)this.dao).findListByRoleCode(user);	
    }	
	
    @Override	
    public String checkLoginCode(String oldLoginCode, String loginCode) {	
        User a2 = new User();	
        String string = loginCode;	
        a2.setLoginCode(string);	
        if (string != null && loginCode.equals(oldLoginCode)) {	
            return "true";	
        }	
        if (loginCode != null && this.getByLoginCode(a2) == null) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void saveAuth(User user) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank((CharSequence)user.getUserCode())) {	
            return;	
        }	
        UserRole a2 = new UserRole();	
        a2.setUserCode(user.getUserCode());	
        List<UserRole> a3 = user.getUserRoleList();	
        this.userRoleDao.deleteByEntity(a2);	
        if (!user.getCurrentUser().isAdmin()) {	
            Iterator<void> iterator;	
            void a4;	
            List a5 = ListUtils.extractToList(a3, (String)"roleCode", null, (boolean)true);	
            UserDataScope userDataScope = new UserDataScope();	
            List list = a5;	
            void v1 = a4;	
            a4.setUserCode(user.getCurrentUser().getUserCode());	
            v1.setCtrlType(Role.class.getSimpleName());	
            v1.setCtrlData_in(list.toArray(new String[list.size()]));	
            a4.setCtrlPermi("2");	
            a3.clear();	
            Iterator<void> iterator2 = iterator = this.userDataScopeDao.findList(a4).iterator();	
            while (iterator2.hasNext()) {	
                void a6;	
                UserDataScope a7 = (UserDataScope)iterator.next();	
                UserRole userRole = new UserRole();	
                iterator2 = iterator;	
                void v3 = a6;	
                v3.setUserCode(user.getUserCode());	
                v3.setRoleCode(a7.getCtrlData());	
                a3.add((UserRole)v3);	
            }	
        } else {	
            Iterator<UserRole> a5;	
            Iterator<UserRole> iterator = a5 = a3.iterator();	
            while (iterator.hasNext()) {	
                UserRole a4 = a5.next();	
                iterator = a5;	
                a4.setUserCode(user.getUserCode());	
            }	
        }	
        if (ListUtils.isNotEmpty(a3)) {	
            this.userRoleDao.insertBatch(user.getUserRoleList());	
        }	
        UserUtils.clearCache(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateQuestion(User user) {	
        Global.assertDemoMode();	
        user.setPwdQuestUpdateDate(new Date());	
        UserUtils.clearCache(user);	
        ((UserDao)this.dao).updateQuestion(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateUserLoginInfo(User user) {	
        User user2 = user;	
        user2.setOldLastLoginIp(user2.getLastLoginIp());	
        user2.setOldLastLoginDate(user2.getLastLoginDate());	
        user2.setLastLoginIp(IpUtils.getRemoteAddr((HttpServletRequest)ServletUtils.getRequest()));	
        User user3 = user;	
        user2.setLastLoginDate(new Date());	
        ((UserDao)this.dao).updateLoginInfo(user);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(User user) {	
        User user2 = user;	
        super.delete(user2);	
        UserUtils.clearCache(user2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(User user) {	
        User user2 = user;	
        super.updateStatus(user2);	
        UserUtils.clearCache(user2);	
    }	
	
    @Override	
    public Page<User> findPage(User user) {	
        return super.findPage(user);	
    }	
	
    @Override	
    public List<User> findCorpList(User user) {	
        return ((UserDao)this.dao).findCorpList(user);	
    }	
}	
	
