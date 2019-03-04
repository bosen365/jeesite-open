package com.jeesite.common.d;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.l.j;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.FileNotFoundException;	
import java.io.InputStream;	
import java.io.StringReader;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import java.util.Map;	
import javax.annotation.PostConstruct;	
import org.apache.ibatis.jdbc.ScriptRunner;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public class l {	
   private static Logger d = LoggerFactory.getLogger(l.class);	
   @Autowired	
   private RoutingDataSource c;	
   @Autowired	
   private ModuleService ALLATORIxDEMO;	
	
   public static String ALLATORIxDEMO() {	
      return (new Module("core")).getLastVersion();	
   }	
	
   // $FF: synthetic method	
   private boolean ALLATORIxDEMO(Module module, String currentVersion) {	
      String a = "";	
      Connection a = null;	
      InputStream a = null;	
	
      boolean var9;	
      label129: {	
         boolean var7;	
         label130: {	
            try {	
               try {	
                  String a = Global.getJdbcType();	
                  String a = IOUtils.toString(a = ResourceUtils.getResourceFileStream(a = (new StringBuilder()).insert(0, "/db/upgrade/").append(module.getModuleCode()).append("/").append(a).append("/").append(a).append("_").append(currentVersion).append(".sql").toString()), "UTF-8");	
                  d.info((new StringBuilder()).insert(0, "\r\n@@检测到").append(module.getModuleCode()).append("模块的数据库有更新，现在运行数据库升级脚本：{}\r\n").toString(), a);	
                  a = StringUtils.replace(a, "${_prefix}", Global.getTablePrefix());	
                  a = this.c.getConnection();	
                  ScriptRunner a;	
                  (a = new ScriptRunner(a)).setAutoCommit(true);	
                  a.runScript(new StringReader(a));	
                  d.info((new StringBuilder()).insert(0, "\r\n@@恭喜您").append(module.getModuleCode()).append("模块的数据库已升级完成：{}\r\n").toString(), a);	
                  var9 = true;	
                  break label129;	
               } catch (FileNotFoundException var24) {	
                  var7 = true;	
                  break label130;	
               } catch (Exception var25) {	
                  d.warn((new StringBuilder()).insert(0, "\r\n@@很抱歉").append(module.getModuleCode()).append("模块的数据库升级脚本执行错误：{}\r\n").toString(), a, var25);	
               }	
            } catch (Throwable var26) {	
               IOUtils.closeQuietly(a);	
               Throwable var10000;	
               if (a != null) {	
                  label135: {	
                     try {	
                        a.close();	
                     } catch (SQLException var21) {	
                        break label135;	
                     }	
	
                     var10000 = var26;	
                     throw var10000;	
                  }	
               }	
	
               var10000 = var26;	
               throw var10000;	
            }	
	
            IOUtils.closeQuietly(a);	
            if (a != null) {	
               try {	
                  a.close();	
               } catch (SQLException var20) {	
               }	
            }	
	
            return false;	
         }	
	
         IOUtils.closeQuietly(a);	
         if (a != null) {	
            try {	
               a.close();	
               return var7;	
            } catch (SQLException var22) {	
            }	
         }	
	
         return var7;	
      }	
	
      IOUtils.closeQuietly(a);	
      if (a != null) {	
         try {	
            a.close();	
            return var9;	
         } catch (SQLException var23) {	
         }	
      }	
	
      return var9;	
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
         d.error((new StringBuilder()).insert(0, "\r\n@@自动更新").append(module.getModuleCode()).append("模块的数据库失败，未知的版本号“").append(dbVersion).append("”，可能是数据库版本比代码版本新。\r\n").toString());	
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
         Map a;	
         Iterator var2 = (a = ModuleUtils.getModuleList()).values().iterator();	
	
         label56:	
         while(true) {	
            for(Iterator var10000 = var2; var10000.hasNext(); var10000 = var2) {	
               Module a;	
               if ((a = (Module)var2.next()).getVersions().length != 0) {	
                  String a = a.getVersions()[0];	
                  String a = a.getCurrentVersion();	
                  String a = a.getCurrentVersion();	
                  int a;	
                  int var9 = a = 0;	
	
                  String var11;	
                  while(true) {	
                     if (var9 >= a.getVersions().length) {	
                        var11 = a;	
                        break;	
                     }	
	
                     l var10;	
                     label50: {	
                        if (StringUtils.isBlank(a = a.getCurrentVersion())) {	
                           if (a.getVersions().length > 0) {	
                              a = a.getVersions()[0];	
                              var10 = this;	
                              break label50;	
                           }	
	
                           a = ALLATORIxDEMO();	
                        }	
	
                        var10 = this;	
                     }	
	
                     String a;	
                     if ((a = var10.ALLATORIxDEMO(a, a)) == null) {	
                        var11 = a;	
                        break;	
                     }	
	
                     a = this.ALLATORIxDEMO.get(a);	
                     String var10001 = a.getModuleCode();	
                     ++a;	
                     a.put(var10001, a);	
                     a = a;	
                     var9 = a;	
                  }	
	
                  if (var11 != null && a != null && !a.equals(a)) {	
                     a.setCurrentVersion(a);	
                     a.setUpgradeInfo("upgrade " + DateUtils.getDateTime() + " (" + a + " -> " + a + ")");	
                     this.ALLATORIxDEMO.update(a);	
                  }	
                  continue label56;	
               }	
            }	
	
            return;	
         }	
      }	
   }	
}	
