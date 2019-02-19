package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
	
public class QueryOrder implements Serializable {	
   private static final long serialVersionUID = 1L;	
   private String orderBy;	
   private BaseEntity entity;	
	
   // $FF: synthetic method	
   private void addOrderBy(StringBuilder sql, Table t, BaseEntity entity, String tableAlias) {	
      if (entity.getPage() != null && StringUtils.isNotBlank(entity.getPage().getOrderBy())) {	
         sql.append(entity.getPage().getOrderBy());	
      } else if (StringUtils.isNotBlank(this.getOrderBy())) {	
         sql.append(this.getOrderBy());	
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
	
   public String getOrderBy() {	
      return EncodeUtils.sqlFilter(this.orderBy);	
   }	
	
   public String toSql() {	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(this.entity);	
      this.addOrderBy(a, a, this.entity, a.alias());	
      return a.toString();	
   }	
	
   public void setOrderBy(String orderBy) {	
      this.orderBy = orderBy;	
   }	
	
   public QueryOrder(BaseEntity var1) {	
      this.entity = var1;	
   }	
}	
