/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.ibatis.binding.BindingException	
 *  org.slf4j.Logger	
 *  org.springframework.transaction.annotation.Isolation	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.autoconfigure.sys.Sys0AutoConfiguration;	
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
import org.hyperic.sigar.pager.PageFetchException;	
import org.slf4j.Logger;	
import org.springframework.transaction.annotation.Isolation;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public abstract class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>>	
extends TreeQueryService<D, T>	
implements TreeQueryServiceApi<T> {	
    @Transactional(readOnly=false, isolation=Isolation.READ_UNCOMMITTED)	
    private /* synthetic */ void updateTreeData(T entity) {	
        if ("0".equals(((BaseEntity)entity).getId())) {	
            return;	
        }	
        try {	
            ((TreeDao)this.dao).updateTreeData(entity);	
            return;	
        }	
        catch (BindingException a2) {	
            this.logger.warn("updateParentCodes", (Throwable)a2);	
            return;	
        }	
    }	
	
    @Transactional(readOnly=false, isolation=Isolation.READ_UNCOMMITTED)	
    private /* synthetic */ void updateTreeLeaf(T entity) {	
        if ("0".equals(((BaseEntity)entity).getId())) {	
            return;	
        }	
        try {	
            ((TreeDao)this.dao).updateTreeLeaf(entity);	
            return;	
        }	
        catch (BindingException a2) {	
            this.logger.warn("updateTreeLeaf", (Throwable)a2);	
            return;	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void fixTreeData() {	
        this.fixTreeData("0");	
    }	
	
    protected void updateChildNode(T childEntity, T parentEntity) {	
        this.updateTreeData(childEntity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateTreeSort(T entity) {	
        TreeEntity treeEntity;	
        Global.assertDemoMode();	
        if (entity.getTreeSort() == null) {	
            entity.setTreeSort(30);	
        }	
        if (entity.getTreeSorts() == null || entity.getParentCodes() == null) {	
            TreeEntity a2 = ((TreeDao)this.dao).get(entity);	
            TreeEntity treeEntity2 = entity;	
            TreeEntity treeEntity3 = a2;	
            entity.setParent(treeEntity3.getParent());	
            treeEntity2.setParentCodes(treeEntity3.getParentCodes());	
            treeEntity2.setTreeSorts(a2.getTreeSorts());	
        }	
        if (entity.getParent() == null || StringUtils.isBlank((CharSequence)entity.getParentCode()) || "0".equals(entity.getParentCode())) {	
            TreeEntity treeEntity4 = entity;	
            treeEntity = treeEntity4;	
            treeEntity4.setParent(null);	
        } else {	
            entity.setParent((TreeEntity)super.get(entity.getParentCode()));	
            treeEntity = entity;	
        }	
        if (treeEntity.getParent() == null) {	
            entity.setParent((TreeEntity)this.newEntity("0"));	
            TreeEntity treeEntity5 = entity;	
            ((TreeEntity)treeEntity5.getParent()).setParentCodes("");	
            ((TreeEntity)treeEntity5.getParent()).setTreeSorts("");	
        }	
        TreeEntity treeEntity6 = entity;	
        String a2 = treeEntity6.getParentCodes();	
        String a3 = treeEntity6.getTreeSorts();	
        TreeEntity treeEntity7 = entity;	
        treeEntity6.setTreeSorts(((TreeEntity)entity.getParent()).getTreeSorts() + StringUtils.leftPad((String)String.valueOf(entity.getTreeSort()), (int)10, (String)"0") + ",");	
        ((TreeDao)this.dao).updateTreeSort(entity);	
        if (a2 != null) {	
            void a4;	
            TreeEntity treeEntity8 = (TreeEntity)this.newEntity();	
            a4.setParentCodes(new StringBuilder().insert(0, a2).append(entity.getId()).append("%").toString());	
            for (TreeEntity a5 : ((TreeDao)this.dao).findByParentCodesLike(a4)) {	
                if (a5.getParentCodes() == null) continue;	
                TreeEntity treeEntity9 = a5;	
                treeEntity9.setTreeSorts(StringUtils.replaceOnce((String)treeEntity9.getTreeSorts(), (String)a3, (String)entity.getTreeSorts()));	
                ((TreeDao)this.dao).updateTreeSort(a5);	
            }	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank((CharSequence)((BaseEntity)entity).getId())) {	
            return;	
        }	
        TreeEntity a2 = (TreeEntity)this.newEntity();	
        a2.getSqlMap().getWhere().andBracket(((BaseEntity)entity).getIdColumnName(), QueryType.EQ, ((BaseEntity)entity).getId()).or("parent_codes", QueryType.LIKE, new StringBuilder().insert(0, ",").append(((BaseEntity)entity).getId()).append(",").toString()).endBracket();	
        ((TreeDao)this.dao).deleteByEntity(a2);	
        if (entity != null && ((TreeEntity)entity).getParent() != null) {	
            this.updateTreeLeaf(((TreeEntity)entity).getParent());	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(T entity) {	
        TreeEntity treeEntity;	
        TreeService treeService;	
        TreeEntity a2 = super.get(entity);	
        if (entity.getParent() == null || StringUtils.isBlank((CharSequence)entity.getParentCode()) || "0".equals(entity.getParentCode())) {	
            TreeEntity treeEntity2 = entity;	
            treeEntity = treeEntity2;	
            treeEntity2.setParent(null);	
        } else {	
            entity.setParent((TreeEntity)super.get(entity.getParentCode()));	
            treeEntity = entity;	
        }	
        if (treeEntity.getParent() == null) {	
            entity.setParent((TreeEntity)this.newEntity("0"));	
            TreeEntity treeEntity3 = entity;	
            ((TreeEntity)treeEntity3.getParent()).setParentCodes("");	
            ((TreeEntity)treeEntity3.getParent()).setTreeSorts("");	
        }	
        TreeEntity treeEntity4 = entity;	
        String a3 = treeEntity4.getParentCodes();	
        String a4 = treeEntity4.getTreeSorts();	
        String a5 = treeEntity4.getTreeNames();	
        TreeEntity treeEntity5 = entity;	
        treeEntity4.setParentCodes(((TreeEntity)entity.getParent()).getParentCodes() + entity.getParentCode() + ",");	
        if (treeEntity4.getTreeSort() == null) {	
            entity.setTreeSort(30);	
        }	
        TreeEntity treeEntity6 = entity;	
        TreeEntity treeEntity7 = entity;	
        treeEntity6.setTreeSorts(new StringBuilder().insert(0, ((TreeEntity)entity.getParent()).getTreeSorts()).append(StringUtils.leftPad((String)String.valueOf(treeEntity7.getTreeSort()), (int)10, (String)"0")).append(",").toString());	
        treeEntity6.setTreeLevel(treeEntity7.getParentCodes().replaceAll("[^,]", "").length() - 1);	
        if (treeEntity6.getIsNewRecord()) {	
            entity.setTreeLeaf("1");	
        }	
        TreeEntity treeEntity8 = entity;	
        treeEntity8.setTreeName_(null);	
        if (StringUtils.isNotBlank((CharSequence)((TreeEntity)treeEntity8.getParent()).getTreeNames())) {	
            treeService = this;	
            TreeEntity treeEntity9 = entity;	
            treeEntity9.setTreeNames(new StringBuilder().insert(0, ((TreeEntity)treeEntity9.getParent()).getTreeNames()).append("/").append(entity.getTreeName_()).toString());	
        } else {	
            TreeEntity treeEntity10 = entity;	
            treeEntity10.setTreeNames(treeEntity10.getTreeName_());	
            treeService = this;	
        }	
        super.save(entity);	
        if (a3 != null) {	
            void a6;	
            TreeEntity treeEntity11 = (TreeEntity)this.newEntity();	
            a6.setParentCodes(new StringBuilder().insert(0, a3).append(entity.getId()).append(",%").toString());	
            for (TreeEntity a7 : ((TreeDao)this.dao).findByParentCodesLike(a6)) {	
                if (a7.getParentCodes() == null) continue;	
                TreeEntity treeEntity12 = a7;	
                treeEntity12.setParentCodes(StringUtils.replaceOnce((String)treeEntity12.getParentCodes(), (String)a3, (String)entity.getParentCodes()));	
                treeEntity12.setTreeSorts(StringUtils.replaceOnce((String)treeEntity12.getTreeSorts(), (String)a4, (String)entity.getTreeSorts()));	
                treeEntity12.setTreeLevel(treeEntity12.getParentCodes().replaceAll("[^,]", "").length() - 1);	
                treeEntity12.setTreeNames(StringUtils.replaceOnce((String)treeEntity12.getTreeNames(), (String)a5, (String)entity.getTreeNames()));	
                this.updateChildNode(treeEntity12, entity);	
            }	
        }	
        if (a2 != null && a2.getParent() != null) {	
            this.updateTreeLeaf(a2.getParent());	
        }	
        if (entity != null && entity.getParent() != null && !StringUtils.equals((CharSequence)entity.getParentCode(), (CharSequence)(a2 == null ? "" : a2.getParentCode()))) {	
            this.updateTreeLeaf(entity.getParent());	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void fixTreeData(String parentCode) {	
        void a2;	
        TreeEntity treeEntity = (TreeEntity)this.newEntity();	
        TreeService treeService = this;	
        void v1 = a2;	
        v1.setStatus(null);	
        v1.setParentCode(parentCode);	
        treeService.fixTreeData(treeService.findList(a2), a2.getParentCode(), "", "", "");	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank((CharSequence)((BaseEntity)entity).getId())) {	
            return;	
        }	
        ((TreeDao)this.dao).updateStatus(entity);	
        if (entity != null && ((TreeEntity)entity).getParent() != null) {	
            this.updateTreeLeaf(((TreeEntity)entity).getParent());	
        }	
    }	
	
    private /* synthetic */ void fixTreeData(List<T> list, String parentCode, String parentCodes, String treeSorts, String treeNames) {	
        for (TreeEntity treeEntity : list) {	
            void a2;	
            TreeService treeService;	
            void a3;	
            void v0 = a3;	
            a3.setParentCodes(parentCodes + parentCode + ",");	
            void v1 = a3;	
            v0.setTreeSorts(new StringBuilder().insert(0, treeSorts).append(StringUtils.leftPad((String)String.valueOf(v1.getTreeSort()), (int)10, (String)"0")).append(",").toString());	
            v0.setTreeLevel(v1.getParentCodes().replaceAll("[^,]", "").length() - 1);	
            if (StringUtils.isNotBlank((CharSequence)treeNames)) {	
                treeService = this;	
                void v3 = a3;	
                v3.setTreeNames(new StringBuilder().insert(0, treeNames).append("/").append(v3.getTreeName_()).toString());	
            } else {	
                void v4 = a3;	
                v4.setTreeNames(v4.getTreeName_());	
                treeService = this;	
            }	
            treeService.updateTreeData(a3);	
            TreeService treeService2 = this;	
            treeService2.updateTreeLeaf(a3);	
            TreeEntity treeEntity2 = (TreeEntity)treeService2.newEntity();	
            void v6 = a2;	
            v6.setStatus(null);	
            v6.setParentCode(a3.getId());	
            List<void> a4 = this.findList(v6);	
            if (a4.size() <= 0) continue;	
            this.fixTreeData(a4, a2.getParentCode(), a3.getParentCodes(), a3.getTreeSorts(), a3.getTreeNames());	
        }	
    }	
}	
	
