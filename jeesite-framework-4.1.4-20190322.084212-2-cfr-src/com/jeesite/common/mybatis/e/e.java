/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class e	
implements Transaction {	
    private final RoutingDataSource J;	
    private final Map<String, SpringManagedTransaction> c;	
    protected Logger ALLATORIxDEMO;	
	
    @Override	
    public Integer getTimeout() throws SQLException {	
        e e2 = this;	
        String a = e2.ALLATORIxDEMO();	
        SpringManagedTransaction a2 = e2.c.get(a);	
        if (a2 != null) {	
            return a2.getTimeout();	
        }	
        return null;	
    }	
	
    @Override	
    public void close() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.c.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().close();	
            iterator2 = iterator;	
        }	
    }	
	
    public e(RoutingDataSource dataSource) {	
        RoutingDataSource routingDataSource = dataSource;	
        e e2 = this;	
        this.ALLATORIxDEMO = LoggerFactory.getLogger(RoutingDataSource.class);	
        e2.c = MapUtils.newConcurrentMap();	
        Assert.notNull((Object)routingDataSource, "No DataSource specified");	
        e2.J = routingDataSource;	
    }	
	
    @Override	
    public void commit() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.c.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().commit();	
            iterator2 = iterator;	
        }	
    }	
	
    @Override	
    public Connection getConnection() throws SQLException {	
        e e2 = this;	
        String a = e2.ALLATORIxDEMO();	
        SpringManagedTransaction a2 = e2.c.get(a);	
        if (a2 == null) {	
            DataSource a3 = this.J;	
            if (!"default".equals(a)) {	
                a3 = this.J.getTargetDataSource(a);	
            }	
            a2 = new SpringManagedTransaction(a3);	
            this.c.put(a, a2);	
        }	
        this.ALLATORIxDEMO.debug(new StringBuilder().insert(0, "Current data source name: ").append(a).toString());	
        return a2.getConnection();	
    }	
	
    private /* synthetic */ String ALLATORIxDEMO() {	
        String a = DataSourceHolder.getDataSourceName();	
        if (StringUtils.isBlank(a)) {	
            a = "default";	
        }	
        return a;	
    }	
	
    @Override	
    public void rollback() throws SQLException {	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator;	
        Iterator<Map.Entry<String, SpringManagedTransaction>> iterator2 = iterator = this.c.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            iterator.next().getValue().rollback();	
            iterator2 = iterator;	
        }	
    }	
}	
	
