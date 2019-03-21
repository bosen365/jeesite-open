/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 *  org.apache.ibatis.session.TransactionIsolationLevel	
 *  org.apache.ibatis.transaction.Transaction	
 *  org.mybatis.spring.transaction.SpringManagedTransaction	
 *  org.mybatis.spring.transaction.SpringManagedTransactionFactory	
 */	
package com.jeesite.common.mybatis.v;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.mybatis.v.l;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.session.TransactionIsolationLevel;	
import org.apache.ibatis.transaction.Transaction;	
import org.mybatis.spring.transaction.SpringManagedTransaction;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
	
public class L	
extends SpringManagedTransactionFactory {	
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {	
        if (StringUtils.isNotBlank((CharSequence)Global.getProperty("jdbc.dataSourceNames")) && dataSource instanceof RoutingDataSource) {	
            return new l((RoutingDataSource)((Object)dataSource));	
        }	
        return new SpringManagedTransaction(dataSource);	
    }	
}	
	
