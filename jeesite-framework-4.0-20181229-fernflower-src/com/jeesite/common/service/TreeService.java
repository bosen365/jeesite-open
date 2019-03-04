package com.jeesite.common.service;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.ibatis.binding.BindingException;	
import org.springframework.transaction.annotation.Isolation;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public abstract class TreeService extends CrudService {	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(TreeEntity entity) {	
      Global.assertDemoMode();	
      if (!StringUtils.isBlank(entity.getId())) {	
         TreeEntity a = (TreeEntity)this.newEntity();	
         a.getSqlMap().getWhere().andBracket(entity.getIdColumnName(), QueryType.EQ, entity.getId()).or("parent_codes", QueryType.LIKE, (new StringBuilder()).insert(0, ",").append(entity.getId()).append(",").toString()).endBracket();	
         ((TreeDao)this.dao).deleteByEntity(a);	
         if (entity != null && entity.getParent() != null) {	
            this.updateTreeLeaf(entity.getParent());	
         }	
	
      }	
   }	
	
   public void listTreeSort(List sourceList, List targetList, String parentCode) {	
      int a;	
      for(int var10000 = a = 0; var10000 < sourceList.size(); var10000 = a) {	
         TreeEntity a = (TreeEntity)sourceList.get(a);	
         if (parentCode != null && parentCode.equals(a.getParentCode())) {	
            targetList.add(a);	
            int a = 0;	
	
            for(var10000 = a; var10000 < sourceList.size(); var10000 = a) {	
               TreeEntity a = (TreeEntity)sourceList.get(a);	
               if (a.getId().equals(a.getParentCode())) {	
                  this.listTreeSort(sourceList, targetList, a.getId());	
                  break;	
               }	
	
               ++a;	
            }	
         }	
	
         ++a;	
      }	
	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void fixTreeData(String parentCode) {	
      TreeEntity a = (TreeEntity)this.newEntity();	
      a.setStatus((String)null);	
      a.setParentCode(parentCode);	
      this.fixTreeData(this.findList(a), a.getParentCode(), "", "", "");	
   }	
	
   protected void updateChildNode(TreeEntity childEntity, TreeEntity parentEntity) {	
      this.updateTreeData(childEntity);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void fixTreeData() {	
      this.fixTreeData("0");	
   }	
	
   public TreeEntity getLastByParentCode(TreeEntity entity) {	
      if (StringUtils.isBlank(entity.getParentCode())) {	
         entity.setParentCode("0");	
      }	
	
      ((TreeEntity)this.newEntity()).setParentCode(entity.getId());	
      entity.setPage(new Page(1, 1, -1L));	
      entity.getPage().setOrderBy((new StringBuilder()).insert(0, MapperHelper.getTable((BaseEntity)entity).alias()).append(".tree_sort DESC").toString());	
      List a = super.findList(entity);	
      return ListUtils.isNotEmpty(a) ? (TreeEntity)a.get(0) : null;	
   }	
	
   // $FF: synthetic method	
   private void fixTreeData(List list, String parentCode, String parentCodes, String treeSorts, String treeNames) {	
      Iterator var6 = list.iterator();	
	
      while(var6.hasNext()) {	
         TreeEntity a = (TreeEntity)var6.next();	
         a.setParentCodes(parentCodes + parentCode + ",");	
         a.setTreeSorts((new StringBuilder()).insert(0, treeSorts).append(StringUtils.leftPad(String.valueOf(a.getTreeSort()), 10, "0")).append(",").toString());	
         a.setTreeLevel(a.getParentCodes().replaceAll("[^,]", "").length() - 1);	
         TreeService var10000;	
         if (StringUtils.isNotBlank(treeNames)) {	
            var10000 = this;	
            a.setTreeNames((new StringBuilder()).insert(0, treeNames).append("/").append(a.getTreeName_()).toString());	
         } else {	
            a.setTreeNames(a.getTreeName_());	
            var10000 = this;	
         }	
	
         var10000.updateTreeData(a);	
         this.updateTreeLeaf(a);	
         TreeEntity a = (TreeEntity)this.newEntity();	
         a.setStatus((String)null);	
         a.setParentCode(a.getId());	
         List a;	
         if ((a = this.findList(a)).size() > 0) {	
            this.fixTreeData(a, a.getParentCode(), a.getParentCodes(), a.getTreeSorts(), a.getTreeNames());	
         }	
      }	
	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateTreeSort(TreeEntity entity) {	
      Global.assertDemoMode();	
      if (entity.getTreeSort() == null) {	
         entity.setTreeSort(30);	
      }	
	
      if (entity.getTreeSorts() == null) {	
         TreeEntity a;	
         TreeEntity var10001 = a = (TreeEntity)((TreeDao)this.dao).get(entity);	
         entity.setParent(a.getParent());	
         entity.setTreeSorts(var10001.getTreeSorts());	
      }	
	
      TreeEntity var10000;	
      if (entity.getParent() != null && !StringUtils.isBlank(entity.getParentCode()) && !"0".equals(entity.getParentCode())) {	
         entity.setParent((TreeEntity)super.get(entity.getParentCode()));	
         var10000 = entity;	
      } else {	
         var10000 = entity;	
         entity.setParent((TreeEntity)null);	
      }	
	
      if (var10000.getParent() == null) {	
         entity.setParent((TreeEntity)this.newEntity("0"));	
         entity.getParent().setTreeSorts("");	
      }	
	
      String a = entity.getTreeSorts();	
      entity.setTreeSorts(entity.getParent().getTreeSorts() + StringUtils.leftPad(String.valueOf(entity.getTreeSort()), 10, "0") + ",");	
      ((TreeDao)this.dao).updateTreeSort(entity);	
      TreeEntity a = (TreeEntity)this.newEntity();	
      a.setParentCodes((new StringBuilder()).insert(0, entity.getParentCodes()).append(entity.getId()).append("%").toString());	
      Iterator var5 = ((TreeDao)this.dao).findByParentCodesLike(a).iterator();	
	
      while(var5.hasNext()) {	
         TreeEntity a;	
         if ((a = (TreeEntity)var5.next()).getTreeSorts() != null && a != null) {	
            a.setTreeSorts(a.getTreeSorts().replace(a, entity.getTreeSorts()));	
            ((TreeDao)this.dao).updateTreeSort(a);	
         }	
      }	
	
   }	
	
   // $FF: synthetic method	
   @Transactional(	
      readOnly = false,	
      isolation = Isolation.READ_UNCOMMITTED	
   )	
   private void updateTreeLeaf(TreeEntity entity) {	
      if (!"0".equals(entity.getId())) {	
         try {	
            ((TreeDao)this.dao).updateTreeLeaf(entity);	
         } catch (BindingException var3) {	
            this.logger.warn("updateTreeLeaf", var3);	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   @Transactional(	
      readOnly = false,	
      isolation = Isolation.READ_UNCOMMITTED	
   )	
   private void updateTreeData(TreeEntity entity) {	
      if (!"0".equals(entity.getId())) {	
         try {	
            ((TreeDao)this.dao).updateTreeData(entity);	
         } catch (BindingException var3) {	
            this.logger.warn("updateParentCodes", var3);	
         }	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(TreeEntity entity) {	
      TreeEntity a = (TreeEntity)super.get(entity);	
      TreeEntity var10000;	
      if (entity.getParent() != null && !StringUtils.isBlank(entity.getParentCode()) && !"0".equals(entity.getParentCode())) {	
         entity.setParent((TreeEntity)super.get(entity.getParentCode()));	
         var10000 = entity;	
      } else {	
         var10000 = entity;	
         entity.setParent((TreeEntity)null);	
      }	
	
      if (var10000.getParent() == null) {	
         entity.setParent((TreeEntity)this.newEntity("0"));	
         entity.getParent().setParentCodes("");	
         entity.getParent().setTreeSorts("");	
      }	
	
      String a = entity.getParentCodes();	
      String a = entity.getTreeSorts();	
      String a = entity.getTreeNames();	
      entity.setParentCodes(entity.getParent().getParentCodes() + entity.getParentCode() + ",");	
      if (entity.getTreeSort() == null) {	
         entity.setTreeSort(30);	
      }	
	
      entity.setTreeSorts((new StringBuilder()).insert(0, entity.getParent().getTreeSorts()).append(StringUtils.leftPad(String.valueOf(entity.getTreeSort()), 10, "0")).append(",").toString());	
      entity.setTreeLevel(entity.getParentCodes().replaceAll("[^,]", "").length() - 1);	
      if (entity.getIsNewRecord()) {	
         entity.setTreeLeaf("1");	
      }	
	
      entity.setTreeName_((String)null);	
      TreeService var10;	
      if (StringUtils.isNotBlank(entity.getParent().getTreeNames())) {	
         var10 = this;	
         entity.setTreeNames((new StringBuilder()).insert(0, entity.getParent().getTreeNames()).append("/").append(entity.getTreeName_()).toString());	
      } else {	
         entity.setTreeNames(entity.getTreeName_());	
         var10 = this;	
      }	
	
      var10.save(entity);	
      if (a != null) {	
         TreeEntity a = (TreeEntity)this.newEntity();	
         a.setParentCodes((new StringBuilder()).insert(0, a).append(entity.getId()).append(",%").toString());	
         Iterator var8 = ((TreeDao)this.dao).findByParentCodesLike(a).iterator();	
	
         while(var8.hasNext()) {	
            TreeEntity a;	
            if ((a = (TreeEntity)var8.next()).getParentCodes() != null) {	
               a.setParentCodes(a.getParentCodes().replaceFirst(a, entity.getParentCodes()));	
               a.setTreeSorts(a.getTreeSorts().replaceFirst(a, entity.getTreeSorts()));	
               a.setTreeLevel(a.getParentCodes().replaceAll("[^,]", "").length() - 1);	
               a.setTreeNames(a.getTreeNames().replaceFirst(a, entity.getTreeNames()));	
               this.updateChildNode(a, entity);	
            }	
         }	
      }	
	
      if (a != null && a.getParent() != null) {	
         this.updateTreeLeaf(a.getParent());	
      }	
	
      if (entity != null && entity.getParent() != null && !StringUtils.equals(entity.getParentCode(), a == null ? "" : a.getParentCode())) {	
         this.updateTreeLeaf(entity.getParent());	
      }	
	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(TreeEntity entity) {	
      if (!StringUtils.isBlank(entity.getId())) {	
         TreeEntity a = (TreeEntity)this.newEntity();	
         a.setId(entity.getId());	
         a.setStatus(entity.getStatus());	
         TreeEntity a = (TreeEntity)this.newEntity();	
         a.getSqlMap().getWhere().and(entity.getIdColumnName(), QueryType.EQ, entity.getId()).or("parent_codes", QueryType.LIKE, (new StringBuilder()).insert(0, ",").append(entity.getId()).append(",").toString());	
         ((TreeDao)this.dao).updateStatusByEntity(a, a);	
         if (entity != null && entity.getParent() != null) {	
            this.updateTreeLeaf(entity.getParent());	
         }	
	
      }	
   }	
	
   public void convertChildList(List sourceList, List targetList, String parentCode) {	
      int a;	
      for(int var10000 = a = 0; var10000 < sourceList.size(); var10000 = a) {	
         TreeEntity a = (TreeEntity)sourceList.get(a);	
         if (parentCode != null && parentCode.equals(a.getParentCode())) {	
            targetList.add(a);	
            if (a.getChildList() == null) {	
               List a = ListUtils.newArrayList();	
               a.setChildList(a);	
            }	
	
            int a;	
            for(var10000 = a = 0; var10000 < sourceList.size(); var10000 = a) {	
               TreeEntity a = (TreeEntity)sourceList.get(a);	
               if (a.getId().equals(a.getParentCode())) {	
                  this.convertChildList(sourceList, a.getChildList(), a.getId());	
                  break;	
               }	
	
               ++a;	
            }	
         }	
	
         ++a;	
      }	
	
   }	
}	
