package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class InsertSqlProvider {	
   private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   // $FF: synthetic method	
   private String insertBatch(List entityList, String paramName) {	
      Assert.notEmpty(entityList, "没有需要插入的实体集合");	
      BaseEntity a = (BaseEntity)entityList.get(0);	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      StringBuilder a = new StringBuilder();	
      Table a = MapperHelper.getTable(a);	
      List a = ListUtils.newArrayList();	
      Iterator var9 = MapperHelper.getColumns(a, a).iterator();	
	
      while(true) {	
         label65:	
         while(true) {	
            String var14;	
            int var15;	
            String[] var10001;	
            boolean var10003;	
            for(Iterator var10000 = var9; var10000.hasNext(); var10000 = var9) {	
               Column a;	
               if (!(a = (Column)var9.next()).isInsert()) {	
                  continue label65;	
               }	
	
               var14 = a.name();	
               var10001 = new String[2];	
               var10003 = true;	
               var10001[0] = "corp_code";	
               var10001[1] = "corp_name";	
               if (!StringUtils.inString(var14, var10001) || Global.isUseCorpModel()) {	
                  String a = MapperHelper.getAttrName(a);	
	
                  int a;	
                  for(var15 = a = 0; var15 < entityList.size(); var15 = a) {	
                     Object var16 = entityList.get(a);	
                     String var17 = "preInsert";	
                     ++a;	
                     ReflectUtils.invokeMethod(var16, var17, (Class[])null, (Object[])null);	
                  }	
	
                  if (paramName == null) {	
                     if (ReflectUtils.invokeGetter(a, a) != null) {	
                        this.addSqlColumn(a, a);	
                        this.addSqlValue(a, a, a);	
                     }	
                  } else {	
                     this.addSqlColumn(a, a);	
                     this.addSqlValue(a, a, "{lis}." + a);	
                  }	
                  continue label65;	
               }	
            }	
	
            a.append("INSERT INTO ");	
            a.append(MapperHelper.getTableName(a, a));	
            a.append(" (");	
            a.append(a);	
            a.append(")");	
            StringBuilder var18;	
            if (paramName == null) {	
               a.append(" VALUES (");	
               a.append(a);	
               var18 = a;	
               a.append(")");	
            } else {	
               int a;	
               for(var15 = a = 0; var15 < entityList.size(); var15 = a) {	
                  var14 = MapperHelper.getDbName();	
                  var10001 = new String[1];	
                  var10003 = true;	
                  var10001[0] = "oracle";	
                  if (StringUtils.inString(var14, var10001)) {	
                     if (a != 0) {	
                        a.append(" UNION ALL ");	
                     }	
	
                     a.append(" SELECT ");	
                     a.append(StringUtils.replace(a.toString(), "{lit}", (new StringBuilder()).insert(0, "param1[").append(a).append("]").toString()));	
                     a.append(" FROM dual");	
                  } else {	
                     if (a == 0) {	
                        a.append(" VALUES ");	
                     }	
	
                     if (a != 0) {	
                        a.append(", ");	
                     }	
	
                     a.append(" (");	
                     a.append(StringUtils.replace(a.toString(), "{list}", (new StringBuilder()).insert(0, "param1[").append(a).append("]").toString()));	
                     a.append(")");	
                  }	
	
                  ++a;	
               }	
	
               var18 = a;	
            }	
	
            return var18.toString();	
         }	
      }	
   }	
	
   public String insertBatch(Map params) {	
      long a = System.currentTimeMillis();	
      List a = (List)params.get("param1");	
      String a = this.insertBatch(ListUtils.newArrayList(a), "param1");	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   public String insert(BaseEntity entity) {	
      long a = System.currentTimeMillis();	
      BaseEntity[] var10001 = new BaseEntity[1];	
      boolean var10003 = true;	
      var10001[0] = entity;	
      String a = this.insertBatch(ListUtils.newArrayList(var10001), (String)null);	
      if (this.logger.isDebugEnabled()) {	
         this.logger.debug((new StringBuilder()).insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a).toString());	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private void addSqlColumn(StringBuilder sqlColumn, Column c) {	
      if (sqlColumn.length() != 0) {	
         sqlColumn.append(", ");	
      }	
	
      sqlColumn.append(MapperHelper.getColumnName(c));	
   }	
	
   // $FF: synthetic method	
   private void addSqlValue(StringBuilder sqlValue, Column c, String attrName) {	
      if (sqlValue.length() != 0) {	
         sqlValue.append(", ");	
      }	
	
      sqlValue.append("#{");	
      sqlValue.append(attrName + MapperHelper.getColumnParamSuffix(c));	
      sqlValue.append("}");	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 5 << 4 ^ 5;	
      int var10001 = 5 << 3 ^ 3 ^ 5;	
      int var10002 = (2 ^ 5) << 3 ^ 5;	
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
}	
