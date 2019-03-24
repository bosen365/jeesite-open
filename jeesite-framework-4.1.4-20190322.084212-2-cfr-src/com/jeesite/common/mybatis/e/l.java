/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.mybatis.e.e;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.session.TransactionIsolationLevel;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
	
public class l	
extends SpringManagedTransactionFactory {	
    @Override	
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {	
        if (StringUtils.isNotBlank(Global.getProperty("jdbc.dataSourceNames")) && dataSource instanceof RoutingDataSource) {	
            return new e((RoutingDataSource)dataSource);	
        }	
        return new SpringManagedTransaction(dataSource);	
    }	
}	
	
