package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.RoutingDataSource;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Map.Entry;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.transaction.Transaction;	
import org.hyperic.sigar.ProcFd;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class H implements Transaction {	
   private final Map D = MapUtils.newConcurrentMap();	
   protected Logger c = LoggerFactory.getLogger(RoutingDataSource.class);	
   private final RoutingDataSource ALLATORIxDEMO;	
	
   public H(RoutingDataSource var1) {	
      Assert.notNull(var1, "No DataSource specified");	
      this.ALLATORIxDEMO = var1;	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO() {	
      String a;	
      if (StringUtils.isBlank(a = DataSourceHolder.getDataSourceName())) {	
         a = "default";	
      }	
	
      return a;	
   }	
	
   public void close() throws SQLException {	
      Iterator var1;	
      for(Iterator var10000 = var1 = this.D.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).close();	
      }	
	
   }	
	
   public Integer getTimeout() throws SQLException {	
      String a = this.ALLATORIxDEMO();	
      SpringManagedTransaction a;	
      return (a = (SpringManagedTransaction)this.D.get(a)) != null ? a.getTimeout() : null;	
   }	
	
   public void rollback() throws SQLException {	
      Iterator var1;	
      for(Iterator var10000 = var1 = this.D.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).rollback();	
      }	
	
   }	
	
   public void commit() throws SQLException {	
      Iterator var1;	
      for(Iterator var10000 = var1 = this.D.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).commit();	
      }	
	
   }	
	
   public Connection getConnection() throws SQLException {	
      String a = this.ALLATORIxDEMO();	
      SpringManagedTransaction a;	
      if ((a = (SpringManagedTransaction)this.D.get(a)) == null) {	
         DataSource a = this.ALLATORIxDEMO;	
         if (!"default".equals(a)) {	
            a = this.ALLATORIxDEMO.getTargetDataSource(a);	
         }	
	
         a = new SpringManagedTransaction((DataSource)a);	
         this.D.put(a, a);	
      }	
	
      this.c.debug((new StringBuilder()).insert(0, com.jeesite.common.j.e.ALLATORIxDEMO ("_\u001dn\u001ay\u0006hHx\th\t<\u001bs\u001dn\u000byHr\tq\r&H")).append(a).toString());	
      return a.getConnection();	
   }	
}	
