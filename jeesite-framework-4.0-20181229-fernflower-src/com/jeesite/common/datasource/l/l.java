package com.jeesite.common.datasource.l;	
	
import com.alibaba.druid.filter.FilterAdapter;	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;	
import com.alibaba.druid.support.logging.Log;	
import com.alibaba.druid.support.logging.LogFactory;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.web.AdviceController;	
	
public class l extends FilterAdapter {	
   private String c;	
   private static Log ALLATORIxDEMO = LogFactory.getLog(l.class);	
	
   public void init(DataSourceProxy dataSourceProxy) {	
      if (!(dataSourceProxy instanceof DruidDataSource)) {	
         ALLATORIxDEMO.error("ConfigFilter only support Druid.ataSource");	
      }	
	
      DruidDataSource a = (DruidDataSource)dataSourceProxy;	
      String a = "jdbc";	
      if (StringUtils.isNotBlank(this.c)) {	
         a = (new StringBuilder()).insert(0, a).append(".").append(this.c).toString();	
      }	
	
      String a = "72d1af0aec0114d3300ddb40cc17e90b";	
      String a;	
      if (ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, a).append(".encrypt.username").toString()))) {	
         a = a.getUsername();	
         a.setUsername(Global.getPropertyDecodeAndEncode(a, a + ".username", a));	
      }	
	
      if (ObjectUtils.toBoolean(Global.getProperty((new StringBuilder()).insert(0, a).append(".encrypt.password").toString()))) {	
         a = a.getPassword();	
         a.setPassword(Global.getPropertyDecodeAndEncode(a, (new StringBuilder()).insert(0, a).append(".password").toString(), a));	
      }	
	
   }	
	
   public l(String var1) {	
      this.c = var1;	
   }	
}	
