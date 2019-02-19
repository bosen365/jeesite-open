package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.util.Iterator;	
import java.util.List;	
import org.hyperic.sigar.SigarException;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SelectSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   public String findList(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      a.append(entity.getSqlMap().getWhere().toSql());	
      a.append(entity.getSqlMap().getColumn().toSql());	
      a.append(entity.getSqlMap().getTable().toSql());	
      a.append("SELECT ");	
      a.append(a);	
      a.append(" FROM ");	
      a.append(a);	
      if (StringUtils.isNotBlank(a)) {	
         a.append(" WHERE ");	
         a.append(a);	
      }	
	
      a.append(" ORDER BY ");	
      a.append(entity.getSqlMap().getOrder().toSql());	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   public String getByEntity(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      a.append(entity.getSqlMap().getWhere().toSql());	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         a.append(entity.getSqlMap().getColumn().toSql());	
         a.append(entity.getSqlMap().getTable().toSql());	
         a.append("SELECT ");	
         a.append(a);	
         a.append(" FROM ");	
         a.append(a);	
         a.append(" WHERE ");	
         a.append(a);	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   public String get(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      List a = ListUtils.newArrayList();	
      Iterator var10 = MapperHelper.getColumns(a, a).iterator();	
	
      while(var10.hasNext()) {	
         Column a;	
         if ((a = (Column)var10.next()).isPK()) {	
            String a = MapperHelper.getAttrName(a);	
            this.addWhere(a, a, a, (String)null, a);	
         }	
      }	
	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else {	
         MapperHelper.addExtWhere(a, entity, a);	
         a.append(entity.getSqlMap().getColumn().toSql());	
         a.append(entity.getSqlMap().getTable().toSql());	
         a.append("SELECT ");	
         a.append(a);	
         a.append(" FROM ");	
         a.append(a);	
         a.append(" WHERE ");	
         a.append(a);	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   // $FF: synthetic method	
   private void addWhere(StringBuilder sqlWhere, Table t, Column c, String paramPrefix, String attrName) {	
      if (sqlWhere.length() != 0) {	
         sqlWhere.append(" AND ");	
      }	
	
      sqlWhere.append((new StringBuilder()).insert(0, t.alias()).append(".").toString());	
      sqlWhere.append(MapperHelper.getColumnName(c));	
      sqlWhere.append(" = ");	
      sqlWhere.append("#{");	
      if (StringUtils.isNotBlank(paramPrefix)) {	
         sqlWhere.append(paramPrefix + ".");	
      }	
	
      sqlWhere.append((new StringBuilder()).insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c)).toString());	
      sqlWhere.append("}");	
   }	
	
   public String findCount(BaseEntity entity) {	
      return (new StringBuilder()).insert(0, "/*return count*/").append(this.findList(entity)).toString();	
   }	
}	
