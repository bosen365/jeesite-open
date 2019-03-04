package com.jeesite.modules.config;	
	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.pool.xa.DruidXADataSource;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.sql.SQLException;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.Primary;	
	
@Configuration	
public class DataSourceConfig {	
   @Bean	
   @Primary	
   public RoutingDataSource dataSource() throws SQLException {	
      RoutingDataSource a = new RoutingDataSource();	
      a.setTargetDataSources(MapUtils.newConcurrentMap());	
      a.setDefaultTargetDataSource(createDataSource((String)null));	
      return a;	
   }	
	
   public static DataSource createDataSource(String dataSourceName) throws SQLException {	
      DruidDataSource a = null;	
      if (StringUtils.isNotBlank(Global.getProperty(TransactionConfig.ALLATORIxDEMO ("6_>Xr_=O=h3N.X9u=V9H")))) {	
         a = new DruidXADataSource();	
      } else {	
         a = new DruidDataSource();	
      }	
	
      String a = "jdbc";	
      if (StringUtils.isNotBlank(dataSourceName)) {	
         a = (new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("\u0015")).append(dataSourceName).toString();	
      }	
	
      String a = Global.getProperty((new StringBuilder()).insert(0, a).append(".type").toString());	
      ((DruidDataSource)a).setDriverClassName(Global.getProperty(a + TransactionConfig.ALLATORIxDEMO ("\u00158I5M9I")));	
      ((DruidDataSource)a).setUrl(Global.getProperty((new StringBuilder()).insert(0, a).append(".url").toString()));	
      ((DruidDataSource)a).setUsername(Global.getProperty((new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("\u0015)H9I2Z1^")).toString()));	
      ((DruidDataSource)a).setPassword(Global.getProperty((new StringBuilder()).insert(0, a).append(".password").toString()));	
      CharSequence[] var10000 = new CharSequence[4];	
      boolean var10002 = true;	
      var10000[0] = a;	
      var10000[1] = ((DruidDataSource)a).getDriverClassName();	
      var10000[2] = ((DruidDataSource)a).getUrl();	
      var10000[3] = ((DruidDataSource)a).getUsername();	
      if (StringUtils.isAnyBlank(var10000)) {	
         ((DruidDataSource)a).close();	
         return null;	
      } else {	
         String a;	
         Object var7;	
         if (StringUtils.isNotBlank(a = Global.getProperty((new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("rO9H(h-W")).toString()))) {	
            ((DruidDataSource)a).setTestWhileIdle(true);	
            var7 = a;	
            ((DruidDataSource)a).setValidationQuery(a);	
         } else {	
            ((DruidDataSource)a).setTestWhileIdle(false);	
            var7 = a;	
         }	
	
         ((DruidDataSource)var7).setInitialSize(ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.init").toString(), "1")));	
         ((DruidDataSource)a).setMinIdle(ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("\u0015,T3WrV5U\u0015_0^")).toString(), "3")));	
         ((DruidDataSource)a).setMaxActive(ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.maxActive").toString(), TransactionConfig.ALLATORIxDEMO ("n\u000b"))));	
         ((DruidDataSource)a).setMaxWait((long)ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.maxWait").toString(), TransactionConfig.ALLATORIxDEMO ("\rl\u000bl\u000b"))));	
         ((DruidDataSource)a).setTestOnBorrow(ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.testOnBorrow").toString(), "false")));	
         ((DruidDataSource)a).setTestOnReturn(ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("rK3T0\u0015(^/O\u0013U\u000e^(N.U")).toString(), "false")));	
         ((DruidDataSource)a).setTimeBetweenEvictionRunsMillis((long)ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.timeBetweenEvictionRunsMillis").toString(), TransactionConfig.ALLATORIxDEMO ("\rl\u000bl\u000b"))));	
         ((DruidDataSource)a).setMinEvictableIdleTimeMillis((long)ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.minEvictableIdleTimeMillis").toString(), TransactionConfig.ALLATORIxDEMO ("o\u000bl\u000bl\u000b"))));	
         ((DruidDataSource)a).setRemoveAbandoned(ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, a).append(".pool.removeAbandoned").toString(), "true")));	
         ((DruidDataSource)a).setRemoveAbandonedTimeout(ObjectUtils.toInteger(Global.getProperty((new StringBuilder()).insert(0, a).append(TransactionConfig.ALLATORIxDEMO ("rK3T0\u0015.^1T*^\u001dY=U8T2^8o5V9T)O")).toString(), "1800")));	
         if (TransactionConfig.ALLATORIxDEMO ("3I=X0^").equals(a)) {	
            ((DruidDataSource)a).setPoolPreparedStatements(true);	
            ((DruidDataSource)a).setMaxPoolPreparedStatementPerConnectionSize(((DruidDataSource)a).getMaxActive());	
         }	
	
         ((DruidDataSource)a).setFilters("stat");	
         ((DruidDataSource)a).getProxyFilters().add(new com.jeesite.common.datasource.l.l(dataSourceName));	
         ((DruidDataSource)a).init();	
         if (a instanceof DruidXADataSource) {	
            AtomikosDataSourceBean a;	
            (a = new AtomikosDataSourceBean()).setXaDataSource((DruidXADataSource)a);	
            a.setUniqueResourceName(StringUtils.defaultString(dataSourceName, "default"));	
            a.setPoolSize(((DruidDataSource)a).getMinIdle());	
            a.setMaxPoolSize(((DruidDataSource)a).getMaxActive());	
            return a;	
         } else {	
            return (DataSource)a;	
         }	
      }	
   }	
}	
