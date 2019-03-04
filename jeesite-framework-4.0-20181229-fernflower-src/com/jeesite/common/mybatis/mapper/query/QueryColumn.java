package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
	
public class QueryColumn implements Serializable {	
   private static final long serialVersionUID = 1L;	
   private BaseEntity entity;	
	
   public String toSql() {	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(this.entity);	
      List a = ListUtils.newArrayList();	
      MapperHelper.getColumns(a, a);	
      this.addEntityColumns(a, a, (String)null, a.alias());	
      JoinTable[] var5 = a.joinTable();	
      int var6 = var5.length;	
	
      int var7;	
      for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
         JoinTable a = var5[var7];	
         a = ListUtils.newArrayList();	
         QueryColumn var9;	
         if (a.columns().length > 0) {	
            var9 = this;	
            MapperHelper.getColumns(a, a);	
         } else {	
            MapperHelper.getColumns(MapperHelper.getTable(a.entity()), a);	
            var9 = this;	
         }	
	
         String var10003 = MapperHelper.getAttrName(a);	
         ++var7;	
         var9.addEntityColumns(a, a, var10003, a.alias());	
      }	
	
      a.append(MapperHelper.getSqlMapValue(this.entity, a.extColumnKeys(), QueryOrder.ALLATORIxDEMO ("]")));	
      return a.toString();	
   }	
	
   // $FF: synthetic method	
   private void addEntityColumns(StringBuilder sql, List columns, String attrNamePrefix, String tableAlias) {	
      Iterator var5;	
      Iterator var10000 = var5 = columns.iterator();	
	
      while(var10000.hasNext()) {	
         Column a;	
         String a = MapperHelper.getAttrName(a = (Column)var5.next());	
         if (StringUtils.isNotBlank(attrNamePrefix) && !"this".equals(attrNamePrefix)) {	
            a = (new StringBuilder()).insert(0, attrNamePrefix).append(QueryOrder.ALLATORIxDEMO ("_")).append(a).toString();	
         }	
	
         if (StringUtils.contains(sql, (new StringBuilder()).insert(0, """).append(a).append(QueryOrder.ALLATORIxDEMO ("S")).toString())) {	
            var10000 = var5;	
         } else {	
            if (sql.length() != 0) {	
               sql.append(", ");	
            }	
	
            if (StringUtils.isNotBlank(tableAlias)) {	
               sql.append(tableAlias + QueryOrder.ALLATORIxDEMO ("_"));	
            }	
	
            sql.append(a.name());	
            sql.append(" AS ");	
            sql.append(QueryOrder.ALLATORIxDEMO ("S"));	
            sql.append(a);	
            sql.append(""");	
            var10000 = var5;	
         }	
      }	
	
   }	
	
   public QueryColumn(BaseEntity var1) {	
      this.entity = var1;	
   }	
}	
