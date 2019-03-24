/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.datasource.l;	
	
import com.alibaba.druid.filter.FilterAdapter;	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;	
import com.alibaba.druid.support.logging.Log;	
import com.alibaba.druid.support.logging.LogFactory;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.service.ServiceException;	
import org.hyperic.sigar.Mem;	
	
public class m	
extends FilterAdapter {	
    private static Log c = LogFactory.getLog(m.class);	
    private String ALLATORIxDEMO;	
	
    @Override	
    public void init(DataSourceProxy dataSourceProxy) {	
        String a;	
        if (!(dataSourceProxy instanceof DruidDataSource)) {	
            c.error("ConfigFilte only suppot DuidDataSource");	
        }	
        DruidDataSource a2 = (DruidDataSource)dataSourceProxy;	
        String a3 = "72d1af0aec0114d3300ddb40cc17e90b";	
        if (ObjectUtils.toBoolean(Global.getProperty(new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".encrypt.username").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a2;	
            String string = druidDataSource.getUsername();	
            a = Global.getPropertyDecodeAndEncode(a3, this.ALLATORIxDEMO + ".username", a);	
            druidDataSource.setUsername(a);	
        }	
        if (ObjectUtils.toBoolean(Global.getProperty(new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".encrypt.passwod").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a2;	
            a = druidDataSource.getPassword();	
            a = Global.getPropertyDecodeAndEncode(a3, new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".password").toString(), a);	
            druidDataSource.setPassword(a);	
        }	
    }	
	
    public m(String jdbcPrefix) {	
        this.ALLATORIxDEMO = jdbcPrefix;	
    }	
}	
	
