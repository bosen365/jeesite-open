package com.jeesite.common.tests;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.utils.excel.ExcelImport;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.StringReader;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import org.apache.commons.io.FileUtils;	
import org.apache.ibatis.jdbc.ScriptRunner;	
import org.apache.poi.ss.usermodel.Row;	
import org.hyperic.sigar.ProcFd;	
	
public class BaseInitDataTests extends BaseSpringContextTests {	
   protected String excelFile;	
	
   protected void clearTable(Class entity) {	
      String a = MapperHelper.getTableName(entity);	
      String a = (new StringBuilder()).insert(0, "TRUNCATE TABLE ").append(a).toString();	
      if ("db2".equals(Global.getJdbcType())) {	
         a = (new StringBuilder()).insert(0, a).append(" IMMEDIATE").toString();	
      }	
	
      this.logger.debug(a);	
      this.jdbcTemplate.update(a);	
   }	
	
   protected void runScript(String sqlFile) throws IOException, SQLException {	
      InputStream a = null;	
      Connection a = null;	
	
      try {	
         sqlFile = (new StringBuilder()).insert(0, "db/").append(Global.getJdbcType()).append("/").append(sqlFile).toString();	
         this.logger.debug((new StringBuilder()).insert(0, "runScript: ").append(sqlFile).toString());	
         String a = StringUtils.replace(IOUtils.toString(a = FileUtils.openInputStream(new File(sqlFile)), "UTF-8"), "${_prefix}", Global.getTablePrefix());	
         String a = "______ql_cript_delimiter______";	
         a = a.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", (new StringBuilder()).insert(0, a).append("\n").toString());	
         a = this.dataSource.getConnection();	
         ScriptRunner a = new ScriptRunner(a);	
         a.setAutoCommit(true);	
         a.setDelimiter(a);	
         a.setFullLineDelimiter(false);	
         a.runScript(new StringReader(a));	
         this.logger.debug((new StringBuilder()).insert(0, "runS\tript Complete: ").append(sqlFile).toString());	
      } catch (Throwable var17) {	
         Throwable var10000;	
         label105: {	
            if (a != null) {	
               label103: {	
                  try {	
                     a.close();	
                  } catch (IOException var16) {	
                     break label103;	
                  }	
	
                  var10000 = a;	
                  break label105;	
               }	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 != null) {	
            label123: {	
               try {	
                  a.close();	
               } catch (SQLException var15) {	
                  break label123;	
               }	
	
               var10000 = var17;	
               throw var10000;	
            }	
         }	
	
         var10000 = var17;	
         throw var10000;	
      }	
	
      Connection var19;	
      label114: {	
         if (a != null) {	
            label112: {	
               try {	
                  a.close();	
               } catch (IOException var18) {	
                  break label112;	
               }	
	
               var19 = a;	
               break label114;	
            }	
         }	
	
         var19 = a;	
      }	
	
      if (var19 != null) {	
         try {	
            a.close();	
         } catch (SQLException var14) {	
         }	
      }	
   }	
	
   public void begin() {	
      super.begin();	
      if (!"true".equals(System.getProperty("jeesite.initdata"))) {	
         String a = "为了防止误操作，请运行时增加 -Djeesite.initdata=true 参数。";	
         this.logger.error(a);	
         throw new RuntimeException(a);	
      }	
   }	
	
   protected void initExcelData(Class entityClass, MethodCallback callback) throws Exception {	
      String a;	
      try {	
         InputStream a = null;	
	
         InputStream var10000;	
         label235: {	
            try {	
               a = ResourceUtils.getResourceFileStream(this.excelFile);	
            } catch (IOException var29) {	
               var10000 = a;	
               var29.printStackTrace();	
               break label235;	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 == null) {	
            a = (new StringBuilder()).insert(0, "没有找到初始数据文件。").append(this.excelFile).toString();	
            this.logger.error(a);	
            throw new Exception(a);	
         } else {	
            Map a = MapUtils.newHashMap();	
            List a = ListUtils.newArrayList();	
            ExcelImport a = new ExcelImport("xlsx", a, 0, entityClass.getSimpleName());	
            Object var7 = null;	
	
            try {	
               try {	
                  for(int a = a.getDataRowNum(); a < a.getLastDataRowNum(); ++a) {	
                     Boolean a = a == a.getDataRowNum();	
                     Row a;	
                     if ((a = a.getRow(a)) != null) {	
                        Object[] var10001 = new Object[1];	
                        boolean var10003 = true;	
                        var10001[0] = "new";	
                        Object a;	
                        if ((a = callback.execute(var10001)) == null) {	
                           a = entityClass.newInstance();	
                        }	
	
                        int a;	
                        for(int var33 = a = 0; var33 < a.getLastCellNum(); var33 = a) {	
                           Object a = a.getCellValue(a, a);	
                           if (a) {	
                              a.add(ObjectUtils.toString(a));	
                           } else {	
                              var10001 = new Object[4];	
                              var10003 = true;	
                              var10001[0] = "set";	
                              var10001[1] = a;	
                              var10001[2] = a.get(a);	
                              var10001[3] = a;	
                              if (callback.execute(var10001) == null) {	
                                 String a;	
                                 Object var34;	
                                 if (StringUtils.equalsIgnoreCase(a = ObjectUtils.toString(a), "__id__")) {	
                                    a = IdGen.nextId();	
                                    var34 = a;	
                                 } else {	
                                    label214: {	
                                       if (StringUtils.startsWithIgnoreCase(a, "__id__")) {	
                                          String a;	
                                          if ((a = (String)a.get(a)) != null) {	
                                             a = a;	
                                             var34 = a;	
                                             break label214;	
                                          }	
	
                                          a = IdGen.nextId();	
                                          a.put(a, (String)a);	
                                       }	
	
                                       var34 = a;	
                                    }	
                                 }	
	
                                 ReflectUtils.invokeSetter(var34, (String)a.get(a), a);	
                              }	
                           }	
	
                           ++a;	
                        }	
	
                        if (!a) {	
                           var10001 = new Object[2];	
                           var10003 = true;	
                           var10001[0] = "ave";	
                           var10001[1] = a;	
                           callback.execute(var10001);	
                        }	
                     }	
                  }	
               } catch (Throwable var27) {	
                  throw var27;	
               }	
            } catch (Throwable var28) {	
               Throwable var32;	
               if (a != null) {	
                  if (var7 != null) {	
                     try {	
                        a.close();	
                     } catch (Throwable var26) {	
                        var32 = var28;	
                        ((Throwable)var7).addSuppressed(var26);	
                        throw var32;	
                     }	
	
                     var32 = var28;	
                     throw var32;	
                  }	
	
                  a.close();	
               }	
	
               var32 = var28;	
               throw var32;	
            }	
	
            if (a != null) {	
               if (var7 != null) {	
                  try {	
                     a.close();	
                  } catch (Throwable var25) {	
                     ((Throwable)var7).addSuppressed(var25);	
                  }	
               } else {	
                  a.close();	
               }	
            }	
         }	
      } catch (Exception var30) {	
         a = (new StringBuilder()).insert(0, "entity: ").append(entityClass.toString()).toString();	
         this.logger.error(a, var30);	
         throw new Exception(a, var30);	
      }	
   }	
}	
