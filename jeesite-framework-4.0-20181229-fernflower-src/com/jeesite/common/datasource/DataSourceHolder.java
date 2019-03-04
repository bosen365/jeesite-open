package com.jeesite.common.datasource;	
	
import org.apache.commons.lang3.StringUtils;	
	
public class DataSourceHolder {	
   private static final ThreadLocal dataSourceName = new ThreadLocal();	
   public static final String DEFAULT = "default";	
	
   public static String getDataSourceName() {	
      return (String)dataSourceName.get();	
   }	
	
   public static void clearDataSourceName() {	
      dataSourceName.remove();	
   }	
	
   public static void setDataSourceName(String dataSourceName) {	
      if (!StringUtils.isBlank(dataSourceName) && !"default".equals(dataSourceName)) {	
         DataSourceHolder.dataSourceName.set(dataSourceName);	
      } else {	
         clearDataSourceName();	
      }	
   }	
}	
