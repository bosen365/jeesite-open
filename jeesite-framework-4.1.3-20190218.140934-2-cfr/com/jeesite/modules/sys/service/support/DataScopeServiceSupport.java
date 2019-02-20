/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.sys.dao.RoleDataScopeDao;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.service.DataScopeService;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.jni.ArchLoaderException;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class DataScopeServiceSupport	
extends BaseService	
implements DataScopeService {	
    @Autowired	
    private UserDataScopeDao userDataScopeDao;	
    @Autowired	
    private RoleDataScopeDao roleDataScopeDao;	
	
    private /* synthetic */ void insertIfParentExistsByUser(String userCode, String ctrlType, String parentCtrlData, String ctrlData, String ctrlPermi) {	
        void a2;	
        if (StringUtils.isBlank((CharSequence)parentCtrlData)) {	
            return;	
        }	
        UserDataScope userDataScope = new UserDataScope();	
        void v0 = a2;	
        void v1 = a2;	
        v1.setUserCode(userCode);	
        v1.setCtrlType(ctrlType);	
        v0.setCtrlData(parentCtrlData);	
        v0.setCtrlPermi(ctrlPermi);	
        if (this.userDataScopeDao.findCount(a2) > 0L) {	
            a2.setCtrlData(ctrlData);	
            if (this.userDataScopeDao.findCount(a2) == 0L) {	
                this.userDataScopeDao.insert(a2);	
            }	
        }	
    }	
	
    private /* synthetic */ void insertIfParentExistsByUser(TreeEntity<?> entity, String ctrlType) {	
        UserDataScope a2;	
        UserDataScope a3;	
        if (StringUtils.isBlank((CharSequence)entity.getParentCode())) {	
            return;	
        }	
        UserDataScope userDataScope = new UserDataScope();	
        void v0 = a3;	
        v0.setCtrlType(ctrlType);	
        v0.setCtrlData(entity.getParentCode());	
        List<Object> a4 = this.userDataScopeDao.findList(a3);	
        Iterator<Object> iterator = a4.iterator();	
        Iterator<Object> iterator2 = iterator;	
        while (iterator2.hasNext()) {	
            UserDataScope userDataScope2 = (UserDataScope)iterator.next();	
            iterator2 = iterator;	
            this.insertIfParentExistsByUser(a2.getUserCode(), ctrlType, entity.getParentCode(), entity.getId(), "1");	
        }	
        UserDataScope userDataScope3 = a3 = new UserDataScope();	
        userDataScope3.setCtrlType(ctrlType);	
        userDataScope3.setCtrlData(entity.getParentCode());	
        a4 = this.userDataScopeDao.findList(a3);	
        iterator = a4.iterator();	
        Iterator<UserDataScope> iterator3 = iterator;	
        while (iterator3.hasNext()) {	
            a2 = (UserDataScope)iterator.next();	
            iterator3 = iterator;	
            this.insertIfParentExistsByUser(a2.getUserCode(), ctrlType, entity.getParentCode(), entity.getId(), "2");	
        }	
    }	
	
    private /* synthetic */ void insertIfParentExistsByRole(TreeEntity<?> entity, String ctrlType) {	
        Iterator<void> iterator;	
        void a2;	
        if (StringUtils.isBlank((CharSequence)entity.getParentCode())) {	
            return;	
        }	
        RoleDataScope roleDataScope = new RoleDataScope();	
        void v0 = a2;	
        v0.setCtrlType(ctrlType);	
        v0.setCtrlData(entity.getParentCode());	
        Iterator<void> iterator2 = iterator = this.roleDataScopeDao.findList(a2).iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            RoleDataScope roleDataScope2 = (RoleDataScope)iterator.next();	
            iterator2 = iterator;	
            this.insertIfParentExistsByRole(a3.getRoleCode(), ctrlType, entity.getParentCode(), entity.getId(), "1");	
        }	
    }	
	
    private /* synthetic */ void insertIfParentExistsByRole(String roleCode, String ctrlType, String parentCtrlData, String ctrlData, String ctrlPermi) {	
        void a2;	
        if (StringUtils.isBlank((CharSequence)parentCtrlData)) {	
            return;	
        }	
        RoleDataScope roleDataScope = new RoleDataScope();	
        void v0 = a2;	
        void v1 = a2;	
        v1.setRoleCode(roleCode);	
        v1.setCtrlType(ctrlType);	
        v0.setCtrlData(parentCtrlData);	
        v0.setCtrlPermi(ctrlPermi);	
        if (this.roleDataScopeDao.findCount(a2) > 0L) {	
            a2.setCtrlData(ctrlData);	
            if (this.roleDataScopeDao.findCount(a2) == 0L) {	
                this.roleDataScopeDao.insert(a2);	
            }	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void insertIfParentExists(TreeEntity<?> entity, String ctrlType) {	
        String a2;	
        if (Global.getConfigToBoolean("sys.user.dataScopeAuthIfParentExists", "false").booleanValue()) {	
            DataScopeServiceSupport dataScopeServiceSupport = this;	
            dataScopeServiceSupport.insertIfParentExistsByRole(entity, ctrlType);	
            dataScopeServiceSupport.insertIfParentExistsByUser(entity, ctrlType);	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a2 = entity.getCurrentUser().getUserCode()))) {	
            void a3;	
            UserDataScope userDataScope = new UserDataScope();	
            void v1 = a3;	
            void v2 = a3;	
            v2.setUserCode(a2);	
            v2.setCtrlType(ctrlType);	
            v1.setCtrlData(entity.getId());	
            v1.setCtrlPermi("1");	
            if (this.userDataScopeDao.findCount(a3) == 0L) {	
                UserUtils.removeCache(new StringBuilder().insert(0, "userDataScope_").append(ctrlType).append("1").toString());	
                this.userDataScopeDao.insert(a3);	
            }	
            a3.setCtrlPermi("2");	
            if (this.userDataScopeDao.findCount(a3) == 0L) {	
                UserUtils.removeCache(new StringBuilder().insert(0, "userDataScope_").append(ctrlType).append("2").toString());	
                this.userDataScopeDao.insert(a3);	
            }	
        }	
    }	
}	
	
