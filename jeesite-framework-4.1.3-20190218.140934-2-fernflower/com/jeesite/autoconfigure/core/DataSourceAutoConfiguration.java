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
@AutoConfigureBefore({org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})	
public class DataSourceAutoConfiguration {	
   @Bean	
   @ConditionalOnMissingBean({DataSource.class, XADataSource.class})	
   public DataSource dataSource() throws SQLException {	
      RoutingDataSource var10000 = new RoutingDataSource();	
      var10000.initJeeSiteTargetDataSource();	
      return var10000;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 3 ^ 4;	
      int var10001 = 5 << 4 ^ 4 << 1;	
      int var10002 = (2 ^ 5) << 4 ^ 3;	
      int var10003 = (s = (String)s).length();	
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
}	
