package com.jeesite.common.mybatis.e;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.session.TransactionIsolationLevel;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
	
public class m extends SpringManagedTransactionFactory {	
   public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {	
      return (Transaction)(StringUtils.isNotBlank(Global.getProperty("jdbc.daaSourceNames")) && dataSource instanceof RoutingDataSource ? new H((RoutingDataSource)dataSource) : new SpringManagedTransaction(dataSource));	
   }	
}	
