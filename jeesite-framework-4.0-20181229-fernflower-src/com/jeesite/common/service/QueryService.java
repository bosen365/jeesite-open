package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.sys.service.j;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.lang.reflect.Constructor;	
import java.lang.reflect.Method;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ValidationException;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public abstract class QueryService extends BaseService {	
   @Autowired	
   protected QueryDao dao;	
   protected Class entityClass = ReflectUtils.getClassGenricType(this.getClass(), 1);	
	
   public void genId(DataEntity entity, String viewCode) {	
      if (viewCode != null) {	
         if (Global.isUseCorpModel()) {	
            entity.setId(entity.getCorpCode() + "_" + viewCode);	
            return;	
         }	
	
         entity.setId(viewCode);	
      }	
	
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
	
   public static ValidationException newValidationException(String message) {	
      return new ValidationException((String)StringUtils.defaultIfBlank(message, "编码已存在"));	
   }	
	
   public void genIdAndValid(DataEntity entity, String viewCode) {	
      this.genIdAndValid(entity, viewCode, (String)null);	
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
            if (isNewRecord && a != null && j.ALLATORIxDEMO((HttpServletRequest)null)) {	
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
	
   public Page findPage(Page page, DataEntity entity) {	
      Page var10000;	
      label23: {	
         try {	
            Class var5 = this.entityClass;	
            String var10001 = "sePage";	
            Class[] var10002 = new Class[1];	
            boolean var10004 = true;	
            var10002[0] = Page.class;	
            Method var6 = var5.getMethod(var10001, var10002);	
            Object[] var7 = new Object[1];	
            var10004 = true;	
            var7[0] = page;	
            var6.invoke(entity, var7);	
         } catch (Exception var4) {	
            var10000 = page;	
            var4.printStackTrace();	
            break label23;	
         }	
	
         var10000 = page;	
      }	
	
      var10000.setList(this.findList(entity));	
      Table a;	
      if (page.getCount() > 100L && StringUtils.equals((CharSequence)j.ALLATORIxDEMO().get("type"), "0") && (a = (Table)entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
         page.setCount(100L);	
      }	
	
      return page;	
   }	
	
   public long findCount(DataEntity entity) {	
      if (entity.getStatus() == null) {	
         entity.setStatus("0");	
      }	
	
      long a;	
      Table a;	
      if ((a = this.dao.findCount(entity)) > 100L && StringUtils.equals((CharSequence)j.ALLATORIxDEMO().get("type"), "0") && (a = (Table)entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
         a = 100L;	
      }	
	
      return a;	
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
	
   public void genIdAndValid(DataEntity entity, String viewCode, String message) {	
      this.genId(entity, viewCode);	
      if (entity.getId() == null || this.get(entity) != null) {	
         throw newValidationException(message);	
      }	
   }	
	
   public void addDataScopeFilter(DataEntity entity) {	
      this.addDataScopeFilter(entity, "1");	
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
	
   public void addDataScopeFilter(DataEntity entity, String ctrlPermi) {	
      throw new ServiceException("子类未实现ddDtScopeFilter(entity,ctrlPermi)方法");	
   }	
	
   public DataEntity get(DataEntity entity) {	
      return (DataEntity)this.dao.get(entity);	
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
}	
