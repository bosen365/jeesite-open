/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.service.TreeQueryService;	
import com.jeesite.common.service.api.TreeQueryServiceApi;	
import java.util.List;	
import org.apache.ibatis.binding.BindingException;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.ProcCredName;	
import org.slf4j.Logger;	
import org.springframework.transaction.annotation.Isolation;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public abstract class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>>	
extends TreeQueryService<D, T>	
implements TreeQueryServiceApi<T> {	
    @Transactional(readOnly=false, isolation=Isolation.READ_UNCOMMITTED)	
    private /* synthetic */ void updateTreeLeaf(T entity) {	
        if ("0".equals(((BaseEntity)entity).getId())) {	
            return;	
        }	
        try {	
            ((TreeDao)this.dao).updateTreeLeaf(entity);	
            return;	
        }	
        catch (BindingException a) {	
            this.logger.warn("updateTreeLeaf", a);	
            return;	
        }	
    }	
	
    protected void updateChildNode(T childEntity, T parentEntity) {	
        this.updateTreeData(childEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void fixTreeData(String parentCode) {	
        void a;	
        TreeEntity treeEntity = (TreeEntity)this.newEntity();	
        TreeService treeService = this;	
        void v1 = a;	
        v1.setStatus(null);	
        v1.setParentCode(parentCode);	
        treeService.fixTreeData(treeService.findList(a), a.getParentCode(), "", "", "");	
    }	
	
    private /* synthetic */ void fixTreeData(List<T> list, String parentCode, String parentCodes, String treeSorts, String treeNames) {	
        for (TreeEntity treeEntity : list) {	
            void a;	
            void a2;	
            TreeService treeService;	
            void v0 = a2;	
            a2.setParentCodes(parentCodes + parentCode + ",");	
            void v1 = a2;	
            v0.setTreeSorts(new StringBuilder().insert(0, treeSorts).append(StringUtils.leftPad(String.valueOf(v1.getTreeSort()), 10, "0")).append(",").toString());	
            v0.setTreeLevel(v1.getParentCodes().replaceAll("[^,]", "").length() - 1);	
            if (StringUtils.isNotBlank(treeNames)) {	
                treeService = this;	
                void v3 = a2;	
                v3.setTreeNames(new StringBuilder().insert(0, treeNames).append("/").append(v3.getTreeName_()).toString());	
            } else {	
                void v4 = a2;	
                v4.setTreeNames(v4.getTreeName_());	
                treeService = this;	
            }	
            treeService.updateTreeData(a2);	
            TreeService treeService2 = this;	
            treeService2.updateTreeLeaf(a2);	
            TreeEntity treeEntity2 = (TreeEntity)treeService2.newEntity();	
            void v6 = a;	
            v6.setStatus(null);	
            v6.setParentCode(a2.getId());	
            List<void> a3 = this.findList(v6);	
            if (a3.size() <= 0) continue;	
            this.fixTreeData(a3, a.getParentCode(), a2.getParentCodes(), a2.getTreeSorts(), a2.getTreeNames());	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void fixTreeData() {	
        this.fixTreeData("0");	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(((BaseEntity)entity).getId())) {	
            return;	
        }	
        ((TreeDao)this.dao).updateStatus(entity);	
        if (entity != null && ((TreeEntity)entity).getParent() != null) {	
            this.updateTreeLeaf(((TreeEntity)entity).getParent());	
        }	
    }	
	
    @Transactional(readOnly=false, isolation=Isolation.READ_UNCOMMITTED)	
    private /* synthetic */ void updateTreeData(T entity) {	
        if ("0".equals(((BaseEntity)entity).getId())) {	
            return;	
        }	
        try {	
            ((TreeDao)this.dao).updateTreeData(entity);	
            return;	
        }	
        catch (BindingException a) {	
            this.logger.warn("updateParentCodes", a);	
            return;	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(T entity) {	
        TreeEntity treeEntity;	
        TreeService treeService;	
        TreeEntity a = super.get(entity);	
        if (entity.getParent() == null || StringUtils.isBlank(entity.getParentCode()) || "0".equals(entity.getParentCode())) {	
            TreeEntity treeEntity2 = entity;	
            treeEntity = treeEntity2;	
            treeEntity2.setParent(null);	
        } else {	
            entity.setParent((TreeEntity)((Object)super.get(entity.getParentCode())));	
            treeEntity = entity;	
        }	
        if (treeEntity.getParent() == null) {	
            entity.setParent((TreeEntity)this.newEntity("0"));	
            TreeEntity treeEntity3 = entity;	
            ((TreeEntity)treeEntity3.getParent()).setParentCodes("");	
            ((TreeEntity)treeEntity3.getParent()).setTreeSorts("");	
        }	
        TreeEntity treeEntity4 = entity;	
        String a2 = treeEntity4.getParentCodes();	
        String a3 = treeEntity4.getTreeSorts();	
        String a4 = treeEntity4.getTreeNames();	
        TreeEntity treeEntity5 = entity;	
        treeEntity4.setParentCodes(((TreeEntity)entity.getParent()).getParentCodes() + entity.getParentCode() + ",");	
        if (treeEntity4.getTreeSort() == null) {	
            entity.setTreeSort(30);	
        }	
        TreeEntity treeEntity6 = entity;	
        TreeEntity treeEntity7 = entity;	
        treeEntity6.setTreeSorts(new StringBuilder().insert(0, ((TreeEntity)entity.getParent()).getTreeSorts()).append(StringUtils.leftPad(String.valueOf(treeEntity7.getTreeSort()), 10, "0")).append(",").toString());	
        treeEntity6.setTreeLevel(treeEntity7.getParentCodes().replaceAll("[^,]", "").length() - 1);	
        if (treeEntity6.getIsNewRecord()) {	
            entity.setTreeLeaf("1");	
        }	
        TreeEntity treeEntity8 = entity;	
        treeEntity8.setTreeName_(null);	
        if (StringUtils.isNotBlank(((TreeEntity)treeEntity8.getParent()).getTreeNames())) {	
            treeService = this;	
            TreeEntity treeEntity9 = entity;	
            treeEntity9.setTreeNames(new StringBuilder().insert(0, ((TreeEntity)treeEntity9.getParent()).getTreeNames()).append("/").append(entity.getTreeName_()).toString());	
        } else {	
            TreeEntity treeEntity10 = entity;	
            treeEntity10.setTreeNames(treeEntity10.getTreeName_());	
            treeService = this;	
        }	
        super.save(entity);	
        if (a2 != null) {	
            void a5;	
            TreeEntity treeEntity11 = (TreeEntity)this.newEntity();	
            a5.setParentCodes(new StringBuilder().insert(0, a2).append(entity.getId()).append(",%").toString());	
            for (TreeEntity a6 : ((TreeDao)this.dao).findByParentCodesLike(a5)) {	
                if (a6.getParentCodes() == null) continue;	
                TreeEntity treeEntity12 = a6;	
                treeEntity12.setParentCodes(StringUtils.replaceOnce(treeEntity12.getParentCodes(), a2, entity.getParentCodes()));	
                treeEntity12.setTreeSorts(StringUtils.replaceOnce(treeEntity12.getTreeSorts(), a3, entity.getTreeSorts()));	
                treeEntity12.setTreeLevel(treeEntity12.getParentCodes().replaceAll("[^,]", "").length() - 1);	
                treeEntity12.setTreeNames(StringUtils.replaceOnce(treeEntity12.getTreeNames(), a4, entity.getTreeNames()));	
                this.updateChildNode(treeEntity12, entity);	
            }	
        }	
        if (a != null && a.getParent() != null) {	
            this.updateTreeLeaf(a.getParent());	
        }	
        if (entity != null && entity.getParent() != null && !StringUtils.equals(entity.getParentCode(), a == null ? "" : a.getParentCode())) {	
            this.updateTreeLeaf(entity.getParent());	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateTreeSort(T entity) {	
        Object a;	
        TreeEntity treeEntity;	
        Global.assertDemoMode();	
        if (entity.getTreeSort() == null) {	
            entity.setTreeSort(30);	
        }	
        if (entity.getTreeSorts() == null || entity.getParentCodes() == null) {	
            a = ((TreeDao)this.dao).get(entity);	
            TreeEntity treeEntity2 = entity;	
            TreeEntity treeEntity3 = a;	
            entity.setParent(treeEntity3.getParent());	
            treeEntity2.setParentCodes(treeEntity3.getParentCodes());	
            treeEntity2.setTreeSorts(((TreeEntity)a).getTreeSorts());	
        }	
        if (entity.getParent() == null || StringUtils.isBlank(entity.getParentCode()) || "0".equals(entity.getParentCode())) {	
            TreeEntity treeEntity4 = entity;	
            treeEntity = treeEntity4;	
            treeEntity4.setParent(null);	
        } else {	
            entity.setParent((TreeEntity)((Object)super.get(entity.getParentCode())));	
            treeEntity = entity;	
        }	
        if (treeEntity.getParent() == null) {	
            entity.setParent((TreeEntity)this.newEntity("0"));	
            TreeEntity treeEntity5 = entity;	
            ((TreeEntity)treeEntity5.getParent()).setParentCodes("");	
            ((TreeEntity)treeEntity5.getParent()).setTreeSorts("");	
        }	
        TreeEntity treeEntity6 = entity;	
        a = treeEntity6.getParentCodes();	
        String a2 = treeEntity6.getTreeSorts();	
        TreeEntity treeEntity7 = entity;	
        treeEntity6.setTreeSorts(((TreeEntity)entity.getParent()).getTreeSorts() + StringUtils.leftPad(String.valueOf(entity.getTreeSort()), 10, "0") + ",");	
        ((TreeDao)this.dao).updateTreeSort(entity);	
        if (a != null) {	
            void a3;	
            TreeEntity treeEntity8 = (TreeEntity)this.newEntity();	
            a3.setParentCodes(new StringBuilder().insert(0, (String)a).append(entity.getId()).append("%").toString());	
            for (TreeEntity a4 : ((TreeDao)this.dao).findByParentCodesLike(a3)) {	
                if (a4.getParentCodes() == null) continue;	
                TreeEntity treeEntity9 = a4;	
                treeEntity9.setTreeSorts(StringUtils.replaceOnce(treeEntity9.getTreeSorts(), a2, entity.getTreeSorts()));	
                ((TreeDao)this.dao).updateTreeSort(a4);	
            }	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(((BaseEntity)entity).getId())) {	
            return;	
        }	
        TreeEntity a = (TreeEntity)this.newEntity();	
        a.getSqlMap().getWhere().andBracket(((BaseEntity)entity).getIdColumnName(), QueryType.EQ, ((BaseEntity)entity).getId()).or("parent_codes", QueryType.LIKE, new StringBuilder().insert(0, ",").append(((BaseEntity)entity).getId()).append(",").toString()).endBracket();	
        ((TreeDao)this.dao).deleteByEntity(a);	
        if (entity != null && ((TreeEntity)entity).getParent() != null) {	
            this.updateTreeLeaf(((TreeEntity)entity).getParent());	
        }	
    }	
}	
	
