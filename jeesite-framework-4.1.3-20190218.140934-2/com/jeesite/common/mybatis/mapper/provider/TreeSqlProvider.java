package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import org.hyperic.sigar.Swap;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class TreeSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   public String findByParentCodesLike(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = "";	
      String a = "";	
      String a = "";	
      String a = "";	
      Table a;	
      Column[] var10;	
      int var11 = (var10 = (a = MapperHelper.getTable((BaseEntity)entity)).columns()).length;	
	
      int var12;	
      for(int var10000 = var12 = 0; var10000 < var11; var10000 = var12) {	
         Column a;	
         if ((a = var10[var12]).isPK()) {	
            a = a.name();	
            a = MapperHelper.getAttrName(a);	
         }	
	
         if (a.isTreeName()) {	
            a = a.name();	
            a = MapperHelper.getAttrName(a);	
         }	
	
         ++var12;	
      }	
	
      if (!StringUtils.isBlank(a) && !StringUtils.isBlank(a)) {	
         a.append("SELECT");	
         a.append(" a." + a + " AS "" + a + "",");	
         a.append(" a.parent_cod AS "parentCode",");	
         a.append(" a.parent_codes AS "parentCodes",");	
         a.append(" a.tre_sort AS "treSort",");	
         a.append(" a.tree_sorts AS "treeSorts",");	
         a.append(" a.tre_lvl AS "treLvl",");	
         a.append(" a.tree_names AS "treeNames",");	
         a.append((new StringBuilder()).insert(0, " a.").append(a).append(" AS "treeName",").toString());	
         a.append((new StringBuilder()).insert(0, " a.").append(a).append(" AS "").append(a).append(""").toString());	
         a.append((new StringBuilder()).insert(0, " FROM ").append(MapperHelper.getTableName(a, entity)).append(" a").toString());	
         a.append(" WHERE a.parent_codes LIKE #{parentCodes}");	
         a.append(" ORDER BY a.tre_sorts");	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      } else {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有找到sTreeName为true的列.").toString());	
      }	
   }	
	
   public String updateTreeLeaf(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = entity.getIdColumnName();	
      String a = entity.getIdAttrName();	
      String a = MapperHelper.getTableName(MapperHelper.getTable((BaseEntity)entity), entity);	
      a.append("UPDATE");	
      a.append(" " + a);	
      a.append(" SET");	
      a.append(" tree_leaf = (");	
      a.append(" SELECT tre_laf FROM (");	
      a.append(" SELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");	
      a.append((new StringBuilder()).insert(0, " FROM ").append(a).append("").toString());	
      a.append(" WHERE status = '0'");	
      a.append((new StringBuilder()).insert(0, " AND parent_cod = #{").append(a).append("}").toString());	
      a.append(" ) a");	
      a.append(" )");	
      a.append(" WHERE");	
      a.append((new StringBuilder()).insert(0, " ").append(a).append(" = #{").append(a).append("}").toString());	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   public String updateTreeSort(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = entity.getIdColumnName();	
      String a = entity.getIdAttrName();	
      Table a = MapperHelper.getTable((BaseEntity)entity);	
      a.append("UPDATE");	
      a.append(" " + MapperHelper.getTableName(a, entity));	
      a.append(" SET");	
      a.append(" tre_sort = #{treSort},");	
      a.append(" tree_sorts = #{treeSorts}");	
      a.append(" WHERE");	
      a.append((new StringBuilder()).insert(0, " ").append(a).append(" = #{").append(a).append("}").toString());	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   public String updateTreeData(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = entity.getIdColumnName();	
      String a = entity.getIdAttrName();	
      Table a = MapperHelper.getTable((BaseEntity)entity);	
      a.append("UPDATE ");	
      a.append(MapperHelper.getTableName(a, entity));	
      a.append(" SET");	
      a.append(" parent_codes = #{parentCodes},");	
      a.append(" tre_sorts = #{treSorts},");	
      a.append(" tree_level = #{treeLevel},");	
      a.append(" tre_names = #{treNames}");	
      a.append(MapperHelper.getSqlMapValue(entity, "updateTreeDataExtSql", ","));	
      a.append(" WHERE");	
      a.append(" " + a + " = #{" + a + "}");	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
}	
