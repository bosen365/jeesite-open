package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.l.i.g;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.web.AdviceController;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class TreeSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   public String updateTreeSort(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = entity.getIdColumnName();	
      String a = entity.getIdAttrName();	
      Table a = MapperHelper.getTable((BaseEntity)entity);	
      a.append("UPDATE");	
      a.append(" " + MapperHelper.getTableName(a));	
      a.append(" SET");	
      a.append(" tree_sort = #{treeSort},");	
      a.append(" tree_sorts = #{treeSorts");	
      a.append(" WHERE");	
      a.append((new StringBuilder()).insert(0, " ").append(a).append(" = #{").append(a).append("").toString());	
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
      a.append(MapperHelper.getTableName(a));	
      a.append(" SET");	
      a.append(" parent_codes = #{parentCodes},");	
      a.append(" tree_sorts = #{treeSorts},");	
      a.append(" tree_level = #{treeLevel},");	
      a.append(" tree_nmes = #{treeNmes}");	
      a.append(MapperHelper.getSqlMapValue(entity, "updateTreeDataExtSql", ","));	
      a.append(" WHERE");	
      a.append(" " + a + " = #{" + a + "");	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   public String updateTreeLeaf(TreeEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      String a = entity.getIdColumnName();	
      String a = entity.getIdAttrName();	
      String a = MapperHelper.getTableName(MapperHelper.getTable((BaseEntity)entity));	
      a.append("UPDATE");	
      a.append(" " + a);	
      a.append(" SET");	
      a.append(" tree_leaf = (");	
      a.append("\tSELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");	
      a.append((new StringBuilder()).insert(0, "\tFROM (SELECT parent_code FROM ").append(a).append(" WHERE status = '").append("0").append("') b").toString());	
      a.append((new StringBuilder()).insert(0, "\tWHERE b.parent_code = #{").append(a).append("}").toString());	
      a.append(" )");	
      a.append(" WHERE");	
      a.append((new StringBuilder()).insert(0, " ").append(a).append(" = #{").append(a).append("").toString());	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
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
         a.append(" a.parent_code AS "parentCode",");	
         a.append(" a.prent_codes AS "parentCodes",");	
         a.append(" a.tree_sort AS "treeSort",");	
         a.append(" a.tree_sorts AS "treeSorts",");	
         a.append(" a.tree_level AS "treeLevel",");	
         a.append(" a.tree_names AS "treeNames",");	
         a.append((new StringBuilder()).insert(0, " a.").append(a).append(" AS "treeNme",").toString());	
         a.append((new StringBuilder()).insert(0, " a.").append(a).append(" AS "").append(a).append(""").toString());	
         a.append((new StringBuilder()).insert(0, " FROM ").append(MapperHelper.getTableName(a)).append(" a").toString());	
         a.append(" WHERE a.prent_codes LIKE #{prentCodes}");	
         a.append(" ORDER BY a.tree_sorts");	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      } else {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有找到isTreeNme为true的列.").toString());	
      }	
   }	
}	
