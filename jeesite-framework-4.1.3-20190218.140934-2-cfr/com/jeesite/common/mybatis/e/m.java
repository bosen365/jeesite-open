/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 *  org.apache.ibatis.session.TransactionIsolationLevel	
 *  org.apache.ibatis.transaction.Transaction	
 *  org.mybatis.spring.transaction.SpringManagedTransaction	
 *  org.mybatis.spring.transaction.SpringManagedTransactionFactory	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.mybatis.e.H;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.session.TransactionIsolationLevel;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
	
public class m	
extends SpringManagedTransactionFactory {	
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {	
        if (StringUtils.isNotBlank((CharSequence)Global.getProperty("jdbc.daaSourceNames")) && dataSource instanceof RoutingDataSource) {	
            return new H((RoutingDataSource)((Object)dataSource));	
        }	
        return new SpringManagedTransaction(dataSource);	
    }	
}	
	
