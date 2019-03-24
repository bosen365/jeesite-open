/*	
 * Decompiled with CFR 0.141.	
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
import com.jeesite.common.shiro.l.k;	
import com.jeesite.modules.gen.service.H;	
import com.jeesite.modules.job.l.m;	
import java.lang.annotation.Annotation;	
import java.lang.reflect.Constructor;	
import java.util.List;	
import javax.validation.ValidationException;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public abstract class QueryService<D extends QueryDao<T>, T extends DataEntity<?>>	
extends BaseService	
implements QueryServiceApi<T> {	
    protected Class<T> entityClass;	
    @Autowired	
    protected D dao;	
	
    @Override	
    public T get(Class<?>[] pkClass, Object[] pkValue, boolean isNewRecord) {	
        DataEntity a = null;	
        if (pkClass != null && pkValue != null && pkClass.length == pkValue.length) {	
            int a2;	
            boolean a3 = false;	
            int n = a2 = 0;	
            while (n < pkValue.length) {	
                if (pkValue[a2] != null) {	
                    if (pkValue[a2] instanceof String) {	
                        if (StringUtils.isNotBlank((String)pkValue[a2])) {	
                            a3 = true;	
                        }	
                    } else {	
                        a3 = true;	
                    }	
                }	
                n = ++a2;	
            }	
            if (a3) {	
                try {	
                    a = (DataEntity)this.entityClass.getConstructor(pkClass).newInstance(pkValue);	
                }	
                catch (Exception a4) {	
                    throw new ServiceException(a4);	
                }	
                a = this.get(a);	
                if (isNewRecord && a != null && H.ALLATORIxDEMO(null)) {	
                    throw QueryService.newValidationException(null);	
                }	
            }	
        }	
        if (a == null) {	
            try {	
                a = (DataEntity)this.entityClass.newInstance();	
            }	
            catch (Exception a5) {	
                throw new ServiceException(a5);	
            }	
            return (T)a;	
        }	
        return (T)a;	
    }	
	
    @Override	
    public Page<T> findPage(Page<T> page, T entity) {	
        T t = entity;	
        ((BaseEntity)t).setPage(page);	
        return this.findPage(t);	
    }	
	
    @Override	
    public void genIdAndValid(T entity, String viewCode) {	
        this.genIdAndValid(entity, viewCode, null);	
    }	
	
    protected T newEntity(String id) {	
        DataEntity a = null;	
        try {	
            Class[] arrclass = new Class[1];	
            arrclass[0] = String.class;	
            Object[] arrobject = new Object[1];	
            arrobject[0] = id;	
            a = (DataEntity)this.entityClass.getConstructor(arrclass).newInstance(arrobject);	
        }	
        catch (Exception a2) {	
            throw new ServiceException(a2);	
        }	
        return (T)a;	
    }	
	
    @Override	
    public List<T> findList(T entity) {	
        if (((DataEntity)entity).getStatus() == null) {	
            ((DataEntity)entity).setStatus("0");	
        }	
        return this.dao.findList(entity);	
    }	
	
    @Override	
    public long findCount(T entity) {	
        long a;	
        Table a2;	
        if (((DataEntity)entity).getStatus() == null) {	
            ((DataEntity)entity).setStatus("0");	
        }	
        if ((a = this.dao.findCount(entity)) > 100L && ("0".equals(H.ALLATORIxDEMO().get("type")) || "9".equals(H.ALLATORIxDEMO().get("type"))) && (a2 = entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a2.name(), "sys_user")) {	
            a = 100L;	
        }	
        return a;	
    }	
	
    public static ValidationException newValidationException(String message) {	
        return new ValidationException(StringUtils.defaultIfBlank(message, "编码已存在"));	
    }	
	
    public QueryService() {	
        QueryService queryService = this;	
        queryService.setEntityClass(ReflectUtils.getClassGenricType(queryService.getClass(), 1));	
    }	
	
    @Override	
    public Page<T> findPage(T entity) {	
        Table a;	
        Page<?> a2 = ((BaseEntity)entity).getPage();	
        if (a2 == null) {	
            throw new ServiceException("page 为空，请设置分页参数。");	
        }	
        Page<?> page = a2;	
        page.setList(this.findList(entity));	
        if (page.getCount() > 100L && ("0".equals(H.ALLATORIxDEMO().get("type")) || "9".equals(H.ALLATORIxDEMO().get("type"))) && (a = entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
            a2.setCount(100L);	
        }	
        return a2;	
    }	
	
    public void setEntityClass(Class<T> entityClass) {	
        this.entityClass = entityClass;	
    }	
	
    @Override	
    public void addDataScopeFilter(T entity, String ctrlPermi) {	
        throw new ServiceException("addDataScopeFilter(entity, ctrlPermi) 方法未实现。");	
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
	
    protected T newEntity() {	
        DataEntity a = null;	
        try {	
            a = (DataEntity)this.entityClass.newInstance();	
        }	
        catch (Exception a2) {	
            throw new ServiceException(a2);	
        }	
        return (T)a;	
    }	
	
    @Override	
    public T get(T entity) {	
        return (T)((DataEntity)this.dao.get(entity));	
    }	
	
    @Override	
    public T get(String id) {	
        DataEntity a = null;	
        try {	
            Class[] arrclass = new Class[1];	
            arrclass[0] = String.class;	
            Object[] arrobject = new Object[1];	
            arrobject[0] = id;	
            a = (DataEntity)this.entityClass.getConstructor(arrclass).newInstance(arrobject);	
        }	
        catch (Exception a2) {	
            throw new ServiceException(a2);	
        }	
        return (T)this.get(a);	
    }	
	
    @Override	
    public void addDataScopeFilter(T entity) {	
        this.addDataScopeFilter(entity, "1");	
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
    public T get(String id, boolean isNewRecord) {	
        Class[] arrclass = new Class[1];	
        arrclass[0] = String.class;	
        Object[] arrobject = new Object[1];	
        arrobject[0] = id;	
        return this.get(arrclass, arrobject, isNewRecord);	
    }	
}	
	
