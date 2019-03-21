/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.boot.autoconfigure.AutoConfigureBefore	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean	
 *  org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.datasource.RoutingDataSource;	
import java.sql.SQLException;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.springframework.boot.autoconfigure.AutoConfigureBefore;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@AutoConfigureBefore(value={org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})	
public class DataSourceAutoConfiguration {	
    @Bean	
    @ConditionalOnMissingBean(value={DataSource.class, XADataSource.class})	
    public DataSource dataSource() throws SQLException {	
        RoutingDataSource routingDataSource = new RoutingDataSource();	
        routingDataSource.initJeeSiteTargetDataSource();	
        return routingDataSource;	
    }	
}	
	
