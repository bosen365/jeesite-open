package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.Swap;	
	
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
	
      a.append(MapperHelper.getSqlMapValue(this.entity, a.extColumnKeys(), ","));	
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
            a = (new StringBuilder()).insert(0, attrNamePrefix).append(".").append(a).toString();	
         }	
	
         if (StringUtils.contains(sql, (new StringBuilder()).insert(0, """).append(a).append(""").toString())) {	
            var10000 = var5;	
         } else {	
            if (sql.length() != 0) {	
               sql.append(", ");	
            }	
	
            if (StringUtils.isNotBlank(tableAlias)) {	
               sql.append(tableAlias + ".");	
            }	
	
            sql.append(a.name());	
            sql.append(" AS ");	
            sql.append(""");	
            sql.append(a);	
            sql.append(""");	
            var10000 = var5;	
         }	
      }	
	
   }	
	
   public QueryColumn(BaseEntity var1) {	
      this.entity = var1;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 4 ^ 4 << 1;	
      int var10001 = 3 << 3 ^ 4;	
      int var10002 = 3 ^ 5;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
}	
