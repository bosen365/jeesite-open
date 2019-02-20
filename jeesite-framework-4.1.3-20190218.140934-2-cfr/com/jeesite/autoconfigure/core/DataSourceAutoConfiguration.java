/*	
 * Decompiled with CFR 0.139.	
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
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 4 ^ 3;	
        int n4 = n2;	
        5 << 4 ^ 4 << 1;	
        int n5 = (2 ^ 5) << 3 ^ 4;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
}	
	
