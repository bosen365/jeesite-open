/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.datasource;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.NetRoute;	
import org.springframework.core.NamedThreadLocal;	
	
public class DataSourceHolder {	
    private static final ThreadLocal<String> dataSourceName = new NamedThreadLocal<String>("DataSourceHolder");	
    public static final String DEFAULT = "default";	
	
    public static String getDataSourceName() {	
        return dataSourceName.get();	
    }	
	
    public static void clearDataSourceName() {	
        dataSourceName.remove();	
    }	
	
    public static void setDataSourceName(String dataSourceName) {	
        if (StringUtils.isBlank(dataSourceName) || DEFAULT.equals(dataSourceName)) {	
            DataSourceHolder.clearDataSourceName();	
            return;	
        }	
        if (StringUtils.isBlank(Global.getProperty(new StringBuilder().insert(0, "jdbc.").append(dataSourceName).append(".type").toString()))) {	
            throw new ServiceException(new StringBuilder().insert(0, "没有找到 ").append(dataSourceName).append(" 数据源").toString());	
        }	
        DataSourceHolder.dataSourceName.set(dataSourceName);	
    }	
}	
	
