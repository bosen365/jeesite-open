/*	
 * Decompiled with CFR 0.141.	
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
import org.hyperic.sigar.test.GetPass;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public abstract class TreeQueryService<D extends TreeDao<T>, T extends TreeEntity<T>>	
extends CrudService<D, T>	
implements TreeServiceApi<T> {	
    @Override	
    public T getLastByParentCode(T entity) {	
        if (StringUtils.isBlank(((TreeEntity)entity).getParentCode())) {	
            ((TreeEntity)entity).setParentCode("0");	
        }	
        ((TreeEntity)this.newEntity()).setParentCode(((BaseEntity)entity).getId());	
        T t = entity;	
        ((BaseEntity)t).setPage(new Page(1, 1, -1L));	
        ((BaseEntity)entity).getPage().setOrderBy(new StringBuilder().insert(0, MapperHelper.getTable(entity).alias()).append(".tree_sort DESC").toString());	
        List<T> a = super.findList(t);	
        if (ListUtils.isNotEmpty(a)) {	
            return (T)((TreeEntity)a.get(0));	
        }	
        return null;	
    }	
	
    @Override	
    public void convertChildList(List<T> sourceList, List<T> targetList, String parentCode) {	
        int a;	
        int n = a = 0;	
        while (n < sourceList.size()) {	
            TreeEntity a2 = (TreeEntity)sourceList.get(a);	
            if (parentCode != null && parentCode.equals(a2.getParentCode())) {	
                int a3;	
                TreeEntity treeEntity = a2;	
                targetList.add(treeEntity);	
                if (treeEntity.getChildList() == null) {	
                    ArrayList a4 = ListUtils.newArrayList();	
                    a2.setChildList(a4);	
                }	
                int n2 = a3 = 0;	
                while (n2 < sourceList.size()) {	
                    TreeEntity a5 = (TreeEntity)sourceList.get(a3);	
                    if (a2.getId().equals(a5.getParentCode())) {	
                        this.convertChildList(sourceList, a2.getChildList(), a2.getId());	
                        break;	
                    }	
                    n2 = ++a3;	
                }	
            }	
            n = ++a;	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void listTreeSort(List<T> sourceList, List<T> targetList, String parentCode) {	
        int a;	
        int n = a = 0;	
        while (n < sourceList.size()) {	
            TreeEntity a2 = (TreeEntity)sourceList.get(a);	
            if (parentCode != null && parentCode.equals(a2.getParentCode())) {	
                void a3;	
                targetList.add(a2);	
                boolean bl = false;	
                void v1 = a3;	
                while (v1 < sourceList.size()) {	
                    TreeEntity a4 = (TreeEntity)sourceList.get((int)a3);	
                    if (a2.getId().equals(a4.getParentCode())) {	
                        this.listTreeSort(sourceList, targetList, a2.getId());	
                        break;	
                    }	
                    v1 = ++a3;	
                }	
            }	
            n = ++a;	
        }	
    }	
}	
	
