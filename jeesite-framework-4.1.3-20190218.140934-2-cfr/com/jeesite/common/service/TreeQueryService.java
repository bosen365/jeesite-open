/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.api.TreeServiceApi;	
import java.util.ArrayList;	
import java.util.List;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public abstract class TreeQueryService<D extends TreeDao<T>, T extends TreeEntity<T>>	
extends CrudService<D, T>	
implements TreeServiceApi<T> {	
    @Override	
    public void listTreeSort(List<T> sourceList, List<T> targetList, String parentCode) {	
        int a2;	
        int n = a2 = 0;	
        while (n < sourceList.size()) {	
            TreeEntity a3 = (TreeEntity)sourceList.get(a2);	
            if (parentCode != null && parentCode.equals(a3.getParentCode())) {	
                void a4;	
                boolean bl = false;	
                targetList.add(a3);	
                void v1 = a4;	
                while (v1 < sourceList.size()) {	
                    TreeEntity a5 = (TreeEntity)sourceList.get((int)a4);	
                    if (a3.getId().equals(a5.getParentCode())) {	
                        this.listTreeSort(sourceList, targetList, a3.getId());	
                        break;	
                    }	
                    v1 = ++a4;	
                }	
            }	
            n = ++a2;	
        }	
    }	
	
    @Override	
    public T getLastByParentCode(T entity) {	
        if (StringUtils.isBlank((CharSequence)((TreeEntity)entity).getParentCode())) {	
            ((TreeEntity)entity).setParentCode("0");	
        }	
        ((TreeEntity)this.newEntity()).setParentCode(((BaseEntity)entity).getId());	
        T t = entity;	
        ((BaseEntity)t).setPage(new Page(1, 1, -1L));	
        ((BaseEntity)entity).getPage().setOrderBy(new StringBuilder().insert(0, MapperHelper.getTable(entity).alias()).append(".tree_sort DESC").toString());	
        List<T> a2 = super.findList(t);	
        if (ListUtils.isNotEmpty(a2)) {	
            return (T)((TreeEntity)a2.get(0));	
        }	
        return null;	
    }	
	
    @Override	
    public void convertChildList(List<T> sourceList, List<T> targetList, String parentCode) {	
        int a2;	
        int n = a2 = 0;	
        while (n < sourceList.size()) {	
            TreeEntity a3 = (TreeEntity)sourceList.get(a2);	
            if (parentCode != null && parentCode.equals(a3.getParentCode())) {	
                int a4;	
                TreeEntity treeEntity = a3;	
                targetList.add(treeEntity);	
                if (treeEntity.getChildList() == null) {	
                    ArrayList a5 = ListUtils.newArrayList();	
                    a3.setChildList(a5);	
                }	
                int n2 = a4 = 0;	
                while (n2 < sourceList.size()) {	
                    TreeEntity a6 = (TreeEntity)sourceList.get(a4);	
                    if (a3.getId().equals(a6.getParentCode())) {	
                        this.convertChildList(sourceList, a3.getChildList(), a3.getId());	
                        break;	
                    }	
                    n2 = ++a4;	
                }	
            }	
            n = ++a2;	
        }	
    }	
}	
	
