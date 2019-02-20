package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.e.F;	
import com.jeesite.common.service.api.CrudServiceApi;	
import org.hyperic.jni.ArchLoaderException;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public abstract class CrudService extends QueryService implements CrudServiceApi {	
   private static long num = 0L;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(DataEntity entity) {	
      this.preSave(entity);	
      if (entity.getIsNewRecord()) {	
         this.insert(entity);	
      } else {	
         this.update(entity);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(DataEntity entity) {	
      if (!StringUtils.isBlank(entity.getId())) {	
         Global.assertDemoMode();	
         ((CrudDao)this.dao).delete(entity);	
      }	
   }	
	
   protected void preSave(DataEntity entity) {	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void insert(DataEntity entity) {	
      Global.assertDemoMode();	
      Table a;	
      if (("0".equals(F.ALLATORIxDEMO().get("type")) || "9".equals(F.ALLATORIxDEMO().get("type"))) && (a = (Table)entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
         if (num == 0L) {	
            num = this.findCount(this.newEntity());	
         }	
	
         if (++num > 100L) {	
            return;	
         }	
      }	
	
      ((CrudDao)this.dao).insert(entity);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(DataEntity entity) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(entity.getId())) {	
         ((CrudDao)this.dao).updateStatus(entity);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void update(DataEntity entity) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(entity.getId())) {	
         ((CrudDao)this.dao).update(entity);	
      }	
   }	
}	
