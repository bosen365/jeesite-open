package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
	
public class QueryOrder implements Serializable {	
   private BaseEntity entity;	
   private static final long serialVersionUID = 1L;	
	
   public String toSql() {	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(this.entity);	
      this.addOrderBy(a, a, this.entity, a.alias());	
      return a.toString();	
   }	
	
   // $FF: synthetic method	
   private void addOrderBy(StringBuilder sql, Table t, BaseEntity entity, String tableAlias) {	
      if (entity.getPage() != null && StringUtils.isNotBlank(entity.getPage().getOrderBy())) {	
         sql.append(entity.getPage().getOrderBy());	
      } else if (StringUtils.isNotBlank(t.orderBy())) {	
         sql.append(t.orderBy());	
      } else {	
         String[] a = StringUtils.split(entity.getIdColumnName(), "#");	
	
         int a;	
         for(int var10000 = a = 0; var10000 < a.length; var10000 = a) {	
            if (a != 0) {	
               sql.append(", ");	
            }	
	
            StringBuilder var10001 = (new StringBuilder()).insert(0, tableAlias).append(".");	
            String var10002 = a[a];	
            ++a;	
            sql.append(var10001.append(var10002).toString());	
         }	
	
      }	
   }	
	
   public QueryOrder(BaseEntity var1) {	
      this.entity = var1;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 2 ^ 5;	
      int var10001 = (2 ^ 5) << 4 ^ 1;	
      int var10002 = 4 << 4 ^ 5;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
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
