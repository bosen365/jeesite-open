package com.jeesite.common.datasource.j;	
	
import com.alibaba.druid.filter.FilterAdapter;	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;	
import com.alibaba.druid.support.logging.Log;	
import com.alibaba.druid.support.logging.LogFactory;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import org.hyperic.sigar.Who;	
	
public class E extends FilterAdapter {	
   private static Log c = LogFactory.getLog(E.class);	
   private String ALLATORIxDEMO;	
	
   public void init(DataSourceProxy dataSourceProxy) {	
      if (!(dataSourceProxy instanceof DruidDataSource)) {	
         c.error("ConfigFilter only support DruidDataSource");	
      }	
	
      DruidDataSource a = (DruidDataSource)dataSourceProxy;	
      String a = "72d1af0aec0114d3300ddb40cc17e90b";	
      String a;	
      if (ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, this.ALLATORIxDEMO).append(".encrypt.username").toString()))) {	
         a = a.getUsername();	
         a.setUsername(Global.getPropertyDecodeAndEncode(a, this.ALLATORIxDEMO + ".username", a));	
      }	
	
      if (ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, this.ALLATORIxDEMO).append(".encrypt.password").toString()))) {	
         a = a.getPassword();	
         a.setPassword(Global.getPropertyDecodeAndEncode(a, (new StringBuilder()).insert(0, this.ALLATORIxDEMO).append(".password").toString(), a));	
      }	
	
   }	
	
   public E(String var1) {	
      this.ALLATORIxDEMO = var1;	
   }	
}	
