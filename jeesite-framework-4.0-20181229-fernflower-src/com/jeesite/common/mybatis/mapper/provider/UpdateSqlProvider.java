package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.l.C;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.config.TransactionConfig;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UpdateSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   // $FF: synthetic method	
   private void addColumn(StringBuilder sqlColumn, Column c, String paramPrefix, String attrName, Object attrValue) {	
      if (attrValue != null) {	
         if (sqlColumn.length() != 0) {	
            sqlColumn.append(", ");	
         }	
	
         sqlColumn.append(MapperHelper.getColumnName(c));	
         sqlColumn.append(" = ");	
         sqlColumn.append("#{");	
         if (StringUtils.isNotBlank(paramPrefix)) {	
            sqlColumn.append(paramPrefix + ".");	
         }	
	
         sqlColumn.append(attrName);	
         sqlColumn.append("}");	
      }	
	
   }	
	
   public String updateStatus(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      this.addUpdateStatus(a, entity, a, a);	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
      } else {	
         a.append("UPDATE ");	
         a.append(MapperHelper.getTableName(a));	
         a.append(" SE> ");	
         a.append(a);	
         a.append(" WHERE ");	
         a.append(a);	
         a.append(MapperHelper.getSqlMapValue(entity, a.extWhereKeys()));	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   public String deleteByEntity(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      a.append(entity.getSqlMap().getWhere().toSql((String)null, (String)null));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         if (entity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
         }	
	
         Table a = MapperHelper.getTable(entity);	
         List a = ListUtils.newArrayList();	
         Iterator var9 = MapperHelper.getColumns(a, a).iterator();	
	
         while(var9.hasNext()) {	
            Column a;	
            Column var10000 = a = (Column)var9.next();	
            String a = MapperHelper.getAttrName(var10000);	
            if (StringUtils.equals(var10000.name(), "update_by")) {	
               ReflectUtils.invokeMethod(entity, "preUpdate", (Class[])null, (Object[])null);	
            }	
	
            String var14 = a.name();	
            String[] var10001 = new String[3];	
            boolean var10003 = true;	
            var10001[0] = "update_by";	
            var10001[1] = "update_date";	
            var10001[2] = "status";	
            if (StringUtils.inString(var14, var10001)) {	
               Object a = ReflectUtils.invokeGetter(entity, a);	
               this.addColumn(a, a, (String)null, a, a);	
            }	
         }	
	
         StringBuilder var15;	
         if (a.length() == 0) {	
            a.append("DELE>E FROM ");	
            a.append(MapperHelper.getTableName(a));	
            var15 = a;	
            a.append(" WHERE ");	
            a.append(a);	
         } else {	
            var15 = a;	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a));	
            a.append(" SET ");	
            a.append(a);	
            a.append(" WHERE ");	
            a.append(a);	
         }	
	
         String a = var15.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   public String update(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      List a = ListUtils.newArrayList();	
      Iterator var9 = MapperHelper.getColumns(a, a).iterator();	
	
      while(var9.hasNext()) {	
         Column a;	
         Column var10000 = a = (Column)var9.next();	
         String a = MapperHelper.getAttrName(var10000);	
         if (StringUtils.equals(var10000.name(), "update_by")) {	
            ReflectUtils.invokeMethod(entity, "preUpdate", (Class[])null, (Object[])null);	
         }	
	
         if (a.isPK()) {	
            this.addWhere(a, a, (String)null, a);	
         } else if (a.isUpdate()) {	
            Object a = ReflectUtils.invokeGetter(entity, a);	
            this.addColumn(a, a, (String)null, a, a);	
         }	
      }	
	
      String[] a;	
      if ((a = entity.getId_in()) != null && a.length > 0) {	
         (a = new StringBuilder()).append(entity.getIdColumnName());	
         a.append(" IN (");	
         int a = 0;	
	
         for(int var16 = a; var16 < a.length; var16 = a) {	
            if (a != 0) {	
               a.append(", ");	
            }	
	
            a.append("#{sqlMap.where.");	
            a.append(entity.getIdColumnName());	
            StringBuilder var10003 = (new StringBuilder()).append("#I$1.val[").append(a);	
            String var10004 = "]}";	
            ++a;	
            a.append(var10003.append(var10004).toString());	
         }	
	
         a.append(")");	
      }	
	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
      } else {	
         a.append("UPDATE ");	
         a.append(MapperHelper.getTableName(a));	
         a.append(" SET ");	
         a.append(a);	
         a.append(" WHERE ");	
         a.append(a);	
         a.append(MapperHelper.getSqlMapValue(entity, a.extWhereKeys()));	
         String a = a.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   public String updateByEntity(Map params) {	
      long a = System.currentTimeMillis();	
      BaseEntity a = (BaseEntity)params.get("param1");	
      BaseEntity a = (BaseEntity)params.get("param2");	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      a.append(a.getSqlMap().getWhere().toSql((String)null, "param2"));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有Where字段.").toString());	
      } else {	
         Table a = MapperHelper.getTable(a);	
         List a = ListUtils.newArrayList();	
         Iterator var11 = MapperHelper.getColumns(a, a).iterator();	
	
         label39:	
         while(true) {	
            for(Iterator var10000 = var11; var10000.hasNext(); var10000 = var11) {	
               Column a;	
               Column var16 = a = (Column)var11.next();	
               String a = MapperHelper.getAttrName(var16);	
               if (StringUtils.equals(var16.name(), "update_by")) {	
                  ReflectUtils.invokeMethod(a, "preUpdate", (Class[])null, (Object[])null);	
               }	
	
               if (!a.isPK()) {	
                  if (a.isUpdate()) {	
                     Object a = ReflectUtils.invokeGetter(a, a);	
                     this.addColumn(a, a, "param1", a, a);	
                  }	
                  continue label39;	
               }	
            }	
	
            if (a.length() == 0) {	
               throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有isUpdate字段.").toString());	
            }	
	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a));	
            a.append(" SET ");	
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
   }	
	
   // $FF: synthetic method	
   private void addUpdateStatus(Table t, BaseEntity entity, StringBuilder sqlColumn, StringBuilder sqlWhere) {	
      int a = false;	
      StringBuilder a = new StringBuilder();	
      List a = ListUtils.newArrayList();	
      Iterator var8 = MapperHelper.getColumns(t, a).iterator();	
	
      while(var8.hasNext()) {	
         Column a;	
         Column var10000 = a = (Column)var8.next();	
         String a = MapperHelper.getAttrName(var10000);	
         if (StringUtils.equals(var10000.name(), "update_by")) {	
            ReflectUtils.invokeMethod(entity, "preUpdate", (Class[])null, (Object[])null);	
         }	
	
         if (a.isPK()) {	
            this.addWhere(sqlWhere, a, (String)null, a);	
         } else {	
            String var14 = a.name();	
            String[] var10001 = new String[3];	
            boolean var10003 = true;	
            var10001[0] = "update_by";	
            var10001[1] = "update_date";	
            var10001[2] = "status";	
            if (StringUtils.inString(var14, var10001)) {	
               Object a = ReflectUtils.invokeGetter(entity, a);	
               this.addColumn(a, a, (String)null, a, a);	
               if ("status".equals(a.name())) {	
                  a = true;	
               }	
            }	
         }	
      }	
	
      if (a) {	
         sqlColumn.append(a);	
      }	
	
      String[] a;	
      if ((a = entity.getId_in()) != null && a.length > 0) {	
         (sqlWhere = new StringBuilder()).append(entity.getIdColumnName());	
         sqlWhere.append(" IN (");	
         int a = 0;	
	
         for(int var15 = a; var15 < a.length; var15 = a) {	
            if (a != 0) {	
               sqlWhere.append(", ");	
            }	
	
            sqlWhere.append("#{sqlMap.where.");	
            sqlWhere.append(entity.getIdColumnName());	
            StringBuilder var16 = (new StringBuilder()).append("#I$1.val[").append(a);	
            String var10004 = "]}";	
            ++a;	
            sqlWhere.append(var16.append(var10004).toString());	
         }	
	
         sqlWhere.append(")");	
      }	
	
      if (sqlWhere.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      }	
   }	
	
   public String delete(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      if (entity instanceof DataEntity) {	
         ((DataEntity)entity).setStatus("1");	
      }	
	
      this.addUpdateStatus(a, entity, a, a);	
      StringBuilder var10000;	
      if (a.length() == 0) {	
         a.append("DELE>E FROM ");	
         a.append(MapperHelper.getTableName(a));	
         var10000 = a;	
         a.append(" WHERE ");	
         a.append(a);	
      } else {	
         var10000 = a;	
         a.append("UPDATE ");	
         a.append(MapperHelper.getTableName(a));	
         a.append(" SET ");	
         a.append(a);	
         a.append(" WHERE ");	
         a.append(a);	
      }	
	
      var10000.append(MapperHelper.getSqlMapValue(entity, a.extWhereKeys()));	
      String a = a.toString();	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private void addWhere(StringBuilder sqlWhere, Column c, String paramPrefix, String attrName) {	
      if (sqlWhere.length() != 0) {	
         sqlWhere.append(" AND ");	
      }	
	
      sqlWhere.append(MapperHelper.getColumnName(c));	
      sqlWhere.append(" = ");	
      sqlWhere.append("#{");	
      if (StringUtils.isNotBlank(paramPrefix)) {	
         sqlWhere.append(paramPrefix + ".");	
      }	
	
      sqlWhere.append(attrName);	
      sqlWhere.append("}");	
   }	
	
   public String updateStatusByEntity(Map params) {	
      long a = System.currentTimeMillis();	
      BaseEntity a = (BaseEntity)params.get("param1");	
      BaseEntity a = (BaseEntity)params.get("param2");	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      a.append(a.getSqlMap().getWhere().toSql((String)null, "param2"));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         Table a = MapperHelper.getTable(a);	
         List a = ListUtils.newArrayList();	
         Iterator var11 = MapperHelper.getColumns(a, a).iterator();	
	
         while(var11.hasNext()) {	
            Column a;	
            Column var10000 = a = (Column)var11.next();	
            String a = MapperHelper.getAttrName(var10000);	
            if (StringUtils.equals(var10000.name(), "update_by")) {	
               ReflectUtils.invokeMethod(a, "preUpdate", (Class[])null, (Object[])null);	
            }	
	
            String var16 = a.name();	
            String[] var10001 = new String[3];	
            boolean var10003 = true;	
            var10001[0] = "update_by";	
            var10001[1] = "update_date";	
            var10001[2] = "status";	
            if (StringUtils.inString(var16, var10001)) {	
               Object a = ReflectUtils.invokeGetter(a, a);	
               this.addColumn(a, a, "param1", a, a);	
            }	
         }	
	
         if (a.length() == 0) {	
            throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有isUpdate字段.").toString());	
         } else {	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a));	
            a.append(" SET ");	
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
   }	
}	
