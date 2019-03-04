package com.jeesite.common.mybatis.i;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.service.BaseService;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Map.Entry;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.springframework.util.Assert;	
	
public class C implements Transaction {	
   private final Map c;	
   private final RoutingDataSource ALLATORIxDEMO;	
	
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
      for(Iterator var10000 = var1 = this.c.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).close();	
      }	
	
      this.c.clear();	
   }	
	
   public Connection getConnection() throws SQLException {	
      String a = this.ALLATORIxDEMO();	
      SpringManagedTransaction a;	
      if ((a = (SpringManagedTransaction)this.c.get(a)) == null) {	
         DataSource a = this.ALLATORIxDEMO.getTargetDataSource(a);	
         a = new SpringManagedTransaction(a);	
         this.c.put(a, a);	
      }	
	
      return a.getConnection();	
   }	
	
   public void rollback() throws SQLException {	
      Iterator var1;	
      for(Iterator var10000 = var1 = this.c.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).rollback();	
      }	
	
   }	
	
   public Integer getTimeout() throws SQLException {	
      String a = this.ALLATORIxDEMO();	
      SpringManagedTransaction a;	
      return (a = (SpringManagedTransaction)this.c.get(a)) != null ? a.getTimeout() : null;	
   }	
	
   public C(DataSource var1) {	
      Assert.notNull(var1, "No DataSource specified");	
      this.ALLATORIxDEMO = (RoutingDataSource)var1;	
      this.c = MapUtils.newConcurrentMap();	
   }	
	
   public void commit() throws SQLException {	
      Iterator var1;	
      for(Iterator var10000 = var1 = this.c.entrySet().iterator(); var10000.hasNext(); var10000 = var1) {	
         ((SpringManagedTransaction)((Entry)var1.next()).getValue()).commit();	
      }	
	
   }	
}	
