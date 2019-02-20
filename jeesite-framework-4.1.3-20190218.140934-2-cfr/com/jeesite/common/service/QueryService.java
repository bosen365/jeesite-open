/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 *  javax.validation.ValidationException	
 *  org.springframework.beans.factory.annotation.Autowired	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.service.api.QueryServiceApi;	
import com.jeesite.modules.gen.service.M;	
import java.lang.annotation.Annotation;	
import java.lang.reflect.Constructor;	
import java.util.List;	
import javax.validation.ValidationException;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public abstract class QueryService<D extends QueryDao<T>, T extends DataEntity<?>>	
extends BaseService	
implements QueryServiceApi<T> {	
    protected Class<T> entityClass;	
    @Autowired	
    protected D dao;	
	
    public static ValidationException newValidationException(String message) {	
        return new ValidationException((String)StringUtils.defaultIfBlank((CharSequence)message, (CharSequence)"编码已存在"));	
    }	
	
    @Override	
    public T get(String id, boolean isNewRecord) {	
        return this.get(new Class[]{String.class}, new Object[]{id}, isNewRecord);	
    }	
	
    protected T newEntity() {	
        DataEntity a2 = null;	
        try {	
            a2 = (DataEntity)this.entityClass.newInstance();	
        }	
        catch (Exception a3) {	
            throw new ServiceException(a3);	
        }	
        return (T)a2;	
    }	
	
    @Override	
    public T get(T entity) {	
        return (T)((DataEntity)this.dao.get(entity));	
    }	
	
    public QueryService() {	
        QueryService queryService = this;	
        queryService.setEntityClass(ReflectUtils.getClassGenricType(queryService.getClass(), (int)1));	
    }	
	
    public void setEntityClass(Class<T> entityClass) {	
        this.entityClass = entityClass;	
    }	
	
    @Override	
    public void addDataScopeFilter(T entity, String ctrlPermi) {	
        throw new ServiceException("addDataScopeFilter(entity, ctrlPermi) 方法未实现。");	
    }	
	
    @Override	
    public void genIdAndValid(T entity, String viewCode) {	
        this.genIdAndValid(entity, viewCode, null);	
    }	
	
    @Override	
    public T get(String id) {	
        DataEntity a2 = null;	
        try {	
            a2 = (DataEntity)this.entityClass.getConstructor(String.class).newInstance(id);	
        }	
        catch (Exception a3) {	
            throw new ServiceException(a3);	
        }	
        return (T)this.get(a2);	
    }	
	
    @Override	
    public long findCount(T entity) {	
        long a2;	
        Table a3;	
        if (((DataEntity)entity).getStatus() == null) {	
            ((DataEntity)entity).setStatus("0");	
        }	
        if ((a2 = this.dao.findCount(entity)) > 100L && ("0".equals(M.ALLATORIxDEMO().get("type")) || "9".equals(M.ALLATORIxDEMO().get("type"))) && (a3 = entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase((CharSequence)a3.name(), (CharSequence)"sys_user")) {	
            a2 = 100L;	
        }	
        return a2;	
    }	
	
    @Override	
    public Page<T> findPage(T entity) {	
        Table a2;	
        Page<?> a3 = ((BaseEntity)entity).getPage();	
        if (a3 == null) {	
            throw new ServiceException("page 为空，请设置分页参数。");	
        }	
        Page<?> page = a3;	
        page.setList(this.findList(entity));	
        if (page.getCount() > 100L && ("0".equals(M.ALLATORIxDEMO().get("type")) || "9".equals(M.ALLATORIxDEMO().get("type"))) && (a2 = entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase((CharSequence)a2.name(), (CharSequence)"sys_user")) {	
            a3.setCount(100L);	
        }	
        return a3;	
    }	
	
    @Override	
    public T get(Class<?>[] pkClass, Object[] pkValue, boolean isNewRecord) {	
        DataEntity a2 = null;	
        if (pkClass != null && pkValue != null && pkClass.length == pkValue.length) {	
            int a3;	
            boolean a4 = false;	
            int n = a3 = 0;	
            while (n < pkValue.length) {	
                if (pkValue[a3] != null) {	
                    if (pkValue[a3] instanceof String) {	
                        if (StringUtils.isNotBlank((CharSequence)((String)pkValue[a3]))) {	
                            a4 = true;	
                        }	
                    } else {	
                        a4 = true;	
                    }	
                }	
                n = ++a3;	
            }	
            if (a4) {	
                try {	
                    a2 = (DataEntity)this.entityClass.getConstructor(pkClass).newInstance(pkValue);	
                }	
                catch (Exception a5) {	
                    throw new ServiceException(a5);	
                }	
                a2 = this.get(a2);	
                if (isNewRecord && a2 != null && M.ALLATORIxDEMO(null)) {	
                    throw QueryService.newValidationException(null);	
                }	
            }	
        }	
        if (a2 == null) {	
            try {	
                a2 = (DataEntity)this.entityClass.newInstance();	
            }	
            catch (Exception a6) {	
                throw new ServiceException(a6);	
            }	
            return (T)a2;	
        }	
        return (T)a2;	
    }	
	
    protected T newEntity(String id) {	
        DataEntity a2 = null;	
        try {	
            a2 = (DataEntity)this.entityClass.getConstructor(String.class).newInstance(id);	
        }	
        catch (Exception a3) {	
            throw new ServiceException(a3);	
        }	
        return (T)a2;	
    }	
	
    @Override	
    public List<T> findList(T entity) {	
        if (((DataEntity)entity).getStatus() == null) {	
            ((DataEntity)entity).setStatus("0");	
        }	
        return this.dao.findList(entity);	
    }	
	
    @Override	
    public void addDataScopeFilter(T entity) {	
        this.addDataScopeFilter(entity, "1");	
    }	
	
    @Override	
    public void genId(T entity, String viewCode) {	
        if (viewCode != null) {	
            if (Global.isUseCorpModel().booleanValue()) {	
                T t = entity;	
                T t2 = entity;	
                ((BaseEntity)t2).setId(((BaseEntity)t2).getCorpCode() + "_" + viewCode);	
                return;	
            }	
            ((BaseEntity)entity).setId(viewCode);	
        }	
    }	
	
    @Override	
    public void genIdAndValid(T entity, String viewCode, String message) {	
        T t = entity;	
        this.genId(t, viewCode);	
        if (((BaseEntity)t).getId() == null || this.get(entity) != null) {	
            throw QueryService.newValidationException(message);	
        }	
    }	
	
    @Override	
    public Page<T> findPage(Page<T> page, T entity) {	
        T t = entity;	
        ((BaseEntity)t).setPage(page);	
        return this.findPage(t);	
    }	
}	
	
