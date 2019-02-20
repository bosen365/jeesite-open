package com.jeesite.modules.gen.service;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.gen.dao.GenDataDictDao;	
import com.jeesite.modules.gen.dao.GenTableColumnDao;	
import com.jeesite.modules.gen.dao.GenTableDao;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import com.jeesite.modules.gen.entity.config.GenTemplate;	
import com.jeesite.modules.gen.utils.GenUtils;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.CpuPerc;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
@ConditionalOnProperty(	
   name = {"gen.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class GenTableService extends CrudService {	
   @Autowired	
   private GenTableColumnDao genTableColumnDao;	
   @Autowired	
   private GenDataDictDao genDataDictDao;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(GenTable genTable) {	
      super.save(genTable);	
      Iterator var2 = genTable.getColumnList().iterator();	
	
      while(var2.hasNext()) {	
         GenTableColumn a;	
         GenTableColumn var10000 = a = (GenTableColumn)var2.next();	
         var10000.setGenTable(genTable);	
         if (StringUtils.isBlank(var10000.getId())) {	
            this.genTableColumnDao.insert(a);	
         } else if ("1".equals(a.getStatus())) {	
            this.genTableColumnDao.delete(a);	
         } else {	
            this.genTableColumnDao.update(a);	
         }	
      }	
	
   }	
	
   public Page findPage(GenTable genTable) {	
      String a = (new StringBuilder()).insert(0, "(SELECT count(1) FROM ").append(MapperHelper.getTableName((BaseEntity)genTable)).append(" WHERE parent_table_name=a.table_name) AS "childNum"").toString();	
      genTable.getSqlMap().add("extColumn", a);	
      return super.findPage(genTable);	
   }	
	
   public List findGenBaseDirList(GenTable genTable) {	
      genTable.setPage(new Page(1, 20, -1L));	
      return ((GenTableDao)this.dao).findGenBaseDirList(genTable);	
   }	
	
   public GenTable getFromDb(GenTable genTable) {	
      if (StringUtils.isNotBlank(genTable.getTableName())) {	
         GenTableService var10000;	
         String a;	
         if (StringUtils.isNotBlank(a = genTable.getDataSourceName())) {	
            try {	
               DataSourceHolder.setDataSourceName(a);	
            } catch (Exception var10) {	
               return genTable;	
            }	
	
            var10000 = this;	
         } else {	
            var10000 = this;	
         }	
	
         List a;	
         if ((a = var10000.genDataDictDao.findTableList(genTable)).size() > 0) {	
            if (genTable.getIsNewRecord()) {	
               genTable = (GenTable)a.get(0);	
               if (StringUtils.isNotBlank(a)) {	
                  genTable.setDataSourceName(a);	
               }	
	
               if (StringUtils.isBlank(genTable.getComments())) {	
                  genTable.setComments(genTable.getTableName());	
               }	
	
               genTable.setFunctionName(genTable.getComments());	
               genTable.setFunctionNameSimple(genTable.getComments());	
               String a;	
               GenTable var12;	
               if (StringUtils.startsWith(genTable.getTableName(), Global.getTablePrefix())) {	
                  a = StringUtils.substringAfter(genTable.getTableName(), Global.getTablePrefix());	
                  var12 = genTable;	
                  genTable.setClassName(StringUtils.capCamelCase(a));	
               } else {	
                  genTable.setClassName(StringUtils.capCamelCase(genTable.getTableName()));	
                  var12 = genTable;	
               }	
	
               if (StringUtils.startsWith(var12.getClassName(), "Sys")) {	
                  genTable.setClassName(StringUtils.substringAfter(genTable.getClassName(), "Sys"));	
               }	
	
               label95: {	
                  if (StringUtils.isBlank(genTable.getPackageName())) {	
                     if (StringUtils.isNotBlank(a = Global.getConfig("gen.defaultPackageName"))) {	
                        genTable.setPackageName(a);	
                        var12 = genTable;	
                        break label95;	
                     }	
	
                     genTable.setPackageName("com.jeesite.modules");	
                  }	
	
                  var12 = genTable;	
               }	
	
               if (StringUtils.isBlank(var12.getModuleName())) {	
                  if (StringUtils.startsWith(a = genTable.getTableName(), Global.getTablePrefix())) {	
                     a = StringUtils.substringAfter(a, Global.getTablePrefix());	
                  }	
	
                  genTable.setModuleName(StringUtils.substringBefore(a, "_"));	
               }	
            }	
	
            List a;	
            Iterator var5 = (a = this.genDataDictDao.findTableColumnList(genTable)).iterator();	
	
            while(true) {	
               GenTableColumn a;	
               boolean a;	
               Iterator var8;	
               if (!var5.hasNext()) {	
                  var5 = genTable.getColumnList().iterator();	
	
                  while(var5.hasNext()) {	
                     a = (GenTableColumn)var5.next();	
                     a = false;	
                     var8 = a.iterator();	
	
                     while(var8.hasNext()) {	
                        if (((GenTableColumn)var8.next()).getColumnName().equals(a.getColumnName())) {	
                           a = true;	
                        }	
                     }	
	
                     if (!a) {	
                        a.setStatus("1");	
                     }	
                  }	
	
                  genTable.setPkList(this.genDataDictDao.findTablePK(genTable));	
                  GenUtils.initColumnField(genTable);	
                  break;	
               }	
	
               a = (GenTableColumn)var5.next();	
               a = false;	
               var8 = genTable.getColumnList().iterator();	
	
               while(var8.hasNext()) {	
                  if (((GenTableColumn)var8.next()).getColumnName().equals(a.getColumnName())) {	
                     a = true;	
                  }	
               }	
	
               if (!a) {	
                  genTable.getColumnList().add(a);	
               }	
            }	
         }	
	
         if (StringUtils.isNotBlank(a)) {	
            DataSourceHolder.clearDataSourceName();	
         }	
      }	
	
      return genTable;	
   }	
	
   public boolean checkTableName(String tableName) {	
      if (StringUtils.isBlank(tableName)) {	
         return true;	
      } else {	
         return ((GenTableDao)this.dao).get(new GenTable(tableName)) == null;	
      }	
   }	
	
   public String generateCode(GenTable genTable) {	
      StringBuilder a = new StringBuilder();	
      GenConfig var10000 = GenUtils.getConfig();	
      List a = GenUtils.getTemplateList(var10000, genTable.getTplCategory(), false);	
      List a;	
      if ((a = GenUtils.getTemplateList(var10000, genTable.getTplCategory(), true)).size() > 0) {	
         GenTable a = new GenTable();	
         a.setStatus("0");	
         a.setParentTableName(genTable.getTableName());	
         genTable.setChildList(((GenTableDao)this.dao).findList(a));	
      }	
	
      Iterator var15;	
      if (a.size() > 0 && genTable.getChildList().size() > 0) {	
         Iterator var11 = genTable.getChildList().iterator();	
	
         while(var11.hasNext()) {	
            GenTable a = (GenTable)var11.next();	
            a.setParent(genTable);	
            a.setColumnList(this.genTableColumnDao.findList(new GenTableColumn(a)));	
            Map a = GenUtils.getDataModel(a);	
            Iterator var9;	
            var15 = var9 = a.iterator();	
	
            while(var15.hasNext()) {	
               GenTemplate a = (GenTemplate)var9.next();	
               var15 = var9;	
               a.append(GenUtils.generateToFile(a, a, a));	
            }	
         }	
      }	
	
      Map a = GenUtils.getDataModel(genTable);	
      Iterator var13;	
      var15 = var13 = a.iterator();	
	
      while(var15.hasNext()) {	
         GenTemplate a = (GenTemplate)var13.next();	
         var15 = var13;	
         a.append(GenUtils.generateToFile(a, a, genTable));	
      }	
	
      return a.toString();	
   }	
	
   public GenTable get(String tableName) {	
      GenTable a;	
      if ((a = (GenTable)super.get(tableName)) != null) {	
         GenTableColumn a = new GenTableColumn(a);	
         a.setColumnList(this.genTableColumnDao.findList(a));	
      }	
	
      return a;	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(GenTable genTable) {	
      super.delete(genTable);	
      if (!StringUtils.isBlank(genTable.getTableName())) {	
         GenTableColumn a = new GenTableColumn();	
         a.setGenTable(new GenTable(genTable.getTableName()));	
         this.genTableColumnDao.deleteByEntity(a);	
      }	
   }	
	
   public List findListFromDb(GenTable genTable) {	
      GenTableService var10000;	
      String a;	
      if (StringUtils.isNotBlank(a = genTable.getDataSourceName())) {	
         try {	
            DataSourceHolder.setDataSourceName(a);	
         } catch (Exception var4) {	
            return ListUtils.newArrayList();	
         }	
	
         var10000 = this;	
      } else {	
         var10000 = this;	
      }	
	
      List a = var10000.genDataDictDao.findTableList(genTable);	
      if (StringUtils.isNotBlank(a)) {	
         DataSourceHolder.clearDataSourceName();	
      }	
	
      return a;	
   }	
}	
