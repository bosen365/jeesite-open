/*	
 * Decompiled with CFR 0.139.	
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
import com.jeesite.common.datasource.j.E;	
import com.jeesite.common.lang.StringUtils;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.hyperic.sigar.cmd.Tail;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;	
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;	
	
public class RoutingDataSource	
extends AbstractRoutingDataSource {	
    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);	
    private Object defaultTargetDataSource;	
    private Map<Object, Object> targetDataSources;	
	
    public void initJeeSiteTargetDataSource() {	
        Object a2;	
        try {	
            a2 = RoutingDataSource.createDataSource("default");	
            this.setDefaultTargetDataSource(a2);	
        }	
        catch (SQLException a3) {	
            logger.error("Init target data source error: default", (Throwable)a3);	
        }	
        a2 = Global.getProperty("jdbc.dataSourceNames");	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            int n;	
            String[] arrstring = StringUtils.split((String)a2, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a4 = arrstring[n];	
                try {	
                    a4 = StringUtils.trim((String)a4);	
                    DataSource a5 = RoutingDataSource.createDataSource(a4);	
                    this.addTargetDataSource(a4, a5);	
                }	
                catch (SQLException a6) {	
                    logger.error(new StringBuilder().insert(0, "Init target data source error: ").append(a4).toString(), (Throwable)a6);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
	
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {	
        this.targetDataSources = targetDataSources;	
        super.setTargetDataSources(this.targetDataSources);	
    }	
	
    public static DataSource createDataSource(String dataSourceName, boolean isJta) throws SQLException {	
        DruidXADataSource druidXADataSource;	
        String a2;	
        if (StringUtils.isBlank((CharSequence)dataSourceName)) {	
            dataSourceName = "default";	
        }	
        String a3 = "jdbc";	
        if (!"default".equals(dataSourceName)) {	
            a3 = new StringBuilder().insert(0, a3).append(".").append(dataSourceName).toString();	
        }	
        if (StringUtils.isBlank((CharSequence)(a2 = Global.getProperty(new StringBuilder().insert(0, a3).append(".type").toString()))) && !"jdbc".equals(a3)) {	
            a3 = "jdbc";	
            a2 = Global.getProperty(new StringBuilder().insert(0, a3).append(".type").toString());	
        }	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            return null;	
        }	
        DruidXADataSource a4 = null;	
        (isJta ? (a4 = new DruidXADataSource()) : (a4 = new DruidDataSource())).setName(dataSourceName);	
        DruidXADataSource druidXADataSource2 = a4;	
        druidXADataSource2.setDriverClassName(Global.getProperty(a3 + ".driver"));	
        a4.setUrl(Global.getProperty(new StringBuilder().insert(0, a3).append(".url").toString()));	
        druidXADataSource2.setUsername(Global.getProperty(new StringBuilder().insert(0, a3).append(".username").toString()));	
        druidXADataSource2.setPassword(Global.getProperty(new StringBuilder().insert(0, a3).append(".password").toString()));	
        if (StringUtils.isAnyBlank((CharSequence[])new CharSequence[]{a4.getDriverClassName(), a4.getUrl(), a4.getUsername()})) {	
            a4.close();	
            return null;	
        }	
        String a5 = Global.getProperty(new StringBuilder().insert(0, a3).append(".testSql").toString());	
        DruidXADataSource druidXADataSource3 = a4;	
        if (StringUtils.isNotBlank((CharSequence)a5)) {	
            druidXADataSource3.setTestWhileIdle(true);	
            DruidXADataSource druidXADataSource4 = a4;	
            druidXADataSource = druidXADataSource4;	
            druidXADataSource4.setValidationQuery(a5);	
        } else {	
            druidXADataSource3.setTestWhileIdle(false);	
            logger.warn(new StringBuilder().insert(0, "当前 ").append(a3).append(".testSql 未设置，系统将不会进行空闲连接检查。").toString());	
            druidXADataSource = a4;	
        }	
        druidXADataSource.setInitialSize(Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.init").toString(), "1").intValue());	
        DruidXADataSource druidXADataSource5 = a4;	
        druidXADataSource5.setMinIdle(Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.minIdle").toString(), "3").intValue());	
        druidXADataSource5.setMaxActive(Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.maxActive").toString(), "20").intValue());	
        druidXADataSource5.setMaxWait((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.maxWait").toString(), "60000").intValue());	
        druidXADataSource5.setTestOnBorrow(Global.getPropertyToBoolean(new StringBuilder().insert(0, a3).append(".pool.testOnBorrow").toString(), "false").booleanValue());	
        druidXADataSource5.setTestOnReturn(Global.getPropertyToBoolean(new StringBuilder().insert(0, a3).append(".pool.testOnReturn").toString(), "false").booleanValue());	
        druidXADataSource5.setTimeBetweenEvictionRunsMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.timeBetweenEvictionRunsMillis").toString(), "60000").intValue());	
        druidXADataSource5.setMinEvictableIdleTimeMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.minEvictableIdleTimeMillis").toString(), "1200000").intValue());	
        druidXADataSource5.setMaxEvictableIdleTimeMillis((long)Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.maxEvictableIdleTimeMillis").toString(), "1800000").intValue());	
        druidXADataSource5.setRemoveAbandoned(Global.getPropertyToBoolean(new StringBuilder().insert(0, a3).append(".pool.removeAbandoned").toString(), "true").booleanValue());	
        druidXADataSource5.setRemoveAbandonedTimeout(Global.getPropertyToInteger(new StringBuilder().insert(0, a3).append(".pool.removeAbandonedTimeout").toString(), "2100").intValue());	
        if ("oracle".equals(a2)) {	
            DruidXADataSource druidXADataSource6 = a4;	
            druidXADataSource6.setPoolPreparedStatements(true);	
            druidXADataSource6.setMaxPoolPreparedStatementPerConnectionSize(druidXADataSource6.getMaxActive());	
        }	
        DruidXADataSource druidXADataSource7 = a4;	
        druidXADataSource7.setFilters("stat");	
        a4.init();	
        druidXADataSource7.getProxyFilters().add(new E(a3));	
        if (druidXADataSource7 instanceof XADataSource) {	
            AtomikosDataSourceBean a6 = new AtomikosDataSourceBean();	
            a6.setXaDataSource((XADataSource)a4);	
            AtomikosDataSourceBean atomikosDataSourceBean = a6;	
            a6.setUniqueResourceName(a4.getName());	
            atomikosDataSourceBean.setMinPoolSize(0);	
            atomikosDataSourceBean.setMaxPoolSize(a4.getMaxActive());	
            return atomikosDataSourceBean;	
        }	
        return a4;	
    }	
	
    public void removeTargetDataSource(String dataSourceName) {	
        logger.debug(new StringBuilder().insert(0, "Remove target data source: ").append(dataSourceName).toString());	
        this.targetDataSources.remove(dataSourceName);	
    }	
	
    public RoutingDataSource() throws SQLException {	
        RoutingDataSource routingDataSource = this;	
        routingDataSource.setTargetDataSources(MapUtils.newConcurrentMap());	
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
	
    public DataSource getTargetDataSource(String dataSourceName) {	
        if (StringUtils.isBlank((CharSequence)dataSourceName) || "default".equals(dataSourceName)) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        Object a2 = this.targetDataSources.get(dataSourceName);	
        if (a2 == null) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        return (DataSource)a2;	
    }	
	
    protected Object determineCurrentLookupKey() {	
        return DataSourceHolder.getDataSourceName();	
    }	
	
    public void addTargetDataSource(String dataSourceName, DataSource dataSource) {	
        if (dataSource == null) {	
            logger.warn(new StringBuilder().insert(0, "Add target data source is null: ").append(dataSourceName).toString());	
            return;	
        }	
        logger.debug(new StringBuilder().insert(0, "Add target data source success: ").append(dataSourceName).toString());	
        this.targetDataSources.put(dataSourceName, dataSource);	
    }	
	
    public static DataSource createDataSource(String dataSourceName) throws SQLException {	
        boolean a2 = Global.getPropertyToBoolean("jdbc.jta.enabled", "false");	
        return RoutingDataSource.createDataSource(dataSourceName, a2);	
    }	
}	
	
