/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.alibaba.druid.filter.FilterAdapter	
 *  com.alibaba.druid.pool.DruidDataSource	
 *  com.alibaba.druid.proxy.jdbc.DataSourceProxy	
 *  com.alibaba.druid.support.logging.Log	
 *  com.alibaba.druid.support.logging.LogFactory	
 *  com.jeesite.common.lang.ObjectUtils	
 */	
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
	
public class E	
extends FilterAdapter {	
    private static Log c = LogFactory.getLog(E.class);	
    private String ALLATORIxDEMO;	
	
    public void init(DataSourceProxy dataSourceProxy) {	
        String a2;	
        if (!(dataSourceProxy instanceof DruidDataSource)) {	
            c.error("ConfigFilter only support DruidDataSource");	
        }	
        DruidDataSource a3 = (DruidDataSource)dataSourceProxy;	
        String a4 = "72d1af0aec0114d3300ddb40cc17e90b";	
        if (ObjectUtils.toBoolean((Object)Global.getProperty(new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".encrypt.username").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a3;	
            String string = druidDataSource.getUsername();	
            a2 = Global.getPropertyDecodeAndEncode(a4, this.ALLATORIxDEMO + ".username", a2);	
            druidDataSource.setUsername(a2);	
        }	
        if (ObjectUtils.toBoolean((Object)Global.getProperty(new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".encrypt.password").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a3;	
            a2 = druidDataSource.getPassword();	
            a2 = Global.getPropertyDecodeAndEncode(a4, new StringBuilder().insert(0, this.ALLATORIxDEMO).append(".password").toString(), a2);	
            druidDataSource.setPassword(a2);	
        }	
    }	
	
    public E(String jdbcPrefix) {	
        this.ALLATORIxDEMO = jdbcPrefix;	
    }	
}	
	
