package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.sys.dao.RoleDataScopeDao;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.entity.RoleDataScope;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class DataScopeService extends BaseService {	
   @Autowired	
   private RoleDataScopeDao roleDataScopeDao;	
   @Autowired	
   private UserDataScopeDao userDataScopeDao;	
	
   // $FF: synthetic method	
   private void insertIfParentExistsByRole(String roleCode, String ctrlType, String parentCtrlData, String ctrlData, String ctrlPermi) {	
      RoleDataScope a = new RoleDataScope();	
      a.setRoleCode(roleCode);	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(parentCtrlData);	
      a.setCtrlPermi(ctrlPermi);	
      if (this.roleDataScopeDao.findCount(a) > 0L) {	
         a.setCtrlData(ctrlData);	
         if (this.roleDataScopeDao.findCount(a) == 0L) {	
            this.roleDataScopeDao.insert(a);	
         }	
      }	
	
   }	
	
   public void insertIfParentExistsByRole(TreeEntity entity, String ctrlType) {	
      RoleDataScope a = new RoleDataScope();	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(entity.getParentCode());	
      Iterator var5;	
      Iterator var10000 = var5 = this.roleDataScopeDao.findList(a).iterator();	
	
      RoleDataScope a;	
      while(var10000.hasNext()) {	
         a = (RoleDataScope)var5.next();	
         var10000 = var5;	
         this.insertIfParentExistsByRole(a.getRoleCode(), ctrlType, entity.getParentCode(), entity.getId(), "1");	
      }	
	
      a = new RoleDataScope();	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(entity.getParentCode());	
      var10000 = var5 = this.roleDataScopeDao.findList(a).iterator();	
	
      while(var10000.hasNext()) {	
         a = (RoleDataScope)var5.next();	
         var10000 = var5;	
         this.insertIfParentExistsByRole(a.getRoleCode(), ctrlType, entity.getParentCode(), entity.getId(), "2");	
      }	
	
   }	
	
   // $FF: synthetic method	
   private void insertIfParentExistsByUser(String userCode, String ctrlType, String parentCtrlData, String ctrlData, String ctrlPermi) {	
      UserDataScope a = new UserDataScope();	
      a.setUserCode(userCode);	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(parentCtrlData);	
      a.setCtrlPermi(ctrlPermi);	
      if (this.userDataScopeDao.findCount(a) > 0L) {	
         a.setCtrlData(ctrlData);	
         if (this.userDataScopeDao.findCount(a) == 0L) {	
            this.userDataScopeDao.insert(a);	
         }	
      }	
	
   }	
	
   public void insertIfParentExists(TreeEntity entity, String ctrlType) {	
      this.insertIfParentExistsByRole(entity, ctrlType);	
      this.insertIfParentExistsByUser(entity, ctrlType);	
      UserDataScope a = new UserDataScope();	
      a.setUserCode(entity.getCurrentUser().getUserCode());	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(entity.getId());	
      a.setCtrlPermi("1");	
      if (this.userDataScopeDao.findCount(a) == 0L) {	
         this.userDataScopeDao.insert(a);	
         UserUtils.removeCache((new StringBuilder()).insert(0, "userDataScope_").append(ctrlType).append("1").toString());	
      }	
	
      a.setCtrlPermi("2");	
      if (this.userDataScopeDao.findCount(a) == 0L) {	
         this.userDataScopeDao.insert(a);	
         UserUtils.removeCache((new StringBuilder()).insert(0, "userDataScope_").append(ctrlType).append("2").toString());	
      }	
	
   }	
	
   public void insertIfParentExistsByUser(TreeEntity entity, String ctrlType) {	
      UserDataScope a = new UserDataScope();	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(entity.getParentCode());	
      Iterator var5;	
      Iterator var10000 = var5 = this.userDataScopeDao.findList(a).iterator();	
	
      UserDataScope a;	
      while(var10000.hasNext()) {	
         a = (UserDataScope)var5.next();	
         var10000 = var5;	
         this.insertIfParentExistsByUser(a.getUserCode(), ctrlType, entity.getParentCode(), entity.getId(), "1");	
      }	
	
      a = new UserDataScope();	
      a.setCtrlType(ctrlType);	
      a.setCtrlData(entity.getParentCode());	
      var10000 = var5 = this.userDataScopeDao.findList(a).iterator();	
	
      while(var10000.hasNext()) {	
         a = (UserDataScope)var5.next();	
         var10000 = var5;	
         this.insertIfParentExistsByUser(a.getUserCode(), ctrlType, entity.getParentCode(), entity.getId(), "2");	
      }	
	
   }	
}	
