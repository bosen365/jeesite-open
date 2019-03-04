package com.jeesite.common.mybatis.i;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.session.TransactionIsolationLevel;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
	
public class l extends SpringManagedTransactionFactory {	
   public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {	
      return (Transaction)(StringUtils.isNotBlank(Global.getProperty("jdb\t.dataSour\teNames")) ? new C(dataSource) : new SpringManagedTransaction(dataSource));	
   }	
}	
