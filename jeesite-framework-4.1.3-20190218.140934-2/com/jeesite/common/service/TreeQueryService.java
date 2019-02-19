package com.jeesite.common.service;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.api.TreeServiceApi;	
import java.util.List;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public abstract class TreeQueryService extends CrudService implements TreeServiceApi {	
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
	
   public TreeEntity getLastByParentCode(TreeEntity entity) {	
      if (StringUtils.isBlank(entity.getParentCode())) {	
         entity.setParentCode("0");	
      }	
	
      ((TreeEntity)this.newEntity()).setParentCode(entity.getId());	
      entity.setPage(new Page(1, 1, -1L));	
      entity.getPage().setOrderBy((new StringBuilder()).insert(0, MapperHelper.getTable((BaseEntity)entity).alias()).append(".tree_sort DESC").toString());	
      List a;	
      return ListUtils.isNotEmpty(a = super.findList(entity)) ? (TreeEntity)a.get(0) : null;	
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
