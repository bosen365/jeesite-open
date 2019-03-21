/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.alibaba.druid.pool.DruidDataSource	
 *  com.alibaba.druid.pool.xa.DruidXADataSource	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.boot.jta.atomikos.AtomikosDataSourceBean	
 *  org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource	
 */	
package com.jeesite.common.datasource;	
	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.pool.xa.DruidXADataSource;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.d.i;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.hyperic.sigar.ProcCred;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;	
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;	
	
public class RoutingDataSource	
extends AbstractRoutingDataSource {	
    private Object defaultTargetDataSource;	
    private Map<Object, Object> targetDataSources;	
    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);	
	
    public static DataSource createDataSource(String dataSourceName, boolean isJta) throws SQLException {	
        DruidXADataSource druidXADataSource;	
        String a;	
        if (StringUtils.isBlank((CharSequence)dataSourceName)) {	
            dataSourceName = "default";	
        }	
        String a2 = "jdbc";	
        if (!"default".equals(dataSourceName)) {	
            a2 = new StringBuilder().insert(0, a2).append(".").append(dataSourceName).toString();	
        }	
        if (StringUtils.isBlank((CharSequence)(a = Global.getProperty(new StringBuilder().insert(0, a2).append(".tpe").toString()))) && !"jdbc".equals(a2)) {	
            a2 = "jdbc";	
            a = Global.getProperty(new StringBuilder().insert(0, a2).append(".type").toString());	
        }	
        if (StringUtils.isBlank((CharSequence)a)) {	
            return null;	
        }	
        DruidXADataSource a3 = null;	
        (isJta ? (a3 = new DruidXADataSource()) : (a3 = new DruidDataSource())).setName(dataSourceName);	
        DruidXADataSource druidXADataSource2 = a3;	
        druidXADataSource2.setDriverClassName(Global.getProperty(a2 + ".driver"));	
        a3.setUrl(Global.getProperty(new StringBuilder().insert(0, a2).append(".url").toString()));	
        druidXADataSource2.setUsername(Global.getProperty(new StringBuilder().insert(0, a2).append(".username").toString()));	
        druidXADataSource2.setPassword(Global.getProperty(new StringBuilder().insert(0, a2).append(".password").toString()));	
        if (StringUtils.isAnyBlank((CharSequence[])new CharSequence[]{a3.getDriverClassName(), a3.getUrl(), a3.getUsername()})) {	
            a3.close();	
            return null;	
        }	
        String a4 = Global.getProperty(new StringBuilder().insert(0, a2).append(".testSql").toString());	
        DruidXADataSource druidXADataSource3 = a3;	
        if (StringUtils.isNotBlank((CharSequence)a4)) {	
            druidXADataSource3.setTestWhileIdle(true);	
            DruidXADataSource druidXADataSource4 = a3;	
            druidXADataSource = druidXADataSource4;	
            druidXADataSource4.setValidationQuery(a4);	
        } else {	
            druidXADataSource3.setTestWhileIdle(false);	
            logger.warn(new StringBuilder().insert(0, "当前 ").append(a2).append(".testSql 未设置，系统将不会进行空闲连接检查。").toString());	
            druidXADataSource = a3;	
        }	
        druidXADataSource.setInitialSize(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.init").toString(), "1").intValue());	
        DruidXADataSource druidXADataSource5 = a3;	
        druidXADataSource5.setMinIdle(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.minIdle").toString(), "3").intValue());	
        druidXADataSource5.setMaxActive(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxActive").toString(), "20").intValue());	
        druidXADataSource5.setMaxWait((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxWait").toString(), "60000").intValue());	
        druidXADataSource5.setTestOnBorrow(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.testOnBorrow").toString(), "false").booleanValue());	
        druidXADataSource5.setTestOnReturn(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.testOnReturn").toString(), "false").booleanValue());	
        druidXADataSource5.setTimeBetweenEvictionRunsMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.timeBetweenEvictionRunsMillis").toString(), "60000").intValue());	
        druidXADataSource5.setMinEvictableIdleTimeMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.minEvictableIdleTimeMillis").toString(), "1200000").intValue());	
        druidXADataSource5.setMaxEvictableIdleTimeMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxEvictableIdleTimeMillis").toString(), "1800000").intValue());	
        druidXADataSource5.setRemoveAbandoned(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.removeAbandoned").toString(), "true").booleanValue());	
        druidXADataSource5.setRemoveAbandonedTimeout(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.removeAbandonedTimeout").toString(), "2100").intValue());	
        if ("oracle".equals(a)) {	
            DruidXADataSource druidXADataSource6 = a3;	
            druidXADataSource6.setPoolPreparedStatements(true);	
            druidXADataSource6.setMaxPoolPreparedStatementPerConnectionSize(druidXADataSource6.getMaxActive());	
        }	
        DruidXADataSource druidXADataSource7 = a3;	
        druidXADataSource7.setFilters("stat");	
        a3.init();	
        druidXADataSource7.getProxyFilters().add(new i(a2));	
        if (druidXADataSource7 instanceof XADataSource) {	
            AtomikosDataSourceBean a5 = new AtomikosDataSourceBean();	
            a5.setXaDataSource((XADataSource)a3);	
            AtomikosDataSourceBean atomikosDataSourceBean = a5;	
            a5.setUniqueResourceName(a3.getName());	
            atomikosDataSourceBean.setMinPoolSize(0);	
            atomikosDataSourceBean.setMaxPoolSize(a3.getMaxActive());	
            return atomikosDataSourceBean;	
        }	
        return a3;	
    }	
	
    public void initJeeSiteTargetDataSource() {	
        Object a;	
        try {	
            a = RoutingDataSource.createDataSource("default");	
            this.setDefaultTargetDataSource(a);	
        }	
        catch (SQLException a2) {	
            logger.error("Init target data source error: default", (Throwable)a2);	
        }	
        a = Global.getProperty("jdbc.dataSourceNames");	
        if (StringUtils.isNotBlank((CharSequence)a)) {	
            int n;	
            String[] arrstring = StringUtils.split((String)a, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                try {	
                    a3 = StringUtils.trim((String)a3);	
                    DataSource a4 = RoutingDataSource.createDataSource(a3);	
                    this.addTargetDataSource(a3, a4);	
                }	
                catch (SQLException a5) {	
                    logger.error(new StringBuilder().insert(0, "Init target data source error: ").append(a3).toString(), (Throwable)a5);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
	
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {	
        if (defaultTargetDataSource == null) {	
            logger.warn("Set default target data source is null.");	
            return;	
        }	
        this.defaultTargetDataSource = defaultTargetDataSource;	
        super.setDefaultTargetDataSource(this.defaultTargetDataSource);	
        logger.debug("Set default target data source success.");	
    }	
	
    public static DataSource createDataSource(String dataSourceName) throws SQLException {	
        boolean a = Global.getPropertyToBoolean("jdbc.jta.enabled", "false");	
        return RoutingDataSource.createDataSource(dataSourceName, a);	
    }	
	
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {	
        this.targetDataSources = targetDataSources;	
        super.setTargetDataSources(this.targetDataSources);	
    }	
	
    public void addTargetDataSource(String dataSourceName, DataSource dataSource) {	
        if (dataSource == null) {	
            logger.warn(new StringBuilder().insert(0, "Add target data source is null: ").append(dataSourceName).toString());	
            return;	
        }	
        logger.debug(new StringBuilder().insert(0, "Add target data source success: ").append(dataSourceName).toString());	
        this.targetDataSources.put(dataSourceName, dataSource);	
    }	
	
    protected Object determineCurrentLookupKey() {	
        return DataSourceHolder.getDataSourceName();	
    }	
	
    public RoutingDataSource() throws SQLException {	
        RoutingDataSource routingDataSource = this;	
        routingDataSource.setTargetDataSources(MapUtils.newConcurrentMap());	
    }	
	
    public DataSource getTargetDataSource(String dataSourceName) {	
        if (StringUtils.isBlank((CharSequence)dataSourceName) || "default".equals(dataSourceName)) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        Object a = this.targetDataSources.get(dataSourceName);	
        if (a == null) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        return (DataSource)a;	
    }	
	
    public void removeTargetDataSource(String dataSourceName) {	
        logger.debug(new StringBuilder().insert(0, "Remove target data source: ").append(dataSourceName).toString());	
        this.targetDataSources.remove(dataSourceName);	
    }	
}	
	
