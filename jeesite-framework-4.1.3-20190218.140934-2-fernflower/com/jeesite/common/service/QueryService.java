package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.service.api.QueryServiceApi;	
import com.jeesite.modules.gen.service.M;	
import java.lang.reflect.Constructor;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ValidationException;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public abstract class QueryService extends BaseService implements QueryServiceApi {	
   protected Class entityClass;	
   @Autowired	
   protected QueryDao dao;	
	
   public static ValidationException newValidationException(String message) {	
      return new ValidationException((String)StringUtils.defaultIfBlank(message, "编码已存在"));	
   }	
	
   public DataEntity get(String id, boolean isNewRecord) {	
      Class[] var10001 = new Class[1];	
      boolean var10003 = true;	
      var10001[0] = String.class;	
      Object[] var10002 = new Object[1];	
      boolean var10004 = true;	
      var10002[0] = id;	
      return this.get(var10001, var10002, isNewRecord);	
   }	
	
   protected DataEntity newEntity() {	
      DataEntity a = null;	
	
      try {	
         a = (DataEntity)this.entityClass.newInstance();	
         return a;	
      } catch (Exception var3) {	
         throw new ServiceException(var3);	
      }	
   }	
	
   public DataEntity get(DataEntity entity) {	
      return (DataEntity)this.dao.get(entity);	
   }	
	
   public QueryService() {	
      this.setEntityClass(ReflectUtils.getClassGenricType(this.getClass(), 1));	
   }	
	
   public void setEntityClass(Class entityClass) {	
      this.entityClass = entityClass;	
   }	
	
   public void addDataScopeFilter(DataEntity entity, String ctrlPermi) {	
      throw new ServiceException("addDataScopeFilter(entity, ctrlPermi) 方法未实现。");	
   }	
	
   public void genIdAndValid(DataEntity entity, String viewCode) {	
      this.genIdAndValid(entity, viewCode, (String)null);	
   }	
	
   public DataEntity get(String id) {	
      DataEntity a = null;	
	
      try {	
         Class var10000 = this.entityClass;	
         Class[] var10001 = new Class[1];	
         boolean var10003 = true;	
         var10001[0] = String.class;	
         Constructor var5 = var10000.getConstructor(var10001);	
         Object[] var6 = new Object[1];	
         var10003 = true;	
         var6[0] = id;	
         a = (DataEntity)var5.newInstance(var6);	
      } catch (Exception var4) {	
         throw new ServiceException(var4);	
      }	
	
      return this.get(a);	
   }	
	
   public long findCount(DataEntity entity) {	
      if (entity.getStatus() == null) {	
         entity.setStatus("0");	
      }	
	
      long a;	
      Table a;	
      if ((a = this.dao.findCount(entity)) > 100L && ("0".equals(M.ALLATORIxDEMO().get("type")) || "9".equals(M.ALLATORIxDEMO().get("type"))) && (a = (Table)entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
         a = 100L;	
      }	
	
      return a;	
   }	
	
   public Page findPage(DataEntity entity) {	
      Page a;	
      if ((a = entity.getPage()) == null) {	
         throw new ServiceException("page 为空，请设置分页参数。");	
      } else {	
         a.setList(this.findList(entity));	
         Table a;	
         if (a.getCount() > 100L && ("0".equals(M.ALLATORIxDEMO().get("type")) || "9".equals(M.ALLATORIxDEMO().get("type"))) && (a = (Table)entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
            a.setCount(100L);	
         }	
	
         return a;	
      }	
   }	
	
   public DataEntity get(Class[] pkClass, Object[] pkValue, boolean isNewRecord) {	
      DataEntity a = null;	
      if (pkClass != null && pkValue != null && pkClass.length == pkValue.length) {	
         int a = false;	
	
         int a;	
         for(int var10000 = a = 0; var10000 < pkValue.length; var10000 = a) {	
            if (pkValue[a] != null) {	
               if (pkValue[a] instanceof String) {	
                  if (StringUtils.isNotBlank((String)pkValue[a])) {	
                     a = true;	
                  }	
               } else {	
                  a = true;	
               }	
            }	
	
            ++a;	
         }	
	
         if (a) {	
            try {	
               a = (DataEntity)this.entityClass.getConstructor(pkClass).newInstance(pkValue);	
            } catch (Exception var8) {	
               throw new ServiceException(var8);	
            }	
	
            a = this.get(a);	
            if (isNewRecord && a != null && M.ALLATORIxDEMO((HttpServletRequest)null)) {	
               throw newValidationException((String)null);	
            }	
         }	
      }	
	
      if (a == null) {	
         try {	
            a = (DataEntity)this.entityClass.newInstance();	
            return a;	
         } catch (Exception var7) {	
            throw new ServiceException(var7);	
         }	
      } else {	
         return a;	
      }	
   }	
	
   protected DataEntity newEntity(String id) {	
      DataEntity a = null;	
	
      try {	
         Class var10000 = this.entityClass;	
         Class[] var10001 = new Class[1];	
         boolean var10003 = true;	
         var10001[0] = String.class;	
         Constructor var5 = var10000.getConstructor(var10001);	
         Object[] var6 = new Object[1];	
         var10003 = true;	
         var6[0] = id;	
         a = (DataEntity)var5.newInstance(var6);	
         return a;	
      } catch (Exception var4) {	
         throw new ServiceException(var4);	
      }	
   }	
	
   public List findList(DataEntity entity) {	
      if (entity.getStatus() == null) {	
         entity.setStatus("0");	
      }	
	
      return this.dao.findList(entity);	
   }	
	
   public void addDataScopeFilter(DataEntity entity) {	
      this.addDataScopeFilter(entity, "1");	
   }	
	
   public void genId(DataEntity entity, String viewCode) {	
      if (viewCode != null) {	
         if (Global.isUseCorpModel()) {	
            entity.setId(entity.getCorpCode() + "_" + viewCode);	
            return;	
         }	
	
         entity.setId(viewCode);	
      }	
	
   }	
	
   public void genIdAndValid(DataEntity entity, String viewCode, String message) {	
      this.genId(entity, viewCode);	
      if (entity.getId() == null || this.get(entity) != null) {	
         throw newValidationException(message);	
      }	
   }	
	
   /** @deprecated */	
   public Page findPage(Page page, DataEntity entity) {	
      entity.setPage(page);	
      return this.findPage(entity);	
   }	
}	
