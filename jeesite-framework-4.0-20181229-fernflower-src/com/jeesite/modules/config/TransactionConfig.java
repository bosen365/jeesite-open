package com.jeesite.modules.config;	
	
import com.atomikos.icatch.jta.UserTransactionImp;	
import com.atomikos.icatch.jta.UserTransactionManager;	
import javax.sql.DataSource;	
import javax.transaction.TransactionManager;	
import javax.transaction.UserTransaction;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.jdbc.datasource.DataSourceTransactionManager;	
import org.springframework.transaction.PlatformTransactionManager;	
import org.springframework.transaction.annotation.EnableTransactionManagement;	
import org.springframework.transaction.jta.JtaTransactionManager;	
	
@Configuration	
@EnableTransactionManagement	
public class TransactionConfig {	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 3 ^ 3;	
      int var10001 = 4 << 4 ^ (2 ^ 5) << 1;	
      int var10002 = 5 << 4 ^ (3 ^ 5) << 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   @Bean({"transactionManager"})	
   @DependsOn({"userTransaction", "atomikosTransactionManager"})	
   @ConditionalOnExpression("'${jdbc.dataSourceNames:false}' != 'false'")	
   public PlatformTransactionManager jtaTransactionManager(UserTransaction userTransaction, TransactionManager atomikosTransactionManager) throws Throwable {	
      return new JtaTransactionManager(userTransaction, atomikosTransactionManager);	
   }	
	
   @Bean(	
      initMethod = "init",	
      destroyMethod = "close"	
   )	
   @ConditionalOnExpression("'${jdbc.dataSourceNames:false}' != 'false'")	
   public TransactionManager atomikosTransactionManager() throws Throwable {	
      UserTransactionManager var10000 = new UserTransactionManager();	
      var10000.setForceShutdown(false);	
      return var10000;	
   }	
	
   @Bean({"transactionManager"})	
   @ConditionalOnExpression("'${jdbc.dataSourceNames:true}' == 'true'")	
   public DataSourceTransactionManager transactionManager(DataSource dataSource) {	
      DataSourceTransactionManager var10000 = new DataSourceTransactionManager();	
      var10000.setDataSource(dataSource);	
      return var10000;	
   }	
	
   @Bean	
   @ConditionalOnExpression("'${jdbc.dataSourceNames:false}' != 'false'")	
   public UserTransaction userTransaction() throws Throwable {	
      UserTransactionImp var10000 = new UserTransactionImp();	
      var10000.setTransactionTimeout(180);	
      return var10000;	
   }	
}	
