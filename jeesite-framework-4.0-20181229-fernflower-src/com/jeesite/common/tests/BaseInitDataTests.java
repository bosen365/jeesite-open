package com.jeesite.common.tests;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.utils.excel.ExcelImport;	
import com.jeesite.modules.job.l.l;	
import java.io.File;	
import java.io.FileInputStream;	
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
	
public class BaseInitDataTests extends BaseSpringContextTests {	
   protected String excelFile;	
	
   protected void runScript(String sqlFile) throws IOException, SQLException {	
      Connection a = null;	
      FileInputStream a = null;	
	
      try {	
         sqlFile = (new StringBuilder()).insert(0, "db/").append(Global.getJdbcType()).append("/").append(sqlFile).toString();	
         this.logger.debug((new StringBuilder()).insert(0, "runScript：").append(sqlFile).toString());	
         String a = StringUtils.replace(IOUtils.toString(a = FileUtils.openInputStream(new File(sqlFile)), "UTF-8"), "${_prefix}", Global.getTablePrefix());	
         a = this.dataSource.getConnection();	
         ScriptRunner a;	
         (a = new ScriptRunner(a)).setAutoCommit(true);	
         a.runScript(new StringReader(a));	
         this.logger.debug((new StringBuilder()).insert(0, "runScript Complete：").append(sqlFile).toString());	
      } catch (Throwable var13) {	
         IOUtils.closeQuietly(a);	
         Throwable var10000;	
         if (a != null) {	
            label71: {	
               try {	
                  a.close();	
               } catch (SQLException var12) {	
                  break label71;	
               }	
	
               var10000 = var13;	
               throw var10000;	
            }	
         }	
	
         var10000 = var13;	
         throw var10000;	
      }	
	
      IOUtils.closeQuietly(a);	
      if (a != null) {	
         try {	
            a.close();	
         } catch (SQLException var11) {	
         }	
      }	
   }	
	
   public void begin() {	
      super.begin();	
   }	
	
   protected void initExcelData(Class entityClass, MethodCallback callback) throws Exception {	
      String a;	
      try {	
         InputStream a = null;	
	
         InputStream var10000;	
         label80: {	
            try {	
               a = ResourceUtils.getResourceFileStream(this.excelFile);	
            } catch (IOException var16) {	
               var10000 = a;	
               var16.printStackTrace();	
               break label80;	
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
	
            ExcelImport a;	
            for(int a = (a = new ExcelImport("xlsx", a, 0, entityClass.getSimpleName())).getDataRowNum(); a < a.getLastDataRowNum(); ++a) {	
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
                  for(int var19 = a = 0; var19 < a.getLastCellNum(); var19 = a) {	
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
                           Object var20;	
                           if (StringUtils.equalsIgnoreCase(a = ObjectUtils.toString(a), "__id__")) {	
                              a = IdGen.nextId();	
                              var20 = a;	
                           } else {	
                              label65: {	
                                 if (StringUtils.startsWithIgnoreCase(a, "__id__")) {	
                                    String a;	
                                    if ((a = (String)a.get(a)) != null) {	
                                       a = a;	
                                       var20 = a;	
                                       break label65;	
                                    }	
	
                                    a = IdGen.nextId();	
                                    a.put(a, (String)a);	
                                 }	
	
                                 var20 = a;	
                              }	
                           }	
	
                           ReflectUtils.invokeSetter(var20, (String)a.get(a), a);	
                        }	
                     }	
	
                     ++a;	
                  }	
	
                  if (!a) {	
                     var10001 = new Object[2];	
                     var10003 = true;	
                     var10001[0] = "save";	
                     var10001[1] = a;	
                     callback.execute(var10001);	
                  }	
               }	
            }	
	
         }	
      } catch (Exception var17) {	
         a = (new StringBuilder()).insert(0, "entity: ").append(entityClass.toString()).toString();	
         this.logger.error(a, var17);	
         throw new Exception(a, var17);	
      }	
   }	
	
   protected void clearTable(Class entity) {	
      String a = MapperHelper.getTableName(MapperHelper.getTable(entity));	
      this.logger.debug((new StringBuilder()).insert(0, "TRUNCATE TABLE ").append(a).toString());	
      this.jdbcTemplate.update((new StringBuilder()).insert(0, "TRUNCATE TABLE ").append(a).toString());	
   }	
}	
