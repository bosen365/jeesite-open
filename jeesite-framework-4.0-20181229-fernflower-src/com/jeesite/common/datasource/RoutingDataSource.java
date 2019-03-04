package com.jeesite.common.datasource;	
	
import ch.qos.logback.classic.LoggerContext;	
import ch.qos.logback.classic.selector.ContextSelector;	
import ch.qos.logback.classic.util.ContextInitializer;	
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.d.l;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.d.j;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.config.DataSourceConfig;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import java.io.File;	
import java.sql.SQLException;	
import java.util.Map;	
import java.util.regex.Pattern;	
import javax.sql.DataSource;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.DisposableBean;	
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;	
	
public class RoutingDataSource extends AbstractRoutingDataSource implements DisposableBean {	
   private Pattern p1 = Pattern.compile("[一-龥\s]");	
   private Object defaultTargetDataSource;	
   private Map targetDataSources;	
	
   // $FF: synthetic method	
   private void printWelcomeInfo() {	
      Logger a = LoggerFactory.getLogger(Global.class);	
      StringBuilder a = new StringBuilder();	
      a.append("logPath: " + System.getProperty("logPath"));	
      a.append("\r\n\r\n");	
      a.append("   _____        ___  _   _           ___\r\n");	
      a.append("  |_   _|      / __'(_)_| |_        /   |\r\n");	
      a.append("  _ | | __  __ \___ | |_   _| __   / /| |\r\n");	
      a.append(" ( .| |/__\/__\ ___)| | | |_ /__\ / /_| |_\r\n");	
      a.append((new StringBuilder()).insert(0, "  \___|\__.\__.|____|_| \___)\__.(____   _|  :: JeeSite V").append(l.ALLATORIxDEMO()).append(" :: \r\n").toString());	
      a.append("======================================|_|==========================\r\n");	
      a.append((new StringBuilder()).insert(0, "\r\n    欢迎使用 ").append(Global.getProperty("productName")).append(" ").append(Global.getProperty("productVersion")).toString());	
      a.append((new StringBuilder()).insert(0, "\r\n").append((String)j.ALLATORIxDEMO().get("loadMessage")).append("\r\n").toString());	
      a.append("===================================================================\r\n");	
      a.info(a.toString());	
      String a = FileUtils.getProjectPath();	
      if (this.isContainSpaceOrChinese(a)) {	
         (a = new StringBuilder()).append("\r\n===================================================================\r\n");	
         a.append("\r\n    严重警告：请将系统部署到不带有 “空格” 或 “中文” 的磁盘路径下！！！\r\n");	
         a.append((new StringBuilder()).insert(0, "\r\n    当前路径：").append(a).append("\r\n").toString());	
         a.append("\r\n===================================================================");	
         a.warn(a.toString());	
      }	
	
   }	
	
   public void setTargetDataSources(Map targetDataSources) {	
      super.setTargetDataSources(this.targetDataSources = targetDataSources);	
   }	
	
   // $FF: synthetic method	
   private boolean isContainSpaceOrChinese(String str) {	
      return this.p1.matcher(str).find();	
   }	
	
   public void afterPropertiesSet() {	
      this.initialize();	
      super.afterPropertiesSet();	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("jdbc.dataSourceNames"))) {	
         String[] var2;	
         int var3 = (var2 = StringUtils.split(a, ",")).length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
	
            try {	
               DataSource a;	
               DataSource var8 = a = DataSourceConfig.createDataSource(a);	
               this.logger.debug((new StringBuilder()).insert(0, "Init config data source name: ").append(a).toString());	
               if (var8 != null) {	
                  this.targetDataSources.put(a, a);	
               }	
            } catch (SQLException var7) {	
               this.logger.error((new StringBuilder()).insert(0, "Init config data source error: ").append(a).toString(), var7);	
            }	
	
            ++var4;	
         }	
	
         super.afterPropertiesSet();	
      }	
	
   }	
	
   public DataSource getTargetDataSource(String dataSourceName) {	
      if (!StringUtils.isBlank(dataSourceName) && !"default".equals(dataSourceName)) {	
         Object a;	
         return (a = this.targetDataSources.get(dataSourceName)) == null ? (DataSource)this.defaultTargetDataSource : (DataSource)a;	
      } else {	
         return (DataSource)this.defaultTargetDataSource;	
      }	
   }	
	
   // $FF: synthetic method	
   private void initialize() {	
      String a = null;	
	
      try {	
         a = ResourceUtils.getResource("/").getFile().getPath();	
      } catch (Exception var5) {	
         a = System.getProperty("user.dir");	
      }	
	
      String a = FileUtils.path((new StringBuilder()).insert(0, a).append("/WEB-INF/classes").toString());	
      if ((new File(a)).exists()) {	
         a = a;	
      }	
	
      System.setProperty("logPath", FileUtils.path(a));	
      LoggerContext a = (LoggerContext)LoggerFactory.getILoggerFactory();	
	
      RoutingDataSource var10000;	
      label24: {	
         try {	
            a.reset();	
            (new ContextInitializer(a)).autoConfig();	
         } catch (Exception var6) {	
            var10000 = this;	
            var6.printStackTrace();	
            break label24;	
         }	
	
         var10000 = this;	
      }	
	
      var10000.printWelcomeInfo();	
   }	
	
   public void addTargetDataSource(String dataSourceName, DataSource dataSource) {	
      this.logger.debug((new StringBuilder()).insert(0, "Add target data source name: ").append(dataSourceName).toString());	
      this.targetDataSources.put(dataSourceName, dataSource);	
      super.afterPropertiesSet();	
   }	
	
   public void destroy() throws Exception {	
      ContextSelector var10000 = ContextSelectorStaticBinder.getSingleton().getContextSelector();	
      var10000.detachLoggerContext(var10000.getLoggerContext().getName()).reset();	
   }	
	
   public void setDefaultTargetDataSource(Object defaultTargetDataSource) {	
      super.setDefaultTargetDataSource(this.defaultTargetDataSource = defaultTargetDataSource);	
   }	
	
   public void removeTargetDataSource(String dataSourceName) {	
      this.logger.debug((new StringBuilder()).insert(0, "Remove target data source name: ").append(dataSourceName).toString());	
      this.targetDataSources.remove(dataSourceName);	
      super.afterPropertiesSet();	
   }	
	
   protected Object determineCurrentLookupKey() {	
      String a;	
      if ((a = DataSourceHolder.getDataSourceName()) != null) {	
         this.logger.debug((new StringBuilder()).insert(0, "Current data source name: ").append(a).append(" =================").toString());	
      }	
	
      return a;	
   }	
}	
