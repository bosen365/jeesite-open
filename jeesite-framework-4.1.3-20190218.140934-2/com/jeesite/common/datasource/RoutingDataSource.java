package com.jeesite.common.datasource;	
	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.pool.xa.DruidXADataSource;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.j.E;	
import com.jeesite.common.lang.StringUtils;	
import java.sql.SQLException;	
import java.util.Map;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.hyperic.sigar.cmd.Tail;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;	
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;	
	
public class RoutingDataSource extends AbstractRoutingDataSource {	
   private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);	
   private Object defaultTargetDataSource;	
   private Map targetDataSources;	
	
   public void initJeeSiteTargetDataSource() {	
      try {	
         DataSource a = createDataSource("default");	
         this.setDefaultTargetDataSource(a);	
      } catch (SQLException var8) {	
         logger.error("Init target data source error: default", var8);	
      }	
	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("jdbc.dataSourceNames"))) {	
         String[] var2;	
         int var3 = (var2 = StringUtils.split(a, ",")).length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
	
            try {	
               DataSource a = createDataSource(a = StringUtils.trim(a));	
               this.addTargetDataSource(a, a);	
            } catch (SQLException var7) {	
               logger.error((new StringBuilder()).insert(0, "Init target data source error: ").append(a).toString(), var7);	
            }	
	
            ++var4;	
         }	
      }	
	
   }	
	
   public void setTargetDataSources(Map targetDataSources) {	
      super.setTargetDataSources(this.targetDataSources = targetDataSources);	
   }	
	
   public static DataSource createDataSource(String dataSourceName, boolean isJta) throws SQLException {	
      if (StringUtils.isBlank(dataSourceName)) {	
         dataSourceName = "default";	
      }	
	
      String a = "jdbc";	
      if (!"default".equals(dataSourceName)) {	
         a = (new StringBuilder()).insert(0, a).append(".").append(dataSourceName).toString();	
      }	
	
      String a;	
      if (StringUtils.isBlank(a = Global.getProperty((new StringBuilder()).insert(0, a).append(".type").toString())) && !"jdbc".equals(a)) {	
         a = "jdbc";	
         a = Global.getProperty((new StringBuilder()).insert(0, a).append(".type").toString());	
      }	
	
      if (StringUtils.isBlank(a)) {	
         return null;	
      } else {	
         DruidDataSource a = null;	
         ((DruidDataSource)(isJta ? (a = new DruidXADataSource()) : (a = new DruidDataSource()))).setName(dataSourceName);	
         ((DruidDataSource)a).setDriverClassName(Global.getProperty(a + ".driver"));	
         ((DruidDataSource)a).setUrl(Global.getProperty((new StringBuilder()).insert(0, a).append(".url").toString()));	
         ((DruidDataSource)a).setUsername(Global.getProperty((new StringBuilder()).insert(0, a).append(".username").toString()));	
         ((DruidDataSource)a).setPassword(Global.getProperty((new StringBuilder()).insert(0, a).append(".password").toString()));	
         CharSequence[] var10000 = new CharSequence[3];	
         boolean var10002 = true;	
         var10000[0] = ((DruidDataSource)a).getDriverClassName();	
         var10000[1] = ((DruidDataSource)a).getUrl();	
         var10000[2] = ((DruidDataSource)a).getUsername();	
         if (StringUtils.isAnyBlank(var10000)) {	
            ((DruidDataSource)a).close();	
            return null;	
         } else {	
            String a;	
            Object var8;	
            if (StringUtils.isNotBlank(a = Global.getProperty((new StringBuilder()).insert(0, a).append(".testSql").toString()))) {	
               ((DruidDataSource)a).setTestWhileIdle(true);	
               var8 = a;	
               ((DruidDataSource)a).setValidationQuery(a);	
            } else {	
               ((DruidDataSource)a).setTestWhileIdle(false);	
               logger.warn((new StringBuilder()).insert(0, "当前 ").append(a).append(".testSql 未设置，系统将不会进行空闲连接检查。").toString());	
               var8 = a;	
            }	
	
            ((DruidDataSource)var8).setInitialSize(Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.init").toString(), "1"));	
            ((DruidDataSource)a).setMinIdle(Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.minIdle").toString(), "3"));	
            ((DruidDataSource)a).setMaxActive(Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.maxActive").toString(), "20"));	
            ((DruidDataSource)a).setMaxWait((long)Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.maxWait").toString(), "60000"));	
            ((DruidDataSource)a).setTestOnBorrow(Global.getPropertyToBoolean((new StringBuilder()).insert(0, a).append(".pool.testOnBorrow").toString(), "false"));	
            ((DruidDataSource)a).setTestOnReturn(Global.getPropertyToBoolean((new StringBuilder()).insert(0, a).append(".pool.testOnReturn").toString(), "false"));	
            ((DruidDataSource)a).setTimeBetweenEvictionRunsMillis((long)Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.timeBetweenEvictionRunsMillis").toString(), "60000"));	
            ((DruidDataSource)a).setMinEvictableIdleTimeMillis((long)Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.minEvictableIdleTimeMillis").toString(), "1200000"));	
            ((DruidDataSource)a).setMaxEvictableIdleTimeMillis((long)Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.maxEvictableIdleTimeMillis").toString(), "1800000"));	
            ((DruidDataSource)a).setRemoveAbandoned(Global.getPropertyToBoolean((new StringBuilder()).insert(0, a).append(".pool.removeAbandoned").toString(), "true"));	
            ((DruidDataSource)a).setRemoveAbandonedTimeout(Global.getPropertyToInteger((new StringBuilder()).insert(0, a).append(".pool.removeAbandonedTimeout").toString(), "2100"));	
            if ("oracle".equals(a)) {	
               ((DruidDataSource)a).setPoolPreparedStatements(true);	
               ((DruidDataSource)a).setMaxPoolPreparedStatementPerConnectionSize(((DruidDataSource)a).getMaxActive());	
            }	
	
            ((DruidDataSource)a).setFilters("stat");	
            ((DruidDataSource)a).getProxyFilters().add(new E(a));	
            ((DruidDataSource)a).init();	
            if (a instanceof XADataSource) {	
               AtomikosDataSourceBean a;	
               (a = new AtomikosDataSourceBean()).setXaDataSource((XADataSource)a);	
               a.setUniqueResourceName(((DruidDataSource)a).getName());	
               a.setMinPoolSize(0);	
               a.setMaxPoolSize(((DruidDataSource)a).getMaxActive());	
               return a;	
            } else {	
               return (DataSource)a;	
            }	
         }	
      }	
   }	
	
   public void removeTargetDataSource(String dataSourceName) {	
      this.targetDataSources.remove(dataSourceName);	
      logger.debug((new StringBuilder()).insert(0, "Remove target data source: ").append(dataSourceName).toString());	
   }	
	
   public RoutingDataSource() throws SQLException {	
      this.setTargetDataSources(MapUtils.newConcurrentMap());	
   }	
	
   public void setDefaultTargetDataSource(Object defaultTargetDataSource) {	
      if (defaultTargetDataSource == null) {	
         logger.warn("Set default target data source is null.");	
      } else {	
         super.setDefaultTargetDataSource(this.defaultTargetDataSource = defaultTargetDataSource);	
         logger.debug("Set default target data source success.");	
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
	
   protected Object determineCurrentLookupKey() {	
      return DataSourceHolder.getDataSourceName();	
   }	
	
   public void addTargetDataSource(String dataSourceName, DataSource dataSource) {	
      if (dataSource == null) {	
         logger.warn((new StringBuilder()).insert(0, "Add target data source is null: ").append(dataSourceName).toString());	
      } else {	
         this.targetDataSources.put(dataSourceName, dataSource);	
         logger.debug((new StringBuilder()).insert(0, "Add target data source success: ").append(dataSourceName).toString());	
      }	
   }	
	
   public static DataSource createDataSource(String dataSourceName) throws SQLException {	
      int a = Global.getPropertyToBoolean("jdbc.jta.enabled", "false");	
      return createDataSource(dataSourceName, a);	
   }	
}	
