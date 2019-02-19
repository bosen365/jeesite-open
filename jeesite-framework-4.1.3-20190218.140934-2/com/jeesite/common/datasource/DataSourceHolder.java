package com.jeesite.common.datasource;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.sys.web.AdviceController;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.DirStat;	
import org.springframework.core.NamedThreadLocal;	
	
public class DataSourceHolder {	
   private static final ThreadLocal dataSourceName = new NamedThreadLocal("DataSourceHolder");	
   public static final String DEFAULT = "default";	
	
   public static String getDataSourceName() {	
      return (String)dataSourceName.get();	
   }	
	
   public static void setDataSourceName(String dataSourceName) {	
      if (!StringUtils.isBlank(dataSourceName) && !"default".equals(dataSourceName)) {	
         if (StringUtils.isBlank(Global.getProperty((new StringBuilder()).insert(0, "jdbc.").append(dataSourceName).append(".type").toString()))) {	
            throw new ServiceException((new StringBuilder()).insert(0, "没有找到 ").append(dataSourceName).append(" 数据源").toString());	
         } else {	
            DataSourceHolder.dataSourceName.set(dataSourceName);	
         }	
      } else {	
         clearDataSourceName();	
      }	
   }	
	
   public static void clearDataSourceName() {	
      dataSourceName.remove();	
   }	
}	
