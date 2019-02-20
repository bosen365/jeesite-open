package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UpdateSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
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
            a.append(MapperHelper.getTableName(a, a));	
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
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 5 << 1;	
      int var10001 = 5 << 3 ^ 1;	
      int var10002 = 2 << 3 ^ 3;	
      int var10003 = (s = (String)s).length();	
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
	
      sqlWhere.append((new StringBuilder()).insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c)).toString());	
      sqlWhere.append("}");	
   }	
	
   public String updateStatusByEntity(Map params) {	
      long a = System.currentTimeMillis();	
      BaseEntity a = (BaseEntity)params.get("param1");	
      BaseEntity a = (BaseEntity)params.get("param2");	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(a);	
      a.append(a.getSqlMap().getWhere().toSql((String)null, "param2"));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         this.addUpdateStatus(a, a, a, (StringBuilder)null, "param1");	
         if (a.length() == 0) {	
            throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(a.getClass()).append(" 没有isUpdate字段.").toString());	
         } else {	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a, a));	
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
	
   public String phyDelete(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      this.addUpdateStatus(a, entity, (StringBuilder)null, a, (String)null);	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else {	
         MapperHelper.addExtWhere(a, entity, a);	
         a.append("DELETE FROM ");	
         a.append(MapperHelper.getTableName(a, entity));	
         a.append(" WHERE ");	
         a.append(a);	
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
      Table a = MapperHelper.getTable(entity);	
      a.append(entity.getSqlMap().getWhere().toSql((String)null, (String)null));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         if (entity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
         }	
	
         this.addUpdateStatus(a, entity, a, (StringBuilder)null, (String)null);	
         StringBuilder var10000;	
         if (a.length() == 0) {	
            a.append("DELETE FROM ");	
            a.append(MapperHelper.getTableName(a, entity));	
            var10000 = a;	
            a.append(" WHERE ");	
            a.append(a);	
         } else {	
            var10000 = a;	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a, entity));	
            a.append(" SET ");	
            a.append(a);	
            a.append(" WHERE ");	
            a.append(a);	
         }	
	
         String a = var10000.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   // $FF: synthetic method	
   private void addUpdateStatus(Table t, BaseEntity entity, StringBuilder sqlColumn, StringBuilder sqlWhere, String paramPrefix) {	
      int a = false;	
      StringBuilder a = new StringBuilder();	
      List a = ListUtils.newArrayList();	
      Iterator var9 = MapperHelper.getColumns(t, a).iterator();	
	
      while(var9.hasNext()) {	
         Column a;	
         Column var10000 = a = (Column)var9.next();	
         String a = MapperHelper.getAttrName(var10000);	
         if (StringUtils.equals(var10000.name(), "update_by")) {	
            ReflectUtils.invokeMethod(entity, "preUpdate", (Class[])null, (Object[])null);	
         }	
	
         if (a.isPK()) {	
            if (sqlWhere != null) {	
               this.addWhere(sqlWhere, a, paramPrefix, a);	
            }	
         } else {	
            String var15 = a.name();	
            String[] var10001 = new String[3];	
            boolean var10003 = true;	
            var10001[0] = "update_by";	
            var10001[1] = "update_date";	
            var10001[2] = "status";	
            if (StringUtils.inString(var15, var10001) && sqlColumn != null) {	
               Object a = ReflectUtils.invokeGetter(entity, a);	
               this.addColumn(a, a, paramPrefix, a, a);	
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
      if (sqlWhere != null && (a = entity.getId_in()) != null && a.length > 0) {	
         (sqlWhere = new StringBuilder()).append(entity.getIdColumnName());	
         sqlWhere.append(" IN (");	
         int a = 0;	
	
         for(int var16 = a; var16 < a.length; var16 = a) {	
            if (a != 0) {	
               sqlWhere.append(", ");	
            }	
	
            sqlWhere.append("#{sqlMap.where.");	
            sqlWhere.append(entity.getIdColumnName());	
            StringBuilder var17 = (new StringBuilder()).append("#IN1.val[").append(a);	
            String var10004 = "]}";	
            ++a;	
            sqlWhere.append(var17.append(var10004).toString());	
         }	
	
         sqlWhere.append(")");	
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
	
      this.addUpdateStatus(a, entity, a, a, (String)null);	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else {	
         MapperHelper.addExtWhere(a, entity, a);	
         StringBuilder var10000;	
         if (a.length() == 0) {	
            a.append("DELETE FROM ");	
            a.append(MapperHelper.getTableName(a, entity));	
            var10000 = a;	
            a.append(" WHERE ");	
            a.append(a);	
         } else {	
            var10000 = a;	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a, entity));	
            a.append(" SET ");	
            a.append(a);	
            a.append(" WHERE ");	
            a.append(a);	
         }	
	
         String a = var10000.toString();	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
         }	
	
         return a;	
      }	
   }	
	
   // $FF: synthetic method	
   private void addColumn(StringBuilder sqlColumn, Column c, String paramPrefix, String attrName, Object attrValue) {	
      if (attrValue != null || c.isUpdateForce()) {	
         if (sqlColumn.length() != 0) {	
            sqlColumn.append(", ");	
         }	
	
         sqlColumn.append(MapperHelper.getColumnName(c));	
         sqlColumn.append(" = ");	
         sqlColumn.append("#");	
         if (StringUtils.isNotBlank(paramPrefix)) {	
            sqlColumn.append(paramPrefix + ".");	
         }	
	
         sqlColumn.append((new StringBuilder()).insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c)).toString());	
         sqlColumn.append("}");	
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
         } else if (a.isUpdate() || a.isUpdateForce()) {	
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
            StringBuilder var10003 = (new StringBuilder()).append("#IN1.val[").append(a);	
            String var10004 = "]}";	
            ++a;	
            a.append(var10003.append(var10004).toString());	
         }	
	
         a.append(")");	
      }	
	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else {	
         MapperHelper.addExtWhere(a, entity, a);	
         if (a.length() == 0) {	
            throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
         } else {	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a, entity));	
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
	
   public String updateStatus(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      this.addUpdateStatus(a, entity, a, a, (String)null);	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
      } else {	
         MapperHelper.addExtWhere(a, entity, a);	
         if (a.length() == 0) {	
            throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
         } else {	
            a.append("UPDATE ");	
            a.append(MapperHelper.getTableName(a, entity));	
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
	
   public String phyDeleteByEntity(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(entity);	
      a.append(entity.getSqlMap().getWhere().toSql((String)null, (String)null));	
      if (a.length() == 0) {	
         throw new MapperException((new StringBuilder()).insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
      } else {	
         a.append("DELETE FROM ");	
         a.append(MapperHelper.getTableName(a, entity));	
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
