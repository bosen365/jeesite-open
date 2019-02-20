/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  org.apache.commons.lang3.StringUtils	
 *  org.apache.ibatis.transaction.Transaction	
 *  org.mybatis.spring.transaction.SpringManagedTransaction	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.util.Assert	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.j.e;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import java.util.concurrent.ConcurrentMap;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.transaction.Transaction;	
import org.hyperic.sigar.ProcFd;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class H	
implements Transaction {	
    private final Map<String, SpringManagedTransaction> D;	
    protected Logger c;	
    private final RoutingDataSource ALLATORIxDEMO;	
	
    public H(RoutingDataSource dataSource) {	
        RoutingDataSource routingDataSource = dataSource;	
        H h2 = this;	
        this.c = LoggerFactory.getLogger(RoutingDataSource.class);	
        h2.D = MapUtils.newConcurrentMap();	
        Assert.notNull((Object)((Object)routingDataSource), (String)"No DataSource specified");	
        h2.ALLATORIxDEMO = routingDataSource;	
    }	
	
    private /* synthetic */ String ALLATORIxDEMO() {	
        String a2 = DataSourceHolder.getDataSourceName();	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            a2 = "default";	
        }	
        return a2;	
    }	
	
    public void close() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.D.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().close();	
            iterator2 = iterator;	
        }	
    }	
	
    public Integer getTimeout() throws SQLException {	
        H h2 = this;	
        String a2 = h2.ALLATORIxDEMO();	
        SpringManagedTransaction a3 = h2.D.get(a2);	
        if (a3 != null) {	
            return a3.getTimeout();	
        }	
        return null;	
    }	
	
    public void rollback() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.D.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().rollback();	
            iterator2 = iterator;	
        }	
    }	
	
    public void commit() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.D.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().commit();	
            iterator2 = iterator;	
        }	
    }	
	
    public Connection getConnection() throws SQLException {	
        H h2 = this;	
        String a2 = h2.ALLATORIxDEMO();	
        SpringManagedTransaction a3 = h2.D.get(a2);	
        if (a3 == null) {	
            Object a4 = this.ALLATORIxDEMO;	
            if (!"default".equals(a2)) {	
                a4 = this.ALLATORIxDEMO.getTargetDataSource(a2);	
            }	
            a3 = new SpringManagedTransaction((DataSource)a4);	
            this.D.put(a2, a3);	
        }	
        this.c.debug(new StringBuilder().insert(0, "Current data source name: ").append(a2).toString());	
        return a3.getConnection();	
    }	
}	
	
