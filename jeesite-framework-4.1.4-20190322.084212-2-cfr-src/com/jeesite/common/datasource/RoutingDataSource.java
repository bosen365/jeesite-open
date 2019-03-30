/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.datasource;	
	
import com.alibaba.druid.filter.Filter;	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.pool.xa.DruidXADataSource;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.l.m;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.shiro.l.k;	
import java.sql.SQLException;	
import java.util.List;	
import java.util.Map;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;	
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;	
	
public class RoutingDataSource	
extends AbstractRoutingDataSource {	
    private Map<Object, Object> targetDataSources;	
    private Object defaultTargetDataSource;	
    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);	
	
    @Override	
    protected Object determineCurrentLookupKey() {	
        return DataSourceHolder.getDataSourceName();	
    }	
	
    @Override	
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {	
        this.targetDataSources = targetDataSources;	
        super.setTargetDataSources(this.targetDataSources);	
    }	
	
    public void removeTargetDataSource(String dataSourceName) {	
        this.targetDataSources.remove(dataSourceName);	
        logger.debug(new StringBuilder().insert(0, "Remove target data source: ").append(dataSourceName).toString());	
    }	
	
    public void addTargetDataSource(String dataSourceName, DataSource dataSource) {	
        if (dataSource == null) {	
            logger.warn(new StringBuilder().insert(0, "Add target data source is null: ").append(dataSourceName).toString());	
            return;	
        }	
        this.targetDataSources.put(dataSourceName, dataSource);	
        logger.debug(new StringBuilder().insert(0, "Add target data source success: ").append(dataSourceName).toString());	
    }	
	
    public static DataSource createDataSource(String dataSourceName, boolean isJta) throws SQLException {	
        DruidDataSource druidDataSource;	
        String a;	
        if (StringUtils.isBlank(dataSourceName)) {	
            dataSourceName = "default";	
        }	
        String a2 = "jdbc";	
        if (!"default".equals(dataSourceName)) {	
            a2 = new StringBuilder().insert(0, a2).append(".").append(dataSourceName).toString();	
        }	
        if (StringUtils.isBlank(a = Global.getProperty(new StringBuilder().insert(0, a2).append(".type").toString())) && !"jdbc".equals(a2)) {	
            a2 = "jdbc";	
            a = Global.getProperty(new StringBuilder().insert(0, a2).append(".type").toString());	
        }	
        if (StringUtils.isBlank(a)) {	
            return null;	
        }	
        DruidDataSource a3 = null;	
        (isJta ? (a3 = new DruidXADataSource()) : (a3 = new DruidDataSource())).setName(dataSourceName);	
        DruidDataSource druidDataSource2 = a3;	
        druidDataSource2.setDriverClassName(Global.getProperty(a2 + ".driver"));	
        a3.setUrl(Global.getProperty(new StringBuilder().insert(0, a2).append(".url").toString()));	
        druidDataSource2.setUsername(Global.getProperty(new StringBuilder().insert(0, a2).append(".username").toString()));	
        druidDataSource2.setPassword(Global.getProperty(new StringBuilder().insert(0, a2).append(".password").toString()));	
        CharSequence[] arrcharSequence = new CharSequence[3];	
        arrcharSequence[0] = a3.getDriverClassName();	
        arrcharSequence[1] = a3.getUrl();	
        arrcharSequence[2] = a3.getUsername();	
        if (StringUtils.isAnyBlank(arrcharSequence)) {	
            a3.close();	
            return null;	
        }	
        String a4 = Global.getProperty(new StringBuilder().insert(0, a2).append(".testSql").toString());	
        DruidDataSource druidDataSource3 = a3;	
        if (StringUtils.isNotBlank(a4)) {	
            druidDataSource3.setTestWhileIdle(true);	
            DruidDataSource druidDataSource4 = a3;	
            druidDataSource = druidDataSource4;	
            druidDataSource4.setValidationQuery(a4);	
        } else {	
            druidDataSource3.setTestWhileIdle(false);	
            logger.warn(new StringBuilder().insert(0, "当前 ").append(a2).append(".testSql 未设置，系统将不会进行空闲连接检查。").toString());	
            druidDataSource = a3;	
        }	
        druidDataSource.setInitialSize(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.init").toString(), "1"));	
        DruidDataSource druidDataSource5 = a3;	
        druidDataSource5.setMinIdle(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.minIdle").toString(), "3"));	
        druidDataSource5.setMaxActive(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxActive").toString(), "20"));	
        druidDataSource5.setMaxWait(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxWait").toString(), "60000").intValue());	
        druidDataSource5.setTestOnBorrow(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.testOnBorrow").toString(), "false"));	
        druidDataSource5.setTestOnReturn(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.testOnReturn").toString(), "false"));	
        druidDataSource5.setTimeBetweenEvictionRunsMillis(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.timeBetweenEvictionRunsMillis").toString(), "60000").intValue());	
        druidDataSource5.setMinEvictableIdleTimeMillis(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.minEvictableIdleTimeMillis").toString(), "1200000").intValue());	
        druidDataSource5.setMaxEvictableIdleTimeMillis(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.maxEvictableIdleTimeMillis").toString(), "1800000").intValue());	
        druidDataSource5.setRemoveAbandoned(Global.getPropertyToBoolean(new StringBuilder().insert(0, a2).append(".pool.removeAbandoned").toString(), "true"));	
        druidDataSource5.setRemoveAbandonedTimeout(Global.getPropertyToInteger(new StringBuilder().insert(0, a2).append(".pool.removeAbandonedTimeout").toString(), "2100"));	
        if ("oracle".equals(a)) {	
            DruidDataSource druidDataSource6 = a3;	
            druidDataSource6.setPoolPreparedStatements(true);	
            druidDataSource6.setMaxPoolPreparedStatementPerConnectionSize(druidDataSource6.getMaxActive());	
        }	
        DruidDataSource druidDataSource7 = a3;	
        druidDataSource7.setFilters("stat");	
        druidDataSource7.getProxyFilters().add(new m(a2));	
        a3.init();	
        if (druidDataSource7 instanceof XADataSource) {	
            AtomikosDataSourceBean a5 = new AtomikosDataSourceBean();	
            a5.setXaDataSource((XADataSource)((Object)a3));	
            AtomikosDataSourceBean atomikosDataSourceBean = a5;	
            a5.setUniqueResourceName(a3.getName());	
            atomikosDataSourceBean.setMinPoolSize(0);	
            atomikosDataSourceBean.setMaxPoolSize(a3.getMaxActive());	
            return atomikosDataSourceBean;	
        }	
        return a3;	
    }	
	
    public RoutingDataSource() throws SQLException {	
        RoutingDataSource routingDataSource = this;	
        routingDataSource.setTargetDataSources(MapUtils.newConcurrentMap());	
    }	
	
    @Override	
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
        if (StringUtils.isBlank(dataSourceName) || "default".equals(dataSourceName)) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        Object a = this.targetDataSources.get(dataSourceName);	
        if (a == null) {	
            return (DataSource)this.defaultTargetDataSource;	
        }	
        return (DataSource)a;	
    }	
	
    public static DataSource createDataSource(String dataSourceName) throws SQLException {	
        boolean a = Global.getPropertyToBoolean("jdbc.jta.enabled", "false");	
        return RoutingDataSource.createDataSource(dataSourceName, a);	
    }	
	
    public void initJeeSiteTargetDataSource() {	
        Object a;	
        try {	
            a = RoutingDataSource.createDataSource("default");	
            this.setDefaultTargetDataSource(a);	
        }	
        catch (SQLException a2) {	
            logger.error("Init target data source error: default", a2);	
        }	
        a = Global.getProperty("jdbc.dataSourceNames");	
        if (StringUtils.isNotBlank((CharSequence)a)) {	
            int n;	
            String[] arrstring = StringUtils.split((String)a, ",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                try {	
                    a3 = StringUtils.trim(a3);	
                    DataSource a4 = RoutingDataSource.createDataSource(a3);	
                    this.addTargetDataSource(a3, a4);	
                }	
                catch (SQLException a5) {	
                    logger.error(new StringBuilder().insert(0, "Init target data source error: ").append(a3).toString(), a5);	
                }	
                n3 = ++n;	
            }	
        }	
    }	
}	
	
