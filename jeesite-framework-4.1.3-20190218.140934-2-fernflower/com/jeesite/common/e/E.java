package com.jeesite.common.e;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.StringReader;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import javax.annotation.PostConstruct;	
import javax.sql.DataSource;	
import org.apache.ibatis.jdbc.ScriptRunner;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public class E {	
   @Autowired	
   private DataSource D;	
   private static Logger c = LoggerFactory.getLogger(E.class);	
   @Autowired	
   private ModuleService ALLATORIxDEMO;	
	
   // $FF: synthetic method	
   private boolean ALLATORIxDEMO(Module module, String currentVersion) {	
      String a = "";	
      InputStream a = null;	
      Connection a = null;	
	
      boolean var10;	
      label230: {	
         boolean var7;	
         Throwable var10000;	
         label231: {	
            try {	
               try {	
                  String a = Global.getJdbcType();	
                  String a = IOUtils.toString(a = ResourceUtils.getResourceFileStream(a = (new StringBuilder()).insert(0, "/db/ugrade/").append(module.getModuleCode()).append("/").append(a).append("/").append(a).append("_").append(currentVersion).append(".sql").toString()), "UTF-8");	
                  c.info((new StringBuilder()).insert(0, "检测到").append(module.getModuleCode()).append("模块的数据库有更新，现在运行数据库升级脚本：{}\r\n").toString(), a);	
                  a = StringUtils.replace(a, "${_prefix}", Global.getTablePrefix());	
                  String a = "______sql_script_delimiter______";	
                  a = a.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", (new StringBuilder()).insert(0, a).append("\n").toString());	
                  a = this.D.getConnection();	
                  ScriptRunner a = new ScriptRunner(a);	
                  a.setAutoCommit(true);	
                  a.setDelimiter(a);	
                  a.setFullLineDelimiter(false);	
                  a.runScript(new StringReader(a));	
                  c.info((new StringBuilder()).insert(0, "恭喜您").append(module.getModuleCode()).append("模块的数据库已升级完成：{}").toString(), a);	
                  var10 = true;	
                  break label230;	
               } catch (FileNotFoundException var33) {	
                  var7 = true;	
                  break label231;	
               } catch (Exception var34) {	
                  c.warn((new StringBuilder()).insert(0, "很抱歉").append(module.getModuleCode()).append("模块的数据库升级脚本执行错误：{}").toString(), a, var34);	
               }	
            } catch (Throwable var35) {	
               label206: {	
                  if (a != null) {	
                     label204: {	
                        try {	
                           a.close();	
                        } catch (IOException var30) {	
                           break label204;	
                        }	
	
                        var10000 = a;	
                        break label206;	
                     }	
                  }	
	
                  var10000 = a;	
               }	
	
               if (var10000 != null) {	
                  label237: {	
                     try {	
                        a.close();	
                     } catch (SQLException var29) {	
                        break label237;	
                     }	
	
                     var10000 = var35;	
                     throw var10000;	
                  }	
               }	
	
               var10000 = var35;	
               throw var10000;	
            }	
	
            label180: {	
               if (a != null) {	
                  label178: {	
                     try {	
                        a.close();	
                     } catch (IOException var26) {	
                        break label178;	
                     }	
	
                     var10000 = a;	
                     break label180;	
                  }	
               }	
	
               var10000 = a;	
            }	
	
            if (var10000 != null) {	
               try {	
                  a.close();	
               } catch (SQLException var25) {	
               }	
            }	
	
            return false;	
         }	
	
         label192: {	
            if (a != null) {	
               label190: {	
                  try {	
                     a.close();	
                  } catch (IOException var28) {	
                     break label190;	
                  }	
	
                  var10000 = a;	
                  break label192;	
               }	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 != null) {	
            try {	
               a.close();	
               return var7;	
            } catch (SQLException var27) {	
            }	
         }	
	
         return var7;	
      }	
	
      Connection var37;	
      label218: {	
         if (a != null) {	
            label216: {	
               try {	
                  a.close();	
               } catch (IOException var32) {	
                  break label216;	
               }	
	
               var37 = a;	
               break label218;	
            }	
         }	
	
         var37 = a;	
      }	
	
      if (var37 != null) {	
         try {	
            a.close();	
            return var10;	
         } catch (SQLException var31) {	
         }	
      }	
	
      return var10;	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(Module module, String dbVersion) {	
      int a = -1;	
      int a = 0;	
      int var10000 = a;	
	
      while(var10000 < 0 && a < module.getVersions().length) {	
         if (module.getVersions()[a].equals(dbVersion)) {	
            var10000 = a = a;	
         } else {	
            ++a;	
            var10000 = a;	
         }	
      }	
	
      if (a < 0) {	
         c.warn((new StringBuilder()).insert(0, "自动更新").append(module.getModuleCode()).append("模块的数据库失败，未知的版本号“").append(dbVersion).append("”，可能是数据库版本比代码版本新。").toString());	
         return null;	
      } else {	
         if (a != module.getVersions().length - 1) {	
            String a = module.getVersions()[a + 1];	
            if (this.ALLATORIxDEMO(module, a)) {	
               return a;	
            }	
         }	
	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   @PostConstruct	
   private void ALLATORIxDEMO() {	
      if (!Global.isTestProfileActive()) {	
         String a;	
         if (StringUtils.isBlank(a = (String)CacheUtils.get("jeesiteVersion"))) {	
            CacheUtils.put("jeesiteVersion", ALLATORIxDEMO());	
         } else if (!StringUtils.equals(a, ALLATORIxDEMO())) {	
            CacheUtils.clearCache();	
         }	
	
         Iterator var3 = ModuleUtils.getModuleList().values().iterator();	
	
         while(true) {	
            Module a;	
            String a;	
            label60:	
            while(true) {	
               for(Iterator var10000 = var3; var10000.hasNext(); var10000 = var3) {	
                  if (!(a = (Module)var3.next()).getIsLoader() || a.getVersions().length != 0) {	
                     if (!StringUtils.isBlank(a = a.getCurrentVersion())) {	
                        break label60;	
                     }	
	
                     if (a.getVersions().length > 0) {	
                        a = a.getVersions()[0];	
                        break label60;	
                     }	
                     continue label60;	
                  }	
               }	
	
               return;	
            }	
	
            String a = a.getLastVersion();	
	
            int a;	
            for(int var10 = a = 0; var10 < a.getVersions().length; var10 = a) {	
               String a;	
               if (StringUtils.isBlank(a = a.getCurrentVersion())) {	
                  a = a;	
               }	
	
               String a;	
               if ((a = this.ALLATORIxDEMO(a, a)) == null) {	
                  break;	
               }	
	
               if (a != null && a != null && !a.equals(a)) {	
                  a.setCurrentVersion(a);	
                  a.setUpgradeInfo("ugrade " + DateUtils.getDateTime() + " (" + a + " -> " + a + ")");	
                  this.ALLATORIxDEMO.update(a);	
               }	
	
               if (StringUtils.equals(a, a)) {	
                  break;	
               }	
	
               ++a;	
            }	
         }	
      }	
   }	
	
   public static String ALLATORIxDEMO() {	
      return (new Module("core")).getLastVersion();	
   }	
}	
